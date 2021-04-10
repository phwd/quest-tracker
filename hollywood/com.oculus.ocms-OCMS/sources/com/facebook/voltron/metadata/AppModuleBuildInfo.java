package com.facebook.voltron.metadata;

import android.content.Context;
import com.facebook.debug.log.BLog;
import com.facebook.infer.annotation.Nullsafe;
import java.io.IOException;
import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class AppModuleBuildInfo {
    private static final String TAG = "AppModuleBuildInfo";
    @GuardedBy("AppModuleBuildInfo.class")
    @Nullable
    private static Boolean sHasDownloadableMetadata;

    private AppModuleBuildInfo() {
    }

    public static synchronized boolean hasDownloadableMetadata(Context context) {
        boolean booleanValue;
        synchronized (AppModuleBuildInfo.class) {
            if (sHasDownloadableMetadata == null) {
                try {
                    sHasDownloadableMetadata = false;
                    context.getAssets().open("app_modules.json").close();
                    sHasDownloadableMetadata = true;
                } catch (IOException e) {
                    BLog.d(TAG, "Downloadable metadata not found", (Throwable) e);
                }
            }
            booleanValue = sHasDownloadableMetadata.booleanValue();
        }
        return booleanValue;
    }
}
