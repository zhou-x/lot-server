package com.lot.serviceImpl;

import com.lot.Base.PutMap;
import com.lot.Base.ReflectUtil;
import com.lot.dao.LotDemandHistoryDao;
import com.lot.entity.LotDemandHistoryEntity;
import com.lot.mapper.LotDemandHistoryMapper;
import com.lot.service.LotDemandHistoryService;
import com.lot.util.IDKeyUtil;
import com.lot.util.JwtUtil;
import com.lot.vo.lotDemandHistory.LotDemandHistorySearchVo;
import com.lot.vo.lotDemandHistory.LotDemandHistoryVo;
import com.lot.vo.lotDemandHistory.LotDemandQuotationVo;
import com.lot.vo.lotDemandHistory.LotUserAgree;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class LotDemandHistoryServiceImpl implements LotDemandHistoryService {
    private static final String AUTHORIZATION = "Authorization";
    @Autowired
    private LotDemandHistoryMapper lotDemandHistoryMapper;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LotDemandHistoryDao lotDemandHistoryDao;

    @Override
    public List<Object> findListByUserId(LotDemandHistorySearchVo searchVo) {
        Map<String, Object> map = new PutMap().returnMap(searchVo);
        String token = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        if (token != null) {
            final Jws<Claims> claimsJws = JwtUtil.parseToken(token);
            final Claims body = claimsJws.getBody();
            String userId = String.valueOf(body.get("userId"));
            map.put("userId", userId);
        }
        if (searchVo.getStatus() != null && !searchVo.getStatus().equals("")) {
            map.put("status", searchVo.getStatus());
        }
        List<LotDemandHistoryVo> list = lotDemandHistoryMapper.findListByUserId(map);
        List<Object> newList = new ArrayList<>();
        list.forEach(e -> {
            String[] strArr = null;
            if (e.getImgPath() != null) {
                strArr = e.getImgPath().split(",");
            }
            Map<String, Object> newMap = new HashMap<String, Object>();
            newMap.put("imgList", strArr);
            Object obj = ReflectUtil.getTarget(e, newMap);
            newList.add(obj);
        });
        return newList;
    }

    @Override       //报价操作，保存历史信息
    public void demandHistorySave(LotDemandQuotationVo vo) {
        String token = WebUtils.toHttp(request).getHeader("Authorization");
        String userId = "";
        if (token != null) {
            final Jws<Claims> claimsJws = JwtUtil.parseToken(token);
            final Claims body = claimsJws.getBody();
            userId = String.valueOf(body.get("userId"));
        }
        LotDemandHistoryEntity lotDemandHistoryEntity = new LotDemandHistoryEntity();
        lotDemandHistoryEntity = (LotDemandHistoryEntity) vo.setInitial(lotDemandHistoryEntity);
        lotDemandHistoryEntity.setStartUserId(vo.getStartUserId());
        lotDemandHistoryEntity.setOfferUserId(userId);
        lotDemandHistoryEntity.setOfferPrice(vo.getOfferPrice());
        lotDemandHistoryEntity.setStatus("YS");
        lotDemandHistoryEntity.setDemandId(vo.getDemandId());
        lotDemandHistoryEntity.setDemandHistoryId(IDKeyUtil.getStringId());
        lotDemandHistoryDao.save(lotDemandHistoryEntity);
    }

    @Override
    public void userAgree(LotUserAgree saveVo) {
        String token = WebUtils.toHttp(request).getHeader("Authorization");
        String userId = "";
        if (token != null) {
            final Jws<Claims> claimsJws = JwtUtil.parseToken(token);
            final Claims body = claimsJws.getBody();
            userId = String.valueOf(body.get("userId"));
        }
        LotDemandHistoryEntity lotDemandHistoryEntity = new LotDemandHistoryEntity();
        lotDemandHistoryEntity.setStartUserId(saveVo.getOfferUserId());
        lotDemandHistoryEntity.setOfferUserId(userId);
        lotDemandHistoryEntity.setStatus(saveVo.getStatus().equals("Y") ? "ES" : "TR");
        lotDemandHistoryEntity.setDemandId(saveVo.getDemandId());
        lotDemandHistoryEntity.setDemandHistoryId(IDKeyUtil.getStringId());
        lotDemandHistoryDao.save(lotDemandHistoryEntity);
    }
}