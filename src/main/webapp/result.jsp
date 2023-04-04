<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <style>
      * {
        margin: 0;
        padding: 0;
        box-sizing: border-box;
      }
      body {
        display: flex;
        height: 100vh;
        justify-content: center;
        align-items: center;
      }
      .container {
        display: flex;
        flex-direction: column;
        margin: 0 auto;
        justify-content: center;
        font-size: 30px;
        min-height: 600px;
      }
    </style>
  </head>
  <body>
    <div class="container">
      <table>
        <tr>
          <th>팀 이름</th>
          <th>도전 횟수</th>
        </tr>
        <c:forEach var="result" items="${resultList}">
          <tr>
            <td>${result.team}</td>
            <td>${result.count}</td>
          </tr>
        </c:forEach>
      </table>

    </div>
  </body>
</html>
