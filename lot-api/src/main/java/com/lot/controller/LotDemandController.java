package com.lot.controller;

import com.lot.service.LotDemandService;
import com.lot.util.Msg;
import com.lot.vo.lotDemand.LotDemandSaveVo;
import com.lot.vo.lotDemand.LotDemandSearchVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/lot/demand")
public class LotDemandController {
    @Autowired
    private LotDemandService lotDemandService;

    // 把"" 转化成null
    @InitBinder
    public void bind(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }


    @PostMapping("/findlist")
    @ApiOperation("获取需求列表")
    public Msg findList(@RequestBody LotDemandSearchVo searchVo){
        return lotDemandService.findList(searchVo);
    }

    @GetMapping("/get")
    @ApiOperation("获取单个需求")
    public Msg get(@RequestParam String demandId){
        return Msg.success().add("demand",lotDemandService.get(demandId));
    }

    @PostMapping("/save")
    @Transactional
    @ApiOperation("保存需求")
    public Msg save(@RequestBody LotDemandSaveVo saveVo){
        lotDemandService.save(saveVo);
        return Msg.success();
    }

    @PostMapping("/delete")
    @Transactional
    @ApiOperation("删除单个需求")
    public Msg delete(@RequestParam String demandId){
        lotDemandService.delete(demandId);
        return Msg.success();
    }
}
