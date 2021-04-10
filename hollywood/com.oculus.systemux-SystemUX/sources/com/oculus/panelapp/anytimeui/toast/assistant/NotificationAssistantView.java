package com.oculus.panelapp.anytimeui.toast.assistant;

import android.content.Context;
import android.service.notification.StatusBarNotification;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.oculus.panelapp.anytimeui.R;
import com.oculus.vrshell.util.NotificationsActionsUtil;

public final class NotificationAssistantView extends FrameLayout {
    private static final int ASSISTANT_IN_APP_NOTIFICATION_ID = 1000;

    public NotificationAssistantView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public static boolean isAssistantInAppNotification(StatusBarNotification statusBarNotification) {
        return 1000 == statusBarNotification.getId() && "com.oculus.assistant".equals(statusBarNotification.getPackageName());
    }

    public static View getViewForNotification(Context context, StatusBarNotification statusBarNotification) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.assistant_in_app_toast, (ViewGroup) null);
        NotificationAssistantView notificationAssistantView = (NotificationAssistantView) inflate.findViewById(R.id.assistant_in_app_content);
        notificationAssistantView.loadNotificationIcon(statusBarNotification);
        notificationAssistantView.loadNotificationText(statusBarNotification);
        return inflate;
    }

    private void loadNotificationIcon(StatusBarNotification statusBarNotification) {
        findViewById(R.id.assistant_in_app_icon).setBackground(statusBarNotification.getNotification().getSmallIcon().loadDrawable(getContext()));
    }

    private void loadNotificationText(StatusBarNotification statusBarNotification) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        spannableStringBuilder.append((CharSequence) new SpannableString(NotificationsActionsUtil.getNotificationDescription(statusBarNotification.getNotification())));
        TextView textView = (TextView) findViewById(R.id.assistant_in_app_text);
        textView.setText(spannableStringBuilder, TextView.BufferType.SPANNABLE);
        textView.setTextColor(getResources().getColor(R.color.oc_gray_10));
    }
}
