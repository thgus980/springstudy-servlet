<%@ page contentType="text/html;charset=UTF-8" language="java" %> <%-- 이 코드가 추가 되어야 jsp 파일이라는 것을 인식 --%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/jsp/members/save.jsp" method="post"> <%-- 전송 누르면 여기 접속 --%>
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>