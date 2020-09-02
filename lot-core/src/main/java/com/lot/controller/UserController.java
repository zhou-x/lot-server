package com.lot.controller;

import com.lot.service.LotUserService;
import com.lot.util.Msg;
import com.lot.vo.lotUser.LotUserSaveVo;
import com.lot.vo.lotUser.LotUserSearchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zel
 * @Date 2020-06-3
 */
@Api(value = "登录后的用户信息")
@RestController
@RequestMapping("/lot/users")
public class UserController {
    @Autowired
    private LotUserService lotUserService;

    @PostMapping("/findlist")
    @ApiOperation("获取用户的列表")
    public Msg findList(@RequestBody LotUserSearchVo lotUserSearchVo){
        return lotUserService.findList(lotUserSearchVo);
    }

    @GetMapping("/get")
    @ApiOperation("根据ID获取用户的信息")
    public Msg getUserById(@RequestParam String userId) {
        return Msg.success().add("user",lotUserService.
                getUserById(userId));
    }

    @GetMapping("/getuser")
    @ApiOperation("根据username获取用户的信息")
    public Msg getUser(@RequestParam String username){
        return Msg.success().add("user",lotUserService.getUser(username));
    }

    @PostMapping("/save")
    @ApiOperation("保存用户信息")
    @Transactional
    public Msg saveUser(@RequestBody LotUserSaveVo lotUserSaveVo){
        return Msg.success().add("message",lotUserService.saveUser(lotUserSaveVo));
    }

    @PostMapping("/delete")
    @ApiOperation("作废用户信息")
    @Transactional
    public Msg delete(@RequestParam String userId){
        return Msg.success().add("message",lotUserService.delete(userId));
    }

}
