package com.oculus.deviceconfigclient;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import com.facebook.breakpad.BreakpadManager;
import com.facebook.debug.log.BLog;
import com.facebook.mobileconfig.factory.MobileConfigOptions;
import com.facebook.mobileconfig.metadata.ParamsMapEntry;
import com.facebook.mobileconfigservice.client_base.MobileConfigBaseClient;
import com.facebook.mobileconfigservice.client_base.ValueInfo;
import com.facebook.mobileconfigservice.client_ifaces.IMobileConfigChangeListener;
import com.facebook.mobileconfigservice.client_ifaces.MobileConfigServiceSubscribeCallback;
import com.oculus.common.build.BuildConfig;
import com.oculus.deviceconfigclient.DeviceConfigCallback;
import com.oculus.deviceconfigclient.DeviceConfigTelemetryLogger;
import com.oculus.panellib.Qpl;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class DeviceConfigClient {
    private static final Boolean DEFAULT_BOOLEAN = false;
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
        private String mServicePackage = BuildConfig.PACKAGE_NAME_HORIZON;

        public String getMobileConfigServicePackage() {
            return this.mServicePackage;
        }
    }

    public DeviceConfigClient(Context context) {
        this(context, new Configuration());
    }

    public DeviceConfigClient(Context context, Configuration configuration) {
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
        MobileConfigBaseClient baseClient = null;
        try {
            MobileConfigBaseClient baseClient2 = new MobileConfigBaseClient(configuration.getMobileConfigServicePackage(), context);
            try {
                baseClient2.initLogging(new MarauderLogger(context), null);
                String paramsMapContent = baseClient2.getParamsMapContent();
                this.mCurrentParamsMapVersion = ParamsMapEntry.parseSchemaHash(paramsMapContent);
                this.mParamsMapEntries = ParamsMapEntry.parseParamsMapToMap(paramsMapContent);
                baseClient = baseClient2;
            } catch (Exception e2) {
                e = e2;
                baseClient = baseClient2;
                DeviceConfigTelemetryLogger.logInternalError(this.mContext, e);
                this.mCurrentParamsMapVersion = "##UNKNOWN_VERSION##";
                this.mParamsMapEntries = new HashMap();
                setParamNamesByConfig();
                this.mMobileConfigClient = baseClient;
                this.mMobileConfigOptions = MobileConfigOptions.create().requestForValueSource();
                this.mMemoryState = ConfigStorageAdapter.createMemoryStateFromStorageCache(context);
                fillParamsDefaults();
            }
        } catch (Exception e3) {
            e = e3;
            DeviceConfigTelemetryLogger.logInternalError(this.mContext, e);
            this.mCurrentParamsMapVersion = "##UNKNOWN_VERSION##";
            this.mParamsMapEntries = new HashMap();
            setParamNamesByConfig();
            this.mMobileConfigClient = baseClient;
            this.mMobileConfigOptions = MobileConfigOptions.create().requestForValueSource();
            this.mMemoryState = ConfigStorageAdapter.createMemoryStateFromStorageCache(context);
            fillParamsDefaults();
        }
        setParamNamesByConfig();
        this.mMobileConfigClient = baseClient;
        this.mMobileConfigOptions = MobileConfigOptions.create().requestForValueSource();
        this.mMemoryState = ConfigStorageAdapter.createMemoryStateFromStorageCache(context);
        fillParamsDefaults();
    }

    public Set<String> getParamNamesFromSchema() {
        return this.mParamsMapEntries.keySet();
    }

    private void setParamNamesByConfig() {
        for (Map.Entry<String, ParamsMapEntry> paramEntry : this.mParamsMapEntries.entrySet()) {
            String paramName = paramEntry.getKey();
            String paramConfigName = paramEntry.getValue().configName;
            Set<String> paramNames = this.mParamNamesByConfig.get(paramConfigName);
            if (paramNames == null) {
                paramNames = new HashSet<>();
                this.mParamNamesByConfig.put(paramConfigName, paramNames);
            }
            paramNames.add(paramName);
        }
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
            if (this.mMobileConfigClient != null) {
                this.mMobileConfigClient.subscribe(mobileConfigCallback);
            }
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
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.BOOLEAN, paramName, value, valueInfo.getValueToOverride() != null ? "OVERRIDDEN" : "CACHED", getTimeNowInMs() - startTime);
            if (value == null) {
                value = getBooleanDefault(paramName);
            }
            return value.booleanValue();
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
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DOUBLE, paramName, value, valueInfo.getValueToOverride() != null ? "OVERRIDDEN" : "CACHED", getTimeNowInMs() - startTime);
            if (value == null) {
                value = getDoubleDefault(paramName);
            }
            return value.doubleValue();
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
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.LONG, paramName, value, valueInfo.getValueToOverride() != null ? "OVERRIDDEN" : "CACHED", getTimeNowInMs() - startTime);
            if (value == null) {
                value = getLongDefault(paramName);
            }
            return value.longValue();
        } catch (Exception e) {
            DeviceConfigTelemetryLogger.logGetValueFailure(this.mContext, DeviceConfigTelemetryLogger.ValueType.LONG, paramName, e);
            return getLongDefault(paramName).longValue();
        }
    }

    public String getString(String paramName) {
        try {
            long startTime = getTimeNowInMs();
            if (!isParamValid(paramName, 3)) {
                return BuildConfig.PROVIDER_SUFFIX;
            }
            ValueInfo<String> valueInfo = getFromMemoryStateAndSendExposureToService(this.mMemoryState.getStringValues(), paramName);
            if (valueInfo == null) {
                return getStringDefault(paramName);
            }
            String value = valueInfo.getValue();
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.STRING, paramName, value, valueInfo.getValueToOverride() != null ? "OVERRIDDEN" : "CACHED", getTimeNowInMs() - startTime);
            if (value == null) {
                return getStringDefault(paramName);
            }
            return value;
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
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_BOOLEAN, paramName, serviceCachedValue, valueInfo.getValueToOverride() != null ? "OVERRIDDEN" : "SERVICE_CACHED", getTimeNowInMs() - startTime);
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
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_DOUBLE, paramName, serviceCachedValue, valueInfo.getValueToOverride() != null ? "OVERRIDDEN" : "SERVICE_CACHED", getTimeNowInMs() - startTime);
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
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_LONG, paramName, serviceCachedValue, valueInfo.getValueToOverride() != null ? "OVERRIDDEN" : "SERVICE_CACHED", getTimeNowInMs() - startTime);
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
                return BuildConfig.PROVIDER_SUFFIX;
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
            DeviceConfigTelemetryLogger.logGetValueSuccess(this.mContext, DeviceConfigTelemetryLogger.ValueType.DEVICE_STRING, paramName, serviceCachedValue, valueInfo.getValueToOverride() != null ? "OVERRIDDEN" : "SERVICE_CACHED", getTimeNowInMs() - startTime);
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

    /* access modifiers changed from: private */
    @SuppressLint({"BadMethodUse-java.lang.System.currentTimeMillis"})
    public static long getTimeNowInMs() {
        return System.currentTimeMillis();
    }

    private void fillParamsDefaults() {
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.mContext.getAssets().open("params_default.txt")));
            boolean checkHeader = true;
            while (true) {
                try {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        bufferedReader.close();
                        return;
                    } else if (checkHeader) {
                        if (!line.equals("DeviceConfig,params_default.txt,V1")) {
                            BLog.e("DeviceConfigClient", "Unexpected header '%s' for default param file", line);
                            bufferedReader.close();
                            return;
                        }
                        checkHeader = false;
                    } else if (!line.isEmpty()) {
                        String[] splitLine = line.split("=", 2);
                        if (splitLine.length == 2) {
                            String configParamName = splitLine[0];
                            String defaultValueAsString = splitLine[1];
                            ParamsMapEntry entry = this.mParamsMapEntries.get(configParamName);
                            if (entry != null) {
                                int paramType = entry.paramType;
                                switch (paramType) {
                                    case 1:
                                        this.mParamsDefaults.put(configParamName, Boolean.valueOf(Boolean.parseBoolean(defaultValueAsString)));
                                        continue;
                                    case Qpl.ACTION_SUCCESS /*{ENCODED_INT: 2}*/:
                                        this.mParamsDefaults.put(configParamName, Long.valueOf(Long.parseLong(defaultValueAsString)));
                                        continue;
                                    case 3:
                                        this.mParamsDefaults.put(configParamName, defaultValueAsString);
                                        continue;
                                    case BreakpadManager.SIGILL:
                                        this.mParamsDefaults.put(configParamName, Double.valueOf(Double.parseDouble(defaultValueAsString)));
                                        continue;
                                    default:
                                        try {
                                            BLog.e("DeviceConfigClient", "Unsupported param type %s", Integer.valueOf(paramType));
                                            continue;
                                        } catch (NumberFormatException e) {
                                            DeviceConfigTelemetryLogger.logIncorrectDefaultValue(this.mContext, String.format("Cannot convert default value '%s' to type %s", defaultValueAsString, Integer.valueOf(paramType)), configParamName);
                                            break;
                                        }
                                }
                            } else {
                                BLog.e("DeviceConfigClient", "Can't set default for unknown param '%s'", configParamName);
                            }
                        } else {
                            BLog.e("DeviceConfigClient", "Incorrect default format for param '%s'", line);
                        }
                    }
                } catch (Throwable th) {
                    th.addSuppressed(th);
                }
            }
            throw th;
        } catch (IOException e2) {
            DeviceConfigTelemetryLogger.logMissingDefaultFile(this.mContext, "IOException while reading default params file", "params_default.txt");
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
            switch (expectedParamType) {
                case 1:
                    errorMessage = "Param is not a boolean";
                    break;
                case Qpl.ACTION_SUCCESS /*{ENCODED_INT: 2}*/:
                    errorMessage = "Param is not a long";
                    break;
                case 3:
                    errorMessage = "Param is not a string";
                    break;
                case BreakpadManager.SIGILL:
                    errorMessage = "Param is not a double";
                    break;
                default:
                    errorMessage = "Param has an unknown type";
                    break;
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
        return BuildConfig.PROVIDER_SUFFIX;
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
    private <Type> ValueInfo<Type> getServiceValueFromMemoryState(Map<String, ValueInfo<Type>> values, String paramName) {
        ValueInfo<Type> memoryStateValue;
        synchronized (this.mMemoryStateLocker) {
            memoryStateValue = values.get(paramName);
        }
        if (memoryStateValue == null || (memoryStateValue.getValueToOverride() == null && !memoryStateValue.hasValueSetFromService())) {
            return null;
        }
        return memoryStateValue;
    }

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
    private <Type> void updateMemoryStateValuePrefetchedFromService(Map<String, ValueInfo<Type>> valuesToUpdate, String paramName, Type value, CacheUpdateMode cacheUpdateMode, String loggingId, boolean sessionless) {
        updateMemoryStateValueFromServiceInternal(valuesToUpdate, paramName, value, cacheUpdateMode, loggingId, sessionless);
    }

    private <Type> void updateMemoryStateValueFromServiceInternal(Map<String, ValueInfo<Type>> valuesToUpdate, String paramName, Type value, CacheUpdateMode cacheUpdateMode, String loggingId, boolean sessionless) {
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
        switch (AnonymousClass2.$SwitchMap$com$oculus$deviceconfigclient$DeviceConfigClient$CacheUpdateMode[cacheUpdateMode.ordinal()]) {
            case 1:
                serializationNeeded = updatedValue.setValueFromService(value, true);
                break;
            case Qpl.ACTION_SUCCESS /*{ENCODED_INT: 2}*/:
                serializationNeeded = updatedValue.setValueFromService(value, false);
                break;
            case 3:
                serializationNeeded = updatedValue.setValueForSerialization(value);
                break;
            default:
                DeviceConfigTelemetryLogger.logInternalError(this.mContext, "Incorrect CacheUpdateMode");
                serializationNeeded = false;
                break;
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
            BLog.d("DeviceConfigClient", "Config '%s' updated.", configName);
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
            BLog.e("DeviceConfigClient", "MobileConfig client is not initialized properly - Can't add change listeners.");
        } else if (this.mChangeListenersAdded) {
            BLog.w("DeviceConfigClient", "ChangeListeners have been already added.");
        } else {
            for (Map.Entry<String, Set<String>> configEntry : this.mParamNamesByConfig.entrySet()) {
                this.mMobileConfigClient.addChangeListener(configEntry.getKey(), this.mChangeListener);
            }
            this.mChangeListenersAdded = true;
        }
    }

    private String getParamNameForGetMultiple(String paramName, int paramType) {
        String defaultValue;
        switch (paramType) {
            case 1:
                defaultValue = Boolean.toString(getBooleanDefault(paramName).booleanValue());
                break;
            case Qpl.ACTION_SUCCESS /*{ENCODED_INT: 2}*/:
                defaultValue = Long.toString(getLongDefault(paramName).longValue());
                break;
            case 3:
                defaultValue = getStringDefault(paramName);
                break;
            case BreakpadManager.SIGILL:
                defaultValue = Double.toString(getDoubleDefault(paramName).doubleValue());
                break;
            default:
                DeviceConfigTelemetryLogger.logUnknownMCType(this.mContext, paramType);
                defaultValue = BuildConfig.PROVIDER_SUFFIX;
                break;
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
                DeviceConfigClient.this.logSynchronizedSet("Unsubscribed - ParamsToPrefetch: ", DeviceConfigClient.this.mParamsToPrefetch);
                DeviceConfigClient.this.logSynchronizedSet("Unsubscribed - LogExposure: ", DeviceConfigClient.this.mSessionLoggingIdsToLogExposure);
                DeviceConfigClient.this.logSynchronizedSet("Unsubscribed - LogExposureSessionless: ", DeviceConfigClient.this.mSessionlessLoggingIdsToLogExposure);
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
            if (paramsToPrefetch.isEmpty()) {
                return false;
            }
            String[] paramsToPrefetchAsArray = new String[paramsToPrefetch.size()];
            paramsToPrefetch.toArray(paramsToPrefetchAsArray);
            Map<String, ValueInfo> multipleValues = DeviceConfigClient.this.mMobileConfigClient.getMultiple(paramsToPrefetchAsArray);
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
                    switch (mobileConfigValue.getType()) {
                        case 1:
                            DeviceConfigClient.this.updateMemoryStateValuePrefetchedFromService(DeviceConfigClient.this.mMemoryState.getBooleanValues(), paramName, Boolean.valueOf(((Boolean) newServiceValue).booleanValue()), queryCacheUpdateMode(paramName, DeviceConfigClient.this.mMemoryState.getBooleanValues(), newServiceValue), loggingId, sessionless);
                            continue;
                        case Qpl.ACTION_SUCCESS /*{ENCODED_INT: 2}*/:
                            DeviceConfigClient.this.updateMemoryStateValuePrefetchedFromService(DeviceConfigClient.this.mMemoryState.getLongValues(), paramName, Long.valueOf(((Long) newServiceValue).longValue()), queryCacheUpdateMode(paramName, DeviceConfigClient.this.mMemoryState.getLongValues(), newServiceValue), loggingId, sessionless);
                            continue;
                        case 3:
                            DeviceConfigClient.this.updateMemoryStateValuePrefetchedFromService(DeviceConfigClient.this.mMemoryState.getStringValues(), paramName, (String) newServiceValue, queryCacheUpdateMode(paramName, DeviceConfigClient.this.mMemoryState.getStringValues(), newServiceValue), loggingId, sessionless);
                            continue;
                        case BreakpadManager.SIGILL:
                            DeviceConfigClient.this.updateMemoryStateValuePrefetchedFromService(DeviceConfigClient.this.mMemoryState.getDoubleValues(), paramName, Double.valueOf(((Double) newServiceValue).doubleValue()), queryCacheUpdateMode(paramName, DeviceConfigClient.this.mMemoryState.getDoubleValues(), newServiceValue), loggingId, sessionless);
                            continue;
                        default:
                            DeviceConfigTelemetryLogger.logUnknownMCType(DeviceConfigClient.this.mContext, mobileConfigValue.getType());
                            continue;
                    }
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
                            BLog.d("DeviceConfigClient", "AsyncFetchThread has been idle for a while. Stopping it.");
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
