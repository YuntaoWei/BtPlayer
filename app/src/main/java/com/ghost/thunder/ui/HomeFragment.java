package com.ghost.thunder.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ghost.thunder.app.MainActivity;
import com.ghost.thunder.demo.R;
import com.ghost.thunder.download.DownLoadUtil;
import com.ghost.thunder.utils.LogPrinter;
import com.ghost.thunder.utils.UrlUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by wyt on 2018/8/9.
 */

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";

    private Unbinder unbinder;

    @BindView(R.id.et_url)
    EditText etUrl;

    @BindView(R.id.btn_download)
    Button btnDownload;

    @BindView(R.id.btn_select_file)
    Button btnSelectFile;

    DownLoadUtil downLoadUtil;

    MainActivity mainActivity;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainActivity = (MainActivity) context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_layout, null);
        unbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @OnClick(R.id.btn_download)
    public void onDonwloadButtonClicked() {
        String url = etUrl.getText().toString();
        LogPrinter.i(TAG, "onDonwloadButtonClicked : " + url);
        if(TextUtils.isEmpty(url)) {
            Toast.makeText(getContext(), R.string.url_is_null, Toast.LENGTH_SHORT).show();
            return;
        }

        if(!UrlUtils.isValidUrl(url)) {
            Toast.makeText(getContext(), R.string.url_format_error, Toast.LENGTH_SHORT).show();
            return;
        }

        checkDonwloadUtil();
        try {
            downLoadUtil.startDownLoad(url, mainActivity);
            LogPrinter.i(TAG, "url is valid");
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getContext(), R.string.task_error, Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.btn_select_file)
    public void startSelectTorrentFile() {

    }

    private void checkDonwloadUtil() {
        if(downLoadUtil == null)
            downLoadUtil = DownLoadUtil.getInstance(getContext().getApplicationContext());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

}
