package com.oculus.locale;

import X.AbstractC03180ld;
import X.AnonymousClass0Hr;
import X.AnonymousClass0Qr;
import X.AnonymousClass0VC;
import X.AnonymousClass0VI;
import X.AnonymousClass0lg;
import X.AnonymousClass1TK;
import X.C00610Hs;
import X.C01150Rm;
import android.content.Context;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.ForAppContext;
import com.facebook.inject.InjectorModule;
import com.facebook.inject.ProviderMethod;
import com.facebook.ultralight.AutoGeneratedAccessMethod;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import java.util.Locale;
import javax.inject.Provider;

@InjectorModule
public class LocaleModule extends AnonymousClass0VI {

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_java_util_Locale_ULSEP_BINDING_ID = 49;
    }

    @AutoGeneratedAccessMethod
    public static final AbstractC03180ld _UL__ULSEP_com_facebook_inject_Lazy_ULLT_java_util_Locale_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0Hr.A00(49, r1);
    }

    @AutoGeneratedAccessMethod
    public static final Locale _UL__ULSEP_java_util_Locale_ULSEP_ACCESS_METHOD(AnonymousClass0lg r2) {
        return (Locale) AnonymousClass1TK.A00(49, r2, null);
    }

    @AutoGeneratedAccessMethod
    public static final Provider _UL__ULSEP_javax_inject_Provider_ULLT_java_util_Locale_ULGT__ULSEP_ACCESS_METHOD(AnonymousClass0lg r1) {
        return AnonymousClass0VC.A00(49, r1);
    }

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForLocaleModule {
        public static void bind(AnonymousClass0Qr r0) {
        }
    }

    @AutoGeneratedFactoryMethod
    public static final Locale _UL__ULSEP_java_util_Locale_ULSEP_FACTORY_METHOD(AnonymousClass0lg r1, Object obj) {
        Locale provideLocale = provideLocale(C00610Hs.A00(r1));
        C01150Rm.A00(provideLocale, r1);
        return provideLocale;
    }

    @ProviderMethod
    public static Locale provideLocale(@ForAppContext Context context) {
        Locale locale = context.getResources().getConfiguration().locale;
        if (locale == null) {
            return Locale.getDefault();
        }
        return locale;
    }
}