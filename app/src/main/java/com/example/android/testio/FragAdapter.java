package com.example.android.testio;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

//Фрагмент адаптер 1
public class FragAdapter extends FragmentPagerAdapter {

    private Context mContext;

    public FragAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new MakeTestFragment();
        } else{
            return new PassTestFragment();
        }
    }


        @Override
        public CharSequence getPageTitle ( int position){
            if (position == 0) {
                return mContext.getString(R.string.make_test);
            } else{
                return mContext.getString(R.string.pass_test);
            }
        }


    @Override
    public int getCount() {
        return 2;
    }
}