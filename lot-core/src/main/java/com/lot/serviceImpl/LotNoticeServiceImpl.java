package com.lot.serviceImpl;

import com.lot.Base.PutMap;
import com.lot.Base.ReflectUtil;
import com.lot.dao.LotNoticeDao;
import com.lot.entity.LotFileEntity;
import com.lot.entity.LotNoticeEntity;
import com.lot.mapper.LotNoticeMapper;
import com.lot.service.LotNoticeService;
import com.lot.util.DateUtils;
import com.lot.util.IDKeyUtil;
import com.lot.util.Msg;
import com.lot.vo.lotNotice.LotNoticeSaveVo;
import com.lot.vo.lotNotice.LotNoticeSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LotNoticeServiceImpl implements LotNoticeService {
    @Autowired
    private LotNoticeMapper lotNoticeMapper;
    @Autowired
    private LotNoticeDao lotNoticeDao;

    @Override
    public Msg findList(LotNoticeSearchVo searchVo) {
        Map<String, Object> conMap = new PutMap().returnMap(searchVo);
        if (searchVo.getTitle() != null && !searchVo.getTitle().equals("")) {
            conMap.put("title", searchVo.getTitle());
        }
        if (searchVo.getIsHide() != null && !searchVo.getIsHide().equals("")) {
            conMap.put("isHide", searchVo.getIsHide());
        }
        if (searchVo.getIsRecommend() != null && !searchVo.getIsRecommend().equals("")) {
            conMap.put("isRecommend", searchVo.getIsRecommend());
        }
        if (searchVo.getStatus() != null && !searchVo.getStatus().equals("")) {
            conMap.put("status", searchVo.getStatus());
        }
        if (searchVo.getTodayDate() != null && searchVo.getTodayDate().equals("Y")) {
            conMap.put("createTimeStart", DateUtils.getDayBegin(DateUtils.getNow()));
            conMap.put("createTimeEnd", DateUtils.getDayEnd(DateUtils.getNow()));
        }
        if (searchVo.getModel() != null && !searchVo.getModel().equals("")) {
            conMap.put("model", searchVo.getModel());
        }
        if (searchVo.getIsReadCount() != null && !searchVo.getIsReadCount().equals("")) {
            conMap.put("isReadCount", searchVo.getIsReadCount());
        }
        return Msg.success().add("list", lotNoticeMapper.findList(conMap)).add("total", lotNoticeMapper.getCount(conMap));
    }

    @Override
    public Object get(String noticeId) {
        LotNoticeEntity lotNoticeEntity = lotNoticeDao.getOne(noticeId);
        Map<String, Object> newMap = new HashMap<String, Object>();
        if (lotNoticeEntity.getImgPath() != null) {
            List<LotFileEntity> list = new ArrayList<>();
            LotFileEntity lotFileEntity = new LotFileEntity();
            lotFileEntity.setUrl(lotNoticeEntity.getImgPath());
            lotFileEntity.setStatus("done");
            list.add(lotFileEntity);
            newMap.put("imgPaths", list);
        } else {
            newMap.put("imgPaths", null);
        }
        Object obj = ReflectUtil.getTarget(lotNoticeEntity, newMap);
        return obj;
    }

    @Override
    public Object wxGet(String noticeId) {
        LotNoticeEntity lotNoticeEntity = lotNoticeDao.getOne(noticeId);
        Map<String, Object> newMap = new HashMap<String, Object>();
        if (lotNoticeEntity.getImgPath() != null) {
            List<LotFileEntity> list = new ArrayList<>();
            LotFileEntity lotFileEntity = new LotFileEntity();
            lotFileEntity.setUrl(lotNoticeEntity.getImgPath());
            lotFileEntity.setStatus("done");
            list.add(lotFileEntity);
            newMap.put("imgPaths", list);
        } else {
            newMap.put("imgPaths", null);
        }
        lotNoticeEntity.setReadCount(lotNoticeEntity.getReadCount() + 1);
        lotNoticeDao.save(lotNoticeEntity);
        Object obj = ReflectUtil.getTarget(lotNoticeEntity, newMap);
        return obj;
    }

    @Override
    public void save(LotNoticeSaveVo saveVo) {
        LotNoticeEntity lotNoticeEntity;
        if (saveVo.getNoticeId() != null) {
            lotNoticeEntity = lotNoticeDao.getOne(saveVo.getNoticeId());
        } else {
            lotNoticeEntity = new LotNoticeEntity();
            lotNoticeEntity.setNoticeId(IDKeyUtil.getStringId());
        }
        lotNoticeEntity = (LotNoticeEntity) saveVo.setInitial(lotNoticeEntity);
        lotNoticeEntity.setContent(saveVo.getContent());
        lotNoticeEntity.setTitle(saveVo.getTitle());
        lotNoticeEntity.setModel(saveVo.getModel());
        lotNoticeEntity.setOrders(saveVo.getOrders());
        lotNoticeEntity.setReadCount(saveVo.getReadCount());
        lotNoticeEntity.setIsHide(saveVo.getIsHide());
        if (saveVo.getImgPaths() != null) {
            lotNoticeEntity.setImgPath(saveVo.getImgPaths().get(0).getUrl());
        }
        lotNoticeEntity.setIsRecommend(saveVo.getIsRecommend());
        lotNoticeEntity.setStatus(saveVo.getStatus());

        lotNoticeDao.save(lotNoticeEntity);
    }

    @Override
    public void delete(String noticeId) {
        LotNoticeEntity lotNoticeEntity = lotNoticeDao.getOne(noticeId);
        lotNoticeEntity.setYnFlag("N");
        lotNoticeDao.save(lotNoticeEntity);
    }

    @Override
    public List<LotNoticeEntity> getNoticeList() {
        return lotNoticeMapper.getNoticeList();
    }

}
