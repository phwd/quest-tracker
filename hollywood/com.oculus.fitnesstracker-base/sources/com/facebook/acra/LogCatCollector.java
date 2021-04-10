package com.facebook.acra;

import android.content.Context;
import android.os.Build;
import android.util.Base64;
import com.facebook.acra.config.AcraReportingConfig;
import com.facebook.acraconfig.AcraConfig;
import com.facebook.debug.log.BLog;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/* access modifiers changed from: package-private */
public class LogCatCollector {
    private static final String TAG = "LogCatCollector";

    LogCatCollector() {
    }

    protected static String collectLogCat(Context context, AcraReportingConfig acraReportingConfig, String str, boolean z) {
        return collectLogCat(context, acraReportingConfig, str, null, false, false, false);
    }

    private static String getLogcatFileContent(String str) {
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
                BLog.e(TAG, e, "Could not close LogcatInterceptor buffer reader");
            }
        } catch (FileNotFoundException e2) {
            BLog.e(TAG, e2, "Could not find LogcatInterceptor file");
        }
        return sb.toString();
    }

    protected static String collectLogCat(Context context, AcraReportingConfig acraReportingConfig, String str, String str2, boolean z, boolean z2, boolean z3) {
        String logcatFileContent = (z2 || !AcraConfig.isLogcatInterceptorAfterCrashEnabled(context) || (str != null && !str.equals("main")) || str2 == null) ? null : getLogcatFileContent(str2);
        if (logcatFileContent == null && Build.VERSION.SDK_INT >= 16 && !AcraConfig.avoidSpawnProcessToCollectLogcat(context)) {
            logcatFileContent = collectLogCatBySpawningOtherProcess(acraReportingConfig.logcatArguments(z3), str, z ? "\\n" : "\n");
        }
        if (logcatFileContent == null) {
            return null;
        }
        return z ? compressBase64(logcatFileContent) : logcatFileContent;
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0089 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x008a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String collectLogCatBySpawningOtherProcess(java.lang.String[] r4, java.lang.String r5, java.lang.String r6) {
        /*
        // Method dump skipped, instructions count: 143
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.LogCatCollector.collectLogCatBySpawningOtherProcess(java.lang.String[], java.lang.String, java.lang.String):java.lang.String");
    }

    private static String compressBase64(String str) {
        if (!(str == null || str.length() == 0)) {
            BLog.d(TAG, "Precompression logcat size: %d", Integer.valueOf(str.length()));
            try {
                byte[] bytes = str.getBytes("UTF-8");
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                gZIPOutputStream.write(bytes);
                gZIPOutputStream.close();
                String encodeToString = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2);
                BLog.d(TAG, "After compression logcat size: %d", Integer.valueOf(encodeToString.length()));
                return encodeToString;
            } catch (IOException e) {
                BLog.e(TAG, e, "Failed to compress string");
            }
        }
        return null;
    }
}
