package com.oculus.panelapp.anytimeui.toast.policies;

import android.content.Context;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.oculus.common.fbaccountsmanager.MessengerVrAccountsContentProviderClient;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.NotificationsHelper;

public class MessengerIsLockedNotificationPolicy implements IPerNotifPassFailPolicy {
    private static final String TAG = "MessengerIsLockedNotificationPolicy";
    private Context mContext;

    public MessengerIsLockedNotificationPolicy(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override // com.oculus.panelapp.anytimeui.toast.policies.IPerNotifPassFailPolicy
    public boolean evaluate(StatusBarNotification statusBarNotification) {
        if (!NotificationsHelper.isFBMessengerNotification(statusBarNotification)) {
            return true;
        }
        try {
            boolean isMessengerAuthenticated = MessengerVrAccountsContentProviderClient.isMessengerAuthenticated(this.mContext);
            Log.d(TAG, String.format("Policy decision %s", Boolean.valueOf(isMessengerAuthenticated)));
            return isMessengerAuthenticated;
        } catch (Exception e) {
            Log.e(TAG, "Error retrieving authentication value", e);
            return false;
        }
    }

    @Override // com.oculus.panelapp.anytimeui.toast.policies.IPerNotifPassFailPolicy
    public void destroy() {
        this.mContext = null;
    }
}
