package com.lot.mapper;


import com.lot.entity.LotCodeEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("LotCodeMapper")
public interface LotCodeMapper {

    List<LotCodeEntity> getCode(String codeBelong);

    List<LotCodeEntity> findList();
}
