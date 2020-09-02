package com.lot.wechatApi;

import com.lot.service.LotRotationChartService;
import com.lot.util.Msg;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/lot/weixin/open/rotationchart")
public class WxLotRotationChartController {

    @Autowired
    private LotRotationChartService lotRotationChartService;

    // 把"" 转化成null
    @InitBinder
    public void bind(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/getList")
    @ApiOperation("微信获取轮播图列表")
    public Msg getList(){
        return Msg.success().add("list",lotRotationChartService.getList());
    }

}
