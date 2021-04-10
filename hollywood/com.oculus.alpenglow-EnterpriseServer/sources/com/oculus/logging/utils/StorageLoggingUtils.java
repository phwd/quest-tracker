package com.oculus.logging.utils;

import X.AbstractC02990bJ;
import X.AnonymousClass0R7;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;
import com.oculus.appmanager.info.ApkUpdateInfoContract;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID"})
public class StorageLoggingUtils {
    public static final Integer BYTES_PER_MEGABYTE = Integer.valueOf((int) ApkUpdateInfoContract.UPDATE_TYPE_FULL);
    public static final String FREE_SPACE_INTERNAL = "free_space_internal";
    public static final String FREE_SPACE_SD = "free_space_sd";
    public static final String TAG = "StorageLoggingUtils";
    public AnonymousClass0R7 _UL_mInjectionContext;

    @Inject
    public StorageLoggingUtils(AbstractC02990bJ r3) {
        this._UL_mInjectionContext = new AnonymousClass0R7(1, r3);
    }
}
