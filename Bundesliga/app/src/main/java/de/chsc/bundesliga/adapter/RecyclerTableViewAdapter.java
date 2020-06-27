package de.chsc.bundesliga.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import de.chsc.bundesliga.R;
import de.chsc.bundesliga.model.TableItem;


public class RecyclerTableViewAdapter extends ListAdapter<TableItem, RecyclerTableViewAdapter.ViewHolder> {
    private Context context;


    public RecyclerTableViewAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<TableItem> DIFF_CALLBACK = new DiffUtil.ItemCallback<TableItem>() {
        @Override
        public boolean areItemsTheSame(@NonNull TableItem oldItem, @NonNull TableItem newItem) {
            return oldItem.getPlace() == newItem.getPlace();
        }

        @Override
        public boolean areContentsTheSame(@NonNull TableItem oldItem, @NonNull TableItem newItem) {
            return oldItem.getTeamName().equals(newItem.getTeamName());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.table_item_row, parent, false);
        return new ViewHolder(view, this.context);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TableItem tableItem = getItem(position);

        holder.textViewPlace.setText(String.valueOf(tableItem.getPlace()));
        holder.textViewTeamName.setText(String.valueOf(tableItem.getTeamName()));
        holder.textViewGames.setText(String.valueOf(tableItem.getGames()));
        holder.textViewGoals.setText(String.valueOf(tableItem.getGoalsForPrinting()));
        holder.textViewGoalDiff.setText(String.valueOf(tableItem.getGoalDiff()));
        holder.textViewPoints.setText(String.valueOf(tableItem.getPoints()));
        holder.colorItems(tableItem.getPlace(), tableItem.getLeague());

    }

    public TableItem getTableItemAtPosition(int position){
        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private Context context;
        private TextView textViewPlace;
        private TextView textViewTeamName;
        private TextView textViewGames;
        private TextView textViewGoals;
        private TextView textViewGoalDiff;
        private TextView textViewPoints;

        public ViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            this.view = itemView;
            this.context = context;

            this.initTextViews(itemView);
        }

        private void initTextViews(View view){
            this.textViewPlace = view.findViewById(R.id.tv_place);
            this.textViewTeamName = view.findViewById(R.id.tv_team_name);
            this.textViewGames = view.findViewById(R.id.tv_games);
            this.textViewGoals = view.findViewById(R.id.tv_goals);
            this.textViewGoalDiff = view.findViewById(R.id.tv_goal_diff);
            this.textViewPoints = view.findViewById(R.id.tv_points);
        }

        private void colorItems(int place, int league){
            if (league == 1){
                this.colorItemsBundesliga(place);
            } else if (league == 2){
                this.colorItems2Bundesliga(place);
            } else if (league == 3){
                this.colorItems3Liga(place);
            }
        }

        private void colorItemsBundesliga(int place){
            int colorRes;
            if (1 <= place && place <= 4){
                colorRes = android.R.color.holo_green_dark;
            } else if (5 <= place && place <= 7){
                colorRes = android.R.color.holo_green_light;
            } else if (8 <= place && place <= 15){
                colorRes = android.R.color.darker_gray;
            } else if (place == 16){
                colorRes = android.R.color.holo_red_light;
            }else {
                colorRes = android.R.color.holo_red_dark;
            }
            this.view.setBackgroundResource(colorRes);
        }

        private void colorItems2Bundesliga(int place){
            int colorRes;
            if (1 <= place && place <= 2){
                colorRes = android.R.color.holo_green_dark;
            } else if (place == 3){
                colorRes = android.R.color.holo_green_light;
            } else if (4 <= place && place <= 15){
                colorRes = android.R.color.darker_gray;
            }else if (place == 16){
                colorRes = android.R.color.holo_red_light;
            }else {
                colorRes = android.R.color.holo_red_dark;
            }
            this.view.setBackgroundResource(colorRes);
        }

        private void colorItems3Liga(int place){
            int colorRes;
            if (1 <= place && place <= 2){
                colorRes = android.R.color.holo_green_dark;
            } else if (place == 3){
                colorRes = android.R.color.holo_green_light;
            } else if (4 <= place && place <= 16){
                colorRes = android.R.color.darker_gray;
            }else {
                colorRes = android.R.color.holo_red_dark;
            }
            this.view.setBackgroundResource(colorRes);
        }
    }
}
