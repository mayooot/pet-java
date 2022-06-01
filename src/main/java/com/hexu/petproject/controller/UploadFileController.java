package com.hexu.petproject.controller;

import com.hexu.petproject.util.FileNameUtils;
import com.hexu.petproject.util.ResultVO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Classname UploadFileController
 * @Description TODO
 * @Author 86176
 * @Date 2022-04-07 13:02
 * @Version 1.0
 **/
@CrossOrigin
@RestController
@RequestMapping("/api/files")
public class UploadFileController {

    @PostMapping("uploadFile")
    public ResultVO uploadFile(MultipartFile file, Integer type, HttpServletResponse response) {
        Map<Integer, String> map = FileNameUtils.stringMap(file,"comment");
        String s = map.get(0);
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("id",s);
        hashMap.put("filePath",s);
        return ResultVO.ok(hashMap);
    }
}
