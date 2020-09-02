package com.lot.service;

import com.lot.entity.LotRotationChartEntity;
import com.lot.util.Msg;
import com.lot.vo.lotRotationChart.LotRotationChartSaveVo;
import com.lot.vo.lotRotationChart.LotRotationChartSearchVo;

import java.util.List;

public interface LotRotationChartService {
    public Msg findList(LotRotationChartSearchVo searchVo);
    public Object get(String rotationChartId);
    public void save(LotRotationChartSaveVo saveVo);
    public void delete(String rotationChartId);

    List<LotRotationChartEntity> getList();
}
