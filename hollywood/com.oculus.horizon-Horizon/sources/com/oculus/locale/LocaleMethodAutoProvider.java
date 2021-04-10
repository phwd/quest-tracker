package com.oculus.locale;

import X.AnonymousClass0J3;
import X.C003108z;
import com.facebook.annotations.Generated;
import java.util.Locale;

@Generated({"By: InjectorProcessor"})
public class LocaleMethodAutoProvider extends AnonymousClass0J3<Locale> {
    public final Object get() {
        Locale locale = C003108z.A00(this).getResources().getConfiguration().locale;
        if (locale == null) {
            return Locale.getDefault();
        }
        return locale;
    }
}
