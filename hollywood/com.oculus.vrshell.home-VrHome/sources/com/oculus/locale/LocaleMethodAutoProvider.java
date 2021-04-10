package com.oculus.locale;

import com.facebook.inject.AbstractProvider;
import com.facebook.inject.BundledAndroidModule;
import java.util.Locale;

public class LocaleMethodAutoProvider extends AbstractProvider<Locale> {
    @Override // javax.inject.Provider
    public Locale get() {
        return LocaleModule.provideLocale(BundledAndroidModule.$ul_$xXXandroid_content_Context$xXXcom_facebook_inject_ForAppContext$xXXACCESS_METHOD(this));
    }
}
