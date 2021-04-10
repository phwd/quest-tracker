package com.facebook.xplat.fbglog;

import X.C0431hn;
import X.Mu;
import X.X3;
import com.facebook.debug.log.BLogLevelCallback;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class FbGlog {
    public static X3 sCallback;

    public static native void setLogLevel(int i);

    static {
        C0431hn.A00("fb");
    }

    @DoNotStrip
    public static synchronized void ensureSubscribedToBLogLevelChanges() {
        synchronized (FbGlog.class) {
            if (sCallback == null) {
                BLogLevelCallback x3 = new X3();
                sCallback = x3;
                synchronized (Mu.class) {
                    Mu.A00.add(x3);
                }
                setLogLevel(Mu.A01.A2f());
            }
        }
    }
}
