package com.lot.serviceImpl;

import com.lot.Base.MenuTree;
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

    @Override
    public List<LotRoutesVo> findList() {
        MenuTree menuTree = new MenuTree(lotRoutesMapper.findList());
        return menuTree.buildMenu();
    }

}
