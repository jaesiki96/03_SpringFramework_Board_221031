<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-01
  Time: 오후 3:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>boardDetail.jsp</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <style>
        #detail {
            width: 800px;
            margin-top: 50px;
        }
    </style>
</head>
<body>
<jsp:include page="../layout/header.jsp" flush="false"></jsp:include>
<div class="container" id="detail">
    <table class="table table-hover">
        <tr>
            <th>id</th>
            <td>${board.id}</td>
        </tr>
        <tr>
            <th>writer</th>
            <td>${board.boardWriter}</td>
        </tr>
        <tr>
            <th>date</th>
            <td>${board.boardCreatedDate}</td>
        </tr>
        <tr>
            <th>hits</th>
            <td>${board.boardHits}</td>
        </tr>
        <tr>
            <th>title</th>
            <td>${board.boardTitle}</td>
        </tr>
        <tr>
            <th>contents</th>
            <td>${board.boardContents}</td>
        </tr>
    </table>
    <button class="btn btn-primary" onclick="listFn()">목록</button>
    <button class="btn btn-warning" onclick="updateFn()">수정</button>
    <button class="btn btn-danger" onclick="deleteFn()">삭제</button>
</div>
</body>
<script>
    const listFn = () => {
        location.href = "/board/";
    }
    const updateFn = () => {
        const id = '${board.id}';
        location.href = "/board/update?id=" + id;
    }
    const deleteFn = () => {
        const id = '${board.id}';
        location.href = "/board/deleteCheck?id=" + id;
    }
</script>
</html>
