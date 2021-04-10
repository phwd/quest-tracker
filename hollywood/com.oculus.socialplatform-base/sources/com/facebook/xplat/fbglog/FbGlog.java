package com.facebook.xplat.fbglog;

import X.AnonymousClass0MD;
import X.AnonymousClass0ME;
import X.AnonymousClass0lD;
import X.C02330iX;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class FbGlog {
    public static AnonymousClass0ME sCallback;

    public static native void setLogLevel(int i);

    static {
        AnonymousClass0lD.A01("fb");
    }

    @DoNotStrip
    public static synchronized void ensureSubscribedToBLogLevelChanges() {
        synchronized (FbGlog.class) {
            if (sCallback == null) {
                C02330iX r2 = new C02330iX();
                sCallback = r2;
                synchronized (AnonymousClass0MD.class) {
                    AnonymousClass0MD.A00.add(r2);
                }
                setLogLevel(AnonymousClass0MD.A01.A4S());
            }
        }
    }
}
