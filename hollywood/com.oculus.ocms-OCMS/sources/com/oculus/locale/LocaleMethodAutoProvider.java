package com.oculus.locale;

import com.facebook.annotations.Generated;
import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import java.util.Locale;

@Generated({"By: InjectorProcessor"})
public class LocaleMethodAutoProvider extends AbstractProvider<Locale> {
    @Override // javax.inject.Provider
    public Locale get() {
        return LocaleModule.provideLocale(BundledAndroidModule._UL__ULSEP_android_content_Context_ULSEP_com_facebook_inject_ForAppContext_ULSEP_ACCESS_METHOD(this));
    }
}
