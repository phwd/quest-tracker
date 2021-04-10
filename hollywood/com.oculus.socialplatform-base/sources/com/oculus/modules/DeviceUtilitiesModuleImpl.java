package com.oculus.modules;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import com.oculus.modules.codegen.DeviceUtilitiesModule;
import com.oculus.modules.codegen.Resolver;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Iterator;

public class DeviceUtilitiesModuleImpl extends DeviceUtilitiesModule {
    public static final String OTA_CHECK_FOR_UPDATE_ACTION = "ext_check_updates";
    public static final String OTA_COMPONENT_NAME = "com.oculus.updater.core.os.OSUpdateService";
    public static final String OTA_PACKAGE_NAME = "com.oculus.updater";
    public static final String TAG = "DeviceUtilitiesModuleImpl";
    public final Context mContext;

    @Override // com.oculus.modules.codegen.DeviceUtilitiesModule
    public void checkForOTAImpl(Resolver<Void> resolver) {
        ComponentName componentName = new ComponentName("com.oculus.updater", OTA_COMPONENT_NAME);
        Intent intent = new Intent();
        intent.setAction(OTA_CHECK_FOR_UPDATE_ACTION);
        intent.setComponent(componentName);
        try {
            this.mContext.startService(intent);
            resolver.resolve(null);
        } catch (IllegalStateException | SecurityException e) {
            resolver.reject(e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0024, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0028, code lost:
        throw r0;
     */
    @Override // com.oculus.modules.codegen.DeviceUtilitiesModule
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void checkPermissionGrantedImpl(java.lang.String r4, java.lang.String r5, com.oculus.modules.codegen.Resolver<java.lang.Boolean> r6) {
        /*
            r3 = this;
            java.lang.String r1 = "DeviceUtilitiesModuleImpl"
            java.lang.String r0 = "checkPermissionGrantedImpl"
            com.oculus.panellib.SystraceBlock r2 = new com.oculus.panellib.SystraceBlock
            r2.<init>(r1, r0)
            android.content.Context r0 = r3.mContext     // Catch:{ all -> 0x0022 }
            android.content.pm.PackageManager r0 = r0.getPackageManager()     // Catch:{ all -> 0x0022 }
            int r1 = r0.checkPermission(r5, r4)     // Catch:{ all -> 0x0022 }
            r0 = 0
            if (r1 != 0) goto L_0x0017
            r0 = 1
        L_0x0017:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x0022 }
            r6.resolve(r0)     // Catch:{ all -> 0x0022 }
            r2.close()
            return
        L_0x0022:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0024 }
        L_0x0024:
            r0 = move-exception
            r2.close()     // Catch:{ all -> 0x0028 }
        L_0x0028:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.DeviceUtilitiesModuleImpl.checkPermissionGrantedImpl(java.lang.String, java.lang.String, com.oculus.modules.codegen.Resolver):void");
    }

    @Override // com.oculus.modules.codegen.DeviceUtilitiesModule
    public void restartHeadsetImpl() {
        ((PowerManager) this.mContext.getSystemService("power")).reboot(null);
    }

    public DeviceUtilitiesModuleImpl(Context context) {
        this.mContext = context;
    }

    @Override // com.oculus.modules.codegen.DeviceUtilitiesModule
    public void getMacAddressImpl(Resolver<String> resolver) {
        try {
            Iterator it = Collections.list(NetworkInterface.getNetworkInterfaces()).iterator();
            while (it.hasNext()) {
                NetworkInterface networkInterface = (NetworkInterface) it.next();
                if (networkInterface.getName().equalsIgnoreCase("wlan0")) {
                    byte[] hardwareAddress = networkInterface.getHardwareAddress();
                    if (hardwareAddress == null) {
                        resolver.reject(new Exception("Failed to get MAC address. No hardware address"));
                    }
                    StringBuilder sb = new StringBuilder();
                    for (byte b : hardwareAddress) {
                        sb.append(String.format("%02X:", Byte.valueOf(b)));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    resolver.resolve(sb.toString());
                }
            }
        } catch (Exception e) {
            resolver.reject(e);
        }
    }
}
