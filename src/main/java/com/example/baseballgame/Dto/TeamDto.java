package com.example.baseballgame.Dto;

public class TeamDto {
    int gameCode;
    String team;
    String answer;

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    int count;

    public TeamDto() {
    }

    public int getGameCode() {
        return gameCode;
    }

    public void setGameCode(int gameCode) {
        this.gameCode = gameCode;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

}
