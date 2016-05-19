package com.wd.musicplay.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.wd.musicplay.R;
import com.wd.musicplay.adapter.SearchMusicAdapter;
import com.wd.musicplay.demo.SearchGson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/5/18.
 */
public class SearchMusicActivity extends Activity {
    EditText searchContent;//搜索内容
    Button search;//搜索按钮
    ListView searchList;//搜索结果列表
    String TAG = "result";
    SearchMusicAdapter adapter;
    List<SearchGson.PageGson.Data> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_music_layout);
        initView();

    }
    private void initView(){
        searchContent = (EditText) findViewById(R.id.edit_search);
        search = (Button) findViewById(R.id.search);
        searchList = (ListView) findViewById(R.id.search_music);
        list = new ArrayList<>();
        adapter = new SearchMusicAdapter(this,list);
        searchList.setAdapter(adapter);
        search.setOnClickListener(listener);
    }
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.search:
                    musicRequest(searchContent.getText().toString());
                break;
            }
        }
    };
    public void musicRequest(String musicName) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://apis.baidu.com/geekery/music/query?s="+musicName).addHeader("apikey","c50789bc6e54b94370a1de4f5fd64082")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {


            }

            @Override
            public void onResponse(Response response) throws IOException {
                String result = response.body().string();
                try {
                    JSONObject object = new JSONObject(result);
                    Gson  gson = new Gson();
                    SearchGson searchGson = gson.fromJson(object.toString(), SearchGson.class);
                    List<SearchGson.PageGson.Data> data = searchGson.getData().getData();
                    list.clear();
                    for(SearchGson.PageGson.Data music:data){
                        list.add(music);
                    }
                    handler.sendEmptyMessage(10);
                    Log.i(TAG, "onResponse: " + searchGson.getData().getData().get(0).getFilename());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if (msg.what==10){
                adapter.notifyDataSetChanged();
            }
        }
    };
}
