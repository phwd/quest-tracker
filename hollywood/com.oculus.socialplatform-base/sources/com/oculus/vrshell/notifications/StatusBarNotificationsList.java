package com.oculus.vrshell.notifications;

import android.os.Parcel;
import android.os.Parcelable;
import android.service.notification.StatusBarNotification;
import java.util.List;

public class StatusBarNotificationsList implements Parcelable {
    public static final Parcelable.Creator<StatusBarNotificationsList> CREATOR = new Parcelable.Creator<StatusBarNotificationsList>() {
        /* class com.oculus.vrshell.notifications.StatusBarNotificationsList.AnonymousClass1 */

        @Override // android.os.Parcelable.Creator
        public StatusBarNotificationsList createFromParcel(Parcel parcel) {
            return new StatusBarNotificationsList(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public StatusBarNotificationsList[] newArray(int i) {
            return new StatusBarNotificationsList[i];
        }
    };
    public List<StatusBarNotification> notifications;
    public NotificationsType type;

    public int describeContents() {
        return 0;
    }

    public List<StatusBarNotification> getNotifications() {
        return this.notifications;
    }

    public NotificationsType getType() {
        return this.type;
    }

    public void readFromParcel(Parcel parcel) {
        this.type = NotificationsType.CREATOR.createFromParcel(parcel);
        this.notifications = parcel.createTypedArrayList(StatusBarNotification.CREATOR);
    }

    public void writeToParcel(Parcel parcel, int i) {
        this.type.writeToParcel(parcel, i);
        parcel.writeTypedList(this.notifications);
    }

    public StatusBarNotificationsList(Parcel parcel) {
        readFromParcel(parcel);
    }

    public StatusBarNotificationsList(NotificationsType notificationsType, List<StatusBarNotification> list) {
        this.type = notificationsType;
        this.notifications = list;
    }
}
