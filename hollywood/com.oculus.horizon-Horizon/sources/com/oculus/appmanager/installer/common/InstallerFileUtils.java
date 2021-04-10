package com.oculus.appmanager.installer.common;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import X.AnonymousClass0p1;
import X.C01010Iv;
import android.os.Environment;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.appmanager.patcher.Patcher;
import java.util.Locale;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_BINDING_ID", "_UL__ULSEP_android_content_pm_PackageManager_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_patcher_Patcher_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_common_CryptoMethods_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_installer_analytics_InstallerAnalytics_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_time_Clock_ULSEP_BINDING_ID"})
public class InstallerFileUtils {
    public static final String APK_FOLDER = "apk_to_install";
    public static final String APK_PATCH_SUFFIX = ".apkpatch";
    public static final String APK_SUFFIX = ".apk";
    public static final String ASSET_PATCH_SUFFIX = ".assetpatch";
    public static final String ASSET_SUFFIX = ".asset";
    public static final String DOWNLOADING_APK_PATCH_SUFFIX = ".apk_patch_downloading";
    public static final String DOWNLOADING_APK_SUFFIX = ".apk_downloading";
    public static final String DOWNLOADING_ASSET_PATCH_SUFFIX = ".asset_patch_downloading";
    public static final String DOWNLOADING_ASSET_SUFFIX = ".asset_downloading";
    public static final String DOWNLOADING_OBB_PATCH_SUFFIX = ".obb_patch_downloading";
    public static final String DOWNLOADING_OBB_SUFFIX = ".obb_downloading";
    public static final String FILENAME_BACKUP_FORMAT = "%s.backup.%s.%s.tmp";
    public static final String FILENAME_OBB_FORMAT = "main.%s.%s.obb";
    public static final String FILENAME_PREP_FORMAT = "%s.prep.%s.tmp";
    public static final String FILE_PATH_OBB_ASSET_FORMAT_STRING = "%s/Android/obb/%s/%s";
    public static final String OBB_PATCH_SUFFIX = ".obbpatch";
    public static final String OBB_SUFFIX = ".obb";
    public static final String TAG = "InstallerFileUtils";
    public static final String UNIQUE_NAME_FORMAT_STRING = "%d.%s%s";
    public static final long UNKNOWN_FILE_SIZE = -1;
    public AnonymousClass0QC _UL_mInjectionContext;
    @Inject
    public final AnonymousClass0p1<Patcher> mPatcherLazy;

    /* renamed from: com.oculus.appmanager.installer.common.InstallerFileUtils$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$appmanager$info$ApkUpdateInfoContract$UpdateType;

        /* JADX WARNING: Can't wrap try/catch for region: R(20:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|15|16|17|18|20) */
        /* JADX WARNING: Code restructure failed: missing block: B:21:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0036 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:15:0x0048 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:17:0x0052 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
        static {
            /*
                com.oculus.appmanager.info.ApkUpdateInfoContract$UpdateType[] r0 = com.oculus.appmanager.info.ApkUpdateInfoContract.UpdateType.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.appmanager.installer.common.InstallerFileUtils.AnonymousClass1.$SwitchMap$com$oculus$appmanager$info$ApkUpdateInfoContract$UpdateType = r2
                com.oculus.appmanager.info.ApkUpdateInfoContract$UpdateType r0 = com.oculus.appmanager.info.ApkUpdateInfoContract.UpdateType.STORE_FULL_APK     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.appmanager.info.ApkUpdateInfoContract$UpdateType r0 = com.oculus.appmanager.info.ApkUpdateInfoContract.UpdateType.DIRECT_APK     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.appmanager.info.ApkUpdateInfoContract$UpdateType r0 = com.oculus.appmanager.info.ApkUpdateInfoContract.UpdateType.STORE_PATCH_APK     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.appmanager.info.ApkUpdateInfoContract$UpdateType r0 = com.oculus.appmanager.info.ApkUpdateInfoContract.UpdateType.STORE_FULL_OBB     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                com.oculus.appmanager.info.ApkUpdateInfoContract$UpdateType r0 = com.oculus.appmanager.info.ApkUpdateInfoContract.UpdateType.DIRECT_OBB     // Catch:{ NoSuchFieldError -> 0x0036 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0036 }
                r0 = 5
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0036 }
            L_0x0036:
                com.oculus.appmanager.info.ApkUpdateInfoContract$UpdateType r0 = com.oculus.appmanager.info.ApkUpdateInfoContract.UpdateType.STORE_PATCH_OBB     // Catch:{ NoSuchFieldError -> 0x003f }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x003f }
                r0 = 6
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x003f }
            L_0x003f:
                com.oculus.appmanager.info.ApkUpdateInfoContract$UpdateType r0 = com.oculus.appmanager.info.ApkUpdateInfoContract.UpdateType.STORE_FULL_ASSET     // Catch:{ NoSuchFieldError -> 0x0048 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0048 }
                r0 = 7
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0048 }
            L_0x0048:
                com.oculus.appmanager.info.ApkUpdateInfoContract$UpdateType r0 = com.oculus.appmanager.info.ApkUpdateInfoContract.UpdateType.DIRECT_ASSET     // Catch:{ NoSuchFieldError -> 0x0052 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0052 }
                r0 = 8
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0052 }
            L_0x0052:
                com.oculus.appmanager.info.ApkUpdateInfoContract$UpdateType r0 = com.oculus.appmanager.info.ApkUpdateInfoContract.UpdateType.STORE_PATCH_ASSET     // Catch:{ NoSuchFieldError -> 0x005c }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x005c }
                r0 = 9
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x005c }
            L_0x005c:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.appmanager.installer.common.InstallerFileUtils.AnonymousClass1.<clinit>():void");
        }
    }

    public static String A00(String str, String str2) {
        return String.format(Locale.US, FILE_PATH_OBB_ASSET_FORMAT_STRING, Environment.getExternalStorageDirectory(), str, str2);
    }

    @Inject
    public InstallerFileUtils(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(6, r3);
        this.mPatcherLazy = new C01010Iv(24, r3);
    }
}
