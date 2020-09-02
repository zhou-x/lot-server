package com.lot.mapper;

import com.lot.entity.LotOtherEntity;
import org.springframework.stereotype.Component;

@Component("LotOtherMapper")
public interface LotOtherMapper {
    LotOtherEntity get();

    void updateABOUT_US(String s);
    void updateCONTACT_US(String s);
    void updateUSER_PROTOCOL(String s);
    void updatePRIVACY_POLICY(String s);
    void updateDEMAND_EXAMPLE(String s);
    void updateCOMMON_PROBLEM(String s);
    void updateStartImgUrl(String s);
}
