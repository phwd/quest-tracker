package com.oculus.autoupdates;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.oculus.managed.ManagedMode;

@Generated({"By: InjectorProcessor"})
public class AutoUpdatesManagerAutoProvider extends AbstractProvider<AutoUpdatesManager> {
    @Override // javax.inject.Provider
    public AutoUpdatesManager get() {
        return new AutoUpdatesManager(this, ManagedMode._UL__ULSEP_com_oculus_managed_ManagedMode_ULSEP_ACCESS_METHOD(this));
    }
}
