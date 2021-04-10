package com.oculus.notifications.view;

import X.AnonymousClass006;
import X.AnonymousClass0w3;
import android.app.Notification;
import android.app.UiModeManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
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
import bolts.WebViewAppLinkResolver;
import com.oculus.notifications.NotificationConstants;
import com.oculus.os.SettingsManager;
import com.oculus.socialplatform.R;
import com.oculus.vrshell.panels.DensityUtils;
import com.oculus.vrshell.util.NotificationsActionsUtil;
import com.oculus.vrshell.util.PackageUtil;

public class NotificationView extends LinearLayout {
    public static final String NOTIFICATION_CTA_IMAGE_GETTER_OCULUS_ICON_SOURCE = "oculus_icon";
    public static final int NOTIFICATION_CTA_IMAGE_SIZE_DP = 12;
    public static final String NOTIFICATION_PHONE_NOTIFICATION_APP_NAME = "phone_notif_app_name";

    public enum Theme {
        DARK,
        LIGHT;

        public static Theme fromContext(Context context) {
            if (((UiModeManager) context.getSystemService("uimode")).getNightMode() == 1) {
                return LIGHT;
            }
            return DARK;
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

    /* renamed from: com.oculus.notifications.view.NotificationView$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$notifications$NotificationConstants$LargeImageType;

        /* JADX WARNING: Can't wrap try/catch for region: R(10:0|1|2|3|4|5|6|7|8|10) */
        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        static {
            /*
                com.oculus.notifications.NotificationConstants$LargeImageType[] r0 = com.oculus.notifications.NotificationConstants.LargeImageType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.notifications.view.NotificationView.AnonymousClass2.$SwitchMap$com$oculus$notifications$NotificationConstants$LargeImageType = r2
                com.oculus.notifications.NotificationConstants$LargeImageType r0 = com.oculus.notifications.NotificationConstants.LargeImageType.INVALID     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.notifications.NotificationConstants$LargeImageType r0 = com.oculus.notifications.NotificationConstants.LargeImageType.ICON     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.notifications.NotificationConstants$LargeImageType r0 = com.oculus.notifications.NotificationConstants.LargeImageType.DETAIL     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.notifications.NotificationConstants$LargeImageType r0 = com.oculus.notifications.NotificationConstants.LargeImageType.PROFILE     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.notifications.view.NotificationView.AnonymousClass2.<clinit>():void");
        }
    }

    public static boolean doesNotificationNeedCTA(StatusBarNotification statusBarNotification, boolean z) {
        if (z || !NotificationConstants.notificationHasClickAction(statusBarNotification)) {
            return false;
        }
        return true;
    }

    public static Bitmap getBitmapFromDrawable(Drawable drawable) {
        Bitmap createBitmap;
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            if (bitmapDrawable.getBitmap() != null) {
                return bitmapDrawable.getBitmap();
            }
        }
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth <= 0 || intrinsicHeight <= 0) {
            createBitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888);
        } else {
            createBitmap = Bitmap.createBitmap(intrinsicWidth, intrinsicHeight, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(createBitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return createBitmap;
    }

    public static String getNotificationCTA(Context context, StatusBarNotification statusBarNotification, boolean z, boolean z2) {
        int i;
        if (z) {
            String string = statusBarNotification.getNotification().extras.getString(NotificationConstants.KEY_ACTION_TEXT);
            Boolean valueOf = Boolean.valueOf(doesNotificationNeedCTA(statusBarNotification, z2));
            Boolean valueOf2 = Boolean.valueOf(!TextUtils.isEmpty(string));
            if (!valueOf.booleanValue()) {
                return "";
            }
            String string2 = context.getResources().getString(R.string.notification_null_cta);
            if (valueOf2.booleanValue()) {
                return string;
            }
            return string2;
        }
        boolean z3 = new SettingsManager().getBoolean("hand_tracking_enabled", false);
        Resources resources = context.getResources();
        if (z3) {
            i = R.string.notification_hand_enabled_cta;
        } else {
            i = R.string.notification_cta;
        }
        return resources.getString(i);
    }

    private Html.ImageGetter getNotificationCallToActionImageGetter(Context context, final Theme theme) {
        return new Html.ImageGetter() {
            /* class com.oculus.notifications.view.NotificationView.AnonymousClass1 */

            public Drawable getDrawable(String str) {
                if (!NotificationView.NOTIFICATION_CTA_IMAGE_GETTER_OCULUS_ICON_SOURCE.equals(str)) {
                    return null;
                }
                Resources resources = NotificationView.this.getResources();
                Theme theme = r8;
                Theme theme2 = Theme.DARK;
                int i = R.drawable.ic_bt_oculus_light;
                if (theme == theme2) {
                    i = R.drawable.ic_bt_oculus_dark;
                }
                Drawable drawable = resources.getDrawable(i);
                int dipToPixelsInt = DensityUtils.dipToPixelsInt(12.0f, NotificationView.this.getResources().getDisplayMetrics());
                drawable.setBounds(0, 0, dipToPixelsInt, dipToPixelsInt);
                return drawable;
            }
        };
    }

    private boolean shouldApplyBackgroundToNotificationIcon(Theme theme, NotificationConstants.LargeImageType largeImageType) {
        if (theme != Theme.LIGHT) {
            return false;
        }
        if (largeImageType == NotificationConstants.LargeImageType.ICON || largeImageType == NotificationConstants.LargeImageType.INVALID) {
            return true;
        }
        return false;
    }

    private String getNotificationAppFromNotification(StatusBarNotification statusBarNotification) {
        String packageName = statusBarNotification.getPackageName();
        if (packageName.startsWith(WebViewAppLinkResolver.KEY_ANDROID) || PackageUtil.isFirstPartySystem(packageName)) {
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

    private Drawable getNotificationIcon(Notification notification) {
        Drawable loadDrawable;
        Icon largeIcon = notification.getLargeIcon();
        if (largeIcon == null || (loadDrawable = largeIcon.loadDrawable(getContext())) == null) {
            return notification.getSmallIcon().loadDrawable(getContext());
        }
        return loadDrawable;
    }

    private String getNotificationTextFromNotification(StatusBarNotification statusBarNotification) {
        String notificationTitle = NotificationsActionsUtil.getNotificationTitle(statusBarNotification.getNotification());
        if (notificationTitle.toLowerCase().equals("oculus")) {
            notificationTitle = "";
        }
        String notificationDescription = NotificationsActionsUtil.getNotificationDescription(statusBarNotification.getNotification(), false);
        if (notificationDescription.isEmpty()) {
            return notificationTitle;
        }
        if (!notificationTitle.isEmpty()) {
            notificationTitle = AnonymousClass006.A07(notificationTitle, ". ");
        }
        return AnonymousClass006.A07(notificationTitle, notificationDescription);
    }

    private void loadNotificationAppName(StatusBarNotification statusBarNotification, Theme theme) {
        TextView textView = (TextView) findViewById(R.id.notif_app);
        String notificationAppFromNotification = getNotificationAppFromNotification(statusBarNotification);
        if (notificationAppFromNotification == null) {
            textView.setVisibility(8);
            return;
        }
        textView.setText(notificationAppFromNotification);
        Theme theme2 = Theme.DARK;
        int i = R.color.oc_gray_40;
        if (theme == theme2) {
            i = R.color.oc_white_a60;
        }
        textView.setTextColor(getResources().getColor(i));
    }

    private void loadNotificationCallToAction(Context context, StatusBarNotification statusBarNotification, final Theme theme, View view, boolean z, boolean z2, boolean z3) {
        int i;
        String notificationCTA;
        TextView textView = (TextView) findViewById(R.id.notif_text);
        boolean doesNotificationNeedCTA = doesNotificationNeedCTA(statusBarNotification, z3);
        if (!doesNotificationNeedCTA || !z || !z2) {
            Theme theme2 = Theme.DARK;
            i = R.drawable.notification_view_background_light;
            if (theme == theme2) {
                i = R.drawable.notification_view_background_dark;
            }
        } else {
            Theme theme3 = Theme.DARK;
            i = R.drawable.notification_view_background_light_with_cta;
            if (theme == theme3) {
                i = R.drawable.notification_view_background_dark_with_cta;
            }
        }
        view.setBackgroundResource(i);
        if (!doesNotificationNeedCTA || (notificationCTA = getNotificationCTA(context, statusBarNotification, z2, z3)) == null || notificationCTA.isEmpty()) {
            textView.setVisibility(8);
            return;
        }
        textView.setText(Html.fromHtml(notificationCTA, 63, new Html.ImageGetter() {
            /* class com.oculus.notifications.view.NotificationView.AnonymousClass1 */

            public Drawable getDrawable(String str) {
                if (!NotificationView.NOTIFICATION_CTA_IMAGE_GETTER_OCULUS_ICON_SOURCE.equals(str)) {
                    return null;
                }
                Resources resources = NotificationView.this.getResources();
                Theme theme = theme;
                Theme theme2 = Theme.DARK;
                int i = R.drawable.ic_bt_oculus_light;
                if (theme == theme2) {
                    i = R.drawable.ic_bt_oculus_dark;
                }
                Drawable drawable = resources.getDrawable(i);
                int dipToPixelsInt = DensityUtils.dipToPixelsInt(12.0f, NotificationView.this.getResources().getDisplayMetrics());
                drawable.setBounds(0, 0, dipToPixelsInt, dipToPixelsInt);
                return drawable;
            }
        }, null));
        Theme theme4 = Theme.DARK;
        int i2 = R.color.oc_gray_60;
        if (theme == theme4) {
            i2 = R.color.oc_white_a60;
        }
        textView.setTextColor(getResources().getColor(i2));
        int i3 = R.color.oc_gray_10;
        if (theme == theme4) {
            i3 = R.color.oc_gray_100;
        }
        textView.setBackgroundResource(i3);
        textView.setVisibility(0);
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

    private Drawable loadNotificationIconDrawable(Notification notification, NotificationConstants.LargeImageType largeImageType) {
        switch (largeImageType.ordinal()) {
            case 0:
                return getResources().getDrawable(R.drawable.ic_notifications);
            case 1:
                return getNotificationIcon(notification);
            case 2:
                AnonymousClass0w3 r2 = new AnonymousClass0w3(getResources(), getBitmapFromDrawable(getNotificationIcon(notification)));
                r2.A05 = true;
                r2.A04 = true;
                r2.A00 = (float) (Math.min(r2.A01, r2.A02) >> 1);
                r2.A0A.setShader(r2.A09);
                r2.invalidateSelf();
                return r2;
            case 3:
                Bitmap bitmapFromDrawable = getBitmapFromDrawable(getNotificationIcon(notification));
                Resources resources = getResources();
                AnonymousClass0w3 r1 = new AnonymousClass0w3(resources, bitmapFromDrawable);
                r1.A01(resources.getDimension(R.dimen.abc_action_bar_elevation_material));
                return r1;
            default:
                throw new IllegalArgumentException("Invalid NotificationConstants.LargeImageType given to loadNotificationIconDrawable.");
        }
    }

    public void initialize(StatusBarNotification statusBarNotification) {
        loadNotificationDescription(statusBarNotification);
        loadNotificationIcon(statusBarNotification);
        loadNotificationTitle(statusBarNotification);
    }

    public NotificationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
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

    private void loadNotificationIcon(StatusBarNotification statusBarNotification, Theme theme) {
        Drawable loadNotificationIconDrawable;
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
            loadNotificationIconDrawable = largeIcon.loadDrawable(getContext());
        } else if (fromNotification == NotificationConstants.LargeImageType.ICON || fromNotification == NotificationConstants.LargeImageType.INVALID) {
            findViewById2.setBackground(loadNotificationIconDrawable(notification, fromNotification));
            findViewById3.setVisibility(8);
            return;
        } else {
            findViewById2.setVisibility(8);
            loadNotificationIconDrawable = loadNotificationIconDrawable(notification, fromNotification);
        }
        findViewById3.setBackground(loadNotificationIconDrawable);
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

    private void loadNotificationTitle(StatusBarNotification statusBarNotification, Theme theme) {
        TextView textView = (TextView) findViewById(R.id.notif_title);
        textView.setText(Html.fromHtml(getNotificationTextFromNotification(statusBarNotification)));
        Theme theme2 = Theme.DARK;
        int i = R.color.oc_gray_90;
        if (theme == theme2) {
            i = R.color.oc_gray_10;
        }
        textView.setTextColor(getResources().getColor(i));
    }
}
