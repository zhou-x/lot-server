package com.lot.wechatApi;


import com.lot.service.LotResourcesHistoryService;
import com.lot.service.LotResourcesService;
import com.lot.util.Msg;
import com.lot.vo.lotResources.GenerateSchemeVo;
import com.lot.vo.lotResources.LotResourcesSearchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;

@Api("管理资源")
@RestController
@RequestMapping("/lot/weixin/open/resources")
public class WxLotResourcesController {
    @Autowired
    private LotResourcesService lotResourcesService;
    @Autowired
    private LotResourcesHistoryService lotResourcesHistoryService;

    // 把"" 转化成null
    @InitBinder
    public void bind(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @PostMapping("/getlist")
    @ApiOperation("获取小程序首页资源点击下的详细列表")
    public Msg getList(@RequestParam String address, @RequestParam String brand) {
        return Msg.success().add("list", lotResourcesService.getList(address, brand));
    }

    @PostMapping("/search")
    @ApiOperation("根据品名或材质搜索")
    public Msg searchList(@RequestParam String searchField) {
        return Msg.success().add("list", lotResourcesService.searchList(searchField));
    }

    @PostMapping("/getcombolist")
    @ApiOperation("根据条件获取列表")
    public Msg getComboList(@RequestBody LotResourcesSearchVo searchVo) {
        return lotResourcesService.findList(searchVo);
    }

    @PostMapping("/getconditionlist")
    @ApiOperation("根据条件获取列表")
    public Msg getConditionList() {
        return lotResourcesService.getConditionList();
    }

    @PostMapping("/generateScheme")
    @ApiOperation("根据条件生成方案")
    public Msg generateScheme(@RequestBody GenerateSchemeVo vo) {
        return lotResourcesService.generateScheme(vo);
    }

    @PostMapping("/findListByPrice")
    @ApiOperation("根据单个资源获取历史详情")
    public Msg findListByPrice(@RequestParam String resourcesId, @RequestParam int day) {
        return lotResourcesHistoryService.findListByPrice(resourcesId, day);
    }

    @PostMapping("/getAddressListByAddress")
    @ApiOperation("根据地址获取改地址的品牌")
    public Msg getAddressListByAddress(@RequestParam String address) {
        return lotResourcesService.getAddressListByAddress(address);
    }

    @PostMapping("/getResourcesListByDate")
    @ApiOperation("根据日期获取改日期的详细资源列表(ps:mark不为空的时候返回的结果为首页的历史价格)")
    public Msg getResourcesListByDate(@RequestParam Timestamp Date, String mark, String address, String brand) {
        return lotResourcesService.getResourcesListByDate(Date, mark, address, brand);
    }

}
