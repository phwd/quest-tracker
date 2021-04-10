package com.oculus.notifications.view;

import android.app.Notification;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.service.notification.StatusBarNotification;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import com.oculus.common.notifications.R;
import com.oculus.notifications.NotificationConstants;
import com.oculus.os.SettingsManager;
import com.oculus.vrshell.panels.DensityUtils;
import com.oculus.vrshell.util.NotificationsActionsUtil;
import com.oculus.vrshell.util.PackageUtil;

public class NotificationView extends LinearLayout {
    private static final String NOTIFICATION_CTA_IMAGE_GETTER_OCULUS_ICON_SOURCE = "oculus_icon";
    private static final int NOTIFICATION_CTA_IMAGE_SIZE_DP = 12;
    private static final String NOTIFICATION_PHONE_NOTIFICATION_APP_NAME = "phone_notif_app_name";

    public NotificationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void initialize(StatusBarNotification statusBarNotification) {
        loadNotificationDescription(statusBarNotification);
        loadNotificationIcon(statusBarNotification);
        loadNotificationTitle(statusBarNotification);
    }

    private void loadNotificationDescription(StatusBarNotification statusBarNotification) {
        TextView textView = (TextView) findViewById(R.id.notif_description_text);
        String notificationDescription = NotificationsActionsUtil.getNotificationDescription(statusBarNotification.getNotification(), false);
        if (notificationDescription.isEmpty()) {
            textView.setVisibility(8);
        } else {
            textView.setText(notificationDescription);
        }
    }

    private void loadNotificationIcon(StatusBarNotification statusBarNotification) {
        Icon notificationIcon = NotificationsActionsUtil.getNotificationIcon(statusBarNotification.getNotification());
        View findViewById = findViewById(R.id.notif_icon);
        if (notificationIcon == null) {
            findViewById.setVisibility(8);
        } else {
            findViewById.setBackground(notificationIcon.loadDrawable(getContext()));
        }
    }

    private void loadNotificationTitle(StatusBarNotification statusBarNotification) {
        TextView textView = (TextView) findViewById(R.id.notif_title_text);
        String notificationTitle = NotificationsActionsUtil.getNotificationTitle(statusBarNotification.getNotification());
        if (notificationTitle.isEmpty()) {
            textView.setVisibility(8);
        } else {
            textView.setText(notificationTitle);
        }
    }

    public static View getViewForNotification(Context context, StatusBarNotification statusBarNotification, boolean z, boolean z2, boolean z3, boolean z4) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.notification_view, (ViewGroup) null);
        NotificationView notificationView = (NotificationView) inflate.findViewById(R.id.notification_content);
        Theme fromContext = Theme.fromContext(context);
        notificationView.loadNotificationIcon(statusBarNotification, fromContext);
        notificationView.loadNotificationAppName(statusBarNotification, fromContext);
        notificationView.loadNotificationTitle(statusBarNotification, fromContext);
        notificationView.loadNotificationCallToAction(context, statusBarNotification, fromContext, inflate, z2, z3, z4);
        return inflate;
    }

    private void loadNotificationIcon(StatusBarNotification statusBarNotification, Theme theme) {
        Notification notification = statusBarNotification.getNotification();
        NotificationConstants.LargeImageType fromNotification = NotificationConstants.LargeImageType.fromNotification(notification);
        Icon largeIcon = notification.getLargeIcon();
        View findViewById = findViewById(R.id.notif_icon_background);
        if (shouldApplyBackgroundToNotificationIcon(theme, fromNotification)) {
            findViewById.setBackground(getResources().getDrawable(R.drawable.notification_view_icon_background));
        }
        View findViewById2 = findViewById(R.id.notif_icon_glyph);
        View findViewById3 = findViewById(R.id.notif_icon_image);
        if (largeIcon != null) {
            findViewById2.setVisibility(8);
            findViewById.setVisibility(8);
            findViewById3.setVisibility(0);
            findViewById3.setBackground(largeIcon.loadDrawable(getContext()));
        } else if (fromNotification == NotificationConstants.LargeImageType.ICON || fromNotification == NotificationConstants.LargeImageType.INVALID) {
            findViewById2.setBackground(loadNotificationIconDrawable(notification, fromNotification));
            findViewById3.setVisibility(8);
        } else {
            findViewById2.setVisibility(8);
            findViewById3.setBackground(loadNotificationIconDrawable(notification, fromNotification));
        }
    }

    private boolean shouldApplyBackgroundToNotificationIcon(Theme theme, NotificationConstants.LargeImageType largeImageType) {
        return theme == Theme.LIGHT && (largeImageType == NotificationConstants.LargeImageType.ICON || largeImageType == NotificationConstants.LargeImageType.INVALID);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.notifications.view.NotificationView$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$notifications$NotificationConstants$LargeImageType = new int[NotificationConstants.LargeImageType.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x002a */
        static {
            /*
                com.oculus.notifications.NotificationConstants$LargeImageType[] r0 = com.oculus.notifications.NotificationConstants.LargeImageType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.notifications.view.NotificationView.AnonymousClass2.$SwitchMap$com$oculus$notifications$NotificationConstants$LargeImageType = r0
                int[] r0 = com.oculus.notifications.view.NotificationView.AnonymousClass2.$SwitchMap$com$oculus$notifications$NotificationConstants$LargeImageType     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.notifications.NotificationConstants$LargeImageType r1 = com.oculus.notifications.NotificationConstants.LargeImageType.INVALID     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.notifications.view.NotificationView.AnonymousClass2.$SwitchMap$com$oculus$notifications$NotificationConstants$LargeImageType     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.notifications.NotificationConstants$LargeImageType r1 = com.oculus.notifications.NotificationConstants.LargeImageType.ICON     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.notifications.view.NotificationView.AnonymousClass2.$SwitchMap$com$oculus$notifications$NotificationConstants$LargeImageType     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.notifications.NotificationConstants$LargeImageType r1 = com.oculus.notifications.NotificationConstants.LargeImageType.DETAIL     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                int[] r0 = com.oculus.notifications.view.NotificationView.AnonymousClass2.$SwitchMap$com$oculus$notifications$NotificationConstants$LargeImageType     // Catch:{ NoSuchFieldError -> 0x0035 }
                com.oculus.notifications.NotificationConstants$LargeImageType r1 = com.oculus.notifications.NotificationConstants.LargeImageType.PROFILE     // Catch:{ NoSuchFieldError -> 0x0035 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0035 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0035 }
            L_0x0035:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.notifications.view.NotificationView.AnonymousClass2.<clinit>():void");
        }
    }

    private Drawable loadNotificationIconDrawable(Notification notification, NotificationConstants.LargeImageType largeImageType) {
        int i = AnonymousClass2.$SwitchMap$com$oculus$notifications$NotificationConstants$LargeImageType[largeImageType.ordinal()];
        if (i == 1) {
            return getResources().getDrawable(R.drawable.ic_notifications);
        }
        if (i == 2) {
            return getNotificationIcon(notification);
        }
        if (i == 3) {
            RoundedBitmapDrawable create = RoundedBitmapDrawableFactory.create(getResources(), getBitmapFromDrawable(getNotificationIcon(notification)));
            create.setCornerRadius(getResources().getDimension(R.dimen.notification_icon_detail_border_radius));
            return create;
        } else if (i == 4) {
            RoundedBitmapDrawable create2 = RoundedBitmapDrawableFactory.create(getResources(), getBitmapFromDrawable(getNotificationIcon(notification)));
            create2.setCircular(true);
            return create2;
        } else {
            throw new IllegalArgumentException("Invalid NotificationConstants.LargeImageType given to loadNotificationIconDrawable.");
        }
    }

    private Drawable getNotificationIcon(Notification notification) {
        Drawable loadDrawable;
        Icon largeIcon = notification.getLargeIcon();
        if (largeIcon == null || (loadDrawable = largeIcon.loadDrawable(getContext())) == null) {
            return notification.getSmallIcon().loadDrawable(getContext());
        }
        return loadDrawable;
    }

    private void loadNotificationAppName(StatusBarNotification statusBarNotification, Theme theme) {
        TextView textView = (TextView) findViewById(R.id.notif_app);
        String notificationAppFromNotification = getNotificationAppFromNotification(statusBarNotification);
        if (notificationAppFromNotification == null) {
            textView.setVisibility(8);
            return;
        }
        textView.setText(notificationAppFromNotification);
        textView.setTextColor(getResources().getColor(theme == Theme.DARK ? R.color.oc_white_a60 : R.color.oc_gray_40));
    }

    private String getNotificationAppFromNotification(StatusBarNotification statusBarNotification) {
        String packageName = statusBarNotification.getPackageName();
        if (packageName.startsWith("android") || PackageUtil.isFirstPartySystem(packageName)) {
            return statusBarNotification.getNotification().extras.getString(NOTIFICATION_PHONE_NOTIFICATION_APP_NAME);
        }
        try {
            PackageManager packageManager = getContext().getPackageManager();
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 0);
            if (applicationInfo != null) {
                return packageManager.getApplicationLabel(applicationInfo).toString();
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    private void loadNotificationTitle(StatusBarNotification statusBarNotification, Theme theme) {
        TextView textView = (TextView) findViewById(R.id.notif_title);
        textView.setText(Html.fromHtml(getNotificationTextFromNotification(statusBarNotification)));
        textView.setTextColor(getResources().getColor(theme == Theme.DARK ? R.color.oc_gray_10 : R.color.oc_gray_90));
    }

    private String getNotificationTextFromNotification(StatusBarNotification statusBarNotification) {
        String notificationTitle = NotificationsActionsUtil.getNotificationTitle(statusBarNotification.getNotification());
        if (notificationTitle.toLowerCase().equals("oculus")) {
            notificationTitle = "";
        }
        String notificationDescription = NotificationsActionsUtil.getNotificationDescription(statusBarNotification.getNotification());
        if (notificationDescription.isEmpty()) {
            return notificationTitle;
        }
        if (!notificationTitle.isEmpty()) {
            notificationTitle = notificationTitle + ". ";
        }
        return notificationTitle + notificationDescription;
    }

    private void loadNotificationCallToAction(Context context, StatusBarNotification statusBarNotification, Theme theme, View view, boolean z, boolean z2, boolean z3) {
        TextView textView = (TextView) findViewById(R.id.notif_text);
        boolean doesNotificationNeedCTA = doesNotificationNeedCTA(statusBarNotification, z3);
        if (!doesNotificationNeedCTA || !z || !z2) {
            view.setBackgroundResource(theme == Theme.DARK ? R.drawable.notification_view_background_dark : R.drawable.notification_view_background_light);
        } else {
            view.setBackgroundResource(theme == Theme.DARK ? R.drawable.notification_view_background_dark_with_cta : R.drawable.notification_view_background_light_with_cta);
        }
        if (doesNotificationNeedCTA) {
            String notificationCTA = getNotificationCTA(context, statusBarNotification, z2, z3);
            if (notificationCTA == null || notificationCTA.isEmpty()) {
                textView.setVisibility(8);
                return;
            }
            textView.setText(Html.fromHtml(notificationCTA, 63, getNotificationCallToActionImageGetter(context, theme), null));
            textView.setTextColor(getResources().getColor(theme == Theme.DARK ? R.color.oc_white_a60 : R.color.oc_gray_60));
            textView.setVisibility(0);
            return;
        }
        textView.setVisibility(8);
    }

    private Html.ImageGetter getNotificationCallToActionImageGetter(Context context, final Theme theme) {
        return new Html.ImageGetter() {
            /* class com.oculus.notifications.view.NotificationView.AnonymousClass1 */

            public Drawable getDrawable(String str) {
                if (!NotificationView.NOTIFICATION_CTA_IMAGE_GETTER_OCULUS_ICON_SOURCE.equals(str)) {
                    return null;
                }
                Drawable drawable = NotificationView.this.getResources().getDrawable(theme == Theme.DARK ? R.drawable.ic_bt_oculus_dark : R.drawable.ic_bt_oculus_light);
                int dipToPixelsInt = DensityUtils.dipToPixelsInt(12.0f, NotificationView.this.getResources().getDisplayMetrics());
                drawable.setBounds(0, 0, dipToPixelsInt, dipToPixelsInt);
                return drawable;
            }
        };
    }

    private static boolean doesNotificationNeedCTA(StatusBarNotification statusBarNotification, boolean z) {
        return !z && NotificationConstants.notificationHasClickAction(statusBarNotification);
    }

    private static String getNotificationCTA(Context context, StatusBarNotification statusBarNotification, boolean z, boolean z2) {
        if (z) {
            String string = statusBarNotification.getNotification().extras.getString(NotificationConstants.KEY_ACTION_TEXT);
            Boolean valueOf = Boolean.valueOf(doesNotificationNeedCTA(statusBarNotification, z2));
            Boolean valueOf2 = Boolean.valueOf(!TextUtils.isEmpty(string));
            if (!valueOf.booleanValue()) {
                return "";
            }
            return valueOf2.booleanValue() ? string : context.getResources().getString(R.string.notification_null_cta);
        } else if (new SettingsManager().getBoolean(SettingsManager.HAND_TRACKING_ENABLED, false)) {
            return context.getResources().getString(R.string.notification_hand_enabled_cta);
        } else {
            return context.getResources().getString(R.string.notification_cta);
        }
    }

    private static Bitmap getBitmapFromDrawable(Drawable drawable) {
        Bitmap bitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } else {
            bitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return bitmap;
    }

    public enum Theme {
        DARK,
        LIGHT;

        public static Theme fromContext(Context context) {
            return DARK;
        }
    }
}
