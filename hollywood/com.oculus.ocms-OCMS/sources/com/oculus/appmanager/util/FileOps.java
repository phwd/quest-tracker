package com.oculus.appmanager.util;

import com.facebook.inject.InjectorLike;
import com.facebook.inject.Lazy;
import com.facebook.inject.UltralightLazy;
import com.facebook.inject.UltralightProvider;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.Dependencies;
import com.facebook.ultralight.UL;
import com.oculus.appmanager.util.UtilModule;
import java.io.File;
import javax.inject.Provider;

@Dependencies({})
public class FileOps {
    @AutoGeneratedAccessMethod
    public static final Lazy _UL__ULSEP_com_facebook_inject_Lazy_ULLT_com_oculus_appmanager_util_FileOps_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightLazy.get(UtilModule.UL_id._UL__ULSEP_com_oculus_appmanager_util_FileOps_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedAccessMethod
    public static final FileOps _UL__ULSEP_com_oculus_appmanager_util_FileOps_ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return (FileOps) UL.factorymap.get(UtilModule.UL_id._UL__ULSEP_com_oculus_appmanager_util_FileOps_ULSEP_BINDING_ID, injectorLike);
    }

    @AutoGeneratedFactoryMethod
    public static final FileOps _UL__ULSEP_com_oculus_appmanager_util_FileOps_ULSEP_FACTORY_METHOD(InjectorLike injectorLike) {
        return new FileOps();
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_com_oculus_appmanager_util_FileOps_ULGT__ULSEP_ACCESS_METHOD(InjectorLike injectorLike) {
        return UltralightProvider.get(UtilModule.UL_id._UL__ULSEP_com_oculus_appmanager_util_FileOps_ULSEP_BINDING_ID, injectorLike);
    }

    public long getFileSize(String str) {
        return new File(str).length();
    }

    public boolean exists(String str) {
        return new File(str).exists();
    }
}
