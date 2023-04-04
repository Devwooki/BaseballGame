package com.example.baseballgame.Dto;

import java.sql.Date;

public class GameDto {
    String gameName;
//    String startTime;
//    String endTime;
//    public String getStartTime() {
//        return startTime;
//    }
//
//    public void setStartTime(String startTime) {
//        this.startTime = startTime;
//    }
//
//    public String getEndTime() {
//        return endTime;
//    }
//
//    public void setEndTime(String endTime) {
//        this.endTime = endTime;
//    }
//public GameDto(String gameName, String startTime, String endTime) {
//    this.gameName = gameName;
//    this.startTime = startTime;
//    this.endTime = endTime;
//}
    Date startTime;
    Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public GameDto() {
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }



    @Override
    public String toString() {
        return "GameDto{" +
                "gameName='" + gameName + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }


}
