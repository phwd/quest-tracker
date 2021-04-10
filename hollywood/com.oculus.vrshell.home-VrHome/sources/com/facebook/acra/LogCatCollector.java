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
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
public class LogCatCollector {
    private static final String TAG = LogCatCollector.class.getSimpleName();

    LogCatCollector() {
    }

    @Nullable
    protected static String collectLogCat(Context ctx, AcraReportingConfig config, @Nullable String bufferName, boolean doCompress) {
        return collectLogCat(ctx, config, bufferName, null, doCompress, false, false);
    }

    @Nullable
    protected static String getLogcatFileContent(String fileName) {
        File logcatFile = new File(fileName);
        StringBuilder text = new StringBuilder();
        try {
            FileReader fileReader = new FileReader(logcatFile);
            if (fileReader != null) {
                try {
                    BufferedReader br = new BufferedReader(fileReader);
                    while (true) {
                        String line = br.readLine();
                        if (line == null) {
                            break;
                        }
                        text.append(line);
                        text.append('\n');
                    }
                    br.close();
                } catch (IOException e) {
                    BLog.e(TAG, e, "Could not close LogcatInterceptor buffer reader");
                }
            }
        } catch (FileNotFoundException e2) {
            BLog.e(TAG, e2, "Could not find LogcatInterceptor file");
            if (0 != 0) {
                try {
                    BufferedReader br2 = new BufferedReader(null);
                    while (true) {
                        String line2 = br2.readLine();
                        if (line2 == null) {
                            break;
                        }
                        text.append(line2);
                        text.append('\n');
                    }
                    br2.close();
                } catch (IOException e3) {
                    BLog.e(TAG, e3, "Could not close LogcatInterceptor buffer reader");
                }
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    BufferedReader br3 = new BufferedReader(null);
                    while (true) {
                        String line3 = br3.readLine();
                        if (line3 == null) {
                            break;
                        }
                        text.append(line3);
                        text.append('\n');
                    }
                    br3.close();
                } catch (IOException e4) {
                    BLog.e(TAG, e4, "Could not close LogcatInterceptor buffer reader");
                }
            }
            throw th;
        }
        return text.toString();
    }

    @Nullable
    protected static String collectLogCat(Context ctx, AcraReportingConfig config, @Nullable String bufferName, @Nullable String logcatFilePath, boolean doCompress, boolean collectLogcatFromExternalProcess, boolean maxNumLines) {
        String rawLogcat = null;
        if (!collectLogcatFromExternalProcess && AcraConfig.isLogcatInterceptorAfterCrashEnabled(ctx) && ((bufferName == null || bufferName.equals("main")) && logcatFilePath != null)) {
            rawLogcat = getLogcatFileContent(logcatFilePath);
        }
        if (rawLogcat == null && Build.VERSION.SDK_INT >= 16 && !AcraConfig.avoidSpawnProcessToCollectLogcat(ctx)) {
            rawLogcat = collectLogCatBySpawningOtherProcess(config.logcatArguments(maxNumLines), bufferName, doCompress ? "\\n" : "\n");
        }
        if (rawLogcat == null) {
            return null;
        }
        if (doCompress) {
            return compressBase64(rawLogcat);
        }
        return rawLogcat;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x007f A[ORIG_RETURN, RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x008a  */
    @javax.annotation.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String collectLogCatBySpawningOtherProcess(java.lang.String[] r12, @javax.annotation.Nullable java.lang.String r13, java.lang.String r14) {
        /*
        // Method dump skipped, instructions count: 146
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.acra.LogCatCollector.collectLogCatBySpawningOtherProcess(java.lang.String[], java.lang.String, java.lang.String):java.lang.String");
    }

    @Nullable
    protected static String compressBase64(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        BLog.d(TAG, "Precompression logcat size: %d", Integer.valueOf(str.length()));
        try {
            byte[] strByte = str.getBytes("UTF-8");
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(out);
            gzip.write(strByte);
            gzip.close();
            String compressedLogcat = Base64.encodeToString(out.toByteArray(), 2);
            BLog.d(TAG, "After compression logcat size: %d", Integer.valueOf(compressedLogcat.length()));
            return compressedLogcat;
        } catch (IOException ex) {
            BLog.e(TAG, ex, "Failed to compress string");
            return null;
        }
    }
}
