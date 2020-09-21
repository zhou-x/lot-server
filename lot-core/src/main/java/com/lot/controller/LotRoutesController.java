package com.lot.controller;

import com.lot.service.LotRoutesService;
import com.lot.util.Msg;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/lot/routes")
public class LotRoutesController {
    @Autowired
    private LotRoutesService lotRoutesService;


    // 把"" 转化成null
    @InitBinder
    public void bind(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @PostMapping("/findlist")
    @ApiOperation("获取所有的路由数据")
    public Msg findList() {
        return Msg.success().add("routes", lotRoutesService.findList());
    }

    @GetMapping("/get")
    @ApiOperation("获取单个路由数据")
    public Msg get(@RequestParam String routesId) {
        return Msg.success().add("routes", lotRoutesService.get(routesId));
    }

}
