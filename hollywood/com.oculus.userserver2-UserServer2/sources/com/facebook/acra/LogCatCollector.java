package com.facebook.acra;

import X.Mi;
import android.content.Context;
import android.util.Base64;
import com.facebook.acra.config.AcraReportingConfig;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;
import javax.annotation.Nullable;

public class LogCatCollector {
    public static final String COMPRESS_NEWLINE = "\\n";
    public static final String NEWLINE = "\n";
    public static final String TAG = "LogCatCollector";
    public static final String UTF_8_ENCODING = "UTF-8";

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0082  */
    /* JADX WARNING: Removed duplicated region for block: B:23:? A[RETURN, SYNTHETIC] */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String collectLogCatBySpawningOtherProcess(java.lang.String[] r6, @javax.annotation.Nullable java.lang.String r7, java.lang.String r8) {
        /*
        // Method dump skipped, instructions count: 135
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.LogCatCollector.collectLogCatBySpawningOtherProcess(java.lang.String[], java.lang.String, java.lang.String):java.lang.String");
    }

    @Nullable
    public static String compressBase64(String str) {
        if (!(str == null || str.length() == 0)) {
            try {
                byte[] bytes = str.getBytes(UTF_8_ENCODING);
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                gZIPOutputStream.write(bytes);
                gZIPOutputStream.close();
                return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
            } catch (IOException e) {
                Mi.A06(TAG, e, "Failed to compress string");
            }
        }
        return null;
    }

    @Nullable
    public static String getLogcatFileContent(String str) {
        File file = new File(str);
        StringBuilder sb = new StringBuilder();
        try {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine);
                    sb.append('\n');
                }
                bufferedReader.close();
            } catch (IOException e) {
                Mi.A06(TAG, e, "Could not close LogcatInterceptor buffer reader");
            }
        } catch (FileNotFoundException e2) {
            Mi.A06(TAG, e2, "Could not find LogcatInterceptor file");
        }
        return sb.toString();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0033, code lost:
        if (r0 == null) goto L_0x0035;
     */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String collectLogCat(android.content.Context r3, com.facebook.acra.config.AcraReportingConfig r4, @javax.annotation.Nullable java.lang.String r5, @javax.annotation.Nullable java.lang.String r6, boolean r7, boolean r8, boolean r9) {
        /*
            r2 = 0
            if (r8 != 0) goto L_0x001d
            java.lang.String r0 = "acraconfig_logcat_interceptor_after_crash_enabled"
            boolean r0 = X.NP.A01(r3, r0)
            if (r0 == 0) goto L_0x001d
            if (r5 == 0) goto L_0x0015
            java.lang.String r0 = "main"
            boolean r0 = r5.equals(r0)
            if (r0 == 0) goto L_0x001d
        L_0x0015:
            if (r6 == 0) goto L_0x001d
            java.lang.String r0 = getLogcatFileContent(r6)
            if (r0 != 0) goto L_0x0039
        L_0x001d:
            java.lang.String r1 = "acraconfig_avoid_spawn_process_to_collect_logcat"
            r0 = 0
            int r1 = X.NP.A00(r3, r1, r0)
            r0 = 1
            if (r1 == r0) goto L_0x0035
            java.lang.String[] r1 = r4.logcatArguments(r9)
            if (r7 == 0) goto L_0x0036
            java.lang.String r0 = "\\n"
        L_0x002f:
            java.lang.String r0 = collectLogCatBySpawningOtherProcess(r1, r5, r0)
            if (r0 != 0) goto L_0x0039
        L_0x0035:
            return r2
        L_0x0036:
            java.lang.String r0 = "\n"
            goto L_0x002f
        L_0x0039:
            if (r7 == 0) goto L_0x003f
            java.lang.String r0 = compressBase64(r0)
        L_0x003f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.LogCatCollector.collectLogCat(android.content.Context, com.facebook.acra.config.AcraReportingConfig, java.lang.String, java.lang.String, boolean, boolean, boolean):java.lang.String");
    }

    @Nullable
    public static String collectLogCat(Context context, AcraReportingConfig acraReportingConfig, @Nullable String str, boolean z) {
        return collectLogCat(context, acraReportingConfig, str, null, z, false, false);
    }

    @Nullable
    public static String collectLogCat(Context context, AcraReportingConfig acraReportingConfig, String str, boolean z, boolean z2) {
        return collectLogCat(context, acraReportingConfig, str, null, z, z2, false);
    }
}
