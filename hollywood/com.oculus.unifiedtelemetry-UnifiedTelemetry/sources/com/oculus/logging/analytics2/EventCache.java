package com.oculus.logging.analytics2;

import X.AbstractC0096Hu;
import X.AbstractC0247Xu;
import X.Mu;
import X.QC;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import androidx.annotation.VisibleForTesting;
import com.facebook.acra.util.minidump.MinidumpReader;
import com.facebook.inject.Assisted;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.directboot.DirectBootUtils;
import com.oculus.directboot.ForDeviceProtectedStorage;
import com.oculus.logging.EventFactory;
import com.oculus.logging.EventTag;
import com.oculus.logging.OculusLoggingEvent;
import com.oculus.util.thread.ThreadUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_oculus_directboot_ForDeviceProtectedStorage_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_analytics2_RtcClock_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_util_thread_ThreadUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_logging_analytics2_FileOps_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_directboot_DirectBootUtils_ULSEP_BINDING_ID"})
@SuppressLint({"ApplySharedPref", "BadMethodUse-android.content.Context.getSharedPreferences", "BadMethodUse-java.lang.Class.getSimpleName", "ConstructorInjectedContext", "SharedPreferencesUse"})
@TargetApi(MinidumpReader.MODULE_LIST_OFFSET)
public class EventCache {
    @VisibleForTesting
    public static final int BUFFER_SIZE = 65536;
    public static final String CACHE_SHARED_PREFERENCE_FILE = "oculus.event.cache.pref";
    public static final String EVENT_CACHE_FILE_NAME = "event_cache.txt";
    public static final String EVENT_NAME_KEY = "utl_name";
    @VisibleForTesting
    public static final String EVENT_RTC_TIME = "utl_rtc_timestamp";
    public static final String MODULE_NAME_KEY = "utl_module";
    public static final String TAG = "EventCache";
    public static final String TIME_SET = "time_set";
    public static final long ZERO_EPOCH = 0;
    public QC _UL_mInjectionContext;
    public final SharedPreferences mDeviceProtectedPrefs;
    @VisibleForTesting
    public ByteBuffer mEventBuffer;
    public final EventFactory mEventFactory;
    public AtomicBoolean mEventFlushInProgress = new AtomicBoolean(false);
    public final Object mFileWriteLock = new Object();
    public Handler mHandler = new Handler(Looper.getMainLooper());
    @Nullable
    public SharedPreferences mPrefs;
    public AtomicLong mRtcTime;
    public boolean mTimeSet;

    /* renamed from: com.oculus.logging.analytics2.EventCache$1  reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        public final void run() {
            EventCache.this.mRtcTime.incrementAndGet();
            if (!EventCache.this.A04()) {
                EventCache eventCache = EventCache.this;
                eventCache.mHandler.postDelayed(new AnonymousClass1(), TimeUnit.SECONDS.toMillis(1));
            }
        }
    }

    private synchronized SharedPreferences A00() {
        SharedPreferences sharedPreferences;
        sharedPreferences = this.mPrefs;
        if (sharedPreferences == null) {
            sharedPreferences = ((Context) AbstractC0096Hu.A03(0, 3, this._UL_mInjectionContext)).getSharedPreferences(CACHE_SHARED_PREFERENCE_FILE, 0);
            this.mPrefs = sharedPreferences;
        }
        return sharedPreferences;
    }

    public final void A03() {
        synchronized (this) {
            ByteBuffer byteBuffer = this.mEventBuffer;
            if (byteBuffer != null) {
                byte[] bArr = new byte[byteBuffer.position()];
                this.mEventBuffer.flip();
                this.mEventBuffer.get(bArr);
                this.mEventBuffer.clear();
                A01(this, bArr);
            }
        }
    }

    public final synchronized boolean A04() {
        return this.mTimeSet;
    }

    public static void A01(EventCache eventCache, byte[] bArr) {
        try {
            synchronized (eventCache.mFileWriteLock) {
                if (bArr.length > 0) {
                    FileOutputStream openFileOutput = ((Context) AbstractC0096Hu.A03(0, 3, eventCache._UL_mInjectionContext)).openFileOutput(EVENT_CACHE_FILE_NAME, 32768);
                    openFileOutput.write(bArr);
                    openFileOutput.close();
                }
            }
        } catch (IOException e) {
            Mu.A02(TAG, "Failed to write bytes to file", e);
        }
    }

    public final void A02() {
        AtomicBoolean atomicBoolean;
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            throw new IllegalStateException(ThreadUtils.CANT_BE_UI_THREAD);
        }
        boolean z = false;
        boolean z2 = true;
        int i = 1;
        if (this.mEventFlushInProgress.compareAndSet(false, true)) {
            A03();
            try {
                String concat = ((Context) AbstractC0096Hu.A03(0, 3, this._UL_mInjectionContext)).getFilesDir().getCanonicalPath().concat("/event_cache.txt");
                if (concat != null && new File(concat).length() > 0) {
                    long currentTimeMillis = System.currentTimeMillis();
                    long A00 = ((RtcClock) AbstractC0096Hu.A03(2, 30, this._UL_mInjectionContext)).A00() * TimeUnit.SECONDS.toMillis(1);
                    synchronized (this.mFileWriteLock) {
                        try {
                            int i2 = A00().getInt("bytes_flushed", 0);
                            try {
                                RandomAccessFile randomAccessFile = new RandomAccessFile(concat, "r");
                                if (i2 > 0) {
                                    randomAccessFile.seek((long) i2);
                                }
                                Scanner scanner = new Scanner(new BufferedInputStream(Channels.newInputStream(randomAccessFile.getChannel())));
                                scanner.useDelimiter("\n");
                                while (scanner.hasNext()) {
                                    String next = scanner.next();
                                    int length = next.getBytes(StandardCharsets.UTF_8).length + i + i2;
                                    long j = currentTimeMillis - A00;
                                    try {
                                        JSONObject jSONObject = new JSONObject(next);
                                        String optString = jSONObject.optString(EVENT_NAME_KEY, null);
                                        jSONObject.remove(EVENT_NAME_KEY);
                                        if (optString == null) {
                                            Mu.A00(TAG, "Cached event JSON has no event name");
                                        } else {
                                            String optString2 = jSONObject.optString(MODULE_NAME_KEY, null);
                                            jSONObject.remove(MODULE_NAME_KEY);
                                            long j2 = 0;
                                            long optLong = jSONObject.optLong(EVENT_RTC_TIME, 0);
                                            jSONObject.remove(EVENT_RTC_TIME);
                                            if (optLong != 0) {
                                                j2 = j + (optLong * TimeUnit.SECONDS.toMillis(1));
                                            }
                                            OculusLoggingEvent A3T = this.mEventFactory.A3T(optString2, optString, false);
                                            if (A3T.A3I()) {
                                                A3T.A1A(jSONObject.toString());
                                                A3T.A59(j2);
                                                Object remove = jSONObject.remove(EventTag.EVENT_TAG);
                                                if (remove != null) {
                                                    EventTag.A00(A3T, ((Integer) remove).intValue());
                                                }
                                                A3T.A3Q();
                                            }
                                        }
                                    } catch (JSONException e) {
                                        Mu.A02(TAG, "Failed to parse cached event JSON", e);
                                    }
                                    A00().edit().putInt("bytes_flushed", length).commit();
                                    try {
                                        Thread.sleep(TimeUnit.SECONDS.toMillis(1));
                                        i2 = length;
                                        i = 1;
                                    } catch (InterruptedException e2) {
                                        Mu.A02(TAG, "Sleep interrupted", e2);
                                        i2 = length;
                                    }
                                }
                            } catch (FileNotFoundException unused) {
                            }
                            File file = new File(concat);
                            if (((long) i2) >= file.length() && !file.delete()) {
                                Mu.A00(TAG, "Failed to delete file after flushing");
                            }
                        } catch (IOException e3) {
                            Mu.A02(TAG, "Error while reading and flushing cache", e3);
                        }
                    }
                    atomicBoolean = this.mEventFlushInProgress;
                    z = false;
                    z2 = true;
                    atomicBoolean.compareAndSet(z2, z);
                }
            } catch (IOException e4) {
                Mu.A02(TAG, "Unable to set cache file path", e4);
            }
            atomicBoolean = this.mEventFlushInProgress;
            atomicBoolean.compareAndSet(z2, z);
        }
    }

    @Inject
    public EventCache(AbstractC0247Xu xu, @ForDeviceProtectedStorage Context context, @Assisted EventFactory eventFactory) {
        boolean z;
        this._UL_mInjectionContext = new QC(6, xu);
        this.mEventFactory = eventFactory;
        SharedPreferences sharedPreferences = context.getSharedPreferences(CACHE_SHARED_PREFERENCE_FILE, 0);
        this.mDeviceProtectedPrefs = sharedPreferences;
        if (sharedPreferences.getBoolean(TIME_SET, false) || !((DirectBootUtils) AbstractC0096Hu.A03(5, 121, this._UL_mInjectionContext)).A01()) {
            z = true;
        } else {
            z = A00().getBoolean(TIME_SET, false);
        }
        this.mTimeSet = z;
        if (z) {
            this.mRtcTime = new AtomicLong(0);
            return;
        }
        this.mRtcTime = new AtomicLong(((RtcClock) AbstractC0096Hu.A03(2, 30, this._UL_mInjectionContext)).A00());
        this.mHandler.postDelayed(new AnonymousClass1(), TimeUnit.SECONDS.toMillis(1));
    }
}
