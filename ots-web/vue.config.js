module.exports = {
    devServer: {
        port: 8888,   // 端口号
        host: "127.0.0.1",   // 主机地址
        https: false,  // 协议
        open: true,  // 启动服务时自动打开浏览器访问
        proxy: {  // 开发环境代理设置
            '/dev-api': {
                target: 'http://localhost:8080',
                changeOrigin: true,  // 目标服务器地址
                pathRewrite: {   
                    '^/dev-api': '',
                }
            }
        }

    },
    lintOnSave: false,  // 关闭代码规范检查
    productionSourceMap: false  // 打包时不会生成.map文件，加快打包速度
    
}