package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private Button but;
    private TextView tex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tex = (TextView) findViewById(R.id.textView);
        but = (Button)findViewById(R.id.button);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getWebsite();
            }
        });

    }
    private void getWebsite(){
        final Integer extra_seat = 2;
        new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuilder builder = new StringBuilder();
                try {
                    Document doc = Jsoup.connect("http://210.94.185.43:4000/main").get();
                    String title = doc.title();
//                    Elements links = doc.select("a[href]");
                    String links = doc.select("h1").text();
//                    builder.append(title).append("\n");
                    builder.append(links);
//                    for (Element link : links){
//                        builder.append("\n").append("Link : ").append(link.attr("href"))
//                                .append("\n").append("Text : ").append(link.text());
//                    }
                } catch (IOException e) {
                    builder.append("Error : ").append(e.getMessage()).append("\n");
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        int n = Integer.parseInt(builder.toString());
                        int r = 0;

                        if(extra_seat-n>=0){
                            r = extra_seat-n;
                        }
                        else {
                            r = 0;
                        }
                        double answer=n/extra_seat;

                        if(answer >= 0.5){
                            tex.setText(String.format("%s %s %d", "혼잡","잔여석:",r));
                            tex.setTextColor(Color.parseColor("#FF0000"));
                        }
                        else{
                            tex.setText(String.format("%s %s %d", "원활","잔여석:",r ));
                            tex.setTextColor(Color.parseColor("#00C800"));
                        }
//                        tex.setText(builder.toString());

                    }
                });



            }
        }).start();

    }
}
