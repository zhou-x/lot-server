package com.lot.service;

import com.lot.util.Msg;
import com.lot.vo.lotUser.LotWeChatUserLogin;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;

public interface WeChatUserService {
    String weChatUserLogin(LotWeChatUserLogin login);

    Msg getPhoneNumber(String encryptedData, String iv,String token) throws InvalidAlgorithmParameterException, UnsupportedEncodingException;
}
