let api = {
    view: true,
    /**
     * 全屏显示
     */
    homeView : function fullScreenView(){
        if (this.view) {
            $(".left-nav").css({left: "-251px"});
            $(".head-nav").css({width: "100%"});
            $(".main").css({width: "100%"},"slow");
            this.view = false;
        }else{
            $(".left-nav").css({left: "0px"});
            $(".head-nav").css({width: "calc(100% - 250px)"});
            $(".main").css({width: "calc(100% - 250px)"});
            this.view = true;
        }
    },
    /**
     * 更新用户状态
     * @param state
     * @param id
     * @returns {boolean}
     */
    upstate : function upuserdate(state,id) {
        if(state == 1){
            alert("管理员账户不可修改！")
        }else{
            if (confirm((state == 2) ? "你确定要解锁该账户吗？":"你确定要锁定该账户吗？")){
                $.ajax({
                    type: "post",
                    url: "/admin.update",
                    data: {state:(state == 2) ? 0:2, id:id},
                    dataType: "json",
                    success:function(data){
                        alert(data);
                        window.location.replace("/admin.user");
                    },
                    error : function(e){
                        if(e.status === 200) {
                            alert(e.responseText);
                            window.location.replace("/admin.user");
                        }
                    }
                });
            }else{
                return false;
            }

        }
    },
    /**
     * 消息提示于页面跳转
     * @param msg
     * @param url
     */
    tips : function msgtips(msg,url){
        if (msg != null) {
            alert(msg);
            window.location.replace(url);
        }
    },
    /**
     * 加载二级联动
     * @param column
     * @param column_value
     * @param columnclass
     * @param classDate
     */
    initarea : function init_area(column,column_value,columnclass,classDate){
        //移除area除第一个选项以外的其他选项
        columnclass.find("option:not(:first)").remove();
        for(let i = 0;i < classDate.length;i++){

            if (column_value == classDate[i].columnid) {

                columnclass.append("<option value="+ classDate[i].id +">"+ classDate[i].content +"</option>");
            }
        }
        columnclass.selectpicker('refresh');
    }
}