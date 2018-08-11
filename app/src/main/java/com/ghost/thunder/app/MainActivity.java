package com.ghost.thunder.app;

import android.os.Bundle;

import com.ghost.thunder.demo.R;
import com.ghost.thunder.utils.FragmentUtils;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.btn_home)
    public void onHomeButtonClicked() {
        if(currentType == FragmentUtils.TYPE_HOME)
            return;

        changePage(FragmentUtils.TYPE_HOME);
    }

    @OnClick(R.id.btn_download_list)
    public void onDownloadListButtonClicked() {
        if(currentType == FragmentUtils.TYPE_FILE_LIST)
            return;

        changePage(FragmentUtils.TYPE_FILE_LIST);
    }

    @OnClick(R.id.btn_user)
    public void onUserButtonClicked() {
        if(currentType == FragmentUtils.TYPE_USER)
            return;

        changePage(FragmentUtils.TYPE_USER);
    }

    public void changePage(int type) {
        currentType = type;
        FragmentUtils.getFragment(type);

        changeButtonStyle(type);
    }

}
