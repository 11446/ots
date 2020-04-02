import request from "../utils/request";

// 存入留言信息
export function setMessageInfo(mesInfo) {
  return request({
    url: "/setMessageInfo/",
    method: "POST",
    data: mesInfo
  });
}

// 获取第一页的留言信息
export function getPageMesInfo(pageNum, pageSize) {
  return request({
    url: "/getPageMesInfo/",
    method: "GET",
    params: {
      pageNum,
      pageSize
    }
  });
}

// 获取所有留言信息
export function getAllMesInfo() {
  return request({
    url: "/getAllMesInfo/",
    method: "GET"
  });
}

// 修改留言
export function updateMessage(mesInfo) {
  return request({
    url: "/updateMessage",
    method: "POST",
    data: mesInfo
  });
}

// 删除留言
export function deleteMessage(id) {
  return request({
    url: "/deleteMessage",
    method: "GET",
    params: {
      id
    }
  });
}
