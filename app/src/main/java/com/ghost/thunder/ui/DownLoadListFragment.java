package com.ghost.thunder.ui;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.ghost.thunder.adapter.DownLoadListAdapter;
import com.ghost.thunder.app.MainActivity;
import com.ghost.thunder.data.DownLoadDBUtil;
import com.ghost.thunder.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wyt on 2018/8/9.
 */

public class DownLoadListFragment extends Fragment {

    private DownLoadDBUtil downLoadDBUtil;

    private Unbinder unbinder;

    MainActivity mainActivity;

    @BindView(R.id.download_list)
    ListView downloadList;

    DownLoadListAdapter mAdapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    private void init() {
        downLoadDBUtil = DownLoadDBUtil.getInstance(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.download_fragment_layout, null);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        Cursor c = downLoadDBUtil.queryAll();
        mAdapter = new DownLoadListAdapter(getContext(), c, false);
        downloadList.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        release();
    }

    private void release() {
        downLoadDBUtil.release();
    }
}
