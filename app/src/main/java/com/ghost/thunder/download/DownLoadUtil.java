package com.ghost.thunder.download;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.ghost.thunder.utils.LogPrinter;
import com.ghost.thunder.utils.StorageUtils;
import com.ghost.thunder.utils.UrlType;
import com.xunlei.downloadlib.XLTaskHelper;
import com.xunlei.downloadlib.parameter.TorrentFileInfo;
import com.xunlei.downloadlib.parameter.TorrentInfo;
import com.xunlei.downloadlib.parameter.XLTaskInfo;

/**
 * Created by yuntao.wei on 2018/8/9.
 * github:https://github.com/YuntaoWei
 * blog:http://blog.csdn.net/qq_17541215
 */

public class DownLoadUtil {

    private static final String TAG = "DownLoadUtil";

    private static final int TASK_UPDATE_PROGRESS = 0x001;

    private static DownLoadUtil mInstance;

    private DownLoadProgressListener progressListener;

    private long taskID;

    private Context globalContext;

    private String savePath;

    private String preFile;

    private Handler progressHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case TASK_UPDATE_PROGRESS: {
                    if (progressListener != null) {
                        XLTaskInfo taskInfo = XLTaskHelper.instance(globalContext).getTaskInfo(taskID);
                        if (taskInfo.mFileSize == taskInfo.mDownloadSize) {
                            if(TextUtils.isEmpty(taskInfo.mFileName)) {
                                progressListener.onDonwloadEnd(preFile);
                            } else {
                                progressListener.onDonwloadEnd(savePath + "/" + taskInfo.mFileName);
                            }
                            return;
                        }
                        progressListener.onProgressChange(StorageUtils.convertFileSize(taskInfo.mFileSize)
                                , StorageUtils.convertFileSize(taskInfo.mDownloadSize)
                                , StorageUtils.convertFileSize(taskInfo.mDownloadSpeed));
                        progressHandler.sendEmptyMessageDelayed(TASK_UPDATE_PROGRESS, 1000);
                    }
                    break;
                }



            }
        }
    };

    private DownLoadUtil(Context ctx) {
        globalContext = ctx.getApplicationContext();
        init();
    }

    public static DownLoadUtil getInstance(Context ctx) {

        if(mInstance == null) {
            synchronized (DownLoadUtil.class) {

                if(mInstance == null)
                    mInstance = new DownLoadUtil(ctx);

            }
        }

        return mInstance;
    }

    private void init() {
        LogPrinter.i(TAG, "init");
        XLTaskHelper.init(globalContext);
    }

    public void startDownLoad(String url) throws Exception {
        savePath = StorageUtils.getDefaultSavePath(globalContext) + "/" + System.currentTimeMillis();
        String tmpFileName = "tmp_" + System.currentTimeMillis();

        preFile = savePath + "/" + tmpFileName;
        LogPrinter.i(TAG, "start download url : " + url + ", save path : " + savePath);

        if (UrlType.isThunderUrl(url) || UrlType.isHttpOrHttpsUrl(url) || UrlType.isFTPUrl(url)) {
            taskID = XLTaskHelper.instance(globalContext)
                    .addThunderTask(url, savePath, tmpFileName);
        } else if (UrlType.isMagnetUrl(url)) {
            taskID = XLTaskHelper.instance(globalContext)
                    .addMagentTask(url, savePath, tmpFileName);
        } else if (StorageUtils.isTorrentFile(url)) {
            taskID = XLTaskHelper.instance(globalContext)
                    .addTorrentTask(url, savePath, null);
        }

        LogPrinter.i(TAG, "download url taskID : " + taskID);
        updateProgress();
    }

    public void startDownLoad(String url, DownLoadProgressListener progressListener) throws Exception {
        this.progressListener = progressListener;
        startDownLoad(url);
    }

    public void updateProgress() {

        if(progressListener != null) {
            progressHandler.sendEmptyMessageDelayed(TASK_UPDATE_PROGRESS, 1000);
        }

    }

    public void getTaskInfo(String url) {
        XLTaskHelper xlTaskHelper = XLTaskHelper.instance(globalContext);

        if(UrlType.isTorrentUrl(url)) {
            TorrentInfo torrentInfo = xlTaskHelper.getTorrentInfo(url);
            LogPrinter.i(TAG, torrentInfo.toString());
        } else {
            String fileName = xlTaskHelper.getFileName(url);
            LogPrinter.i(TAG, fileName);
        }

    }

}
