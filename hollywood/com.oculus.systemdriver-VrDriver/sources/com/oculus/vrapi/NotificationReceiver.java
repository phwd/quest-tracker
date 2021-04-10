package com.oculus.vrapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

class NotificationReceiver extends BroadcastReceiver {
    private static String BITMAP_EXTRA = "com.oculus.vrapi.notify.BITMAP";
    private static String DURATION_EXTRA = "com.oculus.vrapi.notify.DURATION";
    private static String ENABLE_CAC_EXTRA = "com.oculus.vrapi.notify.ENABLE_CAC";
    private static String FIXED_TO_VIEW_EXTRA = "com.oculus.vrapi.notify.FIXED_TO_VIEW";
    private static String ICON_ID = "com.oculus.vrapi.notify.ICON_ID";
    private static String ID_EXTRA = "com.oculus.vrapi.notify.NOTIFICATION_ID";
    private static final long NOTIFICATION_DEFAULT_ID = 0;
    private static String NOTIFICATION_DISMISS_ACTION = "com.oculus.vrapi.NOTIFICATION_DISMISS";
    private static final int NOTIFICATION_DURATION_LARGE = 10;
    private static final int NOTIFICATION_DURATION_SMALL = 3;
    private static String NOTIFICATION_ICON_ACTION = "com.oculus.vrapi.NOTIFICATION_ACTION";
    private static String NOTIFICATION_LARGE_ACTION = "com.oculus.vrapi.NOTIFICATION_LARGE_ACTION";
    private static final int NOTIFICATION_TYPE_ICON = 0;
    private static final int NOTIFICATION_TYPE_LARGE = 1;
    private static String PACKAGE_NAME = "com.oculus.vrapi.notify.PACKAGE_NAME";
    private static final String PERMISSION_NAME = "com.oculus.systemdriver.DISPLAY_VR_NOTIFICATION";
    private static String POS_X_EXTRA = "com.oculus.vrapi.notify.POS_X";
    private static String POS_Y_EXTRA = "com.oculus.vrapi.notify.POS_Y";
    private static String SCALE_EXTRA = "com.oculus.vrapi.notify.SCALE";
    private static final String TAG = "VrApiNotificationReceiver";
    private static boolean isShuttingDown = false;
    private static NotificationReceiver receiver;

    private static native void dismissNotification(long j);

    private static native void notificationReceived(Context context, long j, int i, int i2, int i3, int i4, int[] iArr, float f, float f2, float f3, boolean z, boolean z2, boolean z3);

    NotificationReceiver() {
    }

    /* access modifiers changed from: package-private */
    public static class NotificationDisplayParameters {
        public boolean enableCAC;
        public boolean isFixedToView;
        public float posX;
        public float posY;
        public float scale;
        public boolean shouldSoftFollow;

        NotificationDisplayParameters() {
        }
    }

    private static void startReceiver(Context context) {
        if (receiver == null) {
            receiver = new NotificationReceiver();
        }
        context.registerReceiver(receiver, new IntentFilter("android.intent.action.ACTION_SHUTDOWN"));
        context.registerReceiver(receiver, new IntentFilter(NOTIFICATION_ICON_ACTION));
        IntentFilter extraPermissionActions = new IntentFilter();
        extraPermissionActions.addAction(NOTIFICATION_LARGE_ACTION);
        extraPermissionActions.addAction(NOTIFICATION_DISMISS_ACTION);
        context.registerReceiver(receiver, extraPermissionActions, PERMISSION_NAME, null);
    }

    private static void stopReceiver(Context context) {
        context.unregisterReceiver(receiver);
    }

    private static void displayBitmapNotification(Context context, long id, int duration, Bitmap bitmap, int type, NotificationDisplayParameters params) {
        if (isShuttingDown) {
            Log.d(TAG, "Shutdown in progress, suppressing notifications");
            return;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        int numPixels = width * height;
        if (numPixels > 0) {
            int[] pixels = new int[numPixels];
            bitmap.getPixels(pixels, 0, width, 0, 0, width, height);
            notificationReceived(context, id, duration, type, width, height, pixels, params.scale, params.posX, params.posY, params.isFixedToView, params.enableCAC, params.shouldSoftFollow);
        }
    }

    public static void displayNotification(Context context, int iconID, String packageName) {
        Bitmap bitmap = getIconFromPackage(context, packageName, iconID);
        if (bitmap == null) {
            Log.d(TAG, "Error loading icon " + iconID);
            return;
        }
        displayBitmapNotification(context, NOTIFICATION_DEFAULT_ID, 3, bitmap, 0, getDefaultParameters(0));
    }

    private static NotificationDisplayParameters getDefaultParameters(int type) {
        NotificationDisplayParameters params = new NotificationDisplayParameters();
        params.isFixedToView = true;
        params.enableCAC = true;
        params.shouldSoftFollow = false;
        if (type == 0) {
            params.posX = 0.125f;
            params.posY = 0.125f;
            params.scale = 3.75f;
        } else {
            params.posX = 0.0f;
            params.posY = -0.3f;
            params.scale = 30.0f;
        }
        return params;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.ACTION_SHUTDOWN".equals(action)) {
            isShuttingDown = true;
        } else if (NOTIFICATION_ICON_ACTION.equals(action)) {
            String packageName = intent.getExtras().getString(PACKAGE_NAME);
            int iconID = intent.getExtras().getInt(ICON_ID);
            Log.d(TAG, "Notification: " + packageName + " : " + iconID);
            displayNotification(context, iconID, packageName);
        } else if (NOTIFICATION_LARGE_ACTION.equals(action)) {
            parseNotificationBundle(context, intent.getExtras());
        } else if (!NOTIFICATION_DISMISS_ACTION.equals(action)) {
            Log.e(TAG, "Unsupported action: " + action);
        } else if (!intent.hasExtra(ID_EXTRA)) {
            Log.e(TAG, "Dismiss intent doesn't contain a notification id");
        } else {
            dismissNotification(intent.getLongExtra(ID_EXTRA, -1));
        }
    }

    private void parseNotificationBundle(Context context, Bundle bundle) {
        if (bundle == null) {
            Log.w(TAG, "Invalid bundle, ignoring");
            return;
        }
        long id = bundle.getLong(ID_EXTRA, NOTIFICATION_DEFAULT_ID);
        int duration = bundle.getInt(DURATION_EXTRA, NOTIFICATION_DURATION_LARGE);
        NotificationDisplayParameters params = getDefaultParameters(1);
        params.scale = bundle.getFloat(SCALE_EXTRA, params.scale);
        params.posX = bundle.getFloat(POS_X_EXTRA, params.posX);
        params.posY = bundle.getFloat(POS_Y_EXTRA, params.posY);
        params.isFixedToView = false;
        params.enableCAC = bundle.getBoolean(ENABLE_CAC_EXTRA, params.enableCAC);
        params.shouldSoftFollow = true;
        Bitmap bitmap = (Bitmap) bundle.getParcelable(BITMAP_EXTRA);
        if (bitmap != null) {
            displayBitmapNotification(context, id, duration, bitmap, 1, params);
        }
    }

    private static Context getRemoteContext(Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, 0);
            return context.createPackageContext(packageName, 2);
        } catch (Exception e) {
            throw new RuntimeException("Couldn't load package " + packageName, e);
        }
    }

    private static Bitmap getIconFromPackage(Context context, String packageName, int iconID) {
        Context c = getRemoteContext(context, packageName);
        if (c == null) {
            return null;
        }
        return BitmapFactory.decodeResource(c.getResources(), iconID);
    }
}
