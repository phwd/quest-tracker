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
    private static final String TAG = "DeviceConfigModuleImpl";
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

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public double getDoubleSyncImpl(String str) {
        try {
            return getDeviceConfigClient().getDouble(str);
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "DeviceConfigClient.getDouble(" + str + ") threw an exception " + e.getMessage());
            return 0.0d;
        }
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public double getLongSyncImpl(String str) {
        try {
            return (double) getDeviceConfigClient().getLong(str);
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "DeviceConfigClient.getLong(" + str + ") threw an exception " + e.getMessage());
            return 0.0d;
        }
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public String getStringSyncImpl(String str) {
        try {
            return getDeviceConfigClient().getString(str);
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "DeviceConfigClient.getString(" + str + ") threw an exception " + e.getMessage());
            return BuildConfig.PROVIDER_SUFFIX;
        }
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public boolean getBooleanSyncImpl(String str) {
        try {
            return getDeviceConfigClient().getBoolean(str);
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "DeviceConfigClient.getBoolean(" + str + ") threw an exception " + e.getMessage());
            return false;
        }
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getLongImpl(String str, Resolver<Double> resolver) {
        double d;
        try {
            d = (double) getDeviceConfigClient().getLong(str);
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "DeviceConfigClient.getLong(" + str + ") threw an exception " + e.getMessage());
            d = 0.0d;
        }
        resolver.resolve(Double.valueOf(d));
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getBooleanImpl(String str, Resolver<Boolean> resolver) {
        try {
            resolver.resolve(Boolean.valueOf(getDeviceConfigClient().getBoolean(str)));
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "DeviceConfigClient.getBoolean(" + str + ") threw an exception " + e.getMessage());
            resolver.reject(e);
        }
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getDoubleImpl(String str, Resolver<Double> resolver) {
        try {
            resolver.resolve(Double.valueOf(getDeviceConfigClient().getDouble(str)));
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "DeviceConfigClient.getDouble(" + str + ") threw an exception " + e.getMessage());
            resolver.reject(e);
        }
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getStringImpl(String str, Resolver<String> resolver) {
        try {
            resolver.resolve(getDeviceConfigClient().getString(str));
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "DeviceConfigClient.getString(" + str + ") threw an exception " + e.getMessage());
            resolver.reject(e);
        }
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getDeviceLongImpl(String str, Resolver<Double> resolver) {
        try {
            resolver.resolve(Double.valueOf((double) getDeviceConfigClient().getDeviceLong(str)));
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "DeviceConfigClient.getDeviceLong(" + str + ") threw an exception " + e.getMessage());
            resolver.reject(e);
        }
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getDeviceBooleanImpl(String str, Resolver<Boolean> resolver) {
        try {
            resolver.resolve(Boolean.valueOf(getDeviceConfigClient().getDeviceBoolean(str)));
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "DeviceConfigClient.getDeviceBoolean(" + str + ") threw an exception " + e.getMessage());
            resolver.reject(e);
        }
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getDeviceDoubleImpl(String str, Resolver<Double> resolver) {
        try {
            resolver.resolve(Double.valueOf(getDeviceConfigClient().getDeviceDouble(str)));
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "DeviceConfigClient.getDeviceDouble(" + str + ") threw an exception " + e.getMessage());
            resolver.reject(e);
        }
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getDeviceStringImpl(String str, Resolver<String> resolver) {
        try {
            resolver.resolve(getDeviceConfigClient().getDeviceString(str));
        } catch (Exception e) {
            String str2 = TAG;
            Log.e(str2, "DeviceConfigClient.getDeviceString(" + str + ") threw an exception " + e.getMessage());
            resolver.reject(e);
        }
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void prefetchImpl(DeviceConfigModule.ConfigParamList configParamList) {
        List<String> list = configParamList.paramNames;
        try {
            getDeviceConfigClient().internalPrefetch((String[]) list.toArray(new String[list.size()]));
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "DeviceConfigClient.prefetch() threw an exception " + e.getMessage());
        }
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getParamNamesFromSchemaImpl(Resolver<List<String>> resolver) {
        try {
            resolver.resolve(new ArrayList(getDeviceConfigClient().mParamsMapEntries.keySet()));
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "DeviceConfigClient.getParamsNamesFromSchema() threw an exception " + e.getMessage());
            resolver.reject(e);
        }
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void subscribeImpl(final boolean z) {
        try {
            getDeviceConfigClient().subscribe(new DeviceConfigCallback() {
                /* class com.oculus.modules.DeviceConfigModuleImpl.AnonymousClass1 */

                @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
                public final void onSuccess() {
                    DeviceConfigModuleImpl.this.emitOnSubscriptionSuccess("\"success\"");
                }

                @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
                public final void onFailure(String str) {
                    DeviceConfigModuleImpl deviceConfigModuleImpl = DeviceConfigModuleImpl.this;
                    deviceConfigModuleImpl.emitOnSubscriptionFailure("\"" + str + "\"");
                }

                @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
                public final void onPrefetched(String[] strArr) {
                    DeviceConfigModule.ConfigParamList configParamList = new DeviceConfigModule.ConfigParamList();
                    configParamList.paramNames = Arrays.asList(strArr);
                    DeviceConfigModuleImpl.this.emitOnSubscriptionPrefetched(configParamList);
                }

                @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
                public final boolean enableAutoPrefetch() {
                    return z;
                }
            });
        } catch (Exception e) {
            String str = TAG;
            Log.e(str, "DeviceConfigClient.subscribe() threw an exception " + e.getMessage());
            emitOnSubscriptionFailure("\"Exception thrown: " + e.getClass().getName() + "\"");
        }
    }
}
