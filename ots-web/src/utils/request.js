import axios from 'axios'
import { Loading} from 'element-ui'

let loadingInstance
const loading = {
    open: function(){
        loadingInstance = Loading.service({target: '.main'})
    },
    close: function(){
        if(loadingInstance !== null){
            loadingInstance.close();             
        }
    },

}
const request = axios.create({
    baseURL: '/',
    timeout: 5000
})

// 请求拦截器
request.interceptors.request.use(
    config=> {
        // 请求拦截,只有第一次请求才会打开加载页面
        if(loadingInstance == null){
            loading.open()
        }     
         return config
      },
    err=>{
        loading.close()
        return Promise.reject(err)
})

// 响应拦截器
request.interceptors.response.use(response => {
    
    loading.close()
    return response
}, error => {
    loading.close()
    return Promise.reject(error)
})

export default request
