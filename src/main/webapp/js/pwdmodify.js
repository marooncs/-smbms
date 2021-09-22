var oldPassword= null;
var newPassword = null;
var renewPassword = null;
var saveBtn = null;


$(function () {
    oldPassword = $('#oldpassword');
    newPassword = $('#newpassword');
    renewPassword = $('#renewpassword');
    saveBtn = $('#save')

    oldPassword.next().html("*");
    newPassword.next().html("*");
    renewPassword.next().html("*");

    oldPassword.bind("blur",function () {
        $.get({
            url:path + "/jsp/user.do",
            data:{method:"pwdmodify",oldPassword:oldPassword.val()},
            dataType:"json",
            success:function (data) {
                if(data.result == "true"){
                    validateTip(oldPassword.next(),{"color":"green"}, imgYes,true);
                }else if(data.result == "false"){
                    validateTip(oldPassword.next(),{"color":"green"}, imgNo + " Wrong oldPassword",false);
                }else if(data.result == "oldPasswordError"){
                    validateTip(oldPassword.next(),{"color":"green"}, imgNo + " Please input oldPassword",false);
                }else if(data.result == "sessionError"){
                    validateTip(oldPassword.next(),{"color":"green"}, imgNo + " User session expired, please login in",false);
                }
            },
            error:function(data){
                //请求出错
                validateTip(oldPassword.next(),{"color":"red"},imgNo + " bad request",false);
            }
        });
    }).bind("focus",function () {
        validateTip(oldPassword.next(),{"color":"red"},"* please input oldPassword",false);
    })

    newPassword.bind("blur",function () {
        if(newPassword.val() != null && newPassword.val().length > 6
            && newPassword.val().length < 20 && oldPassword.val() != null && newPassword.val() != oldPassword.val()){
            validateTip(newPassword.next(),{"color":"green"},imgYes,true);
        }else if(oldPassword.val() == ""){
            validateTip(newPassword.next(),{"color":"red"},imgNo + " Input oldPassword first,please",false);
        }else if(newPassword.val() == oldPassword.val() ){
            validateTip(newPassword.next(),{"color":"red"},imgNo + " NewPassWord cannot same to oldPassword",false);
        }else{
            validateTip(newPassword.next(),{"color":"red"},imgNo + " Not in compliance, Re-set please",false);
        }
    }).bind("focus",function () {
        validateTip(newPassword.next(),{"color":"#666666"},"* length:>6 & <20",false);
    })

    renewPassword.bind("blur",function () {
        if(renewPassword.val() != null && renewPassword.val().length > 6
            && renewPassword.val().length < 20 && renewPassword.val() == newPassword.val()){
            validateTip(renewPassword.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(renewPassword.next(),{"color":"red"},imgNo + " Two password entries are inconsistent, Re-set please",false);
        }
    }).bind("focus",function () {
        validateTip(renewPassword.next(),{"color":"#666666"},"* validate the password",false);
    })

    saveBtn.on("click",function () {
        oldPassword.blur();
        newPassword.blur();
        renewPassword.blur();
        if(oldPassword.attr("validateStatus") == "true"
            &&newPassword.attr("validateStatus") == "true"
            && renewPassword.attr("validateStatus") == "true"){
            if(confirm("Confirm to change your password?")){
                $("#userForm").submit();
            }
        }
    })

})