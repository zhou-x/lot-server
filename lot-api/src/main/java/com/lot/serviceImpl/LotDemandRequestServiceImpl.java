package com.lot.serviceImpl;

import com.lot.Base.PutMap;
import com.lot.Base.ReflectUtil;
import com.lot.dao.LotDemandRequestDao;
import com.lot.dao.LotUserDao;
import com.lot.entity.LotDemandRequestEntity;
import com.lot.entity.LotFileEntity;
import com.lot.entity.LotUserEntity;
import com.lot.mapper.LotDemandRequestMapper;
import com.lot.service.LotDemandRequestService;
import com.lot.util.IDKeyUtil;
import com.lot.util.Msg;
import com.lot.vo.lotDemandRequest.LotDemandRequestSaveVo;
import com.lot.vo.lotDemandRequest.LotDemandRequestSearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LotDemandRequestServiceImpl implements LotDemandRequestService {
    @Autowired
    private LotDemandRequestMapper lotDemandRequestMapper;
    @Autowired
    private LotDemandRequestDao lotDemandRequestDao;
    @Autowired
    private LotUserDao lotUserDao;
    @Autowired
    private HttpServletRequest request;

    @Override
    public Msg findList(LotDemandRequestSearchVo searchVo) {
        Map<String, Object> conMap = new PutMap().returnMap(searchVo);
        List<LotDemandRequestEntity> list = lotDemandRequestMapper.findList(conMap);
        List<Object> newList = new ArrayList<>();
        if (list != null) {
            list.forEach(e -> {
                String[] strArr = null;
                if (e.getDemandImgPath() != null) {
                    strArr = e.getDemandImgPath().split(",");
                }
                Map<String, Object> newMap = new HashMap<String, Object>();
                if (e.getUserId() != null) {
                    LotUserEntity lotUserEntity = lotUserDao.getOne(e.getUserId());
                    newMap.put("imgList", strArr);
                    newMap.put("userName", lotUserEntity.getUsername());
                    newMap.put("telphone", lotUserEntity.getTelphone());
                }
                Object obj = ReflectUtil.getTarget(e, newMap);
                newList.add(obj);
            });
        }

        return Msg.success().add("list", newList).add("total", lotDemandRequestMapper.getCount(conMap));
    }

    @Override
    public Object get(String demandRequestId) {
        LotDemandRequestEntity lotDemandRequestEntity = lotDemandRequestDao.getOne(demandRequestId);
        Map<String, Object> newMap = new HashMap<String, Object>();
        String[] strArr = null;
        LotUserEntity lotUserEntity = lotUserDao.getOne(lotDemandRequestEntity.getUserId());
        if (lotDemandRequestEntity.getDemandImgPath() != null) {
            List<LotFileEntity> list = new ArrayList<>();
            strArr = lotDemandRequestEntity.getDemandImgPath().split(",");
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
        Object obj = ReflectUtil.getTarget(lotDemandRequestEntity, newMap);
        return obj;
    }

    @Override
    public void save(LotDemandRequestSaveVo saveVo) {
        LotDemandRequestEntity lotDemandRequestEntity;
        if (saveVo.getDemandRequestId() != null) {
            lotDemandRequestEntity = lotDemandRequestDao.getOne(saveVo.getDemandRequestId());
        } else {
            lotDemandRequestEntity = new LotDemandRequestEntity();
            lotDemandRequestEntity.setDemandRequestId(IDKeyUtil.getStringId());
        }
        lotDemandRequestEntity = (LotDemandRequestEntity) saveVo.setInitial(lotDemandRequestEntity);
        lotDemandRequestEntity.setDemandContent(saveVo.getDemandContent());
        if (saveVo.getImgPaths() != null && saveVo.getImgPaths().size() != 0) {
            saveVo.getImgPaths().forEach(e -> {

            });
            lotDemandRequestEntity.setDemandImgPath(saveVo.getImgPaths().get(0).getUrl());
        }
        String imgPaths = "";
        if (saveVo.getImgPaths() != null && saveVo.getImgPaths().size() != 0) {
            for (int i = 0; i < saveVo.getImgPaths().size(); i++) {
                if (saveVo.getImgPaths().size() == i + 1) {
                    imgPaths += saveVo.getImgPaths().get(i).getUrl();
                } else {
                    imgPaths += saveVo.getImgPaths().get(i).getUrl() + ",";
                }
            }
        }
        lotDemandRequestEntity.setDemandImgPath(imgPaths);
        lotDemandRequestEntity.setRemark(saveVo.getRemark());
        lotDemandRequestEntity.setStatus(saveVo.getStatus());
        lotDemandRequestEntity.setUserId(saveVo.getUserId());

        lotDemandRequestDao.save(lotDemandRequestEntity);
    }

    @Override
    public void delete(String demandRequestId) {
        LotDemandRequestEntity lotDemandRequestEntity = lotDemandRequestDao.getOne(demandRequestId);
        lotDemandRequestEntity.setYnFlag("N");
        lotDemandRequestDao.save(lotDemandRequestEntity);
    }

}
