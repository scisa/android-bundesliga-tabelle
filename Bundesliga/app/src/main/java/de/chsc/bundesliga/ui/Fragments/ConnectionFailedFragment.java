package de.chsc.bundesliga.ui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import de.chsc.bundesliga.R;

public class ConnectionFailedFragment extends Fragment {
    ImageButton imageButton;
    private OnRefreshFailureClickedListener listener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.connection_failed_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.imageButton = view.findViewById(R.id.image_button_refresh_failure_fragment);
        this.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRefreshClicked();
            }
        });
    }

    public interface OnRefreshFailureClickedListener{
        void onRefreshClicked();
    }

    public void setOnRefreshFailureClickedListener(OnRefreshFailureClickedListener listener){
        this.listener = listener;
    }
}
