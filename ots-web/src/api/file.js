import request from "@/utils/request";

export function fileUpload() {
  return request({
    url: "/upload/",
    method: "POST"
  });
}
// 删除文件
export function fileRemove(fileName) {
  return request({
    url: "/removeFile/",
    method: "get",
    params: {
      fileName
    }
  });
}

// 移除文件信息
export function removeFileInfo(fileID) {
  return request({
    url: "/removeFileInfo/",
    method: "get",
    params: {
      fileID
    }
  });
}

// 分页查询
export function getPageFileInfo(PageNum, PageSize) {
  return request({
    url: "/getPageFileInfo/",
    method: "GET",
    params: {
      PageNum,
      PageSize
    }
  });
}

// 所有文件信息
export function getAllFileInfo() {
  return request({
    url: "/getAllFileInfo",
    method: "GET"
  });
}

// 文件信息模糊查询
export function searchFileByName(fileName) {
  return request({
    url: "/searchFileByName/",
    method: "GET",
    params: {
      fileName
    }
  });
}

export function setFileInfo(uploadData) {
  return request({
    url: "/upload/fileinfo/",
    method: "POST",
    data: uploadData
  });
}
