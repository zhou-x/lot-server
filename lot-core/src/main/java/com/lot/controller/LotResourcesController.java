package com.lot.controller;


import com.lot.service.LotResourcesService;
import com.lot.util.Msg;
import com.lot.vo.lotResources.LotResourcesSaveVo;
import com.lot.vo.lotResources.LotResourcesSearchVo;
import com.lot.vo.lotResources.SubPrices;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@Api("管理资源")
@RestController
@RequestMapping("/lot/resources")
public class LotResourcesController {
    @Autowired
    private LotResourcesService lotResourcesService;

    // 把"" 转化成null
    @InitBinder
    public void bind(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @PostMapping("/findlist")
    @ApiOperation("资源数据列表")
    public Msg findList(@RequestBody LotResourcesSearchVo searchVo) {
        return lotResourcesService.findList(searchVo);
    }

    @GetMapping("/get")
    @ApiOperation("获取单个资源数据")
    public Msg get(@RequestParam String resourcesId) {
        return Msg.success().add("resources", lotResourcesService.get(resourcesId));
    }

    @PostMapping("/save")
    @Transactional
    @ApiOperation("保存资源数据")
    public Msg save(@RequestBody LotResourcesSaveVo saveVo) {
        lotResourcesService.save(saveVo);
        return Msg.success();
    }

    @PostMapping("/delete")
    @Transactional
    @ApiOperation("删除单个资源数据")
    public Msg delete(@RequestParam String resourcesId) {
        lotResourcesService.delete(resourcesId);
        return Msg.success();
    }

    @PostMapping("/addSubPrice")
    @Transactional
    @ApiOperation("修改所有的数据，在之前的基础上加减（ps:请不要随意动此接口，性能消耗比较大，请选择在流量低谷的时候调用）")
    public Msg addSubPrice(@RequestParam int price) {
        lotResourcesService.addSubPrice(price);
        return Msg.success();
    }

    @PostMapping("/addSubPrices")
    @Transactional
    @ApiOperation("根据条件规则修改资源数据（主页和详情页的数据部分同步）")
    public Msg addSubPrices(@RequestBody SubPrices subPrices) {
        lotResourcesService.addSubPrices(subPrices);
        return Msg.success();
    }

    @PostMapping("/getSaveData")
    @ApiOperation("拿到展示到后台规格的数据")
    public Msg getSaveData() {
        return Msg.success().add("saveData", lotResourcesService.getSaveData());
    }
}
