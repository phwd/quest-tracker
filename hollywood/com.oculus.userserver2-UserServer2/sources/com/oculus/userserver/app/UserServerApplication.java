package com.oculus.userserver.app;

import X.BZ;
import X.Oe;
import android.app.Application;

public final class UserServerApplication extends Application implements Oe {
    public final UserServerApplicationDelegate mDelegate = new UserServerApplicationDelegate(this);

    @Override // X.Oe
    public final BZ A1h() {
        BZ bz;
        UserServerApplicationDelegate userServerApplicationDelegate = this.mDelegate;
        synchronized (userServerApplicationDelegate) {
            while (true) {
                try {
                    bz = userServerApplicationDelegate.mInjector;
                    if (bz == null) {
                        if (userServerApplicationDelegate.mTestCallbacks != null) {
                            throw null;
                        }
                        userServerApplicationDelegate.wait();
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return bz;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x00ba, code lost:
        if (r1.hasNext() != false) goto L_0x00bc;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onCreate() {
        /*
        // Method dump skipped, instructions count: 293
        */
        throw new UnsupportedOperationException("Method not decompiled: com.oculus.userserver.app.UserServerApplication.onCreate():void");
    }
}
