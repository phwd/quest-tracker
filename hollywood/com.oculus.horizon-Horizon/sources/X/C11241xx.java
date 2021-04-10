package X;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.1xx  reason: invalid class name and case insensitive filesystem */
public class C11241xx {
    public final /* synthetic */ AbstractC11261xz A00;
    public final /* synthetic */ AbstractC11151xn A01;
    public final /* synthetic */ C11211xt A02;
    public final /* synthetic */ AtomicInteger A03;

    public C11241xx(C11211xt r1, AbstractC11261xz r2, AtomicInteger atomicInteger, AbstractC11151xn r4) {
        this.A02 = r1;
        this.A00 = r2;
        this.A03 = atomicInteger;
        this.A01 = r4;
    }

    public final void A00() {
        if (this.A03.decrementAndGet() == 0) {
            C11211xt r4 = this.A02;
            AnonymousClass1xu r1 = r4.A03;
            r1.A0E = true;
            r1.A06.postDelayed(r1.A09, (long) r1.A08.A33());
            for (AbstractC11261xz r12 : r4.A04.values()) {
                r12.A9G(new AnonymousClass1yU(r4));
            }
        }
    }
}
