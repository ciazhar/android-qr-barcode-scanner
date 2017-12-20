package com.ciazhar.qrbarcodescanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class ParticipantActivity extends AppCompatActivity {

    DatabaseConfig database;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant);
        queue = Volley.newRequestQueue(this);
        database = new DatabaseConfig(this);
    }

    public void getParticipantsFromServer() {
        int method = Request.Method.GET;
        String url = "http://103.246.107.213:9999/api/participant/all";

        JsonArrayRequest request = new JsonArrayRequest(method, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("Data :",response.toString());
                        database.deleteAllParticipantFromDatabase();
                        database.insertParticipantsToDatabase(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }

    public void getParticipantsFromDatabase(View view) {
        getParticipantsFromServer();
        database.getParticipantsFromDatabase();
    }

}
