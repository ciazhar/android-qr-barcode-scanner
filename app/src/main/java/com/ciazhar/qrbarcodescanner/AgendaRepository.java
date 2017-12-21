package com.ciazhar.qrbarcodescanner;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.ciazhar.qrbarcodescanner.model.Agenda;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by ciazhar on 22/12/17.
 */

public class AgendaRepository extends SQLiteOpenHelper {

    static final private String DB_NAME = "agenda";
    static final private Integer DB_VER = 1;

    Context context;
    SQLiteDatabase database;

    public AgendaRepository(Context context) {
        super(context, DB_NAME,null,DB_VER);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+DB_NAME+" (id text, name agendaName);");
        Log.i("Database","Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+DB_NAME);
        onCreate(database);
    }

    void insertAgendaToDatabase(JSONArray response){
        database = getWritableDatabase();

        String id;
        String name;

        for (int i=0;i<response.length();i++){
            try {
                JSONObject data = response.getJSONObject(i);

                id = data.getString("id");
                name = data.getString("agendaName");

                database.execSQL("insert into "+DB_NAME+" values('"+
                        id+"','"+name+"');");

                Log.i("Data parsing",data.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        Toast.makeText(context,"Update Sukses",Toast.LENGTH_SHORT).show();
    }

    List<Agenda> getObjectAgendaFromDatabase(List<Agenda> agendaList){
        database = getWritableDatabase();

        Cursor cursor = database.rawQuery("select * from "+DB_NAME,null);
        while (cursor.moveToNext()){
            String id = cursor.getString(0);
            String name = cursor.getString(1);

            agendaList.add(new Agenda(id,name));
        }
        return agendaList;
    }

    void deleteAllAgendaFromDatabase(){
        database = getWritableDatabase();

        database.delete(DB_NAME,null,null);
    }

}
