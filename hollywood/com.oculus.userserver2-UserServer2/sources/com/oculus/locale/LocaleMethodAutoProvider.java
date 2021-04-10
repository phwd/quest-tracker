package com.oculus.locale;

import X.AbstractC0029Ba;
import X.IX;
import android.content.Context;
import com.facebook.annotations.Generated;
import java.util.Locale;

@Generated({"By: InjectorProcessor"})
public class LocaleMethodAutoProvider extends AbstractC0029Ba<Locale> {
    public final Object get() {
        Locale locale = ((Context) IX.A00(1, this)).getResources().getConfiguration().locale;
        if (locale == null) {
            return Locale.getDefault();
        }
        return locale;
    }
}
