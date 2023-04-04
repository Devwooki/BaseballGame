<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<form method="GET" action="/baseball">
    <input type="hidden" name="action" value="join"/>
  <input type="text" name="team" placeholder="팀 이름 입력" />
  <button>참가</button>
</form>

</body>
</html>