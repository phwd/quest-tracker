package com.oculus.panelapp.anytimeui.toast.policies;

import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import com.oculus.notifications.NotificationDataSetService;
import java.util.HashSet;

public class NotificationAlreadyToastedPolicy implements IPerNotifPassFailPolicy {
    private static final String TAG = NotificationAlreadyToastedPolicy.class.getSimpleName();
    private static volatile HashSet<String> mAlreadyToastedServerNotifs = new HashSet<>();
    private NotificationDataSetService mNotifDatasetService;
    private boolean mPassesRefreshGK;

    public NotificationAlreadyToastedPolicy(NotificationDataSetService notificationDataSetService, boolean z) {
        this.mNotifDatasetService = notificationDataSetService;
        this.mPassesRefreshGK = z;
    }

    @Override // com.oculus.panelapp.anytimeui.toast.policies.IPerNotifPassFailPolicy
    public boolean evaluate(StatusBarNotification statusBarNotification) {
        boolean z = this.mPassesRefreshGK;
        if (!z) {
            return true;
        }
        if (!z || !notifWasAlreadyToasted(statusBarNotification)) {
            addToToastedSet(statusBarNotification);
            return true;
        }
        this.mNotifDatasetService.removeNotification(statusBarNotification.getKey());
        Log.v(TAG, String.format("Notification %s was already toasted. Skipping toast", statusBarNotification.getNotification()));
        return false;
    }

    @Override // com.oculus.panelapp.anytimeui.toast.policies.IPerNotifPassFailPolicy
    public void destroy() {
        this.mNotifDatasetService = null;
    }

    private synchronized boolean notifWasAlreadyToasted(StatusBarNotification statusBarNotification) {
        Bundle bundle = statusBarNotification.getNotification().extras;
        if (!bundle.containsKey("oculus_notification_fbid")) {
            return false;
        }
        return mAlreadyToastedServerNotifs.contains(bundle.getString("oculus_notification_fbid"));
    }

    private synchronized void addToToastedSet(StatusBarNotification statusBarNotification) {
        Bundle bundle = statusBarNotification.getNotification().extras;
        if (bundle.containsKey("oculus_notification_fbid")) {
            String string = bundle.getString("oculus_notification_fbid");
            if (!TextUtils.isEmpty(string)) {
                Log.v(TAG, String.format("Adding notif with id %s to the already toasted set", bundle.getString("oculus_notification_fbid")));
                mAlreadyToastedServerNotifs.add(string);
            }
        }
    }
}
