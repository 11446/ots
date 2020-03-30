import {userLogin} from '@/api/login'
// 官方建议用actions来操作mutations，进而操作state的状态值
// 将复杂的逻辑放入到actions里，mutations只用来赋值
const user = {
    state: {
        // username: JSON.parse(localStorage.getItem("user-info")).uname,
        // username: "张帅",
        // userrole: JSON.parse(localStorage.getItem("user-info")).urole,
        // userrole: "教师",
        // userid: JSON.parse(localStorage.getItem("user-info")).id,
        // userid: 1,
        username: null
    },
    mutations: {
        // 设置登录用户信息，userinfo为载荷，也就是参数
        SET_USER(state, userinfo){ 
            const uinfo = JSON.stringify(userinfo)
            localStorage.setItem('user-info', uinfo)  
            state.username = JSON.parse(uinfo).uname

        },
        LOGOUT_USER(state){
            state.username = null

        }
    },
    actions: {
        Login({commit}, form){
            return new Promise((resolve, reject)=>{
                userLogin(form.uname.trim(), form.upwd).then(resp=>{
                    console.log(resp)
                    commit('SET_USER', resp.data)
                    resolve(resp)
                }).catch( error=>{
                    reject(error)
                });
                
            })
           
        },
        Logout({commit}){
            return new Promise((resolve, reject)=>{
                localStorage.removeItem("user-info");
                commit('LOGOUT_USER')
                const resp = "退出成功"
                resolve(resp)
            })
           
        },
     
    },

    getters: {
        desc(state){
    
        }
    }
}
export default user