//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

// Vue实例
new Vue({
    el: '#app',
    data() {
        return {
            login: {
                name: '',
                password: '',
                remember: ''
            },
            loading: {}, //loading动画
        };
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
        //登录
        submitForm() {
            this.$refs.login.validate((valid) => {
                console.log(this.login.remember);
                console.log(this.login);
                if (valid) {
                    this.loadings(); //加载动画
                    //提交表单
                    axios.post("/SSM_CRUD_war/user/login.action", this.login).then((res) => {
                        if (res.data.flag) {
                            this.$message.success(res.data.msg);
                            if (this.login.remember) {
                                $.cookie('name', this.login.name, {
                                    expires: 10
                                });
                                $.cookie('password', this.login.password, {
                                    expires: 10
                                });
                                $.cookie('remember', this.login.remember, {
                                    expires: 10
                                })
                            } else {
                                $.cookie('name', null);
                                $.cookie('password', null);
                                $.cookie('remember', null)
                            }
                            window.location.href = "/SSM_CRUD_war/toIndex.action";
                            this.loading.close(); //关闭动画加载
                        } else {
                            this.$message.error(res.data.msg);
                        }

                        // 清空表单状态
                        this.$refs.login.resetFields();
                    });
                } else {
                    this.message.warning("输入信息有误！");
                    return false;
                }
            });
        },
        loginEnter() {
            this.submitForm();
        }

    }, created() {
        if ($.cookie('remember') == "true") {
            this.login.remember = true;
            this.login.name = $.cookie('name');
            this.login.password = $.cookie('password');
        }
    }
});