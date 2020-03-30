package com.zs.ots.mapper;

import com.zs.ots.entity.FileInfo;

import java.util.List;

/**
 * @author 张帅
 * @date 2020/2/13 18:16
 * @description 文件信息mapper文件
 */
public interface FileMapper {

    /**
     * 数据库存储上传的文件信息
     */
    void setFileInfo(FileInfo fileInfo);

    /**
     * 获取所有文件信息
     */
    List<FileInfo> getAllFileInfo();

    /**
     * 分页查询文件信息
     */
    List<FileInfo> getPageFileInfo();

    /**
     * 根据文件名称查询
     *
     * @param fileName
     */
    List<FileInfo> searchFileByName(String fileName);

    /**
     * 根据文件ID删除文件信息
     *
     * @param delFileID
     * @return
     */
    boolean removeFileInfo(Integer delFileID);

    /**
     * 验证文件信息是否重复
     *
     * @param fileInfo
     * @return
     */
    FileInfo isRepeatFileInfo(FileInfo fileInfo);
}
