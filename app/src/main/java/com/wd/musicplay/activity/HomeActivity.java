package com.wd.musicplay.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.wd.musicplay.R;
import com.wd.musicplay.adapter.MusicAdapter;
import com.wd.musicplay.service.MusicService;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends Activity {
    ListView musicList;//歌曲列表
    Button play;//播放
    Button pause;//暂停
    Button stop;//停止
    Button netWorkSearch;
    Button previousTrack;//上一首
    Button nextTrack;//下一首
    Button seach;//搜索本地歌曲
    TextView musicName;//歌曲名字
    List<File> fileList;
    MusicAdapter adapter;
    Context context = HomeActivity.this;
    int count = 0;
    Intent service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        initView();
    }
    //初始化控件
    private void initView(){
        fileList = new ArrayList<File>();
//        musicList = (ListView) findViewById(R.id.music_listview);
        play = (Button) findViewById(R.id.music_play);
//        stop = (Button) findViewById(R.id.music_stop);
        previousTrack = (Button) findViewById(R.id.music_previousTrack);
        nextTrack = (Button) findViewById(R.id.music_nextTrack);
        seach = (Button) findViewById(R.id.music_seach);
        musicName = (TextView) findViewById(R.id.music_name);
        service = new Intent(context, MusicService.class);
        netWorkSearch = (Button) findViewById(R.id.search_network_music);

        netWorkSearch.setOnClickListener(listener);
        play.setOnClickListener(listener);
//        stop.setOnClickListener(listener);
        previousTrack.setOnClickListener(listener);
        nextTrack.setOnClickListener(listener);
        seach.setOnClickListener(listener);
        adapter = new MusicAdapter(this,fileList);
//        musicList.setAdapter(adapter);
//        musicList.setOnItemClickListener(itemClickListener);
    }
    AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            File file = (File) parent.getItemAtPosition(position);
            Log.i("file", "onItemClick: "+file.getAbsolutePath());
            service.putExtra("music",file);
            startService(service);
        }
    };
    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.music_play:
                    break;
                case R.id.music_previousTrack:
                    break;
                case R.id.music_nextTrack:

                    break;
                case R.id.search_network_music:
                    Intent intent = new Intent(context,SearchMusicActivity.class);
                    startActivity(intent);
                    break;
                case R.id.music_seach:
                    Log.e("result", "onClick: 进入");
                    musicName.setText("正在搜索……");
                    new Thread(){
                        @Override
                        public void run() {
//                            checkStorage();
                        }
                    }.start();
                    break;
            }
        }
    };
    //检查SD卡挂载状态
    private void checkStorage(){
        File storageDirectory = null;
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            storageDirectory = Environment.getExternalStorageDirectory();
        }
        if(storageDirectory!=null){
            File[] files = storageDirectory.listFiles();
            for(File file:files){
                checkMusic(file);
            }
            handler.sendEmptyMessage(5);
        }
    }
    //检查本地歌曲，并将其添加到列表里面
    private void checkMusic(File musicFile){
        if(musicFile.isDirectory()){
            File[] files = musicFile.listFiles();
            if(files!=null) {
                for (File file : files) {
                    checkMusic(file);
                }
            }
        }else{
            String fileName = musicFile.getName();
            if(fileName.endsWith("mp3")){
                fileList.add(musicFile);
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("name", fileName);
                message.setData(bundle);
                message.what = 10;
                handler.sendMessage(message);
            }
        }
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==10){
                count++;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                musicName.setText(msg.getData().getString("name")+"搜索到"+count+"首歌曲");
                Log.e("result", "handleMessage: "+fileList.size() );
                adapter.notifyDataSetChanged();
            }else if(msg.what==5){
                adapter.notifyDataSetChanged();
                
            }
        }
    };
}
