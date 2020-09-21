package com.lot.serviceImpl;

import com.lot.Base.MenuTree;
import com.lot.config.exception.UnauthorizedException;
import com.lot.dao.LotRoleDao;
import com.lot.dao.LotRoutesDao;
import com.lot.mapper.LotRoutesMapper;
import com.lot.service.LotRoutesService;
import com.lot.vo.LotRoutes.LotRoutesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotRoutesServiceImpl implements LotRoutesService {
    @Autowired
    private LotRoutesMapper lotRoutesMapper;
    @Autowired
    private LotRoutesDao lotRoutesDao;
    @Autowired
    private LotRoleDao lotRoleDao;

    @Override
    public List<LotRoutesVo> findList() {
        MenuTree menuTree = new MenuTree(lotRoutesMapper.findList());
        return menuTree.buildMenu();
    }

    @Override
    public LotRoutesVo get(String routesId) {
        try {
            LotRoutesVo lotRoutesVo = new LotRoutesVo().from(lotRoutesDao.getOne(routesId));
            String[] authorityDesc = new String[lotRoutesVo.getAuthority().length];
            for (int i = 0; i < lotRoutesVo.getAuthority().length; i++) {
                String authority = lotRoutesVo.getAuthority()[i].substring(lotRoutesVo.getAuthority()[i].indexOf("[") + 1, lotRoutesVo.getAuthority()[i].indexOf("]"));
                authorityDesc[i] = lotRoleDao.getByName(authority).getRoleDesc();
            }
            lotRoutesVo.setAuthorityDesc(authorityDesc);
            return lotRoutesVo;
        } catch (Exception e) {
            new UnauthorizedException("根据路由Id获取出错");
        }
        return null;
    }

}
