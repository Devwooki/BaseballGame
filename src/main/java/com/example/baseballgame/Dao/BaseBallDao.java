package com.example.baseballgame.Dao;

import com.example.baseballgame.DBUtil.DBUtil;
import com.example.baseballgame.Dto.GameDto;
import com.example.baseballgame.Dto.LogDto;
import com.example.baseballgame.Dto.TeamDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    void insertAnswer(TeamDto team) throws SQLException{
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
            pstmt.setString(++idx, team.getGameCode());
            pstmt.setString(++idx, team.getTeam());
            pstmt.setString(++idx, team.getAnswer());
            pstmt.executeUpdate();
        }
    }


    //팀별 정답 입력
    //팀별 로그 출력
    List<LogDto> selectGameLog(String team) throws SQLException{

    }
    //팀별

}
