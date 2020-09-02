package com.lot.wechatApi;

import com.lot.service.WeChatUserService;
import com.lot.util.Msg;
import com.lot.vo.lotUser.GetPhone;
import com.lot.vo.lotUser.LotWeChatUserLogin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;

@Api("微信用户登录")
@RestController
@RequestMapping("/lot/weixin/open")
public class WechatUserController {
    @Autowired
    private WeChatUserService weChatUserService;

    // 把"" 转化成null
    @InitBinder
    public void bind(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @PostMapping("/login")
    @ApiOperation("小程序登陆接口")
    public Msg wechatUserLogin(@RequestBody LotWeChatUserLogin login) {
        return Msg.success().add("token", weChatUserService.weChatUserLogin(login));
    }

    @PostMapping("/addtelphone")
    @ApiOperation("微信用户添加手机号")
    public Msg addTelphone(@RequestBody GetPhone getPhone) throws UnsupportedEncodingException, InvalidAlgorithmParameterException {
        return weChatUserService.getPhoneNumber(getPhone.getEncryptedData(), getPhone.getIv(), getPhone.getToken());
    }

}
