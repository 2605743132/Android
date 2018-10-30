package com.example.caoyuan.helper;


import android.os.Handler;
import android.os.Message;

import com.google.common.io.CharStreams;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Helper  {

    public  Helper(){ };

    public Helper get(final String url) {

        new Thread() {
            @Override
            public void run() {
                super.run();

                try {
                    URL url1 = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
                    connection.setConnectTimeout(5000);
                    connection.setRequestMethod( "GET");
                    if (connection.getResponseCode()==HttpURLConnection.HTTP_OK){
                        String data = CharStreams.toString(new InputStreamReader(connection.getInputStream(), "utf-8"));
                        Message message = Message.obtain();
                        message.obj=data;

                       handler.sendMessage(message);

                        }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }.start();
        return this;
    }







        private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String data = (String) msg.obj;
         httpListener.success(data);

        }
    };

    private HttpListener httpListener;
public void Result(HttpListener httpListener){

    this.httpListener = httpListener;
}
    public  interface  HttpListener{
        void success(String data);


    }
}
