package com.oculus.appmanager.info.database;

import X.AnonymousClass0J3;
import X.C003108z;
import com.facebook.annotations.Generated;
import com.oculus.appmanager.info.schema.ApkUpdateSchemaPart;
import com.oculus.errorreporting.interfaces.InterfacesModule;

@Generated({"By: InjectorProcessor"})
public class ApkUpdateDatabaseSupplierAutoProvider extends AnonymousClass0J3<ApkUpdateDatabaseSupplier> {
    public final Object get() {
        return new ApkUpdateDatabaseSupplier(C003108z.A02(this), InterfacesModule.A00(this), ApkUpdateSchemaPart.A00(this));
    }
}
