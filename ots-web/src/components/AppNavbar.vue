<template>
  <div class="navbar">
    <img class="timg" src="../assets/uugai.com_1583153838790.png">
    <!-- :router：开启路由模式    default-active：默认选中   -->
    <el-menu
      :router="true"
      default-active="/file/"
      class="el-menu-vertical-demo"
      background-color="#304156"
      text-color="#ffffff"
      active-text-color="#409eff"
    >
      <!-- 首页 -->
      <el-menu-item index="/home/">
        <i class="el-icon-s-home" />
        <span slot="title">首页</span>
      </el-menu-item>

      <!-- 文件管理 -->
      <el-menu-item index="/file/">
        <i class="el-icon-reading" />
        <span slot="title">文件列表</span>
      </el-menu-item>

      <el-menu-item index="/upload/">
        <i class="el-icon-upload" />
        <span slot="title">上传文件</span>
      </el-menu-item>
      <!-- <el-menu-item index="/goods/">
        <i class="el-icon-s-goods"></i>
        <span slot="title">文档分类</span>
      </el-menu-item> -->
      <el-menu-item v-if="urole == '教师' || urole == 'admin'" index="/user/">
        <i class="el-icon-s-custom" />
        <span slot="title">用户管理</span>
      </el-menu-item>
      <el-menu-item index="/board/">
        <i class="el-icon-notebook-2" />
        <span slot="title">留言板</span>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<style scoped>
.navbar {
  position: absolute;
  width: 200px;
  top: 0px;
  left: 0px;
  bottom: 0px;
  overflow-y: auto;
  background-color: #304156;
}
.el-menu {
  border-right: none;
}
.el-menu-item {
  font-family: "宋体";
  font-size: 17px;
}
.timg {
  position: relative;
  margin-top: 5px;
  width: 200px;
}
</style>
<script>
export default {
  data() {
    return {
      urole: JSON.parse(localStorage.getItem('user-info')).urole
    }
  }
}
</script>
