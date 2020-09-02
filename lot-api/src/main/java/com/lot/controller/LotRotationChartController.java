package com.lot.controller;

import com.lot.service.LotRotationChartService;
import com.lot.util.Msg;
import com.lot.vo.lotRotationChart.LotRotationChartSaveVo;
import com.lot.vo.lotRotationChart.LotRotationChartSearchVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/lot/rotationchart")
public class LotRotationChartController {

    @Autowired
    private LotRotationChartService lotRotationChartService;

    // 把"" 转化成null
    @InitBinder
    public void bind(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @PostMapping("/findlist")
    @ApiOperation("获取轮播图的列表数据")
    public Msg findList(@RequestBody LotRotationChartSearchVo searchVo){
        return lotRotationChartService.findList(searchVo);
    }

    @GetMapping("/get")
    @ApiOperation("获取单个轮播图数据")
    public Msg get(@RequestParam String rotationChartId){
        return Msg.success().add("rotationChart",lotRotationChartService.get(rotationChartId));
    }

    @PostMapping("/save")
    @ApiOperation("保存轮播图数据")
    @Transactional
    public Msg save(@RequestBody LotRotationChartSaveVo saveVo){
        lotRotationChartService.save(saveVo);
        return Msg.success();
    }

    @PostMapping("/delete")
    @ApiOperation("删除单个轮播图数据")
    @Transactional
    public Msg delete(@RequestParam String rotationChartId){
        lotRotationChartService.delete(rotationChartId);
        return Msg.success();
    }
}
