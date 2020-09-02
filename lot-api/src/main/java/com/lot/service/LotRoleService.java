package com.lot.service;

import com.lot.util.Msg;
import com.lot.vo.lotRole.LotRoleSearchVo;

import java.util.Set;

public interface LotRoleService {

    Set<String> getRoleByAccount(String username);

    Msg findList(LotRoleSearchVo lotRoleSearchVo);
}
