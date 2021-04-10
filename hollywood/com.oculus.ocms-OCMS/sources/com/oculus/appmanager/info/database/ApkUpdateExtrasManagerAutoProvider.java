package com.oculus.appmanager.info.database;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class ApkUpdateExtrasManagerAutoProvider extends AbstractProvider<ApkUpdateExtrasManager> {
    @Override // javax.inject.Provider
    public ApkUpdateExtrasManager get() {
        return new ApkUpdateExtrasManager(ApkUpdateDatabaseSupplier._UL__ULSEP_com_oculus_appmanager_info_database_ApkUpdateDatabaseSupplier_ULSEP_ACCESS_METHOD(this));
    }
}
