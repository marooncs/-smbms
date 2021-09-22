var proCode = null;
var proName = null;
var proContact = null;
var proPhone = null;
var addBtn = null;
var backBtn = null;

$(function(){
    proCode = $("#proCode");
    proName = $("#proName");
    proContact = $("#proContact");
    proPhone = $("#proPhone");
    addBtn = $("#addBtn");
    backBtn = $("#backBtn");

    proCode.next().html("*");
    proName.next().html("*");
    proContact.next().html("*");
    proPhone.next().html("*");

    /*
     * 验证
     * 失焦\获焦
     * jquery的方法传递
     */
    proCode.on("blur",function(){
        $.get({
            url:path + "/jsp/provider.do?method=proCodeExist",
            data:{proCode:proCode.val()},
            dataType:"json",
            success:function (data) {
                if(data.proCode == "exist"){
                    validateTip(proCode.next(),{"color":"red"},imgNo + " The proCode is already exist! Please change another one",false);
                }else{
                    validateTip(proCode.next(),{"color":"green"}, imgYes + "The proCode is OK",true)
                }
            },
            error:function(data){//当访问时候，404，500 等非200的错误状态码
                validateTip(proCode.next(),{"color":"red"},"the web is not exist",false);
            }
        })
    }).on("focus",function(){
        validateTip(proCode.next(),{"color":"#666666"},"* insert provider code",false);
    }).focus();

    proName.on("focus",function(){
        validateTip(proName.next(),{"color":"#666666"},"* please insert provider name",false);
    }).on("blur",function(){
        if(proName.val() != null && proName.val() != ""){
            validateTip(proName.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(proName.next(),{"color":"red"},imgNo+" name is null, please insert again",false);
        }
    });

    proContact.on("focus",function(){
        validateTip(proContact.next(),{"color":"#666666"},"* please insert contact person",false);
    }).on("blur",function(){
        if(proContact.val() != null && proContact.val() != ""){
            validateTip(proContact.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(proContact.next(),{"color":"red"},imgNo+" contact person is null, re-set please",false);
        }

    });

    proPhone.bind("focus",function(){
        validateTip(proPhone.next(),{"color":"#666666"},"* insert phone number",false);
    }).bind("blur",function(){
        var patrn=/^(1[0-9][0-9])\d{8}$/;
        if(proPhone.val().match(patrn)){
            validateTip(proPhone.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(proPhone.next(),{"color":"red"},imgNo + " phone number pattern is wrong",false);
        }
    });

    addBtn.bind("click",function(){
        if(proCode.attr("validateStatus") != "true"){
            proCode.blur();
        }else if(proName.attr("validateStatus") != "true"){
            proName.blur();
        }else if(proContact.attr("validateStatus") != "true"){
            proContact.blur();
        }else if(proPhone.attr("validateStatus") != "true"){
            proPhone.blur();
        }else{
            if(confirm("Confirm to submit the data?")){
                $("#providerForm").submit();
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
});