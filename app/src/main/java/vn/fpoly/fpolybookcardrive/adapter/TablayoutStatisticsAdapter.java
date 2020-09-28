package vn.fpoly.fpolybookcardrive.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import vn.fpoly.fpolybookcardrive.view.splashscreen.statistical.day.StaDayFragment;
import vn.fpoly.fpolybookcardrive.view.splashscreen.statistical.month.StaMonthFragment;
import vn.fpoly.fpolybookcardrive.view.splashscreen.statistical.yesterday.StaYesterdayFragment;

public class TablayoutStatisticsAdapter extends FragmentPagerAdapter{
    private Context context;
    private int totalTabs;

    public TablayoutStatisticsAdapter(FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                StaYesterdayFragment staYesterdayFragment = new StaYesterdayFragment();
                return staYesterdayFragment;

            case 1:
                StaDayFragment staDayFragment = new StaDayFragment();
                return staDayFragment;
            case 2:
                StaMonthFragment staMonthFragment = new StaMonthFragment();
                return staMonthFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
