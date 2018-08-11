package com.ghost.thunder.utils;

/**
 * Created by yuntao.wei on 2018/8/9.
 * github:https://github.com/YuntaoWei
 * blog:http://blog.csdn.net/qq_17541215
 */

public class UrlUtils {

    public static boolean isValidUrl(String url) {
        if (url.startsWith(UrlType.TYPE_THUNDER_URL))
            return true;
        else if(url.startsWith(UrlType.TYPE_MAGNET_URL))
            return true;
        else if(url.endsWith(UrlType.TYPE_TORRENT_FILE))
            return true;

        return false;
    }

}
