var path = $("#path").val();
var imgYes = "<img width='15px' src='"+ path +"/images/y.png' />";
var imgNo = "<img width='15px' src='"+ path +"/images/n.png' />";
var referer = $("#referer").val();

/**
 * 提示信息显示
 * @param element :显示提示信息的元素（font）
 * @param css :提示样式
 * @param tipString :提示信息
 * @param status :true/false --验证是否通过
 */

function validateTip(element, css, tipString, status) {
    element.css(css);
    element.html(tipString);
    element.prev().attr("validateStatus", status);
}