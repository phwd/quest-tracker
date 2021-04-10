package defpackage;

import java.util.Iterator;

/* renamed from: Ua1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1221Ua1 implements Runnable {
    public final C1343Wa1 F;

    public RunnableC1221Ua1(C1343Wa1 wa1) {
        this.F = wa1;
    }

    public void run() {
        C1343Wa1 wa1 = this.F;
        Iterator it = wa1.b.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC1282Va1) uq0.next()).w(wa1.f9156a.b());
            } else {
                return;
            }
        }
    }
}
