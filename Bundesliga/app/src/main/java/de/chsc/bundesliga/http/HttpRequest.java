package de.chsc.bundesliga.http;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import de.chsc.bundesliga.builder.TableItemBuilder;
import de.chsc.bundesliga.model.TableItem;
import de.chsc.bundesliga.util.Constants;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HttpRequest {
    private HttpResponseListener listener;
    private Context context;

    public HttpRequest(Context context) {
        this.context = context;
    }

    public void get(String url, final int league){
        final List<TableItem> tableItems = new ArrayList<>();

        StringRequest getRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObjectTableItem = jsonArray.getJSONObject(i);
                        TableItem tableItem =
                                buildTableItemFromJSONObject(jsonObjectTableItem, i+1, league);
                        tableItems.add(tableItem);
                    }
                    listener.onResponse(tableItems);
                } catch (JSONException e) {

                }
            }},
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        listener.onError(error.getMessage());
                    }
                });
        Volley.newRequestQueue(this.context).add(getRequest);
    }

    private TableItem buildTableItemFromJSONObject(JSONObject jsonObject, int place, int league) throws JSONException {
        TableItemBuilder tableItemBuilder = new TableItemBuilder();
        tableItemBuilder.setLeague(league);
        tableItemBuilder.setPlace(place);
        String teamName = jsonObject.getString(Constants.TEAM_NAME);
        tableItemBuilder.setTeamName(teamName);
        int games = jsonObject.getInt(Constants.MATCHES_NUMBER);
        tableItemBuilder.setGames(games);
        int goals = jsonObject.getInt(Constants.GOALS);
        tableItemBuilder.setGoals(goals);
        int opponentGoals = jsonObject.getInt(Constants.OPPONENT_GOALS);
        tableItemBuilder.setOpponentGoals(opponentGoals);
        int goalDiff = jsonObject.getInt(Constants.GOAL_DIFF);
        tableItemBuilder.setGoalDiff(goalDiff);
        int points = jsonObject.getInt(Constants.POINTS);
        tableItemBuilder.setPoints(points);

        return tableItemBuilder.build();
    }

    public void setListener(HttpResponseListener listener) {
        this.listener = listener;
    }
}
