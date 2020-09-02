package com.lot.service;

import com.lot.util.Msg;


public interface LotResourcesHistoryService {
    Msg findListByPrice(String resourcesId, int day);
}
