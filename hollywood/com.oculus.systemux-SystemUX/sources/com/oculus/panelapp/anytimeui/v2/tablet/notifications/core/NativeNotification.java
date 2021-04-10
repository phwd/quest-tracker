package com.oculus.panelapp.anytimeui.v2.tablet.notifications.core;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Parcelable;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import androidx.databinding.Bindable;
import com.oculus.common.notifications.R;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.NotificationsHelper;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.AbstractVRNotification;
import com.oculus.vrshell.notifications.NotificationsType;
import com.oculus.vrshell.util.NotificationsActionsUtil;
import com.oculus.vrshell.util.PackageUtil;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;

public class NativeNotification<T extends StatusBarNotification> extends AbstractVRNotification implements IBaseVRNotification {
    public static final String MESSENGER_NOTIFICATION_TYPE = "oculus_vr_messaging_new_message";
    public static final String NOTIF_CATEGORY_FIELD = "oculus_notification_category";
    public static final String OCULUS_CHATS_NOTIFICATION_TYPE = "oculus_messaging_new_message";
    public static final String PHONE_NOTIFICATION_TYPE = "phone_notification";
    private static String TAG = "NativeNotification";
    private static HashSet<String> mSystemNotifs = new HashSet<>(Arrays.asList(PackageUtil.PACKAGE_NAME_NOTIFICATION_PROXY, "com.oculus.vrshell|guardian", "com.oculus.vrshell|hands", "com.oculus.vrshell|storage", "com.oculus.vrshell.home|system", "com.oculus.horizon|vr_notification"));
    private long mPostedTime;
    private StatusBarNotification mRaw;

    public NativeNotification(StatusBarNotification statusBarNotification) {
        this.mRaw = statusBarNotification;
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public long getId() {
        return (long) this.mRaw.getId();
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public String getFBID() {
        try {
            return this.mRaw.getNotification().extras.getString("oculus_notification_fbid");
        } catch (NullPointerException e) {
            Log.e(TAG, "No value for fbid field", e);
            return "";
        }
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public String getNDID() {
        try {
            return this.mRaw.getNotification().extras.getString("oculus_notification_ndid");
        } catch (NullPointerException e) {
            Log.e(TAG, "No value for fbid field", e);
            return "";
        }
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public String getNotificationType() {
        return NotificationsHelper.getNotificationType(this.mRaw);
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public String getTitle() {
        return this.mRaw.getNotification().extras.getString(NotificationCompat.EXTRA_TITLE, "");
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public NotificationsType getCategory() {
        return getCategoryV2();
    }

    private NotificationsType getCategoryV2() {
        if ((isDownloadInProgressNotification(this.mRaw) || isDownloadNotification(this.mRaw)) && !isFBUploadId(this.mRaw)) {
            return NotificationsType.APPS;
        }
        if (isPhoneNotif(getNotificationType())) {
            return NotificationsType.PHONE;
        }
        if (isSystemNotif(this.mRaw)) {
            return NotificationsType.DEVICE;
        }
        NotificationsType notificationsType = null;
        try {
            String string = this.mRaw.getNotification().extras.getString(NOTIF_CATEGORY_FIELD);
            if (string == null) {
                Log.d(TAG, "oculus_notification_category is missing. Defaulting to device category");
                return NotificationsType.DEVICE;
            }
            notificationsType = NotificationsType.mapFromUri(string);
            return notificationsType != null ? notificationsType : NotificationsType.DEVICE;
        } catch (NullPointerException e) {
            Log.e(TAG, "Exception parsing value for category field", e);
        }
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public boolean isDismissable() {
        return isDownloadInProgressNotification(this.mRaw) || isSystemNotif(this.mRaw);
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public String getShortText() {
        return getTitle();
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public String getLongText() {
        return NotificationsActionsUtil.getNotificationDescription(this.mRaw.getNotification());
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public Drawable getPrimaryIcon(Context context) {
        Icon largeIcon = this.mRaw.getNotification().getLargeIcon();
        if (largeIcon == null) {
            largeIcon = Icon.createWithResource(context.getPackageName(), R.drawable.ic_notifications_default);
        }
        return getRoundDrawableFromBitmap(context, ((BitmapDrawable) largeIcon.loadDrawable(context)).getBitmap());
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public long getPostedTimeSeconds() {
        if (this.mPostedTime == 0) {
            this.mPostedTime = this.mRaw.getNotification().when / 1000;
        }
        return this.mPostedTime;
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public Parcelable getPrimaryAction() {
        if (this.mRaw.getNotification().contentIntent != null) {
            return this.mRaw.getNotification().contentIntent;
        }
        if (this.mRaw.getNotification().actions == null || this.mRaw.getNotification().actions.length <= 0) {
            return null;
        }
        return this.mRaw.getNotification().actions[0].actionIntent;
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public Parcelable[] getAllActions() {
        Notification.Action[] actionArr = this.mRaw.getNotification().actions;
        PendingIntent pendingIntent = this.mRaw.getNotification().contentIntent;
        int length = (actionArr == null || actionArr.length <= 0) ? 0 : actionArr.length + 0;
        if (pendingIntent != null) {
            length++;
        }
        Parcelable[] parcelableArr = null;
        if (length > 0) {
            parcelableArr = new Parcelable[length];
            for (int i = 0; i < actionArr.length; i++) {
                parcelableArr[i] = actionArr[i];
            }
        }
        if (pendingIntent != null) {
            parcelableArr[length - 1] = pendingIntent;
        }
        return parcelableArr;
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    @Bindable
    public AbstractVRNotification.NotificationSeenState getSeenState() {
        if (this.mSeenState == null) {
            this.mSeenState = AbstractVRNotification.NotificationSeenState.UNSEEN_AND_UNREAD;
        }
        return this.mSeenState;
    }

    @Override // com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.IBaseVRNotification
    public StatusBarNotification getRaw() {
        return this.mRaw;
    }

    public boolean equals(Object obj) {
        if (obj instanceof NativeNotification) {
            return this.mRaw.getKey().equals(((NativeNotification) obj).getRaw().getKey());
        }
        return false;
    }

    public String toString() {
        return String.format(Locale.ROOT, "NativeNotification{id: %d, category: %s, type: %s}", Long.valueOf(getId()), getCategory(), getNotificationType());
    }

    public int hashCode() {
        return Objects.hash(this.mRaw.getKey());
    }

    public boolean contentsAreUpdated(NativeNotification nativeNotification) {
        return contentsAreUpdated(this, nativeNotification);
    }

    public static boolean contentsAreUpdated(NativeNotification nativeNotification, NativeNotification nativeNotification2) {
        return nativeNotification.getRaw().getKey().equals(nativeNotification2.getRaw().getKey()) && (!nativeNotification.getTitle().equals(nativeNotification2.getTitle()) || !nativeNotification.getLongText().equals(nativeNotification2.getLongText()));
    }

    public static boolean isDownloadNotification(StatusBarNotification statusBarNotification) {
        return statusBarNotification.getNotification().extras.getBoolean("is_download");
    }

    public boolean isDownloadNotification() {
        return isDownloadNotification(this.mRaw);
    }

    public boolean isDownloadInProgressNotification() {
        return isDownloadInProgressNotification(this.mRaw);
    }

    public boolean isMessengerNotification() {
        return MESSENGER_NOTIFICATION_TYPE.equals(getNotificationType());
    }

    public boolean isOculusChatsNotification() {
        return OCULUS_CHATS_NOTIFICATION_TYPE.equals(getNotificationType());
    }

    public boolean isAnyMessagingNotification() {
        return isMessengerNotification() || isOculusChatsNotification();
    }

    public static boolean isDownloadInProgressNotification(StatusBarNotification statusBarNotification) {
        return NotificationsActionsUtil.getNotificationIsDownloadInProgress(statusBarNotification.getNotification());
    }

    public static boolean isFBUploadInProgressNotification(NativeNotification nativeNotification) {
        return (nativeNotification.getCategory().equals(NotificationsType.SOCIAL) || nativeNotification.getCategory().equals(NotificationsType.DEVICE) || nativeNotification.isDownloadInProgressNotification()) && isFBUploadId(nativeNotification.getRaw()) && nativeNotification.getLongText().contains("%");
    }

    public static boolean isFBUploadId(StatusBarNotification statusBarNotification) {
        return statusBarNotification.getId() == 500000;
    }

    public static boolean isPhoneNotif(String str) {
        return PHONE_NOTIFICATION_TYPE.equals(str);
    }

    public static boolean isSystemNotif(StatusBarNotification statusBarNotification) {
        String packageName = statusBarNotification.getPackageName();
        String tag = statusBarNotification.getTag();
        int id = statusBarNotification.getId();
        int i = statusBarNotification.getNotification().priority;
        if (!mSystemNotifs.contains(packageName)) {
            if (!mSystemNotifs.contains(String.format(Locale.getDefault(), "%s|%s", packageName, tag))) {
                if (!mSystemNotifs.contains(String.format(Locale.getDefault(), "%s|%s|%d", packageName, tag, Integer.valueOf(id)))) {
                    if (!mSystemNotifs.contains(String.format(Locale.getDefault(), "%s|%s|%d|%d", packageName, tag, Integer.valueOf(id), Integer.valueOf(i)))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
