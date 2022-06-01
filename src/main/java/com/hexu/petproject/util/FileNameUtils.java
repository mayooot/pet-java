package com.hexu.petproject.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import com.hexu.petproject.config.AiliyunOssConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
public class FileNameUtils {

    //表示第几张图片   图片入数据库时要用到
    static int i = 0;

    //根据UUID生成文件名
    public static String getUUIDFileName() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replace("-", "");
    }

    //从请求头中提取文件名和类型
    public static String getRealFileName(String context) {
        // Content-Disposition: form-data; name="myfile"; filename="a_left.jpg"
        int index = context.lastIndexOf("=");
        String filename = context.substring(index + 2, context.length() - 1);
        return filename;
    }

    //根据给定的文件名和后缀截取文件名
    public static String getFileType(String fileName) {
        //9527s.jpg
        int index = fileName.lastIndexOf(".");
        return fileName.substring(index);
    }


    //上传文件工具
    public static Map<Integer, String> stringMap(MultipartFile multipartFile, String objectKey2) {
        //定义一个map用于接收图片信息
        HashMap<Integer, String> objectObjectHashMap = new HashMap<>();
        if (null == multipartFile) {
            return null;
        }
        if (!multipartFile.isEmpty()) {
            //生成文件名
            String fileName = FileNameUtils.getUUIDFileName() + FileNameUtils.getFileType(multipartFile.getOriginalFilename());
            log.info(fileName);
            OSS ossClient = new OSSClientBuilder().build(AiliyunOssConfig.ENDPOINT, AiliyunOssConfig.ACCESSKEYID, AiliyunOssConfig.ACCESSKEYSECRET);
            try {
                InputStream multipartFileInputStream = multipartFile.getInputStream();
                PutObjectRequest putObjectRequest = new PutObjectRequest(AiliyunOssConfig.BUCKETNAME, AiliyunOssConfig.PREFIX + objectKey2 + "/" + fileName, multipartFileInputStream);
                ossClient.putObject(putObjectRequest);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                // 关闭流
                ossClient.shutdown();
            }
            //放入map集合  这时i的作用就体现出来了
            objectObjectHashMap.put(i, AiliyunOssConfig.PREFIX + objectKey2 + "/" + fileName);
        }
        return objectObjectHashMap;
    }
}
