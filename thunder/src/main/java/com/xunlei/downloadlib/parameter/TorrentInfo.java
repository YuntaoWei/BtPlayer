package com.xunlei.downloadlib.parameter;

public class TorrentInfo {
    public int mFileCount;
    public String mInfoHash;
    public boolean mIsMultiFiles;
    public String mMultiFileBaseFolder;
    public TorrentFileInfo[] mSubFileInfo;

    @Override
    public String toString() {
        return "file count : " + mFileCount + "\n"
                + "Info Hash : " + mInfoHash + "\n"
                + "is muti files : " + mIsMultiFiles + "\n"
                + "muti file base folder : " + mMultiFileBaseFolder + "\n";
    }
}
