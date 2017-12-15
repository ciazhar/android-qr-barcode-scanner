package com.ciazhar.qrbarcodescanner;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by ciazhar on 15/12/17.
 */

public class DatabaseConfig extends SQLiteOpenHelper {

    static final private String DB_NAME = "rp";
    static final private String DB_TABLE = "";
    static final private Integer DB_VER = 1;

    Context context;
    SQLiteDatabase database;

    public DatabaseConfig(Context context) {
        super(context, DB_NAME,null,DB_VER);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+DB_NAME+" (id text, name text, phoneNumber text, email text, jobStatus text, dvdBitBit text, paymentStatus boolean, attendanceStatus boolean);");
        Log.i("Database","Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+DB_NAME);
        onCreate(database);
    }

    void insertParticipantsToDatabase(JSONArray response){
        database = getWritableDatabase();

        String id;
        String name;
        String phoneNumber;
        String email;
        String jobStatus;
        String dvdBitBit;
        int paymentStatus;
        int attendanceStatus;

        for (int i=0;i<response.length();i++){
            try {
                JSONObject data = response.getJSONObject(i);

                id = data.getString("id");
                name = data.getString("name");
                phoneNumber = data.getString("phoneNumber");
                email = data.getString("email");
                jobStatus = data.getString("jobStatus");
                dvdBitBit = data.getString("dvdBitBit");
                paymentStatus = data.getBoolean("paymentStatus")?1:0;
                attendanceStatus = data.getBoolean("attendanceStatus")?1:0;

                database.execSQL("insert into "+DB_NAME+" values('"+
                        id+"','"+name+"','"+phoneNumber+"','"+email+"','"+
                        jobStatus+"','"+dvdBitBit+"',"+paymentStatus+","+attendanceStatus+");");

                Log.i("Data parsing",data.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    void getParticipantsFromDatabase(){
        database = getWritableDatabase();

        Cursor cursor = database.rawQuery("select * from "+DB_NAME,null);
        StringBuilder builder = new StringBuilder();

        while (cursor.moveToNext()){
            String nama = cursor.getString(1);
            String collage= cursor.getString(3);
            builder.append(nama+"     "+collage+"\n");
        }
        Toast.makeText(context,builder.toString(),Toast.LENGTH_LONG).show();
    }
}
