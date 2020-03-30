package com.zs.ots.service.impl;

import com.zs.ots.entity.FileInfo;
import com.zs.ots.mapper.FileMapper;
import com.zs.ots.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张帅
 * @date 2020/3/26 21:10
 * @description
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileMapper fileMapper;

    // 存储文件信息
    @Override
    public String setFileInfo(FileInfo fileInfo){
        fileMapper.setFileInfo(fileInfo);
        return null;
    }
    // 获取所有文件信息
    @Override
    public List<FileInfo> getAllFileInfo() {
        List<FileInfo> allFileInfo = fileMapper.getAllFileInfo();
        return allFileInfo;
    }

    // 分页查询文件信息
    @Override
    public List<FileInfo> getPageFileInfo() {
        return fileMapper.getPageFileInfo();

    }

    /**
     * 根据文件名称查询
     * @param fileName
     */
    @Override
    public List<FileInfo> searchFileByName(String fileName) {

        // 为模糊查询进行拼接
        fileName = "%"+fileName+"%";
        return fileMapper.searchFileByName(fileName);
    }


    @Override
    public boolean removeFileInfo(Integer delFileID) {
        return fileMapper.removeFileInfo(delFileID);

    }

    @Override
    public FileInfo isRepeatFileInfo(FileInfo fileInfo) {
        return fileMapper.isRepeatFileInfo(fileInfo);

    }
}
