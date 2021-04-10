package com.oculus.locale;

import X.AnonymousClass0Li;
import X.C01340Gg;
import com.facebook.annotations.Generated;
import java.util.Locale;

@Generated({"By: InjectorProcessor"})
public class LocaleMethodAutoProvider extends AnonymousClass0Li<Locale> {
    public final Object get() {
        Locale locale = C01340Gg.A00(this).getResources().getConfiguration().locale;
        if (locale == null) {
            return Locale.getDefault();
        }
        return locale;
    }
}
