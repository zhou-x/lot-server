package com.lot.controller;

import com.lot.dao.LotFileDao;
import com.lot.entity.LotFileEntity;
import com.lot.mapper.LotCodeMapper;
import com.lot.util.FileUpload;
import com.lot.util.IDKeyUtil;
import com.lot.util.Msg;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/lot/open")
public class OpenController {

    @Value("${file.address}")
    private String URL;

    @Autowired
    private LotCodeMapper lotCodeMapper;
    @Autowired
    private LotFileDao lotFileDao;

    @ApiOperation("Select组件列表code")
    @GetMapping("/code")
    public Msg getCode(String code){
        return Msg.success().add("value",lotCodeMapper.getCode(code));
    }

    @ApiOperation("图片上传")
    @PostMapping("/upload")
    @ResponseBody
    @Transactional
    public Msg upload(@RequestParam("file") MultipartFile file) {
        String filename = file.getOriginalFilename();
        LotFileEntity lotFileEntity = new LotFileEntity();
        lotFileEntity.setUid(IDKeyUtil.getStringId());
        lotFileEntity.setName(filename);
        lotFileEntity.setStatus("done");
        lotFileEntity.setBelong("img");
        filename = FileUpload.writeUploadFile(file,"upload");
        String url = URL + filename;
        lotFileEntity.setUrl(url);
        lotFileDao.save(lotFileEntity);
        return Msg.success().add("url",url).add("uid",lotFileEntity.getUid());
    }
}
