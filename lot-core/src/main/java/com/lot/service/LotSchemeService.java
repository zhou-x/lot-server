package com.lot.service;

import com.lot.vo.lotScheme.LotSchemeSaveVo;

public interface LotSchemeService {

    void wxSave(LotSchemeSaveVo saveVo);

    Object getSchemeListByUserId();
}
