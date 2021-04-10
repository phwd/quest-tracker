package com.oculus.locale;

import X.AbstractC0097Hv;
import X.C00208d;
import com.facebook.annotations.Generated;
import java.util.Locale;

@Generated({"By: InjectorProcessor"})
public class LocaleMethodAutoProvider extends AbstractC0097Hv<Locale> {
    public final Object get() {
        Locale locale = C00208d.A00(this).getResources().getConfiguration().locale;
        if (locale == null) {
            return Locale.getDefault();
        }
        return locale;
    }
}
