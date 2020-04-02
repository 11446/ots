module.exports = {
    devServer: {
        port: 8888,   // Vue启动端口号
        host: "localhost",   // 主机地址
        https: false,  // 协议
        open: true,  // 启动服务时自动打开浏览器访问
        // proxy: {  // 开发环境代理设置
        //     '/dev-api': {
        //         target: 'http://47.93.189.114:8080',
        //         changeOrigin: true,  // 目标服务器地址
        //         pathRewrite: {   
        //             '^/dev-api': '',
        //         }
        //     }
        // }

    },
    lintOnSave: false,  // 关闭代码规范检查
    productionSourceMap: false  // 打包时不会生成.map文件，加快打包速度
    
}