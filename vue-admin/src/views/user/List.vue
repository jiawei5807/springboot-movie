<template>
  <div class="main">
    <el-table
        :data="userList"
        style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="用户名">
              <span>{{ props.row.username }}</span>
            </el-form-item>
            <el-form-item label="昵称">
              <span>{{ props.row.nickname }}</span>
            </el-form-item>
            <el-form-item label="密码">
              <el-button type="text" @click="openUpdatePassword(props.row)">
                <i class="el-icon-edit"></i>编辑密码
              </el-button>
            </el-form-item>
            <el-form-item label="邮箱">
              <span>{{ props.row.email }}</span>
            </el-form-item>
            <el-form-item label="性别">
              <span>{{ props.row.gender }}</span>
            </el-form-item>
            <el-form-item label="生日">
              <span>{{ props.row.birthday }}</span>
            </el-form-item>
            <el-form-item label="个人简介">
              <span>{{ props.row.info }}</span>
            </el-form-item>
            <el-form-item label="更新时间">
              <span>{{ props.row.updateAt }}</span>
            </el-form-item>
            <el-form-item label="用户头像 URL">
              <a target="_blank" :href="props.row.avatar">{{ props.row.avatar }}</a>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column
          label="用户 ID"
          width="430"
          prop="id">
      </el-table-column>
      <el-table-column
          label="用户名"
          width="200"
          prop="username">
      </el-table-column>
      <el-table-column
          label="昵称"
          width="200"
          prop="nickname">
      </el-table-column>
      <el-table-column
          label="注册时间"
          prop="createAt">
      </el-table-column>
      <el-table-column
              label="购票信息">
        <template style="white-space: nowrap" slot-scope="scope">
          <el-button
                  size="mini"
                  icon="el-icon-edit"
                  type="primary"
                  @click="userOrderInfo(scope.row.id)"
                  >详情
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog
            title="用户购票记录"
            :visible.sync="dialog1"
            width="80%">

      <el-table
              v-loading="loading"
              :data="orders"
              style="width: 100%">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item label="订单ID：">
                <span>{{ props.row.order.id }}</span>
              </el-form-item>
              <el-form-item label="用户ID：">
                <span>{{ props.row.user.id }}</span>
              </el-form-item>
              <el-form-item label="电影ID：">
                <span>{{ props.row.film.id }}</span>
              </el-form-item>
              <el-form-item label="场次ID：">
                <span>{{ props.row.arrangement.id }}</span>
              </el-form-item>
              <el-form-item label="电影名：">
                <span>《 {{ props.row.film.name }} 》</span>
              </el-form-item>
              <el-form-item label="座位号：">
                <span>{{ props.row.order.seats }}</span>
              </el-form-item>
              <el-form-item label="订单金额：">
                <span>{{ props.row.order.price }}</span>
              </el-form-item>
              <el-form-item label="下单时间：">
                <span>{{ props.row.order.createAt }}</span>
              </el-form-item>
              <el-form-item label="支付时间：">
                <span>{{ props.row.order.payAt }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column
                label="订单 ID"
                width="400"
                prop="order.id">
        </el-table-column>
        <el-table-column
                width="200"
                label="订单金额"
                prop="order.price">
        </el-table-column>
        <el-table-column label="订单状态">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.order.status === 2" type="success">支付成功</el-tag>
            <el-tag v-if="scope.row.order.status === 0" type="info">等待支付</el-tag>
            <el-tag v-if="scope.row.order.status === 3" type="warning">已被撤销</el-tag>
            <el-tag v-if="scope.row.order.status === 1" type="danger">支付失败</el-tag>
          </template>
        </el-table-column>
      </el-table>

      <span slot="footer" class="dialog-footer">
        <el-button @click="closeDialog()">关 闭</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {FindAllUser, UpdateUser} from "@/api/user";
import {FindUserOrder} from "@/api/order";
export default {


  data() {
    return {
      userList: [],
      loading:false,
      dialog1:false,
      orders: [],
    }
  },

  mounted() {
    FindAllUser().then(res => {
      this.userList = res.data
    })

  },

  methods: {

    openUpdatePassword(user) {
      this.$prompt('请输入新的密码', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(({value}) => {
        if (!value) {
          this.$message({
            type: 'danger',
            message: '密码不能为空'
          });
          return
        }
        if (value.length < 6) {
          this.$message({
            type: 'danger',
            message: '请输入不少于6位的密码'
          });
          return
        }
        user.password = value
        UpdateUser(user).then(res => {
          this.$message({
            message: '账号密码修改成功',
            type: 'success',
          });
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '取消修改'
        });
      });
    },
    userOrderInfo(uId){
      this.loading=true;
      FindUserOrder(uId).then(res=>{
        if (res.success) {
          setTimeout(() => {
            this.orders = res.data;
            this.loading = false;
            this.dialog1 = true;
          }, 700)
        }
      })
    },
    closeDialog(){
      this.dialog1=false;
    }
  },

}
</script>

<style scoped>
.demo-table-expand {
  font-size: 0;
}

.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}

.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>