package com.example.baseballgame.Dao;

import com.example.baseballgame.DBUtil.DBUtil;
import com.example.baseballgame.Dto.GameDto;
import com.example.baseballgame.Dto.LogDto;
import com.example.baseballgame.Dto.TeamDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BaseBallDao {
    private DBUtil dbUtil;
    private static BaseBallDao instance = new BaseBallDao();
    private BaseBallDao(){
        dbUtil = DBUtil.getInstance();
    }
    public static BaseBallDao getInstance(){
        return instance;
    }

    //관리자-게임생성
    void insertGame(GameDto game) throws SQLException {
        try(
                Connection con = dbUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(
                        " insert into baseballgame " +
                            " (gameCode, startTime, endTime) " +
                            " values " +
                            " (?, ?, ? ) "
                );
                ){
            int idx = 0;
            pstmt.setString(++idx, game.getGameName());
            pstmt.setDate(++idx, game.getStartTime());
            pstmt.setDate(++idx, game.getEndTime());
            pstmt.executeUpdate();
        }
    }

    //사용자-게임회차 선택 :
    GameDto selectGame(String gameCode) throws SQLException {
        GameDto game = new GameDto();
        try(
                Connection con = dbUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(
                        " select * from baseballgame where gameCode = ? "
                );
        ){
            pstmt.setString(1, gameCode);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                game.setGameName(rs.getString("gameCode"));
                game.setStartTime(rs.getDate("startTime"));
                game.setEndTime(rs.getDate("endTime"));
            }
            return game;
        }
    }

    //게임회차 및 팀별 랜덤 값 부여
    public void insertTeam(TeamDto team) throws SQLException{
        try(
                Connection con = dbUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(
                        " insert into baseballgame_team " +
                                " (gameCode, team, answer) " +
                                " values " +
                                " (?, ?, ?) "
                );
        ){
            int idx = 0;
            pstmt.setInt(++idx, team.getGameCode());
            pstmt.setString(++idx, team.getTeam());
            pstmt.setString(++idx, team.getAnswer());
            pstmt.executeUpdate();
        }
    }

    public String selectAnswer(TeamDto team) throws SQLException {
        try(
                Connection con = dbUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(
                        " select answer from baseballgame_team where gameCode = ? and team = ? "
                );
        ){
            pstmt.setInt(1, team.getGameCode());
            pstmt.setString(2, team.getTeam());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
               return rs.getString("answer");
            }
            return null;
        }
    }

    //사용자가 input던질 때 마다 count증가
    public void updateCount(TeamDto team) throws SQLException{
        try(
                Connection con = dbUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(
                        " update baseballgame_team set count = count+1 where gameCode = ? and team = ? "
                );
        ){
            pstmt.setInt(1, team.getGameCode());
            pstmt.setString(2, team.getTeam());
            pstmt.executeUpdate();
        }
    }

//    팀별 정답 입력
    public void insertLog(LogDto log) throws SQLException{
        try(
                Connection con = dbUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(
                        "insert into baseballgame_log (gameCode, team, input, result) " +
                            " values " +
                            " (?, ?, ?, ? )"
                );
        ){
            int idx = 0;
            pstmt.setInt(++idx, log.getGameCode());
            pstmt.setString(++idx, log.getTeam());
            pstmt.setString(++idx, log.getInput());
            pstmt.setString(++idx, log.getResult());
            pstmt.executeUpdate();
        }
    }

    public List<TeamDto> selectGameResult(TeamDto team) throws SQLException{
        List<TeamDto> list = new ArrayList<>();

        try(
                Connection con = dbUtil.getConnection();
                PreparedStatement pstmt = con.prepareStatement(
//                        " select * from baseballgame_log where team = ? and gameCode = ?  " +
//                            " order by idx asc "
                        " select * from baseballgame_team where gameCode = ?  "
                );
        ){
            pstmt.setInt(1, team.getGameCode());
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                TeamDto result = new TeamDto();
                result.setGameCode(rs.getInt("gameCode"));
                result.setTeam(rs.getString("team"));
                result.setCount(rs.getInt("count"));
                list.add(result);
            }
            System.out.println(list.size());
            return list;
        }
    }
    //팀별
}

