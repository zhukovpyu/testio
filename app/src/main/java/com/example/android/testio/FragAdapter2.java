package com.example.android.testio;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

//Фрагмент адаптер 2
public class FragAdapter2 extends FragmentPagerAdapter {

    private Context mContext;

    public FragAdapter2(Context context, FragmentManager fm2) {
        super(fm2);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new TestPassesFragment();
        } else {
            return new TestInfFragment();
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return (CharSequence) mContext.getString(R.string.passes);
        } else {
            return mContext.getString(R.string.test_inf);
        }
    }


    @Override
    public int getCount() {
        return 2;
    }
}
