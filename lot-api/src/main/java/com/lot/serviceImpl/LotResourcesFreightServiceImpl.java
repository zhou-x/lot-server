package com.lot.serviceImpl;

import com.lot.Base.PutMap;
import com.lot.dao.LotResourcesFreightDao;
import com.lot.entity.LotResourcesFreightEntity;
import com.lot.mapper.LotResourcesFreightMapper;
import com.lot.service.LotResourcesFreightService;
import com.lot.util.IDKeyUtil;
import com.lot.util.Msg;
import com.lot.vo.lotResourcesFreight.LotResourcesFreightSaveVo;
import com.lot.vo.lotResourcesFreight.LotResourcesFreightSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class LotResourcesFreightServiceImpl implements LotResourcesFreightService {
    @Autowired
    private LotResourcesFreightMapper lotResourcesFreightMapper;
    @Autowired
    private LotResourcesFreightDao lotResourcesFreightDao;

    @Override
    public Msg findList(LotResourcesFreightSearchVo searchVo) {
        Map<String, Object> conMap = new PutMap().returnMap(searchVo);
        return Msg.success().add("list", lotResourcesFreightMapper.findList(conMap)).add("total", lotResourcesFreightMapper.getCount(conMap));
    }

    @Override
    public LotResourcesFreightEntity get(String resourcesFreightId) {
        return lotResourcesFreightDao.getOne(resourcesFreightId);
    }

    @Override
    public void save(LotResourcesFreightSaveVo saveVo) {
        LotResourcesFreightEntity lotResourcesFreightEntity;
        if (saveVo.getResourcesFreightId() != null) {
            lotResourcesFreightEntity = lotResourcesFreightDao.getOne(saveVo.getResourcesFreightId());
        } else {
            lotResourcesFreightEntity = new LotResourcesFreightEntity();
            lotResourcesFreightEntity.setResourcesFreightId(IDKeyUtil.getStringId());
        }
        lotResourcesFreightEntity = (LotResourcesFreightEntity) saveVo.setInitial(lotResourcesFreightEntity);
        lotResourcesFreightEntity.setAddress(saveVo.getAddress());
        lotResourcesFreightEntity.setFreight(saveVo.getFreight());
        lotResourcesFreightEntity.setSubordinateArea(saveVo.getSubordinateArea());
        lotResourcesFreightDao.save(lotResourcesFreightEntity);
    }

    @Override
    public void delete(String resourcesFreightId) {
        LotResourcesFreightEntity lotResourcesFreightEntity = lotResourcesFreightDao.getOne(resourcesFreightId);
        lotResourcesFreightEntity.setYnFlag("N");
        lotResourcesFreightDao.save(lotResourcesFreightEntity);
    }
}
