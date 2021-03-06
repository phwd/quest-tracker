package com.oculus.logging.utils;

import com.facebook.inject.InjectionContext;
import com.facebook.inject.InjectorLike;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.Inject;

@Dependencies
public class StorageLoggingUtils {
    private static final Integer BYTES_PER_MEGABYTE = 1048576;
    private static final String TAG = "StorageLoggingUtils";
    private InjectionContext _UL_mInjectionContext;

    @AutoGeneratedFactoryMethod
    public static final StorageLoggingUtils _UL__ULSEP_com_oculus_logging_utils_StorageLoggingUtils_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new StorageLoggingUtils(injectorLike);
    }

    @Inject
    public StorageLoggingUtils(InjectorLike injectorLike) {
        this._UL_mInjectionContext = new InjectionContext(1, injectorLike);
    }
}
