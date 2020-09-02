package com.lot.controller;

import com.lot.service.LotOtherService;
import com.lot.util.Msg;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/lot/other")
public class LotOtherController {

    @Autowired
    private LotOtherService lotOtherService;

    // 把"" 转化成null
    @InitBinder
    public void bind(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/getaboutus")
    @ApiOperation("获取'关于我们'的信息")
    public Msg getAboutUs(){
        return Msg.success().add("aboutUs",lotOtherService.getAboutUs());
    }

    @GetMapping("/getcontactus")
    @ApiOperation("获取'联系我们'的信息")
    public Msg getContactUs(){
        return Msg.success().add("aboutUs",lotOtherService.getContactUs());
    }

    @GetMapping("/getuserprotocol")
    @ApiOperation("获取'用户协议'的信息")
    public Msg getUserProtocol(){
        return Msg.success().add("aboutUs",lotOtherService.getUserProtocol());
    }

    @GetMapping("/getprivacypolicy")
    @ApiOperation("获取'隐私政策'的信息")
    public Msg getPrivacyPolicy(){
        return Msg.success().add("aboutUs",lotOtherService.getPrivacyPolicy());
    }

    @GetMapping("/getdemandexample")
    @ApiOperation("获取'需求示例'的信息")
    public Msg getDemandExample(){
        return Msg.success().add("aboutUs",lotOtherService.getDemandExample());
    }

    @GetMapping("/getcommonproblem")
    @ApiOperation("获取'常见问题'的信息")
    public Msg getCommonProblem(){
        return Msg.success().add("aboutUs",lotOtherService.getCommonProblem());
    }

    @GetMapping("/getStartImgUrl")
    @ApiOperation("获取'图片'的信息")
    public Msg getStartImgUrl(){
        return Msg.success().add("aboutUs",lotOtherService.getStartImgUrl());
    }

    @PostMapping("/updateaboutus")
    @ApiOperation("修改'关于我们'的信息")
    @Transactional
    public Msg updateAboutUs(@RequestBody String string){
        lotOtherService.updateAboutUs(string);
        return Msg.success();
    }

    @PostMapping("/updatecontactus")
    @ApiOperation("修改'联系我们'的信息")
    @Transactional
    public Msg updateContactUs(@RequestBody String string){
        lotOtherService.updateContactUs(string);
        return Msg.success();
    }

    @PostMapping("/updateuserprotocol")
    @ApiOperation("修改'用户协议'的信息")
    @Transactional
    public Msg updateUserProtocol(@RequestBody String string){
        lotOtherService.updateUserProtocol(string);
        return Msg.success();
    }

    @PostMapping("/updateprivacypolicy")
    @ApiOperation("修改'隐私政策'的信息")
    @Transactional
    public Msg updatePrivacyPolicy(@RequestBody String string){
        lotOtherService.updatePrivacyPolicy(string);
        return Msg.success();
    }

    @PostMapping("/updatedemandexample")
    @ApiOperation("修改'需求示例'的信息")
    @Transactional
    public Msg updateDemandExample(@RequestBody String string){
        lotOtherService.updateDemandExample(string);
        return Msg.success();
    }

    @PostMapping("/updatecommonproblem")
    @ApiOperation("修改'常见问题'的信息")
    @Transactional
    public Msg updateCommonProblem(@RequestBody String string){
        lotOtherService.updateCommonProblem(string);
        return Msg.success();
    }

    @PostMapping("/updateStartImgUrl")
    @ApiOperation("修改'图片'的信息")
    @Transactional
    public Msg updateStartImgUrl(@RequestBody String string){
        lotOtherService.updateStartImgUrl(string);
        return Msg.success();
    }

}
