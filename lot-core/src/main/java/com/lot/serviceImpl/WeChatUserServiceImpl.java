package com.lot.serviceImpl;

import com.alibaba.fastjson.JSONObject;
import com.lot.dao.LotRoleDao;
import com.lot.dao.LotUserDao;
import com.lot.dao.LotUserRoleDao;
import com.lot.entity.LotRoleEntity;
import com.lot.entity.LotUserEntity;
import com.lot.entity.LotUserRoleEntity;
import com.lot.mapper.LotUserMapper;
import com.lot.service.WeChatUserService;
import com.lot.util.*;
import com.lot.vo.lotUser.LotWeChatUserLogin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.util.HashMap;
import java.util.Map;

@Service
public class WeChatUserServiceImpl implements WeChatUserService {
    // 微信小程序ID
    private static final String appid = "wx31ce54a0aabc3d0a";
    // 微信小程序秘钥
    private static final String secret = "49d271401ab3ae1db437aa93564f0da3";

    private static final String AUTHORIZATION = "Authorization";
    @Autowired
    private LotUserDao lotUserDao;
    @Autowired
    private LotUserMapper lotUserMapper;
    @Autowired
    private LotUserRoleDao lotUserRoleDao;
    @Autowired
    private LotRoleDao lotRoleDao;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public String weChatUserLogin(LotWeChatUserLogin login) {
        // 根据小程序传过来的code想这个url发送请求
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + appid + "&secret=" + secret + "&js_code=" + login.getCode() + "&grant_type=authorization_code";
        // 发送请求，返回Json字符串
        String str = WeChatUtil.httpRequest(url, "GET", null);
        // 转成Json对象 获取openid
        JSONObject jsonObject = JSONObject.parseObject(str);
//        System.out.println(jsonObject);
        // 我们需要的openid，在一个小程序中，openid是唯一的
        String openid = jsonObject.get("openid").toString();
        String session_key = jsonObject.get("session_key").toString();
        // 书写处理逻辑
        LotUserEntity lotUserEntity;
        String userId = IDKeyUtil.getStringId();
        if (lotUserMapper.getCountByOpenId(openid) == 0) {
            lotUserEntity = new LotUserEntity();
            lotUserEntity.setId(userId);
            //新用户授角色
            LotRoleEntity lotRoleEntity = lotRoleDao.getByName("wechatUser");
            LotUserRoleEntity lotUserRoleEntity = new LotUserRoleEntity();
            lotUserRoleEntity.setId(IDKeyUtil.getStringId());
            lotUserRoleEntity.setRoleId(lotRoleEntity.getId());
            lotUserRoleEntity.setUserId(lotUserEntity.getId());
            lotUserRoleEntity.setYnFlag("Y");
            lotUserRoleDao.save(lotUserRoleEntity);
        } else {
            lotUserEntity = lotUserMapper.getUserByOpenId(openid);
            userId = lotUserEntity.getId();
            //老用户已经有权限，多次授角色会崩溃...
        }
        lotUserEntity.setOpenId(openid);
        lotUserEntity.setAvatar(login.getAvatarUrl());
        lotUserEntity.setUsername(login.getNickName());
        lotUserEntity.setYnFlag("Y");
        lotUserEntity.setStatus("1");
        lotUserEntity.setProvince(login.getCity());
        lotUserEntity.setGender(login.getGender());
        lotUserDao.save(lotUserEntity);

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        String token = JwtUtil.createToken(userId, "weixin", map);

        redisUtil.set(userId, session_key, -1);   //将session_key保存到缓存中，设置永久保存，自行删除

        return token;
    }

    public Msg getPhoneNumber(String encryptedData, String iv, String token) throws InvalidAlgorithmParameterException, UnsupportedEncodingException {
//        String token = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        String session_key = "";
        String userId = "";
        if (token != null) {
            final Jws<Claims> claimsJws = JwtUtil.parseToken(token);
            final Claims body = claimsJws.getBody();
            userId = String.valueOf(body.get("userId"));
            session_key = String.valueOf(redisUtil.get(userId));
        }

        Msg msg = null;
        try {
            String result = WxUtils.wxDecrypt(encryptedData, session_key, iv);
            JSONObject json = JSONObject.parseObject(result);
            if (json.containsKey("phoneNumber")) {
                String phone = json.getString("phoneNumber");
//                String appid = json.getJSONObject("watermark").getString("appid");
                if (StringUtils.isNotBlank(phone)) {
                    //保存号码
                    LotUserEntity lotUserEntity = lotUserDao.getOne(userId);
                    lotUserEntity.setTelphone(phone);
                    lotUserDao.save(lotUserEntity);
                    msg = Msg.success().add("phoneNumber", phone);
                } else {
                    msg = Msg.error("失败！用户未绑定手机号");
                }
            } else {
                msg = Msg.error("获取失败");
            }
        } catch (Exception e) {
            msg = Msg.error("获取失败");
        }
        return msg;
    }
}
