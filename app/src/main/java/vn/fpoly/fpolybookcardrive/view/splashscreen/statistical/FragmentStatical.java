package vn.fpoly.fpolybookcardrive.view.splashscreen.statistical;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import vn.fpoly.fpolybookcardrive.R;
import vn.fpoly.fpolybookcardrive.adapter.TablayoutStatisticsAdapter;

public class FragmentStatical extends Fragment {
    private RecyclerView recyclerViewStatistical;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistical, container, false);
        initView(view);
        initTabLayout(view);
        return view;
    }
    private void initView(View view){
        viewPager = view.findViewById(R.id.viewPager_sta);
        tabLayout   = view.findViewById(R.id.tabLayout_sta);
    }
    private void initTabLayout(View view) {
        TablayoutStatisticsAdapter tablayoutStatisticsAdapter = new TablayoutStatisticsAdapter(getChildFragmentManager(),view.getContext(),3);
        viewPager.setAdapter(tablayoutStatisticsAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setText(getString(R.string.yesterday));
        tabLayout.getTabAt(1).setText(getString(R.string.day));
        tabLayout.getTabAt(2).setText(getString(R.string.month));

    }
}
