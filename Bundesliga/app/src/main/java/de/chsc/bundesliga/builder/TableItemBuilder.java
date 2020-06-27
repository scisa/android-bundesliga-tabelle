package de.chsc.bundesliga.builder;

import de.chsc.bundesliga.model.TableItem;

public class TableItemBuilder {
    private TableItem tableItem;
    private int league;
    private int place;
    private String teamName;
    private int games;
    private int goals;
    private int opponentGoals;
    private int goalDiff;
    private int points;

    public TableItemBuilder() {
        this.tableItem = new TableItem();
    }

    public TableItem build(){
        this.tableItem.setLeague(this.league);
        this.tableItem.setPlace(this.place);
        this.tableItem.setTeamName(this.teamName);
        this.tableItem.setGames(this.games);
        this.tableItem.setGoals(this.goals);
        this.tableItem.setOpponentGoals(this.opponentGoals);
        this.tableItem.setGoalDiff(this.goalDiff);
        this.tableItem.setPoints(this.points);

        return this.tableItem;
    }

    public void setLeague(int league) {
        this.league = league;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setGames(int games) {
        this.games = games;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public void setOpponentGoals(int opponentGoals) {
        this.opponentGoals = opponentGoals;
    }

    public void setGoalDiff(int goalDiff) {
        this.goalDiff = goalDiff;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
