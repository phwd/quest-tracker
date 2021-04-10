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
import com.facebook.mobileconfigservice.serviceconstants.MobileConfigOverriddenParamSchema;
import com.facebook.mobileconfigservice.serviceconstants.MobileConfigServiceConstants;
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

    public static class Configuration {
        private String mServicePackage = "com.oculus.horizon";

        public String getMobileConfigServicePackage() {
            return this.mServicePackage;
        }

        public void setMobileConfigServicePackage(String servicePackage) {
            this.mServicePackage = servicePackage;
        }
    }

    public DeviceConfigClient(Context context) {
        this(context, new Configuration());
    }

    public DeviceConfigClient(Context context, Configuration configuration) {
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
        MobileConfigBaseClient baseClient = null;
        try {
            baseClient = new MobileConfigBaseClient(configuration.getMobileConfigServicePackage(), context);
            baseClient.initLogging(new MarauderLogger(context), null);
            String paramsMapContent = baseClient.getParamsMapContent();
            this.mCurrentParamsMapVersion = paramsMapContent.split("\n", 2)[0];
            this.mParamsMapEntries = ParamsMapEntry.parseParamsMapToMap(paramsMapContent);
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logInternalError(this.mContext, e);
            this.mCurrentParamsMapVersion = "##UNKNOWN_VERSION##";
            this.mParamsMapEntries = new HashMap();
        }
        setParamNamesByConfig();
        this.mMobileConfigClient = baseClient;
        this.mMobileConfigOptions = MobileConfigOptions.create().requestForValueSource();
        this.mMemoryState = ConfigStorageAdapter.createMemoryStateFromStorageCache(context);
        fillParamsDefaults();
    }

    private void setParamNamesByConfig() {
        for (Map.Entry<String, ParamsMapEntry> paramEntry : this.mParamsMapEntries.entrySet()) {
            String paramName = paramEntry.getKey();
            String paramConfigName = paramEntry.getValue().configName;
            Set<String> paramNames = this.mParamNamesByConfig.get(paramConfigName);
            if (paramNames == null) {
                paramNames = new HashSet();
                this.mParamNamesByConfig.put(paramConfigName, paramNames);
            }
            paramNames.add(paramName);
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

    public boolean subscribe(final DeviceConfigCallback callback) {
        if (this.mSubscribeSucceeded.get()) {
            return false;
        }
        try {
            this.mCallback = callback;
            final long subscriptionStart = getTimeNowInMs();
            MobileConfigServiceSubscribeCallback mobileConfigCallback = new MobileConfigServiceSubscribeCallback() {
                /* class com.oculus.deviceconfigclient.DeviceConfigClient.AnonymousClass1 */

                @Override // com.facebook.mobileconfigservice.client_ifaces.MobileConfigServiceSubscribeCallback
                public void onMobileConfigSubscribeSuccess() {
                    DeviceConfigTelemetryLogger.logSubscriptionSuccess(DeviceConfigClient.this.mContext, DeviceConfigClient.getTimeNowInMs() - subscriptionStart);
                    DeviceConfigClient.this.mSubscribeSucceeded.set(true);
                    if (callback.enableAutoPrefetch()) {
                        DeviceConfigClient.this.addChangeListeners();
                        DeviceConfigClient.this.addAsyncPrefetchAllParams();
                    }
                    callback.onSuccess();
                }

                @Override // com.facebook.mobileconfigservice.client_ifaces.MobileConfigServiceSubscribeCallback
                public void onMobileConfigSubscribeFailure(String error) {
                    DeviceConfigTelemetryLogger.logSubscriptionFailure(DeviceConfigClient.this.mContext, error);
                    callback.onFailure(error);
                }
            };
            if (this.mMobileConfigClient == null) {
                return true;
            }
            this.mMobileConfigClient.subscribe(mobileConfigCallback);
            return true;
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logSubscriptionFailure(this.mContext, e);
            callback.onFailure("subscribe() threw an exception");
            return false;
        }
    }

    public boolean getBoolean(String paramName) {
        try {
            long startTime = getTimeNowInMs();
            if (!isParamValid(paramName, 1)) {
                return DEFAULT_BOOLEAN.booleanValue();
            }
            ValueInfo<Boolean> valueInfo = getFromMemoryStateAndSendExposureToService(this.mMemoryState.getBooleanValues(), paramName);
            if (valueInfo == null) {
                return getBooleanDefault(paramName).booleanValue();
            }
            Boolean value = valueInfo.getValue();
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.BOOLEAN, paramName, value, valueInfo.getValueToOverride() != null ? OVERRIDDEN_SOURCE : CACHED_SOURCE, getTimeNowInMs() - startTime);
            return (value != null ? value : getBooleanDefault(paramName)).booleanValue();
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.BOOLEAN, paramName, e);
            return getBooleanDefault(paramName).booleanValue();
        }
    }

    public double getDouble(String paramName) {
        try {
            long startTime = getTimeNowInMs();
            if (!isParamValid(paramName, 4)) {
                return DEFAULT_DOUBLE.doubleValue();
            }
            ValueInfo<Double> valueInfo = getFromMemoryStateAndSendExposureToService(this.mMemoryState.getDoubleValues(), paramName);
            if (valueInfo == null) {
                return getDoubleDefault(paramName).doubleValue();
            }
            Double value = valueInfo.getValue();
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DOUBLE, paramName, value, valueInfo.getValueToOverride() != null ? OVERRIDDEN_SOURCE : CACHED_SOURCE, getTimeNowInMs() - startTime);
            return (value != null ? value : getDoubleDefault(paramName)).doubleValue();
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.DOUBLE, paramName, e);
            return getDoubleDefault(paramName).doubleValue();
        }
    }

    public long getLong(String paramName) {
        try {
            long startTime = getTimeNowInMs();
            if (!isParamValid(paramName, 2)) {
                return DEFAULT_LONG.longValue();
            }
            ValueInfo<Long> valueInfo = getFromMemoryStateAndSendExposureToService(this.mMemoryState.getLongValues(), paramName);
            if (valueInfo == null) {
                return getLongDefault(paramName).longValue();
            }
            Long value = valueInfo.getValue();
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.LONG, paramName, value, valueInfo.getValueToOverride() != null ? OVERRIDDEN_SOURCE : CACHED_SOURCE, getTimeNowInMs() - startTime);
            return (value != null ? value : getLongDefault(paramName)).longValue();
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.LONG, paramName, e);
            return getLongDefault(paramName).longValue();
        }
    }

    public String getString(String paramName) {
        try {
            long startTime = getTimeNowInMs();
            if (!isParamValid(paramName, 3)) {
                return "";
            }
            ValueInfo<String> valueInfo = getFromMemoryStateAndSendExposureToService(this.mMemoryState.getStringValues(), paramName);
            if (valueInfo == null) {
                return getStringDefault(paramName);
            }
            String value = valueInfo.getValue();
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.STRING, paramName, value, valueInfo.getValueToOverride() != null ? OVERRIDDEN_SOURCE : CACHED_SOURCE, getTimeNowInMs() - startTime);
            return value != null ? value : getStringDefault(paramName);
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.STRING, paramName, e);
            return getStringDefault(paramName);
        }
    }

    public boolean getDeviceBoolean(String paramName) {
        Boolean serviceCachedValue;
        try {
            if (!this.mSubscribeSucceeded.get()) {
                DeviceConfigTelemetryLogger.logNotSubscribedError(this.mContext, String.format("Calling getDeviceBoolean(%s) when not subscribed. Return value from local cache.", paramName));
                return getBoolean(paramName);
            }
            long startTime = getTimeNowInMs();
            if (!isParamValid(paramName, 1)) {
                return DEFAULT_BOOLEAN.booleanValue();
            }
            ValueInfo<Boolean> valueInfo = getServiceValueFromMemoryState(this.mMemoryState.getBooleanValues(), paramName);
            if (valueInfo == null || (serviceCachedValue = valueInfo.getValue()) == null) {
                boolean defaultValue = getBooleanDefault(paramName).booleanValue();
                if (this.mMobileConfigClient == null) {
                    return defaultValue;
                }
                boolean serviceValue = this.mMobileConfigClient.getBooleanWithOptions(paramName, defaultValue, this.mMobileConfigOptions);
                DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_BOOLEAN, paramName, Boolean.valueOf(serviceValue), getValueSourceString(this.mMobileConfigOptions), getTimeNowInMs() - startTime);
                updateMemoryStateValueDirectlyFromService(this.mMemoryState.getBooleanValues(), paramName, Boolean.valueOf(serviceValue));
                return serviceValue;
            }
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_BOOLEAN, paramName, serviceCachedValue, valueInfo.getValueToOverride() != null ? OVERRIDDEN_SOURCE : SERVICE_CACHED_SOURCE, getTimeNowInMs() - startTime);
            return serviceCachedValue.booleanValue();
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_BOOLEAN, paramName, e);
            return getBooleanDefault(paramName).booleanValue();
        }
    }

    public double getDeviceDouble(String paramName) {
        Double serviceCachedValue;
        try {
            if (!this.mSubscribeSucceeded.get()) {
                DeviceConfigTelemetryLogger.logNotSubscribedError(this.mContext, String.format("Calling getDeviceDouble(%s) when not subscribed. Return value from local cache.", paramName));
                return getDouble(paramName);
            }
            long startTime = getTimeNowInMs();
            if (!isParamValid(paramName, 4)) {
                return DEFAULT_DOUBLE.doubleValue();
            }
            ValueInfo<Double> valueInfo = getServiceValueFromMemoryState(this.mMemoryState.getDoubleValues(), paramName);
            if (valueInfo == null || (serviceCachedValue = valueInfo.getValue()) == null) {
                double defaultValue = getDoubleDefault(paramName).doubleValue();
                if (this.mMobileConfigClient == null) {
                    return defaultValue;
                }
                double serviceValue = this.mMobileConfigClient.getDoubleWithOptions(paramName, defaultValue, this.mMobileConfigOptions);
                DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_DOUBLE, paramName, Double.valueOf(serviceValue), getValueSourceString(this.mMobileConfigOptions), getTimeNowInMs() - startTime);
                updateMemoryStateValueDirectlyFromService(this.mMemoryState.getDoubleValues(), paramName, Double.valueOf(serviceValue));
                return serviceValue;
            }
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_DOUBLE, paramName, serviceCachedValue, valueInfo.getValueToOverride() != null ? OVERRIDDEN_SOURCE : SERVICE_CACHED_SOURCE, getTimeNowInMs() - startTime);
            return serviceCachedValue.doubleValue();
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_DOUBLE, paramName, e);
            return getDoubleDefault(paramName).doubleValue();
        }
    }

    public long getDeviceLong(String paramName) {
        Long serviceCachedValue;
        try {
            if (!this.mSubscribeSucceeded.get()) {
                DeviceConfigTelemetryLogger.logNotSubscribedError(this.mContext, String.format("Calling getDeviceLong(%s) when not subscribed. Return value from local cache.", paramName));
                return getLong(paramName);
            }
            long startTime = getTimeNowInMs();
            if (!isParamValid(paramName, 2)) {
                return DEFAULT_LONG.longValue();
            }
            ValueInfo<Long> valueInfo = getServiceValueFromMemoryState(this.mMemoryState.getLongValues(), paramName);
            if (valueInfo == null || (serviceCachedValue = valueInfo.getValue()) == null) {
                long defaultValue = getLongDefault(paramName).longValue();
                if (this.mMobileConfigClient == null) {
                    return defaultValue;
                }
                long serviceValue = this.mMobileConfigClient.getLongWithOptions(paramName, defaultValue, this.mMobileConfigOptions);
                DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_LONG, paramName, Long.valueOf(serviceValue), getValueSourceString(this.mMobileConfigOptions), getTimeNowInMs() - startTime);
                updateMemoryStateValueDirectlyFromService(this.mMemoryState.getLongValues(), paramName, Long.valueOf(serviceValue));
                return serviceValue;
            }
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_LONG, paramName, serviceCachedValue, valueInfo.getValueToOverride() != null ? OVERRIDDEN_SOURCE : SERVICE_CACHED_SOURCE, getTimeNowInMs() - startTime);
            return serviceCachedValue.longValue();
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_LONG, paramName, e);
            return getLongDefault(paramName).longValue();
        }
    }

    public String getDeviceString(String paramName) {
        String serviceCachedValue;
        try {
            if (!this.mSubscribeSucceeded.get()) {
                DeviceConfigTelemetryLogger.logNotSubscribedError(this.mContext, String.format("Calling getDeviceString(%s) when not subscribed. Return value from local cache.", paramName));
                return getString(paramName);
            }
            long startTime = getTimeNowInMs();
            if (!isParamValid(paramName, 3)) {
                return "";
            }
            ValueInfo<String> valueInfo = getServiceValueFromMemoryState(this.mMemoryState.getStringValues(), paramName);
            if (valueInfo == null || (serviceCachedValue = valueInfo.getValue()) == null) {
                String defaultValue = getStringDefault(paramName);
                if (this.mMobileConfigClient == null) {
                    return defaultValue;
                }
                String serviceValue = this.mMobileConfigClient.getStringWithOptions(paramName, defaultValue, this.mMobileConfigOptions);
                DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_STRING, paramName, serviceValue, getValueSourceString(this.mMobileConfigOptions), getTimeNowInMs() - startTime);
                updateMemoryStateValueDirectlyFromService(this.mMemoryState.getStringValues(), paramName, serviceValue);
                return serviceValue;
            }
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_STRING, paramName, serviceCachedValue, valueInfo.getValueToOverride() != null ? OVERRIDDEN_SOURCE : SERVICE_CACHED_SOURCE, getTimeNowInMs() - startTime);
            return serviceCachedValue;
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_STRING, paramName, e);
            return getStringDefault(paramName);
        }
    }

    public void prefetch(String... paramNames) {
        internalPrefetch(paramNames);
    }

    /* access modifiers changed from: package-private */
    public void internalPrefetch(String[] paramNames) {
        try {
            addAsyncPrefetch(paramNames);
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

    public void debugOnlySetAsyncFetchWaitTimeInMs(int waitTimeInMs) {
        this.mAsyncFetchWaitTimeInMs = waitTimeInMs;
    }

    public String debugOnlyGetMemorySnapshot(String debugText) {
        String debugOnlyGetMemorySnapshot;
        synchronized (this.mMemoryStateLocker) {
            debugOnlyGetMemorySnapshot = this.mMemoryState.debugOnlyGetMemorySnapshot(debugText);
        }
        return debugOnlyGetMemorySnapshot;
    }

    @Deprecated
    public void debugOnlyEnableSetOnFetch(boolean enableSetOnFetch) {
    }

    public Map<String, Object> debugOnlyGetParamsDefaults() {
        Map<String, Object> paramsDefaultsMap = new HashMap<>();
        for (Map.Entry<String, ParamsMapEntry> paramEntry : this.mParamsMapEntries.entrySet()) {
            String paramName = paramEntry.getKey();
            paramsDefaultsMap.put(paramName, getParamDefault(paramName));
        }
        return paramsDefaultsMap;
    }

    public Map<String, Object> debugOnlyGetCachedParams() {
        Map<String, Object> debugOnlyGetValues;
        synchronized (this.mMemoryStateLocker) {
            debugOnlyGetValues = this.mMemoryState.debugOnlyGetValues();
        }
        return debugOnlyGetValues;
    }

    public void debugOnlySetBooleanOverriddenValue(String paramName, Object overriddenValue) {
        synchronized (this.mMemoryStateLocker) {
            this.mMemoryState.debugOnlySetBooleanOverriddenValue(paramName, overriddenValue);
        }
    }

    public void debugOnlySetLongOverriddenValue(String paramName, Object overriddenValue) {
        synchronized (this.mMemoryStateLocker) {
            this.mMemoryState.debugOnlySetLongOverriddenValue(paramName, overriddenValue);
        }
    }

    public void debugOnlySetDoubleOverriddenValue(String paramName, Object overriddenValue) {
        synchronized (this.mMemoryStateLocker) {
            this.mMemoryState.debugOnlySetDoubleOverriddenValue(paramName, overriddenValue);
        }
    }

    public void debugOnlySetStringOverriddenValue(String paramName, Object overriddenValue) {
        synchronized (this.mMemoryStateLocker) {
            this.mMemoryState.debugOnlySetStringOverriddenValue(paramName, overriddenValue);
        }
    }

    @Nullable
    public Boolean debugOnlyGetDeviceBoolean(String paramName) {
        try {
            if (!this.mSubscribeSucceeded.get()) {
                BLog.e(TAG, "debugOnlyGetDeviceBoolean: Calling debugOnlyGetDeviceBoolean(%s) when not subscribed.", new Object[]{paramName});
                return null;
            } else if (this.mMobileConfigClient != null) {
                return Boolean.valueOf(this.mMobileConfigClient.getBooleanWithOptions(paramName, getBooleanDefault(paramName).booleanValue(), this.mMobileConfigOptions));
            } else {
                BLog.e(TAG, "debugOnlyGetDeviceBoolean: Calling debugOnlyGetDeviceBoolean(%s) when mobile config client is not ready", new Object[]{paramName});
                return null;
            }
        } catch (Exception e) {
            BLog.e(TAG, "debugOnlyGetDeviceBoolean: Calling debugOnlyGetDeviceBoolean(%s) and got exception", new Object[]{paramName, e});
            return null;
        }
    }

    @Nullable
    public Double debugOnlyGetDeviceDouble(String paramName) {
        try {
            if (!this.mSubscribeSucceeded.get()) {
                BLog.e(TAG, "debugOnlyGetDeviceDouble: Calling debugOnlyGetDeviceDouble(%s) when not subscribed.", new Object[]{paramName});
                return null;
            } else if (this.mMobileConfigClient != null) {
                return Double.valueOf(this.mMobileConfigClient.getDoubleWithOptions(paramName, getDoubleDefault(paramName).doubleValue(), this.mMobileConfigOptions));
            } else {
                BLog.e(TAG, "debugOnlyGetDeviceDouble: Calling debugOnlyGetDeviceDouble(%s) when mobile config client is not ready", new Object[]{paramName});
                return null;
            }
        } catch (Exception e) {
            BLog.e(TAG, "debugOnlyGetDeviceDouble: Calling debugOnlyGetDeviceDouble(%s) and got exception", new Object[]{paramName, e});
            return null;
        }
    }

    @Nullable
    public Long debugOnlyGetDeviceLong(String paramName) {
        try {
            if (!this.mSubscribeSucceeded.get()) {
                BLog.e(TAG, "debugOnlyGetDeviceLong: Calling debugOnlyGetDeviceLong(%s) when not subscribed.", new Object[]{paramName});
                return null;
            } else if (this.mMobileConfigClient != null) {
                return Long.valueOf(this.mMobileConfigClient.getLongWithOptions(paramName, getLongDefault(paramName).longValue(), this.mMobileConfigOptions));
            } else {
                BLog.e(TAG, "debugOnlyGetDeviceLong: Calling debugOnlyGetDeviceLong(%s) when mobile config client is not ready", new Object[]{paramName});
                return null;
            }
        } catch (Exception e) {
            BLog.e(TAG, "debugOnlyGetDeviceLong: Calling debugOnlyGetDeviceLong(%s) and got exception", new Object[]{paramName, e});
            return null;
        }
    }

    @Nullable
    public String debugOnlyGetDeviceString(String paramName) {
        try {
            if (!this.mSubscribeSucceeded.get()) {
                BLog.e(TAG, "debugOnlyGetDeviceString: Calling debugOnlyGetDeviceString(%s) when not subscribed.", new Object[]{paramName});
                return null;
            } else if (this.mMobileConfigClient != null) {
                return this.mMobileConfigClient.getStringWithOptions(paramName, getStringDefault(paramName), this.mMobileConfigOptions);
            } else {
                BLog.e(TAG, "debugOnlyGetDeviceString: Calling debugOnlyGetDeviceString(%s) when mobile config client is not ready", new Object[]{paramName});
                return null;
            }
        } catch (Exception e) {
            BLog.e(TAG, "debugOnlyGetDeviceString: Calling debugOnlyGetDeviceString(%s) and got exception", new Object[]{paramName, e});
            return null;
        }
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:43:0x011f */
    /* JADX INFO: Multiple debug info for r7v6 java.lang.String: [D('cachedValueIndex' int), D('overriddenValueStr' java.lang.String)] */
    /* JADX INFO: Multiple debug info for r7v12 'cachedValueIndex'  ??: [D('cachedValueIndex' int), D('overriddenValueStr' java.lang.String)] */
    /* JADX WARN: Type inference failed for: r7v2, types: [int] */
    /* JADX WARN: Type inference failed for: r7v3 */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r11v11, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r7v12, types: [java.lang.String] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.oculus.deviceconfigclient.DebugOnlyValueInfo> debugOnlyFetchAllParams() {
        /*
        // Method dump skipped, instructions count: 655
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.deviceconfigclient.DeviceConfigClient.debugOnlyFetchAllParams():java.util.List");
    }

    @Nullable
    private Boolean debugOnlyParseAsBoolean(@Nullable String valueStr) {
        if (debugOnlyCheckValueStr(valueStr)) {
            return Boolean.valueOf(Boolean.parseBoolean(valueStr));
        }
        return null;
    }

    @Nullable
    private Double debugOnlyParseAsDouble(@Nullable String valueStr) {
        if (valueStr != null && debugOnlyCheckValueStr(valueStr)) {
            return Double.valueOf(Double.parseDouble(valueStr));
        }
        return null;
    }

    @Nullable
    private Long debugOnlyParseAsLong(@Nullable String valueStr) {
        if (valueStr != null && debugOnlyCheckValueStr(valueStr)) {
            return Long.valueOf(Long.parseLong(valueStr));
        }
        return null;
    }

    @Nullable
    private String debugOnlyParseAsString(@Nullable String valueStr) {
        if (debugOnlyCheckValueStr(valueStr)) {
            return valueStr;
        }
        return null;
    }

    private boolean debugOnlyCheckValueStr(@Nullable String valueStr) {
        return valueStr != null && !valueStr.equals(EMPTY_CONFIG_PARAM);
    }

    public void debugOnlySetOverriddenParams(Map<String, DebugOnlyValueInfo> overriddenParams) {
        try {
            ContentProviderClient client = this.mContext.getContentResolver().acquireUnstableContentProviderClient(MobileConfigServiceConstants.DEBUG_ONLY_OVERRIDE_CONFIGS_URI);
            if (client == null) {
                try {
                    BLog.e(TAG, "Content provider for the mobileconfig service not found");
                    if (client != null) {
                        client.close();
                        return;
                    }
                    return;
                } catch (Throwable th) {
                }
            } else {
                List<String> overriddenParamsQuery = new ArrayList<>();
                for (Map.Entry<String, DebugOnlyValueInfo> overriddenParam : overriddenParams.entrySet()) {
                    String overriddenParamQuery = overriddenParam.getKey();
                    Object valueToOverride = overriddenParam.getValue().getOverriddenValue();
                    if (valueToOverride != null) {
                        overriddenParamQuery = overriddenParamQuery + ":" + valueToOverride.toString();
                    }
                    overriddenParamsQuery.add(overriddenParamQuery);
                }
                Cursor cursor = client.query(MobileConfigServiceConstants.DEBUG_ONLY_OVERRIDE_CONFIGS_URI, (String[]) overriddenParamsQuery.toArray(new String[0]), "", new String[0], null);
                if (cursor != null) {
                    try {
                        if (cursor.moveToFirst()) {
                            int configParamNameIndex = MobileConfigOverriddenParamSchema.CONFIG_PARAM_NAME.ordinal();
                            int overriddenValueIndex = MobileConfigOverriddenParamSchema.OVERRIDDEN_VALUE.ordinal();
                            for (boolean hasItem = cursor.moveToFirst(); hasItem; hasItem = cursor.moveToNext()) {
                                String configParamName = cursor.getString(configParamNameIndex);
                                String overriddenValueStr = cursor.getString(overriddenValueIndex);
                                if (EMPTY_CONFIG_PARAM.equals(overriddenValueStr)) {
                                    BLog.d(TAG, "Successfully cleared overridden value for %s.", configParamName);
                                } else {
                                    BLog.d(TAG, "Successfully overridden %s with value %s.", configParamName, overriddenValueStr);
                                }
                            }
                            cursor.close();
                            client.close();
                            return;
                        }
                    } catch (Throwable th2) {
                    }
                }
                BLog.e(TAG, "no results returned from mobile config content provider");
                if (cursor != null) {
                    cursor.close();
                }
                client.close();
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
            boolean checkHeader = true;
            while (true) {
                try {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        bufferedReader.close();
                        return;
                    } else if (checkHeader) {
                        if (!line.equals("DeviceConfig,params_default.txt,V1")) {
                            BLog.e(TAG, "Unexpected header '%s' for default param file", new Object[]{line});
                            bufferedReader.close();
                            return;
                        }
                        checkHeader = false;
                    } else if (!line.isEmpty()) {
                        String[] splitLine = line.split("=", 2);
                        if (splitLine.length != 2) {
                            BLog.e(TAG, "Incorrect default format for param '%s'", new Object[]{line});
                        } else {
                            String configParamName = splitLine[0];
                            String defaultValueAsString = splitLine[1];
                            ParamsMapEntry entry = this.mParamsMapEntries.get(configParamName);
                            if (entry == null) {
                                BLog.e(TAG, "Can't set default for unknown param '%s'", new Object[]{configParamName});
                            } else {
                                int paramType = entry.paramType;
                                if (paramType == 1) {
                                    this.mParamsDefaults.put(configParamName, Boolean.valueOf(Boolean.parseBoolean(defaultValueAsString)));
                                } else if (paramType == 2) {
                                    this.mParamsDefaults.put(configParamName, Long.valueOf(Long.parseLong(defaultValueAsString)));
                                } else if (paramType == 3) {
                                    this.mParamsDefaults.put(configParamName, defaultValueAsString);
                                } else if (paramType != 4) {
                                    try {
                                        BLog.e(TAG, "Unsupported param type %s", new Object[]{Integer.valueOf(paramType)});
                                    } catch (NumberFormatException e) {
                                        DeviceConfigTelemetryLogger.logIncorrectDefaultValue(this.mContext, String.format("Cannot convert default value '%s' to type %s", defaultValueAsString, Integer.valueOf(paramType)), configParamName);
                                    }
                                } else {
                                    this.mParamsDefaults.put(configParamName, Double.valueOf(Double.parseDouble(defaultValueAsString)));
                                }
                            }
                        }
                    }
                } catch (Throwable th) {
                }
            }
            throw th;
        } catch (IOException e2) {
            DeviceConfigTelemetryLogger.logMissingDefaultFile(this.mContext, "IOException while reading default params file", PARAMS_DEFAULT_FILENAME);
        }
    }

    private boolean isParamValid(String paramName, int expectedParamType) {
        String errorMessage;
        ParamsMapEntry entry = this.mParamsMapEntries.get(paramName);
        if (entry == null) {
            DeviceConfigTelemetryLogger.logUnknownParam(this.mContext, "Can't find param", paramName);
            return false;
        } else if (entry.paramType == expectedParamType) {
            return true;
        } else {
            if (expectedParamType == 1) {
                errorMessage = "Param is not a boolean";
            } else if (expectedParamType == 2) {
                errorMessage = "Param is not a long";
            } else if (expectedParamType == 3) {
                errorMessage = "Param is not a string";
            } else if (expectedParamType != 4) {
                errorMessage = "Param has an unknown type";
            } else {
                errorMessage = "Param is not a double";
            }
            DeviceConfigTelemetryLogger.logIncorrectTypeParam(this.mContext, errorMessage, paramName);
            return false;
        }
    }

    private Boolean getBooleanDefault(String paramName) {
        Object defaultValue = this.mParamsDefaults.get(paramName);
        if (defaultValue instanceof Boolean) {
            return (Boolean) defaultValue;
        }
        return DEFAULT_BOOLEAN;
    }

    private Double getDoubleDefault(String paramName) {
        Object defaultValue = this.mParamsDefaults.get(paramName);
        if (defaultValue instanceof Double) {
            return (Double) defaultValue;
        }
        return DEFAULT_DOUBLE;
    }

    private Long getLongDefault(String paramName) {
        Object defaultValue = this.mParamsDefaults.get(paramName);
        if (defaultValue instanceof Long) {
            return (Long) defaultValue;
        }
        return DEFAULT_LONG;
    }

    private String getStringDefault(String paramName) {
        Object defaultValue = this.mParamsDefaults.get(paramName);
        if (defaultValue instanceof String) {
            return (String) defaultValue;
        }
        return "";
    }

    @Nullable
    private Object getParamDefault(String paramName) {
        ParamsMapEntry entry = this.mParamsMapEntries.get(paramName);
        if (entry == null) {
            BLog.e(TAG, "Cannot get default value for unsupported param name %s", new Object[]{paramName});
            return null;
        }
        int paramType = entry.paramType;
        if (paramType == 1) {
            return getBooleanDefault(paramName);
        }
        if (paramType == 2) {
            return getLongDefault(paramName);
        }
        if (paramType == 3) {
            return getStringDefault(paramName);
        }
        if (paramType == 4) {
            return getDoubleDefault(paramName);
        }
        BLog.e(TAG, "Unsupported param type %s for param name %s", new Object[]{Integer.valueOf(paramType), paramName});
        return null;
    }

    private static String getValueSourceString(MobileConfigOptions op) {
        String[] valueSourceStrings = {"SERVER", "OVERRIDE", "DEFAULT__SERVER_RETURNED_NULL", "DEFAULT__ACCESSED_BEFORE_MC_INIT", "DEFAULT__NO_DATA_ON_DISK", "DEFAULT__ACCESSED_AFTER_MC_DISPOSE", "DEFAULT__MISSING_SERVER_VALUE"};
        int source = op.getValueSource().getSource();
        if (source < 0 || source >= valueSourceStrings.length) {
            return "UNKNOWN";
        }
        return valueSourceStrings[source];
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @Nullable
    private <Type> ValueInfo<Type> getServiceValueFromMemoryState(Map<String, ValueInfo<Type>> values, String paramName) {
        ValueInfo<Type> memoryStateValue;
        synchronized (this.mMemoryStateLocker) {
            memoryStateValue = values.get(paramName);
        }
        if (memoryStateValue == null) {
            return null;
        }
        if (memoryStateValue.getValueToOverride() != null || memoryStateValue.hasValueSetFromService()) {
            return memoryStateValue;
        }
        return null;
    }

    @Nullable
    private <Type> ValueInfo<Type> getFromMemoryStateAndSendExposureToService(Map<String, ValueInfo<Type>> values, String paramName) {
        ValueInfo<Type> cachedValue;
        synchronized (this.mMemoryStateLocker) {
            cachedValue = values.get(paramName);
        }
        if (cachedValue == null) {
            return null;
        }
        if (cachedValue.getValueToOverride() != null) {
            return cachedValue;
        }
        sendExposureToService(paramName, cachedValue);
        return cachedValue;
    }

    private <Type> void updateMemoryStateValueDirectlyFromService(Map<String, ValueInfo<Type>> valuesToUpdate, String paramName, Type value) {
        updateMemoryStateValueFromServiceInternal(valuesToUpdate, paramName, value, CacheUpdateMode.ReturnedValueAndSerialization, null, false);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private <Type> void updateMemoryStateValuePrefetchedFromService(Map<String, ValueInfo<Type>> valuesToUpdate, String paramName, Type value, CacheUpdateMode cacheUpdateMode, @Nullable String loggingId, boolean sessionless) {
        updateMemoryStateValueFromServiceInternal(valuesToUpdate, paramName, value, cacheUpdateMode, loggingId, sessionless);
    }

    private <Type> void updateMemoryStateValueFromServiceInternal(Map<String, ValueInfo<Type>> valuesToUpdate, String paramName, Type value, CacheUpdateMode cacheUpdateMode, @Nullable String loggingId, boolean sessionless) {
        ValueInfo<Type> updatedValue;
        boolean serializationNeeded;
        this.mMemoryState.setParamsMapVersion(this.mCurrentParamsMapVersion);
        synchronized (this.mMemoryStateLocker) {
            updatedValue = valuesToUpdate.get(paramName);
            if (updatedValue == null) {
                updatedValue = new ValueInfo<>();
                valuesToUpdate.put(paramName, updatedValue);
            }
        }
        int i = AnonymousClass2.$SwitchMap$com$oculus$deviceconfigclient$DeviceConfigClient$CacheUpdateMode[cacheUpdateMode.ordinal()];
        if (i == 1) {
            serializationNeeded = updatedValue.setValueFromService(value, true);
        } else if (i == 2) {
            serializationNeeded = updatedValue.setValueFromService(value, false);
        } else if (i != 3) {
            DeviceConfigTelemetryLogger.logInternalError(this.mContext, "Incorrect CacheUpdateMode");
            serializationNeeded = false;
        } else {
            serializationNeeded = updatedValue.setValueForSerialization(value);
        }
        if (serializationNeeded) {
            ConfigStorageAdapter.writeMemoryStateToStorageCache(this.mContext, this.mMemoryState);
        }
        if (loggingId != null) {
            updatedValue.setLoggingId(loggingId, sessionless);
        } else {
            updatedValue.setExposureLogged();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.oculus.deviceconfigclient.DeviceConfigClient$2  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$oculus$deviceconfigclient$DeviceConfigClient$CacheUpdateMode = new int[CacheUpdateMode.values().length];

        static {
            try {
                $SwitchMap$com$oculus$deviceconfigclient$DeviceConfigClient$CacheUpdateMode[CacheUpdateMode.ReturnedValueAndSerialization.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$oculus$deviceconfigclient$DeviceConfigClient$CacheUpdateMode[CacheUpdateMode.InMemoryAndSerialization.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$oculus$deviceconfigclient$DeviceConfigClient$CacheUpdateMode[CacheUpdateMode.SerializationOnly.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    private <Type> void sendExposureToService(String paramName, ValueInfo<Type> valueInfo) {
        String loggingId = valueInfo.getLoggingId();
        if (!valueInfo.hasExposureLogged() && !TextUtils.isEmpty(loggingId)) {
            addAsyncExposure(paramName, loggingId, valueInfo.isSessionless());
            valueInfo.setExposureLogged();
        }
    }

    /* access modifiers changed from: package-private */
    public class ChangeListener implements IMobileConfigChangeListener {
        ChangeListener() {
        }

        @Override // com.facebook.mobileconfigservice.client_ifaces.IMobileConfigChangeListener
        public void onConfigChanged(String configName) {
            BLog.d(DeviceConfigClient.TAG, "Config '%s' updated.", configName);
            String[] paramNamesAsArray = null;
            Set<String> paramNamesAsSet = (Set) DeviceConfigClient.this.mParamNamesByConfig.get(configName);
            if (paramNamesAsSet != null) {
                paramNamesAsArray = new String[paramNamesAsSet.size()];
                int i = 0;
                for (String paramName : paramNamesAsSet) {
                    paramNamesAsArray[i] = paramName;
                    i++;
                }
            } else {
                DeviceConfigTelemetryLogger.logInternalParamError(DeviceConfigClient.this.mContext, "Config could not be found in params_map.txt", configName);
            }
            if (paramNamesAsArray != null) {
                DeviceConfigClient.this.internalPrefetch(paramNamesAsArray);
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

        public void waitForSignal(Context context, long timeoutInMS) {
            if (!this.mSignaled.get()) {
                try {
                    synchronized (this) {
                        wait(timeoutInMS);
                    }
                } catch (InterruptedException e) {
                    DeviceConfigTelemetryLogger.logInternalError(context, "Signal wait() got interrupted");
                    Thread.currentThread().interrupt();
                }
            }
            this.mSignaled.set(false);
        }
    }

    private void addAsyncExposure(String paramName, String loggingId, boolean sessionless) {
        boolean added;
        Set<String> loggingIdsToLogExposure = sessionless ? this.mSessionlessLoggingIdsToLogExposure : this.mSessionLoggingIdsToLogExposure;
        synchronized (this.mParamsLock) {
            added = loggingIdsToLogExposure.add(paramName);
        }
        if (added) {
            signalAsyncFetch();
        }
    }

    private void addAsyncPrefetch(String[] paramNames) {
        boolean added;
        Set<String> allParamNamesForGetMultiple = new HashSet<>();
        for (String paramName : paramNames) {
            ParamsMapEntry entry = this.mParamsMapEntries.get(paramName);
            if (entry != null) {
                allParamNamesForGetMultiple.add(getParamNameForGetMultiple(paramName, entry.paramType));
            } else {
                DeviceConfigTelemetryLogger.logUnknownParam(this.mContext, "Expected to fail with getMultiple()", paramName);
                allParamNamesForGetMultiple.add(paramName);
            }
        }
        synchronized (this.mParamsLock) {
            added = this.mParamsToPrefetch.addAll(allParamNamesForGetMultiple);
        }
        if (added) {
            signalAsyncFetch();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addAsyncPrefetchAllParams() {
        boolean added;
        Set<String> allParamNamesForGetMultiple = new HashSet<>();
        for (Map.Entry<String, ParamsMapEntry> paramEntry : this.mParamsMapEntries.entrySet()) {
            allParamNamesForGetMultiple.add(getParamNameForGetMultiple(paramEntry.getKey(), paramEntry.getValue().paramType));
        }
        synchronized (this.mParamsLock) {
            added = this.mParamsToPrefetch.addAll(allParamNamesForGetMultiple);
        }
        if (added) {
            signalAsyncFetch();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void logSynchronizedSet(String prefix, Set<String> set) {
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void addChangeListeners() {
        if (this.mMobileConfigClient == null) {
            BLog.e(TAG, "MobileConfig client is not initialized properly - Can't add change listeners.");
        } else if (this.mChangeListenersAdded) {
            BLog.w(TAG, "ChangeListeners have been already added.");
        } else {
            for (Map.Entry<String, Set<String>> configEntry : this.mParamNamesByConfig.entrySet()) {
                this.mMobileConfigClient.addChangeListener(configEntry.getKey(), this.mChangeListener);
            }
            this.mChangeListenersAdded = true;
        }
    }

    private void removeChangeListeners() {
        if (this.mChangeListenersAdded) {
            for (Map.Entry<String, Set<String>> configEntry : this.mParamNamesByConfig.entrySet()) {
                this.mMobileConfigClient.removeChangeListener(configEntry.getKey(), this.mChangeListener);
            }
            this.mChangeListenersAdded = false;
        }
    }

    private String getParamNameForGetMultiple(String paramName) {
        ParamsMapEntry entry = this.mParamsMapEntries.get(paramName);
        if (entry != null) {
            return getParamNameForGetMultiple(paramName, entry.paramType);
        }
        DeviceConfigTelemetryLogger.logUnknownParam(this.mContext, "Expected to fail with getMultiple()", paramName);
        return paramName;
    }

    private String getParamNameForGetMultiple(String paramName, int paramType) {
        String defaultValue;
        if (paramType == 1) {
            defaultValue = Boolean.toString(getBooleanDefault(paramName).booleanValue());
        } else if (paramType == 2) {
            defaultValue = Long.toString(getLongDefault(paramName).longValue());
        } else if (paramType == 3) {
            defaultValue = getStringDefault(paramName);
        } else if (paramType != 4) {
            DeviceConfigTelemetryLogger.logUnknownMCType(this.mContext, paramType);
            defaultValue = "";
        } else {
            defaultValue = Double.toString(getDoubleDefault(paramName).doubleValue());
        }
        return paramName + ":" + defaultValue;
    }

    private void signalAsyncFetch() {
        AsyncFetch asyncFetchInstance;
        synchronized (this.mParamsLock) {
            if (this.mAsyncFetchInstance == null) {
                this.mAsyncFetchInstance = new AsyncFetch();
            }
            asyncFetchInstance = this.mAsyncFetchInstance;
        }
        asyncFetchInstance.signal();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean clearAsyncFetch(AsyncFetch asyncFetchInstance, boolean checkNoWorkLeft) {
        boolean clearInstance;
        synchronized (this.mParamsLock) {
            if (this.mAsyncFetchInstance == asyncFetchInstance) {
                if (checkNoWorkLeft) {
                    clearInstance = this.mSessionLoggingIdsToLogExposure.isEmpty() && this.mSessionlessLoggingIdsToLogExposure.isEmpty() && this.mParamsToPrefetch.isEmpty();
                } else {
                    clearInstance = true;
                }
                if (clearInstance) {
                    this.mAsyncFetchInstance = null;
                    return true;
                }
            }
            return false;
        }
    }

    private void asyncFetchShutdown() {
        AsyncFetch asyncFetchInstance;
        synchronized (this.mParamsLock) {
            asyncFetchInstance = this.mAsyncFetchInstance;
        }
        if (asyncFetchInstance != null) {
            asyncFetchInstance.shutdown();
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
            Set<String> sessionLoggingIdsToLogExposure;
            Set<String> sessionlessLoggingIdsToLogExposure;
            Set<String> paramsToPrefetch;
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
                sessionLoggingIdsToLogExposure = DeviceConfigClient.this.mSessionLoggingIdsToLogExposure;
                sessionlessLoggingIdsToLogExposure = DeviceConfigClient.this.mSessionlessLoggingIdsToLogExposure;
                paramsToPrefetch = DeviceConfigClient.this.mParamsToPrefetch;
                DeviceConfigClient.this.mSessionLoggingIdsToLogExposure = new HashSet();
                DeviceConfigClient.this.mSessionlessLoggingIdsToLogExposure = new HashSet();
                DeviceConfigClient.this.mParamsToPrefetch = new HashSet();
            }
            return fetchConfigParams(paramsToPrefetch) | logExposures(sessionLoggingIdsToLogExposure, false) | logExposures(sessionlessLoggingIdsToLogExposure, true);
        }

        private boolean fetchConfigParams(Set<String> paramsToPrefetch) {
            String[] paramsToPrefetchAsArray;
            if (paramsToPrefetch.isEmpty()) {
                return false;
            }
            String[] paramsToPrefetchAsArray2 = new String[paramsToPrefetch.size()];
            paramsToPrefetch.toArray(paramsToPrefetchAsArray2);
            Map<String, ValueInfo> multipleValues = DeviceConfigClient.this.mMobileConfigClient.getMultiple(paramsToPrefetchAsArray2);
            Set<String> cleanedUpParamsActuallyPrefetched = new HashSet<>();
            Set<String> paramsActuallyPrefetched = new HashSet<>();
            for (Map.Entry<String, ValueInfo> entry : multipleValues.entrySet()) {
                String paramName = entry.getKey();
                ValueInfo mobileConfigValue = entry.getValue();
                String queryString = mobileConfigValue.getQueryString();
                if (paramsToPrefetch.contains(queryString)) {
                    cleanedUpParamsActuallyPrefetched.add(paramName);
                    paramsActuallyPrefetched.add(queryString);
                }
                Object newServiceValue = mobileConfigValue.getValue();
                if (newServiceValue == null) {
                    DeviceConfigTelemetryLogger.logUnknownParam(DeviceConfigClient.this.mContext, "ConfigParam fetched a null. Skipping.", paramName);
                } else {
                    String loggingId = mobileConfigValue.getLoggingId();
                    boolean sessionless = mobileConfigValue.getIsSessionless();
                    int type = mobileConfigValue.getType();
                    if (type == 1) {
                        paramsToPrefetchAsArray = paramsToPrefetchAsArray2;
                        CacheUpdateMode cacheUpdateMode = queryCacheUpdateMode(paramName, DeviceConfigClient.this.mMemoryState.getBooleanValues(), newServiceValue);
                        DeviceConfigClient deviceConfigClient = DeviceConfigClient.this;
                        deviceConfigClient.updateMemoryStateValuePrefetchedFromService(deviceConfigClient.mMemoryState.getBooleanValues(), paramName, Boolean.valueOf(((Boolean) newServiceValue).booleanValue()), cacheUpdateMode, loggingId, sessionless);
                    } else if (type == 2) {
                        paramsToPrefetchAsArray = paramsToPrefetchAsArray2;
                        CacheUpdateMode cacheUpdateMode2 = queryCacheUpdateMode(paramName, DeviceConfigClient.this.mMemoryState.getLongValues(), newServiceValue);
                        DeviceConfigClient deviceConfigClient2 = DeviceConfigClient.this;
                        deviceConfigClient2.updateMemoryStateValuePrefetchedFromService(deviceConfigClient2.mMemoryState.getLongValues(), paramName, Long.valueOf(((Long) newServiceValue).longValue()), cacheUpdateMode2, loggingId, sessionless);
                    } else if (type == 3) {
                        paramsToPrefetchAsArray = paramsToPrefetchAsArray2;
                        CacheUpdateMode cacheUpdateMode3 = queryCacheUpdateMode(paramName, DeviceConfigClient.this.mMemoryState.getStringValues(), newServiceValue);
                        DeviceConfigClient deviceConfigClient3 = DeviceConfigClient.this;
                        deviceConfigClient3.updateMemoryStateValuePrefetchedFromService(deviceConfigClient3.mMemoryState.getStringValues(), paramName, (String) newServiceValue, cacheUpdateMode3, loggingId, sessionless);
                    } else if (type != 4) {
                        DeviceConfigTelemetryLogger.logUnknownMCType(DeviceConfigClient.this.mContext, mobileConfigValue.getType());
                        paramsToPrefetchAsArray = paramsToPrefetchAsArray2;
                    } else {
                        CacheUpdateMode cacheUpdateMode4 = queryCacheUpdateMode(paramName, DeviceConfigClient.this.mMemoryState.getDoubleValues(), newServiceValue);
                        DeviceConfigClient deviceConfigClient4 = DeviceConfigClient.this;
                        paramsToPrefetchAsArray = paramsToPrefetchAsArray2;
                        deviceConfigClient4.updateMemoryStateValuePrefetchedFromService(deviceConfigClient4.mMemoryState.getDoubleValues(), paramName, Double.valueOf(((Double) newServiceValue).doubleValue()), cacheUpdateMode4, loggingId, sessionless);
                    }
                    paramsToPrefetchAsArray2 = paramsToPrefetchAsArray;
                }
            }
            if (!paramsToPrefetch.isEmpty()) {
                if (DeviceConfigClient.this.mCallback != null) {
                    String[] paramNamesPrefetched = new String[cleanedUpParamsActuallyPrefetched.size()];
                    cleanedUpParamsActuallyPrefetched.toArray(paramNamesPrefetched);
                    DeviceConfigClient.this.mCallback.onPrefetched(paramNamesPrefetched);
                }
                if (paramsToPrefetch.size() != paramsActuallyPrefetched.size()) {
                    Set<String> incorrectParamNames = new HashSet<>(paramsToPrefetch);
                    incorrectParamNames.removeAll(paramsActuallyPrefetched);
                    DeviceConfigTelemetryLogger.logUnknownParam(DeviceConfigClient.this.mContext, "Could not prefetch the following config params", TextUtils.join(", ", incorrectParamNames));
                }
            }
            return true;
        }

        private <Type> CacheUpdateMode queryCacheUpdateMode(String paramName, Map<String, ValueInfo<Type>> values, Object newValue) {
            if (DeviceConfigClient.this.mCallback == null) {
                return CacheUpdateMode.SerializationOnly;
            }
            ValueInfo<Type> valueInfo = DeviceConfigClient.this.getServiceValueFromMemoryState(values, paramName);
            if (valueInfo == null || !valueInfo.wasValueReturned()) {
                return CacheUpdateMode.InMemoryAndSerialization;
            }
            if (valueInfo.getValueToOverride() != null) {
                return CacheUpdateMode.SerializationOnly;
            }
            Object currentValue = valueInfo.getValue();
            if (currentValue == null) {
                DeviceConfigTelemetryLogger.logInternalParamError(DeviceConfigClient.this.mContext, "Current value is null", paramName);
                return CacheUpdateMode.SerializationOnly;
            } else if (currentValue.equals(newValue)) {
                return CacheUpdateMode.SerializationOnly;
            } else {
                if (DeviceConfigClient.this.mCallback.onParamChanged(paramName, currentValue, newValue) == DeviceConfigCallback.ParamChangedResult.UpdateNow) {
                    return CacheUpdateMode.ReturnedValueAndSerialization;
                }
                return CacheUpdateMode.SerializationOnly;
            }
        }

        private boolean logExposures(Set<String> loggingIds, boolean sessionless) {
            if (loggingIds.isEmpty()) {
                return false;
            }
            for (String loggingId : loggingIds) {
                DeviceConfigClient.this.mMobileConfigClient.logExposure(loggingId, sessionless);
            }
            return true;
        }

        public void shutdown() {
            this.mStopThread.set(true);
            this.mSignal.setSignal();
            try {
                this.mThread.join();
            } catch (InterruptedException e) {
                DeviceConfigTelemetryLogger.logInternalError(DeviceConfigClient.this.mContext, "Thread join() got interrupted during shutdown");
                Thread.currentThread().interrupt();
            }
        }
    }

    private class AsyncFetchThread extends Thread {
        private final AsyncFetch mAsyncFetchInstance;

        public AsyncFetchThread(AsyncFetch asyncFetchInstance) {
            this.mAsyncFetchInstance = asyncFetchInstance;
        }

        public void run() {
            try {
                long lastUpdateTime = DeviceConfigClient.getTimeNowInMs();
                while (true) {
                    if (Thread.interrupted() || this.mAsyncFetchInstance.mStopThread.get()) {
                        break;
                    }
                    if (!this.mAsyncFetchInstance.doWork()) {
                        if (DeviceConfigClient.getTimeNowInMs() - lastUpdateTime > 60000 && DeviceConfigClient.this.clearAsyncFetch(this.mAsyncFetchInstance, true)) {
                            BLog.d(DeviceConfigClient.TAG, "AsyncFetchThread has been idle for a while. Stopping it.");
                            break;
                        }
                    } else {
                        lastUpdateTime = DeviceConfigClient.getTimeNowInMs();
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
