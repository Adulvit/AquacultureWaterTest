package com.betagro.adulvitc.aquaculturewatertest.utility;

import android.content.Context;
import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

public class MyPostData extends AsyncTask<String, Void, String> {

    private Context context;

    public MyPostData(Context context) {
        this.context = context;
    }


    @Override
    protected String doInBackground(String... strings) {

        // statement Try และ Catch เพื่อป้องกันการ error แล้ว Close App

        try {

            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = new FormEncodingBuilder()
                    .add("isAdd", "true")
                    .add("Name", strings[0])
                    .add("User", strings[1])
                    .add("Password", strings[2])
                    .build();
            Request.Builder builder = new Request.Builder();
            Request request = builder.url(strings[3]).post(requestBody).build();
            Response response = okHttpClient.newCall(request).execute();
            return response.body().string();



        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}   //Main Class
