package com.facebook.common.manifest;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.DeadObjectException;
import android.util.Log;
import javax.annotation.Nullable;
import javax.inject.Inject;

public class ManifestReader {
    private final Context mContext;

    @Inject
    public ManifestReader(Context context) {
        this.mContext = context;
    }

    @Nullable
    public String getMetaDataValueForKey(String str, String str2) {
        Object obj;
        try {
            ApplicationInfo applicationInfo = this.mContext.getPackageManager().getApplicationInfo(str2, 128);
            if (applicationInfo == null || applicationInfo.metaData == null || (obj = applicationInfo.metaData.get(str)) == null) {
                return null;
            }
            return obj.toString();
        } catch (PackageManager.NameNotFoundException e) {
            reportException(e);
            return null;
        } catch (RuntimeException e2) {
            if (e2.getCause() instanceof DeadObjectException) {
                reportException(e2);
                return null;
            }
            throw e2;
        }
    }

    private static void reportException(Exception exc) {
        Log.e(ManifestReader.class.getName(), "Error reading <meta-data> from AndroidManifest.xml.", exc);
    }
}
