package com.lot.serviceImpl;

import com.lot.Base.LotAuthority;
import com.lot.Base.PutMap;
import com.lot.dao.LotAuthorityDao;
import com.lot.dao.LotRoleAuthorityDao;
import com.lot.dao.LotRoleDao;
import com.lot.entity.LotAuthorityEntity;
import com.lot.entity.LotRoleAuthorityEntity;
import com.lot.mapper.LotAuthorityMapper;
import com.lot.service.LotAuthorityService;
import com.lot.util.IDKeyUtil;
import com.lot.util.Msg;
import com.lot.vo.lotAuthority.LotAuthorityIdList;
import com.lot.vo.lotAuthority.LotAuthoritySearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LotAuthorityServiceImpl implements LotAuthorityService {
    @Autowired
    private LotAuthorityMapper lotAuthorityMapper;
    @Autowired
    private LotAuthorityDao lotAuthorityDao;
    @Autowired
    private LotRoleDao lotRoleDao;
    @Autowired
    private LotRoleAuthorityDao lotRoleAuthorityDao;

    @Override
    public Set<String> getAuthority(String username) {
        List<LotAuthority> lotAuthorityList = lotAuthorityMapper.getAuthorityBy(username);
        Set<String> result = new HashSet<>();
        if (lotAuthorityList != null) {
            for (LotAuthority lotAuthority : lotAuthorityList) {
                result.add(lotAuthority.getCode());
            }
        }
        return result;
    }

    @Override
    public Msg findList(LotAuthoritySearchVo lotAuthoritySearchVo) {
        Map<String, Object> conMap = new PutMap().returnMap(lotAuthoritySearchVo);
        conMap.put("roleId", lotAuthoritySearchVo.getRoleId());
        List<LotAuthority> list = lotAuthorityMapper.findList(conMap);
        String roleName = null;
        if (lotAuthoritySearchVo.getRoleName() != null && lotAuthoritySearchVo.getRoleId() != null && lotAuthoritySearchVo.getRoleName().equals("none")) {
            roleName = lotRoleDao.getOne(lotAuthoritySearchVo.getRoleId()).getName();
        }
        return Msg.success().add("authority", list).add("total", lotAuthorityMapper.getCount(conMap)).add("roleName", roleName);
    }

    @Override
    public Boolean isAuthorityUrl(String url) {
        List<LotAuthorityEntity> lotAuthorityEntity = lotAuthorityDao.findAll();
        for (LotAuthorityEntity entity : lotAuthorityEntity) {
            if (url.equals(entity.getCode())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void save(LotAuthorityIdList lotAuthorityIdList) {
        lotRoleAuthorityDao.deleteByRoleId(lotAuthorityIdList.getRoleId());
        List<String> idList = lotAuthorityIdList.getAuthorityIdList();
        List<LotRoleAuthorityEntity> entityList = new ArrayList<>();
        idList.forEach(authorityId->{
            LotRoleAuthorityEntity lotRoleAuthorityEntity = new LotRoleAuthorityEntity();
            lotRoleAuthorityEntity.setId(IDKeyUtil.getStringId());
            lotRoleAuthorityEntity.setRoleId(lotAuthorityIdList.getRoleId());
            lotRoleAuthorityEntity.setAuthorityId(authorityId);
            entityList.add(lotRoleAuthorityEntity);
        });
        lotRoleAuthorityDao.saveAll(entityList);
    }

    @Override
    public Set<String> getAuthorityByOpenId(String userId) {
        List<LotAuthority> lotAuthorityList = lotAuthorityMapper.getAuthorityByOpenId(userId);
        Set<String> result = new HashSet<>();
        if (lotAuthorityList != null) {
            for (LotAuthority lotAuthority : lotAuthorityList) {
                result.add(lotAuthority.getCode());
            }
        }
        return result;
    }
}
