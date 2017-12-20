package com.ciazhar.qrbarcodescanner.net;

import com.ciazhar.qrbarcodescanner.model.response.AttendanceData;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RestService {

    String BASE_URL = "http://103.246.107.213:9999/";

    @GET("")
    Call<AttendanceData> setAttendance(@Url String url);

    class Factory {

        private static RestService service;
        public static RestService getInstance(){

            if (service == null) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                        .build();

                service = retrofit.create(RestService.class);
                return service;
            }
            else return service;
        }
    }



}
