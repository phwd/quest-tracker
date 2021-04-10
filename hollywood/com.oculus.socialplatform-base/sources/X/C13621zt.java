package X;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1zt  reason: invalid class name and case insensitive filesystem */
public final class C13621zt extends AbstractC13241zD implements AbstractC12941yc {
    public static final AnonymousClass201[] A04 = new AnonymousClass201[0];
    public static final AnonymousClass201[] A05 = new AnonymousClass201[0];
    public Throwable A00;
    public final AbstractC12981yg A01;
    public final AtomicBoolean A02 = new AtomicBoolean();
    public final AtomicReference<AnonymousClass201[]> A03 = new AtomicReference<>(A04);

    @Override // X.AbstractC12941yc
    public final void A8A(AbstractC12271xB r1) {
    }

    public final void A03(AnonymousClass201 r9) {
        AtomicReference<AnonymousClass201[]> atomicReference;
        AnonymousClass201[] r6;
        AnonymousClass201[] r1;
        do {
            atomicReference = this.A03;
            r6 = atomicReference.get();
            int length = r6.length;
            if (length != 0) {
                int i = 0;
                while (r6[i] != r9) {
                    i++;
                    if (i >= length) {
                        return;
                    }
                }
                if (i < 0) {
                    return;
                }
                if (length == 1) {
                    r1 = A04;
                } else {
                    r1 = new AnonymousClass201[(length - 1)];
                    System.arraycopy(r6, 0, r1, 0, i);
                    System.arraycopy(r6, i + 1, r1, i, (length - i) - 1);
                }
            } else {
                return;
            }
        } while (!atomicReference.compareAndSet(r6, r1));
    }

    @Override // X.AbstractC12941yc
    public final void onComplete() {
        AnonymousClass201[] andSet = this.A03.getAndSet(A05);
        for (AnonymousClass201 r1 : andSet) {
            if (!r1.get()) {
                r1.downstream.onComplete();
            }
        }
    }

    @Override // X.AbstractC12941yc
    public final void onError(Throwable th) {
        this.A00 = th;
        AnonymousClass201[] andSet = this.A03.getAndSet(A05);
        for (AnonymousClass201 r1 : andSet) {
            if (!r1.get()) {
                r1.downstream.onError(th);
            }
        }
    }

    public C13621zt(AbstractC12981yg r3) {
        this.A01 = r3;
    }
}
