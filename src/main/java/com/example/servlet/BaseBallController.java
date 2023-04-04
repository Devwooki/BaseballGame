package com.example.servlet;

import com.example.baseballgame.Dao.BaseBallDao;
import com.example.baseballgame.Dto.LogDto;
import com.example.baseballgame.Dto.TeamDto;
import com.example.baseballgame.validate.Game;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/baseball")
public class BaseBallController extends HttpServlet {
    private BaseBallDao dao = BaseBallDao.getInstance();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if(action == null || action.equals("")) action = "main";
        System.out.println(action);
        try{
            switch(action){
                case "regist" :
                    regist(req, resp);
                    return;
                case "join" :
                    join(req, resp);
                    break;
                case "result" :
                    System.out.println("스위치안");
                    result(req,resp);
                    break;
            }
        }catch(Exception e){
            e.printStackTrace();
        }


    }

    private void result(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        
        System.out.println("왔냐?");
        int gameCode = Integer.parseInt(req.getParameter("gameCode"));
        String teamName = req.getParameter("team");
        TeamDto team = new TeamDto();
        team.setGameCode(gameCode);
        team.setTeam(req.getParameter("team"));

        req.setAttribute("resultList", dao.selectGameResult(team));
        req.getRequestDispatcher("/result.jsp").forward(req,resp);
    }

    private void join(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException, ServletException {
        TeamDto team = new TeamDto();
        team.setGameCode(1);
        team.setTeam(req.getParameter("team"));
        team.setAnswer( Game.makeRandomAnwer());
        dao.insertTeam(team);

        req.setAttribute("teamInfo", team);
        req.getRequestDispatcher("/regist.jsp").forward(req,resp);

    }

    private void regist(HttpServletRequest req, HttpServletResponse resp) throws IOException, SQLException {
        String num1 = req.getParameter("num1");
        String num2 = req.getParameter("num2");
        String num3 = req.getParameter("num3");
        int gameCode = Integer.parseInt(req.getParameter("gameCode"));
        String teamName = req.getParameter("team");
        System.out.println(num1 + ", " + num2 + ", " + num3);

        TeamDto team = new TeamDto();
        team.setGameCode(gameCode);
        team.setTeam(teamName);
        String answer = dao.selectAnswer(team); //DB에서 가져올 값
        dao.updateCount(team);
        String result = Game.startGame(num1+num2+num3, answer);

        LogDto log = new LogDto();
        log.setGameCode(gameCode);
        log.setTeam(teamName);
        log.setInput(num1 + ", " + num2 + ", " + num3);
        log.setResult(result);
        dao.insertLog(log);

        resp.setCharacterEncoding("utf-8");
        resp.getWriter().println(result);

    }

    @Override
    public void init() throws ServletException {
        super.init();
    }


}
