package com.example.zj.day20homework.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016/8/20 0020.
 */
public class DownLoadUtils {

    public static String getString(String url){
        String  json=null;
        URL url1=null;
        InputStream   input=null;
        ByteArrayOutputStream  bos=null;
        try {
            url1=new URL(url);
            HttpURLConnection  hcn= (HttpURLConnection) url1.openConnection();
            hcn.connect();
            input=hcn.getInputStream();
            bos=new ByteArrayOutputStream();
            byte[] b=new byte[1024];
            int len=0;
            while((len=input.read(b))!=-1){
                bos.write(b,0,len);
            }
            json=bos.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bos!=null){
                try {
                    bos.close();
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return json;
    }
    public static byte[] getbyte(String url){
        byte[] data=null;
        URL url1=null;
        InputStream   input=null;
        ByteArrayOutputStream  bos=null;
        try {
            url1=new URL(url);
            HttpURLConnection  hcn= (HttpURLConnection) url1.openConnection();
            input=hcn.getInputStream();
            bos=new ByteArrayOutputStream();
            byte[] b=new byte[1024];
            int len=0;
            while((len=input.read(b))!=-1){
                bos.write(b,0,len);
            }
            data=bos.toByteArray();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bos!=null){
                try {
                    bos.close();
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data;
    }
}
