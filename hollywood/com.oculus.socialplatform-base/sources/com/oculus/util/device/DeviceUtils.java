package com.oculus.util.device;

import X.AbstractC03180ld;
import X.AbstractC05710wh;
import X.AnonymousClass006;
import X.AnonymousClass0GR;
import X.AnonymousClass0Hr;
import X.AnonymousClass0MD;
import X.AnonymousClass0NC;
import X.AnonymousClass0VC;
import X.AnonymousClass0lg;
import X.AnonymousClass1TK;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import com.facebook.inject.UnsafeContextInjection;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Eager;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import java.io.File;
import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Provider;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID"})
@TargetApi(21)
public class DeviceUtils {
    public static final ImmutableSet<String> ALL_MODELS;
    public static final int DEFAULT_THRESHOLD_MAX_BYTES = 524288000;
    public static final int DEFAULT_THRESHOLD_PERCENTAGE = 10;
    public static final String EXTERNAL_STORAGE_FILE_PATH;
    public static final String FAKE_MODEL_PLACEHOLDER = "fake_placeholder";
    public static final ImmutableSet<String> JAPAN_MODELS;
    public static final String MODEL_DEBUG_FILE_PATH;
    public static final ImmutableSet<String> NOTE4_MODELS;
    public static final String OCULUS_DIR = "Oculus";
    public static final String STORAGE_THRESHOLD_MAX_BYTES = "sys_storage_threshold_max_bytes";
    public static final String STORAGE_THRESHOLD_PERCENTAGE = "sys_storage_threshold_percentage";
    public static final String TAG = "DeviceUtils";
    public static String mFakeModel;
    @UnsafeContextInjection
    @Inject
    @Eager
    public final Context mContext;
    public final boolean mIsStandalone;

    @Nullable
    public static File getSDCardDirectory(Context context) {
        File[] externalFilesDirs = context.getExternalFilesDirs(null);
        for (File file : externalFilesDirs) {
            if (isDirectoryMounted(file) && Environment.isExternalStorageRemovable(file)) {
                return file;
            }
        }
        return null;
    }

    public static boolean isDirectoryMounted(File file) {
        if (file != null) {
            String externalStorageState = Environment.getExternalStorageState(file);
            if ("mounted".equals(externalStorageState) || "mounted_ro".equals(externalStorageState)) {
                return true;
            }
        }
        return false;
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_util_device_DeviceUtils_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(21, r1);
    }

    @AutoGeneratedAccessMethod
    public static final DeviceUtils _UL__ULSEP_com_oculus_util_device_DeviceUtils_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (DeviceUtils) AnonymousClass1TK.A00(21, r2, null);
    }

    @AutoGeneratedFactoryMethod
    public static final DeviceUtils _UL__ULSEP_com_oculus_util_device_DeviceUtils_ULSEP_FACTORY_METHOD(AnonymousClass0lg r1, Object obj) {
        return new DeviceUtils(r1);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_util_device_DeviceUtils_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(21, r1);
    }

    public static String getBuildModel() {
        String str = mFakeModel;
        if (Strings.isNullOrEmpty(str)) {
            str = initializeFakeModel();
            mFakeModel = str;
        }
        if (FAKE_MODEL_PLACEHOLDER.equals(str)) {
            return Build.MODEL;
        }
        return str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0036, code lost:
        if (r2 == null) goto L_0x003b;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0031 A[SYNTHETIC, Splitter:B:13:0x0031] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String initializeFakeModel() {
        /*
            java.lang.String r0 = com.oculus.util.device.DeviceUtils.MODEL_DEBUG_FILE_PATH
            java.io.File r1 = new java.io.File
            r1.<init>(r0)
            boolean r0 = r1.exists()
            if (r0 == 0) goto L_0x0046
            boolean r0 = r1.isDirectory()
            if (r0 != 0) goto L_0x0046
            r3 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0035, all -> 0x002b }
            r2.<init>(r1)     // Catch:{ IOException -> 0x0035, all -> 0x002b }
            java.io.InputStreamReader r1 = new java.io.InputStreamReader     // Catch:{ IOException -> 0x0036, all -> 0x002e }
            r1.<init>(r2)     // Catch:{ IOException -> 0x0036, all -> 0x002e }
            java.io.BufferedReader r0 = new java.io.BufferedReader     // Catch:{ IOException -> 0x0036, all -> 0x002e }
            r0.<init>(r1)     // Catch:{ IOException -> 0x0036, all -> 0x002e }
            java.lang.String r3 = r0.readLine()     // Catch:{ IOException -> 0x0036, all -> 0x002e }
            r0.close()     // Catch:{ IOException -> 0x0036, all -> 0x002e }
            goto L_0x0038
        L_0x002b:
            r0 = move-exception
            r2 = r3
            goto L_0x002f
        L_0x002e:
            r0 = move-exception
        L_0x002f:
            if (r2 == 0) goto L_0x0034
            r2.close()     // Catch:{ IOException -> 0x0034 }
        L_0x0034:
            throw r0
        L_0x0035:
            r2 = r3
        L_0x0036:
            if (r2 == 0) goto L_0x003b
        L_0x0038:
            r2.close()     // Catch:{ IOException -> 0x003b }
        L_0x003b:
            if (r3 == 0) goto L_0x0046
            com.google.common.collect.ImmutableSet<java.lang.String> r0 = com.oculus.util.device.DeviceUtils.ALL_MODELS
            boolean r0 = isOneOfTheModels(r0, r3)
            if (r0 == 0) goto L_0x0046
            return r3
        L_0x0046:
            java.lang.String r0 = "fake_placeholder"
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.util.device.DeviceUtils.initializeFakeModel():java.lang.String");
    }

    public static boolean isOculusSDCardInserted() {
        String str;
        String str2;
        String str3 = System.getenv("SECONDARY_STORAGE");
        if (str3 == null) {
            str = TAG;
            str2 = "SD card path is invalid";
        } else {
            File file = new File(str3);
            if (!file.exists() || !file.canRead()) {
                str = TAG;
                str2 = "SD card could not be read";
            } else {
                File file2 = new File(AnonymousClass006.A09(str3, "/", OCULUS_DIR));
                if (file2.exists() && file2.canRead()) {
                    return true;
                }
                str = TAG;
                str2 = "Oculus directory could not be read";
            }
        }
        AnonymousClass0MD.A04(str, str2);
        return false;
    }

    @SuppressLint({"InstanceMethodCanBeStatic"})
    public boolean areDeviceIdentityFeaturesDisabled() {
        return AnonymousClass0GR.A02("debug.oculus.disable_device_identity_features").equals("1");
    }

    public long getAvailableSDCardMemory() {
        File sDCardDirectory = getSDCardDirectory(this.mContext);
        if (sDCardDirectory != null) {
            return getAvailableDiskSpace(sDCardDirectory);
        }
        return 0;
    }

    public long getMinimumInternalMemoryInstallationTreshold() {
        int i = Settings.Secure.getInt(this.mContext.getContentResolver(), STORAGE_THRESHOLD_PERCENTAGE, 10);
        return Math.min((long) Settings.Secure.getInt(this.mContext.getContentResolver(), STORAGE_THRESHOLD_MAX_BYTES, DEFAULT_THRESHOLD_MAX_BYTES), (long) ((((float) i) * ((float) getAvailableInternalMemory())) / 100.0f));
    }

    static {
        String path = Environment.getExternalStorageDirectory().getPath();
        EXTERNAL_STORAGE_FILE_PATH = path;
        MODEL_DEBUG_FILE_PATH = AnonymousClass006.A07(path, "/oculus_model.test");
        AnonymousClass0NC r1 = new AnonymousClass0NC();
        r1.A05("SM-N910", "SM-N916", "SC-05G", "SC-04G", "SCV31", "404SC", "SM-G920", "SM-G925");
        ALL_MODELS = r1.build();
        AnonymousClass0NC r12 = new AnonymousClass0NC();
        r12.A05("SM-N910", "SM-N916");
        NOTE4_MODELS = r12.build();
        AnonymousClass0NC r13 = new AnonymousClass0NC();
        r13.A05("SC-05G", "SC-04G", "SCV31", "404SC");
        JAPAN_MODELS = r13.build();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0016, code lost:
        if (r1.hasSystemFeature("oculus.hardware.standalone_vr") == false) goto L_0x0018;
     */
    @javax.inject.Inject
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public DeviceUtils(X.AnonymousClass0lg r3) {
        /*
            r2 = this;
            r2.<init>()
            android.content.Context r0 = X.C00610Hs.A02(r3)
            r2.mContext = r0
            android.content.pm.PackageManager r1 = r0.getPackageManager()
            if (r1 == 0) goto L_0x0018
            java.lang.String r0 = "oculus.hardware.standalone_vr"
            boolean r1 = r1.hasSystemFeature(r0)
            r0 = 1
            if (r1 != 0) goto L_0x0019
        L_0x0018:
            r0 = 0
        L_0x0019:
            r2.mIsStandalone = r0
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.util.device.DeviceUtils.<init>(X.0lg):void");
    }

    public static String getDeviceId(Context context) {
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (string == null) {
            string = "";
        }
        int length = string.length();
        if (length >= 16) {
            return string;
        }
        StringBuilder sb = new StringBuilder(16);
        while (length < 16) {
            sb.append('0');
            length++;
        }
        sb.append(string);
        return sb.toString();
    }

    public static boolean isAndroidMarshmallowOrNewer() {
        return true;
    }

    public static boolean isJapanS6Device() {
        if (isOneOfTheModels(JAPAN_MODELS, getBuildModel())) {
            return true;
        }
        return false;
    }

    public static boolean isNote4Device() {
        return isOneOfTheModels(NOTE4_MODELS, getBuildModel());
    }

    public static boolean isOculusSDCardInsertedRequired() {
        isNote4Device();
        return false;
    }

    public static boolean isOneOfTheModels(ImmutableSet<String> immutableSet, String str) {
        AbstractC05710wh<String> A0I = immutableSet.iterator();
        while (A0I.hasNext()) {
            if (str.contains(A0I.next())) {
                return true;
            }
        }
        return false;
    }

    public long getAvailableExternalStorageMemory() {
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory != null) {
            return getAvailableDiskSpace(externalStorageDirectory);
        }
        return 0;
    }

    public long getAvailableInternalMemory() {
        return getAvailableDiskSpace(Environment.getDataDirectory());
    }

    public boolean isStandAloneDevice() {
        return this.mIsStandalone;
    }

    public long getAvailableDiskSpace(File file) {
        return new StatFs(file.getPath()).getAvailableBytes();
    }

    public long getAvailableDiskSpace(String str) {
        return getAvailableDiskSpace(new File(str));
    }

    public boolean isSDCardAvailable() {
        return getSDCardDirectory(this.mContext) != null;
    }

    public boolean isSDCardAvailable(Context context) {
        return getSDCardDirectory(context) != null;
    }
}