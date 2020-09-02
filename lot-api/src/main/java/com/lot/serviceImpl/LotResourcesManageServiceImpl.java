package com.lot.serviceImpl;

import com.lot.Base.PutMap;
import com.lot.dao.LotResourcesManageDao;
import com.lot.entity.LotResourcesManageEntity;
import com.lot.mapper.LotResourcesManageMapper;
import com.lot.service.LotResourcesManageService;
import com.lot.util.DateUtils;
import com.lot.util.IDKeyUtil;
import com.lot.util.Msg;
import com.lot.vo.lotResourcesManage.LotResourcesManageSaveVo;
import com.lot.vo.lotResourcesManage.LotResourcesManageSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LotResourcesManageServiceImpl implements LotResourcesManageService {
    @Autowired
    private LotResourcesManageMapper lotResourcesManageMapper;
    @Autowired
    private LotResourcesManageDao lotResourcesManageDao;

    @Override
    public Msg findList(LotResourcesManageSearchVo searchVo) {
        Map<String, Object> conMap = new PutMap().returnMap(searchVo);
        if (searchVo.getBrand() != null && !searchVo.getBrand().equals("")) {
            conMap.put("brand", searchVo.getBrand());
        }
        if (searchVo.getMaterial_or_model() != null && !searchVo.getMaterial_or_model().equals("")) {
            conMap.put("material_or_model", searchVo.getMaterial_or_model());
        }
        if (searchVo.getCreateDate() != null && searchVo.getCreateDate().size() != 0) {
            conMap.put("createTimeStart", DateUtils.getDayBegin(searchVo.getCreateDate().get(0)));
            conMap.put("createTimeEnd", DateUtils.getDayEnd(searchVo.getCreateDate().get(1)));
        }
        return Msg.success().add("list", lotResourcesManageMapper.findList(conMap)).add("total", lotResourcesManageMapper.getCount(conMap));
    }

    @Override
    public LotResourcesManageEntity get(String resourcesManageId) {
        return lotResourcesManageDao.getOne(resourcesManageId);
    }

    @Override
    public void save(LotResourcesManageSaveVo saveVo) {
        LotResourcesManageEntity lotResourcesManageEntity;
        if (saveVo.getResourcesManageId() != null) {
            lotResourcesManageEntity = lotResourcesManageDao.getOne(String.valueOf(saveVo.getResourcesManageId()));
        } else {
            lotResourcesManageEntity = new LotResourcesManageEntity();
            lotResourcesManageEntity.setResourcesManageId(IDKeyUtil.getStringId());
        }
        lotResourcesManageEntity = (LotResourcesManageEntity) saveVo.setInitial(lotResourcesManageEntity);
        lotResourcesManageEntity.setBrand(saveVo.getBrand());
        lotResourcesManageEntity.setModel(saveVo.getModel());
        lotResourcesManageEntity.setPrice(saveVo.getPrice());
        lotResourcesManageEntity.setMaterial(saveVo.getMaterial());
        lotResourcesManageEntity.setAddress(saveVo.getAddress());
        lotResourcesManageDao.save(lotResourcesManageEntity);
    }

    @Override
    public void delete(String resourcesManageId) {
        LotResourcesManageEntity lotResourcesManageEntity = lotResourcesManageDao.getOne(resourcesManageId);
        lotResourcesManageEntity.setYnFlag("N");
        lotResourcesManageDao.save(lotResourcesManageEntity);
    }

    @Override
    public Map<String, Object> getSimpleList(String address) {
        String[] str = address.split(",");
        Map<String, Object> map = new HashMap<>();
        List<LotResourcesManageEntity> list = new ArrayList<>();
        if (str.length >= 2) {
            list = lotResourcesManageMapper.getSimpleList(str[1]);
            map.put("address", str[1]);
            if (list.size() == 0) {
                list = lotResourcesManageMapper.getSimpleList(str[0]);
                map.put("address", str[0]);
            }
        }
        map.put("list", list);
        return map;
    }

    @Override
    public Msg getAddressList() {
        List<LotResourcesManageEntity> list = lotResourcesManageDao.findAll();
        Set<String> firstAddressList = new HashSet<>();
        list.forEach(e -> {
            if (e.getYnFlag() != null && e.getYnFlag().equals("Y")) {
                firstAddressList.add(e.getAddress());
            }
        });
        List<Object> map = new ArrayList<>();
        firstAddressList.forEach(e -> {
            List<LotResourcesManageEntity> list1 = lotResourcesManageDao.findAllByAddress(e);
            Set<String> twoAddressList = new HashSet<>();
            list1.forEach(es -> {
                if (es.getSubordinateArea() != null) {
                    twoAddressList.add(es.getSubordinateArea());
                }
            });
            map.add(twoAddressList);
        });
        return Msg.success().add("firstList", firstAddressList).add("secondaryList", map);
    }
}
