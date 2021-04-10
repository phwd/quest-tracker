package com.oculus.panelapp.anytimeui.v2.tablet.notifications.core;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.AbstractVRNotification;
import com.oculus.vrshell.notifications.NotificationsType;

public interface IBaseVRNotification<T> {
    Parcelable[] getAllActions();

    NotificationsType getCategory();

    String getFBID();

    long getId();

    String getLongText();

    String getNDID();

    String getNotificationType();

    long getPostedTimeSeconds();

    Parcelable getPrimaryAction();

    Drawable getPrimaryIcon(Context context);

    T getRaw();

    AbstractVRNotification.NotificationSeenState getSeenState();

    String getShortText();

    String getTitle();

    boolean isDismissable();

    void setSeenState(AbstractVRNotification.NotificationSeenState notificationSeenState);
}
