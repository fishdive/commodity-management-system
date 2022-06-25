//设置全局表单提交格式
Vue.http.options.emulateJSON = true;

// Vue实例
new Vue({
    el: '#app',
    data() {
        return {
            register: {
                username: '',
                password: '',
                repassword: ''
            }
        }
    },
    methods: {
        //注册
        submitForm() {
            console.log(this.register);
            if (this.register.repassword != this.register.password) {
                // 弹出错误信息框
                this.$message.warning("两次输入的密码不相同");
                // 清空表单状态
                this.$refs.register.resetFields();
            } else {
                this.$refs.register.validate((valid) => {
                    if (valid) {
                        axios.post("/SSM_CRUD_war/user/register.action", {
                            name: this.register.username,
                            password: this.register.password
                        }).then((res) => {
                            console.log(res);
                            if (res.data.flag) {
                                this.$message.success(res.data.msg + "即将跳转至登录页面");
                                setTimeout(() => {
                                    window.location.href = "/SSM_CRUD_war/toLogin.action";
                                }, 2000);
                            } else {
                                this.$message.error(res.data.msg);
                            }
                            // 清空表单状态
                            this.$refs.register.resetFields();
                            //提交表单
                        })
                    } else {
                        this.$message.warning("输入信息有误");
                        return false;
                    }
                });
            }
        },
        registerEnter() {
            this.submitForm();
        }
    }
});
