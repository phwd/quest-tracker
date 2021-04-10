package com.oculus.panelapp.anytimeui.toast.view;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.PathInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;
import com.oculus.assistant.service.api.panel.AssistantComponentContract;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.notifications.NotificationConstants;
import com.oculus.notifications.NotificationHelper;
import com.oculus.notifications.view.NotificationView;
import com.oculus.os.ActivityManagerUtils;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import com.oculus.panelapp.anytimeui.v2.bar.BarView;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.NotificationsHelper;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.AbstractVRNotification;
import com.oculus.panelapp.anytimeui.v2.tablet.notifications.core.NotificationDatasetProxy;
import com.oculus.vrshell.util.PackageUtil;
import javax.annotation.Nullable;

public final class ToastLayerView extends LinearLayout implements View.OnClickListener {
    private static long ANIMATION_DURATION_FAST_2_MS = 200;
    private static final String TAG = LoggingUtil.tag(ToastLayerView.class);
    private IActionURIHandler mActionURIHandler;
    private BarView mBarView;
    private StatusBarNotification mCurrentNotification;
    private final UnifiedTelemetryLogger mUnifiedTelemetryLogger;

    public interface IActionURIHandler {
        void navigateToToastURI(String str);
    }

    public ToastLayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mUnifiedTelemetryLogger = UnifiedTelemetryLogger.getInstance(context);
        setOnClickListener(this);
    }

    public void setActionURIHandler(IActionURIHandler iActionURIHandler) {
        this.mActionURIHandler = iActionURIHandler;
    }

    public void showNotification(StatusBarNotification statusBarNotification, @Nullable BarView barView) {
        String str = TAG;
        Log.d(str, "showNotification(" + statusBarNotification.getId() + ")");
        if (barView != null) {
            this.mBarView = barView;
        }
        boolean z = this.mCurrentNotification == null;
        Context context = getContext();
        boolean isSystemNotification = NotificationHelper.isSystemNotification(context, statusBarNotification);
        String foregroundApp = new ActivityManagerUtils().getForegroundApp(context);
        View viewForNotification = NotificationView.getViewForNotification(context, statusBarNotification, isSystemNotification, true, PackageUtil.isShellApp(foregroundApp), PackageUtil.PACKAGE_NAME_GUARDIAN.equals(foregroundApp));
        if (viewForNotification != null) {
            this.mCurrentNotification = statusBarNotification;
            removeAllViews();
            addView(viewForNotification);
            if (z) {
                viewForNotification.startAnimation(getAnimation(true));
            }
        }
    }

    public void hideNotification() {
        removeAllViews();
        this.mBarView = null;
        this.mCurrentNotification = null;
    }

    public boolean hideNotificationIfDisplaying(String str) {
        String str2 = TAG;
        Log.d(str2, "hideNotificationIfDisplaying(" + str + ")");
        StatusBarNotification statusBarNotification = this.mCurrentNotification;
        if (statusBarNotification == null) {
            Log.d(TAG, "No current notification being displayed.");
            return false;
        } else if (!statusBarNotification.getKey().equals(str)) {
            Log.d(TAG, "Requested notification to be hidden is not being displayed.");
            return false;
        } else {
            hideNotification();
            return true;
        }
    }

    public void onClick(View view) {
        if (this.mCurrentNotification == null) {
            Log.d(TAG, "onClick(null)");
            return;
        }
        String str = TAG;
        Log.d(str, "onClick(" + this.mCurrentNotification.getId() + ")");
        if (NotificationConstants.notificationHasClickAction(this.mCurrentNotification)) {
            Notification notification = this.mCurrentNotification.getNotification();
            NotificationDatasetProxy.getInstance(getContext(), null, null).updateVrNotifFromStatusBarNotif(this.mCurrentNotification, AbstractVRNotification.NotificationSeenState.CLICKED);
            if (notification.contentIntent != null) {
                Log.d(TAG, "Firing content intent");
                launchPendingIntent(notification.contentIntent);
            } else if (notification.actions != null && notification.actions.length > 0) {
                Log.d(TAG, "Firing action intent");
                launchPendingIntent(notification.actions[0].actionIntent);
            }
        } else {
            Log.e(TAG, "Notification is missing a click action");
        }
        view.clearAnimation();
        ScaleAnimation animation = getAnimation(false);
        animation.setAnimationListener(new Animation.AnimationListener() {
            /* class com.oculus.panelapp.anytimeui.toast.view.ToastLayerView.AnonymousClass1 */

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                ToastLayerView.this.hideNotification();
            }
        });
        view.setAnimation(animation);
        view.animate();
        unbadgeForNotification();
    }

    private void launchPendingIntent(PendingIntent pendingIntent) {
        try {
            pendingIntent.send();
            logClickAction(null);
        } catch (PendingIntent.CanceledException e) {
            Log.e(TAG, "Failed to fire pending intent", e);
        }
    }

    private ScaleAnimation getAnimation(boolean z) {
        if (z) {
            ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 1.0f, 1.0f, 2, 0.5f, 2, 0.0f);
            scaleAnimation.setDuration(ANIMATION_DURATION_FAST_2_MS);
            scaleAnimation.setInterpolator(new PathInterpolator(0.25f, 0.01f, 0.01f, 0.99f));
            return scaleAnimation;
        }
        ScaleAnimation scaleAnimation2 = new ScaleAnimation(1.0f, 0.0f, 1.0f, 1.0f, 2, 0.5f, 2, 0.0f);
        scaleAnimation2.setInterpolator(new PathInterpolator(0.25f, 0.0f, 0.0f, 1.0f));
        scaleAnimation2.setDuration(500);
        return scaleAnimation2;
    }

    private void logClickAction(String str) {
        if (this.mCurrentNotification == null) {
            Log.e(TAG, "Logging click action for null notification");
            return;
        }
        AnalyticsEvent analyticsEvent = new AnalyticsEvent("oculus_hmd_notification_click");
        analyticsEvent.setExtra(AssistantComponentContract.Components.RemoteImageViewComponent.URI, str);
        analyticsEvent.setExtra("fromPackage", this.mCurrentNotification.getPackageName());
        Bundle bundle = this.mCurrentNotification.getNotification().extras;
        analyticsEvent.setExtra("oculus_notification_ndid", bundle.getString("oculus_notification_ndid"));
        String string = bundle.getString("oculus_notification_fbid", "");
        if (!TextUtils.isEmpty(string)) {
            analyticsEvent.setExtra("oculus_notification_fbid", string);
        }
        analyticsEvent.setExtra("oculus_notification_type", bundle.getString("oculus_notification_type"));
        String str2 = TAG;
        Log.d(str2, "Log click action, ndid = " + bundle.getString("oculus_notification_ndid"));
        String str3 = TAG;
        Log.d(str3, "Log click action, type = " + bundle.getString("oculus_notification_type"));
        if (this.mCurrentNotification.getTag() != null) {
            analyticsEvent.setExtra("notifTag", this.mCurrentNotification.toString());
        }
        this.mUnifiedTelemetryLogger.reportEvent(analyticsEvent, false);
    }

    private void unbadgeForNotification() {
        NotificationDatasetProxy instance = NotificationDatasetProxy.getInstance(getContext(), null, null);
        if (this.mBarView == null) {
            return;
        }
        if (NotificationsHelper.isMessagingVRNotification(instance.getVrNotificiationFromNotifKey(this.mCurrentNotification.getKey()))) {
            this.mBarView.removeMessengerBadge();
        } else {
            this.mBarView.removeNotifsBadge();
        }
    }
}
