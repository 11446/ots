package com.zs.ots.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author 张帅
 * @date 2020/2/16 15:55
 * @description 文件的上传与下载
 */
@RestController
@RequestMapping
@Api(tags = "文件的上传与下载")
public class FileTransferController {

    private static final Logger logger = LoggerFactory.getLogger(FileInfoController.class);
    private final static String fileDir = "E:/ots-download/";
    private final static String rootPath = "E:/ots-upload/";


    @ApiOperation(value = "上传文件")
    @GetMapping(value = "/upload")
    public boolean uploadWork(HttpServletRequest request,
                              HttpServletResponse resp,
                              @RequestParam(value = "file", required = false) MultipartFile[] files
    ) throws IOException {
        for (MultipartFile file : files) {
            resp.setCharacterEncoding("utf-8");
            if (file.isEmpty()) {
                return false;
            }
            System.out.println("准备上传文件");
            // 上传文件存放路径
            String path = "E:/ots-upload/";

            System.out.println("开始上传文件！");
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(
                    new File(path + file.getOriginalFilename())));
            bos.write(file.getBytes());
            bos.flush();
            bos.close();

        }
        System.out.println("文件上传成功！");
        return true;
    }



    @ApiOperation(value = "文件下载")
    @GetMapping(value = "/download/{fileName}")
    public void downloadFile(@PathVariable("fileName") String fileName, final HttpServletResponse response, final HttpServletRequest request) {
        System.out.println(fileName);
        OutputStream os = null;
        InputStream is = null;
        try {
            // 取得输出流
            os = response.getOutputStream();
            // 清空输出流
            response.reset();
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes("utf-8"), "iso-8859-1"));
            //读取流
            File f = new File(rootPath + fileName);
            is = new FileInputStream(f);
            if (is == null) {
                logger.error("下载附件失败，请检查文件“" + fileName + "”是否存在");
            }
            //复制
            IOUtils.copy(is, response.getOutputStream());
            response.getOutputStream().flush();
            System.out.println("进来了try");
        } catch (IOException e) {
        }
        //文件的关闭放在finally中
        finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                logger.error("关闭错误");
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                logger.error("关闭错误");
            }
        }
    }

    @ApiOperation(value = "文件移除")
    @GetMapping(value = "/removeFile")
    public Boolean delFile(@RequestParam("fileName") String fileName) throws FileNotFoundException {
        InputStream is = null;
        File f = new File(rootPath + fileName);
        System.out.println(rootPath + fileName);
        if (f.exists()) {
            if (f.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
