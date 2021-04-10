package com.oculus.util.device;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import com.facebook.androidinternals.android.os.SystemPropertiesInternal;
import com.facebook.debug.log.BLog;
import com.facebook.inject.BundledAndroidModule;
import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.UL;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.UnmodifiableIterator;
import com.oculus.common.build.BuildConstants;
import com.oculus.os.Version;
import com.oculus.util.device.DeviceModule;
import java.io.File;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Provider;

@TargetApi(Version.VERSION_21)
public class DeviceUtils {
    private static final ImmutableSet<String> ALL_MODELS = ImmutableSet.builder().add((Object[]) new String[]{"SM-N910", "SM-N916", "SC-05G", "SC-04G", "SCV31", "404SC", "SM-G920", "SM-G925"}).build();
    private static final int DEFAULT_THRESHOLD_MAX_BYTES = 524288000;
    private static final int DEFAULT_THRESHOLD_PERCENTAGE = 10;
    private static final String EXTERNAL_STORAGE_FILE_PATH = Environment.getExternalStorageDirectory().getPath();
    private static final String FAKE_MODEL_PLACEHOLDER = "fake_placeholder";
    private static final ImmutableSet<String> JAPAN_MODELS = ImmutableSet.builder().add((Object[]) new String[]{"SC-05G", "SC-04G", "SCV31", "404SC"}).build();
    private static final String MODEL_DEBUG_FILE_PATH = (EXTERNAL_STORAGE_FILE_PATH + "/oculus_model.test");
    private static final ImmutableSet<String> NOTE4_MODELS = ImmutableSet.builder().add((Object[]) new String[]{"SM-N910", "SM-N916"}).build();
    private static final String OCULUS_DIR = "Oculus";
    private static final String STORAGE_THRESHOLD_MAX_BYTES = "sys_storage_threshold_max_bytes";
    private static final String STORAGE_THRESHOLD_PERCENTAGE = "sys_storage_threshold_percentage";
    private static final String TAG = DeviceUtils.class.getSimpleName();
    private static String mFakeModel;
    @UnsafeContextInjection
    @Inject
    private final Context mContext;

    public static final Lazy $ul_$xXXcom_facebook_inject_Lazy$x3Ccom_oculus_util_device_DeviceUtils$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightLazy.get(DeviceModule.UL_id.$ul_$xXXcom_oculus_util_device_DeviceUtils$xXXBINDING_ID, $ul_injector);
    }

    public static final DeviceUtils $ul_$xXXcom_oculus_util_device_DeviceUtils$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return (DeviceUtils) UL.factorymap.get(DeviceModule.UL_id.$ul_$xXXcom_oculus_util_device_DeviceUtils$xXXBINDING_ID, $ul_injector);
    }

    public static final DeviceUtils $ul_$xXXcom_oculus_util_device_DeviceUtils$xXXFACTORY_METHOD(InjectorLike $ul_injector) {
        return new DeviceUtils($ul_injector);
    }

    public static final Provider $ul_$xXXjavax_inject_Provider$x3Ccom_oculus_util_device_DeviceUtils$x3E$xXXACCESS_METHOD(InjectorLike $ul_injector) {
        return UltralightProvider.get(DeviceModule.UL_id.$ul_$xXXcom_oculus_util_device_DeviceUtils$xXXBINDING_ID, $ul_injector);
    }

    @Inject
    public DeviceUtils(InjectorLike $ul_injector) {
        this.mContext = BundledAndroidModule.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_UnsafeContextInjection$xXXACCESS_METHOD($ul_injector);
    }

    public boolean isSDCardAvailable() {
        return getSDCardDirectory(this.mContext) != null;
    }

    public boolean isStandAloneDevice() {
        return this.mContext.getPackageManager().hasSystemFeature(BuildConstants.PACKAGE_NAME_OVR_STANDALONE);
    }

    public static String getDeviceId(Context context) {
        String deviceID = Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (deviceID == null) {
            deviceID = "";
        }
        return Strings.padStart(deviceID, 16, '0');
    }

    private static boolean isDirectoryMounted(File path) {
        if (path == null) {
            return false;
        }
        String directoryState = Environment.getExternalStorageState(path);
        if ("mounted".equals(directoryState) || "mounted_ro".equals(directoryState)) {
            return true;
        }
        return false;
    }

    public boolean isSDCardAvailable(Context context) {
        return getSDCardDirectory(context) != null;
    }

    public static boolean isOculusSDCardInserted() {
        String sdDirPath = System.getenv("SECONDARY_STORAGE");
        if (sdDirPath == null) {
            BLog.e(TAG, "SD card path is invalid");
            return false;
        }
        File sdDir = new File(sdDirPath);
        if (!sdDir.exists() || !sdDir.canRead()) {
            BLog.e(TAG, "SD card could not be read");
            return false;
        }
        File oculusDir = new File(sdDirPath + "/" + OCULUS_DIR);
        if (oculusDir.exists() && oculusDir.canRead()) {
            return true;
        }
        BLog.e(TAG, "Oculus directory could not be read");
        return false;
    }

    public static boolean isOculusSDCardInsertedRequired() {
        return isNote4Device() && !isAndroidMarshmallowOrNewer() && !isOculusSDCardInserted();
    }

    private static boolean isAndroidMarshmallowOrNewer() {
        return Build.VERSION.SDK_INT >= 23;
    }

    public static boolean isNote4Device() {
        return isOneOfTheModels(NOTE4_MODELS, getBuildModel());
    }

    public static String getBuildModel() {
        if (Strings.isNullOrEmpty(mFakeModel)) {
            mFakeModel = initializeFakeModel();
        }
        return FAKE_MODEL_PLACEHOLDER.equals(mFakeModel) ? Build.MODEL : mFakeModel;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0043 A[SYNTHETIC, Splitter:B:21:0x0043] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x004c A[SYNTHETIC, Splitter:B:26:0x004c] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String initializeFakeModel() {
        /*
            java.io.File r1 = new java.io.File
            java.lang.String r5 = com.oculus.util.device.DeviceUtils.MODEL_DEBUG_FILE_PATH
            r1.<init>(r5)
            r4 = 0
            boolean r5 = r1.exists()
            if (r5 == 0) goto L_0x0050
            boolean r5 = r1.isDirectory()
            if (r5 != 0) goto L_0x0050
            r2 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0040, all -> 0x0049 }
            r3.<init>(r1)     // Catch:{ IOException -> 0x0040, all -> 0x0049 }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0058, all -> 0x0055 }
            java.io.InputStreamReader r5 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0058, all -> 0x0055 }
            r5.<init>(r3)     // Catch:{ IOException -> 0x0058, all -> 0x0055 }
            r0.<init>(r5)     // Catch:{ IOException -> 0x0058, all -> 0x0055 }
            java.lang.String r4 = r0.readLine()     // Catch:{ IOException -> 0x0058, all -> 0x0055 }
            r0.close()     // Catch:{ IOException -> 0x0058, all -> 0x0055 }
            if (r3 == 0) goto L_0x0030
            r3.close()     // Catch:{ IOException -> 0x003d }
        L_0x0030:
            r2 = r3
        L_0x0031:
            if (r4 == 0) goto L_0x0050
            com.google.common.collect.ImmutableSet<java.lang.String> r5 = com.oculus.util.device.DeviceUtils.ALL_MODELS
            boolean r5 = isOneOfTheModels(r5, r4)
            if (r5 == 0) goto L_0x0050
            r5 = r4
        L_0x003c:
            return r5
        L_0x003d:
            r5 = move-exception
            r2 = r3
            goto L_0x0031
        L_0x0040:
            r5 = move-exception
        L_0x0041:
            if (r2 == 0) goto L_0x0031
            r2.close()     // Catch:{ IOException -> 0x0047 }
            goto L_0x0031
        L_0x0047:
            r5 = move-exception
            goto L_0x0031
        L_0x0049:
            r5 = move-exception
        L_0x004a:
            if (r2 == 0) goto L_0x004f
            r2.close()     // Catch:{ IOException -> 0x0053 }
        L_0x004f:
            throw r5
        L_0x0050:
            java.lang.String r5 = "fake_placeholder"
            goto L_0x003c
        L_0x0053:
            r6 = move-exception
            goto L_0x004f
        L_0x0055:
            r5 = move-exception
            r2 = r3
            goto L_0x004a
        L_0x0058:
            r5 = move-exception
            r2 = r3
            goto L_0x0041
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.util.device.DeviceUtils.initializeFakeModel():java.lang.String");
    }

    public static boolean isJapanS6Device() {
        if (isOneOfTheModels(JAPAN_MODELS, getBuildModel())) {
            return true;
        }
        return false;
    }

    public static boolean isOneOfTheModels(ImmutableSet<String> models, String actualModel) {
        UnmodifiableIterator<String> it = models.iterator();
        while (it.hasNext()) {
            if (actualModel.contains(it.next())) {
                return true;
            }
        }
        return false;
    }

    public long getAvailableInternalMemory() {
        return getAvailableDiskSpace(Environment.getDataDirectory());
    }

    public long getAvailableExternalStorageMemory() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory != null) {
            return getAvailableDiskSpace(externalStorageDirectory);
        }
        return 0;
    }

    public long getAvailableSDCardMemory() {
        File SDCardDirectory = getSDCardDirectory(this.mContext);
        if (SDCardDirectory != null) {
            return getAvailableDiskSpace(SDCardDirectory);
        }
        return 0;
    }

    public long getMinimumInternalMemoryInstallationTreshold() {
        int thresholdPercentage = Settings.Secure.getInt(this.mContext.getContentResolver(), STORAGE_THRESHOLD_PERCENTAGE, 10);
        int maxThresholdSize = Settings.Secure.getInt(this.mContext.getContentResolver(), STORAGE_THRESHOLD_MAX_BYTES, DEFAULT_THRESHOLD_MAX_BYTES);
        return Math.min((long) maxThresholdSize, (long) ((((float) thresholdPercentage) * ((float) getAvailableInternalMemory())) / 100.0f));
    }

    public long getAvailableDiskSpace(String directory) {
        return getAvailableDiskSpace(new File(directory));
    }

    public long getAvailableDiskSpace(File directory) {
        return new StatFs(directory.getPath()).getAvailableBytes();
    }

    @Nullable
    private static File getSDCardDirectory(Context context) {
        File[] allExternalDirectories = context.getExternalFilesDirs(null);
        for (File externalDir : allExternalDirectories) {
            if (isDirectoryMounted(externalDir) && Environment.isExternalStorageRemovable(externalDir)) {
                return externalDir;
            }
        }
        return null;
    }

    @SuppressLint({"InstanceMethodCanBeStatic"})
    public boolean areDeviceIdentityFeaturesDisabled() {
        return SystemPropertiesInternal.get("debug.oculus.disable_device_identity_features").equals("1");
    }
}
