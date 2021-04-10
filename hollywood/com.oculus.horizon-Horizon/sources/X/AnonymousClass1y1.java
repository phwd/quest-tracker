package X;

import java.util.LinkedList;

/* renamed from: X.1y1  reason: invalid class name */
public class AnonymousClass1y1 implements AnonymousClass1zF {
    public final /* synthetic */ AnonymousClass1y2 A00;

    public AnonymousClass1y1(AnonymousClass1y2 r1) {
        this.A00 = r1;
    }

    @Override // X.AnonymousClass1zF
    public final void A5y(AnonymousClass1lF r4) {
        AnonymousClass1y2 r2 = this.A00;
        synchronized (r2) {
            r2.A02 = true;
            AnonymousClass1z7.A01(r2.A04, r2.A03, r4);
            while (true) {
                LinkedList<Runnable> linkedList = r2.A05;
                if (!linkedList.isEmpty()) {
                    linkedList.pop().run();
                }
            }
        }
    }

    @Override // X.AnonymousClass1zF
    public final void onSuccess() {
        AnonymousClass1y2 r2 = this.A00;
        synchronized (r2) {
            int addAndGet = r2.A06.addAndGet(1);
            if (!r2.A02 && r2.A01 && addAndGet == r2.A00) {
                AnonymousClass1z7.A00(r2.A04, r2.A03);
            }
        }
    }
}
