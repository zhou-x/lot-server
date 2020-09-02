package com.lot.controller;

import com.lot.service.LotRoleService;
import com.lot.util.Msg;
import com.lot.vo.lotRole.LotRoleSearchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author zel
 * @Date 2020-06-19
 */
@Api(value = "角色信息")
@RestController
@RequestMapping("/lot/role")
public class LotRoleController {
    @Autowired
    private LotRoleService lotRoleService;

    @PostMapping("/findlist")
    @ApiOperation("获取全部的角色")
    public Msg findList(@RequestBody LotRoleSearchVo lotRoleSearchVo){
        return lotRoleService.findList(lotRoleSearchVo);
    }
}
