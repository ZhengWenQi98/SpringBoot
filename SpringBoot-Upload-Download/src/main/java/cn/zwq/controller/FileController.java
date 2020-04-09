package cn.zwq.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
public class FileController {

    @PostMapping("upload")
    public String upload(MultipartFile file){
        try {
            if (file.isEmpty()){
                return "文件为空";
            }
            //获取文件名
            String fileName = file.getOriginalFilename();
            log.info("上传的文件名："+fileName);
            //获取文件后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            log.info("文件后缀名："+suffixName);
            //设置文件存储路径
            String filePath = "f:/upload/";
            String path = filePath+fileName;
            File dest = new File(path);
            //检测是否存在该目录
            if (!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }
            //写入文件
            file.transferTo(dest);
            return "上传成功！";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "上传失败";
    }

    @PostMapping("uploadFile")
    public String uploadFile(MultipartFile file){
        try {
            if (file.isEmpty()){
                return "文件为空";
            }
            //获取文件名
            System.out.println(file.getOriginalFilename());
            System.out.println(file.isEmpty());
            System.out.println(file.getName());//请求名称
            System.out.println(file.getSize());
            System.out.println(file.getResource());
            System.out.println(file.getContentType());
            System.out.println(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("BATCH")
    public String batchUpload(MultipartHttpServletRequest request){
        /**
         *  多文件上传流程
         *      1.前端上传多个文件
         *      2.后台使用请求对象接收整个请求流
         *      3.获取MultipartFile集合
         *      4.定义缓冲字节输出流
         *      5.遍历集合
         *      6.获取每一个MultipartFile对象
         *      7.定义上传路径
         *      8.判断上传文件是否为空（也就是没有上传）
         *      9.如果不为空，则通过缓冲字节输出流写入到上传路径
         *      10.这里有一个bug，就是后面上传的文件为空，则不影响前面上传的文件；如果前面上传的文件为空，则会影响后上传的文件
         *      11.针对上面所说的bug，加以改进，就是把一次上传的文件当成一个原子操作。
         */
        List<MultipartFile> files = request.getFiles("file");
        MultipartFile file = null;
        BufferedOutputStream stream = null;
        for (int i = 0; i < files.size(); i++) {
            file = files.get(i);
            String filePath = "f:/upload/";
            if (!file.isEmpty()){
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(new File(filePath+file.getOriginalFilename())));
                    stream.write(bytes);
                    stream.close();
                } catch (IOException e) {
                    stream = null;
                    return "第"+i+"个文件上传失败："+e.getMessage();
                }
            }else {
                return "第"+i+"个文件上传失败因为文件为空";
            }
        }
        return "上传成功";
    }

    @GetMapping("download")
    public String download(){
        String fileName = "";
        return null;
    }
}