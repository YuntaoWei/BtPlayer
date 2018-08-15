package com.ghost.thunder.download;

/**
 * Created by yuntao.wei on 2018/8/9.
 * github:https://github.com/YuntaoWei
 * blog:http://blog.csdn.net/qq_17541215
 */

public interface DownLoadProgressListener {

    void onProgressChange(String totalSize, String downloadedSize, String downSpeed);

    void onProgressChangeRealSize(long totalSize, long downloadedSize, long downSpeed);

    void onDonwloadEnd(String filePath);

    void onTaskStart(String fileName);

}
