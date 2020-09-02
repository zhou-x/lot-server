package com.lot.controller;

import com.lot.service.LotResourcesManageService;
import com.lot.util.Msg;
import com.lot.vo.lotResourcesManage.LotResourcesManageSaveVo;
import com.lot.vo.lotResourcesManage.LotResourcesManageSearchVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/lot/resourcesmanage")
public class LotResourcesManageController {
    @Autowired
    private LotResourcesManageService lotResourcesManageService;

    // 把"" 转化成null
    @InitBinder
    public void bind(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @PostMapping("/findlist")
    @ApiOperation("首页资源数据列表")
    public Msg findList(@RequestBody LotResourcesManageSearchVo searchVo){
        return lotResourcesManageService.findList(searchVo);
    }

    @GetMapping("/get")
    @ApiOperation("获取单个首页资源数据")
    public Msg get(@RequestParam String resourcesManageId){
        return Msg.success().add("resourcesManage",lotResourcesManageService.get(resourcesManageId));
    }

    @PostMapping("/save")
    @Transactional
    @ApiOperation("保存首页资源数据")
    public Msg save(@RequestBody LotResourcesManageSaveVo saveVo){
        lotResourcesManageService.save(saveVo);
        return Msg.success();
    }

    @PostMapping("/delete")
    @Transactional
    @ApiOperation("删除单个首页资源数据")
    public Msg delete(@RequestParam String resourcesManageId){
        lotResourcesManageService.delete(resourcesManageId);
        return Msg.success();
    }
}
