package com.oculus.deviceconfigclient;

import X.AnonymousClass006;
import X.AnonymousClass0MD;
import X.AnonymousClass0Sp;
import X.AnonymousClass0T4;
import X.AnonymousClass0T5;
import X.AnonymousClass0T7;
import X.AnonymousClass0T8;
import X.AnonymousClass0TC;
import X.AnonymousClass0TE;
import X.C01200Sw;
import android.annotation.SuppressLint;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import com.facebook.acra.CrashTimeDataCollector;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.deviceconfigclient.DeviceConfigTelemetryLogger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DeviceConfigClient {
    public static final String CACHED_SOURCE = "CACHED";
    public static final Boolean DEFAULT_BOOLEAN = false;
    public static final Double DEFAULT_DOUBLE = Double.valueOf(0.0d);
    public static final Long DEFAULT_LONG = 0L;
    public static final String DEFAULT_STRING = "";
    public static final String EMPTY_CONFIG_PARAM = "__DEVICE_CONFIG_EMPTY_VALUE__";
    public static final String NOT_AVAILABLE = "N/A";
    public static final String OVERRIDDEN_SOURCE = "OVERRIDDEN";
    public static final String PARAMS_DEFAULT_FILENAME = "params_default.txt";
    public static final String SERVICE_CACHED_SOURCE = "SERVICE_CACHED";
    public static final String TAG = "DeviceConfigClient";
    public static final boolean mDebugLog = false;
    public static final AtomicInteger sInUse = new AtomicInteger();
    @Nullable
    public AsyncFetch mAsyncFetchInstance;
    public int mAsyncFetchWaitTimeInMs;
    @Nullable
    public DeviceConfigCallback mCallback;
    public final ChangeListener mChangeListener;
    public boolean mChangeListenersAdded;
    public final Context mContext;
    public String mCurrentParamsMapVersion;
    public ConfigMemoryState mMemoryState;
    public final Object mMemoryStateLocker;
    public final AnonymousClass0T8 mMobileConfigClient;
    public final AnonymousClass0Sp mMobileConfigOptions;
    public final Map<String, Set<String>> mParamNamesByConfig;
    public final Map<String, Object> mParamsDefaults;
    public final Object mParamsLock;
    public Map<String, C01200Sw> mParamsMapEntries;
    public Set<String> mParamsToPrefetch;
    public Set<String> mSessionLoggingIdsToLogExposure;
    public Set<String> mSessionlessLoggingIdsToLogExposure;
    public final AtomicBoolean mSubscribeSucceeded;

    public class AsyncFetch {
        public final Signal mSignal = new Signal();
        public final AtomicBoolean mStopThread = new AtomicBoolean(false);
        public final Thread mThread;

        public AsyncFetch() {
            AsyncFetchThread asyncFetchThread = new AsyncFetchThread(this);
            this.mThread = asyncFetchThread;
            asyncFetchThread.setName("DeviceConfig-AsyncFetch");
            this.mThread.start();
        }

        /* JADX WARNING: Code restructure failed: missing block: B:45:0x0105, code lost:
            if (r10 != null) goto L_0x0107;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean fetchConfigParams(java.util.Set<java.lang.String> r25) {
            /*
            // Method dump skipped, instructions count: 587
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.deviceconfigclient.DeviceConfigClient.AsyncFetch.fetchConfigParams(java.util.Set):boolean");
        }

        private <Type> CacheUpdateMode queryCacheUpdateMode(String str, Map<String, ValueInfo<Type>> map, Object obj) {
            DeviceConfigClient deviceConfigClient = DeviceConfigClient.this;
            if (deviceConfigClient.mCallback != null) {
                ValueInfo serviceValueFromMemoryState = deviceConfigClient.getServiceValueFromMemoryState(map, str);
                if (serviceValueFromMemoryState == null || !serviceValueFromMemoryState.mValueReturned) {
                    return CacheUpdateMode.InMemoryAndSerialization;
                }
                if (serviceValueFromMemoryState.mValueToOverride == null) {
                    Object value = serviceValueFromMemoryState.getValue();
                    if (value == null) {
                        DeviceConfigTelemetryLogger.logInternalParamError(DeviceConfigClient.this.mContext, "Current value is null", str);
                    } else {
                        value.equals(obj);
                    }
                }
            }
            return CacheUpdateMode.SerializationOnly;
        }

        public boolean doWork() {
            Set<String> set;
            Set<String> set2;
            Set<String> set3;
            if (!DeviceConfigClient.this.mSubscribeSucceeded.get()) {
                return true;
            }
            synchronized (DeviceConfigClient.this.mParamsLock) {
                DeviceConfigClient deviceConfigClient = DeviceConfigClient.this;
                set = deviceConfigClient.mSessionLoggingIdsToLogExposure;
                set2 = deviceConfigClient.mSessionlessLoggingIdsToLogExposure;
                set3 = deviceConfigClient.mParamsToPrefetch;
                deviceConfigClient.mSessionLoggingIdsToLogExposure = new HashSet();
                deviceConfigClient.mSessionlessLoggingIdsToLogExposure = new HashSet();
                deviceConfigClient.mParamsToPrefetch = new HashSet();
            }
            return fetchConfigParams(set3) | logExposures(set, false) | logExposures(set2, true);
        }

        public void shutdown() {
            this.mStopThread.set(true);
            this.mSignal.setSignal();
            try {
                this.mThread.join();
            } catch (InterruptedException unused) {
                DeviceConfigTelemetryLogger.logInternalError(DeviceConfigClient.this.mContext, "Thread join() got interrupted during shutdown");
                Thread.currentThread().interrupt();
            }
        }

        public void signal() {
            this.mSignal.setSignal();
        }

        public void waitForSignal() {
            Signal signal = this.mSignal;
            DeviceConfigClient deviceConfigClient = DeviceConfigClient.this;
            signal.waitForSignal(deviceConfigClient.mContext, (long) deviceConfigClient.mAsyncFetchWaitTimeInMs);
        }

        /* JADX WARNING: Can't wrap try/catch for region: R(6:(2:12|14)(1:13)|15|(2:17|18)|19|20|33) */
        /* JADX WARNING: Code restructure failed: missing block: B:27:0x0056, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0057, code lost:
            X.AnonymousClass0MD.A07("MobileConfigBaseClient", "Could not find mobileconfigservice; is the service running?", r1);
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x004b */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private boolean logExposures(java.util.Set<java.lang.String> r11, boolean r12) {
            /*
                r10 = this;
                boolean r0 = r11.isEmpty()
                if (r0 == 0) goto L_0x0008
                r0 = 0
                return r0
            L_0x0008:
                java.util.Iterator r3 = r11.iterator()
            L_0x000c:
                boolean r0 = r3.hasNext()
                if (r0 == 0) goto L_0x005d
                java.lang.Object r7 = r3.next()
                java.lang.String r7 = (java.lang.String) r7
                com.oculus.deviceconfigclient.DeviceConfigClient r0 = com.oculus.deviceconfigclient.DeviceConfigClient.this
                X.0T8 r0 = r0.mMobileConfigClient
                java.lang.String r2 = "MobileConfigBaseClient"
                android.content.Context r0 = r0.A00
                android.content.ContentResolver r0 = r0.getContentResolver()
                android.net.Uri r5 = X.AnonymousClass0TE.A08
                android.content.ContentProviderClient r4 = r0.acquireUnstableContentProviderClient(r5)
                if (r4 != 0) goto L_0x0032
                java.lang.String r0 = "Content provider for the mobileconfig service not found"
                X.AnonymousClass0MD.A04(r2, r0)     // Catch:{ all -> 0x004f }
                goto L_0x000c
            L_0x0032:
                if (r12 == 0) goto L_0x0035
                goto L_0x0038
            L_0x0035:
                java.lang.String r1 = "0"
                goto L_0x003a
            L_0x0038:
                java.lang.String r1 = "1"
            L_0x003a:
                r0 = 0
                java.lang.String[] r6 = new java.lang.String[]{r1}     // Catch:{ all -> 0x004f }
                java.lang.String[] r8 = new java.lang.String[r0]     // Catch:{ all -> 0x004f }
                r9 = 0
                android.database.Cursor r0 = r4.query(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x004f }
                if (r0 == 0) goto L_0x004b
                r0.close()     // Catch:{ Exception -> 0x004b }
            L_0x004b:
                r4.close()     // Catch:{ RemoteException | SecurityException -> 0x0056 }
                goto L_0x000c
            L_0x004f:
                r0 = move-exception
                if (r4 == 0) goto L_0x0055
                r4.close()     // Catch:{ all -> 0x0055 }
            L_0x0055:
                throw r0
            L_0x0056:
                r1 = move-exception
                java.lang.String r0 = "Could not find mobileconfigservice; is the service running?"
                X.AnonymousClass0MD.A07(r2, r0, r1)
                goto L_0x000c
            L_0x005d:
                r0 = 1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.deviceconfigclient.DeviceConfigClient.AsyncFetch.logExposures(java.util.Set, boolean):boolean");
        }
    }

    public class AsyncFetchThread extends Thread {
        public final AsyncFetch mAsyncFetchInstance;

        public AsyncFetchThread(AsyncFetch asyncFetch) {
            this.mAsyncFetchInstance = asyncFetch;
        }

        public void run() {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                while (true) {
                    if (Thread.interrupted() || this.mAsyncFetchInstance.mStopThread.get()) {
                        break;
                    }
                    if (!this.mAsyncFetchInstance.doWork()) {
                        if (System.currentTimeMillis() - currentTimeMillis > 60000 && DeviceConfigClient.this.clearAsyncFetch(this.mAsyncFetchInstance, true)) {
                            break;
                        }
                    } else {
                        currentTimeMillis = System.currentTimeMillis();
                    }
                    this.mAsyncFetchInstance.waitForSignal();
                }
            } catch (Exception e) {
                DeviceConfigTelemetryLogger.logInternalError(DeviceConfigClient.this.mContext, e);
            }
            DeviceConfigClient.this.clearAsyncFetch(this.mAsyncFetchInstance, false);
        }
    }

    public enum CacheUpdateMode {
        ReturnedValueAndSerialization,
        InMemoryAndSerialization,
        SerializationOnly
    }

    public class ChangeListener {
        public ChangeListener() {
        }

        public void onConfigChanged(String str) {
            Set<String> set = DeviceConfigClient.this.mParamNamesByConfig.get(str);
            if (set != null) {
                String[] strArr = new String[set.size()];
                int i = 0;
                for (String str2 : set) {
                    strArr[i] = str2;
                    i++;
                }
                DeviceConfigClient.this.internalPrefetch(strArr);
                return;
            }
            DeviceConfigTelemetryLogger.logInternalParamError(DeviceConfigClient.this.mContext, "Config could not be found in params_map.txt", str);
        }
    }

    public static class Signal {
        public final AtomicBoolean mSignaled;

        public void setSignal() {
            this.mSignaled.set(true);
            synchronized (this) {
                notifyAll();
            }
        }

        public void waitForSignal(Context context, long j) {
            if (!this.mSignaled.get()) {
                try {
                    synchronized (this) {
                        wait(j);
                    }
                } catch (InterruptedException unused) {
                    DeviceConfigTelemetryLogger.logInternalError(context, "Signal wait() got interrupted");
                    Thread.currentThread().interrupt();
                }
            }
            this.mSignaled.set(false);
        }

        public Signal() {
            this.mSignaled = new AtomicBoolean(false);
        }
    }

    @Nullable
    private Double debugOnlyParseAsDouble(@Nullable String str) {
        if (str == null || !debugOnlyCheckValueStr(str)) {
            return null;
        }
        return Double.valueOf(Double.parseDouble(str));
    }

    @Nullable
    private Long debugOnlyParseAsLong(@Nullable String str) {
        if (str == null || !debugOnlyCheckValueStr(str)) {
            return null;
        }
        return Long.valueOf(Long.parseLong(str));
    }

    public static String getValueSourceString(AnonymousClass0Sp r10) {
        String[] strArr = {"SERVER", "OVERRIDE", "DEFAULT__SERVER_RETURNED_NULL", "DEFAULT__ACCESSED_BEFORE_MC_INIT", "DEFAULT__NO_DATA_ON_DISK", "DEFAULT__ACCESSED_AFTER_MC_DISPOSE", "DEFAULT__MISSING_SERVER_VALUE"};
        int source = r10.A00.getSource();
        if (source < 0 || source >= 7) {
            return CrashTimeDataCollector.ANDROID_RUNTIME_UNKNOWN;
        }
        return strArr[source];
    }

    private void logSynchronizedSet(String str, Set<String> set) {
    }

    @Deprecated
    public void debugOnlyEnableSetOnFetch(boolean z) {
    }

    public boolean getBoolean(String str) {
        String str2;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (!isParamValid(str, 1)) {
                return DEFAULT_BOOLEAN.booleanValue();
            }
            ValueInfo fromMemoryStateAndSendExposureToService = getFromMemoryStateAndSendExposureToService(this.mMemoryState.mBooleanValues, str);
            if (fromMemoryStateAndSendExposureToService == null) {
                return getBooleanDefault(str).booleanValue();
            }
            Boolean bool = (Boolean) fromMemoryStateAndSendExposureToService.getValue();
            Context context = this.mContext;
            DeviceConfigTelemetryLogger.ValueType valueType = DeviceConfigTelemetryLogger.ValueType.BOOLEAN;
            if (fromMemoryStateAndSendExposureToService.mValueToOverride != null) {
                str2 = OVERRIDDEN_SOURCE;
            } else {
                str2 = CACHED_SOURCE;
            }
            DeviceConfigTelemetryLogger.logGetValueSuccess(context, valueType, str, bool, str2, System.currentTimeMillis() - currentTimeMillis);
            if (bool == null) {
                bool = getBooleanDefault(str);
            }
            return bool.booleanValue();
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.BOOLEAN, str, e);
            return getBooleanDefault(str).booleanValue();
        }
    }

    public boolean getDeviceBoolean(String str) {
        Boolean bool;
        String str2;
        try {
            if (!this.mSubscribeSucceeded.get()) {
                DeviceConfigTelemetryLogger.logNotSubscribedError(this.mContext, String.format("Calling getDeviceBoolean(%s) when not subscribed. Return value from local cache.", str));
                return getBoolean(str);
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (!isParamValid(str, 1)) {
                return DEFAULT_BOOLEAN.booleanValue();
            }
            ValueInfo serviceValueFromMemoryState = getServiceValueFromMemoryState(this.mMemoryState.mBooleanValues, str);
            if (serviceValueFromMemoryState == null || (bool = (Boolean) serviceValueFromMemoryState.getValue()) == null) {
                boolean booleanValue = getBooleanDefault(str).booleanValue();
                AnonymousClass0T8 r1 = this.mMobileConfigClient;
                if (r1 == null) {
                    return booleanValue;
                }
                boolean A07 = r1.A07(str, booleanValue, this.mMobileConfigOptions);
                Context context = this.mContext;
                DeviceConfigTelemetryLogger.ValueType valueType = DeviceConfigTelemetryLogger.ValueType.DEVICE_BOOLEAN;
                Boolean valueOf = Boolean.valueOf(A07);
                DeviceConfigTelemetryLogger.logGetValueSuccess(context, valueType, str, valueOf, getValueSourceString(this.mMobileConfigOptions), System.currentTimeMillis() - currentTimeMillis);
                updateMemoryStateValueDirectlyFromService(this.mMemoryState.mBooleanValues, str, valueOf);
                return A07;
            }
            Context context2 = this.mContext;
            DeviceConfigTelemetryLogger.ValueType valueType2 = DeviceConfigTelemetryLogger.ValueType.DEVICE_BOOLEAN;
            if (serviceValueFromMemoryState.mValueToOverride != null) {
                str2 = OVERRIDDEN_SOURCE;
            } else {
                str2 = SERVICE_CACHED_SOURCE;
            }
            DeviceConfigTelemetryLogger.logGetValueSuccess(context2, valueType2, str, bool, str2, System.currentTimeMillis() - currentTimeMillis);
            return bool.booleanValue();
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_BOOLEAN, str, e);
            return getBooleanDefault(str).booleanValue();
        }
    }

    public double getDeviceDouble(String str) {
        Double d;
        String str2;
        try {
            if (!this.mSubscribeSucceeded.get()) {
                DeviceConfigTelemetryLogger.logNotSubscribedError(this.mContext, String.format("Calling getDeviceDouble(%s) when not subscribed. Return value from local cache.", str));
                return getDouble(str);
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (!isParamValid(str, 4)) {
                return DEFAULT_DOUBLE.doubleValue();
            }
            ValueInfo serviceValueFromMemoryState = getServiceValueFromMemoryState(this.mMemoryState.mDoubleValues, str);
            if (serviceValueFromMemoryState == null || (d = (Double) serviceValueFromMemoryState.getValue()) == null) {
                double doubleValue = getDoubleDefault(str).doubleValue();
                AnonymousClass0T8 r3 = this.mMobileConfigClient;
                if (r3 == null) {
                    return doubleValue;
                }
                double A03 = r3.A03(str, doubleValue, this.mMobileConfigOptions);
                Context context = this.mContext;
                DeviceConfigTelemetryLogger.ValueType valueType = DeviceConfigTelemetryLogger.ValueType.DEVICE_DOUBLE;
                Double valueOf = Double.valueOf(A03);
                DeviceConfigTelemetryLogger.logGetValueSuccess(context, valueType, str, valueOf, getValueSourceString(this.mMobileConfigOptions), System.currentTimeMillis() - currentTimeMillis);
                updateMemoryStateValueDirectlyFromService(this.mMemoryState.mDoubleValues, str, valueOf);
                return A03;
            }
            Context context2 = this.mContext;
            DeviceConfigTelemetryLogger.ValueType valueType2 = DeviceConfigTelemetryLogger.ValueType.DEVICE_DOUBLE;
            if (serviceValueFromMemoryState.mValueToOverride != null) {
                str2 = OVERRIDDEN_SOURCE;
            } else {
                str2 = SERVICE_CACHED_SOURCE;
            }
            DeviceConfigTelemetryLogger.logGetValueSuccess(context2, valueType2, str, d, str2, System.currentTimeMillis() - currentTimeMillis);
            return d.doubleValue();
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_DOUBLE, str, e);
            return getDoubleDefault(str).doubleValue();
        }
    }

    public long getDeviceLong(String str) {
        Long l;
        String str2;
        try {
            if (!this.mSubscribeSucceeded.get()) {
                DeviceConfigTelemetryLogger.logNotSubscribedError(this.mContext, String.format("Calling getDeviceLong(%s) when not subscribed. Return value from local cache.", str));
                return getLong(str);
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (!isParamValid(str, 2)) {
                return DEFAULT_LONG.longValue();
            }
            ValueInfo serviceValueFromMemoryState = getServiceValueFromMemoryState(this.mMemoryState.mLongValues, str);
            if (serviceValueFromMemoryState == null || (l = (Long) serviceValueFromMemoryState.getValue()) == null) {
                long longValue = getLongDefault(str).longValue();
                AnonymousClass0T8 r3 = this.mMobileConfigClient;
                if (r3 == null) {
                    return longValue;
                }
                long A04 = r3.A04(str, longValue, this.mMobileConfigOptions);
                Context context = this.mContext;
                DeviceConfigTelemetryLogger.ValueType valueType = DeviceConfigTelemetryLogger.ValueType.DEVICE_LONG;
                Long valueOf = Long.valueOf(A04);
                DeviceConfigTelemetryLogger.logGetValueSuccess(context, valueType, str, valueOf, getValueSourceString(this.mMobileConfigOptions), System.currentTimeMillis() - currentTimeMillis);
                updateMemoryStateValueDirectlyFromService(this.mMemoryState.mLongValues, str, valueOf);
                return A04;
            }
            Context context2 = this.mContext;
            DeviceConfigTelemetryLogger.ValueType valueType2 = DeviceConfigTelemetryLogger.ValueType.DEVICE_LONG;
            if (serviceValueFromMemoryState.mValueToOverride != null) {
                str2 = OVERRIDDEN_SOURCE;
            } else {
                str2 = SERVICE_CACHED_SOURCE;
            }
            DeviceConfigTelemetryLogger.logGetValueSuccess(context2, valueType2, str, l, str2, System.currentTimeMillis() - currentTimeMillis);
            return l.longValue();
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_LONG, str, e);
            return getLongDefault(str).longValue();
        }
    }

    public String getDeviceString(String str) {
        String str2;
        String str3;
        try {
            if (!this.mSubscribeSucceeded.get()) {
                DeviceConfigTelemetryLogger.logNotSubscribedError(this.mContext, String.format("Calling getDeviceString(%s) when not subscribed. Return value from local cache.", str));
                return getString(str);
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (!isParamValid(str, 3)) {
                return "";
            }
            ValueInfo serviceValueFromMemoryState = getServiceValueFromMemoryState(this.mMemoryState.mStringValues, str);
            if (serviceValueFromMemoryState == null || (str2 = (String) serviceValueFromMemoryState.getValue()) == null) {
                String stringDefault = getStringDefault(str);
                AnonymousClass0T8 r1 = this.mMobileConfigClient;
                if (r1 == null) {
                    return stringDefault;
                }
                String A06 = r1.A06(str, stringDefault, this.mMobileConfigOptions);
                DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_STRING, str, A06, getValueSourceString(this.mMobileConfigOptions), System.currentTimeMillis() - currentTimeMillis);
                updateMemoryStateValueDirectlyFromService(this.mMemoryState.mStringValues, str, A06);
                return A06;
            }
            Context context = this.mContext;
            DeviceConfigTelemetryLogger.ValueType valueType = DeviceConfigTelemetryLogger.ValueType.DEVICE_STRING;
            if (serviceValueFromMemoryState.mValueToOverride != null) {
                str3 = OVERRIDDEN_SOURCE;
            } else {
                str3 = SERVICE_CACHED_SOURCE;
            }
            DeviceConfigTelemetryLogger.logGetValueSuccess(context, valueType, str, str2, str3, System.currentTimeMillis() - currentTimeMillis);
            return str2;
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_STRING, str, e);
            return getStringDefault(str);
        }
    }

    public double getDouble(String str) {
        String str2;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (!isParamValid(str, 4)) {
                return DEFAULT_DOUBLE.doubleValue();
            }
            ValueInfo fromMemoryStateAndSendExposureToService = getFromMemoryStateAndSendExposureToService(this.mMemoryState.mDoubleValues, str);
            if (fromMemoryStateAndSendExposureToService == null) {
                return getDoubleDefault(str).doubleValue();
            }
            Double d = (Double) fromMemoryStateAndSendExposureToService.getValue();
            Context context = this.mContext;
            DeviceConfigTelemetryLogger.ValueType valueType = DeviceConfigTelemetryLogger.ValueType.DOUBLE;
            if (fromMemoryStateAndSendExposureToService.mValueToOverride != null) {
                str2 = OVERRIDDEN_SOURCE;
            } else {
                str2 = CACHED_SOURCE;
            }
            DeviceConfigTelemetryLogger.logGetValueSuccess(context, valueType, str, d, str2, System.currentTimeMillis() - currentTimeMillis);
            if (d == null) {
                d = getDoubleDefault(str);
            }
            return d.doubleValue();
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.DOUBLE, str, e);
            return getDoubleDefault(str).doubleValue();
        }
    }

    public long getLong(String str) {
        String str2;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (!isParamValid(str, 2)) {
                return DEFAULT_LONG.longValue();
            }
            ValueInfo fromMemoryStateAndSendExposureToService = getFromMemoryStateAndSendExposureToService(this.mMemoryState.mLongValues, str);
            if (fromMemoryStateAndSendExposureToService == null) {
                return getLongDefault(str).longValue();
            }
            Long l = (Long) fromMemoryStateAndSendExposureToService.getValue();
            Context context = this.mContext;
            DeviceConfigTelemetryLogger.ValueType valueType = DeviceConfigTelemetryLogger.ValueType.LONG;
            if (fromMemoryStateAndSendExposureToService.mValueToOverride != null) {
                str2 = OVERRIDDEN_SOURCE;
            } else {
                str2 = CACHED_SOURCE;
            }
            DeviceConfigTelemetryLogger.logGetValueSuccess(context, valueType, str, l, str2, System.currentTimeMillis() - currentTimeMillis);
            if (l == null) {
                l = getLongDefault(str);
            }
            return l.longValue();
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.LONG, str, e);
            return getLongDefault(str).longValue();
        }
    }

    public String getString(String str) {
        String str2;
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (!isParamValid(str, 3)) {
                return "";
            }
            ValueInfo fromMemoryStateAndSendExposureToService = getFromMemoryStateAndSendExposureToService(this.mMemoryState.mStringValues, str);
            if (fromMemoryStateAndSendExposureToService == null) {
                return getStringDefault(str);
            }
            String str3 = (String) fromMemoryStateAndSendExposureToService.getValue();
            Context context = this.mContext;
            DeviceConfigTelemetryLogger.ValueType valueType = DeviceConfigTelemetryLogger.ValueType.STRING;
            if (fromMemoryStateAndSendExposureToService.mValueToOverride != null) {
                str2 = OVERRIDDEN_SOURCE;
            } else {
                str2 = CACHED_SOURCE;
            }
            DeviceConfigTelemetryLogger.logGetValueSuccess(context, valueType, str, str3, str2, System.currentTimeMillis() - currentTimeMillis);
            if (str3 == null) {
                return getStringDefault(str);
            }
            return str3;
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.STRING, str, e);
            return getStringDefault(str);
        }
    }

    /* renamed from: com.oculus.deviceconfigclient.DeviceConfigClient$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$deviceconfigclient$DeviceConfigClient$CacheUpdateMode;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        static {
            /*
                com.oculus.deviceconfigclient.DeviceConfigClient$CacheUpdateMode[] r0 = com.oculus.deviceconfigclient.DeviceConfigClient.CacheUpdateMode.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.deviceconfigclient.DeviceConfigClient.AnonymousClass2.$SwitchMap$com$oculus$deviceconfigclient$DeviceConfigClient$CacheUpdateMode = r2
                com.oculus.deviceconfigclient.DeviceConfigClient$CacheUpdateMode r0 = com.oculus.deviceconfigclient.DeviceConfigClient.CacheUpdateMode.ReturnedValueAndSerialization     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.deviceconfigclient.DeviceConfigClient$CacheUpdateMode r0 = com.oculus.deviceconfigclient.DeviceConfigClient.CacheUpdateMode.InMemoryAndSerialization     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.deviceconfigclient.DeviceConfigClient$CacheUpdateMode r0 = com.oculus.deviceconfigclient.DeviceConfigClient.CacheUpdateMode.SerializationOnly     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.deviceconfigclient.DeviceConfigClient.AnonymousClass2.<clinit>():void");
        }
    }

    public static class Configuration {
        public String mServicePackage = "com.oculus.horizon";

        public String getMobileConfigServicePackage() {
            return this.mServicePackage;
        }

        public void setMobileConfigServicePackage(String str) {
            this.mServicePackage = str;
        }
    }

    private void addAsyncExposure(String str, String str2, boolean z) {
        Set<String> set;
        boolean add;
        if (z) {
            set = this.mSessionlessLoggingIdsToLogExposure;
        } else {
            set = this.mSessionLoggingIdsToLogExposure;
        }
        synchronized (this.mParamsLock) {
            add = set.add(str);
        }
        if (add) {
            signalAsyncFetch();
        }
    }

    private void addAsyncPrefetch(String[] strArr) {
        boolean addAll;
        HashSet hashSet = new HashSet();
        for (String str : strArr) {
            C01200Sw r0 = this.mParamsMapEntries.get(str);
            if (r0 != null) {
                str = getParamNameForGetMultiple(str, r0.A01);
            } else {
                DeviceConfigTelemetryLogger.logUnknownParam(this.mContext, "Expected to fail with getMultiple()", str);
            }
            hashSet.add(str);
        }
        synchronized (this.mParamsLock) {
            addAll = this.mParamsToPrefetch.addAll(hashSet);
        }
        if (addAll) {
            signalAsyncFetch();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAsyncPrefetchAllParams() {
        boolean addAll;
        HashSet hashSet = new HashSet();
        for (Map.Entry<String, C01200Sw> entry : this.mParamsMapEntries.entrySet()) {
            hashSet.add(getParamNameForGetMultiple(entry.getKey(), entry.getValue().A01));
        }
        synchronized (this.mParamsLock) {
            addAll = this.mParamsToPrefetch.addAll(hashSet);
        }
        if (addAll) {
            signalAsyncFetch();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x007b, code lost:
        if (r0 <= 0) goto L_0x007d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void addChangeListeners() {
        /*
        // Method dump skipped, instructions count: 252
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.deviceconfigclient.DeviceConfigClient.addChangeListeners():void");
    }

    private void asyncFetchShutdown() {
        AsyncFetch asyncFetch;
        synchronized (this.mParamsLock) {
            asyncFetch = this.mAsyncFetchInstance;
        }
        if (asyncFetch != null) {
            asyncFetch.shutdown();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean clearAsyncFetch(AsyncFetch asyncFetch, boolean z) {
        synchronized (this.mParamsLock) {
            if (this.mAsyncFetchInstance != asyncFetch || (z && (!this.mSessionLoggingIdsToLogExposure.isEmpty() || !this.mSessionlessLoggingIdsToLogExposure.isEmpty() || !this.mParamsToPrefetch.isEmpty()))) {
                return false;
            }
            this.mAsyncFetchInstance = null;
            return true;
        }
    }

    private boolean debugOnlyCheckValueStr(@Nullable String str) {
        if (str == null || str.equals(EMPTY_CONFIG_PARAM)) {
            return false;
        }
        return true;
    }

    private void fillParamsDefaults() {
        String str;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.mContext.getAssets().open(PARAMS_DEFAULT_FILENAME)));
            boolean z = true;
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else if (z) {
                        if (!readLine.equals("DeviceConfig,params_default.txt,V1")) {
                            AnonymousClass0MD.A09(TAG, "Unexpected header '%s' for default param file", readLine);
                            break;
                        }
                        z = false;
                    } else if (!readLine.isEmpty()) {
                        String[] split = readLine.split("=", 2);
                        if (split.length != 2) {
                            str = "Incorrect default format for param '%s'";
                        } else {
                            readLine = split[0];
                            String str2 = split[1];
                            C01200Sw r0 = this.mParamsMapEntries.get(readLine);
                            if (r0 == null) {
                                str = "Can't set default for unknown param '%s'";
                            } else {
                                int i = r0.A01;
                                if (i == 1) {
                                    this.mParamsDefaults.put(readLine, Boolean.valueOf(Boolean.parseBoolean(str2)));
                                } else if (i == 2) {
                                    this.mParamsDefaults.put(readLine, Long.valueOf(Long.parseLong(str2)));
                                } else if (i == 3) {
                                    this.mParamsDefaults.put(readLine, str2);
                                } else if (i != 4) {
                                    try {
                                        AnonymousClass0MD.A09(TAG, "Unsupported param type %s", Integer.valueOf(i));
                                    } catch (NumberFormatException unused) {
                                        DeviceConfigTelemetryLogger.logIncorrectDefaultValue(this.mContext, String.format("Cannot convert default value '%s' to type %s", str2, Integer.valueOf(i)), readLine);
                                    }
                                } else {
                                    this.mParamsDefaults.put(readLine, Double.valueOf(Double.parseDouble(str2)));
                                }
                            }
                        }
                        AnonymousClass0MD.A09(TAG, str, readLine);
                    }
                } catch (Throwable unused2) {
                }
            }
            bufferedReader.close();
            return;
            throw th;
        } catch (IOException unused3) {
            DeviceConfigTelemetryLogger.logMissingDefaultFile(this.mContext, "IOException while reading default params file", PARAMS_DEFAULT_FILENAME);
        }
    }

    private Boolean getBooleanDefault(String str) {
        Object obj = this.mParamsDefaults.get(str);
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        return DEFAULT_BOOLEAN;
    }

    private Double getDoubleDefault(String str) {
        Object obj = this.mParamsDefaults.get(str);
        if (obj instanceof Double) {
            return (Double) obj;
        }
        return DEFAULT_DOUBLE;
    }

    @Nullable
    private <Type> ValueInfo<Type> getFromMemoryStateAndSendExposureToService(Map<String, ValueInfo<Type>> map, String str) {
        ValueInfo<Type> valueInfo;
        synchronized (this.mMemoryStateLocker) {
            valueInfo = map.get(str);
        }
        if (valueInfo == null) {
            return null;
        }
        if (valueInfo.mValueToOverride == null) {
            sendExposureToService(str, valueInfo);
        }
        return valueInfo;
    }

    private Long getLongDefault(String str) {
        Object obj = this.mParamsDefaults.get(str);
        if (obj instanceof Long) {
            return (Long) obj;
        }
        return DEFAULT_LONG;
    }

    @Nullable
    private Object getParamDefault(String str) {
        C01200Sw r1 = this.mParamsMapEntries.get(str);
        if (r1 == null) {
            AnonymousClass0MD.A09(TAG, "Cannot get default value for unsupported param name %s", str);
            return null;
        }
        int i = r1.A01;
        if (i == 1) {
            return getBooleanDefault(str);
        }
        if (i == 2) {
            return getLongDefault(str);
        }
        if (i == 3) {
            return getStringDefault(str);
        }
        if (i == 4) {
            return getDoubleDefault(str);
        }
        AnonymousClass0MD.A09(TAG, "Unsupported param type %s for param name %s", Integer.valueOf(i), str);
        return null;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @Nullable
    private <Type> ValueInfo<Type> getServiceValueFromMemoryState(Map<String, ValueInfo<Type>> map, String str) {
        ValueInfo<Type> valueInfo;
        synchronized (this.mMemoryStateLocker) {
            valueInfo = map.get(str);
        }
        if (valueInfo == null) {
            return null;
        }
        if (valueInfo.mValueToOverride != null || valueInfo.hasValueSetFromService()) {
            return valueInfo;
        }
        return null;
    }

    private String getStringDefault(String str) {
        Object obj = this.mParamsDefaults.get(str);
        if (obj instanceof String) {
            return (String) obj;
        }
        return "";
    }

    public static boolean isInUse() {
        if (sInUse.get() != 0) {
            return true;
        }
        return false;
    }

    private boolean isParamValid(String str, int i) {
        String str2;
        C01200Sw r0 = this.mParamsMapEntries.get(str);
        if (r0 == null) {
            DeviceConfigTelemetryLogger.logUnknownParam(this.mContext, "Can't find param", str);
            return false;
        } else if (r0.A01 == i) {
            return true;
        } else {
            if (i == 1) {
                str2 = "Param is not a boolean";
            } else if (i == 2) {
                str2 = "Param is not a long";
            } else if (i == 3) {
                str2 = "Param is not a string";
            } else if (i != 4) {
                str2 = "Param has an unknown type";
            } else {
                str2 = "Param is not a double";
            }
            DeviceConfigTelemetryLogger.logIncorrectTypeParam(this.mContext, str2, str);
            return false;
        }
    }

    private void removeChangeListeners() {
        if (this.mChangeListenersAdded) {
            for (Map.Entry<String, Set<String>> entry : this.mParamNamesByConfig.entrySet()) {
                String key = entry.getKey();
                AnonymousClass0T8 r6 = this.mMobileConfigClient;
                ChangeListener changeListener = this.mChangeListener;
                Map<String, List<AnonymousClass0T7>> map = r6.A03;
                synchronized (map) {
                    AnonymousClass0T7 A01 = AnonymousClass0T8.A01(r6, key, changeListener);
                    if (A01 == null) {
                        r6.A02.A00("removeChangeListener", key, true, "Already removed");
                    } else {
                        if (map.containsKey(key)) {
                            List<AnonymousClass0T7> list = map.get(key);
                            list.remove(A01);
                            if (list.isEmpty()) {
                                map.remove(key);
                            }
                        }
                        Context context = r6.A00;
                        context.getContentResolver().unregisterContentObserver(A01);
                        Map<String, List<AnonymousClass0T7>> map2 = r6.A03;
                        if (!map2.containsKey(key) || map2.get(key).size() <= 0) {
                            try {
                                ContentResolver contentResolver = context.getContentResolver();
                                Uri uri = AnonymousClass0TE.A01;
                                ContentProviderClient acquireUnstableContentProviderClient = contentResolver.acquireUnstableContentProviderClient(uri);
                                try {
                                    Cursor query = acquireUnstableContentProviderClient.query(uri, new String[0], key, new String[0], AnonymousClass0T8.A02(r6));
                                    if (query != null) {
                                        try {
                                            query.close();
                                        } catch (Exception unused) {
                                        }
                                    }
                                    r6.A02.A00("removeChangeListener", key, true, "");
                                    acquireUnstableContentProviderClient.close();
                                } catch (Throwable unused2) {
                                }
                            } catch (RemoteException e) {
                                r6.A02.A00("removeChangeListener", key, false, "Service not running");
                                AnonymousClass0MD.A07("MobileConfigBaseClient", "Could not find mobileconfigservice; is the service running?", e);
                            }
                        } else {
                            r6.A02.A00("removeChangeListener", key, true, "");
                        }
                    }
                }
            }
            this.mChangeListenersAdded = false;
            return;
        }
        return;
        throw th;
    }

    private <Type> void sendExposureToService(String str, ValueInfo<Type> valueInfo) {
        String str2 = valueInfo.mLoggingId;
        if (!valueInfo.mExposureLogged && !TextUtils.isEmpty(str2)) {
            addAsyncExposure(str, str2, valueInfo.mSessionless);
            valueInfo.mExposureLogged = true;
        }
    }

    private void setParamNamesByConfig() {
        for (Map.Entry<String, C01200Sw> entry : this.mParamsMapEntries.entrySet()) {
            String key = entry.getKey();
            String str = entry.getValue().A02;
            Set<String> set = this.mParamNamesByConfig.get(str);
            if (set == null) {
                set = new HashSet<>();
                this.mParamNamesByConfig.put(str, set);
            }
            set.add(key);
        }
    }

    private void signalAsyncFetch() {
        AsyncFetch asyncFetch;
        synchronized (this.mParamsLock) {
            asyncFetch = this.mAsyncFetchInstance;
            if (asyncFetch == null) {
                asyncFetch = new AsyncFetch();
                this.mAsyncFetchInstance = asyncFetch;
            }
        }
        asyncFetch.signal();
    }

    private <Type> void updateMemoryStateValueDirectlyFromService(Map<String, ValueInfo<Type>> map, String str, Type type) {
        updateMemoryStateValueFromServiceInternal(map, str, type, CacheUpdateMode.ReturnedValueAndSerialization, null, false);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0040  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private <Type> void updateMemoryStateValueFromServiceInternal(java.util.Map<java.lang.String, com.oculus.deviceconfigclient.ValueInfo<Type>> r5, java.lang.String r6, Type r7, com.oculus.deviceconfigclient.DeviceConfigClient.CacheUpdateMode r8, @javax.annotation.Nullable java.lang.String r9, boolean r10) {
        /*
            r4 = this;
            com.oculus.deviceconfigclient.ConfigMemoryState r1 = r4.mMemoryState
            java.lang.String r0 = r4.mCurrentParamsMapVersion
            r1.mParamsMapVersion = r0
            java.lang.Object r1 = r4.mMemoryStateLocker
            monitor-enter(r1)
            java.lang.Object r3 = r5.get(r6)     // Catch:{ all -> 0x004b }
            com.oculus.deviceconfigclient.ValueInfo r3 = (com.oculus.deviceconfigclient.ValueInfo) r3     // Catch:{ all -> 0x004b }
            if (r3 != 0) goto L_0x0019
            com.oculus.deviceconfigclient.ValueInfo r3 = new com.oculus.deviceconfigclient.ValueInfo     // Catch:{ all -> 0x004b }
            r3.<init>()     // Catch:{ all -> 0x004b }
            r5.put(r6, r3)     // Catch:{ all -> 0x004b }
        L_0x0019:
            monitor-exit(r1)     // Catch:{ all -> 0x004b }
            int r1 = r8.ordinal()
            r0 = 0
            r2 = 1
            switch(r1) {
                case 0: goto L_0x003a;
                case 1: goto L_0x0035;
                case 2: goto L_0x0030;
                default: goto L_0x0023;
            }
        L_0x0023:
            android.content.Context r1 = r4.mContext
            java.lang.String r0 = "Incorrect CacheUpdateMode"
            com.oculus.deviceconfigclient.DeviceConfigTelemetryLogger.logInternalError(r1, r0)
        L_0x002a:
            if (r9 == 0) goto L_0x0048
            r3.setLoggingId(r9, r10)
            return
        L_0x0030:
            boolean r0 = r3.setValueForSerialization(r7)
            goto L_0x003e
        L_0x0035:
            boolean r0 = r3.setValueFromService(r7, r0)
            goto L_0x003e
        L_0x003a:
            boolean r0 = r3.setValueFromService(r7, r2)
        L_0x003e:
            if (r0 == 0) goto L_0x002a
            android.content.Context r1 = r4.mContext
            com.oculus.deviceconfigclient.ConfigMemoryState r0 = r4.mMemoryState
            com.oculus.deviceconfigclient.ConfigStorageAdapter.writeMemoryStateToStorageCache(r1, r0)
            goto L_0x002a
        L_0x0048:
            r3.mExposureLogged = r2
            return
        L_0x004b:
            r0 = move-exception
            monitor-exit(r1)
            throw r0
            switch-data {0->0x003a, 1->0x0035, 2->0x0030, }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.deviceconfigclient.DeviceConfigClient.updateMemoryStateValueFromServiceInternal(java.util.Map, java.lang.String, java.lang.Object, com.oculus.deviceconfigclient.DeviceConfigClient$CacheUpdateMode, java.lang.String, boolean):void");
    }

    public void debugOnlyClearMemoryCache() {
        this.mMemoryState = new ConfigMemoryState();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0145, code lost:
        if (r13 != null) goto L_0x0147;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.oculus.deviceconfigclient.DebugOnlyValueInfo> debugOnlyFetchAllParams() {
        /*
        // Method dump skipped, instructions count: 355
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.deviceconfigclient.DeviceConfigClient.debugOnlyFetchAllParams():java.util.List");
    }

    public Map<String, Object> debugOnlyGetCachedParams() {
        Map<String, Object> debugOnlyGetValues;
        synchronized (this.mMemoryStateLocker) {
            debugOnlyGetValues = this.mMemoryState.debugOnlyGetValues();
        }
        return debugOnlyGetValues;
    }

    @Nullable
    public Boolean debugOnlyGetDeviceBoolean(String str) {
        String str2;
        Object[] objArr;
        try {
            if (!this.mSubscribeSucceeded.get()) {
                str2 = "debugOnlyGetDeviceBoolean: Calling debugOnlyGetDeviceBoolean(%s) when not subscribed.";
                objArr = new Object[]{str};
            } else {
                AnonymousClass0T8 r2 = this.mMobileConfigClient;
                if (r2 != null) {
                    return Boolean.valueOf(r2.A07(str, getBooleanDefault(str).booleanValue(), this.mMobileConfigOptions));
                }
                str2 = "debugOnlyGetDeviceBoolean: Calling debugOnlyGetDeviceBoolean(%s) when mobile config client is not ready";
                objArr = new Object[]{str};
            }
            AnonymousClass0MD.A09(TAG, str2, objArr);
            return null;
        } catch (Exception e) {
            AnonymousClass0MD.A09(TAG, "debugOnlyGetDeviceBoolean: Calling debugOnlyGetDeviceBoolean(%s) and got exception", str, e);
            return null;
        }
    }

    @Nullable
    public Double debugOnlyGetDeviceDouble(String str) {
        String str2;
        Object[] objArr;
        try {
            if (!this.mSubscribeSucceeded.get()) {
                str2 = "debugOnlyGetDeviceDouble: Calling debugOnlyGetDeviceDouble(%s) when not subscribed.";
                objArr = new Object[]{str};
            } else {
                AnonymousClass0T8 r3 = this.mMobileConfigClient;
                if (r3 != null) {
                    return Double.valueOf(r3.A03(str, getDoubleDefault(str).doubleValue(), this.mMobileConfigOptions));
                }
                str2 = "debugOnlyGetDeviceDouble: Calling debugOnlyGetDeviceDouble(%s) when mobile config client is not ready";
                objArr = new Object[]{str};
            }
            AnonymousClass0MD.A09(TAG, str2, objArr);
            return null;
        } catch (Exception e) {
            AnonymousClass0MD.A09(TAG, "debugOnlyGetDeviceDouble: Calling debugOnlyGetDeviceDouble(%s) and got exception", str, e);
            return null;
        }
    }

    @Nullable
    public Long debugOnlyGetDeviceLong(String str) {
        String str2;
        Object[] objArr;
        try {
            if (!this.mSubscribeSucceeded.get()) {
                str2 = "debugOnlyGetDeviceLong: Calling debugOnlyGetDeviceLong(%s) when not subscribed.";
                objArr = new Object[]{str};
            } else {
                AnonymousClass0T8 r3 = this.mMobileConfigClient;
                if (r3 != null) {
                    return Long.valueOf(r3.A04(str, getLongDefault(str).longValue(), this.mMobileConfigOptions));
                }
                str2 = "debugOnlyGetDeviceLong: Calling debugOnlyGetDeviceLong(%s) when mobile config client is not ready";
                objArr = new Object[]{str};
            }
            AnonymousClass0MD.A09(TAG, str2, objArr);
            return null;
        } catch (Exception e) {
            AnonymousClass0MD.A09(TAG, "debugOnlyGetDeviceLong: Calling debugOnlyGetDeviceLong(%s) and got exception", str, e);
            return null;
        }
    }

    @Nullable
    public String debugOnlyGetDeviceString(String str) {
        String str2;
        Object[] objArr;
        try {
            if (!this.mSubscribeSucceeded.get()) {
                str2 = "debugOnlyGetDeviceString: Calling debugOnlyGetDeviceString(%s) when not subscribed.";
                objArr = new Object[]{str};
            } else {
                AnonymousClass0T8 r2 = this.mMobileConfigClient;
                if (r2 != null) {
                    return r2.A06(str, getStringDefault(str), this.mMobileConfigOptions);
                }
                str2 = "debugOnlyGetDeviceString: Calling debugOnlyGetDeviceString(%s) when mobile config client is not ready";
                objArr = new Object[]{str};
            }
            AnonymousClass0MD.A09(TAG, str2, objArr);
            return null;
        } catch (Exception e) {
            AnonymousClass0MD.A09(TAG, "debugOnlyGetDeviceString: Calling debugOnlyGetDeviceString(%s) and got exception", str, e);
            return null;
        }
    }

    public String debugOnlyGetMemorySnapshot(String str) {
        String debugOnlyGetMemorySnapshot;
        synchronized (this.mMemoryStateLocker) {
            debugOnlyGetMemorySnapshot = this.mMemoryState.debugOnlyGetMemorySnapshot(str);
        }
        return debugOnlyGetMemorySnapshot;
    }

    public Map<String, Object> debugOnlyGetParamsDefaults() {
        HashMap hashMap = new HashMap();
        for (Map.Entry<String, C01200Sw> entry : this.mParamsMapEntries.entrySet()) {
            String key = entry.getKey();
            hashMap.put(key, getParamDefault(key));
        }
        return hashMap;
    }

    public void debugOnlyReadMemoryCacheFromStorage() {
        this.mMemoryState = ConfigStorageAdapter.createMemoryStateFromStorageCache(this.mContext);
    }

    public void debugOnlySetBooleanOverriddenValue(String str, Object obj) {
        synchronized (this.mMemoryStateLocker) {
            this.mMemoryState.debugOnlySetBooleanOverriddenValue(str, obj);
        }
    }

    public void debugOnlySetDoubleOverriddenValue(String str, Object obj) {
        synchronized (this.mMemoryStateLocker) {
            this.mMemoryState.debugOnlySetDoubleOverriddenValue(str, obj);
        }
    }

    public void debugOnlySetLongOverriddenValue(String str, Object obj) {
        synchronized (this.mMemoryStateLocker) {
            this.mMemoryState.debugOnlySetLongOverriddenValue(str, obj);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x007f, code lost:
        if (r4 != null) goto L_0x0081;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void debugOnlySetOverriddenParams(java.util.Map<java.lang.String, com.oculus.deviceconfigclient.DebugOnlyValueInfo> r13) {
        /*
        // Method dump skipped, instructions count: 157
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.deviceconfigclient.DeviceConfigClient.debugOnlySetOverriddenParams(java.util.Map):void");
    }

    public void debugOnlySetStringOverriddenValue(String str, Object obj) {
        synchronized (this.mMemoryStateLocker) {
            this.mMemoryState.debugOnlySetStringOverriddenValue(str, obj);
        }
    }

    public void debugOnlyWriteMemoryCacheToStorage() {
        ConfigStorageAdapter.writeMemoryStateToStorageCache(this.mContext, this.mMemoryState);
    }

    public Set<String> getParamNamesFromSchema() {
        return this.mParamsMapEntries.keySet();
    }

    public boolean isLocalCacheWithFetchedValues() {
        return this.mCurrentParamsMapVersion.equals(this.mMemoryState.mParamsMapVersion);
    }

    public boolean isMobileConfigInitialized() {
        if (this.mMobileConfigClient != null) {
            return true;
        }
        return false;
    }

    public boolean subscribe(final DeviceConfigCallback deviceConfigCallback) {
        if (this.mSubscribeSucceeded.get()) {
            return false;
        }
        try {
            this.mCallback = deviceConfigCallback;
            final long currentTimeMillis = System.currentTimeMillis();
            AnonymousClass1 r4 = new Object() {
                /* class com.oculus.deviceconfigclient.DeviceConfigClient.AnonymousClass1 */

                public void onMobileConfigSubscribeFailure(String str) {
                    DeviceConfigTelemetryLogger.logSubscriptionFailure(DeviceConfigClient.this.mContext, str);
                    deviceConfigCallback.onFailure(str);
                }

                public void onMobileConfigSubscribeSuccess() {
                    DeviceConfigTelemetryLogger.logSubscriptionSuccess(DeviceConfigClient.this.mContext, System.currentTimeMillis() - currentTimeMillis);
                    DeviceConfigClient.this.mSubscribeSucceeded.set(true);
                    if (deviceConfigCallback.enableAutoPrefetch()) {
                        DeviceConfigClient.this.addChangeListeners();
                        DeviceConfigClient.this.addAsyncPrefetchAllParams();
                    }
                    deviceConfigCallback.onSuccess();
                }
            };
            AnonymousClass0T8 r5 = this.mMobileConfigClient;
            if (r5 == null) {
                return true;
            }
            String A05 = r5.A05();
            String A02 = AnonymousClass0T8.A02(r5);
            Uri withAppendedPath = Uri.withAppendedPath(AnonymousClass0TE.A0C, A02);
            Context context = r5.A00;
            AnonymousClass0T4 r6 = new AnonymousClass0T4(r5, new Handler(context.getMainLooper()), withAppendedPath, r4);
            try {
                context.getContentResolver().registerContentObserver(withAppendedPath, false, r6);
                context.getContentResolver().registerContentObserver(Uri.withAppendedPath(AnonymousClass0TE.A0A, A02), false, r6);
                context.getContentResolver().registerContentObserver(Uri.withAppendedPath(AnonymousClass0TE.A0B, A02), false, r6);
                context.getContentResolver().registerContentObserver(Uri.withAppendedPath(AnonymousClass0TE.A09, A02), false, r6);
                new Thread(new AnonymousClass0T5(r5, A05)).start();
                return true;
            } catch (SecurityException e) {
                r4.onMobileConfigSubscribeFailure(e.getMessage());
                return true;
            }
        } catch (Exception e2) {
            DeviceConfigTelemetryLogger.logSubscriptionFailure(this.mContext, e2);
            deviceConfigCallback.onFailure("subscribe() threw an exception");
            return false;
        }
    }

    @Nullable
    private Boolean debugOnlyParseAsBoolean(@Nullable String str) {
        if (debugOnlyCheckValueStr(str)) {
            return Boolean.valueOf(Boolean.parseBoolean(str));
        }
        return null;
    }

    @Nullable
    private String debugOnlyParseAsString(@Nullable String str) {
        if (!debugOnlyCheckValueStr(str)) {
            return null;
        }
        return str;
    }

    @SuppressLint({"BadMethodUse-java.lang.System.currentTimeMillis"})
    public static long getTimeNowInMs() {
        return System.currentTimeMillis();
    }

    public void internalPrefetch(String[] strArr) {
        try {
            addAsyncPrefetch(strArr);
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logInternalError(this.mContext, e);
        }
    }

    public void shutdown() {
        asyncFetchShutdown();
        this.mCallback = null;
        this.mParamNamesByConfig.clear();
        this.mMemoryState.clear();
        this.mSubscribeSucceeded.set(false);
        removeChangeListeners();
        if (sInUse.decrementAndGet() < 0) {
            AnonymousClass0MD.A04(TAG, "Called shutdown() too many times");
        }
    }

    public void debugOnlySetAsyncFetchWaitTimeInMs(int i) {
        this.mAsyncFetchWaitTimeInMs = i;
    }

    public void prefetch(String... strArr) {
        internalPrefetch(strArr);
    }

    public static /* synthetic */ void access$800(DeviceConfigClient deviceConfigClient, String str, Set set) {
    }

    private <Type> void updateMemoryStateValuePrefetchedFromService(Map<String, ValueInfo<Type>> map, String str, Type type, CacheUpdateMode cacheUpdateMode, @Nullable String str2, boolean z) {
        updateMemoryStateValueFromServiceInternal(map, str, type, cacheUpdateMode, str2, z);
    }

    public DeviceConfigClient(Context context) {
        this(context, new Configuration());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:146:0x030d, code lost:
        throw new java.lang.RuntimeException(X.AnonymousClass006.A07("could not find key in configs ", r12));
     */
    /* JADX WARNING: Removed duplicated region for block: B:109:0x026e A[Catch:{ Exception -> 0x0391 }] */
    /* JADX WARNING: Removed duplicated region for block: B:116:0x0287 A[Catch:{ Exception -> 0x0391 }] */
    /* JADX WARNING: Removed duplicated region for block: B:125:0x0295 A[Catch:{ Exception -> 0x0391 }] */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x02a1 A[Catch:{ Exception -> 0x0391 }] */
    /* JADX WARNING: Removed duplicated region for block: B:133:0x02b4 A[Catch:{ Exception -> 0x0391 }] */
    /* JADX WARNING: Removed duplicated region for block: B:143:0x02fa A[Catch:{ Exception -> 0x0391 }] */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x034a A[Catch:{ Exception -> 0x0391 }] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x00bd A[Catch:{ Exception -> 0x0391 }, LOOP:0: B:13:0x00ba->B:15:0x00bd, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x037a A[Catch:{ Exception -> 0x0391 }, LOOP:4: B:163:0x0374->B:165:0x037a, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x00d0 A[Catch:{ Exception -> 0x0391 }] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00d6 A[Catch:{ Exception -> 0x0391 }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ec A[Catch:{ Exception -> 0x0391 }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0156 A[Catch:{ Exception -> 0x0391 }] */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x0167 A[Catch:{ Exception -> 0x0391 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DeviceConfigClient(android.content.Context r34, com.oculus.deviceconfigclient.DeviceConfigClient.Configuration r35) {
        /*
        // Method dump skipped, instructions count: 962
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.deviceconfigclient.DeviceConfigClient.<init>(android.content.Context, com.oculus.deviceconfigclient.DeviceConfigClient$Configuration):void");
    }

    private String getParamNameForGetMultiple(String str) {
        C01200Sw r0 = this.mParamsMapEntries.get(str);
        if (r0 != null) {
            return getParamNameForGetMultiple(str, r0.A01);
        }
        DeviceConfigTelemetryLogger.logUnknownParam(this.mContext, "Expected to fail with getMultiple()", str);
        return str;
    }

    private String getParamNameForGetMultiple(String str, int i) {
        String bool;
        if (i == 1) {
            bool = Boolean.toString(getBooleanDefault(str).booleanValue());
        } else if (i == 2) {
            bool = Long.toString(getLongDefault(str).longValue());
        } else if (i == 3) {
            bool = getStringDefault(str);
        } else if (i != 4) {
            DeviceConfigTelemetryLogger.logUnknownMCType(this.mContext, i);
            bool = "";
        } else {
            bool = Double.toString(getDoubleDefault(str).doubleValue());
        }
        return AnonymousClass006.A09(str, ":", bool);
    }

    @Nullable
    public String debugOnlyHandleCommand(AnonymousClass0TC r2) {
        return debugOnlyHandleCommand(r2, "");
    }

    @Nullable
    public String debugOnlyHandleCommand(AnonymousClass0TC r10, String str) {
        ContentResolver contentResolver = this.mContext.getContentResolver();
        Uri uri = AnonymousClass0TE.A06;
        ContentProviderClient acquireUnstableContentProviderClient = contentResolver.acquireUnstableContentProviderClient(uri);
        if (acquireUnstableContentProviderClient == null) {
            try {
                AnonymousClass0MD.A04(TAG, "Content provider for the mobileconfig service not found");
                return null;
            } catch (Throwable unused) {
            }
        } else {
            Cursor query = acquireUnstableContentProviderClient.query(uri, new String[]{r10.toString(), str}, "", new String[0], null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        query.getString(2);
                        String string = query.getString(2);
                        query.close();
                        try {
                            acquireUnstableContentProviderClient.close();
                            return string;
                        } catch (RemoteException | SecurityException e) {
                            AnonymousClass0MD.A07(TAG, "Could not find mobileconfigservice; is the service running?", e);
                            return null;
                        }
                    }
                } catch (Throwable unused2) {
                }
            }
            AnonymousClass0MD.A04(TAG, "no results returned from mobile config content provider");
            if (query != null) {
                query.close();
            }
            acquireUnstableContentProviderClient.close();
            return null;
        }
        throw th;
        throw th;
    }
}
