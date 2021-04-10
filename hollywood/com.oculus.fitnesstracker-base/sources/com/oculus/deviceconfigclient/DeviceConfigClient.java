package com.oculus.deviceconfigclient;

import android.content.ContentProviderClient;
import android.content.Context;
import android.net.Uri;
import android.os.RemoteException;
import android.text.TextUtils;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.mobileconfig.factory.MobileConfigOptions;
import com.facebook.mobileconfig.metadata.ParamsMapEntry;
import com.facebook.mobileconfigservice.client_base.MobileConfigBaseClient;
import com.facebook.mobileconfigservice.client_ifaces.IMobileConfigChangeListener;
import com.facebook.mobileconfigservice.client_ifaces.MobileConfigServiceSubscribeCallback;
import com.facebook.mobileconfigservice.serviceconstants.MobileConfigServiceConstants;
import com.oculus.common.build.BuildConfig;
import com.oculus.deviceconfigclient.DeviceConfigCallback;
import com.oculus.deviceconfigclient.ValueInfo;
import com.oculus.deviceconfigclient.shared.DeviceConfigTelemetryLogger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DeviceConfigClient {
    private static final Boolean DEFAULT_BOOLEAN = Boolean.FALSE;
    private static final Double DEFAULT_DOUBLE = Double.valueOf(0.0d);
    private static final Long DEFAULT_LONG = 0L;
    private static final AtomicInteger sInUse = new AtomicInteger();
    private AsyncFetch mAsyncFetchInstance;
    private int mAsyncFetchWaitTimeInMs;
    private DeviceConfigCallback mCallback;
    private final ChangeListener mChangeListener;
    private boolean mChangeListenersAdded;
    private final Context mContext;
    private String mCurrentParamsMapVersion;
    private ConfigMemoryState mMemoryState;
    private final Object mMemoryStateLocker;
    private final MobileConfigBaseClient mMobileConfigClient;
    private final MobileConfigOptions mMobileConfigOptions;
    private final Map<String, Set<String>> mParamNamesByConfig;
    private final Map<String, Object> mParamsDefaults;
    private final Object mParamsLock;
    public Map<String, ParamsMapEntry> mParamsMapEntries;
    private Set<String> mParamsToPrefetch;
    private Set<String> mSessionLoggingIdsToLogExposure;
    private Set<String> mSessionlessLoggingIdsToLogExposure;
    private final AtomicBoolean mSubscribeSucceeded;

    public static class Configuration {
        String mServicePackage = BuildConfig.PACKAGE_NAME_HORIZON;
    }

    static /* synthetic */ void access$800(DeviceConfigClient deviceConfigClient, String str, Set set) {
    }

    /* JADX INFO: Failed to restore enum class, 'enum' modifier removed */
    /* access modifiers changed from: package-private */
    public static final class CacheUpdateMode extends Enum<CacheUpdateMode> {
        private static final /* synthetic */ int[] $VALUES$fa5c949 = {ReturnedValueAndSerialization$e213452, InMemoryAndSerialization$e213452, SerializationOnly$e213452};
        public static final int InMemoryAndSerialization$e213452 = 2;
        public static final int ReturnedValueAndSerialization$e213452 = 1;
        public static final int SerializationOnly$e213452 = 3;

        public static int[] values$2dfe5f58() {
            return (int[]) $VALUES$fa5c949.clone();
        }
    }

    public DeviceConfigClient(Context context) {
        this(context, new Configuration());
    }

    private DeviceConfigClient(Context context, Configuration configuration) {
        MobileConfigBaseClient mobileConfigBaseClient;
        Exception e;
        this.mParamNamesByConfig = new HashMap();
        this.mSubscribeSucceeded = new AtomicBoolean(false);
        this.mChangeListener = new ChangeListener();
        this.mChangeListenersAdded = false;
        this.mCallback = null;
        this.mMemoryStateLocker = new Object();
        this.mParamsLock = new Object();
        this.mSessionLoggingIdsToLogExposure = new HashSet();
        this.mSessionlessLoggingIdsToLogExposure = new HashSet();
        this.mParamsToPrefetch = new HashSet();
        this.mAsyncFetchInstance = null;
        this.mAsyncFetchWaitTimeInMs = 10000;
        this.mParamsDefaults = new HashMap();
        this.mContext = context;
        sInUse.incrementAndGet();
        try {
            mobileConfigBaseClient = new MobileConfigBaseClient(configuration.mServicePackage, context);
            try {
                mobileConfigBaseClient.mLogger.mLogger = new MarauderLogger(context);
                mobileConfigBaseClient.mQpl = null;
                String paramsMapContent = mobileConfigBaseClient.getParamsMapContent();
                String str = BuildConfig.PROVIDER_SUFFIX;
                paramsMapContent = paramsMapContent == null ? str : paramsMapContent;
                if (paramsMapContent.startsWith("v2,")) {
                    String[] split = paramsMapContent.split("\\r?\\n");
                    if (split.length != 0) {
                        String[] split2 = split[0].split(",");
                        if (split2.length >= 2) {
                            str = split2[1];
                        }
                    }
                }
                this.mCurrentParamsMapVersion = str;
                this.mParamsMapEntries = ParamsMapEntry.parseParamsMapToMap(paramsMapContent);
            } catch (Exception e2) {
                e = e2;
                DeviceConfigTelemetryLogger.logInternalError(this.mContext, e);
                this.mCurrentParamsMapVersion = "##UNKNOWN_VERSION##";
                this.mParamsMapEntries = new HashMap();
                setParamNamesByConfig();
                this.mMobileConfigClient = mobileConfigBaseClient;
                this.mMobileConfigOptions = MobileConfigOptions.create().requestForValueSource();
                this.mMemoryState = ConfigStorageAdapter.createMemoryStateFromStorageCache(context);
                fillParamsDefaults();
            }
        } catch (Exception e3) {
            e = e3;
            mobileConfigBaseClient = null;
            DeviceConfigTelemetryLogger.logInternalError(this.mContext, e);
            this.mCurrentParamsMapVersion = "##UNKNOWN_VERSION##";
            this.mParamsMapEntries = new HashMap();
            setParamNamesByConfig();
            this.mMobileConfigClient = mobileConfigBaseClient;
            this.mMobileConfigOptions = MobileConfigOptions.create().requestForValueSource();
            this.mMemoryState = ConfigStorageAdapter.createMemoryStateFromStorageCache(context);
            fillParamsDefaults();
        }
        setParamNamesByConfig();
        this.mMobileConfigClient = mobileConfigBaseClient;
        this.mMobileConfigOptions = MobileConfigOptions.create().requestForValueSource();
        this.mMemoryState = ConfigStorageAdapter.createMemoryStateFromStorageCache(context);
        fillParamsDefaults();
    }

    private void setParamNamesByConfig() {
        for (Map.Entry<String, ParamsMapEntry> entry : this.mParamsMapEntries.entrySet()) {
            String key = entry.getKey();
            String str = entry.getValue().configName;
            Set<String> set = this.mParamNamesByConfig.get(str);
            if (set == null) {
                set = new HashSet<>();
                this.mParamNamesByConfig.put(str, set);
            }
            set.add(key);
        }
    }

    public final boolean subscribe(final DeviceConfigCallback deviceConfigCallback) {
        if (this.mSubscribeSucceeded.get()) {
            return false;
        }
        try {
            this.mCallback = deviceConfigCallback;
            final long currentTimeMillis = System.currentTimeMillis();
            AnonymousClass1 r0 = new MobileConfigServiceSubscribeCallback() {
                /* class com.oculus.deviceconfigclient.DeviceConfigClient.AnonymousClass1 */

                @Override // com.facebook.mobileconfigservice.client_ifaces.MobileConfigServiceSubscribeCallback
                public final void onMobileConfigSubscribeSuccess() {
                    DeviceConfigTelemetryLogger.logSubscriptionSuccess(DeviceConfigClient.this.mContext, System.currentTimeMillis() - currentTimeMillis);
                    DeviceConfigClient.this.mSubscribeSucceeded.set(true);
                    if (deviceConfigCallback.enableAutoPrefetch()) {
                        DeviceConfigClient.access$300(DeviceConfigClient.this);
                        DeviceConfigClient.access$400(DeviceConfigClient.this);
                    }
                    deviceConfigCallback.onSuccess();
                }

                @Override // com.facebook.mobileconfigservice.client_ifaces.MobileConfigServiceSubscribeCallback
                public final void onMobileConfigSubscribeFailure(String str) {
                    DeviceConfigTelemetryLogger.logSubscriptionFailure(DeviceConfigClient.this.mContext, str);
                    deviceConfigCallback.onFailure(str);
                }
            };
            if (this.mMobileConfigClient == null) {
                return true;
            }
            this.mMobileConfigClient.subscribe(r0);
            return true;
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logSubscriptionFailure(this.mContext, e);
            deviceConfigCallback.onFailure("subscribe() threw an exception");
            return false;
        }
    }

    public final boolean getDeviceBoolean(String str) {
        Boolean bool;
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
                if (this.mMobileConfigClient == null) {
                    return booleanValue;
                }
                boolean booleanWithOptions = this.mMobileConfigClient.getBooleanWithOptions(str, booleanValue, this.mMobileConfigOptions);
                DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_BOOLEAN, str, Boolean.valueOf(booleanWithOptions), getValueSourceString(this.mMobileConfigOptions), System.currentTimeMillis() - currentTimeMillis);
                updateMemoryStateValueDirectlyFromService(this.mMemoryState.mBooleanValues, str, Boolean.valueOf(booleanWithOptions));
                return booleanWithOptions;
            }
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_BOOLEAN, str, bool, serviceValueFromMemoryState.mValueToOverride != null ? "OVERRIDDEN" : "SERVICE_CACHED", System.currentTimeMillis() - currentTimeMillis);
            return bool.booleanValue();
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_BOOLEAN, str, e);
            return getBooleanDefault(str).booleanValue();
        }
    }

    public final double getDeviceDouble(String str) {
        Double d;
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
                if (this.mMobileConfigClient == null) {
                    return doubleValue;
                }
                double doubleWithOptions = this.mMobileConfigClient.getDoubleWithOptions(str, doubleValue, this.mMobileConfigOptions);
                DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_DOUBLE, str, Double.valueOf(doubleWithOptions), getValueSourceString(this.mMobileConfigOptions), System.currentTimeMillis() - currentTimeMillis);
                updateMemoryStateValueDirectlyFromService(this.mMemoryState.mDoubleValues, str, Double.valueOf(doubleWithOptions));
                return doubleWithOptions;
            }
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_DOUBLE, str, d, serviceValueFromMemoryState.mValueToOverride != null ? "OVERRIDDEN" : "SERVICE_CACHED", System.currentTimeMillis() - currentTimeMillis);
            return d.doubleValue();
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_DOUBLE, str, e);
            return getDoubleDefault(str).doubleValue();
        }
    }

    public final long getDeviceLong(String str) {
        Long l;
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
                if (this.mMobileConfigClient == null) {
                    return longValue;
                }
                long longWithOptions = this.mMobileConfigClient.getLongWithOptions(str, longValue, this.mMobileConfigOptions);
                DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_LONG, str, Long.valueOf(longWithOptions), getValueSourceString(this.mMobileConfigOptions), System.currentTimeMillis() - currentTimeMillis);
                updateMemoryStateValueDirectlyFromService(this.mMemoryState.mLongValues, str, Long.valueOf(longWithOptions));
                return longWithOptions;
            }
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_LONG, str, l, serviceValueFromMemoryState.mValueToOverride != null ? "OVERRIDDEN" : "SERVICE_CACHED", System.currentTimeMillis() - currentTimeMillis);
            return l.longValue();
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_LONG, str, e);
            return getLongDefault(str).longValue();
        }
    }

    public final String getDeviceString(String str) {
        String str2;
        try {
            if (!this.mSubscribeSucceeded.get()) {
                DeviceConfigTelemetryLogger.logNotSubscribedError(this.mContext, String.format("Calling getDeviceString(%s) when not subscribed. Return value from local cache.", str));
                return getString(str);
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (!isParamValid(str, 3)) {
                return BuildConfig.PROVIDER_SUFFIX;
            }
            ValueInfo serviceValueFromMemoryState = getServiceValueFromMemoryState(this.mMemoryState.mStringValues, str);
            if (serviceValueFromMemoryState == null || (str2 = (String) serviceValueFromMemoryState.getValue()) == null) {
                String stringDefault = getStringDefault(str);
                if (this.mMobileConfigClient == null) {
                    return stringDefault;
                }
                String stringWithOptions = this.mMobileConfigClient.getStringWithOptions(str, stringDefault, this.mMobileConfigOptions);
                DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_STRING, str, stringWithOptions, getValueSourceString(this.mMobileConfigOptions), System.currentTimeMillis() - currentTimeMillis);
                updateMemoryStateValueDirectlyFromService(this.mMemoryState.mStringValues, str, stringWithOptions);
                return stringWithOptions;
            }
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_STRING, str, str2, serviceValueFromMemoryState.mValueToOverride != null ? "OVERRIDDEN" : "SERVICE_CACHED", System.currentTimeMillis() - currentTimeMillis);
            return str2;
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_STRING, str, e);
            return getStringDefault(str);
        }
    }

    public final void internalPrefetch(String[] strArr) {
        try {
            addAsyncPrefetch(strArr);
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logInternalError(this.mContext, e);
        }
    }

    private void fillParamsDefaults() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.mContext.getAssets().open("params_default.txt")));
            boolean z = true;
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        bufferedReader.close();
                        return;
                    } else if (z) {
                        if (!readLine.equals("DeviceConfig,params_default.txt,V1")) {
                            BLog.e("DeviceConfigClient", "Unexpected header '%s' for default param file", readLine);
                            bufferedReader.close();
                            return;
                        }
                        z = false;
                    } else if (!readLine.isEmpty()) {
                        String[] split = readLine.split("=", 2);
                        if (split.length != 2) {
                            BLog.e("DeviceConfigClient", "Incorrect default format for param '%s'", readLine);
                        } else {
                            String str = split[0];
                            String str2 = split[1];
                            ParamsMapEntry paramsMapEntry = this.mParamsMapEntries.get(str);
                            if (paramsMapEntry == null) {
                                BLog.e("DeviceConfigClient", "Can't set default for unknown param '%s'", str);
                            } else {
                                int i = paramsMapEntry.paramType;
                                if (i == 1) {
                                    this.mParamsDefaults.put(str, Boolean.valueOf(Boolean.parseBoolean(str2)));
                                } else if (i == 2) {
                                    this.mParamsDefaults.put(str, Long.valueOf(Long.parseLong(str2)));
                                } else if (i == 3) {
                                    this.mParamsDefaults.put(str, str2);
                                } else if (i != 4) {
                                    try {
                                        BLog.e("DeviceConfigClient", "Unsupported param type %s", Integer.valueOf(i));
                                    } catch (NumberFormatException unused) {
                                        DeviceConfigTelemetryLogger.logIncorrectDefaultValue(this.mContext, String.format("Cannot convert default value '%s' to type %s", str2, Integer.valueOf(i)), str);
                                    }
                                } else {
                                    this.mParamsDefaults.put(str, Double.valueOf(Double.parseDouble(str2)));
                                }
                            }
                        }
                    }
                } catch (Throwable unused2) {
                }
            }
            throw th;
        } catch (IOException unused3) {
            DeviceConfigTelemetryLogger.logMissingDefaultFile(this.mContext, "IOException while reading default params file", "params_default.txt");
        }
    }

    private boolean isParamValid(String str, int i) {
        ParamsMapEntry paramsMapEntry = this.mParamsMapEntries.get(str);
        if (paramsMapEntry == null) {
            DeviceConfigTelemetryLogger.logUnknownParam(this.mContext, "Can't find param", str);
            return false;
        } else if (paramsMapEntry.paramType == i) {
            return true;
        } else {
            DeviceConfigTelemetryLogger.logIncorrectTypeParam(this.mContext, i != 1 ? i != 2 ? i != 3 ? i != 4 ? "Param has an unknown type" : "Param is not a double" : "Param is not a string" : "Param is not a long" : "Param is not a boolean", str);
            return false;
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

    private Long getLongDefault(String str) {
        Object obj = this.mParamsDefaults.get(str);
        if (obj instanceof Long) {
            return (Long) obj;
        }
        return DEFAULT_LONG;
    }

    private String getStringDefault(String str) {
        Object obj = this.mParamsDefaults.get(str);
        return obj instanceof String ? (String) obj : BuildConfig.PROVIDER_SUFFIX;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private <Type> ValueInfo<Type> getServiceValueFromMemoryState(Map<String, ValueInfo<Type>> map, String str) {
        ValueInfo<Type> valueInfo;
        synchronized (this.mMemoryStateLocker) {
            valueInfo = map.get(str);
        }
        if (valueInfo == null) {
            return null;
        }
        if (valueInfo.mValueToOverride == null) {
            if (valueInfo.mValueSetFlag == ValueInfo.ValueSetFlags.FromService) {
                return valueInfo;
            }
            return null;
        }
        return valueInfo;
    }

    private <Type> ValueInfo<Type> getFromMemoryStateAndSendExposureToService(Map<String, ValueInfo<Type>> map, String str) {
        ValueInfo<Type> valueInfo;
        synchronized (this.mMemoryStateLocker) {
            valueInfo = map.get(str);
        }
        if (valueInfo == null) {
            return null;
        }
        if (valueInfo.mValueToOverride != null) {
            return valueInfo;
        }
        String str2 = valueInfo.mLoggingId;
        if (!valueInfo.mExposureLogged && !TextUtils.isEmpty(str2)) {
            addAsyncExposure$3b99f9eb(str, valueInfo.mSessionless);
            valueInfo.mExposureLogged = true;
        }
        return valueInfo;
    }

    private <Type> void updateMemoryStateValueDirectlyFromService(Map<String, ValueInfo<Type>> map, String str, Type type) {
        updateMemoryStateValueFromServiceInternal$d3720ba(map, str, type, CacheUpdateMode.ReturnedValueAndSerialization$e213452, null, false);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private <Type> void updateMemoryStateValueFromServiceInternal$d3720ba(Map<String, ValueInfo<Type>> map, String str, Type type, int i, String str2, boolean z) {
        ValueInfo<Type> valueInfo;
        this.mMemoryState.mParamsMapVersion = this.mCurrentParamsMapVersion;
        synchronized (this.mMemoryStateLocker) {
            valueInfo = map.get(str);
            if (valueInfo == null) {
                valueInfo = new ValueInfo<>();
                map.put(str, valueInfo);
            }
        }
        int i2 = AnonymousClass2.$SwitchMap$com$oculus$deviceconfigclient$DeviceConfigClient$CacheUpdateMode[i - 1];
        boolean z2 = false;
        if (i2 == 1) {
            z2 = valueInfo.setValueFromService(type, true);
        } else if (i2 == 2) {
            z2 = valueInfo.setValueFromService(type, false);
        } else if (i2 != 3) {
            DeviceConfigTelemetryLogger.logInternalError(this.mContext, "Incorrect CacheUpdateMode");
        } else {
            z2 = valueInfo.setValueForSerialization(type);
        }
        if (z2) {
            ConfigStorageAdapter.writeMemoryStateToStorageCache(this.mContext, this.mMemoryState);
        }
        if (str2 != null) {
            valueInfo.setLoggingId(str2, z);
        } else {
            valueInfo.mExposureLogged = true;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.deviceconfigclient.DeviceConfigClient$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$deviceconfigclient$DeviceConfigClient$CacheUpdateMode = new int[CacheUpdateMode.values$2dfe5f58().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Code restructure failed: missing block: B:9:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0011 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                int[] r0 = com.oculus.deviceconfigclient.DeviceConfigClient.CacheUpdateMode.values$2dfe5f58()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.deviceconfigclient.DeviceConfigClient.AnonymousClass2.$SwitchMap$com$oculus$deviceconfigclient$DeviceConfigClient$CacheUpdateMode = r0
                r0 = 1
                int[] r1 = com.oculus.deviceconfigclient.DeviceConfigClient.AnonymousClass2.$SwitchMap$com$oculus$deviceconfigclient$DeviceConfigClient$CacheUpdateMode     // Catch:{ NoSuchFieldError -> 0x0011 }
                int r2 = com.oculus.deviceconfigclient.DeviceConfigClient.CacheUpdateMode.ReturnedValueAndSerialization$e213452     // Catch:{ NoSuchFieldError -> 0x0011 }
                int r2 = r2 - r0
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0011 }
            L_0x0011:
                int[] r1 = com.oculus.deviceconfigclient.DeviceConfigClient.AnonymousClass2.$SwitchMap$com$oculus$deviceconfigclient$DeviceConfigClient$CacheUpdateMode     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r2 = com.oculus.deviceconfigclient.DeviceConfigClient.CacheUpdateMode.InMemoryAndSerialization$e213452     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r2 = r2 - r0
                r3 = 2
                r1[r2] = r3     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                int[] r1 = com.oculus.deviceconfigclient.DeviceConfigClient.AnonymousClass2.$SwitchMap$com$oculus$deviceconfigclient$DeviceConfigClient$CacheUpdateMode     // Catch:{ NoSuchFieldError -> 0x0021 }
                int r2 = com.oculus.deviceconfigclient.DeviceConfigClient.CacheUpdateMode.SerializationOnly$e213452     // Catch:{ NoSuchFieldError -> 0x0021 }
                int r2 = r2 - r0
                r0 = 3
                r1[r2] = r0     // Catch:{ NoSuchFieldError -> 0x0021 }
            L_0x0021:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.deviceconfigclient.DeviceConfigClient.AnonymousClass2.<clinit>():void");
        }
    }

    /* access modifiers changed from: package-private */
    public class ChangeListener implements IMobileConfigChangeListener {
        ChangeListener() {
        }

        @Override // com.facebook.mobileconfigservice.client_ifaces.IMobileConfigChangeListener
        public final void onConfigChanged(String str) {
            String[] strArr;
            BLog.d("DeviceConfigClient", "Config '%s' updated.", str);
            Set<String> set = (Set) DeviceConfigClient.this.mParamNamesByConfig.get(str);
            if (set != null) {
                strArr = new String[set.size()];
                int i = 0;
                for (String str2 : set) {
                    strArr[i] = str2;
                    i++;
                }
            } else {
                DeviceConfigTelemetryLogger.logInternalParamError(DeviceConfigClient.this.mContext, "Config could not be found in params_map.txt", str);
                strArr = null;
            }
            if (strArr != null) {
                DeviceConfigClient.this.internalPrefetch(strArr);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static class Signal {
        final AtomicBoolean mSignaled;

        private Signal() {
            this.mSignaled = new AtomicBoolean(false);
        }

        /* synthetic */ Signal(byte b) {
            this();
        }

        public final void setSignal() {
            this.mSignaled.set(true);
            synchronized (this) {
                notifyAll();
            }
        }
    }

    private void addAsyncExposure$3b99f9eb(String str, boolean z) {
        boolean add;
        Set<String> set = z ? this.mSessionlessLoggingIdsToLogExposure : this.mSessionLoggingIdsToLogExposure;
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
            ParamsMapEntry paramsMapEntry = this.mParamsMapEntries.get(str);
            if (paramsMapEntry != null) {
                hashSet.add(getParamNameForGetMultiple(str, paramsMapEntry.paramType));
            } else {
                DeviceConfigTelemetryLogger.logUnknownParam(this.mContext, "Expected to fail with getMultiple()", str);
                hashSet.add(str);
            }
        }
        synchronized (this.mParamsLock) {
            addAll = this.mParamsToPrefetch.addAll(hashSet);
        }
        if (addAll) {
            signalAsyncFetch();
        }
    }

    private String getParamNameForGetMultiple(String str, int i) {
        String str2;
        if (i == 1) {
            str2 = Boolean.toString(getBooleanDefault(str).booleanValue());
        } else if (i == 2) {
            str2 = Long.toString(getLongDefault(str).longValue());
        } else if (i == 3) {
            str2 = getStringDefault(str);
        } else if (i != 4) {
            DeviceConfigTelemetryLogger.logUnknownMCType(this.mContext, i);
            str2 = BuildConfig.PROVIDER_SUFFIX;
        } else {
            str2 = Double.toString(getDoubleDefault(str).doubleValue());
        }
        return str + ":" + str2;
    }

    private void signalAsyncFetch() {
        AsyncFetch asyncFetch;
        synchronized (this.mParamsLock) {
            if (this.mAsyncFetchInstance == null) {
                this.mAsyncFetchInstance = new AsyncFetch();
            }
            asyncFetch = this.mAsyncFetchInstance;
        }
        asyncFetch.mSignal.setSignal();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0029  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean clearAsyncFetch(com.oculus.deviceconfigclient.DeviceConfigClient.AsyncFetch r4, boolean r5) {
        /*
            r3 = this;
            java.lang.Object r0 = r3.mParamsLock
            monitor-enter(r0)
            com.oculus.deviceconfigclient.DeviceConfigClient$AsyncFetch r1 = r3.mAsyncFetchInstance     // Catch:{ all -> 0x0030 }
            r2 = 0
            if (r1 != r4) goto L_0x002e
            r4 = 1
            if (r5 == 0) goto L_0x0026
            java.util.Set<java.lang.String> r5 = r3.mSessionLoggingIdsToLogExposure     // Catch:{ all -> 0x0030 }
            boolean r5 = r5.isEmpty()     // Catch:{ all -> 0x0030 }
            if (r5 == 0) goto L_0x0024
            java.util.Set<java.lang.String> r5 = r3.mSessionlessLoggingIdsToLogExposure     // Catch:{ all -> 0x0030 }
            boolean r5 = r5.isEmpty()     // Catch:{ all -> 0x0030 }
            if (r5 == 0) goto L_0x0024
            java.util.Set<java.lang.String> r5 = r3.mParamsToPrefetch     // Catch:{ all -> 0x0030 }
            boolean r5 = r5.isEmpty()     // Catch:{ all -> 0x0030 }
            if (r5 == 0) goto L_0x0024
            goto L_0x0026
        L_0x0024:
            r5 = 0
            goto L_0x0027
        L_0x0026:
            r5 = 1
        L_0x0027:
            if (r5 == 0) goto L_0x002e
            r5 = 0
            r3.mAsyncFetchInstance = r5     // Catch:{ all -> 0x0030 }
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            return r4
        L_0x002e:
            monitor-exit(r0)
            return r2
        L_0x0030:
            r4 = move-exception
            monitor-exit(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.deviceconfigclient.DeviceConfigClient.clearAsyncFetch(com.oculus.deviceconfigclient.DeviceConfigClient$AsyncFetch, boolean):boolean");
    }

    /* access modifiers changed from: package-private */
    public class AsyncFetch {
        final Signal mSignal = new Signal((byte) 0);
        final AtomicBoolean mStopThread = new AtomicBoolean(false);
        private final Thread mThread;

        public AsyncFetch() {
            DeviceConfigClient.access$800(DeviceConfigClient.this, "StartThread - ParamsToPrefetch: ", DeviceConfigClient.this.mParamsToPrefetch);
            DeviceConfigClient.access$800(DeviceConfigClient.this, "StartThread - LogExposure: ", DeviceConfigClient.this.mSessionLoggingIdsToLogExposure);
            DeviceConfigClient.access$800(DeviceConfigClient.this, "StartThread - LogExposureSessionless: ", DeviceConfigClient.this.mSessionlessLoggingIdsToLogExposure);
            this.mThread = new AsyncFetchThread(this);
            this.mThread.setName("DeviceConfig-AsyncFetch");
            this.mThread.start();
        }

        public final void waitForSignal() {
            Signal signal = this.mSignal;
            Context context = DeviceConfigClient.this.mContext;
            long j = (long) DeviceConfigClient.this.mAsyncFetchWaitTimeInMs;
            if (!signal.mSignaled.get()) {
                try {
                    synchronized (signal) {
                        signal.wait(j);
                    }
                } catch (InterruptedException unused) {
                    DeviceConfigTelemetryLogger.logInternalError(context, "Signal wait() got interrupted");
                    Thread.currentThread().interrupt();
                }
            }
            signal.mSignaled.set(false);
        }

        public final boolean doWork() {
            Set<String> set;
            Set<String> set2;
            Set<String> set3;
            if (!DeviceConfigClient.this.mSubscribeSucceeded.get()) {
                DeviceConfigClient deviceConfigClient = DeviceConfigClient.this;
                DeviceConfigClient.access$800(deviceConfigClient, "Unsubscribed - ParamsToPrefetch: ", deviceConfigClient.mParamsToPrefetch);
                DeviceConfigClient deviceConfigClient2 = DeviceConfigClient.this;
                DeviceConfigClient.access$800(deviceConfigClient2, "Unsubscribed - LogExposure: ", deviceConfigClient2.mSessionLoggingIdsToLogExposure);
                DeviceConfigClient deviceConfigClient3 = DeviceConfigClient.this;
                DeviceConfigClient.access$800(deviceConfigClient3, "Unsubscribed - LogExposureSessionless: ", deviceConfigClient3.mSessionlessLoggingIdsToLogExposure);
                return true;
            }
            synchronized (DeviceConfigClient.this.mParamsLock) {
                set = DeviceConfigClient.this.mSessionLoggingIdsToLogExposure;
                set2 = DeviceConfigClient.this.mSessionlessLoggingIdsToLogExposure;
                set3 = DeviceConfigClient.this.mParamsToPrefetch;
                DeviceConfigClient.this.mSessionLoggingIdsToLogExposure = new HashSet();
                DeviceConfigClient.this.mSessionlessLoggingIdsToLogExposure = new HashSet();
                DeviceConfigClient.this.mParamsToPrefetch = new HashSet();
            }
            return fetchConfigParams(set3) | logExposures(set, false) | logExposures(set2, true);
        }

        private boolean fetchConfigParams(Set<String> set) {
            if (set.isEmpty()) {
                return false;
            }
            String[] strArr = new String[set.size()];
            set.toArray(strArr);
            Map<String, com.facebook.mobileconfigservice.client_base.ValueInfo> multiple = DeviceConfigClient.this.mMobileConfigClient.getMultiple(strArr);
            HashSet hashSet = new HashSet();
            HashSet hashSet2 = new HashSet();
            for (Map.Entry<String, com.facebook.mobileconfigservice.client_base.ValueInfo> entry : multiple.entrySet()) {
                String key = entry.getKey();
                com.facebook.mobileconfigservice.client_base.ValueInfo value = entry.getValue();
                String str = value.mQueryString;
                if (set.contains(str)) {
                    hashSet.add(key);
                    hashSet2.add(str);
                }
                Object obj = value.mValue;
                if (obj == null) {
                    DeviceConfigTelemetryLogger.logUnknownParam(DeviceConfigClient.this.mContext, "ConfigParam fetched a null. Skipping.", key);
                } else {
                    String str2 = value.mLoggingId;
                    boolean z = value.mIsSessionless;
                    int i = value.mType;
                    if (i == 1) {
                        int queryCacheUpdateMode$127abc7e = queryCacheUpdateMode$127abc7e(key, DeviceConfigClient.this.mMemoryState.mBooleanValues, obj);
                        DeviceConfigClient deviceConfigClient = DeviceConfigClient.this;
                        deviceConfigClient.updateMemoryStateValueFromServiceInternal$d3720ba(deviceConfigClient.mMemoryState.mBooleanValues, key, Boolean.valueOf(((Boolean) obj).booleanValue()), queryCacheUpdateMode$127abc7e, str2, z);
                    } else if (i == 2) {
                        int queryCacheUpdateMode$127abc7e2 = queryCacheUpdateMode$127abc7e(key, DeviceConfigClient.this.mMemoryState.mLongValues, obj);
                        DeviceConfigClient deviceConfigClient2 = DeviceConfigClient.this;
                        deviceConfigClient2.updateMemoryStateValueFromServiceInternal$d3720ba(deviceConfigClient2.mMemoryState.mLongValues, key, Long.valueOf(((Long) obj).longValue()), queryCacheUpdateMode$127abc7e2, str2, z);
                    } else if (i == 3) {
                        int queryCacheUpdateMode$127abc7e3 = queryCacheUpdateMode$127abc7e(key, DeviceConfigClient.this.mMemoryState.mStringValues, obj);
                        DeviceConfigClient deviceConfigClient3 = DeviceConfigClient.this;
                        deviceConfigClient3.updateMemoryStateValueFromServiceInternal$d3720ba(deviceConfigClient3.mMemoryState.mStringValues, key, (String) obj, queryCacheUpdateMode$127abc7e3, str2, z);
                    } else if (i != 4) {
                        DeviceConfigTelemetryLogger.logUnknownMCType(DeviceConfigClient.this.mContext, value.mType);
                    } else {
                        int queryCacheUpdateMode$127abc7e4 = queryCacheUpdateMode$127abc7e(key, DeviceConfigClient.this.mMemoryState.mDoubleValues, obj);
                        DeviceConfigClient deviceConfigClient4 = DeviceConfigClient.this;
                        deviceConfigClient4.updateMemoryStateValueFromServiceInternal$d3720ba(deviceConfigClient4.mMemoryState.mDoubleValues, key, Double.valueOf(((Double) obj).doubleValue()), queryCacheUpdateMode$127abc7e4, str2, z);
                    }
                }
            }
            if (!set.isEmpty()) {
                if (DeviceConfigClient.this.mCallback != null) {
                    String[] strArr2 = new String[hashSet.size()];
                    hashSet.toArray(strArr2);
                    DeviceConfigClient.this.mCallback.onPrefetched(strArr2);
                }
                if (set.size() != hashSet2.size()) {
                    HashSet hashSet3 = new HashSet(set);
                    hashSet3.removeAll(hashSet2);
                    DeviceConfigTelemetryLogger.logUnknownParam(DeviceConfigClient.this.mContext, "Could not prefetch the following config params", TextUtils.join(", ", hashSet3));
                }
            }
            return true;
        }

        private <Type> int queryCacheUpdateMode$127abc7e(String str, Map<String, ValueInfo<Type>> map, Object obj) {
            if (DeviceConfigClient.this.mCallback == null) {
                return CacheUpdateMode.SerializationOnly$e213452;
            }
            ValueInfo serviceValueFromMemoryState = DeviceConfigClient.this.getServiceValueFromMemoryState(map, str);
            if (serviceValueFromMemoryState == null || !serviceValueFromMemoryState.mValueReturned) {
                return CacheUpdateMode.InMemoryAndSerialization$e213452;
            }
            if (serviceValueFromMemoryState.mValueToOverride != null) {
                return CacheUpdateMode.SerializationOnly$e213452;
            }
            Object value = serviceValueFromMemoryState.getValue();
            if (value == null) {
                DeviceConfigTelemetryLogger.logInternalParamError(DeviceConfigClient.this.mContext, "Current value is null", str);
                return CacheUpdateMode.SerializationOnly$e213452;
            } else if (value.equals(obj)) {
                return CacheUpdateMode.SerializationOnly$e213452;
            } else {
                DeviceConfigCallback unused = DeviceConfigClient.this.mCallback;
                if (DeviceConfigCallback.ParamChangedResult.UpdateAtNextStart$4778ad50 == DeviceConfigCallback.ParamChangedResult.UpdateNow$4778ad50) {
                    return CacheUpdateMode.ReturnedValueAndSerialization$e213452;
                }
                return CacheUpdateMode.SerializationOnly$e213452;
            }
        }

        private boolean logExposures(Set<String> set, boolean z) {
            if (set.isEmpty()) {
                return false;
            }
            for (String str : set) {
                try {
                    ContentProviderClient acquireUnstableContentProviderClient = DeviceConfigClient.this.mMobileConfigClient.mContext.getContentResolver().acquireUnstableContentProviderClient(MobileConfigServiceConstants.LOG_EXPOSURE_URI);
                    if (acquireUnstableContentProviderClient == null) {
                        try {
                            BLog.e("MobileConfigBaseClient", "Content provider for the mobileconfig service not found");
                            if (acquireUnstableContentProviderClient == null) {
                            }
                        } catch (Throwable unused) {
                        }
                    } else {
                        String[] strArr = new String[1];
                        strArr[0] = z ? "1" : "0";
                        MobileConfigBaseClient.closeSilently(acquireUnstableContentProviderClient.query(MobileConfigServiceConstants.LOG_EXPOSURE_URI, strArr, str, new String[0], null));
                    }
                    acquireUnstableContentProviderClient.close();
                } catch (RemoteException | SecurityException e) {
                    BLog.e("MobileConfigBaseClient", "Could not find mobileconfigservice; is the service running?", e);
                }
            }
            return true;
            throw th;
        }
    }

    class AsyncFetchThread extends Thread {
        private final AsyncFetch mAsyncFetchInstance;

        public AsyncFetchThread(AsyncFetch asyncFetch) {
            this.mAsyncFetchInstance = asyncFetch;
        }

        public final void run() {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                while (true) {
                    if (Thread.interrupted() || this.mAsyncFetchInstance.mStopThread.get()) {
                        break;
                    }
                    if (!this.mAsyncFetchInstance.doWork()) {
                        if (System.currentTimeMillis() - currentTimeMillis > 60000 && DeviceConfigClient.this.clearAsyncFetch(this.mAsyncFetchInstance, true)) {
                            BLog.d("DeviceConfigClient", "AsyncFetchThread has been idle for a while. Stopping it.");
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

    public final boolean getBoolean(String str) {
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
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.BOOLEAN, str, bool, fromMemoryStateAndSendExposureToService.mValueToOverride != null ? "OVERRIDDEN" : "CACHED", System.currentTimeMillis() - currentTimeMillis);
            if (bool == null) {
                bool = getBooleanDefault(str);
            }
            return bool.booleanValue();
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.BOOLEAN, str, e);
            return getBooleanDefault(str).booleanValue();
        }
    }

    public final double getDouble(String str) {
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
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DOUBLE, str, d, fromMemoryStateAndSendExposureToService.mValueToOverride != null ? "OVERRIDDEN" : "CACHED", System.currentTimeMillis() - currentTimeMillis);
            if (d == null) {
                d = getDoubleDefault(str);
            }
            return d.doubleValue();
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.DOUBLE, str, e);
            return getDoubleDefault(str).doubleValue();
        }
    }

    public final long getLong(String str) {
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
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.LONG, str, l, fromMemoryStateAndSendExposureToService.mValueToOverride != null ? "OVERRIDDEN" : "CACHED", System.currentTimeMillis() - currentTimeMillis);
            if (l == null) {
                l = getLongDefault(str);
            }
            return l.longValue();
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.LONG, str, e);
            return getLongDefault(str).longValue();
        }
    }

    public final String getString(String str) {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            if (!isParamValid(str, 3)) {
                return BuildConfig.PROVIDER_SUFFIX;
            }
            ValueInfo fromMemoryStateAndSendExposureToService = getFromMemoryStateAndSendExposureToService(this.mMemoryState.mStringValues, str);
            if (fromMemoryStateAndSendExposureToService == null) {
                return getStringDefault(str);
            }
            String str2 = (String) fromMemoryStateAndSendExposureToService.getValue();
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.STRING, str, str2, fromMemoryStateAndSendExposureToService.mValueToOverride != null ? "OVERRIDDEN" : "CACHED", System.currentTimeMillis() - currentTimeMillis);
            if (str2 != null) {
                return str2;
            }
            return getStringDefault(str);
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.STRING, str, e);
            return getStringDefault(str);
        }
    }

    private static String getValueSourceString(MobileConfigOptions mobileConfigOptions) {
        String[] strArr = {"SERVER", "OVERRIDE", "DEFAULT__SERVER_RETURNED_NULL", "DEFAULT__ACCESSED_BEFORE_MC_INIT", "DEFAULT__NO_DATA_ON_DISK", "DEFAULT__ACCESSED_AFTER_MC_DISPOSE", "DEFAULT__MISSING_SERVER_VALUE"};
        int i = mobileConfigOptions.mValueSource.source;
        return (i < 0 || i >= 7) ? "UNKNOWN" : strArr[i];
    }

    static /* synthetic */ void access$300(DeviceConfigClient deviceConfigClient) {
        MobileConfigBaseClient.MobileConfigContentObserver mobileConfigContentObserver;
        if (deviceConfigClient.mMobileConfigClient == null) {
            BLog.e("DeviceConfigClient", "MobileConfig client is not initialized properly - Can't add change listeners.");
            return;
        } else if (deviceConfigClient.mChangeListenersAdded) {
            BLog.w("DeviceConfigClient", "ChangeListeners have been already added.");
            return;
        } else {
            for (Map.Entry<String, Set<String>> entry : deviceConfigClient.mParamNamesByConfig.entrySet()) {
                String key = entry.getKey();
                MobileConfigBaseClient mobileConfigBaseClient = deviceConfigClient.mMobileConfigClient;
                ChangeListener changeListener = deviceConfigClient.mChangeListener;
                synchronized (mobileConfigBaseClient.mMobileConfigContentObservers) {
                    if (mobileConfigBaseClient.mMobileConfigContentObservers.containsKey(key)) {
                        Iterator<MobileConfigBaseClient.MobileConfigContentObserver> it = mobileConfigBaseClient.mMobileConfigContentObservers.get(key).iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            mobileConfigContentObserver = it.next();
                            if (mobileConfigContentObserver.mConfigChangeListener.equals(changeListener)) {
                                break;
                            }
                        }
                    }
                    mobileConfigContentObserver = null;
                    if (mobileConfigContentObserver != null) {
                        mobileConfigBaseClient.mLogger.logApiResponse("addChangeListener", key, true, "Already added");
                    } else {
                        MobileConfigBaseClient.MobileConfigContentObserver mobileConfigContentObserver2 = new MobileConfigBaseClient.MobileConfigContentObserver(key, changeListener, mobileConfigBaseClient.mContext);
                        mobileConfigBaseClient.mContext.getContentResolver().registerContentObserver(Uri.withAppendedPath(Uri.withAppendedPath(MobileConfigServiceConstants.CHANGE_LISTENER_PATH, key), mobileConfigBaseClient.getProcessName()), false, mobileConfigContentObserver2);
                        boolean z = !(mobileConfigBaseClient.mMobileConfigContentObservers.containsKey(key) && mobileConfigBaseClient.mMobileConfigContentObservers.get(key).size() > 0);
                        if (!mobileConfigBaseClient.mMobileConfigContentObservers.containsKey(key)) {
                            mobileConfigBaseClient.mMobileConfigContentObservers.put(key, new ArrayList());
                        }
                        mobileConfigBaseClient.mMobileConfigContentObservers.get(key).add(mobileConfigContentObserver2);
                        if (!z) {
                            mobileConfigBaseClient.mLogger.logApiResponse("addChangeListener", key, true, "Already added in server");
                        } else {
                            try {
                                ContentProviderClient acquireUnstableContentProviderClient = mobileConfigBaseClient.mContext.getContentResolver().acquireUnstableContentProviderClient(MobileConfigServiceConstants.CHANGE_LISTENER_PATH);
                                if (acquireUnstableContentProviderClient == null) {
                                    try {
                                        BLog.e("MobileConfigBaseClient", "Content provider for the mobileconfig service not found");
                                        mobileConfigBaseClient.mLogger.logApiResponse("addChangeListener", key, false, "Service not found");
                                        if (acquireUnstableContentProviderClient != null) {
                                            acquireUnstableContentProviderClient.close();
                                        }
                                    } catch (Throwable unused) {
                                    }
                                } else {
                                    MobileConfigBaseClient.closeSilently(acquireUnstableContentProviderClient.query(MobileConfigServiceConstants.CHANGE_LISTENER_PATH, new String[0], key, new String[0], mobileConfigBaseClient.getProcessName()));
                                    mobileConfigBaseClient.mLogger.logApiResponse("addChangeListener", key, true, BuildConfig.PROVIDER_SUFFIX);
                                    acquireUnstableContentProviderClient.close();
                                }
                            } catch (RemoteException | SecurityException e) {
                                mobileConfigBaseClient.mLogger.logApiResponse("addChangeListener", key, false, "Service not running");
                                BLog.e("MobileConfigBaseClient", "Could not find mobileconfigservice; is the service running?", e);
                            }
                        }
                    }
                }
            }
            deviceConfigClient.mChangeListenersAdded = true;
            return;
        }
        throw th;
    }

    static /* synthetic */ void access$400(DeviceConfigClient deviceConfigClient) {
        boolean addAll;
        HashSet hashSet = new HashSet();
        for (Map.Entry<String, ParamsMapEntry> entry : deviceConfigClient.mParamsMapEntries.entrySet()) {
            hashSet.add(deviceConfigClient.getParamNameForGetMultiple(entry.getKey(), entry.getValue().paramType));
        }
        synchronized (deviceConfigClient.mParamsLock) {
            addAll = deviceConfigClient.mParamsToPrefetch.addAll(hashSet);
        }
        if (addAll) {
            deviceConfigClient.signalAsyncFetch();
        }
    }
}
