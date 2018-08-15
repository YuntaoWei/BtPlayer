package com.ghost.thunder.download;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.ghost.thunder.utils.LogPrinter;
import com.ghost.thunder.utils.StorageUtils;
import com.ghost.thunder.utils.UrlType;
import com.xunlei.downloadlib.XLTaskHelper;
import com.xunlei.downloadlib.parameter.TorrentInfo;
import com.xunlei.downloadlib.parameter.XLTaskInfo;

import java.io.File;

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

    private boolean dstFileIsTorrentFileTask;

    private Handler progressHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {

                case TASK_UPDATE_PROGRESS: {
                    if (progressListener != null) {
                        XLTaskInfo taskInfo = XLTaskHelper.instance(globalContext).getTaskInfo(taskID);
                        if (taskInfo.mFileSize == taskInfo.mDownloadSize) {
                            if (TextUtils.isEmpty(taskInfo.mFileName)) {
                                progressListener.onDonwloadEnd(preFile);
                            } else {
                                progressListener.onDonwloadEnd(savePath + "/" + taskInfo.mFileName);
                            }
                            return;
                        }

                        progressListener.onProgressChangeRealSize(taskInfo.mFileSize,
                                taskInfo.mDownloadSize, taskInfo.mDownloadSpeed);

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

        if (mInstance == null) {
            synchronized (DownLoadUtil.class) {

                if (mInstance == null)
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
        savePath = StorageUtils.getDefaultSavePath(globalContext);
        String fileName = null;
        if (UrlType.isTorrentUrl(url)) {
            TorrentInfo info = getTorrentInfo(url);
            savePath += "/" + info.mMultiFileBaseFolder;
            File f = new File(savePath);
            f.mkdirs();
            fileName = null;
        } else {
            fileName = getFileName(url);
        }

        preFile = savePath + (fileName == null ? "" : "/" + fileName);
        LogPrinter.i(TAG, "start download url : " + url + ", save path : " + savePath);

        if (UrlType.isThunderUrl(url) || UrlType.isHttpOrHttpsUrl(url) || UrlType.isFTPUrl(url)) {
            taskID = XLTaskHelper.instance(globalContext)
                    .addThunderTask(url, savePath, fileName);
        } else if (UrlType.isMagnetUrl(url)) {
            taskID = XLTaskHelper.instance(globalContext)
                    .addMagentTask(url, savePath, fileName);
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

        if (progressListener != null) {
            progressHandler.sendEmptyMessageDelayed(TASK_UPDATE_PROGRESS, 1000);
        }

    }

    public void stopTask() {
        XLTaskInfo taskInfo = XLTaskHelper.instance(globalContext).getTaskInfo(taskID);
        int status = taskInfo.mTaskStatus;
        LogPrinter.i(TAG, "stopTask -- > taskStatus : " + status);
        XLTaskHelper.instance(globalContext).stopTask(taskID);
    }

    public void getTaskInfo(String url) {
        XLTaskHelper xlTaskHelper = XLTaskHelper.instance(globalContext);

        if (UrlType.isTorrentUrl(url)) {
            TorrentInfo torrentInfo = xlTaskHelper.getTorrentInfo(url);
            LogPrinter.i(TAG, torrentInfo.toString());
        } else {
            String fileName = xlTaskHelper.getFileName(url);
            if (fileName.endsWith(".torrent"))
                dstFileIsTorrentFileTask = true;
            LogPrinter.i(TAG, fileName);
        }
    }

    private TorrentInfo getTorrentInfo(String url) {
        XLTaskHelper xlTaskHelper = XLTaskHelper.instance(globalContext);
        TorrentInfo torrentInfo = xlTaskHelper.getTorrentInfo(url);
        return torrentInfo;
    }

    private String getFileName(String url) {
        XLTaskHelper xlTaskHelper = XLTaskHelper.instance(globalContext);
        return xlTaskHelper.getFileName(url);
    }

}
