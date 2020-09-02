package com.lot.controller;

import com.lot.service.LotRoleService;
import com.lot.service.LotUserService;
import com.lot.util.Msg;
import com.lot.util.RedisUtil;
import com.lot.vo.lotUser.LotUserLogin;
import com.lot.vo.lotUser.LotUserSaveVo;
import com.wf.captcha.SpecCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.UUID;

/**
 * @Author zel
 * @Date 2020-05-21
 */
@Api(value = "用户信息")
@RestController
@RequestMapping("/lot/user")
public class LotUserController {

    @Autowired
    private LotUserService lotUserService;
    @Autowired
    private LotRoleService lotRoleService;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("login")
    @ApiOperation("用户登录")
    public Msg login(HttpServletRequest request, @RequestBody @Valid LotUserLogin lotUserLogin) {
        // 获取redis中的验证码
        String redisCode = (String) redisUtil.get(lotUserLogin.getVerKey());
        // 判断验证码通过则再进行账号验证
        if (lotUserLogin.getVerCode() == null || !redisCode.equals(lotUserLogin.getVerCode().trim().toLowerCase())) {
            return Msg.error().add("", "验证码不正确");
        } else {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(lotUserLogin.getUsername(), lotUserLogin.getPassword());
            try {
                subject.login(token);
                Session session = subject.getSession();
                session.setAttribute("subject", subject);
                //验证完成后让验证码失效
                redisUtil.del(lotUserLogin.getVerKey());
                //登录日志

                return Msg.login(String.valueOf(lotRoleService.getRoleByAccount(lotUserLogin.getUsername()))).add("ok", "登录成功").add("sessionId", session.getId());
            } catch (AuthenticationException e) {
                //验证完成后让验证码失效
                redisUtil.del(lotUserLogin.getVerKey());
                return Msg.errorUserPassword().add("", "验证失败");
            }
        }
    }

    @PostMapping("register")
    @ApiOperation("用户注册")
    @Transactional
    public Msg register(@RequestBody LotUserSaveVo lotUserSaveVo) {
        if(lotUserService.register(lotUserSaveVo)){
            return Msg.success().add("", "注册成功！");
        }else {
            return Msg.error().add("", "注册失败！");
        }
    }

    @PostMapping("verifyaccount")
    @ApiOperation("验证账号是否已经被注册")
    public Msg verifyAccount(@RequestParam String username) {
        if (lotUserService.isUserName(username)) {
            return Msg.success().add("", "可以添加");
        } else {
            return Msg.success().add("", "已经有用户了");
        }
    }

    @PostMapping("/logout")
    @ApiOperation("登录后退出")
    public Msg logout() {
        return Msg.success().add("", "退出登录成功");
    }

    @GetMapping("/captcha")
    @ApiOperation("获取验证码")
    public Msg captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        // 设置位数
//        CaptchaUtil.out(5, request, response);
//        // 设置宽、高、位数
//        CaptchaUtil.out(130, 48, 5, request, response);
//
//        // 使用gif验证码
////        GifCaptcha gifCaptcha = new GifCaptcha(130, 48, 4);
////        CaptchaUtil.out(gifCaptcha, request, response);
//        CaptchaUtil.out(request, response);

        SpecCaptcha specCaptcha = new SpecCaptcha(180, 40, 4);
        String verCode = specCaptcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        // 存入redis并设置过期时间为30分钟
        redisUtil.set(key, verCode, 30);
        // 将key和base64返回给前端
        return Msg.success().add("key", key).add("image", specCaptcha.toBase64());
    }

}
