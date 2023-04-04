package com.example.baseballgame.Dto;

public class TeamDto {
    String gameCode;
    String team;
    String answer;
    String count;

    public TeamDto() {
    }

    public String getGameCode() {
        return gameCode;
    }

    public void setGameCode(String gameCode) {
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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }
}
