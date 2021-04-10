package X;

import java.util.LinkedList;

/* renamed from: X.1y8  reason: invalid class name */
public class AnonymousClass1y8 implements AbstractC11131xk {
    public final /* synthetic */ AnonymousClass1y7 A00;
    public final /* synthetic */ Runnable A01;

    public AnonymousClass1y8(AnonymousClass1y7 r1, Runnable runnable) {
        this.A00 = r1;
        this.A01 = runnable;
    }

    @Override // X.AbstractC11131xk
    public final void A62(Throwable th) {
        AnonymousClass1y7 r2 = this.A00;
        synchronized (r2) {
            r2.A02 = true;
            AnonymousClass1z6.A01(r2.A04, r2.A03, th);
            while (true) {
                LinkedList<Runnable> linkedList = r2.A05;
                if (!linkedList.isEmpty()) {
                    linkedList.pop().run();
                }
            }
        }
    }

    @Override // X.AbstractC11131xk
    public final void onSuccess() {
        AnonymousClass1y7 r2 = this.A00;
        synchronized (r2) {
            int addAndGet = r2.A06.addAndGet(1);
            if (r2.A02) {
                Runnable runnable = this.A01;
                if (runnable != null) {
                    runnable.run();
                }
            } else if (!r2.A01 || addAndGet != r2.A00) {
                Runnable runnable2 = this.A01;
                if (runnable2 != null) {
                    r2.A05.add(runnable2);
                }
            } else {
                Runnable runnable3 = this.A01;
                if (runnable3 != null) {
                    r2.A05.add(runnable3);
                }
                AnonymousClass1z6.A00(r2.A04, r2.A03);
            }
        }
    }
}
