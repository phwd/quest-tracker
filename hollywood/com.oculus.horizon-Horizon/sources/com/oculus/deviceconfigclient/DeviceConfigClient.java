package com.oculus.deviceconfigclient;

import X.AnonymousClass006;
import X.AnonymousClass0NO;
import X.AnonymousClass0Rh;
import X.AnonymousClass0Rj;
import X.AnonymousClass1aU;
import X.AnonymousClass1am;
import X.AnonymousClass1e9;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import com.facebook.LegacyTokenHelper;
import com.facebook.infer.annotation.Nullsafe;
import com.oculus.deviceconfigclient.DeviceConfigTelemetryLogger;
import com.oculus.deviceconfigclient.ValueInfo;
import com.oculus.horizon.notifications.core.NotificationBuilder;
import com.oculus.horizon.service_media.OVRMediaServiceManager;
import com.oculus.socialplatform.util.SocialPlatformVersionUtil;
import java.io.Closeable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DeviceConfigClient {
    public static final String CACHED_SOURCE = "CACHED";
    public static final Boolean DEFAULT_BOOLEAN = false;
    public static final Double DEFAULT_DOUBLE = Double.valueOf((double) OVRMediaServiceManager.SCREENSHOT_SHORTCUT_DELAY);
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
    public AsyncFetch mAsyncFetchInstance = null;
    public int mAsyncFetchWaitTimeInMs = NotificationBuilder.CANCELLABLE_NOTIFICATION_FIRST_ID;
    @Nullable
    public DeviceConfigCallback mCallback = null;
    public final ChangeListener mChangeListener = new ChangeListener();
    public boolean mChangeListenersAdded = false;
    public final Context mContext;
    public String mCurrentParamsMapVersion;
    public ConfigMemoryState mMemoryState;
    public final Object mMemoryStateLocker = new Object();
    public final AnonymousClass1e9 mMobileConfigClient;
    public final AnonymousClass0Rh mMobileConfigOptions;
    public final Map<String, Set<String>> mParamNamesByConfig = new HashMap();
    public final Map<String, Object> mParamsDefaults = new HashMap();
    public final Object mParamsLock = new Object();
    public Map<String, AnonymousClass1am> mParamsMapEntries;
    public Set<String> mParamsToPrefetch = new HashSet();
    public Set<String> mSessionLoggingIdsToLogExposure = new HashSet();
    public Set<String> mSessionlessLoggingIdsToLogExposure = new HashSet();
    public final AtomicBoolean mSubscribeSucceeded = new AtomicBoolean(false);

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

        /* JADX WARN: Incorrect args count in method signature: <Type:Ljava/lang/Object;>(Ljava/lang/String;Ljava/util/Map<Ljava/lang/String;Lcom/oculus/deviceconfigclient/ValueInfo<TType;>;>;Ljava/lang/Object;)Lcom/oculus/deviceconfigclient/DeviceConfigClient$CacheUpdateMode; */
        public static CacheUpdateMode A00(AsyncFetch asyncFetch, String str, Map map, Object obj) {
            DeviceConfigClient deviceConfigClient = DeviceConfigClient.this;
            if (deviceConfigClient.mCallback != null) {
                ValueInfo A00 = DeviceConfigClient.A00(deviceConfigClient, map, str);
                if (A00 == null || !A00.mValueReturned) {
                    return CacheUpdateMode.InMemoryAndSerialization;
                }
                if (A00.mValueToOverride == null) {
                    A00.mValueReturned = true;
                    Type type = A00.mValueToReturn;
                    if (type == null) {
                        DeviceConfigTelemetryLogger.A07(DeviceConfigClient.this.mContext, "Current value is null", str);
                    } else {
                        type.equals(obj);
                    }
                }
            }
            return CacheUpdateMode.SerializationOnly;
        }

        /* JADX WARN: Incorrect args count in method signature: (Ljava/util/Set<Ljava/lang/String;>;Z)Z */
        /* JADX WARNING: Can't wrap try/catch for region: R(7:11|(1:13)(1:17)|14|(3:16|18|19)|20|21|34) */
        /* JADX WARNING: Code restructure failed: missing block: B:28:0x0057, code lost:
            r1 = move-exception;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:29:0x0058, code lost:
            X.AnonymousClass0NO.A0B("MobileConfigBaseClient", "Could not find mobileconfigservice; is the service running?", r1);
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:20:0x004c */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public static boolean A01(com.oculus.deviceconfigclient.DeviceConfigClient.AsyncFetch r10, java.util.Set r11, boolean r12) {
            /*
                boolean r0 = r11.isEmpty()
                if (r0 == 0) goto L_0x0008
                r0 = 0
                return r0
            L_0x0008:
                java.util.Iterator r3 = r11.iterator()
            L_0x000c:
                boolean r0 = r3.hasNext()
                if (r0 == 0) goto L_0x005e
                java.lang.Object r7 = r3.next()
                java.lang.String r7 = (java.lang.String) r7
                com.oculus.deviceconfigclient.DeviceConfigClient r0 = com.oculus.deviceconfigclient.DeviceConfigClient.this
                X.1e9 r0 = r0.mMobileConfigClient
                java.lang.String r2 = "MobileConfigBaseClient"
                android.content.Context r0 = r0.A00
                android.content.ContentResolver r0 = r0.getContentResolver()
                android.net.Uri r5 = X.AnonymousClass1aU.A07
                android.content.ContentProviderClient r4 = r0.acquireUnstableContentProviderClient(r5)
                if (r4 != 0) goto L_0x0032
                java.lang.String r0 = "Content provider for the mobileconfig service not found"
                X.AnonymousClass0NO.A08(r2, r0)     // Catch:{ all -> 0x0050 }
                goto L_0x000c
            L_0x0032:
                r0 = 1
                java.lang.String[] r6 = new java.lang.String[r0]     // Catch:{ all -> 0x0050 }
                if (r12 == 0) goto L_0x0046
                java.lang.String r1 = "1"
            L_0x0039:
                r0 = 0
                r6[r0] = r1     // Catch:{ all -> 0x0050 }
                java.lang.String[] r8 = new java.lang.String[r0]     // Catch:{ all -> 0x0050 }
                r9 = 0
                android.database.Cursor r0 = r4.query(r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0050 }
                if (r0 == 0) goto L_0x004c
                goto L_0x0049
            L_0x0046:
                java.lang.String r1 = "0"
                goto L_0x0039
            L_0x0049:
                r0.close()     // Catch:{ Exception -> 0x004c }
            L_0x004c:
                r4.close()     // Catch:{ RemoteException | SecurityException -> 0x0057 }
                goto L_0x000c
            L_0x0050:
                r0 = move-exception
                if (r4 == 0) goto L_0x0056
                r4.close()     // Catch:{ all -> 0x0056 }
            L_0x0056:
                throw r0
            L_0x0057:
                r1 = move-exception
                java.lang.String r0 = "Could not find mobileconfigservice; is the service running?"
                X.AnonymousClass0NO.A0B(r2, r0, r1)
                goto L_0x000c
            L_0x005e:
                r0 = 1
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.deviceconfigclient.DeviceConfigClient.AsyncFetch.A01(com.oculus.deviceconfigclient.DeviceConfigClient$AsyncFetch, java.util.Set, boolean):boolean");
        }
    }

    public class AsyncFetchThread extends Thread {
        public final AsyncFetch mAsyncFetchInstance;

        public AsyncFetchThread(AsyncFetch asyncFetch) {
            this.mAsyncFetchInstance = asyncFetch;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:60:0x014d, code lost:
            if (r10 != null) goto L_0x014f;
         */
        /* JADX WARNING: Removed duplicated region for block: B:118:0x02e0 A[SYNTHETIC, Splitter:B:118:0x02e0] */
        /* JADX WARNING: Removed duplicated region for block: B:141:0x0307 A[SYNTHETIC] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            /*
            // Method dump skipped, instructions count: 803
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.deviceconfigclient.DeviceConfigClient.AsyncFetchThread.run():void");
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
    }

    public static class Configuration {
        public String mServicePackage = "com.oculus.horizon";
    }

    public static class Signal {
        public final AtomicBoolean mSignaled = new AtomicBoolean(false);
    }

    public static String A02(DeviceConfigClient deviceConfigClient, String str, int i) {
        String bool;
        Number number;
        Number number2;
        if (i == 1) {
            bool = Boolean.toString(deviceConfigClient.A01(str).booleanValue());
        } else if (i != 2) {
            if (i == 3) {
                Object obj = deviceConfigClient.mParamsDefaults.get(str);
                if (obj instanceof String) {
                    bool = (String) obj;
                }
            } else if (i != 4) {
                DeviceConfigTelemetryLogger.A01(deviceConfigClient.mContext, i);
            } else {
                Object obj2 = deviceConfigClient.mParamsDefaults.get(str);
                if (obj2 instanceof Double) {
                    number2 = (Number) obj2;
                } else {
                    number2 = DEFAULT_DOUBLE;
                }
                bool = Double.toString(number2.doubleValue());
            }
            bool = "";
        } else {
            Object obj3 = deviceConfigClient.mParamsDefaults.get(str);
            if (obj3 instanceof Long) {
                number = (Number) obj3;
            } else {
                number = DEFAULT_LONG;
            }
            bool = Long.toString(number.longValue());
        }
        return AnonymousClass006.A07(str, ":", bool);
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

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v38, types: [java.util.Set] */
    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x02a5  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x0350 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00f0 A[Catch:{ Exception -> 0x01aa }] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x010b  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0142 A[Catch:{ JSONException -> 0x0147 }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x0150 A[Catch:{ JSONException -> 0x0155 }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0158  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0178 A[Catch:{ JSONException -> 0x017d }] */
    /* JADX WARNING: Removed duplicated region for block: B:90:0x01b6  */
    /* JADX WARNING: Removed duplicated region for block: B:94:0x01c5  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DeviceConfigClient(android.content.Context r16) {
        /*
        // Method dump skipped, instructions count: 912
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.deviceconfigclient.DeviceConfigClient.<init>(android.content.Context):void");
    }

    /* JADX WARN: Incorrect args count in method signature: <Type:Ljava/lang/Object;>(Ljava/util/Map<Ljava/lang/String;Lcom/oculus/deviceconfigclient/ValueInfo<TType;>;>;Ljava/lang/String;)Lcom/oculus/deviceconfigclient/ValueInfo<TType;>; */
    @Nullable
    public static ValueInfo A00(DeviceConfigClient deviceConfigClient, Map map, String str) {
        ValueInfo valueInfo;
        synchronized (deviceConfigClient.mMemoryStateLocker) {
            valueInfo = (ValueInfo) map.get(str);
        }
        if (valueInfo == null) {
            return null;
        }
        if (valueInfo.mValueToOverride != null || valueInfo.mValueSetFlag == ValueInfo.ValueSetFlags.FromService) {
            return valueInfo;
        }
        return null;
    }

    private Boolean A01(String str) {
        Object obj = this.mParamsDefaults.get(str);
        if (obj instanceof Boolean) {
            return (Boolean) obj;
        }
        return DEFAULT_BOOLEAN;
    }

    public static void A03(DeviceConfigClient deviceConfigClient) {
        AsyncFetch asyncFetch;
        synchronized (deviceConfigClient.mParamsLock) {
            asyncFetch = deviceConfigClient.mAsyncFetchInstance;
            if (asyncFetch == null) {
                asyncFetch = new AsyncFetch();
                deviceConfigClient.mAsyncFetchInstance = asyncFetch;
            }
        }
        Signal signal = asyncFetch.mSignal;
        signal.mSignaled.set(true);
        synchronized (signal) {
            signal.notifyAll();
        }
    }

    /* JADX WARN: Incorrect args count in method signature: <Type:Ljava/lang/Object;>(Ljava/util/Map<Ljava/lang/String;Lcom/oculus/deviceconfigclient/ValueInfo<TType;>;>;Ljava/lang/String;TType;Lcom/oculus/deviceconfigclient/DeviceConfigClient$CacheUpdateMode;Ljava/lang/String;Z)V */
    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0060  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void A04(com.oculus.deviceconfigclient.DeviceConfigClient r3, java.util.Map r4, java.lang.String r5, java.lang.Object r6, @javax.annotation.Nullable com.oculus.deviceconfigclient.DeviceConfigClient.CacheUpdateMode r7, java.lang.String r8, boolean r9) {
        /*
        // Method dump skipped, instructions count: 168
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.deviceconfigclient.DeviceConfigClient.A04(com.oculus.deviceconfigclient.DeviceConfigClient, java.util.Map, java.lang.String, java.lang.Object, com.oculus.deviceconfigclient.DeviceConfigClient$CacheUpdateMode, java.lang.String, boolean):void");
    }

    private boolean A05() {
        AnonymousClass1am r0 = this.mParamsMapEntries.get(SocialPlatformVersionUtil.PARTY_INFRA_GK_MC);
        if (r0 == null) {
            DeviceConfigTelemetryLogger.A08(this.mContext, "Can't find param", SocialPlatformVersionUtil.PARTY_INFRA_GK_MC);
            return false;
        } else if (r0.A04 == 1) {
            return true;
        } else {
            DeviceConfigTelemetryLogger.A00(this.mContext);
            return false;
        }
    }

    public static boolean A06(DeviceConfigClient deviceConfigClient, AsyncFetch asyncFetch, boolean z) {
        synchronized (deviceConfigClient.mParamsLock) {
            if (deviceConfigClient.mAsyncFetchInstance != asyncFetch || (z && (!deviceConfigClient.mSessionLoggingIdsToLogExposure.isEmpty() || !deviceConfigClient.mSessionlessLoggingIdsToLogExposure.isEmpty() || !deviceConfigClient.mParamsToPrefetch.isEmpty()))) {
                return false;
            }
            deviceConfigClient.mAsyncFetchInstance = null;
            return true;
        }
    }

    public final boolean A07() {
        String str;
        String str2;
        String str3;
        Throwable th;
        ValueInfo<Boolean> valueInfo;
        String str4;
        Set<String> set;
        boolean add;
        try {
            if (!this.mSubscribeSucceeded.get()) {
                String format = String.format("Calling getDeviceBoolean(%s) when not subscribed. Return value from local cache.", SocialPlatformVersionUtil.PARTY_INFRA_GK_MC);
                DeviceConfigTelemetryLogger deviceConfigTelemetryLogger = new DeviceConfigTelemetryLogger(this.mContext);
                DeviceConfigTelemetryLogger.A0A(deviceConfigTelemetryLogger, DeviceConfigTelemetryLogger.SubEvent.NOT_SUBSCRIBED);
                DeviceConfigTelemetryLogger.A0C(deviceConfigTelemetryLogger, format);
                DeviceConfigTelemetryLogger.A09(deviceConfigTelemetryLogger);
                AnonymousClass0NO.A08(DeviceConfigTelemetryLogger.TAG, format);
                try {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (!A05()) {
                        return DEFAULT_BOOLEAN.booleanValue();
                    }
                    Map<String, ValueInfo<Boolean>> A01 = this.mMemoryState.A01();
                    synchronized (this.mMemoryStateLocker) {
                        try {
                            valueInfo = A01.get(SocialPlatformVersionUtil.PARTY_INFRA_GK_MC);
                        } catch (Throwable th2) {
                            th = th2;
                            throw th;
                        }
                    }
                    if (valueInfo == null) {
                        return A01(SocialPlatformVersionUtil.PARTY_INFRA_GK_MC).booleanValue();
                    }
                    if (valueInfo.mValueToOverride == null) {
                        String str5 = valueInfo.mLoggingId;
                        if (!valueInfo.mExposureLogged && !TextUtils.isEmpty(str5)) {
                            if (valueInfo.mSessionless) {
                                set = this.mSessionlessLoggingIdsToLogExposure;
                            } else {
                                set = this.mSessionLoggingIdsToLogExposure;
                            }
                            synchronized (this.mParamsLock) {
                                try {
                                    add = set.add(SocialPlatformVersionUtil.PARTY_INFRA_GK_MC);
                                } catch (Throwable th3) {
                                    th = th3;
                                    throw th;
                                }
                            }
                            if (add) {
                                A03(this);
                            }
                            valueInfo.mExposureLogged = true;
                        }
                    }
                    Type type = valueInfo.mValueToOverride;
                    if (type == null) {
                        valueInfo.mValueReturned = true;
                        type = valueInfo.mValueToReturn;
                    }
                    Type type2 = type;
                    Context context = this.mContext;
                    DeviceConfigTelemetryLogger.ValueType valueType = DeviceConfigTelemetryLogger.ValueType.BOOLEAN;
                    if (type != null) {
                        str4 = OVERRIDDEN_SOURCE;
                    } else {
                        str4 = CACHED_SOURCE;
                    }
                    DeviceConfigTelemetryLogger.A04(context, valueType, type2, str4, System.currentTimeMillis() - currentTimeMillis);
                    if (type2 == null) {
                        type2 = A01(SocialPlatformVersionUtil.PARTY_INFRA_GK_MC);
                    }
                    return type2.booleanValue();
                } catch (Exception e) {
                    DeviceConfigTelemetryLogger.A03(this.mContext, DeviceConfigTelemetryLogger.ValueType.BOOLEAN, e);
                    return A01(SocialPlatformVersionUtil.PARTY_INFRA_GK_MC).booleanValue();
                }
            } else {
                long currentTimeMillis2 = System.currentTimeMillis();
                if (!A05()) {
                    return DEFAULT_BOOLEAN.booleanValue();
                }
                ValueInfo A00 = A00(this, this.mMemoryState.A01(), SocialPlatformVersionUtil.PARTY_INFRA_GK_MC);
                if (A00 != null) {
                    Type type3 = A00.mValueToOverride;
                    if (type3 == null) {
                        A00.mValueReturned = true;
                        type3 = A00.mValueToReturn;
                    }
                    Type type4 = type3;
                    if (type4 != null) {
                        Context context2 = this.mContext;
                        DeviceConfigTelemetryLogger.ValueType valueType2 = DeviceConfigTelemetryLogger.ValueType.DEVICE_BOOLEAN;
                        if (type3 != null) {
                            str3 = OVERRIDDEN_SOURCE;
                        } else {
                            str3 = SERVICE_CACHED_SOURCE;
                        }
                        DeviceConfigTelemetryLogger.A04(context2, valueType2, type4, str3, System.currentTimeMillis() - currentTimeMillis2);
                        return type4.booleanValue();
                    }
                }
                boolean booleanValue = A01(SocialPlatformVersionUtil.PARTY_INFRA_GK_MC).booleanValue();
                AnonymousClass1e9 r6 = this.mMobileConfigClient;
                if (r6 == null) {
                    return booleanValue;
                }
                AnonymousClass0Rh r5 = this.mMobileConfigOptions;
                Closeable closeable = null;
                try {
                    boolean z = false;
                    if (SocialPlatformVersionUtil.PARTY_INFRA_GK_MC.isEmpty()) {
                        r6.A02.A00("getBoolean", SocialPlatformVersionUtil.PARTY_INFRA_GK_MC, false, AnonymousClass0Rj.DEFAULT__INVALID_CONFIG_PARAM_NAME.toString());
                        r5.A00 = AnonymousClass0Rj.DEFAULT__INVALID_CONFIG_PARAM_NAME;
                    } else {
                        AnonymousClass0Rh A012 = r5.A01();
                        String bool = Boolean.toString(booleanValue);
                        ContentResolver contentResolver = r6.A00.getContentResolver();
                        Uri uri = AnonymousClass1aU.A03;
                        ContentProviderClient acquireUnstableContentProviderClient = contentResolver.acquireUnstableContentProviderClient(uri);
                        if (acquireUnstableContentProviderClient == null) {
                            try {
                                AnonymousClass0NO.A08("MobileConfigBaseClient", "Content provider for the mobileconfig service not found");
                            } catch (Throwable unused) {
                            }
                        } else {
                            String[] strArr = new String[1];
                            if (A012.A02) {
                                str2 = "request_value_source";
                            } else {
                                str2 = "";
                            }
                            strArr[0] = str2;
                            Cursor query = acquireUnstableContentProviderClient.query(uri, new String[]{LegacyTokenHelper.TYPE_BOOLEAN, bool}, SocialPlatformVersionUtil.PARTY_INFRA_GK_MC, strArr, null);
                            if (query == null || !query.moveToFirst()) {
                                AnonymousClass0NO.A0E("MobileConfigBaseClient", "no results returned for %s", SocialPlatformVersionUtil.PARTY_INFRA_GK_MC);
                                acquireUnstableContentProviderClient.close();
                            } else {
                                try {
                                    acquireUnstableContentProviderClient.close();
                                    if (query.getInt(0) > 0) {
                                        z = true;
                                    }
                                    A012.A00 = AnonymousClass0Rj.fromInt(query.getInt(1));
                                    query.close();
                                    r6.A02.A00("getBoolean", SocialPlatformVersionUtil.PARTY_INFRA_GK_MC, true, A012.A00.toString());
                                    try {
                                        query.close();
                                    } catch (Exception unused2) {
                                    }
                                    booleanValue = z;
                                } catch (RemoteException | SecurityException e2) {
                                    AnonymousClass0NO.A0B("MobileConfigBaseClient", "Could not find mobileconfigservice; is the service running?", e2);
                                }
                            }
                        }
                        r6.A02.A00("getBoolean", SocialPlatformVersionUtil.PARTY_INFRA_GK_MC, false, AnonymousClass0Rj.DEFAULT__SERVICE_NOT_FOUND.toString());
                        A012.A00 = AnonymousClass0Rj.DEFAULT__SERVICE_NOT_FOUND;
                    }
                } catch (Exception e3) {
                    AnonymousClass0NO.A0B("MobileConfigBaseClient", "Cannot retrieve MC value", e3);
                    if (0 != 0) {
                        try {
                            closeable.close();
                        } catch (Exception unused3) {
                        }
                    }
                } catch (Throwable th4) {
                    if (0 != 0) {
                        try {
                            closeable.close();
                        } catch (Exception unused4) {
                        }
                    }
                    throw th4;
                }
                Context context3 = this.mContext;
                DeviceConfigTelemetryLogger.ValueType valueType3 = DeviceConfigTelemetryLogger.ValueType.DEVICE_BOOLEAN;
                Boolean valueOf = Boolean.valueOf(booleanValue);
                String[] strArr2 = {"SERVER", "OVERRIDE", "DEFAULT__SERVER_RETURNED_NULL", "DEFAULT__ACCESSED_BEFORE_MC_INIT", "DEFAULT__NO_DATA_ON_DISK", "DEFAULT__ACCESSED_AFTER_MC_DISPOSE", "DEFAULT__MISSING_SERVER_VALUE"};
                int source = this.mMobileConfigOptions.A00.getSource();
                if (source < 0 || source >= 7) {
                    str = "UNKNOWN";
                } else {
                    str = strArr2[source];
                }
                DeviceConfigTelemetryLogger.A04(context3, valueType3, valueOf, str, System.currentTimeMillis() - currentTimeMillis2);
                A04(this, this.mMemoryState.A01(), SocialPlatformVersionUtil.PARTY_INFRA_GK_MC, valueOf, CacheUpdateMode.ReturnedValueAndSerialization, null, false);
                return booleanValue;
            }
            throw th;
        } catch (Exception e4) {
            DeviceConfigTelemetryLogger.A03(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_BOOLEAN, e4);
            return A01(SocialPlatformVersionUtil.PARTY_INFRA_GK_MC).booleanValue();
        }
    }
}
