package com.oculus.notification_proxy;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.TransactionTooLargeException;
import android.os.UserHandle;
import android.provider.Settings;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.RemoteViews;
import com.oculus.os.ActivityManagerUtils;
import com.oculus.os.SettingsManager;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class OculusNotificationListenerService extends NotificationListenerService {
    private static final String ACCESS_NOTIFICATIONS_PERMISSION = "com.oculus.notification_proxy.ACCESS_NOTIFICATIONS";
    private static final String BITMAP_EXTRA = "com.oculus.vrapi.notify.BITMAP";
    private static final String DURATION_EXTRA = "com.oculus.vrapi.notify.DURATION";
    private static final String FIXED_TO_VIEW_EXTRA = "com.oculus.vrapi.notify.FIXED_TO_VIEW";
    private static final String ID_EXTRA = "com.oculus.vrapi.notify.NOTIFICATION_ID";
    private static final String NOTIFICATION_DISMISS_ACTION = "com.oculus.vrapi.NOTIFICATION_DISMISS";
    private static final String NOTIFICATION_LARGE_ACTION = "com.oculus.vrapi.NOTIFICATION_LARGE_ACTION";
    private static final String NOTIFICATION_WAKE_ACTION = "com.oculus.vrshell.intent.action.NOTIFICATION_WAKE";
    private static final String NO_PERMISSION = null;
    private static final int PARCELABLE_MAX_SIZE = 500000;
    private static final String POS_X_EXTRA = "com.oculus.vrapi.notify.POS_X";
    private static final String POS_Y_EXTRA = "com.oculus.vrapi.notify.POS_Y";
    private static final String SCALE_EXTRA = "com.oculus.vrapi.notify.SCALE";
    private static final String SETTINGS_DURATION_KEY = "vr_notification_default_duration";
    private static final String SETTINGS_EXTERNAL_RENDERER = "vr_notification_external_renderer";
    private static final String SETTINGS_FIXED_TO_VIEW_KEY = "vr_notification_fixed_to_view";
    private static final String SETTINGS_MIN_PRIORITY_KEY = "vr_notification_min_priority";
    private static final String SETTINGS_POS_X_KEY = "vr_notification_pos_x";
    private static final String SETTINGS_POS_Y_KEY = "vr_notification_pos_y";
    private static final String SETTINGS_SCALE_KEY = "vr_notification_scale";
    private static final String TAG = "OculusNotificationListenerService";
    private final Context mContext;
    private IntParameter mFixedToView;
    private final Handler mHandler = new Handler();
    private IntParameter mHasExternalRenderer;
    private Set<String> mHighPriorityNotifications = new HashSet();
    private IntParameter mNotificationDurationSeconds;
    private IntParameter mNotificationMinLevel;
    private Set<String> mPackageBlacklist = new HashSet();
    private Set<String> mPackageWhitelist = new HashSet();
    private FloatParameter mPosX;
    private FloatParameter mPosY;
    private FloatParameter mScale;
    private final SettingsManager mSettingsManager = new SettingsManager();

    interface SettingsUpdateHandler {
        void onKeyUpdated(String str);
    }

    public OculusNotificationListenerService(Context context) {
        this.mContext = context;
        this.mNotificationDurationSeconds = new IntParameter(this.mContext, this.mHandler, SETTINGS_DURATION_KEY, R.integer.def_notification_duration_seconds);
        this.mNotificationMinLevel = new IntParameter(this.mContext, this.mHandler, SETTINGS_MIN_PRIORITY_KEY, R.integer.def_notification_min_level);
        this.mFixedToView = new IntParameter(this.mContext, this.mHandler, SETTINGS_FIXED_TO_VIEW_KEY, R.integer.def_notification_fixed_to_view);
        this.mHasExternalRenderer = new IntParameter(this.mContext, this.mHandler, SETTINGS_EXTERNAL_RENDERER, R.integer.def_notification_external_renderer);
        this.mPosX = new FloatParameter(this.mContext, this.mHandler, SETTINGS_POS_X_KEY, R.dimen.def_notification_pos_x);
        this.mPosY = new FloatParameter(this.mContext, this.mHandler, SETTINGS_POS_Y_KEY, R.dimen.def_notification_pos_y);
        this.mScale = new FloatParameter(this.mContext, this.mHandler, SETTINGS_SCALE_KEY, R.dimen.def_notification_scale);
        for (BaseSettingHolder baseSettingHolder : new BaseSettingHolder[]{this.mNotificationDurationSeconds, this.mNotificationMinLevel, this.mFixedToView, this.mHasExternalRenderer, this.mPosX, this.mPosY, this.mScale}) {
            baseSettingHolder.registerObserver(this.mContext);
        }
        Resources resources = this.mContext.getResources();
        this.mPackageWhitelist.addAll(Arrays.asList(resources.getStringArray(R.array.whitelisted_vr_notifications)));
        this.mPackageBlacklist.addAll(Arrays.asList(resources.getStringArray(R.array.blacklisted_vr_notifications)));
        this.mHighPriorityNotifications.addAll(Arrays.asList(resources.getStringArray(R.array.high_priority_notifications)));
    }

    public void onNotificationPosted(StatusBarNotification statusBarNotification) {
        if (statusBarNotification == null) {
            Log.e(TAG, "onNotificationPosted(): received a null StatusBarNotification");
            return;
        }
        String str = TAG;
        Log.d(str, "Notification posted: " + statusBarNotification.getKey());
        this.mContext.sendBroadcastAsUser(new Intent(NOTIFICATION_WAKE_ACTION), UserHandle.CURRENT, "com.oculus.notification_proxy.ACCESS_NOTIFICATIONS");
        if (((Integer) this.mHasExternalRenderer.getValue()).intValue() == 0 && shouldDisplayNotification(statusBarNotification)) {
            Bitmap renderNotification = renderNotification(statusBarNotification.getNotification());
            Intent intent = new Intent(NOTIFICATION_LARGE_ACTION);
            intent.putExtra(BITMAP_EXTRA, renderNotification);
            intent.putExtra(ID_EXTRA, getNotificationVrApiId(statusBarNotification));
            intent.putExtra(DURATION_EXTRA, getNotificationDuration(statusBarNotification));
            intent.putExtra(SCALE_EXTRA, (Serializable) this.mScale.getValue());
            intent.putExtra(POS_X_EXTRA, (Serializable) this.mPosX.getValue());
            intent.putExtra(POS_Y_EXTRA, (Serializable) this.mPosY.getValue());
            intent.putExtra(FIXED_TO_VIEW_EXTRA, ((Integer) this.mFixedToView.getValue()).intValue() != 0);
            String str2 = TAG;
            Log.d(str2, "Broadcasting a notification to VrApi: " + intent);
            broadcast(intent, NO_PERMISSION);
        }
    }

    public void onNotificationRemoved(StatusBarNotification statusBarNotification) {
        if (statusBarNotification == null) {
            Log.e(TAG, "onNotificationRemoved(): received a null StatusBarNotification");
            return;
        }
        String str = TAG;
        Log.d(str, "Notification removed: " + statusBarNotification.getKey());
        if (((Integer) this.mHasExternalRenderer.getValue()).intValue() == 0) {
            Intent intent = new Intent(NOTIFICATION_DISMISS_ACTION);
            intent.putExtra(ID_EXTRA, getNotificationVrApiId(statusBarNotification));
            String str2 = TAG;
            Log.d(str2, "Broadcasting a dismiss event to VrApi: " + intent);
            broadcast(intent, NO_PERMISSION);
        }
    }

    private boolean shouldDisplayNotification(StatusBarNotification statusBarNotification) {
        if (checkFilter(this.mPackageBlacklist, statusBarNotification)) {
            return false;
        }
        if (isDoNotDisturbModeEnabled()) {
            if (checkFilter(this.mHighPriorityNotifications, statusBarNotification)) {
                return true;
            }
            Log.d(TAG, "Do not disturb mode is set, not showing notification");
            return false;
        } else if (statusBarNotification.getNotification().priority >= ((Integer) this.mNotificationMinLevel.getValue()).intValue() || checkFilter(this.mPackageWhitelist, statusBarNotification)) {
            return true;
        } else {
            return false;
        }
    }

    private void broadcast(Intent intent, String str) {
        int parcelableSize = getParcelableSize(intent);
        if (parcelableSize > PARCELABLE_MAX_SIZE) {
            String str2 = TAG;
            Log.w(str2, intent.toString() + " too large (" + parcelableSize + " bytes), dropping");
            return;
        }
        try {
            this.mContext.sendBroadcastAsUser(intent, UserHandle.SYSTEM, str);
        } catch (RuntimeException e) {
            if (e.getCause() instanceof TransactionTooLargeException) {
                String str3 = TAG;
                Log.w(str3, "Transaction too large: " + intent, e);
                return;
            }
            throw e;
        }
    }

    private int getParcelableSize(Parcelable parcelable) {
        Parcel obtain = Parcel.obtain();
        try {
            obtain.writeParcelable(parcelable, 0);
            return obtain.dataSize();
        } finally {
            obtain.recycle();
        }
    }

    private Bitmap renderNotification(Notification notification) {
        Resources resources = this.mContext.getResources();
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.notification_view_width);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.notification_view_max_height);
        View createNotificationView = createNotificationView(notification);
        createNotificationView.measure(View.MeasureSpec.makeMeasureSpec(dimensionPixelSize, 1073741824), View.MeasureSpec.makeMeasureSpec(dimensionPixelSize2, Integer.MIN_VALUE));
        int dimensionPixelSize3 = resources.getDimensionPixelSize(R.dimen.bitmap_width);
        float measuredWidth = ((float) dimensionPixelSize3) / ((float) createNotificationView.getMeasuredWidth());
        Bitmap createBitmap = Bitmap.createBitmap(dimensionPixelSize3, (int) ((((float) createNotificationView.getMeasuredHeight()) * measuredWidth) + 0.5f), Bitmap.Config.ARGB_8888);
        createBitmap.eraseColor(resources.getColor(R.color.notification_bg));
        Canvas canvas = new Canvas(createBitmap);
        canvas.scale(measuredWidth, measuredWidth);
        createNotificationView.layout(0, 0, createNotificationView.getMeasuredWidth(), createNotificationView.getMeasuredHeight());
        createNotificationView.draw(canvas);
        return createBitmap;
    }

    private View createNotificationView(Notification notification) {
        Notification.Builder recoverBuilder = Notification.Builder.recoverBuilder(this.mContext, notification);
        RemoteViews createBigContentView = recoverBuilder.createBigContentView();
        if (createBigContentView == null) {
            createBigContentView = recoverBuilder.createContentView();
        }
        return createBigContentView.apply(this.mContext, null);
    }

    private long getNotificationVrApiId(StatusBarNotification statusBarNotification) {
        return (long) statusBarNotification.getKey().hashCode();
    }

    private int getNotificationDuration(StatusBarNotification statusBarNotification) {
        return ((Integer) this.mNotificationDurationSeconds.getValue()).intValue();
    }

    private boolean checkFilter(Set<String> set, StatusBarNotification statusBarNotification) {
        String packageName = statusBarNotification.getPackageName();
        String tag = statusBarNotification.getTag();
        int id = statusBarNotification.getId();
        if (!set.contains(packageName)) {
            if (!set.contains(String.format("%s|%s", packageName, tag))) {
                if (!set.contains(String.format("%s|%s|%d", packageName, tag, Integer.valueOf(id)))) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isDoNotDisturbModeEnabled() {
        return this.mSettingsManager.getBoolean("do_not_disturb", false, ActivityManagerUtils.getUserIdFromHandle(ActivityManagerUtils.getCurrentUser()));
    }

    /* access modifiers changed from: private */
    public static abstract class BaseSettingHolder<T> extends ContentObserver {
        private final String mKey;
        private T mValue;

        /* access modifiers changed from: package-private */
        public abstract T readValue(String str);

        public BaseSettingHolder(String str, Handler handler, T t) {
            super(handler);
            this.mKey = str;
            this.mValue = t;
        }

        /* access modifiers changed from: package-private */
        public T getValue() {
            return this.mValue;
        }

        /* access modifiers changed from: package-private */
        public void registerObserver(Context context) {
            context.getContentResolver().registerContentObserver(Settings.Global.getUriFor(this.mKey), false, this);
            onChange(false);
        }

        public void onChange(boolean z) {
            onChange(z, null);
        }

        public void onChange(boolean z, Uri uri) {
            T readValue = readValue(this.mKey);
            if (readValue != null) {
                String str = OculusNotificationListenerService.TAG;
                Log.d(str, "Read a new value for " + this.mKey + ": " + ((Object) readValue));
                this.mValue = readValue;
            }
        }
    }

    /* access modifiers changed from: private */
    public static class IntParameter extends BaseSettingHolder<Integer> {
        private final Context mContext;

        public IntParameter(Context context, Handler handler, String str, int i) {
            super(str, handler, Integer.valueOf(context.getResources().getInteger(i)));
            this.mContext = context;
        }

        /* access modifiers changed from: package-private */
        @Override // com.oculus.notification_proxy.OculusNotificationListenerService.BaseSettingHolder
        public Integer readValue(String str) {
            try {
                return Integer.valueOf(Settings.Global.getInt(this.mContext.getContentResolver(), str));
            } catch (Settings.SettingNotFoundException unused) {
                return null;
            }
        }
    }

    private static class FloatParameter extends BaseSettingHolder<Float> {
        private final Context mContext;

        public FloatParameter(Context context, Handler handler, String str, int i) {
            super(str, handler, Float.valueOf(getFromResources(context.getResources(), i)));
            this.mContext = context;
        }

        /* access modifiers changed from: package-private */
        @Override // com.oculus.notification_proxy.OculusNotificationListenerService.BaseSettingHolder
        public Float readValue(String str) {
            try {
                return Float.valueOf(Settings.Global.getFloat(this.mContext.getContentResolver(), str));
            } catch (Settings.SettingNotFoundException unused) {
                return null;
            }
        }

        private static float getFromResources(Resources resources, int i) {
            TypedValue typedValue = new TypedValue();
            try {
                resources.getValue(i, typedValue, true);
            } catch (Resources.NotFoundException unused) {
                String str = OculusNotificationListenerService.TAG;
                Log.e(str, "Resource id " + i + " is missing");
            }
            return typedValue.getFloat();
        }
    }
}
