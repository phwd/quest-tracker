package com.oculus.trex;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

public class TRexUtils {
    private static final String TAG = "TREX";

    public static Context createPluginPackageContext(Context ctx, String pluginPackageName) {
        try {
            return ctx.createPackageContext(pluginPackageName, 3);
        } catch (PackageManager.NameNotFoundException e) {
            Log.d(TAG, "createPluginPackageContext: " + pluginPackageName + " not found.");
            return null;
        } catch (SecurityException e2) {
            Log.e(TAG, "createPluginPackageContext: Security exception for " + pluginPackageName);
            return null;
        }
    }

    public static boolean isPackageAllowed(Context ctx, String pluginPackageName) {
        return PackageVerifier.isPackageAllowed(ctx, pluginPackageName);
    }
}
