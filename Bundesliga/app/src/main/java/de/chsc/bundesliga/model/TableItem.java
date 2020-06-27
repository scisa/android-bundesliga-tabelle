package de.chsc.bundesliga.model;

public class TableItem {
    private int league;
    private int place;
    private String teamName;
    private int games;
    private int goals;
    private int opponentGoals;
    private int goalDiff;
    private int points;

    public TableItem(int place, String teamName, int games, int goals, int opponentGoals, int goalDiff, int points, int league) {
        this.league = league;
        this.place = place;
        this.teamName = teamName;
        this.games = games;
        this.goals = goals;
        this.opponentGoals = opponentGoals;
        this.goalDiff = goalDiff;
        this.points = points;
    }

    public TableItem() {
    }

    public int getLeague() {
        return league;
    }

    public void setLeague(int league) {
        this.league = league;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getGames() {
        return games;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getOpponentGoals() {
        return opponentGoals;
    }

    public void setOpponentGoals(int opponentGoals) {
        this.opponentGoals = opponentGoals;
    }

    public String getGoalsForPrinting(){
        return this.getGoals() + ":" + this.getOpponentGoals();
    }

    public int getGoalDiff() {
        return goalDiff;
    }

    public void setGoalDiff(int goalDiff) {
        this.goalDiff = goalDiff;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "TableItem{" +
                "teamName='" + teamName + '\'' +
                '}';
    }
}
