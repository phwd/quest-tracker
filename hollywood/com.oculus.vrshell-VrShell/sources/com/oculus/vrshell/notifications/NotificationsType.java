package com.oculus.vrshell.notifications;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;

public enum NotificationsType implements Parcelable {
    ALL,
    SOCIAL,
    SYSTEM,
    DOWNLOADS,
    DOWNLOAD_IN_PROGRESS;
    
    public static final Parcelable.Creator<NotificationsType> CREATOR = new Parcelable.Creator<NotificationsType>() {
        /* class com.oculus.vrshell.notifications.NotificationsType.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public NotificationsType createFromParcel(Parcel parcel) {
            return NotificationsType.valueOf(parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        public NotificationsType[] newArray(int i) {
            return new NotificationsType[i];
        }
    };
    public static final String TAG = LoggingUtil.tag(NotificationsType.class);

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static NotificationsType mapFromUri(String str) {
        char c;
        switch (str.hashCode()) {
            case -897050771:
                if (str.equals("social")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -887328209:
                if (str.equals(NotificationUri.SYSTEM)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 96673:
                if (str.equals(NotificationUri.ALL)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1312704747:
                if (str.equals(NotificationUri.DOWNLOADS)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            return ALL;
        }
        if (c == 1) {
            return DOWNLOADS;
        }
        if (c == 2) {
            return SOCIAL;
        }
        if (c == 3) {
            return SYSTEM;
        }
        Log.e(TAG, "Unknown notification uri");
        return null;
    }
}
