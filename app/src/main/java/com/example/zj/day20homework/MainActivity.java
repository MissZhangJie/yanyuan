package com.example.zj.day20homework;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.zj.day20homework.Utils.BeanDown;
import com.example.zj.day20homework.Utils.HandlerUtils;
import com.example.zj.day20homework.adapter.MyAdapter;
import com.example.zj.day20homework.fragment.ListFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {
    List<Fragment> list;
    ViewPager  viewPager;
    HorizontalScrollView   hsv;
    LinearLayout  linearLayout;
    private static final String url="http://litchiapi.jstv.com/api/GetFeeds?column=0&PageSize=20&pageIndex=%s";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();

        MyAdapter adapter=new MyAdapter(getSupportFragmentManager(),list);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(this);

    }

    private void initData() {
        list=new ArrayList<Fragment>();
        for(int i=1;i<21;i++){
            String path=String.format(url,i);
            ListFragment  fragment=new ListFragment().getFragment(path);
            list.add(fragment);
        }
    }

    private void initView() {
        viewPager= (ViewPager) findViewById(R.id.vpId);
        hsv= (HorizontalScrollView) findViewById(R.id.hsvId);
        linearLayout= (LinearLayout) findViewById(R.id.llId);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
          TextView  textView;
        for(int i=0;i<linearLayout.getChildCount();i++){
            textView= (TextView) linearLayout.getChildAt(i);
            textView.setTag(i);
            if(i==position){
                textView.setTextColor(Color.RED);
            }else{
                textView.setTextColor(Color.BLACK);

            }
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewPager.setCurrentItem((Integer) view.getTag());
                }
            });
        }
        textView= (TextView) linearLayout.getChildAt(position);
        int  left=textView.getLeft();
        int width=getResources().getDisplayMetrics().widthPixels;
        int txtwidth=textView.getWidth();
        int offset=left-width/2+txtwidth/2;
        hsv.smoothScrollTo(offset,0);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
