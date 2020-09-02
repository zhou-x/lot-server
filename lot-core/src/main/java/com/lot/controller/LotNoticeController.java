package com.lot.controller;

import com.lot.service.LotNoticeService;
import com.lot.util.Msg;
import com.lot.vo.lotNotice.LotNoticeSaveVo;
import com.lot.vo.lotNotice.LotNoticeSearchVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/lot/notice")
public class LotNoticeController {
    @Autowired
    private LotNoticeService lotNoticeService;

    // 把"" 转化成null
    @InitBinder
    public void bind(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @PostMapping("/findlist")
    @ApiOperation("公告数据列表")
    public Msg findList(@RequestBody LotNoticeSearchVo searchVo){
        return lotNoticeService.findList(searchVo);
    }

    @GetMapping("/get")
    @ApiOperation("获取单个公告数据")
    public Msg get(@RequestParam String noticeId){
        return Msg.success().add("notice",lotNoticeService.get(noticeId));
    }

    @PostMapping("/save")
    @Transactional
    @ApiOperation("保存公告数据")
    public Msg save(@RequestBody LotNoticeSaveVo saveVo){
        lotNoticeService.save(saveVo);
        return Msg.success();
    }

    @PostMapping("/delete")
    @Transactional
    @ApiOperation("删除单个公告数据")
    public Msg delete(@RequestParam String noticeId){
        lotNoticeService.delete(noticeId);
        return Msg.success();
    }
}
