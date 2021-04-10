package com.oculus.modules;

import X.AnonymousClass006;
import android.content.Context;
import android.util.Log;
import com.oculus.deviceconfigclient.DeviceConfigCallback;
import com.oculus.deviceconfigclient.DeviceConfigClient;
import com.oculus.modules.codegen.DeviceConfigModule;
import com.oculus.modules.codegen.Resolver;
import java.util.Arrays;
import java.util.List;

public class DeviceConfigModuleImpl extends DeviceConfigModule {
    public static final String TAG = "DeviceConfigModuleImpl";
    public Context mContext = null;
    public DeviceConfigClient mDeviceConfigClient = null;

    private DeviceConfigClient getDeviceConfigClient() {
        DeviceConfigClient deviceConfigClient = this.mDeviceConfigClient;
        if (deviceConfigClient != null) {
            return deviceConfigClient;
        }
        DeviceConfigClient deviceConfigClient2 = new DeviceConfigClient(this.mContext);
        this.mDeviceConfigClient = deviceConfigClient2;
        return deviceConfigClient2;
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void prefetchImpl(DeviceConfigModule.ConfigParamList configParamList) {
        List<String> list = configParamList.paramNames;
        try {
            getDeviceConfigClient().internalPrefetch((String[]) list.toArray(new String[list.size()]));
        } catch (Exception e) {
            Log.e(TAG, AnonymousClass006.A07("DeviceConfigClient.prefetch() threw an exception ", e.getMessage()));
        }
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void subscribeImpl(final boolean z, final boolean z2) {
        try {
            getDeviceConfigClient().subscribe(new DeviceConfigCallback() {
                /* class com.oculus.modules.DeviceConfigModuleImpl.AnonymousClass1 */

                @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
                public void onFailure(String str) {
                    DeviceConfigModuleImpl.this.emitOnSubscriptionFailure(AnonymousClass006.A09("\"", str, "\""));
                }

                @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
                public void onPrefetched(String[] strArr) {
                    DeviceConfigModule.ConfigParamList configParamList = new DeviceConfigModule.ConfigParamList();
                    configParamList.paramNames = Arrays.asList(strArr);
                    DeviceConfigModuleImpl.this.emitOnSubscriptionPrefetched(configParamList);
                }

                @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
                public void onSuccess() {
                    DeviceConfigModuleImpl.this.emitOnSubscriptionSuccess("\"success\"");
                }

                @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
                public void onUpdate(String str) {
                    DeviceConfigModuleImpl.this.emitOnSubscriptionUpdate(AnonymousClass006.A09("\"", str, "\""));
                }

                @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
                public boolean enableAutoPrefetch() {
                    return z2;
                }

                @Override // com.oculus.deviceconfigclient.DeviceConfigCallback
                public boolean supportUpdates() {
                    return z;
                }
            });
        } catch (Exception e) {
            Log.e(TAG, AnonymousClass006.A07("DeviceConfigClient.subscribe() threw an exception ", e.getMessage()));
            emitOnSubscriptionFailure(AnonymousClass006.A09("\"Exception thrown: ", e.getClass().getName(), "\""));
        }
    }

    public DeviceConfigModuleImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getBooleanImpl(String str, Resolver<Boolean> resolver) {
        try {
            resolver.resolve(Boolean.valueOf(getDeviceConfigClient().getBoolean(str)));
        } catch (Exception e) {
            Log.e(TAG, AnonymousClass006.A0B("DeviceConfigClient.getBoolean(", str, ") threw an exception ", e.getMessage()));
            resolver.reject(e);
        }
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public boolean getBooleanSyncImpl(String str) {
        try {
            return getDeviceConfigClient().getBoolean(str);
        } catch (Exception e) {
            Log.e(TAG, AnonymousClass006.A0B("DeviceConfigClient.getBoolean(", str, ") threw an exception ", e.getMessage()));
            return false;
        }
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getDeviceBooleanImpl(String str, Resolver<Boolean> resolver) {
        try {
            resolver.resolve(Boolean.valueOf(getDeviceConfigClient().getDeviceBoolean(str)));
        } catch (Exception e) {
            Log.e(TAG, AnonymousClass006.A0B("DeviceConfigClient.getDeviceBoolean(", str, ") threw an exception ", e.getMessage()));
            resolver.reject(e);
        }
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getDeviceDoubleImpl(String str, Resolver<Double> resolver) {
        try {
            resolver.resolve(Double.valueOf(getDeviceConfigClient().getDeviceDouble(str)));
        } catch (Exception e) {
            Log.e(TAG, AnonymousClass006.A0B("DeviceConfigClient.getDeviceDouble(", str, ") threw an exception ", e.getMessage()));
            resolver.reject(e);
        }
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getDeviceLongImpl(String str, Resolver<Double> resolver) {
        try {
            resolver.resolve(Double.valueOf((double) getDeviceConfigClient().getDeviceLong(str)));
        } catch (Exception e) {
            Log.e(TAG, AnonymousClass006.A0B("DeviceConfigClient.getDeviceLong(", str, ") threw an exception ", e.getMessage()));
            resolver.reject(e);
        }
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getDeviceStringImpl(String str, Resolver<String> resolver) {
        try {
            resolver.resolve(getDeviceConfigClient().getDeviceString(str));
        } catch (Exception e) {
            Log.e(TAG, AnonymousClass006.A0B("DeviceConfigClient.getDeviceString(", str, ") threw an exception ", e.getMessage()));
            resolver.reject(e);
        }
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getDoubleImpl(String str, Resolver<Double> resolver) {
        try {
            resolver.resolve(Double.valueOf(getDeviceConfigClient().getDouble(str)));
        } catch (Exception e) {
            Log.e(TAG, AnonymousClass006.A0B("DeviceConfigClient.getDouble(", str, ") threw an exception ", e.getMessage()));
            resolver.reject(e);
        }
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public double getDoubleSyncImpl(String str) {
        try {
            return getDeviceConfigClient().getDouble(str);
        } catch (Exception e) {
            Log.e(TAG, AnonymousClass006.A0B("DeviceConfigClient.getDouble(", str, ") threw an exception ", e.getMessage()));
            return 0.0d;
        }
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getLongImpl(String str, Resolver<Double> resolver) {
        double d;
        try {
            d = (double) getDeviceConfigClient().getLong(str);
        } catch (Exception e) {
            Log.e(TAG, AnonymousClass006.A0B("DeviceConfigClient.getLong(", str, ") threw an exception ", e.getMessage()));
            d = 0.0d;
        }
        resolver.resolve(Double.valueOf(d));
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public double getLongSyncImpl(String str) {
        try {
            return (double) getDeviceConfigClient().getLong(str);
        } catch (Exception e) {
            Log.e(TAG, AnonymousClass006.A0B("DeviceConfigClient.getLong(", str, ") threw an exception ", e.getMessage()));
            return 0.0d;
        }
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public void getStringImpl(String str, Resolver<String> resolver) {
        try {
            resolver.resolve(getDeviceConfigClient().getString(str));
        } catch (Exception e) {
            Log.e(TAG, AnonymousClass006.A0B("DeviceConfigClient.getString(", str, ") threw an exception ", e.getMessage()));
            resolver.reject(e);
        }
    }

    @Override // com.oculus.modules.codegen.DeviceConfigModule
    public String getStringSyncImpl(String str) {
        try {
            return getDeviceConfigClient().getString(str);
        } catch (Exception e) {
            Log.e(TAG, AnonymousClass006.A0B("DeviceConfigClient.getString(", str, ") threw an exception ", e.getMessage()));
            return "";
        }
    }
}
