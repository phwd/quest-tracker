package com.oculus.os;

import android.graphics.Bitmap;
import android.text.TextUtils;
import java.util.List;

public class BugReportBuilder {
    private static final String TAG = "BugReportBuilder";
    private String appId;
    private boolean attachLogs;
    private String categoryId;
    private String description;
    private boolean earlyCollectLogs;
    private String entrySource;
    private String extraMedia;
    private String id;
    private Bitmap screenshot;
    private String sessionId;
    private String sourceApp;

    public BugReportBuilder setId(String id2) {
        this.id = id2;
        return this;
    }

    public BugReportBuilder setEarlyCollectLogs(boolean earlyCollectLogs2) {
        this.earlyCollectLogs = earlyCollectLogs2;
        return this;
    }

    public BugReportBuilder setAttachLogs(boolean attachLogs2) {
        this.attachLogs = attachLogs2;
        return this;
    }

    public BugReportBuilder setEntrySource(String entrySource2) {
        this.entrySource = entrySource2;
        return this;
    }

    public BugReportBuilder setSourceApp(String sourceApp2) {
        this.sourceApp = sourceApp2;
        return this;
    }

    public BugReportBuilder setAppId(String appId2) {
        this.appId = appId2;
        return this;
    }

    public BugReportBuilder setCategoryId(String categoryId2) {
        this.categoryId = categoryId2;
        return this;
    }

    public BugReportBuilder setSessionId(String sessionId2) {
        this.sessionId = sessionId2;
        return this;
    }

    public BugReportBuilder setDescription(String description2) {
        this.description = description2;
        return this;
    }

    public BugReportBuilder setScreenshot(Bitmap screenshot2) {
        Bitmap ashmemBitmap;
        this.screenshot = null;
        if (!(screenshot2 == null || (ashmemBitmap = screenshot2.createAshmemBitmap()) == null)) {
            this.screenshot = ashmemBitmap;
        }
        return this;
    }

    public BugReportBuilder setExtraMedia(List<String> extraMediaList) {
        this.extraMedia = "";
        if (extraMediaList != null) {
            this.extraMedia = TextUtils.join(",", extraMediaList);
        }
        return this;
    }

    public BugReport build() {
        return new BugReport(this.id, this.earlyCollectLogs, this.attachLogs, this.entrySource, this.sourceApp, this.appId, this.categoryId, this.sessionId, this.description, this.screenshot, this.extraMedia);
    }
}
