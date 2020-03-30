package com.zs.ots.entity;

import java.util.Objects;

/**
 * @author 张帅
 * @date 2020/2/13 11:35
 * @description 文件信息
 */
public class FileInfo {
    private String fileId;
    private String fileName;
    private String fileSize;
    private String fileDetail;
    private String uploadUser;
    private String uploadTime;

    public FileInfo() {
    }

    public FileInfo(String fileId, String fileName, String fileSize, String fileDetail, String uploadUser, String uploadTime) {
        this.fileId = fileId;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.fileDetail = fileDetail;
        this.uploadUser = uploadUser;
        this.uploadTime = uploadTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FileInfo fileInfo = (FileInfo) o;
        return fileId.equals(fileInfo.fileId) &&
                fileName.equals(fileInfo.fileName) &&
                fileSize.equals(fileInfo.fileSize) &&
                fileDetail.equals(fileInfo.fileDetail) &&
                uploadUser.equals(fileInfo.uploadUser) &&
                uploadTime.equals(fileInfo.uploadTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileId, fileName, fileSize, fileDetail, uploadUser, uploadTime);
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "fileId='" + fileId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileSize='" + fileSize + '\'' +
                ", fileDetail='" + fileDetail + '\'' +
                ", uploadUser='" + uploadUser + '\'' +
                ", uploadTime='" + uploadTime + '\'' +
                '}';
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileDetail() {
        return fileDetail;
    }

    public void setFileDetail(String fileDetail) {
        this.fileDetail = fileDetail;
    }

    public String getUploadUser() {
        return uploadUser;
    }

    public void setUploadUser(String uploadUser) {
        this.uploadUser = uploadUser;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }
}
