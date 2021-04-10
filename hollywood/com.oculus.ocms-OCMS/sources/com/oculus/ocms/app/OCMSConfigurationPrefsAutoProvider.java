package com.oculus.ocms.app;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class OCMSConfigurationPrefsAutoProvider extends AbstractProvider<OCMSConfigurationPrefs> {
    @Override // javax.inject.Provider
    public OCMSConfigurationPrefs get() {
        return new OCMSConfigurationPrefs(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_UnsafeContextInjection_ULSEP_ACCESS_METHOD(this));
    }
}
