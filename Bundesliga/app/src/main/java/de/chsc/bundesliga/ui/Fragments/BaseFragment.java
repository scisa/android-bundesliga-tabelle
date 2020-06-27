package de.chsc.bundesliga.ui.Fragments;

import android.view.View;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import de.chsc.bundesliga.R;
import de.chsc.bundesliga.adapter.RecyclerTableViewAdapter;
import de.chsc.bundesliga.http.HttpRequest;
import de.chsc.bundesliga.http.HttpResponseListener;
import de.chsc.bundesliga.model.TableItem;

import java.util.Calendar;
import java.util.List;

public class BaseFragment extends Fragment {
    private RecyclerView recyclerViewTable;
    private RecyclerTableViewAdapter recyclerTableViewAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String blYear;

    protected void initRecyclerView(View view){
        this.recyclerViewTable = view.findViewById(R.id.rv_table);
        this.recyclerViewTable.setHasFixedSize(true);
        this.layoutManager = new LinearLayoutManager(requireContext());
        this.recyclerTableViewAdapter = new RecyclerTableViewAdapter(requireContext());
        this.recyclerViewTable.setLayoutManager(this.layoutManager);
        this.recyclerViewTable.setAdapter(this.recyclerTableViewAdapter);
    }

    protected void downloadData(String url, int league){
        HttpRequest request = new HttpRequest(requireContext());
        request.get(url, league);
        request.setListener(new HttpResponseListener() {
            @Override
            public void onResponse(List<TableItem> tableItems) {
                recyclerTableViewAdapter.submitList(tableItems);
            }

            @Override
            public void onError(String msg) {
                Toast.makeText(requireContext(), R.string.fetch_data_failed, Toast.LENGTH_LONG)
                        .show();
            }
        });
    }

    protected void setBlYear(){
//        Date date = new Date(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);

        if (month < 8){
            year = year - 1;
        }

        this.blYear = String.valueOf(year);
    }

    public String getBlYear() {
        return blYear;
    }
}
