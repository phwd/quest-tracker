package com.oculus.vrshell.config;

import android.content.Context;
import android.util.Log;
import com.oculus.common.logutilities.LoggingUtil;
import com.oculus.deviceconfigclient.DeviceConfigCallback;
import com.oculus.deviceconfigclient.DeviceConfigClient;
import java.util.HashMap;
import java.util.Map;

public class DeviceConfigHolder {
    private static final String TAG = LoggingUtil.tag(DeviceConfigHolder.class);
    private static DeviceConfigClient sDeviceConfigClient;

    public static synchronized void initialize(Context context) {
        synchronized (DeviceConfigHolder.class) {
            if (sDeviceConfigClient == null) {
                sDeviceConfigClient = new DeviceConfigClient(context);
                sDeviceConfigClient.subscribe(new DeviceConfigCallback());
            } else {
                Log.e(TAG, "Called initialize twice!");
            }
        }
    }

    public static synchronized void shutdown() {
        synchronized (DeviceConfigHolder.class) {
            if (sDeviceConfigClient != null) {
                sDeviceConfigClient.shutdown();
                sDeviceConfigClient = null;
            } else {
                Log.e(TAG, "Called shutdown twice!");
            }
        }
    }

    public static synchronized DeviceConfigClient getInstance() {
        DeviceConfigClient deviceConfigClient;
        synchronized (DeviceConfigHolder.class) {
            if (sDeviceConfigClient != null) {
                deviceConfigClient = sDeviceConfigClient;
            } else {
                throw new IllegalStateException("DeviceConfigHolder.initialize() has not been called");
            }
        }
        return deviceConfigClient;
    }

    static Map<String, Boolean> getSystemShellParamConfigs() {
        DeviceConfigClient instance = getInstance();
        String debugOnlyGetMemorySnapshot = instance.debugOnlyGetMemorySnapshot("");
        Map<String, Object> debugOnlyGetParamsDefaults = instance.debugOnlyGetParamsDefaults();
        String[] split = debugOnlyGetMemorySnapshot.split("\n");
        HashMap hashMap = new HashMap();
        for (String str : split) {
            String[] split2 = str.split(":");
            if (split2.length >= 2) {
                String str2 = split2[0];
                if (str2.equals("oculus_systemshell")) {
                    String str3 = str2 + ":" + split2[1];
                    if (debugOnlyGetParamsDefaults.containsKey(str3)) {
                        hashMap.put(split2[1], Boolean.valueOf(instance.getBoolean(str3)));
                    }
                }
            }
        }
        return hashMap;
    }
}
