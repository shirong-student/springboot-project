package com.example.file_controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@RestController
public class FileController {

    // 任务一：实现单个文件的上传
    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return "上传失败 请选择文件";
        }
        // 1.设置存放路径
        File path = new File(".");  // 项目当前工作路径
        File upload = new File(path.getAbsolutePath(), "/upload/");
        if (!upload.exists()) {
            upload.mkdirs();    // 如果不存在该文件路径 则创建路径
        }
        // 2.设置文件名
        String fileName = file.getOriginalFilename();   // 得到上传文件的文件名（包括后缀）
        String suffixName = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf("."));  // 得到后缀
        fileName = UUID.randomUUID() + suffixName;  // 生成服务器存储的文件名
        // 3.配置存放的文件
        File dest = new File(upload.getPath() + "/" + fileName);    // 存放的目标文件
        // 4.保存文件
        try {
            file.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    /**
     * 多个文件上传的方法实现就是在单个文件的基础上加上了for循环
     * 请求参数也改变为了数组形式，且多文件上传与单文件上传步骤基本相同
     */
    @PostMapping("/uploadFiles")
    public String uploadFiles(@RequestParam("file") MultipartFile[] files) {
        StringBuilder strTemp = new StringBuilder();
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                strTemp.append(file.getOriginalFilename()).append(" 文件为空 上传失败\n");
                continue;
            }
            // 1.设置存放路径
            File path = new File(".");  // 项目当前工作路径
            File upload = new File(path.getAbsolutePath(), "/upload/");
            if (!upload.exists()) {
                upload.mkdirs();    // 如果不存在该文件路径 则创建路径
            }
            // 2.设置文件名
            String fileName = file.getOriginalFilename();   // 得到上传文件的文件名（包括后缀）
            String suffixName = Objects.requireNonNull(fileName).substring(fileName.lastIndexOf("."));  // 得到后缀
            fileName = UUID.randomUUID() + suffixName;  // 生成服务器存储的文件名
            // 3.配置存放的文件
            File dest = new File(upload.getPath() + "/" + fileName);    // 存放的目标文件
            // 4.保存文件
            try {
                file.transferTo(dest);
                strTemp.append(file.getOriginalFilename()).append(" 上传成功\n");
            } catch (IOException e) {
                e.printStackTrace();
                strTemp.append(file.getOriginalFilename()).append(" 上传异常\n");
            }
        }
        return strTemp.toString();
    }

    @GetMapping("/downloadFile")
    public void downloadFile(String fileName, HttpServletResponse response) throws IOException {
        // 1.读取文件 并转换成文件流
        File path = new File(".");
        File download = new File(path.getAbsolutePath(), "/upload/");
        File file = new File(download.getPath() + "/" + fileName);
        // 转换成文件流
        FileInputStream fileInputStream = new FileInputStream(file);
        // 2.创建response输出对象流
        response.setContentType("application/force-download");
        response.addHeader("Content-disposition", "attachment:fileName=" + fileName);    // 要下载的文件名
        ServletOutputStream outputStream = response.getOutputStream();
        // 3.文件流写入response对象流 文件流操作
        byte[] buf = new byte[1024];
        int len;
        while ((len = fileInputStream.read(buf)) != -1) {
            outputStream.write(buf, 0, len);
        }
        // 4.文件流关闭
        fileInputStream.close();
    }
}
