<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2022-11-01
  Time: 오후 3:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>boardDetail.jsp</title>
    <link rel="stylesheet" href="/resources/css/bootstrap.css">
    <script src="/resources/js/jquery.js"></script>
    <style>
        #detail {
            width: 800px;
            margin-top: 50px;
        }
        #comment-write {
            width: 600px;
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
        <%--  ↓ 아래 c:if 파일이 있을 때는 파일을 미리보기로 보여주고, 없을 때는 안보여주는...  --%>
        <c:if test="${board.storedFileName != null}">
            <tr>
                <th>file</th>
                <td>
                        <%--  servlet-context.xml 에서 "2022-11-02 작업 한 내용", 글 목록에서 첨부 파일 미리보기  --%>
                    <img src="${pageContext.request.contextPath}/upload/${board.storedFileName}"
                         alt="" width="100" height="100">
                </td>
            </tr>
        </c:if>
    </table>
    <button class="btn btn-primary" onclick="listFn()">목록</button>
    <button class="btn btn-warning" onclick="updateFn()">수정</button>
    <button class="btn btn-danger" onclick="deleteFn()">삭제</button>
</div>
<div class="container mt-5">
    <div id="comment-write" class="input-group mb-3">
        <div class="form-floating">
            <input type="text" id="commentWriter" class="form-control" placeholder="작성자">
            <label for="commentWriter">작성자</label>
        </div>
        <div class="form-floating">
            <input type="text" id="commentContents" class="form-control" placeholder="내용">
            <label for="commentContents">내용</label>
        </div>
        <button id="comment-write-btn" class="btn btn-secondary" onclick="commentWrite()">댓글작성</button>
    </div>
</div>
</body>
<script>
    <%--  목록을 클릭했을 때 마지막으로 봤던 페이지로 돌아가기  --%>
    const listFn = () => {
        const page = '${page}';
        location.href = "/board/paging?page=" + page;
    }
    const updateFn = () => {
        const id = '${board.id}';
        location.href = "/board/update?id=" + id;
    }
    const deleteFn = () => {
        const id = '${board.id}';
        location.href = "/board/deleteCheck?id=" + id;
    }
    const commentWrite = () => {
        const writer = document.getElementById("commentWriter").value;
        const contents = document.getElementById("commentContents").value;
        const board = '${board.id}';
        $.ajax({
            type: "post",
            url: "/comment/save",
            data: {
                commentWriter: writer,
                commentContents: contents,
                boardId: board
            },
            dataType: "json", // 리턴 타입
            success: function (commentList) {
                console.log(commentList);
            },
            error: function () {
                console.log("실패");
            }
        });
    }
</script>
</html>
