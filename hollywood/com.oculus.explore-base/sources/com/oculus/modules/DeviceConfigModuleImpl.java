package com.oculus.modules;

import android.content.Context;
import android.util.Log;
import com.oculus.common.build.BuildConfig;
import com.oculus.deviceconfigclient.DeviceConfigCallback;
import com.oculus.deviceconfigclient.DeviceConfigClient;
import com.oculus.modules.codegen.DeviceConfigModule;
import com.oculus.modules.codegen.Resolver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeviceConfigModuleImpl extends DeviceConfigModule {
    private static final String TAG = DeviceConfigModuleImpl.class.getSimpleName();
    private Context mContext = null;
    private DeviceConfigClient mDeviceConfigClient = null;

    public DeviceConfigModuleImpl(Context context) {
        this.mContext = context;
    }

    private DeviceConfigClient getDeviceConfigClient() {
        if (this.mDeviceConfigClient == null) {
            this.mDeviceConfigClient = new DeviceConfigClient(this.mContext);
        }
        return this.mDeviceConfigClient;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public double getDoubleSyncImpl(String param) {
        try {
            return getDeviceConfigClient().getDouble(param);
        } catch (Exception e) {
            Log.e(TAG, "DeviceConfigClient.getDouble(" + param + ") threw an exception " + e.getMessage());
            return 0.0d;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public double getLongSyncImpl(String param) {
        try {
            return (double) getDeviceConfigClient().getLong(param);
        } catch (Exception e) {
            Log.e(TAG, "DeviceConfigClient.getLong(" + param + ") threw an exception " + e.getMessage());
            return 0.0d;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public String getStringSyncImpl(String param) {
        try {
            return getDeviceConfigClient().getString(param);
        } catch (Exception e) {
            Log.e(TAG, "DeviceConfigClient.getString(" + param + ") threw an exception " + e.getMessage());
            return BuildConfig.PROVIDER_SUFFIX;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public boolean getBooleanSyncImpl(String param) {
        try {
            return getDeviceConfigClient().getBoolean(param);
        } catch (Exception e) {
            Log.e(TAG, "DeviceConfigClient.getBoolean(" + param + ") threw an exception " + e.getMessage());
            return false;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getLongImpl(String param, Resolver<Double> resolver) {
        double result;
        try {
            result = (double) getDeviceConfigClient().getLong(param);
        } catch (Exception e) {
            Log.e(TAG, "DeviceConfigClient.getLong(" + param + ") threw an exception " + e.getMessage());
            result = 0.0d;
        }
        resolver.resolve(Double.valueOf(result));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getBooleanImpl(String param, Resolver<Boolean> resolver) {
        try {
            resolver.resolve(Boolean.valueOf(getDeviceConfigClient().getBoolean(param)));
        } catch (Exception e) {
            Log.e(TAG, "DeviceConfigClient.getBoolean(" + param + ") threw an exception " + e.getMessage());
            resolver.reject(e);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getDoubleImpl(String param, Resolver<Double> resolver) {
        try {
            resolver.resolve(Double.valueOf(getDeviceConfigClient().getDouble(param)));
        } catch (Exception e) {
            Log.e(TAG, "DeviceConfigClient.getDouble(" + param + ") threw an exception " + e.getMessage());
            resolver.reject(e);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getStringImpl(String param, Resolver<String> resolver) {
        try {
            resolver.resolve(getDeviceConfigClient().getString(param));
        } catch (Exception e) {
            Log.e(TAG, "DeviceConfigClient.getString(" + param + ") threw an exception " + e.getMessage());
            resolver.reject(e);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getDeviceLongImpl(String param, Resolver<Double> resolver) {
        try {
            resolver.resolve(Double.valueOf((double) getDeviceConfigClient().getDeviceLong(param)));
        } catch (Exception e) {
            Log.e(TAG, "DeviceConfigClient.getDeviceLong(" + param + ") threw an exception " + e.getMessage());
            resolver.reject(e);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getDeviceBooleanImpl(String param, Resolver<Boolean> resolver) {
        try {
            resolver.resolve(Boolean.valueOf(getDeviceConfigClient().getDeviceBoolean(param)));
        } catch (Exception e) {
            Log.e(TAG, "DeviceConfigClient.getDeviceBoolean(" + param + ") threw an exception " + e.getMessage());
            resolver.reject(e);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getDeviceDoubleImpl(String param, Resolver<Double> resolver) {
        try {
            resolver.resolve(Double.valueOf(getDeviceConfigClient().getDeviceDouble(param)));
        } catch (Exception e) {
            Log.e(TAG, "DeviceConfigClient.getDeviceDouble(" + param + ") threw an exception " + e.getMessage());
            resolver.reject(e);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getDeviceStringImpl(String param, Resolver<String> resolver) {
        try {
            resolver.resolve(getDeviceConfigClient().getDeviceString(param));
        } catch (Exception e) {
            Log.e(TAG, "DeviceConfigClient.getDeviceString(" + param + ") threw an exception " + e.getMessage());
            resolver.reject(e);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void prefetchImpl(DeviceConfigModule.ConfigParamList paramNames) {
        List<String> params = paramNames.paramNames;
        try {
            getDeviceConfigClient().prefetch((String[]) params.toArray(new String[params.size()]));
        } catch (Exception e) {
            Log.e(TAG, "DeviceConfigClient.prefetch() threw an exception " + e.getMessage());
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getParamNamesFromSchemaImpl(Resolver<List<String>> resolver) {
        try {
            resolver.resolve(new ArrayList<>(getDeviceConfigClient().getParamNamesFromSchema()));
        } catch (Exception e) {
            Log.e(TAG, "DeviceConfigClient.getParamsNamesFromSchema() threw an exception " + e.getMessage());
            resolver.reject(e);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void subscribeImpl(final boolean enableAutoPrefetch) {
        try {
            getDeviceConfigClient().subscribe(new DeviceConfigCallback() {
                /* class com.oculus.modules.DeviceConfigModuleImpl.AnonymousClass1 */

                /* access modifiers changed from: protected */
                @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
                public void onSuccess() {
                    DeviceConfigModuleImpl.this.emitOnSubscriptionSuccess("\"success\"");
                }

                /* access modifiers changed from: protected */
                @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
                public void onFailure(String error) {
                    DeviceConfigModuleImpl.this.emitOnSubscriptionFailure("\"" + error + "\"");
                }

                /* access modifiers changed from: protected */
                @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
                public void onPrefetched(String[] paramNames) {
                    DeviceConfigModule.ConfigParamList paramList = new DeviceConfigModule.ConfigParamList();
                    paramList.paramNames = Arrays.asList(paramNames);
                    DeviceConfigModuleImpl.this.emitOnSubscriptionPrefetched(paramList);
                }

                /* access modifiers changed from: protected */
                @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
                public boolean enableAutoPrefetch() {
                    return enableAutoPrefetch;
                }
            });
        } catch (Exception e) {
            Log.e(TAG, "DeviceConfigClient.subscribe() threw an exception " + e.getMessage());
            emitOnSubscriptionFailure("\"Exception thrown: " + e.getClass().getName() + "\"");
        }
    }
}
