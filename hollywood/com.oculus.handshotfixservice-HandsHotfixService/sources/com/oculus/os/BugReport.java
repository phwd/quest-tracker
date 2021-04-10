package com.oculus.os;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;

public final class BugReport implements Parcelable {
    public static final Parcelable.Creator<BugReport> CREATOR = new Parcelable.Creator<BugReport>() {
        /* class com.oculus.os.BugReport.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public BugReport createFromParcel(Parcel in) {
            return new BugReport(in);
        }

        @Override // android.os.Parcelable.Creator
        public BugReport[] newArray(int size) {
            return new BugReport[size];
        }
    };
    private String appId;
    private boolean attachLogs;
    private String categoryId;
    private String description;
    private boolean earlyCollectLogs;
    private String entrySource;
    private String extraMedia;
    String id;
    private Bitmap screenshot;
    private String sessionId;
    private String sourceApp;

    BugReport() {
        this.earlyCollectLogs = false;
        this.attachLogs = false;
    }

    BugReport(String id2, boolean earlyCollectLogs2, boolean attachLogs2, String entrySource2, String sourceApp2, String appId2, String categoryId2, String sessionId2, String description2, Bitmap screenshot2, String extraMedia2) {
        this.id = id2;
        this.earlyCollectLogs = earlyCollectLogs2;
        this.attachLogs = attachLogs2;
        this.entrySource = entrySource2;
        this.sourceApp = sourceApp2;
        this.appId = appId2;
        this.categoryId = categoryId2;
        this.sessionId = sessionId2;
        this.description = description2;
        this.screenshot = screenshot2;
        this.extraMedia = extraMedia2;
    }

    public BugReportBuilder toBuilder() {
        List<String> extraMediaList = new ArrayList<>();
        String str = this.extraMedia;
        if (str != null && !str.isEmpty()) {
            for (String fileName : this.extraMedia.split(",")) {
                extraMediaList.add(fileName);
            }
        }
        return new BugReportBuilder().setEarlyCollectLogs(this.earlyCollectLogs).setAttachLogs(this.attachLogs).setEntrySource(this.entrySource).setSourceApp(this.sourceApp).setAppId(this.appId).setCategoryId(this.categoryId).setSessionId(this.sessionId).setDescription(this.description).setScreenshot(this.screenshot).setExtraMedia(extraMediaList);
    }

    private BugReport(Parcel in) {
        readFromParcel(in);
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.id);
        if (this.earlyCollectLogs) {
            out.writeInt(1);
        } else {
            out.writeInt(0);
        }
        if (this.attachLogs) {
            out.writeInt(1);
        } else {
            out.writeInt(0);
        }
        out.writeString(this.entrySource);
        out.writeString(this.sourceApp);
        out.writeString(this.appId);
        out.writeString(this.categoryId);
        out.writeString(this.sessionId);
        out.writeString(this.description);
        out.writeValue(this.screenshot);
        out.writeString(this.extraMedia);
    }

    public void readFromParcel(Parcel in) {
        this.id = in.readString();
        boolean z = true;
        this.earlyCollectLogs = in.readInt() != 0;
        if (in.readInt() == 0) {
            z = false;
        }
        this.attachLogs = z;
        this.entrySource = in.readString();
        this.sourceApp = in.readString();
        this.appId = in.readString();
        this.categoryId = in.readString();
        this.sessionId = in.readString();
        this.description = in.readString();
        this.screenshot = (Bitmap) in.readValue(ClassLoader.getSystemClassLoader());
        this.extraMedia = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    public String getId() {
        return this.id;
    }

    public boolean getEarlyCollectLogs() {
        return this.earlyCollectLogs;
    }

    public boolean getAttachLogs() {
        return this.attachLogs;
    }

    public String getEntrySource() {
        return this.entrySource;
    }

    public String getSourceApp() {
        return this.sourceApp;
    }

    public String getAppId() {
        return this.appId;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public String getDescription() {
        return this.description;
    }

    public Bitmap getScreenshot() {
        return this.screenshot;
    }

    public String getExtraMedia() {
        return this.extraMedia;
    }
}
