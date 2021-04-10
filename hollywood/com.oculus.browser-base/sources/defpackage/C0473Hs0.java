package defpackage;

import java.util.ArrayDeque;
import java.util.Iterator;

/* renamed from: Hs0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C0473Hs0 {

    /* renamed from: a  reason: collision with root package name */
    public final Runnable f8186a;
    public final ArrayDeque b = new ArrayDeque();

    public C0473Hs0(Runnable runnable) {
        this.f8186a = runnable;
    }

    public void a() {
        Iterator descendingIterator = this.b.descendingIterator();
        while (descendingIterator.hasNext()) {
            CS cs = (CS) descendingIterator.next();
            if (cs.f7810a) {
                KS ks = cs.c;
                ks.D(true);
                if (ks.h.f7810a) {
                    ks.c0();
                    return;
                } else {
                    ks.g.a();
                    return;
                }
            }
        }
        Runnable runnable = this.f8186a;
        if (runnable != null) {
            runnable.run();
        }
    }
}
