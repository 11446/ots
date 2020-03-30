package com.zs.ots.service;

import com.zs.ots.entity.FileInfo;
import com.zs.ots.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张帅
 * @date 2020/2/13 18:16
 * @description
 */

public interface FileService {


    /**
     存储文件信息
     */
    public String setFileInfo(FileInfo fileInfo);

    /**
     * 获取所有文件信息
     */
    public List<FileInfo> getAllFileInfo();

    /**
     * 分页查询文件信息
     * @return
     */
    public List<FileInfo> getPageFileInfo();

    /**
     * 根据文件名称查询
     * @param fileName
     */
    public List<FileInfo> searchFileByName(String fileName);

    /**
     * 移除文件信息
     * @param delFileID
     * @return
     */
    public boolean removeFileInfo(Integer delFileID);

    /**
     * 验证文件信息是否重复
     * @param fileInfo
     * @return
     */
    public FileInfo isRepeatFileInfo(FileInfo fileInfo);
}
