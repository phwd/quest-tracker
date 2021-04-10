package com.oculus.library.utils;

import X.AbstractC06640p5;
import X.AnonymousClass0QC;
import androidx.annotation.VisibleForTesting;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.appmanager.info.ApkUpdateInfo;
import java.util.Comparator;

@Dependencies({"_UL__ULSEP_com_oculus_common_packagescache_PackageManagerUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_appmanager_info_InfoUtils_ULSEP_BINDING_ID", "_UL__ULSEP_com_facebook_mobileconfig_factory_MobileConfig_ULSEP_BINDING_ID", "_UL__ULSEP_com_oculus_library_utils_AppSharingUtils_ULSEP_BINDING_ID"})
public class AppInternalConverter {
    @VisibleForTesting
    public static final int PRIMARY_USER_ID = 0;
    public static final String TAG = "AppInternalConverter";
    public AnonymousClass0QC _UL_mInjectionContext;

    /* renamed from: com.oculus.library.utils.AppInternalConverter$1  reason: invalid class name */
    public class AnonymousClass1 {
        public final /* synthetic */ AppInternalConverter this$0;
        public final /* synthetic */ String val$packageName;
    }

    /* renamed from: com.oculus.library.utils.AppInternalConverter$2  reason: invalid class name */
    public class AnonymousClass2 implements Comparator<ApkUpdateInfo> {
        public final /* synthetic */ AppInternalConverter this$0;
    }

    /* renamed from: com.oculus.library.utils.AppInternalConverter$3  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        public static final /* synthetic */ int[] $SwitchMap$com$oculus$appmanager$info$ApkUpdateInfoContract$UpdateState;

        /* JADX WARNING: Can't wrap try/catch for region: R(16:0|1|2|3|4|5|6|7|8|9|10|11|12|13|14|16) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0036 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001b */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0024 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x002d */
        static {
            /*
                com.oculus.appmanager.info.ApkUpdateInfoContract$UpdateState[] r0 = com.oculus.appmanager.info.ApkUpdateInfoContract.UpdateState.values()
                int r0 = r0.length
                int[] r2 = new int[r0]
                com.oculus.library.utils.AppInternalConverter.AnonymousClass3.$SwitchMap$com$oculus$appmanager$info$ApkUpdateInfoContract$UpdateState = r2
                com.oculus.appmanager.info.ApkUpdateInfoContract$UpdateState r0 = com.oculus.appmanager.info.ApkUpdateInfoContract.UpdateState.QUEUED_DOWNLOAD     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0 = 1
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                com.oculus.appmanager.info.ApkUpdateInfoContract$UpdateState r0 = com.oculus.appmanager.info.ApkUpdateInfoContract.UpdateState.DOWNLOADING     // Catch:{ NoSuchFieldError -> 0x001b }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x001b }
                r0 = 2
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x001b }
            L_0x001b:
                com.oculus.appmanager.info.ApkUpdateInfoContract$UpdateState r0 = com.oculus.appmanager.info.ApkUpdateInfoContract.UpdateState.DOWNLOADED     // Catch:{ NoSuchFieldError -> 0x0024 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0024 }
                r0 = 3
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0024 }
            L_0x0024:
                com.oculus.appmanager.info.ApkUpdateInfoContract$UpdateState r0 = com.oculus.appmanager.info.ApkUpdateInfoContract.UpdateState.VERIFYING     // Catch:{ NoSuchFieldError -> 0x002d }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x002d }
                r0 = 4
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x002d }
            L_0x002d:
                com.oculus.appmanager.info.ApkUpdateInfoContract$UpdateState r0 = com.oculus.appmanager.info.ApkUpdateInfoContract.UpdateState.VERIFIED     // Catch:{ NoSuchFieldError -> 0x0036 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0036 }
                r0 = 5
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0036 }
            L_0x0036:
                com.oculus.appmanager.info.ApkUpdateInfoContract$UpdateState r0 = com.oculus.appmanager.info.ApkUpdateInfoContract.UpdateState.INSTALLING     // Catch:{ NoSuchFieldError -> 0x003f }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x003f }
                r0 = 6
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x003f }
            L_0x003f:
                com.oculus.appmanager.info.ApkUpdateInfoContract$UpdateState r0 = com.oculus.appmanager.info.ApkUpdateInfoContract.UpdateState.UNINSTALLING     // Catch:{ NoSuchFieldError -> 0x0048 }
                int r1 = r0.ordinal()     // Catch:{ NoSuchFieldError -> 0x0048 }
                r0 = 7
                r2[r1] = r0     // Catch:{ NoSuchFieldError -> 0x0048 }
            L_0x0048:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.oculus.library.utils.AppInternalConverter.AnonymousClass3.<clinit>():void");
        }
    }

    @Inject
    public AppInternalConverter(AbstractC06640p5 r3) {
        this._UL_mInjectionContext = new AnonymousClass0QC(4, r3);
    }
}
