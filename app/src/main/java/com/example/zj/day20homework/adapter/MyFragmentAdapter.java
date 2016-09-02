package com.example.zj.day20homework.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zj.day20homework.R;
import com.example.zj.day20homework.Utils.BeanDown;
import com.example.zj.day20homework.Utils.HandlerUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.zj.day20homework.R.mipmap.ic_launcher;

/**
 * Created by Administrator on 2016/8/22 0022.
 */
public class MyFragmentAdapter extends BaseAdapter {
    List<BeanDown> list;
    Context context;
    HandlerUtils handlerUtils;
    public Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    bitmap = (Bitmap) msg.obj;
                    break;
                default:
                    break;
            }
        }
    };
        private final static String path = "http://litchiapi.jstv.com/";
        private Bitmap bitmap;


        @Override
        public int getCount() {
            Log.d("**********************", "" + list);
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final ViewHolder viewHolder;
            if (view == null) {
                viewHolder = new ViewHolder();
                view = LayoutInflater.from(context).inflate(R.layout.item_list, null);
                viewHolder.imageView = (ImageView) view.findViewById(R.id.imgId);
                viewHolder.textView = (TextView) view.findViewById(R.id.tvsubId);
                viewHolder.textView1 = (TextView) view.findViewById(R.id.tvsumId);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.textView1.setText(list.get(i).getSummary());
            viewHolder.textView.setText(list.get(i).getSubject());
            viewHolder.imageView.setTag(path+(list.get(i).getCover()));
            handlerUtils.getImag(path+list.get(i).getCover());
            viewHolder.imageView.setImageResource(R.mipmap.ic_launcher);
            handlerUtils=new HandlerUtils(handler);
            if(viewHolder.imageView.getTag()!=null && viewHolder.imageView.getTag().equals(path+(list.get(i).getCover()))){
                viewHolder.imageView.setImageBitmap(bitmap);
            }
            return view;
        }

        class ViewHolder {
            ImageView imageView;
            TextView textView, textView1;
        }

        public MyFragmentAdapter(List<BeanDown> list, Context context) {
            this.list = list;
            this.context = context;
        }

}
