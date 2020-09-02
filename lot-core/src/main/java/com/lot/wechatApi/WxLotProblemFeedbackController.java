package com.lot.wechatApi;

import com.lot.service.LotProblemFeedbackService;
import com.lot.util.Msg;
import com.lot.vo.lotproblemFeedback.LotProblemFeedbackSaveVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/lot/weixin/notdirectly/problemfeedback")
public class WxLotProblemFeedbackController {
    @Autowired
    private LotProblemFeedbackService lotProblemFeedbackService;

    // 把"" 转化成null
    @InitBinder
    public void bind(WebDataBinder binder, WebRequest request) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @PostMapping("/save")
    @ApiOperation("保存用户的反馈问题")
    public Msg save(@RequestBody LotProblemFeedbackSaveVo saveVo){
        lotProblemFeedbackService.save(saveVo);
        return Msg.success();
    }
}
