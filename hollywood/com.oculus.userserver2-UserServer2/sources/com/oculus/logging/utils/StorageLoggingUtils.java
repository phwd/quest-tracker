package com.oculus.logging.utils;

import X.Om;
import X.SZ;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;

@Dependencies({"_UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_BINDING_ID"})
public class StorageLoggingUtils {
    public static final Integer BYTES_PER_MEGABYTE = 1048576;
    public static final String FREE_SPACE_INTERNAL = "free_space_internal";
    public static final String FREE_SPACE_SD = "free_space_sd";
    public static final String TAG = "StorageLoggingUtils";
    public Om _UL_mInjectionContext;

    @Inject
    public StorageLoggingUtils(SZ sz) {
        this._UL_mInjectionContext = new Om(1, sz);
    }
}
