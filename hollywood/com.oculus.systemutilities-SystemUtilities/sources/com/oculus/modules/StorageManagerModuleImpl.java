package com.oculus.modules;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;
import com.oculus.modules.codegen.Resolver;
import com.oculus.modules.codegen.StorageManagerModule;
import com.oculus.os.pm.OculusPackageStats;
import com.oculus.os.pm.PackageManager;

public class StorageManagerModuleImpl extends StorageManagerModule {
    private static final String TAG = StorageManagerModuleImpl.class.getSimpleName();
    private final Context mContext;

    public StorageManagerModuleImpl(Context context) {
        this.mContext = context;
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.StorageManagerModule
    public void getAvailableBytesImpl(Resolver<Double> resolver) {
        long bytesAvailable = 0;
        if (isStorageMounted()) {
            Log.d(TAG, "Storage is mounted");
            bytesAvailable = new StatFs(Environment.getExternalStorageDirectory().getPath()).getAvailableBytes();
        } else {
            Log.w(TAG, "Storage is not mounted");
        }
        Log.d(TAG, "getAvailableBytes returning: " + bytesAvailable);
        resolver.resolve(new Double((double) bytesAvailable));
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.StorageManagerModule
    public void getTotalBytesImpl(Resolver<Double> resolver) {
        long bytesTotal = 0;
        if (isStorageMounted()) {
            Log.d(TAG, "Storage is mounted");
            bytesTotal = new StatFs(Environment.getExternalStorageDirectory().getPath()).getTotalBytes();
        } else {
            Log.w(TAG, "Storage is not mounted");
        }
        Log.d(TAG, "getTotalBytes returning: " + bytesTotal);
        resolver.resolve(new Double((double) bytesTotal));
    }

    private boolean isStorageMounted() {
        String state = Environment.getExternalStorageState();
        return "mounted".equals(state) || "mounted_ro".equals(state);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.modules.codegen.StorageManagerModule
    public void getAppSizeInBytesImpl(String packageName, Resolver<Double> resolver) {
        try {
            OculusPackageStats stats = PackageManager.getInstance().getPackageSizeInfo(this.mContext, packageName).get();
            long size = stats.cacheSize + stats.codeSize + stats.dataSize + stats.externalCacheSize + stats.externalCodeSize + stats.externalDataSize + stats.externalMediaSize + stats.externalObbSize;
            Log.d(TAG, "getAppSizeInBytes for " + packageName + " returning: " + size);
            resolver.resolve(new Double((double) size));
        } catch (Exception e) {
            Log.e(TAG, "Error getting app size", e);
            resolver.reject(e);
        }
    }
}
