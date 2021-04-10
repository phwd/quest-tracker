package com.oculus.panelapp.bugreporter;

import android.media.MediaMetadataRetriever;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MediaFile {
    private static MediaMetadataRetriever sMetadataRetriever = new MediaMetadataRetriever();
    private final String mDuration;
    private final String mFilePath;
    private final long mFileSize;
    private final boolean mIsVideo;

    public MediaFile(String str, int i, long j) {
        this.mFilePath = str;
        this.mIsVideo = i == 3;
        this.mFileSize = j;
        if (this.mIsVideo) {
            sMetadataRetriever.setDataSource(str);
            long parseLong = Long.parseLong(sMetadataRetriever.extractMetadata(9));
            long minutes = TimeUnit.MILLISECONDS.toMinutes(parseLong);
            this.mDuration = String.format(Locale.getDefault(), "%d:%02d", Long.valueOf(minutes), Long.valueOf(TimeUnit.MILLISECONDS.toSeconds(parseLong) - (60 * minutes)));
            return;
        }
        this.mDuration = "";
    }

    public String getFilePath() {
        return this.mFilePath;
    }

    public boolean isVideo() {
        return this.mIsVideo;
    }

    public String getDuration() {
        return this.mDuration;
    }

    public long getFileSize() {
        return this.mFileSize;
    }
}
