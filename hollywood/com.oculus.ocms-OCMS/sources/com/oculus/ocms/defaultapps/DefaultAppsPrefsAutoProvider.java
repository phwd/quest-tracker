package com.oculus.ocms.defaultapps;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class DefaultAppsPrefsAutoProvider extends AbstractProvider<DefaultAppsPrefs> {
    @Override // javax.inject.Provider
    public DefaultAppsPrefs get() {
        return new DefaultAppsPrefs(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(this));
    }
}
