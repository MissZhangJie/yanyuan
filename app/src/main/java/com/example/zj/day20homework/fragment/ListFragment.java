package com.example.zj.day20homework.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.zj.day20homework.R;
import com.example.zj.day20homework.Utils.BeanDown;
import com.example.zj.day20homework.Utils.HandlerUtils;
import com.example.zj.day20homework.adapter.MyFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/22 0022.
 */
public class ListFragment extends Fragment {
    List<BeanDown> list;
    ListView listView;
    HandlerUtils handlerUtils;
    MyFragmentAdapter adapter;
    Bitmap  bitmap;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    list = new ArrayList<BeanDown>();
                    list = (List<BeanDown>) msg.obj;
                    Log.d("===================", "======================" + list);
                    adapter = new MyFragmentAdapter(list, getContext());
                    listView.setAdapter(adapter);
                    break;default:
                    break;
            }


        }
    };

    public ListFragment getFragment(String path) {
        ListFragment fragment = new ListFragment();
        Bundle bundle = new Bundle();
        bundle.putString("zj", path);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.list_fragment, null);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handlerUtils = new HandlerUtils(handler);
        String url = getArguments().getString("zj");
        handlerUtils.JsonString(url);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listView = (ListView) view.findViewById(R.id.listId);
    }
}
