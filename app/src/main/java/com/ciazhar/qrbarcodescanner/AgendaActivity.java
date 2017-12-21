package com.ciazhar.qrbarcodescanner;

import android.content.Intent;
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
import com.ciazhar.qrbarcodescanner.model.Agenda;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class AgendaActivity extends AppCompatActivity {

    AgendaRepository database;
    RequestQueue queue;

    private List<Agenda> agendaList = new ArrayList<>();
    private RecyclerView recyclerView;
    private AgendaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);

        queue = Volley.newRequestQueue(this);
        database = new AgendaRepository(this);
        recyclerView = findViewById(R.id.agenda_rv);
        adapter = new AgendaAdapter(agendaList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        getAgendaFromServer();
//        getAgendaFromServer();
        String s = database.getObjectAgendaFromDatabase(agendaList).toString();

        Log.i("data", s);
        adapter.notifyDataSetChanged();
    }

    public void doReleaseParty(View view) {
        Intent intent = new Intent(this,DialogActivity.class);
        startActivity(intent);
    }

    public void getAgendaFromServer() {
        int method = Request.Method.GET;
        String url = "http://103.246.107.213:9999/api/agenda/all";

        JsonArrayRequest request = new JsonArrayRequest(method, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        database.deleteAllAgendaFromDatabase();
                        database.insertAgendaToDatabase(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }

    void addAgendaToDevice(Agenda agenda){
        agendaList.add(agenda);
    }


}
