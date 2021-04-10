package com.facebook.xplat.fbglog;

import X.Mi;
import X.Rs;
import X.g6;
import com.facebook.debug.log.BLogLevelCallback;
import com.facebook.proguard.annotations.DoNotStrip;

@DoNotStrip
public class FbGlog {
    public static Rs sCallback;

    public static native void setLogLevel(int i);

    @DoNotStrip
    public static synchronized void ensureSubscribedToBLogLevelChanges() {
        synchronized (FbGlog.class) {
            if (sCallback == null) {
                BLogLevelCallback rs = new Rs();
                sCallback = rs;
                synchronized (Mi.class) {
                    Mi.A00.add(rs);
                }
                setLogLevel(Mi.A01.A1n());
            }
        }
    }

    static {
        g6.A00();
        throw new RuntimeException("Redex: Unreachable code after no-return invoke");
    }
}
