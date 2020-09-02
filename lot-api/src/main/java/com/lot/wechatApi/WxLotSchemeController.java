package com.lot.wechatApi;

import com.lot.service.LotSchemeService;
import com.lot.util.Msg;
import com.lot.vo.lotScheme.LotSchemeSaveVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/lot/weixin/notdirectly/scheme")
public class WxLotSchemeController {
    @Autowired
    private LotSchemeService lotSchemeService;

    // 把"" 转化成null
    @InitBinder
    public void bind(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @PostMapping("/save")
    @ApiOperation("保存用户的方案")
    @Transactional
    public Msg save(@RequestBody LotSchemeSaveVo saveVo){
        lotSchemeService.wxSave(saveVo);
        return Msg.success();
    }

    @PostMapping("/getschemelistbyuserid")
    @ApiOperation("根据用户获取当前用户的方案列表数据")
    public Msg getSchemeListByUserId(){
        return Msg.success().add("list",lotSchemeService.getSchemeListByUserId());
    }

}
