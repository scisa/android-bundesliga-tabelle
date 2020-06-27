package de.chsc.bundesliga.test;

import de.chsc.bundesliga.model.TableItem;

import java.util.ArrayList;
import java.util.List;

public class Testing {

    public static List<TableItem> getTestData(){
        List<TableItem> tableItems = new ArrayList<>();
        TableItem tableItem = new TableItem();
        tableItem.setPlace(1);
        tableItem.setTeamName("FC Bayern");
        tableItem.setGames(28);
        tableItem.setGoals(81);
        tableItem.setOpponentGoals(27);
        tableItem.setGoalDiff(81-27);
        tableItem.setPoints(64);
        tableItems.add(tableItem);

        TableItem tableItem1 = new TableItem();
        tableItem1.setPlace(2);
        tableItem1.setTeamName("Borussia Dortmund");
        tableItem1.setGames(28);
        tableItem1.setGoals(65);
        tableItem1.setOpponentGoals(34);
        tableItem1.setGoalDiff(65-34);
        tableItem1.setPoints(57);
        tableItems.add(tableItem1);

        return tableItems;
    }
}
