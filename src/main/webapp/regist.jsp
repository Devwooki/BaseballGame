<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
      .container > div {
        width: 830px;
        border: 1px solid white;
        background: rgb(105, 170, 235);
        color: white;
      }
      .number-area {
        display: flex;
      }
      .number-view {
        padding: 20px;
      }
      .number-view > .number {
        display: inline-block;
        width: 100px;
        height: 46px;
        text-align: center;
        font-weight: bold;
        border: 1px solid;
      }
      .number-input {
        padding: 17px;
      }
      .number-input > input {
        display: inline-block;
        width: 100px;
        height: 40px;
        text-align: center;
        font-weight: bold;
        border: 1px solid;
        font-size: 2rem;
      }

      .number-input > button {
        width: 100px;
        height: 40px;
        text-align: center;
        font-weight: bold;
        border: 1px solid;
        font-size: 1.4rem;
      }
      .number-input > button:hover {
        background: rgb(105, 99, 90);
        color: white;
        border: 0;
        cursor: pointer;
      }
      .history {
        display: flex;
        flex-direction: column;
        height: 500px;
        justify-content: flex-start;
        align-items: center;
      }
      .history-item {
        margin-top: 10px;
        display: flex;
        justify-content: space-around;
      }

      .history-item > .number,
      .history-item > .result {
        background: white;
        color: rgb(105, 99, 90);
        margin: 5px;
        height: 50px;
      }

      .history-item > .number {
        width: 100px;
        text-align: center;
      }
      .history-item > .result {
        background: white;
        color: rgb(105, 99, 90);
        flex-grow: 1;
        padding-left: 20px;
        min-width: 200px;
      }
      .popup {
        position: fixed;
        left: 50%;
        top: 50%;
        transform: translate(-50%, -50%);
        background: #345;
        width: 300px;
        height: 300px;
        color: white;
        font-size: 2rem;
        padding: 1rem;
        border-radius: 20px;
        display: none;
      }
      .active{
        display : block;
      }
    </style>
  </head>
  <body>
  <div>
    <h3>
      ${teamInfo.team}
    </h3>
  </div>
    <div class="container">
      <div class="number-area">
        <div class="number-view">
          <span class="number">?</span>
          <span class="number">?</span>
          <span class="number">?</span>
        </div>
        <div class="number-input">
          <input id="num1" type="number" min="0" max="9" value="0" />
          <input id="num2" type="number" min="0" max="9" value="0" />
          <input id="num3" type="number" min="0" max="9" value="0" />
          <button id="submit" >확인</button>
        </div>
      </div>
      <div class="history">

      </div>
    </div>
    <div class="popup">
      <p>축하합니다.</p>
      5회만에 성공하였습니다.
      <p><a href="/baseball?action=result&gameCode=${teamInfo.gameCode}">결과보기</a></p>
    </div>
  <script>
    let btn = document.getElementById("submit");
    let num1 = document.getElementById("num1");
    let num2 = document.getElementById("num2");
    let num3 = document.getElementById("num3");
    let history = document.querySelector(".history");

    btn.onclick= ()=>{
      let param = "&team=${teamInfo.team}" + "&gameCode=${teamInfo.gameCode}"  + "&num1=" + num1.value + "&num2=" + num2.value + "&num3=" + num3.value
      fetch("/baseball?action=regist" + param).then(response => response.text()).then(data => {
        let result = document.createElement("div");
        result.className="history-item";
        result.innerHTML=`
          <span className="number">\${num1.value}</span>
          <span className="number">\${num2.value}</span>
          <span className="number">\${num3.value}</span>
          <span className="result">\${data}</span>`
        history.appendChild(result);
        console.dir(data)
        if(data.trim() == "3스트라이크"){
          console.log("체크")
          document.querySelector(".popup").className="popup active"
        }
      })
    }
  </script>
  </body>
</html>
