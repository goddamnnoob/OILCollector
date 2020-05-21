package com.drinkwaater.oilcollector;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.lzyzsd.circleprogress.ArcProgress;
import com.github.lzyzsd.circleprogress.CircleProgress;
import com.google.android.material.button.MaterialButton;

import org.json.JSONObject;

public class Bot1Fragment extends Fragment {
    CircleProgress circleProgress;
    Networking networking;
    ArcProgress arcProgress;
    MaterialButton start;
    String battery ;
    String oil;
    String state;
    MaterialButton comeback;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bot1,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        circleProgress = view.findViewById(R.id.circle_progress_bot1);
        arcProgress = view.findViewById(R.id.arc_progress_bot1);
        comeback = view.findViewById(R.id.comeback_1);
        start = view.findViewById(R.id.start_1);
        networking = new Networking(getContext());
        battery = networking.getBot1_battery();
        oil = networking.getBot1_oil();
        state = networking.getBot1_state();
        if(battery != null ){circleProgress.setProgress(Integer.parseInt(battery));}
        if(oil != null){arcProgress.setProgress(Integer.parseInt(oil));}
        super.onViewCreated(view, savedInstanceState);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state=="OFF"){startcall();
                networking.setBot1_state("ON");}
                else if(state == "ON") {
                    Toast.makeText(getContext(),"Already sent",Toast.LENGTH_LONG).show();
                }
                else {Toast.makeText(getContext(),"Error occured check internet connection and restart the app",Toast.LENGTH_SHORT).show();
                     }
            }
        });
        comeback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state=="ON"){comebackcall();
                    networking.setBot1_state("OFF");}
                else if(state == "OFF"){
                    Toast.makeText(getContext(),"Already sent",Toast.LENGTH_LONG).show();
                }
                else {Toast.makeText(getContext(),"Error occured check internet connection and restart the app",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void comebackcall(){
        final String url = "http://httpbin.org/get?param1=hello";//CHANGE IT TO http://[ip of esp8266]/1/off
        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        Log.d("Response", response.toString());
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                        builder1.setMessage("BOT 1 come back signal sent successfully");
                        builder1.setCancelable(true);


                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),"Failed Try Again",Toast.LENGTH_LONG).show();
                        Log.d("Error.Response", error.toString());
                    }
                }
        );

// add it to the RequestQueue
        queue.add(getRequest);
    }
    private void startcall(){
        final String url = "http://httpbin.org/get?param1=hello";//CHANGE IT TO http://[ip of esp8266]/1/on
        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        // display response
                        AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                        builder1.setMessage("BOT 1 start signal sent successfully");
                        builder1.setCancelable(true);


                        AlertDialog alert11 = builder1.create();
                        alert11.show();
                        Log.d("Response", response.toString());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),"Failed Try Again",Toast.LENGTH_LONG).show();

                        Log.d("Error.Response", error.toString());
                    }
                }
        );


// add it to the RequestQueue
        queue.add(getRequest);
    }

}
