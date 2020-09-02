package com.lot.wechatApi;

import com.lot.service.LotNoticeService;
import com.lot.util.Msg;
import com.lot.vo.lotNotice.LotNoticeSearchVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/lot/weixin/open/notice")
public class WxLotNoticeController {
    @Autowired
    private LotNoticeService lotNoticeService;

    // 把"" 转化成null
    @InitBinder
    public void bind(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @PostMapping("/findlist")
    @ApiOperation("微信资讯数据列表")
    public Msg findList(@RequestBody LotNoticeSearchVo searchVo){
        return lotNoticeService.findList(searchVo);
    }

    @GetMapping("/get")
    @ApiOperation("微信获取单个资讯数据")
    public Msg get(@RequestParam String noticeId){
        return Msg.success().add("notice",lotNoticeService.wxGet(noticeId));
    }

    @GetMapping("getnoticelist")
    @ApiOperation("获取公告列表")
    public Msg getNoticeList(){
        return Msg.success().add("list",lotNoticeService.getNoticeList());
    }

}
