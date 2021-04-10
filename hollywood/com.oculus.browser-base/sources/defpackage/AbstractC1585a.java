package defpackage;

import android.util.Log;
import java.io.Closeable;
import java.io.IOException;

/* renamed from: a  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC1585a {

    /* renamed from: a  reason: collision with root package name */
    public static final String f9392a = "a";
    public static AbstractC1876bg b;
    public static AbstractC2182dR c;
    public static final XL0[] d = {XL0.ACRA_REPORT_TYPE, XL0.REPORT_ID, XL0.APP_VERSION_CODE, XL0.APP_VERSION_NAME, XL0.APP_INSTALL_TIME, XL0.APP_UPGRADE_TIME, XL0.PACKAGE_NAME, XL0.FILE_PATH, XL0.PHONE_MODEL, XL0.BRAND, XL0.PRODUCT, XL0.ANDROID_VERSION, XL0.OS_VERSION, XL0.BUILD, XL0.BUILD_HOST, XL0.TOTAL_MEM_SIZE, XL0.AVAILABLE_MEM_SIZE, XL0.CUSTOM_DATA, XL0.STACK_TRACE, XL0.DEVICE, XL0.CRASH_CONFIGURATION, XL0.DISPLAY, XL0.USER_APP_START_DATE, XL0.USER_CRASH_DATE, XL0.DUMPSYS_MEMINFO, XL0.DROPBOX, XL0.LOGCAT, XL0.EVENTSLOG, XL0.RADIOLOG, XL0.DEVICE_ID, XL0.INSTALLATION_ID, XL0.DEVICE_FEATURES, XL0.ENVIRONMENT, XL0.SETTINGS_SYSTEM, XL0.SETTINGS_SECURE, XL0.PROCESS_NAME, XL0.PROCESS_NAME_BY_AMS, XL0.ACTIVITY_LOG, XL0.JAIL_BROKEN, XL0.PROCESS_UPTIME, XL0.DEVICE_UPTIME, XL0.ACRA_REPORT_FILENAME, XL0.EXCEPTION_CAUSE, XL0.REPORT_LOAD_THROW, XL0.MINIDUMP, XL0.ANDROID_ID, XL0.UID, XL0.UPLOADED_BY_PROCESS, XL0.OPEN_FD_COUNT, XL0.OPEN_FD_SOFT_LIMIT, XL0.OPEN_FD_HARD_LIMIT, XL0.IS_LOW_RAM_DEVICE, XL0.SIGQUIT, XL0.LARGE_MEM_HEAP, XL0.ANDROID_RUNTIME, XL0.MINIDUMP_EXCLUDE_REASON, XL0.ATTACHMENT_ORIGINAL_SIZE, XL0.LAST_URL_VISITED, XL0.LAST_URL_VISITED_TIME, XL0.TIME_OF_CRASH, XL0.WEBVIEW_VERSION, XL0.LAST_ACTIVITY_LOGGED, XL0.LAST_ACTIVITY_LOGGED_TIME, XL0.IAB_OPEN_TIMES, XL0.RUNTIME_PERMISSIONS};
    public static final XL0[] e = {XL0.ACRA_REPORT_TYPE, XL0.REPORT_ID, XL0.APP_VERSION_CODE, XL0.APP_VERSION_NAME, XL0.APP_INSTALL_TIME, XL0.APP_UPGRADE_TIME, XL0.PACKAGE_NAME, XL0.FILE_PATH, XL0.PHONE_MODEL, XL0.BRAND, XL0.PRODUCT, XL0.ANDROID_VERSION, XL0.OS_VERSION, XL0.BUILD, XL0.BUILD_HOST, XL0.TOTAL_MEM_SIZE, XL0.AVAILABLE_MEM_SIZE, XL0.CUSTOM_DATA, XL0.STACK_TRACE, XL0.DEVICE, XL0.CRASH_CONFIGURATION, XL0.DISPLAY, XL0.USER_APP_START_DATE, XL0.USER_CRASH_DATE, XL0.DUMPSYS_MEMINFO, XL0.DROPBOX, XL0.DEVICE_ID, XL0.INSTALLATION_ID, XL0.DEVICE_FEATURES, XL0.ENVIRONMENT, XL0.SETTINGS_SYSTEM, XL0.SETTINGS_SECURE, XL0.PROCESS_NAME, XL0.PROCESS_NAME_BY_AMS, XL0.ACTIVITY_LOG, XL0.JAIL_BROKEN, XL0.PROCESS_UPTIME, XL0.DEVICE_UPTIME, XL0.ACRA_REPORT_FILENAME, XL0.EXCEPTION_CAUSE, XL0.REPORT_LOAD_THROW, XL0.MINIDUMP, XL0.ANDROID_ID, XL0.UID, XL0.UPLOADED_BY_PROCESS, XL0.IS_LOW_RAM_DEVICE, XL0.LARGE_MEM_HEAP, XL0.ANDROID_RUNTIME, XL0.ATTACHMENT_ORIGINAL_SIZE, XL0.WEBVIEW_VERSION, XL0.RUNTIME_PERMISSIONS};

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e2) {
                Log.e("ACRA", "Error while closing stream: ", e2);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v0, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.FileOutputStream] */
    /* JADX WARN: Type inference failed for: r7v8 */
    /* JADX WARN: Type inference failed for: r7v9 */
    /* JADX WARN: Type inference failed for: r7v10 */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0174, code lost:
        if (r7 != null) goto L_0x0176;
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00e6 A[Catch:{ Exception -> 0x0127 }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00fc A[Catch:{ Exception -> 0x0127 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x0141 A[Catch:{ IOException -> 0x016b, all -> 0x0169 }] */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x014e A[Catch:{ IOException -> 0x016b, all -> 0x0169 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x014f A[Catch:{ IOException -> 0x016b, all -> 0x0169 }] */
    /* JADX WARNING: Removed duplicated region for block: B:58:0x017f A[SYNTHETIC, Splitter:B:58:0x017f] */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x0202 A[Catch:{ IOException -> 0x0228 }] */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static defpackage.UL b(defpackage.AbstractC1876bg r10, java.lang.String r11, boolean r12) {
        /*
        // Method dump skipped, instructions count: 573
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.AbstractC1585a.b(bg, java.lang.String, boolean):UL");
    }
}
