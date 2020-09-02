package com.lot.controller;

import com.lot.service.LotResourcesFreightService;
import com.lot.util.Msg;
import com.lot.vo.lotResourcesFreight.LotResourcesFreightSaveVo;
import com.lot.vo.lotResourcesFreight.LotResourcesFreightSearchVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/lot/freight")
public class LotResourcesFreightController {
    @Autowired
    private LotResourcesFreightService lotResourcesFreightService;

    // 把"" 转化成null
    @InitBinder
    public void bind(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @PostMapping("/findlist")
    @ApiOperation("运费列表")
    public Msg findList(@RequestBody LotResourcesFreightSearchVo searchVo) {
        return lotResourcesFreightService.findList(searchVo);
    }

    @GetMapping("/get")
    @ApiOperation("获取单个运费数据")
    public Msg get(@RequestParam String resourcesFreightId) {
        return Msg.success().add("freightData", lotResourcesFreightService.get(resourcesFreightId));
    }

    @PostMapping("/save")
    @Transactional
    @ApiOperation("保存运费数据")
    public Msg save(@RequestBody LotResourcesFreightSaveVo saveVo) {
        lotResourcesFreightService.save(saveVo);
        return Msg.success();
    }

    @PostMapping("/delete")
    @Transactional
    @ApiOperation("删除单个运费数据")
    public Msg delete(@RequestParam String resourcesFreightId) {
        lotResourcesFreightService.delete(resourcesFreightId);
        return Msg.success();
    }
}
