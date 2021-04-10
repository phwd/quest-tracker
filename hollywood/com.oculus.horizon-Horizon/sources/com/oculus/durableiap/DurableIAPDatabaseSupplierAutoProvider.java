package com.oculus.durableiap;

import X.AnonymousClass0J3;
import X.AnonymousClass117;
import X.C003108z;
import com.facebook.annotations.Generated;
import com.oculus.errorreporting.interfaces.InterfacesModule;

@Generated({"By: InjectorProcessor"})
public class DurableIAPDatabaseSupplierAutoProvider extends AnonymousClass0J3<DurableIAPDatabaseSupplier> {
    public final Object get() {
        return new DurableIAPDatabaseSupplier(C003108z.A02(this), InterfacesModule.A00(this), (DurableIAPSchemaPart) AnonymousClass117.A00(7, this));
    }
}
