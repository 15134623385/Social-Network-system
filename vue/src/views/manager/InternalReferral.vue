<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="内推标题" prop="Internalreferral title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入内推标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="内推标签" prop="Internalreferral tag">
        <el-input
          v-model="queryParams.tag"
          placeholder="请输入内推标签"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="内推点赞数" prop="Internalreferral likes">
        <el-input
          v-model="queryParams.likes"
          placeholder="请输入内推点赞数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="内推发表时间" prop="date">
        <el-date-picker clearable
          v-model="queryParams.date"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择内推发表时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="内推阅读量" prop="readCnt">
        <el-input
          v-model="queryParams.readCnt"
          placeholder="请输入内推阅读量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['internalreferral:internalreferral:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['internalreferral:internalreferral:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['internalreferral:internalreferral:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['internalreferral:internalreferral:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="internalreferralList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="内推id" align="center" prop="id" />
      <el-table-column label="内推标题" align="center" prop="Internalreferral title" />
      <el-table-column label="内推标签" align="center" prop="Internalreferral tag" />
      <el-table-column label="内推内容" align="center" prop="Internalreferral content" />
      <el-table-column label="内推点赞数" align="center" prop="Internalreferral likes" />
      <el-table-column label="内推发表时间" align="center" prop="date" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.date, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="内推阅读量" align="center" prop="readCnt" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['internalreferral:internalreferral:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['internalreferral:internalreferral:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改内推管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="内推标题" prop="Internalreferral title">
          <el-input v-model="form.title" placeholder="请输入内推标题" />
        </el-form-item>
        <el-form-item label="内推标签" prop="Internalreferral tag">
          <el-input v-model="form.tag" placeholder="请输入内推标签" />
        </el-form-item>
        <el-form-item label="内推内容">
          <editor v-model="form.content" :min-height="192"/>
        </el-form-item>
        <el-form-item label="内推点赞数" prop="Internalreferral likes">
          <el-input v-model="form.likes" placeholder="请输入内推点赞数" />
        </el-form-item>
        <el-form-item label="内推发表时间" prop="date">
          <el-date-picker clearable
            v-model="form.date"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择内推发表时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="内推阅读量" prop="readCnt">
          <el-input v-model="form.readCnt" placeholder="请输入内推阅读量" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listInternalreferral, getInternalreferral, delInternalreferral, addInternalreferral, updateInternalreferral } from "@/api/internalreferral/internalreferral";

export default {
  name: "Internalreferral",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 内推管理表格数据
      internalreferralList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: null,
        tag: null,
        content: null,
        likes: null,
        date: null,
        readCnt: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        title: [
          { required: true, message: "内推标题不能为空", trigger: "blur" }
        ],
        tag: [
          { required: true, message: "内推标签不能为空", trigger: "blur" }
        ],
        content: [
          { required: true, message: "内推内容不能为空", trigger: "blur" }
        ],
        likes: [
          { required: true, message: "内推点赞数不能为空", trigger: "blur" }
        ],
        date: [
          { required: true, message: "内推发表时间不能为空", trigger: "blur" }
        ],
        readCnt: [
          { required: true, message: "内推阅读量不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询内推管理列表 */
    getList() {
      this.loading = true;
      listInternalreferral(this.queryParams).then(response => {
        this.internalreferralList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        title: null,
        tag: null,
        content: null,
        likes: null,
        date: null,
        readCnt: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加内推管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getInternalreferral(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改内推管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateInternalreferral(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInternalreferral(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除内推管理编号为"' + ids + '"的数据项？').then(function() {
        return delInternalreferral(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('internalreferral/internalreferral/export', {
        ...this.queryParams
      }, `internalreferral_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
