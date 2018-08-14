package com.xunlei.downloadlib.parameter;

public class TorrentFileInfo {
    public int mFileIndex;
    public String mFileName;
    public long mFileSize;
    public int mRealIndex;
    public String mSubPath;
    public String playUrl;
    public String hash;

    @Override
    public String toString() {
        return "File index : " + mFileIndex + "\n"
                + "File name : " + mFileName + "\n"
                + "File size : " + mFileSize + "\n"
                + "Real index : " + mRealIndex + "\n"
                + "Sub path : " + mSubPath + "\n"
                + "Play url : " + playUrl + "\n"
                + "hash value : " + hash;
    }
}
