package com.facebook.xplat.fbglog;

import X.C0139Dd;
import X.C1007qb;
import X.KV;

public class FbGlog {
    public static C1007qb sCallback;

    public static native void setLogLevel(int i);

    static {
        KV.A01("fb");
    }

    public static synchronized void ensureSubscribedToBLogLevelChanges() {
        synchronized (FbGlog.class) {
            if (sCallback == null) {
                C1007qb qbVar = new C1007qb();
                sCallback = qbVar;
                synchronized (C0139Dd.class) {
                    C0139Dd.A00.add(qbVar);
                }
                setLogLevel(C0139Dd.A01.A2h());
            }
        }
    }
}
