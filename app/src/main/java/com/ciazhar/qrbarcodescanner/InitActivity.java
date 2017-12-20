package com.ciazhar.qrbarcodescanner;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class InitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);
    }

    public void doReleaseParty(View view) {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
