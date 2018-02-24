package com.example.student.db2018022404;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void click1(View v)
    {
        new Thread(){
            @Override
            public void run() {
                super.run();
                String str_url = "http://rate.bot.com.tw/xrt?Lang=zh-TW";
                URL url = null;
                StringBuilder sb = new StringBuilder();
                String str = "";
                try {
                    url = new URL(str_url);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.connect();
                    InputStream stream = conn.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(stream));
                    while ((str = br.readLine()) != null)
                    {
                        sb.append(str);
                    }
                    br.close();
                    stream.close();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                String data = sb.toString();
                int loc1 = data.indexOf("日圓 (JPY)");
                int loc2 = data.indexOf("本行現金賣出", loc1);
                int loc3 = data.indexOf("0.2765", loc2);
                Log.d("TWD", "loc1:" + loc1 + ", loc2:" + loc2 + ", loc3:" + loc3);
                String twd = data.substring(loc2+56, loc2+62);
                Log.d("TWD", "twd:" + twd);
            }
        }.start();
    }
}
