package com.ciazhar.qrbarcodescanner;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class SplashActivity extends AppCompatActivity {

    AgendaRepository database;
    RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        queue = Volley.newRequestQueue(this);
        database = new AgendaRepository(this);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this,AgendaActivity.class);
//                startActivity(intent);
                getAgendaFromServer(intent);
                finish();
            }
        },3000L);
    }

    public void getAgendaFromServer(final Intent intent) {
        int method = Request.Method.GET;
        String url = "http://103.246.107.213:9999/api/agenda/all";

        JsonArrayRequest request = new JsonArrayRequest(method, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        database.deleteAllAgendaFromDatabase();
                        database.insertAgendaToDatabase(response);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }
}
