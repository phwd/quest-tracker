package X;

import androidx.recyclerview.widget.RecyclerView;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1zi  reason: invalid class name and case insensitive filesystem */
public final class C13511zi<T> extends AbstractC13571zo<T> {
    public static final C13531zk[] A02 = new C13531zk[0];
    public static final C13531zk[] A03 = new C13531zk[0];
    public Throwable A00;
    public final AtomicReference<C13531zk<T>[]> A01 = new AtomicReference<>(A02);

    public final void A01(C13531zk<T> r9) {
        AtomicReference<C13531zk<T>[]> atomicReference;
        C13531zk<T>[] r6;
        C13531zk<T>[] r5;
        do {
            atomicReference = this.A01;
            r6 = atomicReference.get();
            if (!(r6 == A03 || r6 == (r5 = A02))) {
                int length = r6.length;
                for (int i = 0; i < length; i++) {
                    if (r6[i] == r9) {
                        if (i >= 0) {
                            if (length != 1) {
                                r5 = new C13531zk[(length - 1)];
                                System.arraycopy(r6, 0, r5, 0, i);
                                System.arraycopy(r6, i + 1, r5, i, (length - i) - 1);
                            }
                        } else {
                            return;
                        }
                    }
                }
                return;
            }
            return;
        } while (!atomicReference.compareAndSet(r6, r5));
    }

    @Override // X.AbstractC13581zp
    public final void onComplete() {
        AtomicReference<C13531zk<T>[]> atomicReference = this.A01;
        C13531zk<T>[] r1 = atomicReference.get();
        C13531zk<T>[] r0 = A03;
        if (r1 != r0) {
            C13531zk<T>[] andSet = atomicReference.getAndSet(r0);
            for (C13531zk<T> r5 : andSet) {
                if (r5.get() != Long.MIN_VALUE) {
                    r5.downstream.onComplete();
                }
            }
        }
    }

    @Override // X.AbstractC13581zp
    public final void onError(Throwable th) {
        AnonymousClass219.A01(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        AtomicReference<C13531zk<T>[]> atomicReference = this.A01;
        C13531zk<T>[] r1 = atomicReference.get();
        C13531zk<T>[] r0 = A03;
        if (r1 == r0) {
            AnonymousClass1y3.A01(th);
            return;
        }
        this.A00 = th;
        C13531zk<T>[] andSet = atomicReference.getAndSet(r0);
        for (C13531zk<T> r5 : andSet) {
            if (r5.get() != Long.MIN_VALUE) {
                r5.downstream.onError(th);
            } else {
                AnonymousClass1y3.A01(th);
            }
        }
    }

    @Override // X.AbstractC13581zp
    public final void onNext(T t) {
        long j;
        long j2;
        AnonymousClass219.A01(t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        C13531zk<T>[] r8 = this.A01.get();
        for (C13531zk<T> r9 : r8) {
            long j3 = r9.get();
            if (j3 != Long.MIN_VALUE) {
                if (j3 != 0) {
                    r9.downstream.onNext(t);
                    do {
                        j = r9.get();
                        if (j == Long.MIN_VALUE || j == RecyclerView.FOREVER_NS) {
                            break;
                        }
                        j2 = j - 1;
                        if (j2 < 0) {
                            AnonymousClass1y3.A01(new IllegalStateException(AnonymousClass006.A06("More produced than requested: ", j2)));
                            j2 = 0;
                        }
                    } while (!r9.compareAndSet(j, j2));
                } else {
                    r9.cancel();
                    r9.downstream.onError(new C13611zs("Could not emit value due to lack of requests"));
                }
            }
        }
    }

    @Override // X.AbstractC13581zp
    public final void onSubscribe(AbstractC12551xm r3) {
        if (this.A01.get() == A03) {
            r3.cancel();
        } else {
            r3.request(RecyclerView.FOREVER_NS);
        }
    }
}
