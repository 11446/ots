import request from '../utils/request'

export function fileUpload(){
    return request({
        url: 'http://localhost:8080/upload/',
        method: 'POST'
    })
}
// 删除文件
export function fileRemove(fileName){
    return request({
        url: 'http://localhost:8080/removeFile/',
        method: 'get',
        params: {
            fileName
        }
    })
}

export function removeFileInfo(fileID){
    return request({
        url: 'http://localhost:8080/removeFileInfo/',
        method: 'get',
        params: {
            fileID
        }
    })
}

// 分页查询
export function getPageFileInfo(PageNum, PageSize){
    return request({
        url: 'http://localhost:8080/getPageFileInfo/',
        method: "GET",
        params:{
            PageNum,
            PageSize
        }
    })
}

// 所有文件信息
export function getAllFileInfo(){
    return request({
        url: 'http://localhost:8080/getAllFileInfo',
        method: 'GET',
    })
}