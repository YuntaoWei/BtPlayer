package com.ghost.thunder.app;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.Button;

import com.ghost.thunder.adapter.MyPageAdapter;
import com.ghost.thunder.demo.R;
import com.ghost.thunder.download.DownLoadProgressListener;
import com.ghost.thunder.utils.FragmentUtils;
import com.ghost.thunder.utils.LogPrinter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by yuntao.wei on 2018/8/9.
 * github:https://github.com/YuntaoWei
 * blog:http://blog.csdn.net/qq_17541215
 */

public class BaseActivity extends PermissionActivity implements DownLoadProgressListener {

    static final String TAG = "BaseActivity";

    @BindView(R.id.top_tool)
    Toolbar topTool;

    @BindView(R.id.view_pager)
    ViewPager viewPager;

    @BindView(R.id.btn_home)
    Button btnHome;

    @BindView(R.id.btn_download_list)
    Button btnList;

    @BindView(R.id.btn_user)
    Button btnUser;

    int currentType;
    MyPageAdapter pageAdapter;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(hasPermission) {
            init();
            initView();
        }
    }

    private void init() {
        unbinder = ButterKnife.bind(this);
    }

    private void initView() {
        setSupportActionBar(topTool);

        pageAdapter = new MyPageAdapter(getSupportFragmentManager());
        pageAdapter.setFragment(FragmentUtils.getAllFragment());
        viewPager.setAdapter(pageAdapter);
        viewPager.setCurrentItem(0);
    }

    protected void changeButtonStyle(int type) {
        switch (type) {

            case FragmentUtils.TYPE_HOME :

                break;

            case FragmentUtils.TYPE_FILE_LIST :

                break;

            case FragmentUtils.TYPE_USER :

                break;

        }
    }

    @Override
    public void onProgressChange(String totalSize, String downloadedSize, String downSpeed) {
        LogPrinter.i(TAG, "onProgressChange : " + totalSize + "  " + downloadedSize + "  " + downSpeed);
    }

    @Override
    public void onDonwloadEnd(String filePath) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    void onGotPermissionSuccess() {
        init();
        initView();
    }

    void onGotPermissionFailure() {
        showPermissionTips();
    }

}
