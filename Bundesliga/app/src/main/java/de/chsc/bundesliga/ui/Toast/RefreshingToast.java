package de.chsc.bundesliga.ui.Toast;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import de.chsc.bundesliga.R;

public class RefreshingToast {
    private Context context;
    private Toast toast;

    public RefreshingToast(Context context) {
        this.context = context;
        this.toast = new Toast(context);
        this.inflateToast();
        this.createToast();
    }

    private void inflateToast(){
        LayoutInflater inflater = (LayoutInflater) this.context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.toast_refreshing,
                (ViewGroup) ((Activity) this.context).findViewById(R.id.custom_toast_container));

        TextView message = layout.findViewById(R.id.tv_toast_refreshing);
        message.setText(R.string.refreshing_toast);
        this.toast.setView(layout);
    }

    private void createToast(){
        this.toast.setGravity(Gravity.BOTTOM, 0, 0);
        this.toast.setDuration(Toast.LENGTH_SHORT);
    }


    public void show(){
        this.toast.show();
    }
}
