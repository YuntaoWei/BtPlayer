package com.ghost.thunder.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ghost.thunder.demo.R;

/**
 * Created by yuntao.wei on 2018/8/15.
 * github:https://github.com/YuntaoWei
 * blog:http://blog.csdn.net/qq_17541215
 */

public class DownLoadListAdapter extends CursorAdapter {

    private LayoutInflater mInflater;

    public DownLoadListAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.download_list_item, null);
        ViewHolder vh = new ViewHolder();
        vh.tvFileName = (TextView)v.findViewById(R.id.filename);
        vh.tvProgress = (TextView)v.findViewById(R.id.progress);
        vh.tvSpeed = (TextView)v.findViewById(R.id.speed);
        vh.tvPath = (TextView)v.findViewById(R.id.path);

        v.setTag(vh);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder vh = (ViewHolder) view.getTag();
        vh.tvFileName.setText(cursor.getString(1));
        vh.tvProgress.setText(cursor.getInt(4));
        vh.tvSpeed.setText("0");
        vh.tvPath.setText(cursor.getString(2));
    }

    class ViewHolder {

        public TextView tvFileName;

        public TextView tvProgress;

        public TextView tvSpeed;

        public TextView tvPath;

    }

}
