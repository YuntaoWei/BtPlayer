package com.ghost.thunder.utils;

/**
 * Created by yuntao.wei on 2018/8/9.
 * github:https://github.com/YuntaoWei
 * blog:http://blog.csdn.net/qq_17541215
 */

public class UrlType {

    public static final String TYPE_THUNDER_URL = "thunder://";
    public static final String TYPE_THUNDER_URL_1 = "ed2k://";


    public static final String TYPE_MAGNET_URL = "magnet:?";

    public static final String TYPE_TORRENT_FILE = ".torrent";

    public static final String TYPE_FTP_URL = "ftp://";

    public static final String TYPE_HTTP_URL = "http://";

    public static final String TYPE_HTTPS_URL = "https://";

    public static boolean isMagnetUrl(String url) {
        return url.startsWith(TYPE_MAGNET_URL);
    }

    public static boolean isThunderUrl(String url) {
        return url.startsWith(TYPE_THUNDER_URL) || url.startsWith(TYPE_THUNDER_URL_1);
    }

    public static boolean isTorrentUrl(String url) {
        return url.endsWith(TYPE_TORRENT_FILE);
    }

    public static boolean isFTPUrl(String url) {
        return url.startsWith(TYPE_FTP_URL);
    }

    public static boolean isHttpOrHttpsUrl(String url) {
        return url.startsWith(TYPE_HTTP_URL) || url.startsWith(TYPE_HTTPS_URL);
    }

}
