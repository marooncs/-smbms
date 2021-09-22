<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>

<%@include file="common/header.jsp"%>

<div class="right">
    <div class="location">
        <strong>你现在所在的位置是:</strong>
        <span>订单管理页面 >> 添加订单页面</span>
    </div>
    <div class="providerAdd">
        <form id="billForm" name="billForm" method="post" action="${pageContext.request.contextPath }/jsp/bill.do">
            <input type="hidden" name="method" value="addBill">
            <div class="">
                <label for="billCode">订单编码：</label>
                <input type="text" name="billCode" class="text" id="billCode" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="productName">商品名称：</label>
                <input type="text" name="productName" id="productName" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="productDesc">商品描述：</label>
                <input type="text" name="productDesc" id="productDesc" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="productUnit">商品单位：</label>
                <input type="text" name="productUnit" id="productUnit" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="productCount">商品数量：</label>
                <input type="text" name="productCount" id="productCount" value="">
                <font color="red"></font>
            </div>
            <div>
                <label for="totalPrice">总金额：</label>
                <input type="text" name="totalPrice" id="totalPrice" value="">
                <font color="red"></font>
            </div>
            <div>
                <label >供应商：</label>
                <select name="providerId" id="providerId">
                </select>
                <font color="red"></font>
            </div>
            <div>
                <label >是否付款：</label>
                <input type="radio" name="isPayment" value="1" checked="checked">未付款
                <input type="radio" name="isPayment" value="2" >已付款
            </div>
            <div class="providerAddBtn">
                <input type="button" id="addBtn" name="addBtn"  value="保存">
                <input type="button" id="backBtn" name="backBtn" value="返回" >
            </div>
        </form>
    </div>

</div>
</section>

<%@include file="common/footer.jsp"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/billadd.js"></script>