package de.chsc.bundesliga.ui.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

import de.chsc.bundesliga.R;
import de.chsc.bundesliga.adapter.ViewPager2FragmentAdapter;
import de.chsc.bundesliga.ui.Fragments.ConnectionFailedFragment;
import de.chsc.bundesliga.ui.Toast.RefreshingToast;
import de.chsc.bundesliga.util.Constants;
import de.chsc.bundesliga.util.NetworkUtil;


public class TableActivity extends AppCompatActivity implements ConnectionFailedFragment.OnRefreshFailureClickedListener {
    private SwipeRefreshLayout swipeRefreshLayout;
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private List<String> fragmentNames;
    private ViewPager2FragmentAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initViews();
        this.initFragmentNames();
        this.setViewPager();
        this.setTabLayout();
        this.onRefreshClicked();
        this.pullToRefresh();
    }

    private void initViews(){
        this.viewPager = findViewById(R.id.viewPager);
        this.tabLayout = findViewById(R.id.tabLayout);
        this.swipeRefreshLayout = findViewById(R.id.sl_pull_to_refresh);
    }

    private void initFragmentNames(){
        this.fragmentNames = new ArrayList<>();
        this.fragmentNames.add("1. Bundesliga");
        this.fragmentNames.add("2. Bundesliga");
        this.fragmentNames.add("3. Liga");
    }

    private void setViewPager(){
        this.adapter = new ViewPager2FragmentAdapter(getSupportFragmentManager(), getLifecycle());
        this.viewPager.setAdapter(this.adapter);
    }

    private void setTabLayout(){
        TabLayoutMediator mediator = new TabLayoutMediator(this.tabLayout, this.viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override
                    public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        tab.setText(fragmentNames.get(position));
                    }
                });
        mediator.attach();
    }

    private void pullToRefresh(){
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onRefreshClicked();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onRefreshClicked() {
        if (!NetworkUtil.isInternetAvailable(getApplicationContext())){
            this.connectionFailed();
        }else {
            RefreshingToast toast = new RefreshingToast(this);
            toast.show();
            this.connectionEstablished();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.refresh_menu_id:
                this.onRefreshClicked();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }


    private void connectionFailed(){
        this.viewPager.setVisibility(View.GONE);
        ConnectionFailedFragment failureFragment = new ConnectionFailedFragment();
        failureFragment.setOnRefreshFailureClickedListener(this);
        getSupportFragmentManager().beginTransaction().replace(R.id.table_container, failureFragment, Constants.CONNECTION_LOST_FRAGMENT_TAG).commit();
    }

    private void connectionEstablished(){
        this.viewPager.setVisibility(View.VISIBLE);
        Fragment fragment = getSupportFragmentManager().findFragmentByTag(Constants.CONNECTION_LOST_FRAGMENT_TAG);
        if (fragment != null){
            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
        this.setTabLayout();
    }

}
