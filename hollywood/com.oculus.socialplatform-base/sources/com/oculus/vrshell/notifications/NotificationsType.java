package com.oculus.vrshell.notifications;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;

public enum NotificationsType implements Parcelable {
    ALL,
    APPS,
    DEVICE,
    DOWNLOADS,
    DOWNLOAD_IN_PROGRESS,
    PHONE,
    SOCIAL,
    STORE,
    SYSTEM;
    
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

    public static NotificationsType mapFromUri(String str) {
        switch (str.hashCode()) {
            case -1335157162:
                if (str.equals(NotificationUri.DEVICE)) {
                    return DEVICE;
                }
                break;
            case -897050771:
                if (str.equals("social")) {
                    return SOCIAL;
                }
                break;
            case -887328209:
                if (str.equals("system")) {
                    return SYSTEM;
                }
                break;
            case 96673:
                if (str.equals(NotificationUri.ALL)) {
                    return ALL;
                }
                break;
            case 3000946:
                if (str.equals("apps")) {
                    return APPS;
                }
                break;
            case 106642798:
                if (str.equals(NotificationUri.PHONE)) {
                    return PHONE;
                }
                break;
            case 109770977:
                if (str.equals(NotificationUri.STORE)) {
                    return STORE;
                }
                break;
            case 1312704747:
                if (str.equals(NotificationUri.DOWNLOADS)) {
                    return DOWNLOADS;
                }
                break;
        }
        Log.e(TAG, "Unknown notification uri");
        return null;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }
}
