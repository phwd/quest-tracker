package org.chromium.base;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.text.TextUtils;
import android.util.Log;
import java.io.IOException;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ApkAssets {
    public static long[] open(String str, String str2) {
        AssetFileDescriptor assetFileDescriptor = null;
        try {
            Context applicationContext = ContextUtils.getApplicationContext();
            if (!TextUtils.isEmpty(str2) && BundleUtils.c(applicationContext, str2)) {
                applicationContext = BundleUtils.a(applicationContext, str2);
            }
            AssetFileDescriptor openNonAssetFd = applicationContext.getAssets().openNonAssetFd(str);
            long[] jArr = {(long) openNonAssetFd.getParcelFileDescriptor().detachFd(), openNonAssetFd.getStartOffset(), openNonAssetFd.getLength()};
            try {
                openNonAssetFd.close();
            } catch (IOException e) {
                Log.e("ApkAssets", "Unable to close AssetFileDescriptor", e);
            }
            return jArr;
        } catch (IOException e2) {
            if (!e2.getMessage().equals("") && !e2.getMessage().equals(str)) {
                Log.e("ApkAssets", "Error while loading asset " + str + ": " + e2);
            }
            long[] jArr2 = {-1, -1, -1};
            if (0 != 0) {
                try {
                    assetFileDescriptor.close();
                } catch (IOException e3) {
                    Log.e("ApkAssets", "Unable to close AssetFileDescriptor", e3);
                }
            }
            return jArr2;
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    assetFileDescriptor.close();
                } catch (IOException e4) {
                    Log.e("ApkAssets", "Unable to close AssetFileDescriptor", e4);
                }
            }
            throw th;
        }
    }
}
