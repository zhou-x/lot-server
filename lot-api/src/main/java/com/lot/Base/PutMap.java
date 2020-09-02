package com.lot.Base;

import java.util.HashMap;
import java.util.Map;

/**
 * 主要用于findList时向mybatis输入公共数据
 * @author zel
 * */
public class PutMap {
    public Map<String,Object> returnMap(BaseSearchVo t){
        Map<String,Object> conMap = new HashMap<>();
        conMap.put("orderColumn",t.getOrderColumn());
        conMap.put("orderDirection",t.getOrderDirection());
        conMap.put("index",(t.getPagesVo().getCurrent()-1)*t.getPagesVo().getPageSize());
        conMap.put("pageSize",t.getPagesVo().getPageSize());
        conMap.put("ynFlag",t.getYnFlag());
        return conMap;
    }
}
