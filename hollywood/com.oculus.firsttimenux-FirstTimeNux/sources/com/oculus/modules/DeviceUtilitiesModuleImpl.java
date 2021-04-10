package com.oculus.modules;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;
import com.oculus.modules.codegen.DeviceUtilitiesModule;
import com.oculus.modules.codegen.Resolver;
import java.net.NetworkInterface;
import java.util.Collections;

public class DeviceUtilitiesModuleImpl extends DeviceUtilitiesModule {
    private static final String OTA_CHECK_FOR_UPDATE_ACTION = "ext_check_updates";
    private static final String OTA_COMPONENT_NAME = "com.oculus.updater.core.os.OSUpdateService";
    private static final String OTA_PACKAGE_NAME = "com.oculus.updater";
    private static final String TAG = DeviceUtilitiesModuleImpl.class.getSimpleName();
    private final Context mContext;

    public DeviceUtilitiesModuleImpl(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DeviceUtilitiesModule
    public void checkForOTAImpl(Resolver<Void> resolver) {
        Log.d(TAG, "Checking for OTA update");
        ComponentName osUpdaterService = new ComponentName("com.oculus.updater", OTA_COMPONENT_NAME);
        Intent intent = new Intent();
        intent.setAction(OTA_CHECK_FOR_UPDATE_ACTION);
        intent.setComponent(osUpdaterService);
        try {
            this.mContext.startService(intent);
            resolver.resolve(null);
        } catch (IllegalStateException e) {
            resolver.reject(e);
        } catch (SecurityException e2) {
            resolver.reject(e2);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DeviceUtilitiesModule
    public void getMacAddressImpl(Resolver<String> resolver) {
        Log.d(TAG, "Getting MAC address");
        try {
            for (NetworkInterface nif : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                if (nif.getName().equalsIgnoreCase("wlan0")) {
                    byte[] macBytes = nif.getHardwareAddress();
                    if (macBytes == null) {
                        resolver.reject(new Exception("Failed to get MAC address. No hardware address"));
                    }
                    StringBuilder result = new StringBuilder();
                    int length = macBytes.length;
                    for (int i = 0; i < length; i++) {
                        result.append(String.format("%02X:", Byte.valueOf(macBytes[i])));
                    }
                    if (result.length() > 0) {
                        result.deleteCharAt(result.length() - 1);
                    }
                    resolver.resolve(result.toString());
                }
            }
        } catch (Exception e) {
            resolver.reject(e);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0033, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0034, code lost:
        r3 = r2;
        r2 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0048, code lost:
        r2 = th;
     */
    @Override // com.oculus.modules.codegen.DeviceUtilitiesModule
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void checkPermissionGrantedImpl(java.lang.String r7, java.lang.String r8, com.oculus.modules.codegen.Resolver<java.lang.Boolean> r9) {
        /*
            r6 = this;
            com.oculus.panellib.SystraceBlock r0 = new com.oculus.panellib.SystraceBlock
            java.lang.String r2 = com.oculus.modules.DeviceUtilitiesModuleImpl.TAG
            java.lang.String r3 = "checkPermissionGrantedImpl"
            r0.<init>(r2, r3)
            r3 = 0
            android.content.Context r2 = r6.mContext     // Catch:{ Throwable -> 0x0031, all -> 0x0048 }
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch:{ Throwable -> 0x0031, all -> 0x0048 }
            int r2 = r2.checkPermission(r8, r7)     // Catch:{ Throwable -> 0x0031, all -> 0x0048 }
            if (r2 != 0) goto L_0x0026
            r1 = 1
        L_0x0017:
            java.lang.Boolean r2 = java.lang.Boolean.valueOf(r1)     // Catch:{ Throwable -> 0x0031, all -> 0x0048 }
            r9.resolve(r2)     // Catch:{ Throwable -> 0x0031, all -> 0x0048 }
            if (r0 == 0) goto L_0x0025
            if (r3 == 0) goto L_0x002d
            r0.close()     // Catch:{ Throwable -> 0x0028 }
        L_0x0025:
            return
        L_0x0026:
            r1 = 0
            goto L_0x0017
        L_0x0028:
            r2 = move-exception
            r3.addSuppressed(r2)
            goto L_0x0025
        L_0x002d:
            r0.close()
            goto L_0x0025
        L_0x0031:
            r2 = move-exception
            throw r2     // Catch:{ all -> 0x0033 }
        L_0x0033:
            r3 = move-exception
            r5 = r3
            r3 = r2
            r2 = r5
        L_0x0037:
            if (r0 == 0) goto L_0x003e
            if (r3 == 0) goto L_0x0044
            r0.close()     // Catch:{ Throwable -> 0x003f }
        L_0x003e:
            throw r2
        L_0x003f:
            r4 = move-exception
            r3.addSuppressed(r4)
            goto L_0x003e
        L_0x0044:
            r0.close()
            goto L_0x003e
        L_0x0048:
            r2 = move-exception
            goto L_0x0037
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.modules.DeviceUtilitiesModuleImpl.checkPermissionGrantedImpl(java.lang.String, java.lang.String, com.oculus.modules.codegen.Resolver):void");
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.DeviceUtilitiesModule
    public void restartHeadsetImpl() {
        Log.d(TAG, "Restarting headset");
        ((PowerManager) this.mContext.getSystemService("power")).reboot(null);
    }
}
