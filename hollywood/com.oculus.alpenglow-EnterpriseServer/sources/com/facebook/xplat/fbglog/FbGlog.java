package com.facebook.xplat.fbglog;

import X.AnonymousClass0NK;
import X.C02690aa;
import X.C05400jG;
import com.facebook.debug.log.BLogLevelCallback;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class FbGlog {
    public static C02690aa sCallback;

    public static native void setLogLevel(int i);

    static {
        C05400jG.A00("fb");
    }

    @DoNotStrip
    public static synchronized void ensureSubscribedToBLogLevelChanges() {
        synchronized (FbGlog.class) {
            if (sCallback == null) {
                BLogLevelCallback r2 = new C02690aa();
                sCallback = r2;
                synchronized (AnonymousClass0NK.class) {
                    AnonymousClass0NK.A00.add(r2);
                }
                setLogLevel(AnonymousClass0NK.A01.A44());
            }
        }
    }
}
