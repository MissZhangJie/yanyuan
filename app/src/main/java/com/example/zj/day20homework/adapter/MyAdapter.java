package com.example.zj.day20homework.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by Administrator on 2016/8/22 0022.
 */
public class MyAdapter  extends FragmentStatePagerAdapter {
    List<Fragment>  list;

    public MyAdapter(FragmentManager fm,List<Fragment>  list) {
        super(fm);
        this.list=list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
