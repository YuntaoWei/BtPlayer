package com.ghost.thunder.utils;

import android.support.v4.app.Fragment;

import com.ghost.thunder.ui.DownLoadListFragment;
import com.ghost.thunder.ui.HomeFragment;
import com.ghost.thunder.ui.UserFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.WeakHashMap;

/**
 * Created by wyt on 2018/8/9.
 */

public class FragmentUtils {

    public static final int TYPE_HOME = 1;
    public static final int TYPE_FILE_LIST = 2;
    public static final int TYPE_USER = 3;

    private static WeakHashMap<String, Fragment> fragments = new WeakHashMap<>();

    public static Fragment getFragment(int type) {
        Fragment fragment = fragments.get("fragment:" + type);
        if(fragment != null) {
            return fragment;
        }

        switch (type) {

            case TYPE_HOME :
                fragment = new HomeFragment();
                break;

            case TYPE_FILE_LIST :
                fragment = new DownLoadListFragment();
                break;

            case TYPE_USER :
                fragment = new UserFragment();
                break;

        }

        if(fragment != null) {
            fragments.put("fragment:" + type, fragment);
            return fragment;
        }

        return null;
    }

    public static List<Fragment> getAllFragment() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(getFragment(TYPE_HOME));
        fragments.add(getFragment(TYPE_FILE_LIST));
        fragments.add(getFragment(TYPE_USER));
        return fragments;
    }

}
