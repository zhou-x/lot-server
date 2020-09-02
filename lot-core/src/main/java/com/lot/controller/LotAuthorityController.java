package com.lot.controller;

import com.lot.service.LotAuthorityService;
import com.lot.util.Msg;
import com.lot.vo.lotAuthority.LotAuthorityIdList;
import com.lot.vo.lotAuthority.LotAuthoritySearchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

/**
 * @Author zel
 * @Date 2020-06-19
 */
@Api(value = "基于Url权限信息")
@RestController
@RequestMapping("/lot/authority")
public class LotAuthorityController {
    @Autowired
    private LotAuthorityService lotAuthorityService;

    @PostMapping("/findlist")
    @ApiOperation("获取权限列表")
    public Msg findList(@RequestBody LotAuthoritySearchVo lotAuthoritySearchVo){
        return lotAuthorityService.findList(lotAuthoritySearchVo);
    }

    @PostMapping("/save")
    @ApiOperation("根据角色保存权限列表")
    @Transactional
    public Msg save(@RequestBody LotAuthorityIdList lotAuthorityIdList){
        lotAuthorityService.save(lotAuthorityIdList);
        return Msg.success();
    }

}
