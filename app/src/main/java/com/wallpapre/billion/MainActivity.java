package com.wallpapre.billion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    String[] recommendationList = new String[]{"Abstract", "Amoled", "Autumn", "Beach", "Brands", "Bikes", "Black", "Cars", "Desert", "3D", "Flowers", "Fruit", "Islamic", "Lake", "Landscape", "Minimal", "Material", "Mountain", "Nature", "Polygon", "Social", "Sports", "Summer", "Stock", "Spring", "Space", "Technology", "Travel", "Texture", "Winter", "Sunset", "Moon", "City", "Forest", "Quotes", "Food", "Sea", "Colorful"};

    String TAG = "main activity";
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


        EditText search_et = findViewById(R.id.text_search);
        search_et.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //performSearch();
                    return true;
                }
                return false;
            }
        });



        //new JSONAsyncTask().execute("");
        final ArrayList<String> list_images = new ArrayList<String>();
        list_images.add("https://pixabay.com/get/52e1d5414c54b10ff3d8992cc62f307e1c3adce44e507440732e79d7934acd_640.jpg");
        list_images.add("https://pixabay.com/get/54e8d145425ab108f5d08460962930791536dbe7524c704c7c297ad69449c250_1280.jpg");
        list_images.add("https://pixabay.com/get/52e2d0404d57ad14f1dc8460962930791536dbe7524c704c7c297ad69449c250_640.jpg");
        list_images.add("https://pixabay.com/get/50e9d5414351b108f5d08460962930791536dbe7524c704c7c297ad69449c459_1280.jpg");
        list_images.add("https://pixabay.com/get/52e1d5414c54b10ff3d8992cc62f307e1c3adce44e507440732e79d7934acd_640.jpg");
        list_images.add("https://pixabay.com/get/54e8d145425ab108f5d08460962930791536dbe7524c704c7c297ad69449c250_1280.jpg");
        list_images.add("https://pixabay.com/get/52e2d0404d57ad14f1dc8460962930791536dbe7524c704c7c297ad69449c250_640.jpg");
        list_images.add("https://pixabay.com/get/50e9d5414351b108f5d08460962930791536dbe7524c704c7c297ad69449c459_1280.jpg");
        list_images.add("https://pixabay.com/get/52e1d5414c54b10ff3d8992cc62f307e1c3adce44e507440732e79d7934acd_640.jpg");
        list_images.add("https://pixabay.com/get/54e8d145425ab108f5d08460962930791536dbe7524c704c7c297ad69449c250_1280.jpg");
        list_images.add("https://pixabay.com/get/52e2d0404d57ad14f1dc8460962930791536dbe7524c704c7c297ad69449c250_640.jpg");
        list_images.add("https://pixabay.com/get/50e9d5414351b108f5d08460962930791536dbe7524c704c7c297ad69449c459_1280.jpg");



         //GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);

        RecyclerView image_rv = (RecyclerView) findViewById(R.id.image_recyclerview);

        image_rv.setLayoutManager(manager);
        Adapter image_adapter = new images_adapter(this,list_images);
        image_rv.setAdapter(image_adapter);
        image_rv.setItemAnimator(null);

        final LinearLayout search_layout = findViewById(R.id.search_llayout);
        final RelativeLayout recommend_layout = findViewById(R.id.bottom_rlayout);


        image_rv.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    // Scrolling up
                    search_layout.setVisibility(View.GONE);
                    recommend_layout.setVisibility(View.GONE);

                } else {
                    search_layout.setVisibility(View.VISIBLE);
                    recommend_layout.setVisibility(View.VISIBLE);
                    // Scrolling down
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                ((StaggeredGridLayoutManager)recyclerView.getLayoutManager()).invalidateSpanAssignments();

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_FLING) {
                    // Do something
                } else if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    // Do something
                } else {
                    // Do something
                }
            }
        });



    }

    public void onRecommendationClick(String search){
        Log.d("onRecommendationClick",search);

    }

    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {
                //------------------>>
                 HttpGet httppost = new HttpGet("https://www.google.com/search?hl=EN&tbm=isch&sxsrf=&q=black+phone+wallpaper+HD&oq=sunset&sclient=img");

               // HttpGet httppost = new HttpGet("https://pixabay.com/images/search/sky/?orientation=vertical");

                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);

                    Log.d("Server response", data);
                   // JSONObject jsono = new JSONObject(data);

                    return true;
                }else {
                    Log.d("Server response", "Failed to get server response" );

                }


            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;


//
//            String userAgent = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/53.0.2785.116 Safari/537.36";
//            String url = "https://www.google.com/search?site=imghp&tbm=isch&source=hp&q=kittens&gws_rd=cr";
//            String url2 = "https://pixabay.com/images/search/sky/?orientation=vertical";
//
//            List<String> resultUrls = new ArrayList<String>();
//
//            Document doc = null;
//            try {
//                doc = Jsoup.connect(url2).userAgent(userAgent).referrer("https://www.google.com/").get();
//
//                Elements elements = doc.select("div.rg_meta");
//                JSONObject jsonObject;
//
//                Log.d(TAG, "doInBackground: doc"+doc.toString());
//                for (Element element : elements) {
//                    Log.d(TAG, "doInBackground: for loop");
//                    if (element.childNodeSize() > 0) {
//                        Log.d(TAG, "doInBackground: element childNodeSize =" + element.childNodeSize());
//                        jsonObject = (JSONObject) new JSONObject().get(element.childNode(0).toString());
//                        resultUrls.add((String) jsonObject.get("ou"));
//                    }
//                }
//
//                Log.d(TAG, "doInBackground: resultUrls" +resultUrls.size());
//
//
//            } catch (IOException | JSONException e) {
//                Log.d(TAG, "doInBackground: error = "+e.toString());
//                e.printStackTrace();
//            }
//
//            return true;
        }

        protected void onPostExecute(Boolean result) {

        }
    }

}