<template>
  <div class="main">
    <!-- <el-breadcrumb v-show="$route.path !== '/home'" separator="/">
      <el-breadcrumb-item :to="{ path: $route.path }" class="line">{{$route.meta.title}}</el-breadcrumb-item>
    </el-breadcrumb> -->
    <!-- <el-page-header v-show="$route.path !== '/home'" @back="goBack" :content="$route.meta.title">
    </el-page-header> -->
    <!-- 过渡动画 -->
    <transition name="fade-transform" mode="out-in">
      <router-view></router-view>
    </transition>
  </div>
</template>
<script>
export default {
  created() {
    this.$Loading.start();
  },
  mounted() {
    this.$Loading.finish();
  }
};
</script>
<style scoped>
.main {
  position: absolute;
  left: 200px;
  right: 0px;
  top: 60px;
  bottom: 0px;
  padding: 18px;
  overflow-y: auto;
  /* background-image: url('../assets/印度尼西亚Kelingking Beach海滩风景5k壁纸_彼岸图网.jpg') */
}
.el-breadcrumb {
  height: 10px;
  padding: 20px;
  padding-bottom: 30px;
  border-radius: 5px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  background-color: white;
}
.line {
  border-left: 3px solid #31c17b;
  padding-left: 10px;
}
</style>
<style scope lang="scss">
// global transition css

/* fade */
.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.28s;
}

.fade-enter,
.fade-leave-active {
  opacity: 0;
}

/* fade-transform */
.fade-transform-leave-active,
.fade-transform-enter-active {
  transition: all 0.5s;
}

.fade-transform-enter {
  opacity: 0;
  transform: translateX(-30px);
}

.fade-transform-leave-to {
  opacity: 0;
  transform: translateX(30px);
}

/* breadcrumb transition */
.breadcrumb-enter-active,
.breadcrumb-leave-active {
  transition: all 0.5s;
}

.breadcrumb-enter,
.breadcrumb-leave-active {
  opacity: 0;
  transform: translateX(20px);
}

.breadcrumb-move {
  transition: all 0.5s;
}

.breadcrumb-leave-active {
  position: absolute;
}
</style>
