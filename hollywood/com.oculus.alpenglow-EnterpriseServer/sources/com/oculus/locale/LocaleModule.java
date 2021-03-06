package com.oculus.locale;

import X.AbstractC01750Lk;
import X.AbstractC02990bJ;
import X.C01340Gg;
import com.facebook.inject.AutoGeneratedBinder;
import com.facebook.inject.InjectorModule;
import com.facebook.ultralight.AutoGeneratedFactoryMethod;
import com.facebook.ultralight.AutoGeneratedSwitchIdClass;
import java.util.Locale;

@InjectorModule
public class LocaleModule extends AbstractC01750Lk {

    @AutoGeneratedBinder
    public static class AutoGeneratedBindingsForLocaleModule {
    }

    @AutoGeneratedSwitchIdClass
    public static final class UL_id {
        public static final int _UL__ULSEP_java_util_Locale_ULSEP_BINDING_ID = 48;
    }

    @AutoGeneratedFactoryMethod
    public static final Locale A00(AbstractC02990bJ r0) {
        Locale locale = C01340Gg.A00(r0).getResources().getConfiguration().locale;
        if (locale == null) {
            return Locale.getDefault();
        }
        return locale;
    }
}
