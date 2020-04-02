<template>
  <div>
    <!-- 行内表单 -->
    <el-form
      ref="searchForm"
      :inline="true"
      class="demo-form-inline"
      style="margin-top: 13px ; height: 50px"
      @keyup.enter.native="searchFileByName"
    >
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-s-promotion"
          size="small"
          @click="test"
        >上传文件</el-button>
      </el-form-item>
      <el-form-item>
        <el-input
          v-model="fileName"
          size="small"
          placeholder="文件名称"
        />
      </el-form-item>
      <el-form-item style="height: 10px">
        <el-button
          type="primary"
          size="small"
          icon="el-icon-search"
          @click="searchFileByName"
          @keyup.enter.native="searchFileByName"
        >模糊查询</el-button>
        <el-button size="small" @click="reload">查询全部</el-button>
      </el-form-item>
    </el-form>
    <!-- 中心数据 -->
    <el-table :border="true" :data="tableData" style="width: 100%">
      <!-- <el-table-column  type="selection"></el-table-column> -->
      <el-table-column
        align="center"
        type="index"
        :index="indexMethod"
        label="序号"
      />
      <el-table-column
        align="center"
        prop="fileName"
        label="文件名称"
        width="180px"
      />
      <el-table-column
        align="center"
        prop="fileSize"
        label="文件大小"
        width="100px"
      />
      <el-table-column
        align="center"
        prop="fileDetail"
        label="文件描述"
        empty-text="暂无数据"
      />
      <el-table-column align="center" prop="uploadUser" label="上传人">
        <template slot-scope="scope">
          <el-tag disable-transitions>{{ scope.row.uploadUser }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" prop="upload_time" label="上传日期">
        <template slot-scope="scope">
          <el-tag disable-transitions>{{ scope.row.uploadTime }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column align="center" label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="success"
            style="background-color:#3ee83e; border-color:#3ee83e"
            @click="DownloadFile(scope.row)"
          >下载</el-button>
          <el-button
            v-if="username == '教师' || 'admin'"
            size="mini"
            type="danger"
            style="background-color:red; border-color:red"
            @click="RemovedFile(scope.row)"
          >移除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页插件 -->
    <el-pagination
      background
      layout="total, sizes, prev, pager, next, jumper"
      :total="AllFileInfoNum"
      :page-sizes="[10, 20, 50, 100]"
      @current-change="page"
      @size-change="handleSizeChange"
    />
  </div>
</template>
<style scoped>
.el-pagination {
  padding-top: 30px;
  padding-left: 0px;
}
</style>
<script>
import {
  getPageFileInfo,
  fileRemove,
  removeFileInfo,
  getAllFileInfo,
  searchFileByName
} from '../../api/file'
import axios from 'axios'
export default {
  data() {
    return {
      AllFileInfoNum: 0,
      page_data: {
        PageNum: 0,
        PageSize: 10
      },
      tableData: [],
      fileName: '',
      username: JSON.parse(localStorage.getItem('user-info')).urole,
      thePage: 1, // 当前在第几页
      currentPage: 10 // 多少条一页
    }
  },
  // 组件加载的时候，我们应该利用钩子函数去请求直接请求数据库里第一页的数据
  created() {
    axios
      .all([
        getPageFileInfo(this.page_data.PageNum, this.page_data.PageSize),
        getAllFileInfo()
      ])
      .then(
        axios.spread((acct, perms) => {
          this.tableData = acct.data
          console.log(perms)
          this.AllFileInfoNum = perms.data
        })
      )
      .catch(val => {
        console.log(val)
      })
  },
  methods: {
    test() {
      this.$router.push('/upload/')
    },
    // 每页条数改变
    handleSizeChange(val) {
      getPageFileInfo(this.page_data.PageNum, val).then(resp => {
        this.tableData = resp.data
        this.currentPage = val
      })
    },
    // 根据文件名查询查询
    searchFileByName() {
      //  如果模糊查询字段为空，则默认全部查询
      if (this.fileName === '') {
        this.page(this.thePage)
      } else {
        searchFileByName(this.fileName).then(resp => {
          this.tableData = resp.data
          this.AllFileInfoNum = Math.ceil(resp.data.length / 6)
        })
      }
    },
    DownloadFile(row) {
      // 获取到了文件名称
      this.DFileNama = row.fileName
      const url = 'http://47.93.189.114:8080/download/' + row.fileName
      window.location.href = url
    },
    // 移除文件信息
    RemovedFile(row) {
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true
      })
        .then(() => {
          fileRemove(row.fileName).then(resp => {
            if (resp.data) {
              // 如果本地文件删除成功，则删除数据库中的文件信息
              removeFileInfo(row.fileId).then(resp => {
                if (resp.data) {
                  this.$message({
                    message: '删除成功',
                    type: 'success',
                    duration: 2000
                  })
                  this.page(this.thePage)
                } else {
                  this.$message.error('删除失败！')
                }
              })
            } else {
              this.$message.error('删除失败！')
            }
          })
        })
        .catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          })
        })
    },
    // 分页组件触发
    page(currentPage) {
      this.page_data.PageNum = (currentPage - 1) * this.currentPage
      // console.log("从" + this.page_data.PageNum + "开始查询");
      this.page_data.PageSize = this.currentPage
      this.thePage = currentPage
      getPageFileInfo(this.page_data.PageNum, this.page_data.PageSize).then(
        resp => {
          this.tableData = resp.data
          getAllFileInfo().then(resp => {
            this.AllFileInfoNum = resp.data
          })
        }
      )
    },
    // 查询全部,重新请求该页信息
    reload() {
      this.page(this.thePage)
    },
    indexMethod(index) {
      if (this.thePage === 1) {
        return index + 1
      } else {
        return index + 1 + 10 * (this.thePage - 1)
      }
    },
    // 复选框的操作
    handleSelectionChange(val) {
      console.log(val)
    }
  }
}
</script>
