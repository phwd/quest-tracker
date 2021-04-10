package com.oculus.panelapp.anytimeui.toast;

import android.content.Context;
import android.os.Handler;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.notifications.NotificationDataSetService;
import com.oculus.notifications.NotificationHelper;
import com.oculus.panelapp.anytimeui.toast.policies.IPerNotifPassFailPolicy;
import com.oculus.panelapp.anytimeui.toast.policies.MessengerIsLockedNotificationPolicy;
import java.util.ArrayList;
import java.util.Iterator;

public final class ToastController {
    private static final String TAG = LoggingUtil.tag(ToastController.class);
    private static final String WIFI_DIALOG_PACKAGE = "com.android.settings";
    private Context mContext;
    private final Handler mHandler;
    private final NotificationDataSetService mNotificationDataSetService;
    private final NotificationSoundManager mNotificationSoundManager;
    private ArrayList<IPerNotifPassFailPolicy> mPassFailPolicies = new ArrayList<>();
    private final Runnable mToastCompletionRunnable;
    private IToastDisplayer mToastDisplayer;

    public interface IToastDisplayer {
        void hideNotification();

        void hideNotificationIfDisplaying(String str);

        void showNotification(StatusBarNotification statusBarNotification);
    }

    public ToastController(Context context, IToastDisplayer iToastDisplayer, float f) {
        this.mContext = context;
        this.mNotificationSoundManager = new NotificationSoundManager(context);
        this.mToastDisplayer = iToastDisplayer;
        this.mNotificationDataSetService = NotificationDataSetService.getInstance();
        addPassFailPolicy(new MessengerIsLockedNotificationPolicy(this.mContext));
        this.mHandler = new Handler();
        this.mToastCompletionRunnable = new Runnable() {
            /* class com.oculus.panelapp.anytimeui.toast.ToastController.AnonymousClass1 */

            public void run() {
                ToastController.this.onToastCompletion();
            }
        };
    }

    public void destroy() {
        this.mContext = null;
        this.mHandler.removeCallbacks(this.mToastCompletionRunnable);
        destroyPolicies();
        this.mPassFailPolicies.clear();
        this.mToastDisplayer = null;
    }

    public void addPassFailPolicy(IPerNotifPassFailPolicy iPerNotifPassFailPolicy) {
        this.mPassFailPolicies.add(iPerNotifPassFailPolicy);
    }

    public void removePassFailPolicy(IPerNotifPassFailPolicy iPerNotifPassFailPolicy) {
        this.mPassFailPolicies.remove(iPerNotifPassFailPolicy);
    }

    public void onReceiveNotificationToToast(String str) {
        Log.d(TAG, "onReceiveNotificationToToast()");
        StatusBarNotification statusBarNotification = getStatusBarNotification(str);
        if (statusBarNotification == null) {
            Log.e(TAG, "Missing attached status bar notification.");
        } else if (this.mToastDisplayer == null) {
            Log.d(TAG, "Attempting to toast notification after destroy.");
        } else if (evaluatePassFailPolicies(statusBarNotification)) {
            Log.v(TAG, "All Toast Pass/Fail policies passed");
            if (isNotificationExpired(statusBarNotification)) {
                Log.d(TAG, "Notification has expired");
                return;
            }
            this.mHandler.removeCallbacks(this.mToastCompletionRunnable);
            if (!wasImmediatelyRemoved(statusBarNotification)) {
                Log.e(TAG, "Failed to schedule toast removal.");
                onToastCompletion();
                return;
            }
            this.mNotificationSoundManager.playNotificationSoundIfEligibleAndThen(statusBarNotification, new OnNotificationSoundReadyStep(statusBarNotification) {
                /* class com.oculus.panelapp.anytimeui.toast.$$Lambda$ToastController$ZK5zsUS7cm8VwBojn4j9nuTpNYA */
                private final /* synthetic */ StatusBarNotification f$1;

                {
                    this.f$1 = r2;
                }

                @Override // com.oculus.panelapp.anytimeui.toast.OnNotificationSoundReadyStep
                public final void onNotificationSoundReadyStep() {
                    ToastController.this.lambda$onReceiveNotificationToToast$5$ToastController(this.f$1);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onReceiveNotificationToToast$5$ToastController(StatusBarNotification statusBarNotification) {
        this.mToastDisplayer.showNotification(statusBarNotification);
    }

    public void onReceiveNotificationToDismiss(String str) {
        Log.d(TAG, "onReceiveNotificationToDismiss()");
        if (str == null) {
            Log.e(TAG, "Missing attached status bar notification key.");
        } else if (this.mToastDisplayer == null) {
            Log.d(TAG, "Attempting to dismiss notification after destroy.");
        } else {
            this.mNotificationSoundManager.onNotificationDismissed(str);
            this.mToastDisplayer.hideNotificationIfDisplaying(str);
        }
    }

    private synchronized boolean evaluatePassFailPolicies(StatusBarNotification statusBarNotification) {
        Iterator<IPerNotifPassFailPolicy> it = this.mPassFailPolicies.iterator();
        while (it.hasNext()) {
            IPerNotifPassFailPolicy next = it.next();
            if (!next.evaluate(statusBarNotification)) {
                Log.d(TAG, String.format("Policy %s failed for notification %s", next.getClass().getSimpleName(), statusBarNotification.getNotification()));
                return false;
            }
        }
        return true;
    }

    public boolean isNotificationExpired(StatusBarNotification statusBarNotification) {
        return NotificationHelper.getTimeOfPostMs(statusBarNotification) + NotificationHelper.getNotificationDisplayDurationMs(statusBarNotification) <= System.currentTimeMillis();
    }

    public boolean wasImmediatelyRemoved(StatusBarNotification statusBarNotification) {
        this.mHandler.removeCallbacks(this.mToastCompletionRunnable);
        return this.mHandler.postDelayed(this.mToastCompletionRunnable, (NotificationHelper.getTimeOfPostMs(statusBarNotification) + NotificationHelper.getNotificationDisplayDurationMs(statusBarNotification)) - System.currentTimeMillis());
    }

    private void destroyPolicies() {
        this.mPassFailPolicies.forEach($$Lambda$ToastController$tlV33wpxV4yu2tV8SvaxWGQFwE.INSTANCE);
    }

    public StatusBarNotification getStatusBarNotification(String str) {
        if (str == null) {
            return null;
        }
        for (StatusBarNotification statusBarNotification : this.mNotificationDataSetService.getAllNotifications()) {
            if (str.equals(statusBarNotification.getKey())) {
                return statusBarNotification;
            }
        }
        Log.e(TAG, "Notification with key not found.");
        return null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void onToastCompletion() {
        if (this.mToastDisplayer == null) {
            Log.d(TAG, "Attempting to complete notification after destroy.");
            return;
        }
        this.mNotificationSoundManager.onNotificationCompleted();
        this.mToastDisplayer.hideNotification();
    }
}
