package com.lot.mapper;

import com.lot.vo.lotScheme.LotSchemeVo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("LotSchemeMapper")
public interface LotSchemeMapper {
    List<LotSchemeVo> findAllByUserId(String userId);
}
