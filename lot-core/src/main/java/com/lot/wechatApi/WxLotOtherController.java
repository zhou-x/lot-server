package com.lot.wechatApi;

import com.lot.service.LotOtherService;
import com.lot.util.Msg;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/lot/weixin/open/other")
public class WxLotOtherController {

    @Autowired
    private LotOtherService lotOtherService;

    // 把"" 转化成null
    @InitBinder
    public void bind(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/getaboutus")
    @ApiOperation("微信获取'关于我们'的信息")
    public Msg getAboutUs(){
        return Msg.success().add("aboutUs",lotOtherService.getAboutUs());
    }

    @GetMapping("/getcontactus")
    @ApiOperation("微信获取'联系我们'的信息")
    public Msg getContactUs(){
        return Msg.success().add("aboutUs",lotOtherService.getContactUs());
    }

    @GetMapping("/getuserprotocol")
    @ApiOperation("微信获取'用户协议'的信息")
    public Msg getUserProtocol(){
        return Msg.success().add("aboutUs",lotOtherService.getUserProtocol());
    }

    @GetMapping("/getprivacypolicy")
    @ApiOperation("微信获取'隐私政策'的信息")
    public Msg getPrivacyPolicy(){
        return Msg.success().add("aboutUs",lotOtherService.getPrivacyPolicy());
    }

    @GetMapping("/getdemandexample")
    @ApiOperation("微信获取'需求示例'的信息")
    public Msg getDemandExample(){
        return Msg.success().add("aboutUs",lotOtherService.getDemandExample());
    }

    @GetMapping("/getcommonproblem")
    @ApiOperation("微信获取'常见问题'的信息")
    public Msg getCommonProblem(){
        return Msg.success().add("aboutUs",lotOtherService.getCommonProblem());
    }

    @GetMapping("/getStartImgUrl")
    @ApiOperation("微信获取初次打开小程序的图片地址")
    public Msg getStartImgUrl(){
        return Msg.success().add("aboutUs",lotOtherService.getStartImgUrl());
    }

}
