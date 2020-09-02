package com.lot.serviceImpl;

import com.lot.Base.PutMap;
import com.lot.Base.ReflectUtil;
import com.lot.dao.LotDemandDao;
import com.lot.dao.LotDemandRequestDao;
import com.lot.dao.LotUserDao;
import com.lot.entity.*;
import com.lot.mapper.LotDemandMapper;
import com.lot.service.LotDemandService;
import com.lot.util.DateUtils;
import com.lot.util.IDKeyUtil;
import com.lot.util.Msg;
import com.lot.vo.lotDemand.LotDemandSaveVo;
import com.lot.vo.lotDemand.LotDemandSearchVo;
import com.lot.vo.lotDemand.LotDemandVo;
import com.lot.vo.lotDemand.WxDemandSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LotDemandServiceImpl implements LotDemandService {
    @Autowired
    private LotDemandMapper lotDemandMapper;
    @Autowired
    private LotDemandDao lotDemandDao;
    @Autowired
    private LotDemandRequestDao lotDemandRequestDao;
    @Autowired
    private LotUserDao lotUserDao;

    @Override
    public Msg findList(LotDemandSearchVo searchVo) {
        Map<String, Object> conMap = new PutMap().returnMap(searchVo);
        List<LotDemandEntity> list = lotDemandMapper.findList(conMap);
        List<Object> newList = new ArrayList<>();
        list.forEach(e -> {
            String[] strArr = null;
            if (e.getImgPath() != null) {
                strArr = e.getImgPath().split(",");
            }
            Map<String, Object> newMap = new HashMap<String, Object>();
            LotUserEntity lotUserEntity = lotUserDao.getOne(e.getUserId());
            newMap.put("imgList", strArr);
            newMap.put("userName", lotUserEntity.getUsername());
            newMap.put("telphone", lotUserEntity.getTelphone());
            Object obj = ReflectUtil.getTarget(e, newMap);
            newList.add(obj);
        });
        return Msg.success().add("list", newList).add("total", lotDemandMapper.getCount(conMap));
    }

    @Override
    public Object get(String demandId) {
        LotDemandEntity lotDemandEntity = lotDemandDao.getOne(demandId);
        Map<String, Object> newMap = new HashMap<String, Object>();
        String[] strArr = null;
        LotUserEntity lotUserEntity = lotUserDao.getOne(lotDemandEntity.getUserId());
        if (lotDemandEntity.getImgPath() != null) {
            List<LotFileEntity> list = new ArrayList<>();
            strArr = lotDemandEntity.getImgPath().split(",");
            for (int i = 0; i < strArr.length; i++) {
                LotFileEntity lotFileEntity = new LotFileEntity();
                lotFileEntity.setUrl(strArr[i]);
                lotFileEntity.setStatus("done");
                list.add(lotFileEntity);
            }
            newMap.put("imgPaths", list);
        } else {
            newMap.put("imgPaths", null);
        }
        newMap.put("userName", lotUserEntity.getUsername());
        newMap.put("telphone", lotUserEntity.getTelphone());
        Object obj = ReflectUtil.getTarget(lotDemandEntity, newMap);
        return obj;
    }

    @Override
    public void save(LotDemandSaveVo saveVo) {
        LotDemandEntity lotDemandEntity;
        if (saveVo.getDemandId() != null) {
            lotDemandEntity = lotDemandDao.getOne(saveVo.getDemandId());
        } else {
            lotDemandEntity = new LotDemandEntity();
            lotDemandEntity.setDemandId(IDKeyUtil.getStringId());
        }
        lotDemandEntity = (LotDemandEntity) saveVo.setInitial(lotDemandEntity);
        lotDemandEntity.setBrand(saveVo.getBrand());
        lotDemandEntity.setMaterial(saveVo.getMaterial());
        lotDemandEntity.setModel(saveVo.getModel());
        lotDemandEntity.setStatus(saveVo.getStatus());
        lotDemandEntity.setWeight(saveVo.getWeight());
        lotDemandEntity.setUserId(saveVo.getUserId());
        String imgPaths = "";
        if (saveVo.getImgPaths() != null) {
            for (int i = 0; i < saveVo.getImgPaths().size(); i++) {
                if (saveVo.getImgPaths().size() == i + 1) {
                    imgPaths += saveVo.getImgPaths().get(i).getUrl();
                } else {
                    imgPaths += saveVo.getImgPaths().get(i).getUrl() + ",";
                }
            }
        }
        lotDemandEntity.setImgPath(imgPaths);

        LotDemandRequestEntity lotDemandRequestEntity = lotDemandRequestDao.getOne(saveVo.getDemandRequestId());
        lotDemandRequestEntity.setStatus("N");
        lotDemandRequestDao.save(lotDemandRequestEntity);

        lotDemandDao.save(lotDemandEntity);
    }

    @Override
    public void delete(String demandId) {
        LotDemandEntity lotDemandEntity = lotDemandDao.getOne(demandId);
        lotDemandEntity.setYnFlag("N");
        lotDemandDao.save(lotDemandEntity);
    }

    @Override
    public List<LotDemandVo> getListByToday(WxDemandSearchVo searchVo) {
        Map<String, Object> map = new HashMap<>();
        if (searchVo.getDate() != null && !searchVo.getDate().equals("")) {
            map.put("startDateTime", DateUtils.getDayBegin(searchVo.getDate()));
            map.put("endDateTime", DateUtils.getDayEnd(searchVo.getDate()));
        }
        if (searchVo.getBrand() != null && !searchVo.getBrand().equals("")) {
            map.put("brand", searchVo.getBrand());
        }
        if (searchVo.getMaterial() != null && !searchVo.getMaterial().equals("")) {
            map.put("material", searchVo.getMaterial());
        }
        return lotDemandMapper.getListByToday(map);
    }

    @Override
    public Msg getScreeningByBrand() {
        List<LotDemandEntity> list = lotDemandDao.findAll();
        Set<String> brandSet = new HashSet<>();
        Set<String> materialSet = new HashSet<>();
        list.forEach(e -> {
            brandSet.add(e.getBrand());
            materialSet.add(e.getMaterial());
        });
        return Msg.success().add("brandList",brandSet).add("materialList",materialSet);
    }
}
