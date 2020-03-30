package com.zs.ots.controller;

import com.github.pagehelper.PageHelper;
import com.zs.ots.entity.FileInfo;
import com.zs.ots.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 张帅
 * @date 2020/2/10 16:46
 * @description 文件信息管理
 */

@RestController
@RequestMapping
public class FileInfoController {

    private static final Logger logger = LoggerFactory.getLogger(FileInfoController.class);

    @Autowired
    private FileService fileService;


    /**
     * 存储文件信息
     *
     * @param fileInfo
     * @return
     */
    @PostMapping("/upload/fileinfo")
    public String getFileInfo(@RequestBody FileInfo fileInfo) {
        FileInfo f = fileService.isRepeatFileInfo(fileInfo);
        if (f != null) {
            System.out.println("文件重复！");
            return "添加文件信息成功！";
        } else {
            System.out.println(fileInfo);
            System.out.println("写入文件信息：" + fileInfo);
            fileService.setFileInfo(fileInfo);
            return "添加文件信息成功！";
        }
    }


    /**
     * 文件信息总条数查询查询
     *
     * @return
     */
    @GetMapping("/getAllFileInfo")
    public Integer getAllFileInfo() {
        List<FileInfo> allFileInfo = fileService.getAllFileInfo();
        return allFileInfo.size();
    }

    /**
     * 分页查询文件信息
     *
     * @param PageNum
     * @param PageSize
     * @return
     */
    @GetMapping("/getPageFileInfo")
    public List<FileInfo> getPageFileInfo(@RequestParam("PageNum") Integer PageNum,
                                          @RequestParam("PageSize") Integer PageSize) {
        PageHelper.startPage(PageNum, PageSize);
        List<FileInfo> fileInfos = fileService.getPageFileInfo();
        return fileInfos;
    }

    /**
     * 根据文件名模糊查找文件
     *
     * @param fileName
     * @return fileInfos
     */
    @GetMapping("/searchFileByName/{fileName}")
    public List<FileInfo> searchFileByName(@PathVariable("fileName") String fileName) {
        List<FileInfo> fileInfos = fileService.searchFileByName(fileName);
        return fileInfos;
    }

    /**
     * 移除文件信息
     *
     * @param delFileID
     * @return
     */
    @RequestMapping(value = "/removeFileInfo", method = RequestMethod.GET)
    public boolean removeFileInfo(@RequestParam("fileID") Integer delFileID) {
        boolean result = fileService.removeFileInfo(delFileID);
        return result;
    }
}
