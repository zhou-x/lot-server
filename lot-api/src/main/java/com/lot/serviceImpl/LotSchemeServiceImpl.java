package com.lot.serviceImpl;

import com.lot.Base.ReflectUtil;
import com.lot.dao.LotSchemeDao;
import com.lot.dao.LotSpecificationDao;
import com.lot.entity.LotSchemeEntity;
import com.lot.mapper.LotSchemeMapper;
import com.lot.service.LotSchemeService;
import com.lot.util.IDKeyUtil;
import com.lot.util.JwtUtil;
import com.lot.vo.lotScheme.LotSchemeSaveVo;
import com.lot.vo.lotScheme.LotSchemeVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LotSchemeServiceImpl implements LotSchemeService {
    private static final String AUTHORIZATION = "Authorization";
    @Autowired
    private LotSchemeDao lotSchemeDao;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private LotSchemeMapper lotSchemeMapper;
    @Autowired
    private LotSpecificationDao lotSpecificationDao;


    @Override
    public void wxSave(LotSchemeSaveVo saveVo) {
        String token = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        String userId = "";
        if (token != null) {
            final Jws<Claims> claimsJws = JwtUtil.parseToken(token);
            final Claims body = claimsJws.getBody();
            userId = String.valueOf(body.get("userId"));
        }
        LotSchemeEntity lotSchemeEntity = new LotSchemeEntity();
        lotSchemeEntity.setUserId(userId);
        lotSchemeEntity.setYnFlag("Y");
        lotSchemeEntity.setSchemeId(IDKeyUtil.getStringId());
        lotSchemeEntity.setStatus("N");
        lotSchemeEntity.setAddress(saveVo.getAddress());
        lotSchemeEntity.setBrand(saveVo.getBrand());
        lotSchemeEntity.setContent(saveVo.getContent());
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
        lotSchemeEntity.setImgPaths(imgPaths);
        String unifyId = IDKeyUtil.getStringId();
        saveVo.getSpecificationList().forEach(e -> {
            e.setSpecificationId(IDKeyUtil.getStringId());
            e.setIdentifi(unifyId);
            e.setYnFlag("Y");
            e.setModel(e.getModel());
            lotSpecificationDao.save(e);
        });
        lotSchemeEntity.setSpecificationId(unifyId);
        lotSchemeDao.save(lotSchemeEntity);
    }

    @Override
    public Object getSchemeListByUserId() {
        String token = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        String userId = "";
        if (token != null) {
            final Jws<Claims> claimsJws = JwtUtil.parseToken(token);
            final Claims body = claimsJws.getBody();
            userId = String.valueOf(body.get("userId"));
        }
        List<LotSchemeVo> list = lotSchemeMapper.findAllByUserId(userId);
        List<Object> newList = new ArrayList<>();
        list.forEach(e -> {
            String[] strArr = null;
            if (e.getImgPaths() != null) {
                strArr = e.getImgPaths().split(",");
            }
            Map<String, Object> newMap = new HashMap<String, Object>();
            newMap.put("imgList", strArr);
            e.setLotSpecificationList(lotSpecificationDao.findAllByIdentifi(e.getSpecificationId()));
            Object obj = ReflectUtil.getTarget(e, newMap);
            newList.add(obj);
        });
        return newList;
    }
}
