package com.lot.serviceImpl;

import com.lot.dao.LotProblemFeedbackDao;
import com.lot.entity.LotProblemFeedbackEntity;
import com.lot.service.LotProblemFeedbackService;
import com.lot.util.IDKeyUtil;
import com.lot.util.JwtUtil;
import com.lot.vo.lotproblemFeedback.LotProblemFeedbackSaveVo;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class LotProblemFeedbackServiceImpl implements LotProblemFeedbackService {
    private static final String AUTHORIZATION = "Authorization";
    @Autowired
    private LotProblemFeedbackDao lotProblemFeedbackDao;
    @Autowired
    private HttpServletRequest request;

    @Override
    public void save(LotProblemFeedbackSaveVo saveVo) {
        String token = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        String userId = "";
        if (token != null) {
            final Jws<Claims> claimsJws = JwtUtil.parseToken(token);
            final Claims body = claimsJws.getBody();
            userId = String.valueOf(body.get("userId"));
        }
        LotProblemFeedbackEntity lotProblemFeedbackEntity = new LotProblemFeedbackEntity();
        lotProblemFeedbackEntity = (LotProblemFeedbackEntity) saveVo.setInitial(lotProblemFeedbackEntity);
        lotProblemFeedbackEntity.setProblemFeedbackId(IDKeyUtil.getStringId());
        lotProblemFeedbackEntity.setContent(saveVo.getContent());
        lotProblemFeedbackEntity.setStatus("N");
        lotProblemFeedbackEntity.setUserId(userId);
        lotProblemFeedbackDao.save(lotProblemFeedbackEntity);
    }
}
