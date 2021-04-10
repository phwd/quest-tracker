package com.oculus.library.utils.app;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;

@Generated({"By: InjectorProcessor"})
public class AppConverterMethodAutoProvider extends AbstractProvider<AppConverter> {
    @Override // javax.inject.Provider
    public AppConverter get() {
        return AppConverterUtilsModule.provideAppConverter(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(this));
    }
}
