package com.oculus.config;

import android.content.SharedPreferences;
import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class SharedPreferences_com_oculus_config_annotations_GatekeeperSharedPrefsMethodAutoProvider extends AbstractProvider<SharedPreferences> {
    @Override // javax.inject.Provider
    public SharedPreferences get() {
        return ConfigModule.provideGatekeeperSharedPreferences(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(this));
    }
}
