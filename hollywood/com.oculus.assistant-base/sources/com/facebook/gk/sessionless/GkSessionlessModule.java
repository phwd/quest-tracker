package com.facebook.gk.sessionless;

import X.AbstractC0159Eu;
import X.AbstractC0525bO;
import X.AbstractC0562bz;
import X.Ev;
import X.MJ;
import android.content.Context;
import com.facebook.gk.store.GatekeeperWriter;
import com.facebook.ultralight.UL;

public class GkSessionlessModule extends AbstractC0562bz {
    public static final Object A00 = new Object();
    public static final Object A01 = new Object();
    public static final Object A02 = new Object();

    public class GkSessionlessModuleSelendroidInjector implements AbstractC0159Eu {
        public GatekeeperWriter getGatekeeperWriter() {
            throw new MJ("A local injection was attempted before the constructor completed or before injectMe was called.");
        }

        public GkSessionlessModuleSelendroidInjector(Context context) {
            if (UL.USE_STATIC_DI) {
                new Ev(AbstractC0525bO.get(context));
                throw new RuntimeException("Redex: Unreachable code after no-return invoke");
            } else {
                AbstractC0525bO.get(context);
                throw new NullPointerException("injectComponent");
            }
        }
    }
}
