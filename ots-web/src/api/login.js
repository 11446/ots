import request from '../utils/request'

// 用户登录接口
export function userLogin(uaccount,upwd ){
    console.log("调用了用户登录登录接口！")
    return request({
        url: 'http://localhost:8080/login',
        method: 'post', 
        data: {
            uaccount,   // uname   =   uname:uname
            upwd
        }
    })
}

