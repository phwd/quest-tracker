package com.oculus.mobileconfigclient;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.ParcelFileDescriptor;
import android.os.RemoteException;
import android.util.Log;
import android.util.Pair;
import com.oculus.aidl.IMobileConfigService;
import com.oculus.os.AnalyticsEvent;
import com.oculus.os.UnifiedTelemetryLogger;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MobileConfigClient {
    private static final String CONFIG_ERROR = "error";
    private static final String CONFIG_ERROR_DETAILS = "error_details";
    private static final String CONFIG_EVENT_NAME = "oculus_config";
    private static final String CONFIG_METADATA_JSON = "ConfigMetadata.json";
    private static final String CONFIG_STATUS = "status";
    private static final String MOBILE_CONFIG_SERVICE_PACKAGE_NAME = "com.oculus.horizon";
    private static final String MOBILE_CONFIG_SERVICE_SERVICE_NAME = "com.oculus.mobileconfigservice.MobileConfigService";
    private static final String TAG = MobileConfigClient.class.getSimpleName();
    private static final String TYPE_BOOL = "bool";
    private static final String TYPE_DOUBLE = "double";
    private static final String TYPE_FBT = "fbt";
    private static final String TYPE_INT = "int";
    private static final String TYPE_STR = "str";
    private static final Map<Integer, Boolean> booleanValues = new HashMap();
    private static final Map<Integer, Double> doubleValues = new HashMap();
    private static final Map<Integer, Integer> integerValues = new HashMap();
    private static final Object mMobileConfigServiceLocker = new Object();
    private static final Map<Integer, String> stringValues = new HashMap();
    private final Context mContext;
    private String mHash;
    @Nullable
    private IMobileConfigService mMobileConfigService = null;
    private boolean mMobileConfigServiceConnected;
    private final ServiceConnection mMobileConfigServiceConnection = new ServiceConnection() {
        /* class com.oculus.mobileconfigclient.MobileConfigClient.AnonymousClass1 */

        public void onServiceConnected(ComponentName name, IBinder service) {
            synchronized (MobileConfigClient.mMobileConfigServiceLocker) {
                MobileConfigClient.this.setMobileConfigService(IMobileConfigService.Stub.asInterface(service), true);
                if (MobileConfigClient.this.mShouldUpdateMCs) {
                    MobileConfigClient.this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_config_fetch_ipc_queued").setExtra("error", ""), false);
                    MobileConfigClient.this.updateMCsAndHandleResult(MobileConfigClient.this.mHash);
                }
            }
        }

        public void onServiceDisconnected(ComponentName name) {
            synchronized (MobileConfigClient.mMobileConfigServiceLocker) {
                MobileConfigClient.this.setMobileConfigService(null, false);
            }
        }
    };
    private boolean mShouldUpdateMCs;
    private final UnifiedTelemetryLogger mUnifiedTelemetryLogger;

    public enum DefaultValue {
        YES,
        NO
    }

    public MobileConfigClient(Context context) {
        this.mContext = context;
        this.mUnifiedTelemetryLogger = UnifiedTelemetryLogger.getInstance();
        this.mUnifiedTelemetryLogger.init(context);
        bindToMobileConfigService(this.mMobileConfigServiceConnection);
    }

    public void close() {
        try {
            synchronized (mMobileConfigServiceLocker) {
                if (this.mMobileConfigServiceConnected && this.mMobileConfigService != null) {
                    this.mMobileConfigServiceConnected = false;
                    this.mContext.unbindService(this.mMobileConfigServiceConnection);
                }
            }
        } catch (IllegalArgumentException ex) {
            Log.e(TAG, "handling unbinding mobileconfig service failed", ex);
            this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_unbind_failed").setExtra("error", ""), false);
        }
    }

    public void updateMCsImpl(String hash) {
        this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_config_fetch_ipc_reach_java").setExtra("error", ""), false);
        synchronized (mMobileConfigServiceLocker) {
            if (!this.mMobileConfigServiceConnected || this.mMobileConfigService == null) {
                this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_config_fetch_ipc_not_connected").setExtra("error", ""), false);
                this.mShouldUpdateMCs = true;
                this.mHash = hash;
                return;
            }
            this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_config_fetch_ipc_already_connected").setExtra("error", ""), false);
            updateMCsAndHandleResult(hash);
        }
    }

    @Nullable
    public Pair<String, DefaultValue> getStringImpl(MobileConfigParamType param, String defaultVal) {
        String val;
        this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_param_access_start").setExtra("error", "config - " + param.config + "; param - " + param.parameter + "; slot id - " + param.slotId), false);
        synchronized (mMobileConfigServiceLocker) {
            val = stringValues.get(Integer.valueOf((int) param.slotId));
        }
        if (val == null) {
            this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_param_not_found").setExtra("error", "config - " + param.config + "; param - " + param.parameter + "; slot id - " + param.slotId + "; string value map - " + stringValues.toString() + "; int value map - " + integerValues.toString() + "; bool value map - " + booleanValues.toString() + "; double value map - " + doubleValues.toString()), false);
            return new Pair<>(defaultVal, DefaultValue.YES);
        }
        this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_param_found").setExtra("error", "config - " + param.config + "; param - " + param.parameter + "; slot id - " + param.slotId + "; return val - " + val + "; string value map - " + stringValues.toString() + "; int value map - " + integerValues.toString() + "; bool value map - " + booleanValues.toString() + "; double value map - " + doubleValues.toString()), false);
        logExposureImpl(param);
        return new Pair<>(val, DefaultValue.NO);
    }

    @Nullable
    public Pair<Boolean, DefaultValue> getBoolImpl(MobileConfigParamType param, boolean defaultVal) {
        Boolean val;
        this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_param_access_start").setExtra("error", "config - " + param.config + "; param - " + param.parameter + "; slot id - " + param.slotId), false);
        synchronized (mMobileConfigServiceLocker) {
            val = booleanValues.get(Integer.valueOf((int) param.slotId));
        }
        if (val == null) {
            this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_param_not_found").setExtra("error", "config - " + param.config + "; param - " + param.parameter + "; slot id - " + param.slotId + "; string value map - " + stringValues.toString() + "; int value map - " + integerValues.toString() + "; bool value map - " + booleanValues.toString() + "; double value map - " + doubleValues.toString()), false);
            return new Pair<>(Boolean.valueOf(defaultVal), DefaultValue.YES);
        }
        this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_param_found").setExtra("error", "config - " + param.config + "; param - " + param.parameter + "; slot id - " + param.slotId + "; return val - " + val + "; string value map - " + stringValues.toString() + "; int value map - " + integerValues.toString() + "; bool value map - " + booleanValues.toString() + "; double value map - " + doubleValues.toString()), false);
        logExposureImpl(param);
        return new Pair<>(val, DefaultValue.NO);
    }

    @Nullable
    public Pair<Integer, DefaultValue> getIntImpl(MobileConfigParamType param, double defaultVal) {
        Integer val;
        this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_param_access_start").setExtra("error", "config - " + param.config + "; param - " + param.parameter + "; slot id - " + param.slotId), false);
        synchronized (mMobileConfigServiceLocker) {
            val = integerValues.get(Integer.valueOf((int) param.slotId));
        }
        if (val == null) {
            this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_param_not_found").setExtra("error", "config - " + param.config + "; param - " + param.parameter + "; slot id - " + param.slotId + "; string value map - " + stringValues.toString() + "; int value map - " + integerValues.toString() + "; bool value map - " + booleanValues.toString() + "; double value map - " + doubleValues.toString()), false);
            return new Pair<>(Double.valueOf(defaultVal), DefaultValue.YES);
        }
        this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_param_found").setExtra("error", "config - " + param.config + "; param - " + param.parameter + "; slot id - " + param.slotId + "; return val - " + val + "; string value map - " + stringValues.toString() + "; int value map - " + integerValues.toString() + "; bool value map - " + booleanValues.toString() + "; double value map - " + doubleValues.toString()), false);
        logExposureImpl(param);
        return new Pair<>(val, DefaultValue.NO);
    }

    @Nullable
    public Pair<Double, DefaultValue> getDoubleImpl(MobileConfigParamType param, double defaultVal) {
        Double val;
        this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_param_access_start").setExtra("error", "config - " + param.config + "; param - " + param.parameter + "; slot id - " + param.slotId), false);
        synchronized (mMobileConfigServiceLocker) {
            val = doubleValues.get(Integer.valueOf((int) param.slotId));
        }
        if (val == null) {
            this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_param_not_found").setExtra("error", "config - " + param.config + "; param - " + param.parameter + "; slot id - " + param.slotId + "; string value map - " + stringValues.toString() + "; int value map - " + integerValues.toString() + "; bool value map - " + booleanValues.toString() + "; double value map - " + doubleValues.toString()), false);
            return new Pair<>(Double.valueOf(defaultVal), DefaultValue.YES);
        }
        this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_param_found").setExtra("error", "config - " + param.config + "; param - " + param.parameter + "; slot id - " + param.slotId + "; return val - " + val + "; string value map - " + stringValues.toString() + "; int value map - " + integerValues.toString() + "; bool value map - " + booleanValues.toString() + "; double value map - " + doubleValues.toString()), false);
        logExposureImpl(param);
        return new Pair<>(val, DefaultValue.NO);
    }

    @Nullable
    public Pair<String, DefaultValue> getStringWithoutLoggingExposureImpl(MobileConfigParamType param, String defaultVal) {
        Pair<String, DefaultValue> pair;
        synchronized (mMobileConfigServiceLocker) {
            String val = stringValues.get(Integer.valueOf((int) param.slotId));
            if (val == null) {
                pair = new Pair<>(defaultVal, DefaultValue.YES);
            } else {
                pair = new Pair<>(val, DefaultValue.NO);
            }
        }
        return pair;
    }

    @Nullable
    public Pair<Boolean, DefaultValue> getBoolWithoutLoggingExposureImpl(MobileConfigParamType param, boolean defaultVal) {
        Pair<Boolean, DefaultValue> pair;
        synchronized (mMobileConfigServiceLocker) {
            Boolean val = booleanValues.get(Integer.valueOf((int) param.slotId));
            if (val == null) {
                pair = new Pair<>(Boolean.valueOf(defaultVal), DefaultValue.YES);
            } else {
                pair = new Pair<>(val, DefaultValue.NO);
            }
        }
        return pair;
    }

    @Nullable
    public Pair<Integer, DefaultValue> getIntWithoutLoggingExposureImpl(MobileConfigParamType param, double defaultVal) {
        Pair<Integer, DefaultValue> pair;
        synchronized (mMobileConfigServiceLocker) {
            Integer val = integerValues.get(Integer.valueOf((int) param.slotId));
            if (val == null) {
                pair = new Pair<>(Double.valueOf(defaultVal), DefaultValue.YES);
            } else {
                pair = new Pair<>(val, DefaultValue.NO);
            }
        }
        return pair;
    }

    @Nullable
    public Pair<Double, DefaultValue> getDoubleWithoutLoggingExposureImpl(MobileConfigParamType param, double defaultVal) {
        Pair<Double, DefaultValue> pair;
        synchronized (mMobileConfigServiceLocker) {
            Double val = doubleValues.get(Integer.valueOf((int) param.slotId));
            if (val == null) {
                pair = new Pair<>(Double.valueOf(defaultVal), DefaultValue.YES);
            } else {
                pair = new Pair<>(val, DefaultValue.NO);
            }
        }
        return pair;
    }

    @Nullable
    public void logExposureImpl(final MobileConfigParamType param) {
        new Thread(new Runnable() {
            /* class com.oculus.mobileconfigclient.MobileConfigClient.AnonymousClass2 */

            public void run() {
                MobileConfigClient.this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_log_exposure_started").setExtra("error", "config - " + param.config + "; param - " + param.parameter), false);
                try {
                    synchronized (MobileConfigClient.mMobileConfigServiceLocker) {
                        if (!MobileConfigClient.this.mMobileConfigServiceConnected || MobileConfigClient.this.mMobileConfigService == null) {
                            Log.e(MobileConfigClient.TAG, "mobile config exposure log dropped as IPC not connected");
                            MobileConfigClient.this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_log_exposure_failed").setExtra("error", "ipc_not_connected"), false);
                        } else {
                            MobileConfigClient.this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_log_exposure_ipc_connected").setExtra("error", "config - " + param.config + "; param - " + param.parameter), false);
                            MobileConfigClient.this.mMobileConfigService.logExposure(param.config, param.parameter, MobileConfigClient.this.getLoggingId(param.config, param.parameter));
                        }
                    }
                } catch (RemoteException ex) {
                    Log.e(MobileConfigClient.TAG, "ipc fetch for mobile config exposure log failed", ex);
                    MobileConfigClient.this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_log_exposure_failed").setExtra("error", "remote_exception " + ex.getMessage()), false);
                }
            }
        }).start();
    }

    private void bindToMobileConfigService(ServiceConnection serviceConnection) {
        ComponentName component = new ComponentName("com.oculus.horizon", MOBILE_CONFIG_SERVICE_SERVICE_NAME);
        Intent bindIntent = new Intent();
        bindIntent.setComponent(component);
        this.mContext.bindService(bindIntent, serviceConnection, 1);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void setMobileConfigService(@Nullable IMobileConfigService mobileConfigService, boolean mobileConfigServiceConnected) {
        this.mMobileConfigService = mobileConfigService;
        this.mMobileConfigServiceConnected = mobileConfigServiceConnected;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    @Nullable
    private void updateMCsAndHandleResult(String hash) {
        try {
            if (this.mMobileConfigService == null) {
                Log.e(TAG, "ipc fetch failed - service not connected");
                this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_config_fetch_ipc_not_connected_inner").setExtra("error", ""), false);
                return;
            }
            String configDefinition = loadJSONFromAsset();
            if (configDefinition == null) {
                this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_config_fetch_config_definition_null").setExtra("error", ""), false);
                return;
            }
            ParcelFileDescriptor[] pipe = ParcelFileDescriptor.createPipe();
            ParcelFileDescriptor readSide = pipe[0];
            new ParcelFileDescriptor.AutoCloseOutputStream(pipe[1]).write(configDefinition.getBytes("UTF-8"));
            long startTime = System.currentTimeMillis();
            ParcelFileDescriptor returnFD = this.mMobileConfigService.updateMCs(hash, readSide, 1);
            this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "time_to_fetch_config").setExtra("error_details", String.valueOf(System.currentTimeMillis() - startTime)), false);
            String configValueJsonString = getStringFromInputStream(new ParcelFileDescriptor.AutoCloseInputStream(returnFD));
            OutputStreamWriter configValueOutputStreamWriter = new OutputStreamWriter(this.mContext.openFileOutput("config_values.txt", 0));
            configValueOutputStreamWriter.write(configValueJsonString);
            configValueOutputStreamWriter.close();
            decodeResultAndPopulateMCMap(configValueJsonString, configDefinition);
        } catch (RemoteException ex) {
            Log.e(TAG, "ipc fetch for configs failed", ex);
            this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_config_fetch_failed").setExtra("error", "remote exception " + ex.getMessage()), false);
        } catch (IOException ex2) {
            Log.e(TAG, "ipc fetch for configs failed", ex2);
            this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_config_fetch_failed").setExtra("error", "io exception " + ex2.getMessage()), false);
        } catch (NullPointerException ex3) {
            Log.e(TAG, "ipc fetch for configs failed", ex3);
            this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_config_fetch_failed").setExtra("error", "null pointer exception " + ex3.getMessage()), false);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r12v23, resolved type: java.util.Map<java.lang.Integer, java.lang.Double> */
    /* JADX DEBUG: Multi-variable search result rejected for r15v18, resolved type: java.util.Map<java.lang.Integer, java.lang.Boolean> */
    /* JADX DEBUG: Multi-variable search result rejected for r12v29, resolved type: java.util.Map<java.lang.Integer, java.lang.Integer> */
    /* JADX DEBUG: Multi-variable search result rejected for r12v30, resolved type: java.util.Map<java.lang.Integer, java.lang.String> */
    /* JADX WARN: Multi-variable type inference failed */
    private void decodeResultAndPopulateMCMap(@Nullable String configValueJsonString, String configDefinition) {
        if (configValueJsonString == null) {
            this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_config_value_string_empty").setExtra("error", ""), false);
            return;
        }
        MobileConfigSchemaFile schemaFile = getMobileConfigSchemaFromJson(configDefinition);
        Map<Pair<String, String>, String> typeMap = new HashMap<>();
        Map<Pair<String, String>, Integer> slotIdMap = new HashMap<>();
        for (Map.Entry<String, MobileConfigSchema> entry : schemaFile.schemas.entrySet()) {
            String[] keys = entry.getKey().split(":");
            Pair configIdAndParamId = new Pair(keys[0], keys[1]);
            typeMap.put(configIdAndParamId, entry.getValue().type);
            slotIdMap.put(configIdAndParamId, Integer.valueOf(entry.getValue().slotId));
        }
        MobileConfigCombinedResponse configValue = getMobileConfigValueFromJson(configValueJsonString);
        if (configValue == null || configValue.sessionbased == null || configValue.sessionbased.configs == null) {
            this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_config_value_empty").setExtra("error", "string config value - " + configValueJsonString), false);
            return;
        }
        for (Map.Entry<String, Config> entry2 : configValue.sessionbased.configs.entrySet()) {
            for (ConfigField field : entry2.getValue().fields) {
                Pair configIdAndParamId2 = new Pair(entry2.getKey(), field.name);
                String type = typeMap.get(configIdAndParamId2);
                if (type != null) {
                    char c = 65535;
                    switch (type.hashCode()) {
                        case -1325958191:
                            if (type.equals("double")) {
                                c = 4;
                                break;
                            }
                            break;
                        case 101176:
                            if (type.equals(TYPE_FBT)) {
                                c = 1;
                                break;
                            }
                            break;
                        case 104431:
                            if (type.equals(TYPE_INT)) {
                                c = 2;
                                break;
                            }
                            break;
                        case 114225:
                            if (type.equals(TYPE_STR)) {
                                c = 0;
                                break;
                            }
                            break;
                        case 3029738:
                            if (type.equals(TYPE_BOOL)) {
                                c = 3;
                                break;
                            }
                            break;
                    }
                    switch (c) {
                        case 0:
                        case 1:
                            stringValues.put(slotIdMap.get(configIdAndParamId2), field.str);
                            break;
                        case 2:
                            integerValues.put(slotIdMap.get(configIdAndParamId2), Integer.valueOf(field.i64));
                            break;
                        case 3:
                            booleanValues.put(slotIdMap.get(configIdAndParamId2), Boolean.valueOf(field.bln == 1));
                            break;
                        case 4:
                            doubleValues.put(slotIdMap.get(configIdAndParamId2), Double.valueOf(field.dbl));
                            break;
                    }
                }
            }
        }
    }

    private MobileConfigSchemaFile getMobileConfigSchemaFromJson(String configDefinition) {
        MobileConfigSchemaFile mobileConfigSchemaFile = new MobileConfigSchemaFile();
        mobileConfigSchemaFile.schemas = new HashMap();
        try {
            JSONObject mobileConfigSchemaFileJson = new JSONObject(configDefinition).getJSONObject("schema");
            Iterator<String> keys = mobileConfigSchemaFileJson.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                JSONObject mobileConfigSchemaJson = mobileConfigSchemaFileJson.getJSONObject(key);
                MobileConfigSchema mobileConfigSchema = new MobileConfigSchema();
                mobileConfigSchema.defaultValue = mobileConfigSchemaJson.getString("defaultValue");
                mobileConfigSchema.type = mobileConfigSchemaJson.getString("type");
                mobileConfigSchema.slotId = mobileConfigSchemaJson.getInt("slotId");
                mobileConfigSchemaFile.schemas.put(key, mobileConfigSchema);
            }
        } catch (JSONException ex) {
            Log.e(TAG, "exception decoding config schemas", ex);
            this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_config_definition_parsing_failed").setExtra("error", "config definition - " + configDefinition), false);
        }
        return mobileConfigSchemaFile;
    }

    private MobileConfigCombinedResponse getMobileConfigValueFromJson(String configValueJsonString) {
        MobileConfigCombinedResponse mobileConfigCombinedResponse = new MobileConfigCombinedResponse();
        try {
            JSONObject configValueJson = new JSONObject(configValueJsonString);
            mobileConfigCombinedResponse.sessionbased = getMobileConfigResponse(configValueJson.getJSONObject("sessionbased"));
            mobileConfigCombinedResponse.sessionless = getMobileConfigResponse(configValueJson.getJSONObject("sessionless"));
        } catch (JSONException ex) {
            Log.e(TAG, "exception decoding config combined values", ex);
            this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_config_value_parsing_failed").setExtra("error", "config value - " + configValueJsonString + "json exception - " + ex.getMessage()), false);
        }
        return mobileConfigCombinedResponse;
    }

    private MobileConfigResponse getMobileConfigResponse(JSONObject mobileConfigResponseJson) {
        MobileConfigResponse mobileConfigResponse = new MobileConfigResponse();
        if (mobileConfigResponseJson != null) {
            try {
                mobileConfigResponse.queryHash = mobileConfigResponseJson.getString("query_hash");
                mobileConfigResponse.configs = new HashMap();
                JSONObject allConfigsJson = mobileConfigResponseJson.getJSONObject("configs");
                Iterator<String> keys = allConfigsJson.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    JSONObject configJson = allConfigsJson.getJSONObject(key);
                    Config config = new Config();
                    config.hash = configJson.getString("hash");
                    config.fields = new ArrayList();
                    JSONArray configFieldsJson = configJson.getJSONArray("fields");
                    for (int i = 0; i < configFieldsJson.length(); i++) {
                        ConfigField configField = new ConfigField();
                        JSONObject configFieldJsonObject = configFieldsJson.getJSONObject(i);
                        configField.bln = configFieldJsonObject.optInt("bln");
                        configField.name = configFieldJsonObject.optString("pname", null);
                        configField.loggingIdentifier = configFieldJsonObject.optString("li", null);
                        configField.str = configFieldJsonObject.optString(TYPE_STR, null);
                        configField.dbl = configFieldJsonObject.optDouble("dbl");
                        configField.i64 = configFieldJsonObject.optInt("i64");
                        config.fields.add(configField);
                    }
                    mobileConfigResponse.configs.put(key, config);
                }
            } catch (JSONException ex) {
                Log.e(TAG, "exception decoding config values", ex);
                this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_config_value_parsing_failed_json_object").setExtra("error", "config value - " + mobileConfigResponseJson.toString() + "json exception - " + ex.getMessage()), false);
            }
        }
        return mobileConfigResponse;
    }

    public String getLoggingId(String configName, String paramName) {
        try {
            String configValueString = getStringFromInputStream(this.mContext.openFileInput("config_values.txt"));
            MobileConfigCombinedResponse configValue = getMobileConfigValueFromJson(configValueString);
            if (configValue == null || configValue.sessionbased == null || configValue.sessionbased.configs == null) {
                this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_config_value_empty_logging_id_extraction_failed").setExtra("error", "config value - " + configValueString), false);
                return "";
            }
            Config config = configValue.sessionbased.configs.get(configName);
            if (config == null || config.fields == null) {
                this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_config_not_found_logging_id_extraction_failed").setExtra("error", "config value - " + configValueString), false);
                return "";
            }
            for (ConfigField field : config.fields) {
                if (field.name.equals(paramName)) {
                    return field.loggingIdentifier;
                }
            }
            return "";
        } catch (IOException ex) {
            Log.e(TAG, "failed to get logging id for config - " + configName + " param - " + paramName + " ", ex);
            this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_io_exception_logging_id_extraction_failed").setExtra("error", "io exception - " + ex.getMessage()), false);
            return "";
        }
    }

    @Nullable
    private String loadJSONFromAsset() {
        try {
            String configDefinition = getStringFromInputStream(this.mContext.getAssets().open(CONFIG_METADATA_JSON));
            if (configDefinition == null) {
                return null;
            }
            return configDefinition.replaceAll("\\s", "");
        } catch (IOException ex) {
            Log.e(TAG, "Failed to read config asset file", ex);
            this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_config_definition_read_failed").setExtra("error", "io exception - " + ex.getMessage()), false);
            return null;
        }
    }

    @Nullable
    private String getStringFromInputStream(InputStream is) {
        try {
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            return new String(buffer, "UTF-8");
        } catch (IOException ex) {
            Log.e(TAG, "Failed to read from input stream", ex);
            this.mUnifiedTelemetryLogger.reportEvent(new AnalyticsEvent("oculus_config").setExtra("status", "mc_config_input_stream_read_failed").setExtra("error", "io exception - " + ex.getMessage()), false);
            return null;
        }
    }

    /* access modifiers changed from: private */
    public static class MobileConfigSchemaFile {
        public Map<String, MobileConfigSchema> schemas;

        private MobileConfigSchemaFile() {
        }
    }

    /* access modifiers changed from: private */
    public static class MobileConfigSchema {
        public String defaultValue;
        public int slotId;
        public String type;

        private MobileConfigSchema() {
        }
    }

    /* access modifiers changed from: private */
    public static class MobileConfigCombinedResponse {
        public MobileConfigResponse sessionbased;
        public MobileConfigResponse sessionless;

        private MobileConfigCombinedResponse() {
        }
    }

    /* access modifiers changed from: private */
    public static class MobileConfigResponse {
        public Map<String, Config> configs;
        public String queryHash;

        private MobileConfigResponse() {
        }
    }

    /* access modifiers changed from: private */
    public static class Config {
        public List<ConfigField> fields;
        public String hash;

        private Config() {
        }
    }

    /* access modifiers changed from: private */
    public static class ConfigField {
        public int bln;
        public double dbl;
        public int i64;
        public String loggingIdentifier;
        public String name;
        public String str;

        private ConfigField() {
        }
    }

    public static class MobileConfigParamType {
        public String config;
        public double configId;
        public String defaultValue;
        public double paramId;
        public String parameter;
        public double slotId;
        public String type;
        public double unit_type;

        public MobileConfigParamType(String config2, String parameter2, String defaultValue2, String type2, int unit_type2, int configId2, int paramId2, int slotId2) {
            this.config = config2;
            this.parameter = parameter2;
            this.defaultValue = defaultValue2;
            this.type = type2;
            this.unit_type = (double) unit_type2;
            this.configId = (double) configId2;
            this.paramId = (double) paramId2;
            this.slotId = (double) slotId2;
        }
    }
}
