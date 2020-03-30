<template>

  <div class="header">
    <el-breadcrumb separator="/">
     <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
     <!-- <el-breadcrumb-item>{{$route.meta.title}}</el-breadcrumb-item> -->
    <el-breadcrumb-item>{{$route.meta.title}}</el-breadcrumb-item>

    </el-breadcrumb>
    <!-- <h2 class="head-title">教学文档管理系统</h2>  -->
    <!-- ---------------下拉菜单 ------------------------------>
    <img data-v-73b3742a=""  src="https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif?imageView2/1/w/80/h/80" class="user-avatar">
    <el-dropdown trigger="click" class="dropdown" @command="handleCommand">
        <div class="el-dropdown-link" >

          <h3> {{ username }}   [ {{ role }} ]  
          <!-- 动态获取用户名 -->
            <i class="el-icon-arrow-down el-icon--right"></i>
          </h3> 
        </div>
        <el-dropdown-menu slot="dropdown" split-button	>
          <!-- 注意command属性不可以为中文 -->
          <h5>
            <el-dropdown-item icon="el-icon-edit" command="a">修改密码</el-dropdown-item>     
            <el-dropdown-item icon="el-icon-switch-button" command="b">退出账户</el-dropdown-item> 
          </h5>    
        </el-dropdown-menu>
    </el-dropdown>
    <el-dialog style="width: 60%; margin: 15px auto" title="修改密码" :visible.sync="dialogFormVisible">
      <el-form style="width: 100%; margin-left: -20px" :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="旧密码" prop="oldPwd">
          <el-input type="password" v-model="ruleForm.oldPwd" autocomplete="off" ></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newPwd">
          <el-input type="password" v-model="ruleForm.newPwd" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认新密码" prop="checkPwd">
          <el-input type="password" v-model="ruleForm.checkPwd" autocomplete="off"></el-input>
        </el-form-item>
     
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
          <el-button @click="$refs['ruleForm'].resetFields()">重置</el-button>
        </el-form-item>
       </el-form>
    </el-dialog>
  
</div>
</template>

<script>
  import {verifyUserPwd, updateUserPwd} from '../api/user'

  export default {
      data () {
        const validateOldPwd = (rule, value, callback)=>{
          // console.log("原密码输入为："+value)
          const uid = this.uid
          verifyUserPwd(uid, value).then(resp=>{
            if(resp.data){
              callback()
            }else{
              callback(new Error("原密码错误！"))
            }
          })
        }
        const validateCheckPwd = (rule, value, callback)=>{
            if(value != this.ruleForm.checkPwd){
              callback(new Error("两次输入的密码不一致！"))
            }else{
              callback()
            }
        }
      return {
         dialogFormVisible: false,
         ruleForm:{
           oldPwd:"",
           newPwd:"",
           checkPwd:""
         },
         rules: {
           oldPwd: [
             {required: true, message: "原密码不能为空", trigger: "blur"},
              { validator: validateOldPwd, trigger: 'blur' }

           ],
           newPwd: [
             {required: true, message: "新密码不能为空", trigger: "blur"}
           ],
           checkPwd: [
             {required: true, message: "请再次输入新密码", trigger: "blur"},
              { validator: validateCheckPwd, trigger: 'blur' }
           ],

         },
      username: JSON.parse(localStorage.getItem('user-info')).uname,
      uid: JSON.parse(localStorage.getItem('user-info')).id,
      role: JSON.parse(localStorage.getItem('user-info')).urole
      }
    },
    methods: {
        handleCommand(command) {
       switch (command) {
         case 'a':
           this.dialogFormVisible=true;        
           this.$nextTick(()=>{
              this.$refs['ruleForm'].resetFields()
           })
           break;
         case 'b':
           this.userLogout();
           break;    
         default:
           break;
       }
      },
       submitForm(formName){
         this.$refs[formName].validate(valid => {
           if(valid){
             updateUserPwd(this.uid , this.ruleForm.newPwd).then(resp=>{
                // console.log(resp)
                this.$message({
                  message: resp.data? "修改成功！" : "修改失败",
                  type: resp.data? "success" : "warning"
                })
                if(resp.data){
                  // console.log("此时应退出系统！")
                  // 关闭窗口
                  this.dialogFormVisible = false
                  this.userLogout()
                }
             })
           }else{
             return "校验失败"
           }
         })
       },
      //  退出登录
       userLogout(){     
           localStorage.removeItem("user-info");
           this.$router.push('/');
       }
      
      
    },

  }

</script>

<style scoped>
.header {
  position: absolute;
  height: 60px;
  top: 0px;
  left: 200px;
  right: 0px;
  background-color: rgb(250, 250, 250)
;
}
.mylogo {
  vertical-align: middle;
  padding: 10px 10px 0px 40px;
}
.head-title {
  position: fixed;
  color: #304156;
  top: 10px;
  left: 25px;
  font-family: "仿宋体";
  font-size: 25px
}
.dropdown{
  color: #304156;
  top: 16px;
  position: absolute;
  right: 45px;
  cursor: pointer;
}
.user-avatar{
  position: relative;
  margin-left: 1080px;
  margin-top: -25px;
  width: 40px;
  height: 40px;
  border-radius: 10px;
}
.el-breadcrumb{
  position: relative;
  margin-top: 20px;
  margin-left: 20px
}
</style>