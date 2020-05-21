package com.drinkwaater.oilcollector;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import java.io.IOException;

public class Networking {
    String bot1_battery;
    String bot1_oil;
    String bot1_state ;
    String bot2_battery;
    String bot2_oil;
    String bot2_state ;
    String bot3_battery;
    String bot3_oil;
    String bot3_state ;
    String bot4_battery;
    String bot4_oil;
    String bot4_state ;
    Context context;
    String url = "https://stackoverflow.com/questions/2835505";
    Document document;
    public String all;
    Networking (Context context){
        this.context = context;
        getdata g = new getdata();
        g.execute(" ");
    }

    public void setBot1_state(String bot1_state) {
        this.bot1_state = bot1_state;
    }


    public void setBot2_state(String bot2_state) {
        this.bot2_state = bot2_state;
    }


    public void setBot3_state(String bot3_state) {
        this.bot3_state = bot3_state;
    }


    public void setBot4_state(String bot4_state) {
        this.bot4_state = bot4_state;
    }

    public String getBot1_battery() {
        return bot1_battery;
    }

    public String getBot1_oil() {
        return bot1_oil;
    }

    public String getBot1_state() {
        return bot1_state;
    }

    public String getBot2_battery() {
        return bot2_battery;
    }

    public String getBot2_oil() {
        return bot2_oil;
    }

    public String getBot2_state() {
        return bot2_state;
    }

    public String getBot3_battery() {
        return bot3_battery;
    }

    public String getBot3_oil() {
        return bot3_oil;
    }

    public String getBot3_state() {
        return bot3_state;
    }

    public String getBot4_battery() {
        return bot4_battery;
    }

    public String getBot4_oil() {
        return bot4_oil;
    }

    public String getBot4_state() {
        return bot4_state;
    }

    public class getdata extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... urls) {
            String url = "https://owasp.org/www-community/xss-filter-evasion-cheatsheet" ; //change it while testing !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            try {
                Document document = Jsoup.connect(url).get();
                all = document.text();
                bot1_state = all.replaceFirst("Bot 1 - State \\s*(\\S+)", "$1");
                Log.w("regex",bot1_state);
                bot1_battery = all.replaceFirst("Bot 1 - Battery \\s*(\\S+)","$1");
                bot1_oil = all.replaceFirst("Bot 1 - Oil \\s*(\\S+)","$1");
                bot2_state = all.replaceFirst("Bot 2 - State \\s*(\\S+)", "$1");
                Log.w("regex",bot1_state);
                bot2_battery = all.replaceFirst("Bot 2 - Battery \\s*(\\S+)","$1");
                bot2_oil = all.replaceFirst("Bot 2 - Oil \\s*(\\S+)","$1");
                bot3_state = all.replaceFirst("Bot 3 - State \\s*(\\S+)", "$1");
                Log.w("regex",bot1_state);
                bot3_battery = all.replaceFirst("Bot 3 - Battery \\s*(\\S+)","$1");
                bot3_oil = all.replaceFirst("Bot 3 - Oil \\s*(\\S+)","$1");
                bot4_state = all.replaceFirst("Bot 4 - State \\s*(\\S+)", "$1");
                Log.w("regex",bot1_state);
                bot4_battery = all.replaceFirst("Bot 4 - Battery \\s*(\\S+)","$1");
                bot4_oil = all.replaceFirst("Bot 4 - Oil \\s*(\\S+)","$1");


            } catch (IOException e) {
                Toast.makeText(context,"Please check your internet connection",Toast.LENGTH_LONG).show();
                all = null;
            }
            return all;
        }
    }

}
