package com.ciazhar.qrbarcodescanner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.ciazhar.qrbarcodescanner.model.Participant;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class ParticipantActivity extends AppCompatActivity {

    ParticipantRepository database;
    RequestQueue queue;

    private List<Participant> participantList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ParticipantAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant);
        queue = Volley.newRequestQueue(this);
        database = new ParticipantRepository(this);
        recyclerView = findViewById(R.id.participant_rv);
        adapter = new ParticipantAdapter(participantList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        String s = database.getObjectParticipantFromDatabase(participantList).toString();
        adapter.notifyDataSetChanged();
        Log.i("Data",s);
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
