package com.oculus.clay.app;

import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.util.Log;

public final class AndroidAssetHelper {
    public static AssetManager tryGetAssetManagerForPackage(PackageManager packageManager, String str) {
        try {
            return packageManager.getResourcesForApplication(str).getAssets();
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("Clay", "AndroidAssetHelper threw IOException from packageManager.getResourcesForApplication", e);
            return null;
        }
    }
}
