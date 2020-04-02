<template>
  <div>
      <!-- 上传文件组件 
      http://47.93.189.114:8080/upload/
      -->
    <el-upload
        class="upload-demo"
        drag
        :before-upload="beforeUpload"
        :on-success="onSuccess"
        :on-error="onError"
        multiple
        action="http://47.93.189.114:8080/upload"     
        :data="uploadData"
        style="position: absolute; margin-left: 100px;"
      >
      <i class="el-icon-upload"></i>
      <div class="el-upload__text"><b>将文件拖到此处，或<em>选取文件</em></b></div>
      <div slot="tip" class="el-upload__tip"> <el-input
        placeholder="请输入文件描述（选填）"
        v-model="uploadData.file_detail"
        clearable>
      </el-input></div>
      
    </el-upload>
    <!-- 帮助信息 -->

    <el-card class="box-card" shadow="hover">
      <div slot="header" class="clearfix">
      <span style="color: red; font-size: 20px">使用说明</span>
      </div>
      <p>1、支持多文件上传</p>
      <p>2、每次上传文件总体积不得超过1000MB</p>
      <p>3、若上传重复文件，则覆盖前文件</p>
      <p>4、批量上传的文件描述状态一致</p>
      <p>5、支持任意类型的文件上传</p>
    </el-card>
  </div>
</template>
<style lang="stylus" scoped>
.upload-help{
  background: #ffffff;
  position: relative;
  margin-top: 50px ;
  margin-left: 550px;
  width: 600px;
  height: 500px;
  border-radius: 20px;
  box-shadow: 0 0 10px #dcdcdc

}
p{
  line-height : 50px
  font-size : 16px
  color: #0a8aff

}

.detail-input{
  position:absolute;
  bottom : 500px;
  left: 500px;
  font-family : '微软雅黑';

}
.up-hint-b{
   font-size : 25px
}
.text {
    font-size: 14px;
  }

  .item {
    margin-bottom: 18px;
  }

  .clearfix:before,
  .clearfix:after {
    display: table;
    content: "";
  }
  .clearfix:after {
    clear: both
  }

  .box-card {
    position: relative;
    left: 600px;
    width: 500px;
  }
</style>
<script>
import axios from 'axios'
import {setFileInfo} from '@/api/file'
import { fileRemove } from '../../api/file'
export default {
    data(){
      return{
        uploadData: {
            fileName: "",
            fileSize: "",
            fileDetail: "",
            uploadUser: "",
            uploadTime: "",        
        },
      }
    },
    methods: {
    // 文件上传之前的钩子
      beforeUpload(file){ 
        // 重置uploadData的对象数据
        this.uploadData = this.$options.data().uploadData


        if(file.size == 0){
          this.$message.error("禁止上传空文件")
          return false
        }else{
        this.uploadData.fileName = file.name
        console.log(this.uploadData.fileName)
        if(Math.floor(file.size/1000)>1024){
          let a = Math.floor(file.size/1000)
          this.uploadData.fileSize = Math.floor(a/1024)+"MB"
        }else{
          this.uploadData.fileSize = Math.floor(file.size/1000)+"kb"
        }
        this.uploadData.uploadUser = JSON.parse(localStorage.getItem('user-info')).uname
        this.uploadData.uploadTime = this.getNowFormatDate()
        console.log(this.uploadData)
       setFileInfo(this.uploadData).then(resp=>{
          // console.log(resp)
        })
        }

      },

      // 上传文件成功后的钩子函数
      onSuccess(response, file, fileList){  
        // console.log(file)     
        // 上传文件成功后，显示提示信息
        if(response){
        this.$message({message: '上传文件：'+file.name+"成功！",type: 'success' }); 
        }   
      },
      onError(err, file, fileList){
          console.log("上传失败")
          console.log(file)
      },

      // js实时获取当前时间
      getNowFormatDate() {
        var date = new Date();
        var seperator1 = "-";
        var year = date.getFullYear();
        var month = date.getMonth() + 1;
        var strDate = date.getDate();
        if (month >= 1 && month <= 9) {
            month = "0" + month;
        }
        if (strDate >= 0 && strDate <= 9) {
            strDate = "0" + strDate;
        }
        var currentdate = year + seperator1 + month + seperator1 + strDate;
        return currentdate;
    }
    }
}
</script>
