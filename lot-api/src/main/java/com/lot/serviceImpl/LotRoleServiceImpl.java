package com.lot.serviceImpl;

import com.lot.Base.LotRole;
import com.lot.Base.PutMap;
import com.lot.mapper.LotRoleMapper;
import com.lot.service.LotRoleService;
import com.lot.util.Msg;
import com.lot.vo.lotRole.LotRoleSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class LotRoleServiceImpl implements LotRoleService {
    @Autowired
    private LotRoleMapper lotRoleMapper;

    @Override
    public Set<String> getRoleByAccount(String username) {
        List<LotRole> lotRoleList = lotRoleMapper.getRoleBy(username);
        Set<String> result = new HashSet<>();
        if (lotRoleList != null) {
            for (LotRole lotRole : lotRoleList) {
                result.add(lotRole.getRoleName());
            }
        }
        return result;
    }

    @Override
    public Msg findList(LotRoleSearchVo lotRoleSearchVo) {
        Map<String, Object> conMap = new PutMap().returnMap(lotRoleSearchVo);
        List<LotRole> list = lotRoleMapper.findList(conMap);
        return Msg.success().add("role", list).add("total", lotRoleMapper.getCount(conMap));
    }
}
