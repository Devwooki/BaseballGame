package com.example.baseballgame.Dto;

public class LogDto {
    String gameCode;
    String team;
    String input;
    String result;

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
