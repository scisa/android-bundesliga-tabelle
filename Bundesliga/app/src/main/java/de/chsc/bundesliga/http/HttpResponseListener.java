package de.chsc.bundesliga.http;

import de.chsc.bundesliga.model.TableItem;

import java.util.List;

public interface HttpResponseListener {
    void onResponse(List<TableItem> tableItems);
    void onError(String msg);
}
