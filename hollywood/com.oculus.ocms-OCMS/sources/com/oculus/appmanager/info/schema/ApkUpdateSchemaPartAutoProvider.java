package com.oculus.appmanager.info.schema;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;

@Generated({"By: InjectorProcessor"})
public class ApkUpdateSchemaPartAutoProvider extends AbstractProvider<ApkUpdateSchemaPart> {
    @Override // javax.inject.Provider
    public ApkUpdateSchemaPart get() {
        return new ApkUpdateSchemaPart(ApkUpdateTable._UL__ULSEP_com_oculus_appmanager_info_schema_ApkUpdateTable_ULSEP_ACCESS_METHOD(this), ApkUpdateExtrasTable._UL__ULSEP_com_oculus_appmanager_info_schema_ApkUpdateExtrasTable_ULSEP_ACCESS_METHOD(this));
    }
}
