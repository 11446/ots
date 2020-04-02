import request from '../utils/request'

// 分页查询用户信息
export function getPageUserInfo(PageNum, PageSize, urole) {
  return request({
    url: '/getPageUserInfo',
    method: 'GET',
    params: {
      PageNum,
      PageSize,
      urole
    }
  })
}

// 查询全部用户信息

export function getAllUserInfo(urole) {
  return request({
    url: '/getAllUserInfo',
    method: 'GET',
    params: {
      urole
    }
  })
}

// 验证账号是否重复
export function verifyAccount(uaccount, uid) {
  return request({
    url: '/verifyAccount',
    method: 'GET',
    params: {
      uaccount,
      uid
    }
  })
}

// 修改用户信息
export function alterUserInfo(userInfo) {
  return request({
    url: '/alterUserInfo',
    method: 'POST',
    data: userInfo
  })
}

// 移除学生
export function removeStudent(id) {
  return request({
    url: '/removeStudent',
    method: 'delete',
    params: {
      id
    }
  })
}

// 账号模糊查询
export function searchUserByAcc(userAcc) {
  return request({
    url: '/searchUserByAcc/',
    method: 'GET',
    params: {
      userAcc
    }
  })
}
// 添加用户
export function addUser(newUser) {
  return request({
    url: '/addUser/',
    method: 'POST',
    data: newUser
  })
}

// 验证新账号是否重复
export function validateNewAccount(newUserAcc) {
  return request({
    url: '/validateNewAcc/',
    method: 'get',
    params: {
      newUserAcc
    }
  })
}
