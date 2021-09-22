var billCode = null;
var productName = null;
var productUnit = null;
var productCount = null;
var totalPrice = null;
var providerId = null;
var addBtn = null;
var backBtn = null;

function priceReg (value){
    value = value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
    value = value.replace(/^\./g,"");  //验证第一个字符是数字而不是.
    value = value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的.
    value = value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");//去掉特殊符号￥
    if(value.indexOf(".")>0){
        value = value.substring(0,value.indexOf(".")+3);
    }
    return value;
}

$(function () {
    billCode = $("#billCode");
    productName = $("#productName");
    productUnit = $("#productUnit");
    productCount = $("#productCount");
    totalPrice = $("#totalPrice");
    providerId = $("#providerId");
    addBtn = $("#addBtn");
    backBtn = $("#backBtn");

    billCode.next().html("*");
    productName.next().html("*");
    productUnit.next().html("*");
    productCount.next().html("*");
    totalPrice.next().html("*");
    providerId.next().html("*");

    $.get({
        url:path + "/jsp/provider.do?method=getProviderList",
        dataType:"json",
        success:function (data) {
            if(data != null){
                providerId.html("");
                var option = "<option value=\"0\">please choose</option>"
                for(var i = 0; i < data.length; i++){
                    option += "<option value=\"" + data[i].id+"\">" + data[i].proName + "</option>"
                }
                providerId.html(option);
            }
        },
        error:function (data) {
            validateTip(providerId.next(), {"color":"red"}, "error:get providerList", false);
        }
    })


    billCode.on("blur",function(){
        if(billCode.val() != null && billCode.val() != ""){
            validateTip(billCode.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(billCode.next(),{"color":"red"},imgNo+" can not be null, re-set please",false);
        }
    }).on("focus",function(){
        //显示友情提示
        validateTip(billCode.next(),{"color":"#666666"},"* insert billCode",false);
    }).focus();

    productName.on("focus",function(){
        validateTip(productName.next(),{"color":"#666666"},"* insert productName",false);
    }).on("blur",function(){
        if(productName.val() != null && productName.val() != ""){
            validateTip(productName.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(productName.next(),{"color":"red"},imgNo+" can not be null, re-set please",false);
        }
    });

    productUnit.on("focus",function(){
        validateTip(productUnit.next(),{"color":"#666666"},"* insert productUnit",false);
    }).on("blur",function(){
        if(productUnit.val() != null && productUnit.val() != ""){
            validateTip(productUnit.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(productUnit.next(),{"color":"red"},imgNo+" can not be null, re-set please",false);
        }
    });

    providerId.on("focus",function(){
        validateTip(providerId.next(),{"color":"#666666"},"* please choose the provider",false);
    }).on("blur",function(){
        if(providerId.val() != null && providerId.val() != "" && providerId.val() != 0){
            validateTip(providerId.next(),{"color":"green"},imgYes,true);
        }else{
            validateTip(providerId.next(),{"color":"red"},imgNo+" can not be null, please choose the provider",false);
        }
    });

    productCount.on("focus",function(){
        validateTip(productCount.next(),{"color":"#666666"},"* >0, 2 digits after the decimal point",false);
    }).on("keyup",function(){
        this.value = priceReg(this.value);
    }).on("blur",function(){
        this.value = priceReg(this.value);
    });

    totalPrice.on("focus",function(){
        validateTip(totalPrice.next(),{"color":"#666666"},"* >0, 2 digits after the decimal point",false);
    }).on("keyup",function(){
        this.value = priceReg(this.value);
    }).on("blur",function(){
        this.value = priceReg(this.value);
    });

    addBtn.on("click",function(){
        if(billCode.attr("validateStatus") != "true"){
            billCode.blur();
        }else if(productName.attr("validateStatus") != "true"){
            productName.blur();
        }else if(productUnit.attr("validateStatus") != "true"){
            productUnit.blur();
        }else if(providerId.attr("validateStatus") != "true"){
            providerId.blur();
        }else{
            if(confirm("Confirm to submit the data?")){
                $("#billForm").submit();
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