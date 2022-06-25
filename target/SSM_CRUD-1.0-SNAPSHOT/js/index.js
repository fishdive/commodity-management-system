//设置全局表单提交格式
// Vue.http.options.emulateJSON = true;

//Vue实例
new Vue({
    el: '#app',
    data() {
        return {
            userName: '',

            activeIndex: '1'
        }
    },
    methods: {},
    //声明周期钩子函数-->在data和methods渲染结束后执行
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
    }
});