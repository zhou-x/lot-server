package com.lot.wechatApi;

import com.lot.service.LotResourcesManageService;
import com.lot.util.Msg;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lot/weixin/open/resourcesmanage")
public class WxLotResourcesManageController {
    @Autowired
    private LotResourcesManageService lotResourcesManageService;

    @PostMapping("/getlist")
    @ApiOperation("/获取小程序首页列表")
    public Msg getSimpleList(@RequestParam String address) {
        return Msg.success().add("list", lotResourcesManageService.getSimpleList(address));
    }

    @PostMapping("/getaddresslist")
    @ApiOperation("获取地区数组")
    public Msg getAddressList(){
        return lotResourcesManageService.getAddressList();
    }
}
