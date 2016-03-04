package com.example.tianbi.proj2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.support.wearable.view.CardFragment;
import android.support.wearable.view.FragmentGridPagerAdapter;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by TIANBI on 3/2/16.
 */
public class GridViewPagerAdapter extends FragmentGridPagerAdapter{
    private final Context mContext;
    private List<Rep> mReps;

    public GridViewPagerAdapter(Context ctx, FragmentManager fm) {
        super(fm);
        mContext = ctx;
        mReps = new ArrayList<GridViewPagerAdapter.Rep>();
        mReps.add(new Rep(new RepFragments()));
        mReps.add(new Rep(new RepFragments()));
        mReps.add(new Rep(new RepFragments()));
        mReps.add(new Rep(new VoteFragment()));
    }

    private Fragment cardFragment(int titleRes, int textRes) {
        Resources res = mContext.getResources();
        CardFragment fragment =
                CardFragment.create(res.getText(titleRes), res.getText(textRes));
        return fragment;
    }

    private class Rep {
        final List<Fragment> columns = new ArrayList<Fragment>();


        public Rep(Fragment... fragments) {
            for (Fragment f : fragments) {
                add(f);
            }
        }

        public void add(Fragment f) {
            columns.add(f);
        }

        Fragment getColumn(int i) {
            return columns.get(i);
        }

        public int getColumnCount() {
            return columns.size();
        }
    }
    @Override
    public Fragment getFragment(int row, int col) {
        Rep adapterReps = mReps.get(row);
        return adapterReps.getColumn(col);
    }

    @Override
    public int getRowCount() {
        return mReps.size();
    }

    @Override
    public int getColumnCount(int i) {
        return mReps.get(i).getColumnCount();
    }
}

