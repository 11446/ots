<template>
  <div>
    <div class="msg">
      <el-input
        type="text"
        :autosize="{ minRows: 1, maxRows: 4}"
        placeholder="请输入标题"
        v-model="title"
        clearable
        style="width: 350px"
        minlength="2"
        maxlength="20"
        show-word-limit
      ></el-input>
      <div style="margin: 20px 0;"></div>
      <el-input
        type="textarea"
        :autosize="{ minRows: 5, maxRows: 5}"
        placeholder="请输入内容"
        v-model="content"
        style="width: 800px"
        maxlength="500"
        show-word-limit
        resize="none"
      ></el-input>
      <br />
      <el-button type="primary" style="margin-top: 10px" @click="publishMessage">发布</el-button>
      <hr style="margin-top: 10px;color: blue" />
    </div>
    <span class="mes">
      <!-- 留言信息 -->
      <List v-model="activeName" v-for="(mes,index) in message" :key="index"  >
        <ListItem>
            <ListItemMeta 
             :avatar="getAvatar(mes.mesUrole)"
             :title="mes.mesUname+'：'+mes.mesTitle"
             :description="mes.mesContent"
              />            
            <template slot="action" v-if= " mes.mesUid === userID || urole == 'admin'">
              <li>
                  <a @click="updateMes(mes)">修改</a>
              </li>
              <li>
                  <a @click="deleteMes(mes.id)">删除</a>                  
              </li>
            </template>
        </ListItem>
            
      </List>
      <!-- 分页插件 -->
      <el-pagination
        background
        layout="total, sizes, prev, pager, next, jumper"
        :page-sizes="[10, 20, 50, 100]"
        @size-change="handleSizeChange"
        :total="allMesInfoNum"
        @current-change="page"
        style="position: absolute">
      </el-pagination>
    </span>
    <!-- 修改留言 -->
    <el-dialog title="修改留言" :visible.sync="dialogFormVisible">
      <el-form :model="mesInfo">
        <el-form-item label="内容">
          <el-input  :autosize="{ minRows: 5, maxRows: 5}" type="textarea" v-model="mesInfo.mes_content" autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="update">确 定</el-button>
      </div>
    </el-dialog> 
  </div>
</template>

<script>
import {setMessageInfo,getPageMesInfo,getAllMesInfo,updateMessage,deleteMessage} from '@/api/message' 
export default {
  // 利用钩子函数对留言进行初始化
  created(){
    // 默认加载第一页的留言信息
      getPageMesInfo(this.page_data.PageNum,this.page_data.PageSize).then(resp=>{
        this.message = resp.data
         // 获取所有留言信息，以便确定页数
     getAllMesInfo().then(resp=>{
          this.allMesInfoNum = resp.data.length
     })
    })
   },
  data() {
    return {
      message: [],
      mesInfo: {
          id: "",
          mesUid: 0,
          mesTitle: "",
          mesContent: "",
          mesUname: "",
          mesTime: "",
          mesUrole: ""
       },
      page_data: {
          PageNum: 0,    // 当前页数
          PageSize: 10   // 每页条数
       },
      activeName: 1,
      title: "",
      content: "",
      allMesInfoNum: 0,   // 总条数
      thePage: 1,   // 当前在第几页
      dialogFormVisible: false,
      userID : JSON.parse(localStorage.getItem('user-info')).id,
      urole : JSON.parse(localStorage.getItem('user-info')).urole,
      currentPage: 10
    };
  },
  methods: {
    handleSizeChange(val){
        getPageMesInfo(0, val).then(resp=>{
          this.message = resp.data
          this.currentPage  = val
          console.log("当前一页条数为："+this.currentPage )
        })
    },
    // 删除留言
    deleteMes(id){
        deleteMessage(id).then(resp=>{
          this.$notify({title: '成功',message: '删除成功！',type: 'success'});
          this.page(1)
        })
    },
    // 修改留言
    update(){
        updateMessage(this.mesInfo).then(resp=>{
          this.page(this.thePage)
          this.dialogFormVisible = false
           this.$notify({
          title: '成功',
          message: '修改成功！',
          type: 'success'
        });
      })
    },
    // 修改dialog触发
    updateMes(mes){
      this.mesInfo.mesContent = mes.mesContent
      this.mesInfo.id = mes.id
      this.dialogFormVisible = true
    },
    // 分页按钮触发
    page(currentPage){  
      console.log("触发了分页按钮："+currentPage)  
          this.page_data.PageNum = (currentPage - 1) * this.currentPage;
          this.thePage = currentPage
          
          getPageMesInfo(this.page_data.PageNum,this.page_data.PageSize).then(resp => {
            this.message = resp.data;
             getAllMesInfo().then(resp=>{
                 this.allMesInfoNum = resp.data.length
            }) 
          });
     },
    // 发布评论
    publishMessage() {
      this.mesInfo.mesUid = JSON.parse(localStorage.getItem("user-info")).id
      this.mesInfo.mesTitle = this.title
      this.mesInfo.mesContent = this.content
      this.mesInfo.mesUname = JSON.parse(localStorage.getItem("user-info")).uname
      this.mesInfo.mesTime = this.getNowFormatDate()
      this.mesInfo.mesUrole = JSON.parse(localStorage.getItem("user-info")).urole
      setMessageInfo(this.mesInfo).then(resp=>{
        if(resp){
          this.$message({showClose: true,message: '发布成功！', type: 'success'});
          this.page(this.thePage)
        }else{
           this.$message({showClose: true, message: '发布失败，请重新发布！',type: 'error'});
        }
      })
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
    },
    // 切换头像
    getAvatar(urole){
      if(urole == "admin"){
        return "https://zhangshuai-bucket.oss-cn-beijing.aliyuncs.com/ots/avatar/%E7%AE%A1%E7%90%86%E5%91%98.png"
      }else if(urole == "教师"){
        return "https://zhangshuai-bucket.oss-cn-beijing.aliyuncs.com/ots/avatar/%E6%95%99%E5%B8%88%E8%8A%82.png"
      }else{
        return "https://zhangshuai-bucket.oss-cn-beijing.aliyuncs.com/ots/avatar/%E5%AD%A6%E7%94%9F.png"
      }
    }
  }
};
</script>
<style scoped>

.msg {
  position: absolute;
  margin-top: 15px;
}
.mes {
  position: absolute;
  top: 300px;
}

</style>