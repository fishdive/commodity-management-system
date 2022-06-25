/**
 * 校外客户信息列表
 */

//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

// Vue实例
var vm = new Vue({
    el: '#app',
    data() {
        return {
            //当前登录用户
            userName: '',
            //element-ui的table需要的参数必须是Array类型的
            goods: [{
                id: '',
                title: '',
                price: '',
                image: '',
                brand: ''
            }],

            //编辑表
            editor: {
                title: '',
                price: '',
                image: '',
                brand: ''
            },
            //添加dialog
            showSave: false,
            //编辑dialog
            showEditor: false,

            //条件查询单独封装的对象
            searchEntity: {},


            //分页选项
            pageConf: {
                //设置一些初始值(会被覆盖)
                pageCode: 1, //当前页
                pageSize: 6, //每页显示的记录数
                totalPage: 12, //总记录数
                pageOption: [6, 10, 20], //分页选项
            },

            loading: {},

            //文件上传的参数
            dialogImageUrl: '',
            dialogVisible: false,
            //图片列表（用于回显图片）
            fileList: [{name: '', url: ''}],

            activeIndex: '3', //默认激活
            goodsList: []
        }
    },
    methods: {
        /**
         * loading加载动画
         */
        loadings() {
            this.loading = this.$loading({
                lock: true,
                text: '拼命加载中',
                spinner: 'el-icon-loading',
            });
            setTimeout(() => {
                this.loading.close();
            }, 2000);
        },

        /**
         * Public method
         */
        //刷新列表
        reloadList() {
            console.log("totalPage:" + this.pageConf.totalPage + ",pageSize:" + this.pageConf.pageSize + ",:pageCode:" + this.pageConf.pageCode);
            this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        },
        //条件查询
        search(pageCode, pageSize) {
            this.loadings();
            axios.get('/SSM_CRUD_war/goods/findByConPage.action?pageSize=' + pageSize + '&pageCode=' + pageCode + '&title=' + this.searchEntity.title + '&brand=' + this.searchEntity.brand).then(res => {
                console.log(res);
                if (res.data.flag) {
                    this.goods = res.data.data.rows;
                    this.pageConf.totalPage = res.data.data.total;
                    this.loading.close(); //数据更新成功就手动关闭动画
                    this.$message.success(res.data.msg);
                } else {
                    this.$message.error(res.data.msg);
                }
            });
        },
        //pageSize改变时触发的函数
        handleSizeChange(val) {
            this.search(this.pageConf.pageCode, val);
        },
        //当前页改变时触发的函数
        handleCurrentChange(val) {
            this.pageConf.pageCode = val; //为了保证刷新列表后页面还是在当前页，而不是跳转到第一页
            this.search(val, this.pageConf.pageSize);
        },
        //更新
        sureEdit(editor) {
            //关闭对话框
            this.showEditor = false;
            //调用更新数据的接口
            axios.post("/SSM_CRUD_war/goods/update.action", this.editor).then((res) => {
                if (res.data.flag) {
                    this.$message.success(res.data.msg);
                    this.goods = [];

                } else {
                    this.$message.error(res.data.msg);
                }
            }).finally(() => {
                //刷新列表
                this.reloadList();
                this.$refs.editor.resetFields();
            });
        },

        //添加
        save(editor) {
            console.log(this.editor);
            this.$refs[editor].validate((valid) => {
                if (valid) {
                    //关闭dialog
                    this.showSave = false;
                    //调用保存的接口
                    axios.post("/SSM_CRUD_war/goods/create.action", this.editor).then((res) => {
                        if (res.data.flag) {
                            this.$message.success(res.data.msg);

                            this.editor = {};
                            this.$refs.editor.resetFields();
                        } else {
                            this.$message.error(res.data.msg);
                        }
                    }).finally(() => {
                        //刷新表格
                        this.reloadList();
                    });
                } else {
                    this.$message().error("输入信息有误！")
                    return false;
                }
            });
        },

        /**
         * Private method
         */
        //新增按钮
        saveBtn() {
            //打开新增dialog
            this.showSave = true;
            this.editor = {}; //清空表单
            this.fileList = []; //清空文件列表
            //清空原始数据
            if (this.$refs['editor'] !== undefined) {
                this.$refs['editor'].resetFields(); //经查询：可能是由于对象还没有生成，导致误读了空对象而报错
            }
        },
        //更新按钮（表格）
        handleEdit(row) {
            //打开dialog
            this.showEditor = true;
            this.editor = row;
        },
        //删除按钮
        handleDelete(row) {
            //1.弹出提示框
            this.$confirm("此操作永久删除当前数据，是否继续？", "提示", {
                type: 'info'
            }).then(() => {
                //2.做删除业务
                axios.delete("/SSM_CRUD_war/goods/delete.action?id=" + row.id).then((res) => {
                    if (res.data.flag) {
                        //即使调用reloadList()刷新列表，但是对于删除，在reloadList()中获取到的totalPage总记录和pageCode当前页都是未删除之前的记录，当遇到删除此页的最后一个记录时，页码会自动跳到上一页，但是table中的数据显示"暂无记录"
                        //所以要判断，如果是删除此页的最后一条记录，删除后自动跳转到前一页，数据也是前一页的数据
                        if ((this.pageConf.totalPage - 1) / this.pageConf.pageSize === (this.pageConf.pageCode - 1)) {
                            this.pageConf.pageCode = this.pageConf.pageCode - 1;
                        }
                        this.$message.success(res.data.msg);
                    } else {
                        this.$message.error(res.data.msg);
                    }
                }).finally(() => {
                    //刷新列表
                    this.reloadList();
                });
            }).catch(() => {
                //3.取消删除
                this.$message.info("取消删除操作");
            });
        },

        /**
         * 图片上传
         * @param res
         * @param file
         * @param fileList
         */
        //文件上传成功的钩子函数
        handleSuccess(res, file, fileList) {
            this.$message({
                type: 'info',
                message: '图片上传成功',
                duration: 6000
            });
            console.log(file.response);
            console.log(file);
            if (file.response.flag) {
                this.editor.image = file.response.msg; //将返回的文件储存路径赋值image字段
            }
        },
        //删除文件之前的钩子函数
        handleRemove(file, fileList) {
            console.log(file, fileList);
            this.$message({
                type: 'info',
                message: '已删除原有图片',
                duration: 6000
            });
        },
        //点击列表中已上传的文件事的钩子函数
        handlePreview(file) {
            // this.dialogImageUrl = file.url;
            // this.dialogVisible = true;

        },
        //上传的文件个数超出设定时触发的函数
        onExceed(files, fileList) {
            this.$message({
                type: 'info',
                message: '最多只能上传一个图片',
                duration: 6000
            });
        },
        //文件上传前的前的钩子函数
        //参数是上传的文件，若返回false，或返回Primary且被reject，则停止上传
        beforeUpload(file) {
            const isJPG = file.type === 'image/jpeg';
            const isGIF = file.type === 'image/gif';
            const isPNG = file.type === 'image/png';
            const isBMP = file.type === 'image/bmp';
            const isLt2M = file.size / 1024 / 1024 < 2;

            if (!isJPG && !isGIF && !isPNG && !isBMP) {
                this.$message.error('上传图片必须是JPG/GIF/PNG/BMP 格式!');
            }
            if (!isLt2M) {
                this.$message.error('上传图片大小不能超过 2MB!');
            }
            return (isJPG || isBMP || isGIF || isPNG) && isLt2M;
        },

    },

    // 生命周期函数
    created() {
        // this.findAll();
        //获取当前登录用户名
        axios.get("/SSM_CRUD_war/user/getName.action").then((res) => {
            if (res.data.flag) {
                this.userName = res.data.data;
                document.getElementById("userName").innerText = res.data.data;
            } else {
                this.$message().error(res.data.msg);
            }
        });
        this.search(this.pageConf.pageCode, this.pageConf.pageSize);
        this.loadings(); //加载动画
    },

});
