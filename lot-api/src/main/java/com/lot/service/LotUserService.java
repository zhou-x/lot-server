package com.lot.service;

import com.lot.util.Msg;
import com.lot.vo.lotUser.LotUser;
import com.lot.vo.lotUser.LotUserSaveVo;
import com.lot.vo.lotUser.LotUserSearchVo;

public interface LotUserService {

    public LotUser getUser(String username);

    /**
     * 获取User的List
     * */
    public Msg findList(LotUserSearchVo lotUserSearchVo);

    /**
     * 用户注册
     * */
    public boolean register(LotUserSaveVo lotUserSaveVo);

    /**
     * 判断是否已经存在用户名
     *
     * @return*/
    public Boolean isUserName(String username);

    public Object getUserById(String userId);

    public String saveUser(LotUserSaveVo saveVo);

    String delete(String userId);
}
