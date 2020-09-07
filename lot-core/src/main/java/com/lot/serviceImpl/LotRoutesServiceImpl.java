package com.lot.serviceImpl;

import com.lot.dao.LotRoutesDao;
import com.lot.entity.LotRoutesEntity;
import com.lot.service.LotRoutesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LotRoutesServiceImpl implements LotRoutesService {
    @Autowired
    private LotRoutesDao lotRoutesDao;

    @Override
    public List<LotRoutesEntity> findList() {
        return lotRoutesDao.findAll();
    }
}
