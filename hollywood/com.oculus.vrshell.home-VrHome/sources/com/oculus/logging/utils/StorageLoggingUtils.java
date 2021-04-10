package com.oculus.logging.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.TextUtils;
import com.facebook.debug.log.BLog;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.FbInjector;
import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.UL;
import com.oculus.appmanager.info.ApkUpdateInfoContract;
import com.oculus.logging.utils.UtilsModule;
import com.oculus.os.Version;
import java.io.File;
import javax.annotation.Nullable;
import javax.inject.Provider;

public class StorageLoggingUtils {
    private static final Integer BYTES_PER_MEGABYTE = Integer.valueOf((int) ApkUpdateInfoContract.UPDATE_TYPE_FULL);
    public static final String FREE_SPACE_INTERNAL = "free_space_internal";
    public static final String FREE_SPACE_SD = "free_space_sd";
    private static final String TAG = StorageLoggingUtils.class.getSimpleName();
    private InjectionContext $ul_mInjectionContext;

    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Ccom_oculus_logging_utils_StorageLoggingUtils$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(UtilsModule.UL_id.$ul_$xXXcom_oculus_logging_utils_StorageLoggingUtils$xXXBINDING_ID, $ul_injector);
    }

    public static final StorageLoggingUtils $ul_$xXXcom_oculus_logging_utils_StorageLoggingUtils$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (StorageLoggingUtils) UL.factorymap.get(UtilsModule.UL_id.$ul_$xXXcom_oculus_logging_utils_StorageLoggingUtils$xXXBINDING_ID, $ul_injector);
    }

    public static final StorageLoggingUtils $ul_$xXXcom_oculus_logging_utils_StorageLoggingUtils$xXXFACTORY_METHOD(InjectorLike $ul_injector) {
        return new StorageLoggingUtils($ul_injector);
    }

    public static final Provider $ul_$xXXjavax_inject_Provider$x3Ccom_oculus_logging_utils_StorageLoggingUtils$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(UtilsModule.UL_id.$ul_$xXXcom_oculus_logging_utils_StorageLoggingUtils$xXXBINDING_ID, $ul_injector);
    }

    public StorageLoggingUtils(InjectorLike $ul_injector) {
        this.$ul_mInjectionContext = new InjectionContext(1, $ul_injector);
    }

    @Nullable
    public Long getInternalFreeSpace() {
        return getFreeSpace(((Context) FbInjector.lazyInstance(0, BundledAndroidModule.UL_id.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_UnsafeContextInjection$xXXBINDING_ID, this.$ul_mInjectionContext)).getFilesDir().getAbsolutePath());
    }

    @Nullable
    public Long getSDFreeSpace() {
        return getFreeSpace(Environment.getExternalStorageDirectory().getAbsolutePath());
    }

    @Nullable
    @TargetApi(Version.VERSION_18)
    private static Long getFreeSpace(String path) {
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        File file = new File(path);
        if (!file.exists() || !file.canRead()) {
            BLog.d(TAG, "Got path to SD card [%s] but location doesn't exist", path);
            return null;
        }
        try {
            StatFs stats = new StatFs(path);
            return Long.valueOf((stats.getAvailableBlocksLong() * stats.getBlockSizeLong()) / ((long) BYTES_PER_MEGABYTE.intValue()));
        } catch (RuntimeException e) {
            BLog.d(TAG, e, "Got an exception while running statfs on the SD card [%s]", path);
            return null;
        }
    }
}
