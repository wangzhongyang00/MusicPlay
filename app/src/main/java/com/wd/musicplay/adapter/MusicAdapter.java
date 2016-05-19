package com.wd.musicplay.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.wd.musicplay.R;

import java.io.File;
import java.util.List;

/**
 * Created by Administrator on 2016/5/9.
 */
public class MusicAdapter extends BaseAdapter {
    List<File> fileList;
    Context context;
    LayoutInflater inflater;

    public MusicAdapter(Context context, List<File> fileList) {
        this.context = context;
        this.fileList = fileList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return fileList == null ? 0 : fileList.size();
    }

    @Override
    public Object getItem(int position) {
        return fileList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = inflater.inflate(R.layout.music_content_layout, null);
        TextView musicName = (TextView) convertView.findViewById(R.id.music_list_name);

        Log.e("result", "getView: -------------");
        musicName.setText(fileList.get(position).getName());
        return convertView;
    }
}
