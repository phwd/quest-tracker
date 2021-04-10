package com.oculus.device;

import android.content.Context;
import com.oculus.http.headers.RequestHeaders;
import com.oculus.modules.DeviceEnvironmentModuleImpl;
import com.oculus.modules.codegen.DeviceEnvironmentModule;
import com.oculus.panellib.AppDetails;
import com.oculus.panellib.SystemProperties;
import java.util.HashMap;
import java.util.Map;

public class NetworkHeaders {
    public static String sUserAgent;

    public static void reset() {
        sUserAgent = null;
    }

    public static DeviceEnvironmentModule.DeviceEnvironmentHeadersType getHeaders(Context context) {
        DeviceEnvironmentModule.DeviceEnvironmentHeadersType deviceEnvironmentHeadersType = new DeviceEnvironmentModule.DeviceEnvironmentHeadersType();
        deviceEnvironmentHeadersType.Accept_Language = DeviceEnvironmentModuleImpl.getDeviceLocale(context);
        deviceEnvironmentHeadersType.User_Agent = getUserAgent();
        deviceEnvironmentHeadersType.X_ANDROID_ID = DeviceEnvironmentModuleImpl.getDeviceId(context);
        deviceEnvironmentHeadersType.X_Build_Model = DeviceEnvironmentModuleImpl.getDeviceName();
        deviceEnvironmentHeadersType.X_Build_Number = String.valueOf(AppDetails.get(context, context.getPackageName(), false).versionCode);
        deviceEnvironmentHeadersType.X_Build_Version_Incremental = SystemProperties.getString("ro.build.version.incremental", "");
        deviceEnvironmentHeadersType.X_OC_Selected_Headset_Serial = DeviceEnvironmentModuleImpl.getDeviceSerial();
        deviceEnvironmentHeadersType.X_OC_VrShell_Build_Name = AppDetails.get(context, "com.oculus.vrshell", false).versionName;
        return deviceEnvironmentHeadersType;
    }

    public static String getUserAgent() {
        String str = sUserAgent;
        if (str != null) {
            return str;
        }
        throw new RuntimeException("You must call init before calling NetworkHeaders.getUserAgent.");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r2.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0035, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0031, code lost:
        r0 = move-exception;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void init(android.content.Context r10, com.oculus.device.IUserAgentBuilder r11, java.lang.String r12) {
        /*
            java.lang.String r0 = "NetworkHeaders.init"
            com.oculus.panellib.SystraceBlock r2 = new com.oculus.panellib.SystraceBlock
            r2.<init>(r0)
            r4 = r10
            java.lang.String r8 = r10.getPackageName()     // Catch:{ all -> 0x002f }
            r0 = 0
            com.oculus.panellib.AppDetails r1 = com.oculus.panellib.AppDetails.get(r10, r8, r0)     // Catch:{ all -> 0x002f }
            java.lang.String r0 = "http.agent"
            java.lang.String r5 = java.lang.System.getProperty(r0)     // Catch:{ all -> 0x002f }
            java.lang.String r7 = r1.versionName     // Catch:{ all -> 0x002f }
            int r0 = r1.versionCode     // Catch:{ all -> 0x002f }
            java.lang.String r9 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x002f }
            java.lang.String r10 = com.oculus.modules.DeviceEnvironmentModuleImpl.getDeviceLocale(r10)     // Catch:{ all -> 0x002f }
            r3 = r11
            r6 = r12
            java.lang.String r0 = r3.build(r4, r5, r6, r7, r8, r9, r10)     // Catch:{ all -> 0x002f }
            com.oculus.device.NetworkHeaders.sUserAgent = r0     // Catch:{ all -> 0x002f }
            r2.close()
            return
        L_0x002f:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0031 }
        L_0x0031:
            r0 = move-exception
            r2.close()     // Catch:{ all -> 0x0035 }
        L_0x0035:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.device.NetworkHeaders.init(android.content.Context, com.oculus.device.IUserAgentBuilder, java.lang.String):void");
    }

    public static Map<String, String> getHeaderMap(Context context) {
        DeviceEnvironmentModule.DeviceEnvironmentHeadersType headers = getHeaders(context);
        HashMap hashMap = new HashMap();
        hashMap.put("Accept-Language", headers.Accept_Language);
        hashMap.put("User-Agent", headers.User_Agent);
        hashMap.put("X-ANDROID-ID", headers.X_ANDROID_ID);
        hashMap.put("X-Build-Model", headers.X_Build_Model);
        hashMap.put("X-Build-Number", headers.X_Build_Number);
        hashMap.put(RequestHeaders.BUILD_VERSION_REQUEST_HEADER_NAME, headers.X_Build_Version_Incremental);
        hashMap.put("X-OC-Selected-Headset-Serial", headers.X_OC_Selected_Headset_Serial);
        hashMap.put("X-OC-VrShell-Build-Name", headers.X_OC_VrShell_Build_Name);
        return hashMap;
    }
}
