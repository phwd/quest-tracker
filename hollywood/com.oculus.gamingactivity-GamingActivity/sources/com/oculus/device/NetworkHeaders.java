package com.oculus.device;

import android.content.Context;
import com.google.common.net.HttpHeaders;
import com.oculus.modules.DeviceEnvironmentModuleImpl;
import com.oculus.modules.codegen.DeviceEnvironmentModule;
import com.oculus.panellib.AppDetails;
import com.oculus.panellib.SystemProperties;
import java.util.HashMap;
import java.util.Map;

public class NetworkHeaders {
    private static String sUserAgent;

    private NetworkHeaders() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003e, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x003f, code lost:
        r1 = r0;
        r0 = r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0053, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0054, code lost:
        r1 = null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void init(android.content.Context r12, com.oculus.device.IUserAgentBuilder r13, java.lang.String r14) {
        /*
            com.oculus.panellib.SystraceBlock r8 = new com.oculus.panellib.SystraceBlock
            java.lang.String r0 = "NetworkHeaders.init"
            r8.<init>(r0)
            r10 = 0
            java.lang.String r5 = r12.getPackageName()     // Catch:{ Throwable -> 0x003c, all -> 0x0053 }
            com.oculus.panellib.AppDetails r9 = com.oculus.panellib.AppDetails.get(r12, r5)     // Catch:{ Throwable -> 0x003c, all -> 0x0053 }
            java.lang.String r0 = "http.agent"
            java.lang.String r2 = java.lang.System.getProperty(r0)     // Catch:{ Throwable -> 0x003c, all -> 0x0053 }
            java.lang.String r4 = r9.versionName     // Catch:{ Throwable -> 0x003c, all -> 0x0053 }
            int r0 = r9.versionCode     // Catch:{ Throwable -> 0x003c, all -> 0x0053 }
            java.lang.String r6 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x003c, all -> 0x0053 }
            java.lang.String r7 = com.oculus.modules.DeviceEnvironmentModuleImpl.getDeviceLocale(r12)     // Catch:{ Throwable -> 0x003c, all -> 0x0053 }
            r0 = r13
            r1 = r12
            r3 = r14
            java.lang.String r0 = r0.build(r1, r2, r3, r4, r5, r6, r7)     // Catch:{ Throwable -> 0x003c, all -> 0x0053 }
            com.oculus.device.NetworkHeaders.sUserAgent = r0     // Catch:{ Throwable -> 0x003c, all -> 0x0053 }
            if (r8 == 0) goto L_0x0032
            if (r10 == 0) goto L_0x0038
            r8.close()     // Catch:{ Throwable -> 0x0033 }
        L_0x0032:
            return
        L_0x0033:
            r0 = move-exception
            r10.addSuppressed(r0)
            goto L_0x0032
        L_0x0038:
            r8.close()
            goto L_0x0032
        L_0x003c:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x003e }
        L_0x003e:
            r1 = move-exception
            r11 = r1
            r1 = r0
            r0 = r11
        L_0x0042:
            if (r8 == 0) goto L_0x0049
            if (r1 == 0) goto L_0x004f
            r8.close()     // Catch:{ Throwable -> 0x004a }
        L_0x0049:
            throw r0
        L_0x004a:
            r2 = move-exception
            r1.addSuppressed(r2)
            goto L_0x0049
        L_0x004f:
            r8.close()
            goto L_0x0049
        L_0x0053:
            r0 = move-exception
            r1 = r10
            goto L_0x0042
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.device.NetworkHeaders.init(android.content.Context, com.oculus.device.IUserAgentBuilder, java.lang.String):void");
    }

    static void reset() {
        sUserAgent = null;
    }

    public static String getUserAgent() {
        if (sUserAgent != null) {
            return sUserAgent;
        }
        throw new RuntimeException("You must call init before calling NetworkHeaders.getUserAgent.");
    }

    public static Map<String, String> getHeaderMap(Context context) {
        DeviceEnvironmentModule.DeviceEnvironmentHeadersType headers = getHeaders(context);
        Map<String, String> map = new HashMap<>();
        map.put(HttpHeaders.ACCEPT_LANGUAGE, headers.Accept_Language);
        map.put(HttpHeaders.USER_AGENT, headers.User_Agent);
        map.put("X-ANDROID-ID", headers.X_ANDROID_ID);
        map.put("X-Build-Model", headers.X_Build_Model);
        map.put("X-Build-Number", headers.X_Build_Number);
        map.put("X-Build-Version-Incremental", headers.X_Build_Version_Incremental);
        map.put("X-OC-Selected-Headset-Serial", headers.X_OC_Selected_Headset_Serial);
        map.put("X-OC-VrShell-Build-Name", headers.X_OC_VrShell_Build_Name);
        return map;
    }

    public static DeviceEnvironmentModule.DeviceEnvironmentHeadersType getHeaders(Context context) {
        DeviceEnvironmentModule.DeviceEnvironmentHeadersType headers = new DeviceEnvironmentModule.DeviceEnvironmentHeadersType();
        headers.Accept_Language = DeviceEnvironmentModuleImpl.getDeviceLocale(context);
        headers.User_Agent = getUserAgent();
        headers.X_ANDROID_ID = DeviceEnvironmentModuleImpl.getDeviceId(context);
        headers.X_Build_Model = DeviceEnvironmentModuleImpl.getDeviceName();
        headers.X_Build_Number = String.valueOf(AppDetails.get(context, context.getPackageName()).versionCode);
        headers.X_Build_Version_Incremental = SystemProperties.getString("ro.build.version.incremental", "");
        headers.X_OC_Selected_Headset_Serial = DeviceEnvironmentModuleImpl.getDeviceSerial();
        headers.X_OC_VrShell_Build_Name = AppDetails.get(context, "com.oculus.vrshell").versionName;
        return headers;
    }
}
