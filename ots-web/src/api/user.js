import request from '../utils/request'

// 验证用户原密码
export function verifyUserPwd(uid, oldPwd ){
    return request({
        url: 'http://localhost:8080/verifyUserPwd/',
        method: 'get', 
        params: {
            uid,
            oldPwd
        }
    })
}

// 修改新密码
export function updateUserPwd(uid, newPwd){
    return request({
        url: 'http://localhost:8080/updateUserPwd/',
        method: 'get', 
        params: {
            uid,
            newPwd
        }
    })  
}



