package com.example.usr0200467.androidtrainig_03;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class MyAsyncTask extends AsyncTask<String, Void, Boolean> {

    private static final String TAG = MyAsyncTask.class.getSimpleName();
    private final MyAsyncTask self = this;

    private Context context;
    public MyAsyncTask(Context context){
        this.context = context;
    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {
            for (int i = 0; i <= 10; i++) {
                Thread.sleep(1000);
                Log.i("MyAsyncTask", i + "sec");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
}
