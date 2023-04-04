package com.example.baseballgame.Dto;

public class LogDto {
    public int getGameCode() {
        return gameCode;
    }

    public void setGameCode(int gameCode) {
        this.gameCode = gameCode;
    }

    int gameCode;
    String team;
    String input;
    String result;

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public LogDto() {
    }
}
