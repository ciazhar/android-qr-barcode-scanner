package com.ciazhar.qrbarcodescanner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

public class DialogActivity extends AppCompatActivity {
    private static final int ZBAR_CAMERA_PERMISSION = 1;
    private Class<?> mClss;
    RequestQueue queue;
    ParticipantRepository database;
    TextView agendaName;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_dialog);
        setupToolbar();

        queue = Volley.newRequestQueue(this);
        database = new ParticipantRepository(this);

        agendaName = findViewById(R.id.dialog_name);

        Intent intent = getIntent();
        agendaName.setText(intent.getStringExtra("agenda.name"));

    }

    public void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void launchFullActivity(View v) {
        launchActivity(FullScannerActivity.class);
    }

    public void launchActivity(Class<?> clss) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            mClss = clss;
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.CAMERA}, ZBAR_CAMERA_PERMISSION);
        } else {
            Intent intent = new Intent(this, clss);
            startActivity(intent);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case ZBAR_CAMERA_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(mClss != null) {
                        Intent intent = new Intent(this, mClss);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(this, "Please grant camera permission to use the QR Scanner", Toast.LENGTH_SHORT).show();
                }
        }
    }

    public void getIku(View view) {
        int method = Request.Method.GET;
        String url = "http://103.246.107.213:9999/api/participant/attend?id=5a3405bcb95029b2fa93e10c";

        StringRequest request = new StringRequest(method, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("set agenda : ","success");
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        queue.add(request);
    }

    public void getParticipantsList(View view) {
        Intent intent = new Intent(this,ParticipantActivity.class);
        intent.putExtra("agenda.name",agendaName.getText());
        getParticipantsFromServer();
        startActivity(intent);
    }

    public void getParticipantsFromServer() {
        int method = Request.Method.GET;
        String url = "http://103.246.107.213:9999/api/participant/5a3be5b5b95029c89d8bed32/all";

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

}