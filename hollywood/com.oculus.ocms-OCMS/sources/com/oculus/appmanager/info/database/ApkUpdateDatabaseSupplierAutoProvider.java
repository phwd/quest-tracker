package com.oculus.appmanager.info.database;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import com.oculus.appmanager.info.schema.ApkUpdateSchemaPart;
import com.oculus.errorreporting.interfaces.InterfacesModule;

@Generated({"By: InjectorProcessor"})
public class ApkUpdateDatabaseSupplierAutoProvider extends AbstractProvider<ApkUpdateDatabaseSupplier> {
    @Override // javax.inject.Provider
    public ApkUpdateDatabaseSupplier get() {
        return new ApkUpdateDatabaseSupplier(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this), InterfacesModule._UL__ULSEP_com_oculus_errorreporting_interfaces_IErrorReporter_ULSEP_ACCESS_METHOD(this), ApkUpdateSchemaPart._UL__ULSEP_com_oculus_appmanager_info_schema_ApkUpdateSchemaPart_ULSEP_ACCESS_METHOD(this));
    }
}
