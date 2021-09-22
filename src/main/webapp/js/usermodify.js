var userName = null;
var birthday = null;
var phone = null;
var userRole = null;
var saveBtn = null;
var backBtn = null;

$(function () {
    userName = $("#userName");
    birthday = $("#birthday");
    phone = $("#phone");
    userRole = $("#userRole");
    saveBtn = $("#save");
    backBtn = $("#back");

    userName.next().html("*");
    birthday.next().html("*");
    phone.next().html("*");
    userRole.next().html("*");

    $.ajax({
        type:'GET',
        url: path + "/jsp/user.do",
        data:{method:"getRoleList"},
        dataType:"json",
        success:function (data) {
            if(data != null){
                userRole.html("");
                var rid = $("#rid").val();
                var option = "<option value=\"0\">please choose</option>"
                for(var i = 0; i < data.length; i++){
                    if(rid != null && rid != undefined && rid == data[i].id){
                        option += "<option selected=\"selected\" value=\"" + data[i].id + "\">" + data[i].roleName + "</option>";
                    }else{
                        option += "<option value=\"" + data[i].id + "\">" + data[i].roleName + "</option>";
                    }
                }
                userRole.html(option);
            }
        },
        error:function (data) {
            validateTip(userRole.next(), {"color":"red"}, "error:get userRole", false);
        }
    });

    userName.bind("focus",function(){
        validateTip(userName.next(),{"color":"#666666"},"* username must be greater than 1 and less than 10 characters",false);
    }).bind("blur",function(){
        if(userName.val() != null && userName.val().length > 1
            && userName.val().length < 10){
            validateTip(userName.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(userName.next(),{"color":"red"},imgNo+"Not in compliance, Reset please",false);
        }
    });

    birthday.bind("focus",function(){
        validateTip(birthday.next(),{"color":"#666666"},"* choose your birthday",false);
    }).bind("blur",function(){
        if(birthday.val() != null && birthday.val() != ""){
            validateTip(birthday.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(birthday.next(),{"color":"red"},imgNo + " wrong date",false);
        }
    });

    phone.bind("focus",function(){
        validateTip(phone.next(),{"color":"#666666"},"* insert phone number",false);
    }).bind("blur",function(){
        var patrn=/^(1[0-9][0-9])\d{8}$/;
        if(phone.val().match(patrn)){
            validateTip(phone.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(phone.next(),{"color":"red"},imgNo + " phone number pattern is wrong",false);
        }
    });

    userRole.bind("focus",function(){
        validateTip(userRole.next(),{"color":"#666666"},"* choose userRole",false);
    }).bind("blur",function(){
        if(userRole.val() != null && userRole.val() > 0){
            validateTip(userRole.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(userRole.next(),{"color":"red"},imgNo + " choose userRole",false);
        }
    });

    saveBtn.bind("click",function() {
        userName.blur();
        phone.blur();
        birthday.blur();
        userRole.blur();
        if(userName.attr("validateStatus") == "true"
            && phone.attr("validateStatus") == "true"
            && birthday.attr("validateStatus") == "true"
            && userRole.attr("validateStatus") == "true"){
            if(confirm("Confirm to change the data?")){
                $("#userForm").submit();
            }
        }
    });

    backBtn.on("click",function(){
        if(referer != undefined
            && null != referer
            && "" != referer
            && "null" != referer
            && referer.length > 4){
            window.location.href = referer;
        }else{
            history.back(-1);
        }
    });

})