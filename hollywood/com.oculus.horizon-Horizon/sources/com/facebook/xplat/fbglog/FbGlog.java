package com.facebook.xplat.fbglog;

import X.AnonymousClass0NO;
import X.C03250cX;
import X.C04170gw;
import com.facebook.debug.log.BLogLevelCallback;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class FbGlog {
    public static C04170gw sCallback;

    public static native void setLogLevel(int i);

    static {
        C03250cX.A01("fb");
    }

    @DoNotStrip
    public static synchronized void ensureSubscribedToBLogLevelChanges() {
        synchronized (FbGlog.class) {
            if (sCallback == null) {
                BLogLevelCallback r2 = new C04170gw();
                sCallback = r2;
                synchronized (AnonymousClass0NO.class) {
                    AnonymousClass0NO.A00.add(r2);
                }
                setLogLevel(AnonymousClass0NO.A01.A3t());
            }
        }
    }
}
