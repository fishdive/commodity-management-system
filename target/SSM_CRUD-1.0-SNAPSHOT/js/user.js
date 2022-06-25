new Vue({
    el: "#app",
    data: {
        username: '',
        activeIndex: '2',
        //表展示数据
        tableData: [],
        //编辑表单是否显示
        dialogFormVisible4Edit: false,
        //编辑表单数据
        formData: {
            name: '',
            password: '',
            grad: '',
            state: ''
        },
        //添加表单是否显示
        dialogFormVisible4Add: false,
        //添加表单数据
        addFormData: {
            name: '',
            password: '',
            grade: '',
            state: ''
        },
        //等级选择键值对
        gradeOptions: [{
            value: '01',
            label: '管理员'
        }, {
            value: '02',
            label: '普通用户'
        }],
        //状态选择键值对
        stateOptions: [{
            value: '01',
            label: '启用'
        }, {
            value: '02',
            label: '弃用'
        }],
        findValue: {
            id: '',
            name: '',
            grade: '',
            state: ''
        }, findData: {},
        //分页选项
        pageConf: {
            //设置一些初始值(会被覆盖)
            pageCode: 1, //当前页
            pageSize: 6, //每页显示的记录数
            totalPage: 12, //总记录数
            pageOption: [6, 10, 20], //分页选项
        },
    },
    //钩子函数，VUE对象初始化完成后自动执行
    created() {
        //获取当前登录用户名
        axios.get("/SSM_CRUD_war/user/getName.action").then((res) => {
            if (res.data.flag) {
                console.log(res.data.data);
                this.userName = res.data.data;
                document.getElementById("userName").innerText = res.data.data;
            } else {
                this.$message().error(res.data.msg);
            }
        });
        this.search(this.pageConf.pageCode, this.pageConf.pageSize);
    },
    methods: {
        //编辑表单数据填充
        handleEdit(index, row) {
            this.formData.name = row.name;
            this.formData.password = row.password;
            this.formData.grade = row.grade;
            this.formData.state = row.state;
            this.dialogFormVisible4Edit = true;
            console.log(index, row);
        },
        //删除
        handleDelete(index, row) {

            console.log(123);
            console.log(index, row);
            //1.弹出提示框
            this.$confirm("此操作永久删除当前数据，是否继续？", "提示", {
                type: 'info'
            }).then(() => {
                //2.做删除业务
                axios.delete("/SSM_CRUD_war/user/delete.action?name=" + row.name).then((res) => {
                    if (res.data.flag) {
                        this.$message.success(res.data.msg);
                    } else {
                        this.$message.error(res.data.msg);
                    }
                }).finally(() => {
                    this.search(this.pageConf.pageCode, this.pageConf.pageSize);
                });
            }).catch(() => {
                //3.取消删除
                this.$message.info("取消删除操作");
            });
        },
        //修改
        handleEnter() {
            axios.post("/SSM_CRUD_war/user/update.action", this.formData).then((res) => {
                if (res.data.flag) {
                    this.$message.success(res.data.msg);
                } else {
                    this.$message.error(res.data.msg);
                }
            }).finally(() => {
                this.search(this.pageConf.pageCode, this.pageConf.pageSize);
                this.cancel();
            });
            console.log(this.formData);
        },
        //清空编辑表单
        cancel() {
            this.formData = {};
            this.dialogFormVisible4Edit = false;
        },
        //条件查询条件赋值
        findByTerm() {
            this.findData = this.findValue;
            this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        },
        //添加表单显示
        handleAdd() {
            this.dialogFormVisible4Add = true;
        },
        //添加表单隐藏
        cancelAdd() {
            this.addFormData = {};
            this.dialogFormVisible4Add = false;
        },
        //添加
        handleEnterAdd() {
            axios.post("/SSM_CRUD_war/user/register.action", this.addFormData).then((res) => {
                if (res.data.flag) {
                    this.$message.success(res.data.msg);
                    this.cancelAdd();
                } else {
                    this.$message.error(res.data.msg);
                }
            }).finally(() => {
                this.search(this.pageConf.pageCode, this.pageConf.pageSize);
            });
        },
        //分页查询
        search(pageCode, pageSize) {
            axios.post('/SSM_CRUD_war/user/findByConPage.action?pageSize=' + pageSize + '&pageCode=' + pageCode, this.findData).then(res => {

                console.log(res);
                if (res.data.flag) {
                    this.tableData = res.data.data.rows;
                    this.pageConf.totalPage = res.data.data.total;
                    this.$message.success(res.data.msg);
                } else {
                    this.$message.error(res.data.msg);
                }
            });

        },
        //pageSize改变时触发的函数
        handleSizeChange(val) {
            console.log("分页大小改变" + this.pageConf.pageCode + "," + val);
            this.search(this.pageConf.pageCode, val);
        },
        //当前页改变时触发的函数
        handleCurrentChange(val) {
            console.log("当前页改变" + val + "," + this.pageConf.pageSize);
            this.pageConf.pageCode = val; //为了保证刷新列表后页面还是在当前页，而不是跳转到第一页
            this.search(val, this.pageConf.pageSize);
        }
    }
});