package com.wallpapre.billion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    String[] recommendationList = new String[]{"Abstract", "Amoled", "Autumn", "Beach", "Brands", "Bikes", "Black", "Cars", "Desert", "3D", "Flowers", "Fruit", "Islamic", "Lake", "Landscape", "Minimal", "Material", "Mountain", "Nature", "Polygon", "Social", "Sports", "Summer", "Stock", "Spring", "Space", "Technology", "Travel", "Texture", "Winter", "Sunset", "Moon", "City", "Forest", "Quotes", "Food", "Sea", "Colorful"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onRun();

    }

    private void onRun(){
        final ArrayList<String> list = new ArrayList<String>();
        Collections.addAll(list, recommendationList);
        Collections.shuffle(list);
        Log.d("TAG", "onCreate: "+list.toString());

        // adding recycler view
        LinearLayoutManager layoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recommendation_recyclerview);
        mRecyclerView.setLayoutManager(layoutManager);
        Adapter mAdapter = new recommendation_adapter(this, list);
        mRecyclerView.setAdapter(mAdapter);

    }

    public void onRecommendationClick(String search){
        Log.d("onRecommendationClick",search);

    }

//    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//        }
//
//        @Override
//        protected Boolean doInBackground(String... urls) {
//            try {
//
//                //------------------>>
//                HttpGet httppost = new HttpGet("https://www.google.com/search?hl=EN&tbm=isch&sxsrf=&q=black+phone+wallpaper+HD&oq=sunset&sclient=img");
//                HttpClient httpclient = new DefaultHttpClient();
//                HttpResponse response = httpclient.execute(httppost);
//
//                // StatusLine stat = response.getStatusLine();
//                int status = response.getStatusLine().getStatusCode();
//
//                if (status == 200) {
//                    HttpEntity entity = response.getEntity();
//                    String data = EntityUtils.toString(entity);
//
//                    Log.d("Server response", data);
//                   // JSONObject jsono = new JSONObject(data);
//
//                    return true;
//                }else {
//                    Log.d("Server response", "Failed to get server response" );
//
//                }
//
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return false;
//        }
//
//        protected void onPostExecute(Boolean result) {
//
//        }
//    }
}