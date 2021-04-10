package com.oculus.fitnesstracker.provider;

import android.content.ComponentCallbacks2;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.util.Log;
import com.facebook.proguard.annotations.DoNotStrip;
import com.oculus.fitnesstracker.FitnessDataManager;
import com.oculus.fitnesstracker.FitnessLogger;
import com.oculus.fitnesstracker.PanelApplication;
import com.oculus.fitnesstracker.provider.ContentType;
import com.oculus.fitnesstracker.provider.FitnessToast;
import com.oculus.os.SettingsManager;
import com.oculus.os.SettingsObserverCallback;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

public class FitnessDataProvider extends ContentProvider implements ComponentCallbacks2 {
    private static final String SETTING_FITNESS_TRACKING_NOTIFICATIONS_ENABLED = "fitness_tracking_notifications_enabled";
    private static final String TAG = "FitnessDataProvider";
    private static FitnessDataManager mFitnessDataManager = null;
    public static final int pluginVersionChange = 0;
    public static final int pluginVersionMajor = 23;
    public static final int pluginVersionMinor = 0;
    public static final int pluginVersionPatch = 0;
    private static String runningPackageName = "";
    private static Context sContext = null;
    private static boolean scheduleShutdown = false;
    private boolean mNotificationsEnabled;
    private SettingsManager mSettingsManager;
    private SettingsObserverCallback mSettingsObserverCallback;
    private Calendar startOfDay;

    public Bundle call(String str, String str2, Bundle bundle) {
        return null;
    }

    public String getType(Uri uri) {
        return null;
    }

    public boolean onCreate() {
        PanelApplication.setInitOnCreate(false);
        Context context = getContext();
        sContext = context;
        mFitnessDataManager = FitnessDataManager.getInstance(context);
        this.startOfDay = Calendar.getInstance();
        this.startOfDay.set(10, 0);
        this.startOfDay.set(12, 0);
        this.startOfDay.set(13, 0);
        this.startOfDay.set(14, 0);
        if (mFitnessDataManager.getMostRecentCachedDayStart() < this.startOfDay.getTimeInMillis()) {
            mFitnessDataManager.updateDailyDataTable();
        }
        this.mSettingsManager = new SettingsManager();
        this.mSettingsObserverCallback = new SettingsObserverCallback() {
            /* class com.oculus.fitnesstracker.provider.FitnessDataProvider.AnonymousClass1 */

            @DoNotStrip
            public final void onSettingChange(String str) {
                FitnessDataProvider.this.updateNotificationSetting();
            }
        };
        this.mSettingsManager.registerSettingsObserver(SETTING_FITNESS_TRACKING_NOTIFICATIONS_ENABLED, this.mSettingsObserverCallback, new Handler(sContext.getMainLooper()));
        updateNotificationSetting();
        FitnessLogger.log(sContext, FitnessLogger.EventType.data_provider_create);
        return true;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void updateNotificationSetting() {
        this.mNotificationsEnabled = this.mSettingsManager.getBoolean(SETTING_FITNESS_TRACKING_NOTIFICATIONS_ENABLED, false);
    }

    public void onTrimMemory(int i) {
        if (i >= 15 && !scheduleShutdown) {
            scheduleShutdown = true;
            Log.d(TAG, "OnTrimMemory: Schedule Kill Process");
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("package_name", runningPackageName);
                FitnessLogger.log(sContext, FitnessLogger.EventType.trim_memory, jSONObject);
            } catch (JSONException e) {
                Log.e(TAG, "Failed to log memory trim", e);
            }
        }
    }

    private Uri insertResponse() {
        if (!scheduleShutdown) {
            return null;
        }
        new Timer().schedule(new TimerTask() {
            /* class com.oculus.fitnesstracker.provider.FitnessDataProvider.AnonymousClass2 */

            public final void run() {
                String str = FitnessDataProvider.TAG;
                Log.d(str, "Killing Process: " + Process.myPid());
                Process.killProcess(Process.myPid());
            }
        }, 1000);
        return FitnessDataContract.uriForShutdown();
    }

    private void sendNotifIfEnabled(FitnessToast.ToastType toastType) {
        if (this.mNotificationsEnabled) {
            FitnessToast.send(sContext, toastType);
        }
    }

    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        ContentType contentTypeFor = Constants.getContentTypeFor(uri);
        if (contentTypeFor == null) {
            Log.e(TAG, "Invalid uri");
            return null;
        } else if (AnonymousClass3.$SwitchMap$com$oculus$fitnesstracker$provider$ContentType$QueryMethod[contentTypeFor.queryMethod.ordinal()] != 1) {
            Log.e(TAG, "Invalid uri");
            return null;
        } else {
            Calendar instance = Calendar.getInstance();
            instance.set(11, 0);
            instance.set(12, 0);
            instance.set(13, 0);
            instance.set(14, 0);
            return FitnessDataConverter.getCursorFromDailySummaryData(mFitnessDataManager.readDailySummaryData(instance.getTimeInMillis(), new ArrayList(0)));
        }
    }

    /* renamed from: com.oculus.fitnesstracker.provider.FitnessDataProvider$3  reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$fitnesstracker$provider$ContentType$QueryMethod = new int[ContentType.QueryMethod.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        static {
            /*
                com.oculus.fitnesstracker.provider.ContentType$QueryMethod[] r0 = com.oculus.fitnesstracker.provider.ContentType.QueryMethod.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.fitnesstracker.provider.FitnessDataProvider.AnonymousClass3.$SwitchMap$com$oculus$fitnesstracker$provider$ContentType$QueryMethod = r0
                int[] r0 = com.oculus.fitnesstracker.provider.FitnessDataProvider.AnonymousClass3.$SwitchMap$com$oculus$fitnesstracker$provider$ContentType$QueryMethod     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.fitnesstracker.provider.ContentType$QueryMethod r1 = com.oculus.fitnesstracker.provider.ContentType.QueryMethod.CALORIES_DAILY_SUMMARY     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.fitnesstracker.provider.FitnessDataProvider.AnonymousClass3.$SwitchMap$com$oculus$fitnesstracker$provider$ContentType$QueryMethod     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.fitnesstracker.provider.ContentType$QueryMethod r1 = com.oculus.fitnesstracker.provider.ContentType.QueryMethod.EFFORT     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.fitnesstracker.provider.FitnessDataProvider.AnonymousClass3.<clinit>():void");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x00de, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        android.util.Log.e(com.oculus.fitnesstracker.provider.FitnessDataProvider.TAG, "Exception during calorie insert");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00ef, code lost:
        com.oculus.fitnesstracker.FitnessDataManager.insertCalories(r7, r3, r28.getAsInteger(com.oculus.fitnesstracker.database.FitnessTrackerMoveContract.Calories.IS_ACTIVE).intValue(), com.oculus.fitnesstracker.provider.FitnessDataProvider.runningPackageName);
        r0 = null;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x00e8 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public android.net.Uri insert(android.net.Uri r27, android.content.ContentValues r28) {
        /*
        // Method dump skipped, instructions count: 494
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.fitnesstracker.provider.FitnessDataProvider.insert(android.net.Uri, android.content.ContentValues):android.net.Uri");
    }

    /* access modifiers changed from: package-private */
    public void handleGoalNotifs(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
        if (!z4) {
            if (z8) {
                sendNotifIfEnabled(FitnessToast.ToastType.daily_goal_complete);
            } else if (!z && !z2) {
                if (z5 || z6) {
                    if (z5) {
                        sendNotifIfEnabled(FitnessToast.ToastType.active_time_target_complete);
                    } else if (z6) {
                        sendNotifIfEnabled(FitnessToast.ToastType.calorie_target_complete);
                    }
                } else if (!z3 && z7) {
                    sendNotifIfEnabled(FitnessToast.ToastType.daily_goal_half_complete);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void handleGoalLogging(boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6, boolean z7, boolean z8) {
        if (!z4 && z8) {
            FitnessLogger.log(sContext, FitnessLogger.EventType.daily_goal_complete);
        }
        if (!z3 && z7) {
            FitnessLogger.log(sContext, FitnessLogger.EventType.daily_goal_half_complete);
        }
        if (!z && z5) {
            FitnessLogger.log(sContext, FitnessLogger.EventType.active_time_goal_complete);
        }
        if (!z2 && z6) {
            FitnessLogger.log(sContext, FitnessLogger.EventType.calories_goal_complete);
        }
    }

    public int delete(Uri uri, String str, String[] strArr) {
        throw new UnsupportedOperationException("delete not supported");
    }

    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        throw new UnsupportedOperationException("update not supported");
    }
}
