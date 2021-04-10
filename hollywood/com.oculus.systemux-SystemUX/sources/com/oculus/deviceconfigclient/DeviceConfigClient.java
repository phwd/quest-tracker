package com.oculus.deviceconfigclient;

import android.annotation.SuppressLint;
import android.content.ContentProviderClient;
import android.content.Context;
import android.database.Cursor;
import android.os.RemoteException;
import android.text.TextUtils;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import com.facebook.mobileconfig.factory.MobileConfigOptions;
import com.facebook.mobileconfig.metadata.ParamsMapEntry;
import com.facebook.mobileconfigservice.client_base.MobileConfigBaseClient;
import com.facebook.mobileconfigservice.client_base.ValueInfo;
import com.facebook.mobileconfigservice.client_ifaces.IMobileConfigChangeListener;
import com.facebook.mobileconfigservice.client_ifaces.MobileConfigServiceSubscribeCallback;
import com.facebook.mobileconfigservice.serviceconstants.MobileConfigDebugParamSchema;
import com.facebook.mobileconfigservice.serviceconstants.MobileConfigOverriddenParamSchema;
import com.facebook.mobileconfigservice.serviceconstants.MobileConfigServiceConstants;
import com.oculus.deviceconfigclient.DebugOnlyValueInfo;
import com.oculus.deviceconfigclient.DeviceConfigCallback;
import com.oculus.deviceconfigclient.DeviceConfigTelemetryLogger;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class DeviceConfigClient {
    private static final String CACHED_SOURCE = "CACHED";
    private static final Boolean DEFAULT_BOOLEAN = false;
    private static final Double DEFAULT_DOUBLE = Double.valueOf(0.0d);
    private static final Long DEFAULT_LONG = 0L;
    private static final String DEFAULT_STRING = "";
    private static final String EMPTY_CONFIG_PARAM = "__DEVICE_CONFIG_EMPTY_VALUE__";
    private static final String OVERRIDDEN_SOURCE = "OVERRIDDEN";
    private static final String PARAMS_DEFAULT_FILENAME = "params_default.txt";
    private static final String SERVICE_CACHED_SOURCE = "SERVICE_CACHED";
    private static final String TAG = "DeviceConfigClient";
    private static final boolean mDebugLog = false;
    @Nullable
    private AsyncFetch mAsyncFetchInstance;
    private int mAsyncFetchWaitTimeInMs;
    @Nullable
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
    private Map<String, ParamsMapEntry> mParamsMapEntries;
    private Set<String> mParamsToPrefetch;
    private Set<String> mSessionLoggingIdsToLogExposure;
    private Set<String> mSessionlessLoggingIdsToLogExposure;
    private final AtomicBoolean mSubscribeSucceeded;

    /* access modifiers changed from: private */
    public enum CacheUpdateMode {
        ReturnedValueAndSerialization,
        InMemoryAndSerialization,
        SerializationOnly
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void logSynchronizedSet(String str, Set<String> set) {
    }

    @Deprecated
    public void debugOnlyEnableSetOnFetch(boolean z) {
    }

    public static class Configuration {
        private String mServicePackage = "com.oculus.horizon";

        public String getMobileConfigServicePackage() {
            return this.mServicePackage;
        }

        public void setMobileConfigServicePackage(String str) {
            this.mServicePackage = str;
        }
    }

    public DeviceConfigClient(Context context) {
        this(context, new Configuration());
    }

    public DeviceConfigClient(Context context, Configuration configuration) {
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
        try {
            mobileConfigBaseClient = new MobileConfigBaseClient(configuration.getMobileConfigServicePackage(), context);
            try {
                mobileConfigBaseClient.initLogging(new MarauderLogger(context), null);
                String paramsMapContent = mobileConfigBaseClient.getParamsMapContent();
                this.mCurrentParamsMapVersion = paramsMapContent.split("\n", 2)[0];
                this.mParamsMapEntries = ParamsMapEntry.parseParamsMapToMap(paramsMapContent);
            } catch (Exception e2) {
                e = e2;
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

    public boolean isMobileConfigInitialized() {
        return this.mMobileConfigClient != null;
    }

    public boolean isLocalCacheWithFetchedValues() {
        return this.mCurrentParamsMapVersion.equals(this.mMemoryState.getParamsMapVersion());
    }

    public void shutdown() {
        asyncFetchShutdown();
        this.mCallback = null;
        this.mParamNamesByConfig.clear();
        this.mMemoryState.clear();
        this.mSubscribeSucceeded.set(false);
        removeChangeListeners();
    }

    public boolean subscribe(final DeviceConfigCallback deviceConfigCallback) {
        if (this.mSubscribeSucceeded.get()) {
            return false;
        }
        try {
            this.mCallback = deviceConfigCallback;
            final long timeNowInMs = getTimeNowInMs();
            AnonymousClass1 r0 = new MobileConfigServiceSubscribeCallback() {
                /* class com.oculus.deviceconfigclient.DeviceConfigClient.AnonymousClass1 */

                @Override // com.facebook.mobileconfigservice.client_ifaces.MobileConfigServiceSubscribeCallback
                public void onMobileConfigSubscribeSuccess() {
                    DeviceConfigTelemetryLogger.logSubscriptionSuccess(DeviceConfigClient.this.mContext, DeviceConfigClient.getTimeNowInMs() - timeNowInMs);
                    DeviceConfigClient.this.mSubscribeSucceeded.set(true);
                    if (deviceConfigCallback.enableAutoPrefetch()) {
                        DeviceConfigClient.this.addChangeListeners();
                        DeviceConfigClient.this.addAsyncPrefetchAllParams();
                    }
                    deviceConfigCallback.onSuccess();
                }

                @Override // com.facebook.mobileconfigservice.client_ifaces.MobileConfigServiceSubscribeCallback
                public void onMobileConfigSubscribeFailure(String str) {
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

    public boolean getBoolean(String str) {
        try {
            long timeNowInMs = getTimeNowInMs();
            if (!isParamValid(str, 1)) {
                return DEFAULT_BOOLEAN.booleanValue();
            }
            ValueInfo fromMemoryStateAndSendExposureToService = getFromMemoryStateAndSendExposureToService(this.mMemoryState.getBooleanValues(), str);
            if (fromMemoryStateAndSendExposureToService == null) {
                return getBooleanDefault(str).booleanValue();
            }
            Boolean bool = (Boolean) fromMemoryStateAndSendExposureToService.getValue();
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.BOOLEAN, str, bool, fromMemoryStateAndSendExposureToService.getValueToOverride() != null ? OVERRIDDEN_SOURCE : CACHED_SOURCE, getTimeNowInMs() - timeNowInMs);
            if (bool == null) {
                bool = getBooleanDefault(str);
            }
            return bool.booleanValue();
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.BOOLEAN, str, e);
            return getBooleanDefault(str).booleanValue();
        }
    }

    public double getDouble(String str) {
        try {
            long timeNowInMs = getTimeNowInMs();
            if (!isParamValid(str, 4)) {
                return DEFAULT_DOUBLE.doubleValue();
            }
            ValueInfo fromMemoryStateAndSendExposureToService = getFromMemoryStateAndSendExposureToService(this.mMemoryState.getDoubleValues(), str);
            if (fromMemoryStateAndSendExposureToService == null) {
                return getDoubleDefault(str).doubleValue();
            }
            Double d = (Double) fromMemoryStateAndSendExposureToService.getValue();
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DOUBLE, str, d, fromMemoryStateAndSendExposureToService.getValueToOverride() != null ? OVERRIDDEN_SOURCE : CACHED_SOURCE, getTimeNowInMs() - timeNowInMs);
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
        try {
            long timeNowInMs = getTimeNowInMs();
            if (!isParamValid(str, 2)) {
                return DEFAULT_LONG.longValue();
            }
            ValueInfo fromMemoryStateAndSendExposureToService = getFromMemoryStateAndSendExposureToService(this.mMemoryState.getLongValues(), str);
            if (fromMemoryStateAndSendExposureToService == null) {
                return getLongDefault(str).longValue();
            }
            Long l = (Long) fromMemoryStateAndSendExposureToService.getValue();
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.LONG, str, l, fromMemoryStateAndSendExposureToService.getValueToOverride() != null ? OVERRIDDEN_SOURCE : CACHED_SOURCE, getTimeNowInMs() - timeNowInMs);
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
        try {
            long timeNowInMs = getTimeNowInMs();
            if (!isParamValid(str, 3)) {
                return "";
            }
            ValueInfo fromMemoryStateAndSendExposureToService = getFromMemoryStateAndSendExposureToService(this.mMemoryState.getStringValues(), str);
            if (fromMemoryStateAndSendExposureToService == null) {
                return getStringDefault(str);
            }
            String str2 = (String) fromMemoryStateAndSendExposureToService.getValue();
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.STRING, str, str2, fromMemoryStateAndSendExposureToService.getValueToOverride() != null ? OVERRIDDEN_SOURCE : CACHED_SOURCE, getTimeNowInMs() - timeNowInMs);
            return str2 != null ? str2 : getStringDefault(str);
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.STRING, str, e);
            return getStringDefault(str);
        }
    }

    public boolean getDeviceBoolean(String str) {
        Boolean bool;
        try {
            if (!this.mSubscribeSucceeded.get()) {
                DeviceConfigTelemetryLogger.logNotSubscribedError(this.mContext, String.format("Calling getDeviceBoolean(%s) when not subscribed. Return value from local cache.", str));
                return getBoolean(str);
            }
            long timeNowInMs = getTimeNowInMs();
            if (!isParamValid(str, 1)) {
                return DEFAULT_BOOLEAN.booleanValue();
            }
            ValueInfo serviceValueFromMemoryState = getServiceValueFromMemoryState(this.mMemoryState.getBooleanValues(), str);
            if (serviceValueFromMemoryState == null || (bool = (Boolean) serviceValueFromMemoryState.getValue()) == null) {
                boolean booleanValue = getBooleanDefault(str).booleanValue();
                if (this.mMobileConfigClient == null) {
                    return booleanValue;
                }
                boolean booleanWithOptions = this.mMobileConfigClient.getBooleanWithOptions(str, booleanValue, this.mMobileConfigOptions);
                DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_BOOLEAN, str, Boolean.valueOf(booleanWithOptions), getValueSourceString(this.mMobileConfigOptions), getTimeNowInMs() - timeNowInMs);
                updateMemoryStateValueDirectlyFromService(this.mMemoryState.getBooleanValues(), str, Boolean.valueOf(booleanWithOptions));
                return booleanWithOptions;
            }
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_BOOLEAN, str, bool, serviceValueFromMemoryState.getValueToOverride() != null ? OVERRIDDEN_SOURCE : SERVICE_CACHED_SOURCE, getTimeNowInMs() - timeNowInMs);
            return bool.booleanValue();
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_BOOLEAN, str, e);
            return getBooleanDefault(str).booleanValue();
        }
    }

    public double getDeviceDouble(String str) {
        Double d;
        try {
            if (!this.mSubscribeSucceeded.get()) {
                DeviceConfigTelemetryLogger.logNotSubscribedError(this.mContext, String.format("Calling getDeviceDouble(%s) when not subscribed. Return value from local cache.", str));
                return getDouble(str);
            }
            long timeNowInMs = getTimeNowInMs();
            if (!isParamValid(str, 4)) {
                return DEFAULT_DOUBLE.doubleValue();
            }
            ValueInfo serviceValueFromMemoryState = getServiceValueFromMemoryState(this.mMemoryState.getDoubleValues(), str);
            if (serviceValueFromMemoryState == null || (d = (Double) serviceValueFromMemoryState.getValue()) == null) {
                double doubleValue = getDoubleDefault(str).doubleValue();
                if (this.mMobileConfigClient == null) {
                    return doubleValue;
                }
                double doubleWithOptions = this.mMobileConfigClient.getDoubleWithOptions(str, doubleValue, this.mMobileConfigOptions);
                DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_DOUBLE, str, Double.valueOf(doubleWithOptions), getValueSourceString(this.mMobileConfigOptions), getTimeNowInMs() - timeNowInMs);
                updateMemoryStateValueDirectlyFromService(this.mMemoryState.getDoubleValues(), str, Double.valueOf(doubleWithOptions));
                return doubleWithOptions;
            }
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_DOUBLE, str, d, serviceValueFromMemoryState.getValueToOverride() != null ? OVERRIDDEN_SOURCE : SERVICE_CACHED_SOURCE, getTimeNowInMs() - timeNowInMs);
            return d.doubleValue();
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_DOUBLE, str, e);
            return getDoubleDefault(str).doubleValue();
        }
    }

    public long getDeviceLong(String str) {
        Long l;
        try {
            if (!this.mSubscribeSucceeded.get()) {
                DeviceConfigTelemetryLogger.logNotSubscribedError(this.mContext, String.format("Calling getDeviceLong(%s) when not subscribed. Return value from local cache.", str));
                return getLong(str);
            }
            long timeNowInMs = getTimeNowInMs();
            if (!isParamValid(str, 2)) {
                return DEFAULT_LONG.longValue();
            }
            ValueInfo serviceValueFromMemoryState = getServiceValueFromMemoryState(this.mMemoryState.getLongValues(), str);
            if (serviceValueFromMemoryState == null || (l = (Long) serviceValueFromMemoryState.getValue()) == null) {
                long longValue = getLongDefault(str).longValue();
                if (this.mMobileConfigClient == null) {
                    return longValue;
                }
                long longWithOptions = this.mMobileConfigClient.getLongWithOptions(str, longValue, this.mMobileConfigOptions);
                DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_LONG, str, Long.valueOf(longWithOptions), getValueSourceString(this.mMobileConfigOptions), getTimeNowInMs() - timeNowInMs);
                updateMemoryStateValueDirectlyFromService(this.mMemoryState.getLongValues(), str, Long.valueOf(longWithOptions));
                return longWithOptions;
            }
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_LONG, str, l, serviceValueFromMemoryState.getValueToOverride() != null ? OVERRIDDEN_SOURCE : SERVICE_CACHED_SOURCE, getTimeNowInMs() - timeNowInMs);
            return l.longValue();
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_LONG, str, e);
            return getLongDefault(str).longValue();
        }
    }

    public String getDeviceString(String str) {
        String str2;
        try {
            if (!this.mSubscribeSucceeded.get()) {
                DeviceConfigTelemetryLogger.logNotSubscribedError(this.mContext, String.format("Calling getDeviceString(%s) when not subscribed. Return value from local cache.", str));
                return getString(str);
            }
            long timeNowInMs = getTimeNowInMs();
            if (!isParamValid(str, 3)) {
                return "";
            }
            ValueInfo serviceValueFromMemoryState = getServiceValueFromMemoryState(this.mMemoryState.getStringValues(), str);
            if (serviceValueFromMemoryState == null || (str2 = (String) serviceValueFromMemoryState.getValue()) == null) {
                String stringDefault = getStringDefault(str);
                if (this.mMobileConfigClient == null) {
                    return stringDefault;
                }
                String stringWithOptions = this.mMobileConfigClient.getStringWithOptions(str, stringDefault, this.mMobileConfigOptions);
                DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_STRING, str, stringWithOptions, getValueSourceString(this.mMobileConfigOptions), getTimeNowInMs() - timeNowInMs);
                updateMemoryStateValueDirectlyFromService(this.mMemoryState.getStringValues(), str, stringWithOptions);
                return stringWithOptions;
            }
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_STRING, str, str2, serviceValueFromMemoryState.getValueToOverride() != null ? OVERRIDDEN_SOURCE : SERVICE_CACHED_SOURCE, getTimeNowInMs() - timeNowInMs);
            return str2;
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_STRING, str, e);
            return getStringDefault(str);
        }
    }

    public void prefetch(String... strArr) {
        internalPrefetch(strArr);
    }

    /* access modifiers changed from: package-private */
    public void internalPrefetch(String[] strArr) {
        try {
            addAsyncPrefetch(strArr);
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logInternalError(this.mContext, e);
        }
    }

    public void debugOnlyReadMemoryCacheFromStorage() {
        this.mMemoryState = ConfigStorageAdapter.createMemoryStateFromStorageCache(this.mContext);
    }

    public void debugOnlyWriteMemoryCacheToStorage() {
        ConfigStorageAdapter.writeMemoryStateToStorageCache(this.mContext, this.mMemoryState);
    }

    public void debugOnlyClearMemoryCache() {
        this.mMemoryState = new ConfigMemoryState();
    }

    /* access modifiers changed from: private */
    @SuppressLint({"BadMethodUse-java.lang.System.currentTimeMillis"})
    public static long getTimeNowInMs() {
        return System.currentTimeMillis();
    }

    public void debugOnlySetAsyncFetchWaitTimeInMs(int i) {
        this.mAsyncFetchWaitTimeInMs = i;
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
        for (Map.Entry<String, ParamsMapEntry> entry : this.mParamsMapEntries.entrySet()) {
            String key = entry.getKey();
            hashMap.put(key, getParamDefault(key));
        }
        return hashMap;
    }

    public Map<String, Object> debugOnlyGetCachedParams() {
        Map<String, Object> debugOnlyGetValues;
        synchronized (this.mMemoryStateLocker) {
            debugOnlyGetValues = this.mMemoryState.debugOnlyGetValues();
        }
        return debugOnlyGetValues;
    }

    public void debugOnlySetBooleanOverriddenValue(String str, Object obj) {
        synchronized (this.mMemoryStateLocker) {
            this.mMemoryState.debugOnlySetBooleanOverriddenValue(str, obj);
        }
    }

    public void debugOnlySetLongOverriddenValue(String str, Object obj) {
        synchronized (this.mMemoryStateLocker) {
            this.mMemoryState.debugOnlySetLongOverriddenValue(str, obj);
        }
    }

    public void debugOnlySetDoubleOverriddenValue(String str, Object obj) {
        synchronized (this.mMemoryStateLocker) {
            this.mMemoryState.debugOnlySetDoubleOverriddenValue(str, obj);
        }
    }

    public void debugOnlySetStringOverriddenValue(String str, Object obj) {
        synchronized (this.mMemoryStateLocker) {
            this.mMemoryState.debugOnlySetStringOverriddenValue(str, obj);
        }
    }

    @Nullable
    public Boolean debugOnlyGetDeviceBoolean(String str) {
        try {
            if (!this.mSubscribeSucceeded.get()) {
                BLog.e(TAG, "debugOnlyGetDeviceBoolean: Calling debugOnlyGetDeviceBoolean(%s) when not subscribed.", str);
                return null;
            } else if (this.mMobileConfigClient != null) {
                return Boolean.valueOf(this.mMobileConfigClient.getBooleanWithOptions(str, getBooleanDefault(str).booleanValue(), this.mMobileConfigOptions));
            } else {
                BLog.e(TAG, "debugOnlyGetDeviceBoolean: Calling debugOnlyGetDeviceBoolean(%s) when mobile config client is not ready", str);
                return null;
            }
        } catch (Exception e) {
            BLog.e(TAG, "debugOnlyGetDeviceBoolean: Calling debugOnlyGetDeviceBoolean(%s) and got exception", str, e);
            return null;
        }
    }

    @Nullable
    public Double debugOnlyGetDeviceDouble(String str) {
        try {
            if (!this.mSubscribeSucceeded.get()) {
                BLog.e(TAG, "debugOnlyGetDeviceDouble: Calling debugOnlyGetDeviceDouble(%s) when not subscribed.", str);
                return null;
            } else if (this.mMobileConfigClient != null) {
                return Double.valueOf(this.mMobileConfigClient.getDoubleWithOptions(str, getDoubleDefault(str).doubleValue(), this.mMobileConfigOptions));
            } else {
                BLog.e(TAG, "debugOnlyGetDeviceDouble: Calling debugOnlyGetDeviceDouble(%s) when mobile config client is not ready", str);
                return null;
            }
        } catch (Exception e) {
            BLog.e(TAG, "debugOnlyGetDeviceDouble: Calling debugOnlyGetDeviceDouble(%s) and got exception", str, e);
            return null;
        }
    }

    @Nullable
    public Long debugOnlyGetDeviceLong(String str) {
        try {
            if (!this.mSubscribeSucceeded.get()) {
                BLog.e(TAG, "debugOnlyGetDeviceLong: Calling debugOnlyGetDeviceLong(%s) when not subscribed.", str);
                return null;
            } else if (this.mMobileConfigClient != null) {
                return Long.valueOf(this.mMobileConfigClient.getLongWithOptions(str, getLongDefault(str).longValue(), this.mMobileConfigOptions));
            } else {
                BLog.e(TAG, "debugOnlyGetDeviceLong: Calling debugOnlyGetDeviceLong(%s) when mobile config client is not ready", str);
                return null;
            }
        } catch (Exception e) {
            BLog.e(TAG, "debugOnlyGetDeviceLong: Calling debugOnlyGetDeviceLong(%s) and got exception", str, e);
            return null;
        }
    }

    @Nullable
    public String debugOnlyGetDeviceString(String str) {
        try {
            if (!this.mSubscribeSucceeded.get()) {
                BLog.e(TAG, "debugOnlyGetDeviceString: Calling debugOnlyGetDeviceString(%s) when not subscribed.", str);
                return null;
            } else if (this.mMobileConfigClient != null) {
                return this.mMobileConfigClient.getStringWithOptions(str, getStringDefault(str), this.mMobileConfigOptions);
            } else {
                BLog.e(TAG, "debugOnlyGetDeviceString: Calling debugOnlyGetDeviceString(%s) when mobile config client is not ready", str);
                return null;
            }
        } catch (Exception e) {
            BLog.e(TAG, "debugOnlyGetDeviceString: Calling debugOnlyGetDeviceString(%s) and got exception", str, e);
            return null;
        }
    }

    public List<DebugOnlyValueInfo> debugOnlyFetchAllParams() {
        int i;
        int i2;
        int i3;
        boolean z;
        Object obj;
        Object obj2;
        Object obj3;
        ArrayList arrayList = new ArrayList();
        try {
            ContentProviderClient acquireUnstableContentProviderClient = this.mContext.getContentResolver().acquireUnstableContentProviderClient(MobileConfigServiceConstants.DEBUG_ONLY_GET_ALL_CONFIGS_URI);
            if (acquireUnstableContentProviderClient == null) {
                try {
                    BLog.e(TAG, "Content provider for the mobileconfig service not found");
                    if (acquireUnstableContentProviderClient != null) {
                        acquireUnstableContentProviderClient.close();
                    }
                    return arrayList;
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            } else {
                Cursor query = acquireUnstableContentProviderClient.query(MobileConfigServiceConstants.DEBUG_ONLY_GET_ALL_CONFIGS_URI, new String[0], "", new String[0], null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            int ordinal = MobileConfigDebugParamSchema.CONFIG_PARAM_NAME.ordinal();
                            int ordinal2 = MobileConfigDebugParamSchema.TYPE.ordinal();
                            int ordinal3 = MobileConfigDebugParamSchema.CACHED_VALUE.ordinal();
                            int ordinal4 = MobileConfigDebugParamSchema.VALUE.ordinal();
                            int ordinal5 = MobileConfigDebugParamSchema.OVERRIDDEN_VALUE.ordinal();
                            int ordinal6 = MobileConfigDebugParamSchema.LOGGING_ID.ordinal();
                            int ordinal7 = MobileConfigDebugParamSchema.IS_SESSIONLESS.ordinal();
                            boolean moveToFirst = query.moveToFirst();
                            while (moveToFirst) {
                                String string = query.getString(ordinal);
                                String string2 = query.getString(ordinal2);
                                String string3 = query.getString(ordinal3);
                                String string4 = query.getString(ordinal4);
                                String string5 = query.getString(ordinal5);
                                String string6 = query.getString(ordinal6);
                                String string7 = query.getString(ordinal7);
                                if (string == null) {
                                    BLog.e(TAG, "Null type for config param name");
                                    i2 = ordinal3;
                                } else {
                                    i2 = ordinal3;
                                    if (string2 == null) {
                                        BLog.e(TAG, "Null type for config param %s", string);
                                    } else {
                                        boolean parseBoolean = Boolean.parseBoolean(string7);
                                        i3 = ordinal4;
                                        try {
                                            int parseInt = Integer.parseInt(string2);
                                            i = ordinal5;
                                            if (parseInt == 1) {
                                                obj2 = debugOnlyParseAsBoolean(string3);
                                                obj = debugOnlyParseAsBoolean(string4);
                                                obj3 = debugOnlyParseAsBoolean(string5);
                                            } else if (parseInt == 2) {
                                                obj2 = debugOnlyParseAsLong(string3);
                                                obj = debugOnlyParseAsLong(string4);
                                                obj3 = debugOnlyParseAsLong(string5);
                                            } else if (parseInt == 3) {
                                                obj2 = debugOnlyParseAsString(string3);
                                                obj = debugOnlyParseAsString(string4);
                                                obj3 = debugOnlyParseAsString(string5);
                                            } else if (parseInt != 4) {
                                                try {
                                                    BLog.e(TAG, "Unrecognized type %s for config param %s", string2, string);
                                                } catch (NumberFormatException e) {
                                                    BLog.e(TAG, "Cannot parse received cached value %s, mobile config value %s, or overridden value %s for config param %s", string3, string4, string5, string, e);
                                                }
                                                z = false;
                                                moveToFirst = query.moveToNext();
                                                ordinal = ordinal;
                                                ordinal2 = ordinal2;
                                                ordinal4 = i3;
                                                ordinal3 = i2;
                                                ordinal5 = i;
                                            } else {
                                                obj2 = debugOnlyParseAsDouble(string3);
                                                obj = debugOnlyParseAsDouble(string4);
                                                obj3 = debugOnlyParseAsDouble(string5);
                                            }
                                            DebugOnlyValueInfo.Builder overriddenValue = DebugOnlyValueInfo.Builder.createDebugOnlyValueInfo().setConfigParamName(string).setDeviceWideCachedValue(obj2).setMobileConfigServiceValue(obj).setOverriddenValue(obj3);
                                            if (string6 == null) {
                                                string6 = "";
                                            }
                                            arrayList.add(overriddenValue.setLoggingId(string6).setIsSessionless(parseBoolean).setValueType(parseInt).build());
                                            z = false;
                                        } catch (NumberFormatException e2) {
                                            i = ordinal5;
                                            z = false;
                                            BLog.e(TAG, "Cannot format unrecognized type %s for config param %s", string2, string, e2);
                                        }
                                        moveToFirst = query.moveToNext();
                                        ordinal = ordinal;
                                        ordinal2 = ordinal2;
                                        ordinal4 = i3;
                                        ordinal3 = i2;
                                        ordinal5 = i;
                                    }
                                }
                                i3 = ordinal4;
                                i = ordinal5;
                                z = false;
                                moveToFirst = query.moveToNext();
                                ordinal = ordinal;
                                ordinal2 = ordinal2;
                                ordinal4 = i3;
                                ordinal3 = i2;
                                ordinal5 = i;
                            }
                            if (query != null) {
                                query.close();
                            }
                            if (acquireUnstableContentProviderClient != null) {
                                acquireUnstableContentProviderClient.close();
                            }
                            return arrayList;
                        }
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                BLog.e(TAG, "no results returned from mobile config content provider");
                if (query != null) {
                    query.close();
                }
                if (acquireUnstableContentProviderClient != null) {
                    acquireUnstableContentProviderClient.close();
                }
                return arrayList;
            }
            throw th;
            throw th;
        } catch (RemoteException | SecurityException e3) {
            BLog.e(TAG, "Could not find mobileconfigservice; is the service running?", e3);
            return arrayList;
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
    private Double debugOnlyParseAsDouble(@Nullable String str) {
        if (str != null && debugOnlyCheckValueStr(str)) {
            return Double.valueOf(Double.parseDouble(str));
        }
        return null;
    }

    @Nullable
    private Long debugOnlyParseAsLong(@Nullable String str) {
        if (str != null && debugOnlyCheckValueStr(str)) {
            return Long.valueOf(Long.parseLong(str));
        }
        return null;
    }

    @Nullable
    private String debugOnlyParseAsString(@Nullable String str) {
        if (debugOnlyCheckValueStr(str)) {
            return str;
        }
        return null;
    }

    private boolean debugOnlyCheckValueStr(@Nullable String str) {
        return str != null && !str.equals(EMPTY_CONFIG_PARAM);
    }

    public void debugOnlySetOverriddenParams(Map<String, DebugOnlyValueInfo> map) {
        try {
            ContentProviderClient acquireUnstableContentProviderClient = this.mContext.getContentResolver().acquireUnstableContentProviderClient(MobileConfigServiceConstants.DEBUG_ONLY_OVERRIDE_CONFIGS_URI);
            if (acquireUnstableContentProviderClient == null) {
                try {
                    BLog.e(TAG, "Content provider for the mobileconfig service not found");
                    if (acquireUnstableContentProviderClient != null) {
                        acquireUnstableContentProviderClient.close();
                        return;
                    }
                    return;
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            } else {
                ArrayList arrayList = new ArrayList();
                for (Map.Entry<String, DebugOnlyValueInfo> entry : map.entrySet()) {
                    String key = entry.getKey();
                    Object overriddenValue = entry.getValue().getOverriddenValue();
                    if (overriddenValue != null) {
                        key = key + ":" + overriddenValue.toString();
                    }
                    arrayList.add(key);
                }
                Cursor query = acquireUnstableContentProviderClient.query(MobileConfigServiceConstants.DEBUG_ONLY_OVERRIDE_CONFIGS_URI, (String[]) arrayList.toArray(new String[0]), "", new String[0], null);
                if (query != null) {
                    try {
                        if (query.moveToFirst()) {
                            int ordinal = MobileConfigOverriddenParamSchema.CONFIG_PARAM_NAME.ordinal();
                            int ordinal2 = MobileConfigOverriddenParamSchema.OVERRIDDEN_VALUE.ordinal();
                            for (boolean moveToFirst = query.moveToFirst(); moveToFirst; moveToFirst = query.moveToNext()) {
                                String string = query.getString(ordinal);
                                String string2 = query.getString(ordinal2);
                                if (EMPTY_CONFIG_PARAM.equals(string2)) {
                                    BLog.d(TAG, "Successfully cleared overridden value for %s.", string);
                                } else {
                                    BLog.d(TAG, "Successfully overridden %s with value %s.", string, string2);
                                }
                            }
                            if (query != null) {
                                query.close();
                            }
                            if (acquireUnstableContentProviderClient != null) {
                                acquireUnstableContentProviderClient.close();
                                return;
                            }
                            return;
                        }
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                BLog.e(TAG, "no results returned from mobile config content provider");
                if (query != null) {
                    query.close();
                }
                if (acquireUnstableContentProviderClient != null) {
                    acquireUnstableContentProviderClient.close();
                    return;
                }
                return;
            }
            throw th;
            throw th;
        } catch (RemoteException | SecurityException e) {
            BLog.e(TAG, "Could not find mobileconfigservice; is the service running?", e);
        }
    }

    private void fillParamsDefaults() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.mContext.getAssets().open(PARAMS_DEFAULT_FILENAME)));
            boolean z = true;
            while (true) {
                try {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        bufferedReader.close();
                        return;
                    } else if (z) {
                        if (!readLine.equals("DeviceConfig,params_default.txt,V1")) {
                            BLog.e(TAG, "Unexpected header '%s' for default param file", readLine);
                            bufferedReader.close();
                            return;
                        }
                        z = false;
                    } else if (!readLine.isEmpty()) {
                        String[] split = readLine.split("=", 2);
                        if (split.length != 2) {
                            BLog.e(TAG, "Incorrect default format for param '%s'", readLine);
                        } else {
                            String str = split[0];
                            String str2 = split[1];
                            ParamsMapEntry paramsMapEntry = this.mParamsMapEntries.get(str);
                            if (paramsMapEntry == null) {
                                BLog.e(TAG, "Can't set default for unknown param '%s'", str);
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
                                        BLog.e(TAG, "Unsupported param type %s", Integer.valueOf(i));
                                    } catch (NumberFormatException unused) {
                                        DeviceConfigTelemetryLogger.logIncorrectDefaultValue(this.mContext, String.format("Cannot convert default value '%s' to type %s", str2, Integer.valueOf(i)), str);
                                    }
                                } else {
                                    this.mParamsDefaults.put(str, Double.valueOf(Double.parseDouble(str2)));
                                }
                            }
                        }
                    }
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            throw th;
        } catch (IOException unused2) {
            DeviceConfigTelemetryLogger.logMissingDefaultFile(this.mContext, "IOException while reading default params file", PARAMS_DEFAULT_FILENAME);
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
        return obj instanceof String ? (String) obj : "";
    }

    @Nullable
    private Object getParamDefault(String str) {
        ParamsMapEntry paramsMapEntry = this.mParamsMapEntries.get(str);
        if (paramsMapEntry == null) {
            BLog.e(TAG, "Cannot get default value for unsupported param name %s", str);
            return null;
        }
        int i = paramsMapEntry.paramType;
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
        BLog.e(TAG, "Unsupported param type %s for param name %s", Integer.valueOf(i), str);
        return null;
    }

    private static String getValueSourceString(MobileConfigOptions mobileConfigOptions) {
        String[] strArr = {"SERVER", "OVERRIDE", "DEFAULT__SERVER_RETURNED_NULL", "DEFAULT__ACCESSED_BEFORE_MC_INIT", "DEFAULT__NO_DATA_ON_DISK", "DEFAULT__ACCESSED_AFTER_MC_DISPOSE", "DEFAULT__MISSING_SERVER_VALUE"};
        int source = mobileConfigOptions.getValueSource().getSource();
        return (source < 0 || source >= strArr.length) ? "UNKNOWN" : strArr[source];
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
        if (valueInfo.getValueToOverride() != null || valueInfo.hasValueSetFromService()) {
            return valueInfo;
        }
        return null;
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
        if (valueInfo.getValueToOverride() != null) {
            return valueInfo;
        }
        sendExposureToService(str, valueInfo);
        return valueInfo;
    }

    private <Type> void updateMemoryStateValueDirectlyFromService(Map<String, ValueInfo<Type>> map, String str, Type type) {
        updateMemoryStateValueFromServiceInternal(map, str, type, CacheUpdateMode.ReturnedValueAndSerialization, null, false);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private <Type> void updateMemoryStateValuePrefetchedFromService(Map<String, ValueInfo<Type>> map, String str, Type type, CacheUpdateMode cacheUpdateMode, @Nullable String str2, boolean z) {
        updateMemoryStateValueFromServiceInternal(map, str, type, cacheUpdateMode, str2, z);
    }

    private <Type> void updateMemoryStateValueFromServiceInternal(Map<String, ValueInfo<Type>> map, String str, Type type, CacheUpdateMode cacheUpdateMode, @Nullable String str2, boolean z) {
        ValueInfo<Type> valueInfo;
        this.mMemoryState.setParamsMapVersion(this.mCurrentParamsMapVersion);
        synchronized (this.mMemoryStateLocker) {
            valueInfo = map.get(str);
            if (valueInfo == null) {
                valueInfo = new ValueInfo<>();
                map.put(str, valueInfo);
            }
        }
        int i = AnonymousClass2.$SwitchMap$com$oculus$deviceconfigclient$DeviceConfigClient$CacheUpdateMode[cacheUpdateMode.ordinal()];
        boolean z2 = false;
        if (i == 1) {
            z2 = valueInfo.setValueFromService(type, true);
        } else if (i == 2) {
            z2 = valueInfo.setValueFromService(type, false);
        } else if (i != 3) {
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
            valueInfo.setExposureLogged();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.deviceconfigclient.DeviceConfigClient$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$deviceconfigclient$DeviceConfigClient$CacheUpdateMode = new int[CacheUpdateMode.values().length];

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|8) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0014 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001f */
        static {
            /*
                com.oculus.deviceconfigclient.DeviceConfigClient$CacheUpdateMode[] r0 = com.oculus.deviceconfigclient.DeviceConfigClient.CacheUpdateMode.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                com.oculus.deviceconfigclient.DeviceConfigClient.AnonymousClass2.$SwitchMap$com$oculus$deviceconfigclient$DeviceConfigClient$CacheUpdateMode = r0
                int[] r0 = com.oculus.deviceconfigclient.DeviceConfigClient.AnonymousClass2.$SwitchMap$com$oculus$deviceconfigclient$DeviceConfigClient$CacheUpdateMode     // Catch:{ NoSuchFieldError -> 0x0014 }
                com.oculus.deviceconfigclient.DeviceConfigClient$CacheUpdateMode r1 = com.oculus.deviceconfigclient.DeviceConfigClient.CacheUpdateMode.ReturnedValueAndSerialization     // Catch:{ NoSuchFieldError -> 0x0014 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0014 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0014 }
            L_0x0014:
                int[] r0 = com.oculus.deviceconfigclient.DeviceConfigClient.AnonymousClass2.$SwitchMap$com$oculus$deviceconfigclient$DeviceConfigClient$CacheUpdateMode     // Catch:{ NoSuchFieldError -> 0x001f }
                com.oculus.deviceconfigclient.DeviceConfigClient$CacheUpdateMode r1 = com.oculus.deviceconfigclient.DeviceConfigClient.CacheUpdateMode.InMemoryAndSerialization     // Catch:{ NoSuchFieldError -> 0x001f }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001f }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001f }
            L_0x001f:
                int[] r0 = com.oculus.deviceconfigclient.DeviceConfigClient.AnonymousClass2.$SwitchMap$com$oculus$deviceconfigclient$DeviceConfigClient$CacheUpdateMode     // Catch:{ NoSuchFieldError -> 0x002a }
                com.oculus.deviceconfigclient.DeviceConfigClient$CacheUpdateMode r1 = com.oculus.deviceconfigclient.DeviceConfigClient.CacheUpdateMode.SerializationOnly     // Catch:{ NoSuchFieldError -> 0x002a }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002a }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002a }
            L_0x002a:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.deviceconfigclient.DeviceConfigClient.AnonymousClass2.<clinit>():void");
        }
    }

    private <Type> void sendExposureToService(String str, ValueInfo<Type> valueInfo) {
        String loggingId = valueInfo.getLoggingId();
        if (!valueInfo.hasExposureLogged() && !TextUtils.isEmpty(loggingId)) {
            addAsyncExposure(str, loggingId, valueInfo.isSessionless());
            valueInfo.setExposureLogged();
        }
    }

    /* access modifiers changed from: package-private */
    public class ChangeListener implements IMobileConfigChangeListener {
        ChangeListener() {
        }

        @Override // com.facebook.mobileconfigservice.client_ifaces.IMobileConfigChangeListener
        public void onConfigChanged(String str) {
            String[] strArr;
            BLog.d(DeviceConfigClient.TAG, "Config '%s' updated.", str);
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

    /* access modifiers changed from: private */
    public static class Signal {
        private final AtomicBoolean mSignaled;

        private Signal() {
            this.mSignaled = new AtomicBoolean(false);
        }

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
    }

    private void addAsyncExposure(String str, String str2, boolean z) {
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

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAsyncPrefetchAllParams() {
        boolean addAll;
        HashSet hashSet = new HashSet();
        for (Map.Entry<String, ParamsMapEntry> entry : this.mParamsMapEntries.entrySet()) {
            hashSet.add(getParamNameForGetMultiple(entry.getKey(), entry.getValue().paramType));
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
    private void addChangeListeners() {
        if (this.mMobileConfigClient == null) {
            BLog.e(TAG, "MobileConfig client is not initialized properly - Can't add change listeners.");
        } else if (this.mChangeListenersAdded) {
            BLog.w(TAG, "ChangeListeners have been already added.");
        } else {
            for (Map.Entry<String, Set<String>> entry : this.mParamNamesByConfig.entrySet()) {
                this.mMobileConfigClient.addChangeListener(entry.getKey(), this.mChangeListener);
            }
            this.mChangeListenersAdded = true;
        }
    }

    private void removeChangeListeners() {
        if (this.mChangeListenersAdded) {
            for (Map.Entry<String, Set<String>> entry : this.mParamNamesByConfig.entrySet()) {
                this.mMobileConfigClient.removeChangeListener(entry.getKey(), this.mChangeListener);
            }
            this.mChangeListenersAdded = false;
        }
    }

    private String getParamNameForGetMultiple(String str) {
        ParamsMapEntry paramsMapEntry = this.mParamsMapEntries.get(str);
        if (paramsMapEntry != null) {
            return getParamNameForGetMultiple(str, paramsMapEntry.paramType);
        }
        DeviceConfigTelemetryLogger.logUnknownParam(this.mContext, "Expected to fail with getMultiple()", str);
        return str;
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
            str2 = "";
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
        asyncFetch.signal();
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
            r5 = r2
            goto L_0x0027
        L_0x0026:
            r5 = r4
        L_0x0027:
            if (r5 == 0) goto L_0x002e
            r5 = 0
            r3.mAsyncFetchInstance = r5     // Catch:{ all -> 0x0030 }
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            return r4
        L_0x002e:
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            return r2
        L_0x0030:
            r4 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x0030 }
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.deviceconfigclient.DeviceConfigClient.clearAsyncFetch(com.oculus.deviceconfigclient.DeviceConfigClient$AsyncFetch, boolean):boolean");
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

    /* access modifiers changed from: package-private */
    public class AsyncFetch {
        private final Signal mSignal = new Signal();
        private final AtomicBoolean mStopThread = new AtomicBoolean(false);
        private final Thread mThread;

        public AsyncFetch() {
            DeviceConfigClient.this.logSynchronizedSet("StartThread - ParamsToPrefetch: ", DeviceConfigClient.this.mParamsToPrefetch);
            DeviceConfigClient.this.logSynchronizedSet("StartThread - LogExposure: ", DeviceConfigClient.this.mSessionLoggingIdsToLogExposure);
            DeviceConfigClient.this.logSynchronizedSet("StartThread - LogExposureSessionless: ", DeviceConfigClient.this.mSessionlessLoggingIdsToLogExposure);
            this.mThread = new AsyncFetchThread(this);
            this.mThread.setName("DeviceConfig-AsyncFetch");
            this.mThread.start();
        }

        public void signal() {
            this.mSignal.setSignal();
        }

        public void waitForSignal() {
            this.mSignal.waitForSignal(DeviceConfigClient.this.mContext, (long) DeviceConfigClient.this.mAsyncFetchWaitTimeInMs);
        }

        public boolean doWork() {
            Set<String> set;
            Set<String> set2;
            Set<String> set3;
            if (!DeviceConfigClient.this.mSubscribeSucceeded.get()) {
                DeviceConfigClient deviceConfigClient = DeviceConfigClient.this;
                deviceConfigClient.logSynchronizedSet("Unsubscribed - ParamsToPrefetch: ", deviceConfigClient.mParamsToPrefetch);
                DeviceConfigClient deviceConfigClient2 = DeviceConfigClient.this;
                deviceConfigClient2.logSynchronizedSet("Unsubscribed - LogExposure: ", deviceConfigClient2.mSessionLoggingIdsToLogExposure);
                DeviceConfigClient deviceConfigClient3 = DeviceConfigClient.this;
                deviceConfigClient3.logSynchronizedSet("Unsubscribed - LogExposureSessionless: ", deviceConfigClient3.mSessionlessLoggingIdsToLogExposure);
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
            Map<String, ValueInfo> multiple = DeviceConfigClient.this.mMobileConfigClient.getMultiple(strArr);
            HashSet hashSet = new HashSet();
            HashSet hashSet2 = new HashSet();
            for (Map.Entry<String, ValueInfo> entry : multiple.entrySet()) {
                String key = entry.getKey();
                ValueInfo value = entry.getValue();
                String queryString = value.getQueryString();
                if (set.contains(queryString)) {
                    hashSet.add(key);
                    hashSet2.add(queryString);
                }
                Object value2 = value.getValue();
                if (value2 == null) {
                    DeviceConfigTelemetryLogger.logUnknownParam(DeviceConfigClient.this.mContext, "ConfigParam fetched a null. Skipping.", key);
                } else {
                    String loggingId = value.getLoggingId();
                    boolean isSessionless = value.getIsSessionless();
                    int type = value.getType();
                    if (type == 1) {
                        CacheUpdateMode queryCacheUpdateMode = queryCacheUpdateMode(key, DeviceConfigClient.this.mMemoryState.getBooleanValues(), value2);
                        DeviceConfigClient deviceConfigClient = DeviceConfigClient.this;
                        deviceConfigClient.updateMemoryStateValuePrefetchedFromService(deviceConfigClient.mMemoryState.getBooleanValues(), key, Boolean.valueOf(((Boolean) value2).booleanValue()), queryCacheUpdateMode, loggingId, isSessionless);
                    } else if (type == 2) {
                        CacheUpdateMode queryCacheUpdateMode2 = queryCacheUpdateMode(key, DeviceConfigClient.this.mMemoryState.getLongValues(), value2);
                        DeviceConfigClient deviceConfigClient2 = DeviceConfigClient.this;
                        deviceConfigClient2.updateMemoryStateValuePrefetchedFromService(deviceConfigClient2.mMemoryState.getLongValues(), key, Long.valueOf(((Long) value2).longValue()), queryCacheUpdateMode2, loggingId, isSessionless);
                    } else if (type == 3) {
                        CacheUpdateMode queryCacheUpdateMode3 = queryCacheUpdateMode(key, DeviceConfigClient.this.mMemoryState.getStringValues(), value2);
                        DeviceConfigClient deviceConfigClient3 = DeviceConfigClient.this;
                        deviceConfigClient3.updateMemoryStateValuePrefetchedFromService(deviceConfigClient3.mMemoryState.getStringValues(), key, (String) value2, queryCacheUpdateMode3, loggingId, isSessionless);
                    } else if (type != 4) {
                        DeviceConfigTelemetryLogger.logUnknownMCType(DeviceConfigClient.this.mContext, value.getType());
                    } else {
                        CacheUpdateMode queryCacheUpdateMode4 = queryCacheUpdateMode(key, DeviceConfigClient.this.mMemoryState.getDoubleValues(), value2);
                        DeviceConfigClient deviceConfigClient4 = DeviceConfigClient.this;
                        deviceConfigClient4.updateMemoryStateValuePrefetchedFromService(deviceConfigClient4.mMemoryState.getDoubleValues(), key, Double.valueOf(((Double) value2).doubleValue()), queryCacheUpdateMode4, loggingId, isSessionless);
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

        private <Type> CacheUpdateMode queryCacheUpdateMode(String str, Map<String, ValueInfo<Type>> map, Object obj) {
            if (DeviceConfigClient.this.mCallback == null) {
                return CacheUpdateMode.SerializationOnly;
            }
            ValueInfo serviceValueFromMemoryState = DeviceConfigClient.this.getServiceValueFromMemoryState(map, str);
            if (serviceValueFromMemoryState == null || !serviceValueFromMemoryState.wasValueReturned()) {
                return CacheUpdateMode.InMemoryAndSerialization;
            }
            if (serviceValueFromMemoryState.getValueToOverride() != null) {
                return CacheUpdateMode.SerializationOnly;
            }
            Object value = serviceValueFromMemoryState.getValue();
            if (value == null) {
                DeviceConfigTelemetryLogger.logInternalParamError(DeviceConfigClient.this.mContext, "Current value is null", str);
                return CacheUpdateMode.SerializationOnly;
            } else if (value.equals(obj)) {
                return CacheUpdateMode.SerializationOnly;
            } else {
                if (DeviceConfigClient.this.mCallback.onParamChanged(str, value, obj) == DeviceConfigCallback.ParamChangedResult.UpdateNow) {
                    return CacheUpdateMode.ReturnedValueAndSerialization;
                }
                return CacheUpdateMode.SerializationOnly;
            }
        }

        private boolean logExposures(Set<String> set, boolean z) {
            if (set.isEmpty()) {
                return false;
            }
            for (String str : set) {
                DeviceConfigClient.this.mMobileConfigClient.logExposure(str, z);
            }
            return true;
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
    }

    private class AsyncFetchThread extends Thread {
        private final AsyncFetch mAsyncFetchInstance;

        public AsyncFetchThread(AsyncFetch asyncFetch) {
            this.mAsyncFetchInstance = asyncFetch;
        }

        public void run() {
            try {
                long timeNowInMs = DeviceConfigClient.getTimeNowInMs();
                while (true) {
                    if (Thread.interrupted() || this.mAsyncFetchInstance.mStopThread.get()) {
                        break;
                    }
                    if (!this.mAsyncFetchInstance.doWork()) {
                        if (DeviceConfigClient.getTimeNowInMs() - timeNowInMs > 60000 && DeviceConfigClient.this.clearAsyncFetch(this.mAsyncFetchInstance, true)) {
                            BLog.d(DeviceConfigClient.TAG, "AsyncFetchThread has been idle for a while. Stopping it.");
                            break;
                        }
                    } else {
                        timeNowInMs = DeviceConfigClient.getTimeNowInMs();
                    }
                    this.mAsyncFetchInstance.waitForSignal();
                }
            } catch (Exception e) {
                DeviceConfigTelemetryLogger.logInternalError(DeviceConfigClient.this.mContext, e);
            }
            DeviceConfigClient.this.clearAsyncFetch(this.mAsyncFetchInstance, false);
        }
    }
}
