package com.oculus.panelapp.anytimeui.toast;

import android.content.Context;
import android.database.Cursor;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.PowerManager;
import android.os.SystemClock;
import android.service.notification.StatusBarNotification;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import androidx.collection.SimpleArrayMap;
import com.facebook.mobileconfigservice.serviceconstants.MobileConfigServiceConstants;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.notifications.NotificationConstants;
import com.oculus.os.SettingsManager;
import com.oculus.panelapp.anytimeui.R;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class NotificationSoundManager {
    private static final String DBG_NOTIFICATION_SOUND = "persist.oculus.dbg_notification_sound";
    private static final String DBG_NOTIFICATION_SOUND_ATTENUATION = "persist.oculus.dbg_notification_sound_attenuation";
    private static final float DEFAULT_SOUND_ATTENUATION = 0.8f;
    private static final String HORIZON_GK_COLUMN = "gk_enabled";
    private static final String HORIZON_GK_CONTENT_URI = "content://com.oculus.horizon.gatekeeper/fetch?name=";
    private static final String HORIZON_GK_NOTIFICATION_SOUND_GK_NAME = "oculus_new_notifications_sound";
    private static final long SOUND_COOLDOWN_MS = 500;
    private static final long SOUND_SUPPRESSED_UPTIME_MS = 30000;
    private static final String TAG = LoggingUtil.tag(NotificationSoundManager.class);
    private final AudioManager mAudioManager;
    private final Context mContext;
    private String mLastNotificationKey;
    private long mLastSoundTime = 0;
    private final SimpleArrayMap<Integer, List<Pair<OnNotificationSoundReadyStep, String>>> mPendingSoundLoadListeners;
    private final SettingsManager mSettingsManager;
    private final int mSoundIdDefaultNotification;
    private final SoundPool mSoundPool;

    NotificationSoundManager(Context context) {
        this.mContext = context;
        this.mSettingsManager = new SettingsManager();
        this.mAudioManager = (AudioManager) this.mContext.getSystemService("audio");
        this.mSoundPool = new SoundPool.Builder().setAudioAttributes(new AudioAttributes.Builder().setUsage(10).setContentType(4).build()).build();
        this.mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            /* class com.oculus.panelapp.anytimeui.toast.$$Lambda$NotificationSoundManager$KL1pL7sOp2SEw8D1bo1GyQXJafc */

            public final void onLoadComplete(SoundPool soundPool, int i, int i2) {
                NotificationSoundManager.this.lambda$new$80$NotificationSoundManager(soundPool, i, i2);
            }
        });
        this.mSoundIdDefaultNotification = this.mSoundPool.load(this.mContext, R.raw.oc_notif_toast, 1);
        this.mPendingSoundLoadListeners = new SimpleArrayMap<>();
    }

    private static boolean isNotificationSoundGKEnabled(Context context) {
        return fetchHorizonGK(context, HORIZON_GK_NOTIFICATION_SOUND_GK_NAME);
    }

    private static boolean fetchHorizonGK(Context context, String str) {
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(Uri.parse(HORIZON_GK_CONTENT_URI + str), null, null, null, null);
            boolean z = false;
            if (cursor != null) {
                if (cursor.moveToNext()) {
                    if (cursor.getInt(cursor.getColumnIndexOrThrow("gk_enabled")) == 1) {
                        z = true;
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                    return z;
                }
            }
            return false;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void playNotificationSoundIfEligibleAndThen(StatusBarNotification statusBarNotification, OnNotificationSoundReadyStep onNotificationSoundReadyStep) {
        if (!isNotifEligibleForToastSound(statusBarNotification)) {
            onNotificationSoundReadyStep.onNotificationSoundReadyStep();
        } else if (hasCustomSound(statusBarNotification)) {
            loadNotificationSoundAndThen(statusBarNotification, onNotificationSoundReadyStep);
        } else {
            onNotificationSoundReadyStep.onNotificationSoundReadyStep();
            playSoundWithId(this.mSoundIdDefaultNotification, statusBarNotification.getKey());
        }
    }

    public void onNotificationDismissed(String str) {
        String str2 = this.mLastNotificationKey;
        if (str2 != null && str2.equals(str)) {
            this.mLastNotificationKey = null;
        }
    }

    public void onNotificationCompleted() {
        this.mLastNotificationKey = null;
    }

    private boolean isNotifEligibleForToastSound(StatusBarNotification statusBarNotification) {
        if (!isNewNotificationsSoundEnabled()) {
            return false;
        }
        try {
            if (statusBarNotification.getNotification().extras.getBoolean(NotificationConstants.KEY_PREVENT_SOUND) || !((PowerManager) this.mContext.getSystemService("power")).isInteractive() || SystemClock.uptimeMillis() < SOUND_SUPPRESSED_UPTIME_MS) {
                return false;
            }
            if (this.mLastSoundTime + 500 > System.currentTimeMillis()) {
                return false;
            }
            String key = statusBarNotification.getKey();
            String str = this.mLastNotificationKey;
            if (str == null || !str.equals(key)) {
                return true;
            }
            return false;
        } catch (Exception unused) {
            Log.e(TAG, "Failed to load whether the notification prevented sound.");
            return false;
        }
    }

    private boolean isNewNotificationsSoundEnabled() {
        if (!this.mSettingsManager.getBoolean(SettingsManager.NEW_NOTIFICATIONS_SOUND, true) || (!isNotificationSoundGKEnabled(this.mContext) && !isNotificationSoundSysPropEnabled())) {
            return false;
        }
        return true;
    }

    private int getCustomSoundResourceId(StatusBarNotification statusBarNotification) {
        return statusBarNotification.getNotification().extras.getInt(NotificationConstants.KEY_CUSTOM_SOUND_RESOURCE_ID, 0);
    }

    private boolean hasCustomSound(StatusBarNotification statusBarNotification) {
        return getCustomSoundResourceId(statusBarNotification) > 0;
    }

    private void loadNotificationSoundAndThen(StatusBarNotification statusBarNotification, OnNotificationSoundReadyStep onNotificationSoundReadyStep) {
        if (!isNotifEligibleForToastSound(statusBarNotification)) {
            onNotificationSoundReadyStep.onNotificationSoundReadyStep();
            return;
        }
        try {
            int customSoundResourceId = getCustomSoundResourceId(statusBarNotification);
            Context createPackageContext = this.mContext.createPackageContext(statusBarNotification.getPackageName(), 0);
            synchronized (this.mPendingSoundLoadListeners) {
                int load = this.mSoundPool.load(createPackageContext, customSoundResourceId, 1);
                List<Pair<OnNotificationSoundReadyStep, String>> list = this.mPendingSoundLoadListeners.get(Integer.valueOf(load));
                if (list == null) {
                    list = new LinkedList<>();
                    this.mPendingSoundLoadListeners.put(Integer.valueOf(load), list);
                }
                list.add(Pair.create(onNotificationSoundReadyStep, statusBarNotification.getKey()));
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception loading custom sound, using default", e);
            onNotificationSoundReadyStep.onNotificationSoundReadyStep();
            playSoundWithId(this.mSoundIdDefaultNotification, statusBarNotification.getKey());
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: processLoadedSounds */
    public void lambda$new$80$NotificationSoundManager(SoundPool soundPool, int i, int i2) {
        if (i != this.mSoundIdDefaultNotification) {
            synchronized (this.mPendingSoundLoadListeners) {
                List<Pair<OnNotificationSoundReadyStep, String>> list = this.mPendingSoundLoadListeners.get(Integer.valueOf(i));
                if (list != null) {
                    if (list.size() != 0) {
                        ListIterator<Pair<OnNotificationSoundReadyStep, String>> listIterator = list.listIterator();
                        while (listIterator.hasNext()) {
                            Pair<OnNotificationSoundReadyStep, String> next = listIterator.next();
                            String str = (String) next.second;
                            ((OnNotificationSoundReadyStep) next.first).onNotificationSoundReadyStep();
                            if (i2 == 0) {
                                playSoundWithId(i, str);
                            } else {
                                Log.e(TAG, "Failed to load custom sound, using default");
                                playSoundWithId(this.mSoundIdDefaultNotification, str);
                            }
                            listIterator.remove();
                        }
                        this.mSoundPool.unload(i);
                        return;
                    }
                }
                this.mSoundPool.unload(i);
            }
        }
    }

    private synchronized void playSoundWithId(int i, String str) {
        long currentTimeMillis = System.currentTimeMillis();
        if (this.mLastSoundTime + 500 <= currentTimeMillis) {
            this.mLastSoundTime = currentTimeMillis;
            this.mLastNotificationKey = str;
            int streamVolume = this.mAudioManager.getStreamVolume(3);
            int streamMaxVolume = this.mAudioManager.getStreamMaxVolume(3);
            float notificationSoundAttenuation = (streamMaxVolume != 0 ? ((float) streamVolume) / ((float) streamMaxVolume) : 0.0f) * getNotificationSoundAttenuation();
            if (this.mSoundPool.play(i, notificationSoundAttenuation, notificationSoundAttenuation, 1, 0, 1.0f) == 0) {
                String str2 = TAG;
                Log.e(str2, "Cound not play sound with soundId " + i);
            }
        }
    }

    private String getSystemProperty(String str) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getDeclaredMethod(MobileConfigServiceConstants.PATH_GET, String.class).invoke(null, str);
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "Failed to fetch system property: '" + str + "'.", e);
            return null;
        }
    }

    private boolean isNotificationSoundSysPropEnabled() {
        String systemProperty = getSystemProperty(DBG_NOTIFICATION_SOUND);
        if (TextUtils.isEmpty(systemProperty)) {
            return false;
        }
        if (Boolean.parseBoolean(systemProperty) || "1".equals(systemProperty)) {
            return true;
        }
        return false;
    }

    private float getNotificationSoundAttenuation() {
        String systemProperty = getSystemProperty(DBG_NOTIFICATION_SOUND_ATTENUATION);
        if (TextUtils.isEmpty(systemProperty)) {
            return DEFAULT_SOUND_ATTENUATION;
        }
        try {
            String str = TAG;
            Log.i(str, "Using sound attenuation system property of " + systemProperty + ".");
            return Float.parseFloat(systemProperty);
        } catch (Exception e) {
            Log.e(TAG, "Failed to parse sound attenuation system property.", e);
            return DEFAULT_SOUND_ATTENUATION;
        }
    }
}
