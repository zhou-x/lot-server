package com.lot.wechatApi;

import com.lot.service.LotDemandHistoryService;
import com.lot.service.LotDemandService;
import com.lot.util.Msg;
import com.lot.vo.lotDemand.WxDemandSearchVo;
import com.lot.vo.lotDemandHistory.LotDemandQuotationVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/lot/weixin/notdirectly/demand")
public class WxLotDemandController {
    @Autowired
    private LotDemandService lotDemandService;
    @Autowired
    private LotDemandHistoryService lotDemandHistoryService;

    // 把"" 转化成null
    @InitBinder
    public void bind(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @PostMapping("/quotation")
    @ApiOperation("微信用户报价")
    public Msg quotation(@RequestBody LotDemandQuotationVo vo) {
        lotDemandHistoryService.demandHistorySave(vo);
        return Msg.success();
    }

    @PostMapping("/getlistbytoday")
    public Msg getListByToday(@RequestBody WxDemandSearchVo searchVo){
        return Msg.success().add("list",lotDemandService.getListByToday(searchVo));
    }

    @GetMapping("getscreeningbybrand")
    @ApiOperation("获取筛选的品牌和品类")
    public Msg getScreeningByBrand(){
        return lotDemandService.getScreeningByBrand();
    }

}
