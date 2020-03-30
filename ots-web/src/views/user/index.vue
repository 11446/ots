<template>
  <div>
    <el-form
      @keyup.enter.native="searchUserByAcc"
      :inline="true"
      ref="searchForm"
      class="demo-form-inline"
      style="margin-top: 13px; height: 50px"
    >
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-circle-plus-outline"
          @click="addUser"
          size="small"
        >添加用户</el-button>
      </el-form-item>
      <el-form-item>
        <el-input v-model="userAcc" placeholder="账号" size="small"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="small" @click="searchUserByAcc" icon="el-icon-search">模糊查询</el-button>
        <el-button @click="reload" size="small">查询全部</el-button>
      </el-form-item>
    </el-form>
    <!-- 中心数据  -->
    <el-table :data="userData" border style="width: 100%">
      <el-table-column align="center" type="index" :index="indexMethod" label="序号"></el-table-column>
      <el-table-column align="center" prop="uname" label="姓名" width="180px"></el-table-column>
      <el-table-column align="center" prop="uaccount" label="账号"></el-table-column>
      <el-table-column align="center" prop="upwd" label="密码"></el-table-column>

      <el-table-column
        prop="urole"
        label="角色"
        align="center"
        :filters="[{ text: '学生', value: '学生' }, { text: '教师', value: '教师' }]"
        :filter-method="filterTag"
      >
        <template slot-scope="scope">
          <el-tag
            :type="scope.row.urole === '学生' ? 'primary' : 'success'"
            disable-transitions
          >{{scope.row.urole}}</el-tag>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="success"
            @click="updateUserPwd(scope.row)"
            style="background-color:#1890ff; border-color:#1890ff"
          >修改</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="removedUser(scope.row)"
            style="background-color:red; border-color:red"
          >移除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 分页插件 -->
    <el-pagination
      background
      layout="total, sizes, prev, pager, next, jumper"
      :total="AllUserInfoNum"
      :page-sizes="[10, 20, 50, 100]"
      @current-change="page"
      @size-change="handleSizeChange"
    ></el-pagination>
    <!-- 修改学生信息的 Dialog -->
    <el-dialog
      title="操作界面"
      style="width: 60%; margin: 15px auto"
      :visible.sync="dialogFormVisible"
      destroy-on-close
    >
      <el-form
        style="width: 100%; margin-left: -20px"
        :model="ruleForm"
        status-icon
        :rules="rules"
        ref="ruleForm"
        label-width="100px"
        class="demo-ruleForm"
      >
        <el-form-item label="姓名" prop="uname">
          <el-input type="text" v-model="ruleForm.uname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="账户" prop="uaccount">
          <el-input type="text" v-model="ruleForm.uaccount" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="upwd">
          <el-input type="text" v-model="ruleForm.upwd" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
          <el-button @click="cancle('ruleForm')">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>

    <!-- 添加用户的dislog -->
    <el-dialog
      style="width: 60%; margin: 15px auto;"
      title="添加用户"
      :visible.sync="addUserDialog"
      destroy-on-close
    >
      <el-form
        style="width: 100%; margin-left: -20px"
        :model="ruleForm"
        status-icon
        :rules="addUserRules"
        ref="ruleForm"
        label-width="100px"
      >
        <el-form-item label="姓名" prop="uname">
          <el-input type="text" v-model="ruleForm.uname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="账户" prop="uaccount">
          <el-input type="text" v-model="ruleForm.uaccount" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="upwd">
          <el-input type="text" v-model="ruleForm.upwd" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="角色" prop="urole">
          <el-select v-model="roleValue" placeholder="请选择">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
              :disabled="item.disabled"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitAddUserForm('ruleForm')">提交</el-button>
          <el-button @click="cancle('ruleForm')">取消</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import {
  getPageUserInfo,
  getAllUserInfo,
  verifyAccount,
  alterUserInfo,
  removeStudent,
  searchUserByAcc,
  addUser,
  validateNewAccount
} from "@/api/userinfo";
import axios from "axios";

export default {
  //   钩子函数请求第一页用户信息数据
  created() {
    const urole = JSON.parse(localStorage.getItem("user-info")).urole;
    axios.all([
        getPageUserInfo(this.page_data.PageNum, this.page_data.PageSize, urole),
        getAllUserInfo(urole)
      ])
      .then(
        axios.spread((acct, perms) => {
          this.userData = acct.data;
          this.AllUserInfoNum = perms.data.length;
        })
      )
      .catch(val => {
        Message("服务器出现错误！", "error")
      });
  },
  methods: {
    // 改变每页条数
    handleSizeChange(val) {
      const urole = JSON.parse(localStorage.getItem("user-info")).urole;
      getPageUserInfo(this.page_data.PageNum, val, urole).then(resp => {
        this.userData = resp.data;
        this.currentPage = val;
      });
    },
    //  管理员筛选角色
    filterTag(value, row) {
      return row.urole === value;
    },
    //  取消修改
    cancle(ruleForm) {
      this.dialogFormVisible = false;
      this.addUserDialog = false;
      this.ruleForm.id = "";
      this.ruleForm.uname = "";
      this.ruleForm.uaccount = "";
      this.ruleForm.upwd = "";
    },
    // 添加用户按钮
    addUser() {
      this.ruleForm = {};
      this.addUserDialog = true;
    },
    //  添加用户按钮的提交
    submitAddUserForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.ruleForm.urole = this.roleValue;
          addUser(this.ruleForm).then(resp => {
            if (resp.data) {
              this.dialogFormVisible = false;
              this.$message({ type: "success", message: "添加成功!" });
              this.page(this.thePage);
              this.addUserDialog = false;
            } else {
              this.addUserDialog = false;
              this.$message({type: "success", message: "添加失败"});
            }
          });
        } else {
          return "校验失败";
        }
      });
    },
    // 查询全部,刷新当前页面即可
    reload() {
      this.page(this.thePage);
    },
    // 修改按钮的提交
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          alterUserInfo(this.ruleForm).then(resp => {
            if (resp.data) {
              this.dialogFormVisible = false;
              this.$message({ type: "success", message: "修改成功!" });
              this.page(this.thePage);
            } else {
              alert("修改失败！");
            }
          });
        } else {
          return "校验失败";
        }
      });
    },
    // 分页按钮触发
    page(currentPage) {
      const urole = JSON.parse(localStorage.getItem("user-info")).urole;
      this.page_data.PageNum = (currentPage - 1) * this.currentPage;
      this.thePage = currentPage;
      getPageUserInfo(
        this.page_data.PageNum,
        this.page_data.PageSize,
        urole
      ).then(resp => {
        this.userData = resp.data;
        getAllUserInfo(urole).then(resp => {
          this.AllUserInfoNum = resp.data.length;
        });
      });
    },
    // 账号模糊查询
    searchUserByAcc() {
      if (this.fileName == "") {
        this.page(this.thePage);
      } else {
        searchUserByAcc(this.userAcc).then(resp => {
          this.userData = resp.data;
        });
      }
    },
    // 修改用户密码
    updateUserPwd(row) {
      this.ruleForm.id = row.id;
      this.ruleForm.uname = row.uname;
      this.ruleForm.uaccount = row.uaccount;
      this.ruleForm.upwd = row.upwd;
      this.dialogFormVisible = true;
    },
    // 移除用户
    removedUser(row) {
      this.$confirm("此操作将永久删除该用户, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true
      })
        .then(action => {
          removeStudent(row.id).then(resp => {
            if (resp.data) {
              this.$message({message: "删除成功",type: "success",duration: 2000});
              this.page(this.thePage);
            } else {
              alert("删除用户失败！");
            }
          });
        })
        .catch(() => {
          this.$message({type: "info",message: "已取消移除"});
        });
    },
    // 索引格式化
    indexMethod(index) {
      if (this.thePage == 1) {
        return index + 1;
      } else {
        return index + 1 + 10 * (this.thePage - 1);
      }
    }
  },
  data() {
    const validateUname = (rule, value, callback) => {
      if (value == this.ruleForm.uaccount) {
        callback();
      } else {
        // 失去焦点后验证账号是否重复
        verifyAccount(value, this.ruleForm.id).then(resp => {
          if (resp.data) {
            callback();
          } else {
            callback(new Error("账号重复！"));
          }
        });
      }
    };
    const validateNewAcc = (rule, value, callback) => {
      if (value == "" || value == null) {
        callback();
      } else {
        // 验证新用户账号是否重复
        validateNewAccount(value).then(resp => {
          if (resp.data) {
            // 账号不重复，验证通过
            callback();
          } else {
            callback(new Error("账号重复！"));
          }
        });
      }
    };
    return {
      userData: [],
      AllUserInfoNum: 0,
      page_data: {
        PageNum: 0,
        PageSize: 10
      },
      loading: true,
      isOnePage: false,
      thePage: 1,
      dialogFormVisible: false,
      userAcc: "",
      ruleForm: {
        id: "",
        uname: "",
        uaccount: "",
        upwd: "",
        urole: ""
      },
      rules: {
        uname: [{ required: true, message: "姓名不能为空", trigger: "blur" }],
        uaccount: [
          { required: true, message: "账号不能为空", trigger: "blur" },
          {
            pattern: /^[a-zA-Z]\w{4,17}$/,
            message: "以字母开头，长度在5-15之间，只能包含字符、数字"
          },
          { validator: validateUname, trigger: "change" }
        ],
        upwd: [
          { required: true, message: "密码不能为空", trigger: "blur" },
          {
            pattern: /^[a-zA-Z]\w{5,17}$/,
            message: "以字母开头，长度在6-18之间，只能包含字符、数字和下划线"
          }
        ]
      },
      addUserRules: {
        uname: [{ required: true, message: "姓名不能为空", trigger: "blur" }],
        uaccount: [
          { required: true, message: "账号不能为空", trigger: "blur" },
          {
            pattern: /^[a-zA-Z]\w{4,17}$/,
            message: "以字母开头，长度在5-15之间，只能包含字符、数字"
          },
          { validator: validateNewAcc, trigger: "change" }
        ],
        upwd: [
          { required: true, message: "密码不能为空", trigger: "blur" },
          {
            pattern: /^[a-zA-Z]\w{5,17}$/,
            message: "以字母开头，长度在6-18之间，只能包含字符、数字和下划线"
          }
        ]
      },
      addUserDialog: false,
      options: [
        {
          value: "学生",
          label: "学生"
        },
        {
          value: "教师",
          label: "教师",
          disabled:
            JSON.parse(localStorage.getItem("user-info")).urole == "教师"
        }
      ],
      roleValue: "学生",
      currentPage: 10
    };
  }
};
</script>
<style scoped>
.el-pagination {
  padding-top: 30px;
  padding-left: 0px;
}
</style>