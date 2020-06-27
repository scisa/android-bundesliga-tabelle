package de.chsc.bundesliga.ui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import de.chsc.bundesliga.R;
import de.chsc.bundesliga.util.Constants;

public class Bl1Fragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.table_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.initRecyclerView(view);
        this.setBlYear();
        this.downloadData(Constants.TABLE_BL1_BASE_URL + this.getBlYear(), 1);
    }

}
