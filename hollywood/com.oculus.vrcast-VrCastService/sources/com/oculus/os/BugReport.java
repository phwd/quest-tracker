package com.oculus.os;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public final class BugReport implements Parcelable {
    public static final Parcelable.Creator<BugReport> CREATOR = new Parcelable.Creator<BugReport>() {
        /* class com.oculus.os.BugReport.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public BugReport createFromParcel(Parcel parcel) {
            return new BugReport(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public BugReport[] newArray(int i) {
            return new BugReport[i];
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

    public int describeContents() {
        return 0;
    }

    BugReport() {
        this.earlyCollectLogs = false;
        this.attachLogs = false;
    }

    private BugReport(Parcel parcel) {
        readFromParcel(parcel);
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        if (this.earlyCollectLogs) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
        }
        if (this.attachLogs) {
            parcel.writeInt(1);
        } else {
            parcel.writeInt(0);
        }
        parcel.writeString(this.entrySource);
        parcel.writeString(this.sourceApp);
        parcel.writeString(this.appId);
        parcel.writeString(this.categoryId);
        parcel.writeString(this.sessionId);
        parcel.writeString(this.description);
        parcel.writeValue(this.screenshot);
        parcel.writeString(this.extraMedia);
    }

    public void readFromParcel(Parcel parcel) {
        this.id = parcel.readString();
        boolean z = true;
        this.earlyCollectLogs = parcel.readInt() != 0;
        if (parcel.readInt() == 0) {
            z = false;
        }
        this.attachLogs = z;
        this.entrySource = parcel.readString();
        this.sourceApp = parcel.readString();
        this.appId = parcel.readString();
        this.categoryId = parcel.readString();
        this.sessionId = parcel.readString();
        this.description = parcel.readString();
        this.screenshot = (Bitmap) parcel.readValue(ClassLoader.getSystemClassLoader());
        this.extraMedia = parcel.readString();
    }
}
