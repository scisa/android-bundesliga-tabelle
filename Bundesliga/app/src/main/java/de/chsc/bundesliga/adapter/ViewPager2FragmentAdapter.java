package de.chsc.bundesliga.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

import de.chsc.bundesliga.ui.Fragments.Bl1Fragment;
import de.chsc.bundesliga.ui.Fragments.Bl2Fragment;
import de.chsc.bundesliga.ui.Fragments.Bl3Fragment;

public class ViewPager2FragmentAdapter extends FragmentStateAdapter {
    private List<Fragment> fragmentList;

    public ViewPager2FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        this.fragmentList = new ArrayList<>();
        this.initFragments();
    }

    private void initFragments(){
        this.fragmentList.add(new Bl1Fragment());
        this.fragmentList.add(new Bl2Fragment());
        this.fragmentList.add(new Bl3Fragment());
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return this.fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return this.fragmentList.size();
    }
}
