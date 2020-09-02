package com.lot.serviceImpl;

import com.lot.Base.PutMap;
import com.lot.Base.ReflectUtil;
import com.lot.dao.LotRotationChartDao;
import com.lot.entity.LotFileEntity;
import com.lot.entity.LotRotationChartEntity;
import com.lot.mapper.LotRotationChartMapper;
import com.lot.service.LotRotationChartService;
import com.lot.util.DateUtils;
import com.lot.util.IDKeyUtil;
import com.lot.util.Msg;
import com.lot.vo.lotRotationChart.LotRotationChartSaveVo;
import com.lot.vo.lotRotationChart.LotRotationChartSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LotRotationChartServiceImpl implements LotRotationChartService {
    @Autowired
    private LotRotationChartMapper lotRotationChartMapper;
    @Autowired
    private LotRotationChartDao lotRotationChartDao;

    @Override
    public Msg findList(LotRotationChartSearchVo searchVo) {
        Map<String, Object> conMap = new PutMap().returnMap(searchVo);
        if (searchVo.getIsHide() != null) {
            conMap.put("isHide",searchVo.getIsHide());
        }
        if (searchVo.getCreateDate() != null && searchVo.getCreateDate().size() != 0) {
            conMap.put("createTimeStart", DateUtils.getDayBegin(searchVo.getCreateDate().get(0)));
            conMap.put("createTimeEnd", DateUtils.getDayEnd(searchVo.getCreateDate().get(1)));
        }
        return Msg.success().add("list", lotRotationChartMapper.findList(conMap)).add("total", lotRotationChartMapper.getCount(conMap));
    }

    @Override
    public Object get(String rotationChartId) {
        LotRotationChartEntity lotRotationChartEntity = lotRotationChartDao.getOne(rotationChartId);
        Map<String, Object> newMap = new HashMap<String, Object>();
        if(lotRotationChartEntity.getImgPath()!=null){
            List<LotFileEntity> list = new ArrayList<>();
            LotFileEntity lotFileEntity = new LotFileEntity();
            lotFileEntity.setUrl(lotRotationChartEntity.getImgPath());
            lotFileEntity.setStatus("done");
            list.add(lotFileEntity);
            newMap.put("imgPaths", list);
        }else {
            newMap.put("imgPaths", null);
        }
        Object obj = ReflectUtil.getTarget(lotRotationChartEntity, newMap);
        return obj;
    }

    @Override
    public void save(LotRotationChartSaveVo saveVo) {
        LotRotationChartEntity lotRotationChartEntity;
        if (saveVo.getRotationChartId() == null) {
            lotRotationChartEntity = new LotRotationChartEntity();
            lotRotationChartEntity.setRotationChartId(IDKeyUtil.getStringId());
        } else {
            lotRotationChartEntity = lotRotationChartDao.getOne(saveVo.getRotationChartId());
        }
        lotRotationChartEntity = (LotRotationChartEntity) saveVo.setInitial(lotRotationChartEntity);
        lotRotationChartEntity.setImgDesc(saveVo.getImgDesc());
        lotRotationChartEntity.setImgLink(saveVo.getImgLink());
        lotRotationChartEntity.setImgPath(saveVo.getImgPaths().get(0).getUrl());
        lotRotationChartEntity.setIsHide(saveVo.getIsHide());
        lotRotationChartEntity.setOrders(String.valueOf(saveVo.getOrders()));
        lotRotationChartEntity.setRemark(saveVo.getRemark());

        lotRotationChartDao.save(lotRotationChartEntity);
    }

    @Override
    public void delete(String rotationChartId) {
        LotRotationChartEntity lotRotationChartEntity = lotRotationChartDao.getOne(rotationChartId);
        lotRotationChartEntity.setYnFlag("N");
        lotRotationChartDao.save(lotRotationChartEntity);
    }

    @Override
    public List<LotRotationChartEntity> getList() {
        return lotRotationChartMapper.getList();
    }
}
