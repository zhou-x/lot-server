package com.lot.service;

import com.lot.entity.LotNoticeEntity;
import com.lot.util.Msg;
import com.lot.vo.lotNotice.LotNoticeSaveVo;
import com.lot.vo.lotNotice.LotNoticeSearchVo;

import java.util.List;

public interface LotNoticeService {
    Msg findList(LotNoticeSearchVo searchVo);
    Object get(String noticeId);
    Object wxGet(String noticeId);

    void save(LotNoticeSaveVo saveVo);
    void delete(String noticeId);

    List<LotNoticeEntity> getNoticeList();
}
