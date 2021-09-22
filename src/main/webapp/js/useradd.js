var userCode = null;
var userName = null;
var userPassword = null;
var ruserPassword = null;
var birthday = null;
var phone = null;
var userRole = null;
var addBtn = null;
var backBtn = null;

$(function () {
    userCode = $('#userCode');
    userName = $('#userName');
    userPassword = $('#userPassword');
    ruserPassword = $('#ruserPassword');
    birthday = $('#birthday');
    phone = $('#phone');
    userRole = $('#userRole');
    addBtn = $("#addBtn");
    backBtn = $("#backBtn");
    //初始化的时候，要把所有的提示信息变为：* 以提示必填项，更灵活，不要写在页面上
    userCode.next().html("*");
    userName.next().html("*");
    userPassword.next().html("*");
    ruserPassword.next().html("*");
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
                var option = "<option value=\"0\">please choose</option>"
                for(var i = 0; i < data.length; i++){
                    option += "<option value=\"" + data[i].id + "\">" + data[i].roleName + "</option>";
                }
                userRole.html(option);
            }
        },
        error:function (data) {
            validateTip(userRole.next(), {"color":"red"}, "error:get userRole", false);
        }
    });

    userCode.bind("blur", function () {
        // 后台验证 userCode 是否已经存在
        $.get({
            url:path + "/jsp/user.do?method=userCodeExist",
            data: {userCode:userCode.val()},
            dataType: "json",
            success:function (data) {
                if(data.userCode == "exist"){
                    validateTip(userCode.next(),{"color":"red"},  "The userCode is already exist! Please change another one",false)
                }else{
                    validateTip(userCode.next(),{"color":"green"}, imgYes + "The userCode is OK",true)
                }
            },
            error:function(data){//当访问时候，404，500 等非200的错误状态码
                validateTip(userCode.next(),{"color":"red"},"the web is not exist",false);
            }
        })
    }).bind("focus",function () {
        validateTip(userCode.next(),{"color":"#666666"}, "* userCode is the username to log in to website")
    }).focus();

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

    userPassword.bind("focus",function(){
        validateTip(userPassword.next(),{"color":"#666666"},"* length:>6 & <20",false);
    }).bind("blur",function(){
        if(userPassword.val() != null && userPassword.val().length > 6
            && userPassword.val().length < 20 ){
            validateTip(userPassword.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(userPassword.next(),{"color":"red"},imgNo + "Not in compliance, Re-set please",false);
        }
    });

    ruserPassword.bind("focus",function(){
        validateTip(ruserPassword.next(),{"color":"#666666"},"* validate the password",false);
    }).bind("blur",function(){
        if(ruserPassword.val() != null && ruserPassword.val().length > 6
            && ruserPassword.val().length < 20 && userPassword.val() == ruserPassword.val()){
            validateTip(ruserPassword.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(ruserPassword.next(),{"color":"red"},imgNo + "Two password entries are inconsistent, Re-set please",false);
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

    addBtn.bind("click",function(){
        if(userCode.attr("validateStatus") != "true"){
            userCode.blur();
        }else if(userName.attr("validateStatus") != "true"){
            userName.blur();
        }else if(userPassword.attr("validateStatus") != "true"){
            userPassword.blur();
        }else if(ruserPassword.attr("validateStatus") != "true"){
            ruserPassword.blur();
        }else if(birthday.attr("validateStatus") != "true"){
            birthday.blur();
        }else if(phone.attr("validateStatus") != "true"){
            phone.blur();
        }else if(userRole.attr("validateStatus") != "true"){
            userRole.blur();
        }else{
            if(confirm("Confirm to submit data?")){
                $("#userForm").submit();
            }
        }

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
    });

});

