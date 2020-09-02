package com.lot.wechatApi;

import com.lot.service.LotDemandHistoryService;
import com.lot.util.Msg;
import com.lot.vo.lotDemandHistory.LotDemandHistorySearchVo;
import com.lot.vo.lotDemandHistory.LotUserAgree;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/lot/weixin/notdirectly/demandhistory")
public class WxLotDemandHistoryController {

    @Autowired
    private LotDemandHistoryService lotDemandHistoryService;

    // 把"" 转化成null
    @InitBinder
    public void bind(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @PostMapping("/findlistbyid")
    @ApiOperation("微信获取需求列表")
    public Msg findListByUserId(@RequestBody LotDemandHistorySearchVo searchVo) {
        return Msg.success().add("list",lotDemandHistoryService.findListByUserId(searchVo));
    }

    @PostMapping("/useragree")
    @Transactional
    @ApiOperation("用户同意或拒绝报价")
    public Msg userAgree(@RequestBody LotUserAgree saveVo){
        lotDemandHistoryService.userAgree(saveVo);
        return Msg.success();
    }
}
