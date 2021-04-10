package com.facebook.acra;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;

public final class NativeCrashDumpReporterUtil {
    static void updateProcessedTime(File file, long j) throws IOException {
        if (!file.exists()) {
            new FileOutputStream(file).close();
        } else {
            file.setLastModified(j);
        }
    }

    @SuppressLint({"MissingPermission"})
    static boolean isConnectedToWifi(Context context) {
        NetworkInfo networkInfo;
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
            return (connectivityManager == null || (networkInfo = connectivityManager.getNetworkInfo(1)) == null || !networkInfo.isConnected()) ? false : true;
        } catch (Exception unused) {
        }
    }

    static void removeFatMinidump(Context context) {
        File[] listFiles = context.getDir("minidumps", 0).listFiles(new FilenameFilter() {
            /* class com.facebook.acra.NativeCrashDumpReporterUtil.AnonymousClass1 */

            public final boolean accept(File file, String str) {
                return str.endsWith("extra-maps.fatdmp");
            }
        });
        if (listFiles != null) {
            for (File file : listFiles) {
                file.delete();
            }
        }
    }
}
