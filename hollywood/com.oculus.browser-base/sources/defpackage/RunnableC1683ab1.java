package defpackage;

import J.N;
import java.util.Iterator;

/* renamed from: ab1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class RunnableC1683ab1 implements Runnable {
    public final C0307Fa1 F;

    public RunnableC1683ab1(C0307Fa1 fa1) {
        this.F = fa1;
    }

    public void run() {
        C4423qa1 qa1;
        C0551Ja1 ja1 = this.F.f8024a;
        if (!ja1.h) {
            ja1.h = true;
            Iterator it = ja1.f.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (!uq0.hasNext()) {
                    break;
                }
                ((AbstractC0612Ka1) uq0.next()).b();
            }
        }
        if (ja1.l.getAndSet(false) && (qa1 = (C4423qa1) ja1.l(false)) != null) {
            N.MY_BqaOA(qa1.b, qa1);
            if (qa1.C() && qa1.p == -1) {
                if (qa1.r) {
                    qa1.x(0, 3);
                } else {
                    qa1.p = 0;
                }
            }
            Iterator it2 = qa1.k.iterator();
            while (true) {
                C1261Uq0 uq02 = (C1261Uq0) it2;
                if (uq02.hasNext()) {
                    ((AbstractC5783ya1) uq02.next()).E();
                } else {
                    return;
                }
            }
        }
    }
}
