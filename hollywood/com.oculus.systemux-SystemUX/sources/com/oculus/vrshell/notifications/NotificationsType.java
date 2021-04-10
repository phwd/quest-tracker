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

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name());
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public static NotificationsType mapFromUri(String str) {
        char c;
        switch (str.hashCode()) {
            case -1335157162:
                if (str.equals(NotificationUri.DEVICE)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -897050771:
                if (str.equals("social")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -887328209:
                if (str.equals(NotificationUri.SYSTEM)) {
                    c = 7;
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
            case 3000946:
                if (str.equals("apps")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 106642798:
                if (str.equals(NotificationUri.PHONE)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 109770977:
                if (str.equals(NotificationUri.STORE)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1312704747:
                if (str.equals(NotificationUri.DOWNLOADS)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                return ALL;
            case 1:
                return APPS;
            case 2:
                return DEVICE;
            case 3:
                return DOWNLOADS;
            case 4:
                return PHONE;
            case 5:
                return SOCIAL;
            case 6:
                return STORE;
            case 7:
                return SYSTEM;
            default:
                Log.e(TAG, "Unknown notification uri");
                return null;
        }
    }
}
