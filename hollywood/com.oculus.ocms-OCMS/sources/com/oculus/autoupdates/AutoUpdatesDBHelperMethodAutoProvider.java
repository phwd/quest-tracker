package com.oculus.autoupdates;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import com.oculus.autoupdates.database.AutoUpdatesDBHelper;

@Generated({"By: InjectorProcessor"})
public class AutoUpdatesDBHelperMethodAutoProvider extends AbstractProvider<AutoUpdatesDBHelper> {
    @Override // javax.inject.Provider
    public AutoUpdatesDBHelper get() {
        return AutoUpdatesModule.provideAutoUpdatesDBHelper(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(this));
    }
}
