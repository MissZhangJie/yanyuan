package com.example.zj.day20homework.Utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2016/8/22 0022.
 */
public class HandlerUtils {
    List<BeanDown> list;
    Handler handler;
    private static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public HandlerUtils(Handler handler) {

        this.handler = handler;
    }

    public  void JsonString(final String url){
       executorService.execute(new Runnable() {
           @Override
           public void run() {
               list=new ArrayList<BeanDown>();
               String str=DownLoadUtils.getString(url);
               try {
                   JSONObject  object=new JSONObject(str);
                   BeanDown beanDown=null;
                   JSONObject  par=object.optJSONObject("paramz");
                   JSONArray  feed=par.optJSONArray("feeds");
                   List li=new ArrayList();
                   for (int i=0;i<feed.length();i++){
                       JSONObject da=feed.optJSONObject(i);
                       JSONObject dia=da.optJSONObject("data");
                       String subject=dia.optString("subject");
                       String summary=dia.optString("summary");
                       String cover=dia.optString("cover");
                       beanDown=new BeanDown(subject,summary,cover);
                       li.add(beanDown);
                   }
                   list=li;
                   Log.d("+++++++++++++++","+++++++++++++++++++"+list);
               } catch (JSONException e) {
                   e.printStackTrace();
               }
               Message message=Message.obtain();
               message.what=0;
               message.obj=list;
               Log.d("+++++++++++++++","++++++====================+++++++++"+list);
               handler.sendMessage(message);
           }
       });
    }
    public void getImag(final String  url1){
      executorService.execute(new Runnable() {
          @Override
          public void run() {
              byte[] bs=DownLoadUtils.getbyte(url1);
              Bitmap  bitmap= BitmapFactory.decodeByteArray(bs,0,bs.length);
              Message message=Message.obtain();
              message.what=1;
              message.obj=bitmap;
              Bundle bundle1=new Bundle();
              bundle1.putString("123",url1);
              message.setData(bundle1);
              handler.sendMessage(message);
          }
      });
    }
}
