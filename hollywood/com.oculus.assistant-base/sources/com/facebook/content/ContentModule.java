package com.facebook.content;

import X.AbstractC0159Eu;
import X.AbstractC0525bO;
import X.AbstractC0562bz;
import X.Ev;
import X.MJ;
import android.content.Context;
import com.facebook.ultralight.UL;

public abstract class ContentModule extends AbstractC0562bz {

    public class ContentModuleSelendroidInjector implements AbstractC0159Eu {
        public SecureContextHelper getSecureContextHelper() {
            throw new MJ("A local injection was attempted before the constructor completed or before injectMe was called.");
        }

        public ContentModuleSelendroidInjector(Context context) {
            if (UL.USE_STATIC_DI) {
                new Ev(AbstractC0525bO.get(context));
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            } else {
                AbstractC0525bO.get(context);
                throw new NullPointerException("injectComponent");
            }
        }
    }

    public static SecureContextHelper getInstanceForTest_SecureContextHelper(AbstractC0525bO bOVar) {
        throw new NullPointerException("getInjectorThreadStack");
    }
}
