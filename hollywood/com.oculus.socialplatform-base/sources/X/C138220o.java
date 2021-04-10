package X;

import io.reactivex.annotations.Nullable;
import java.util.Iterator;

/* renamed from: X.20o  reason: invalid class name and case insensitive filesystem */
public final class C138220o<T> extends AbstractC13311zN<T> {
    public boolean A00;
    public boolean A01;
    public boolean A02;
    public final AnonymousClass1yM<? super T> A03;
    public final Iterator<? extends T> A04;
    public volatile boolean A05;

    @Override // X.AbstractC13481zf
    public final void clear() {
        this.A01 = true;
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        this.A05 = true;
    }

    @Override // X.AbstractC13491zg
    public final int requestFusion(int i) {
        if ((i & 1) == 0) {
            return 0;
        }
        this.A02 = true;
        return 1;
    }

    @Override // X.AbstractC13481zf
    public final boolean isEmpty() {
        return this.A01;
    }

    @Override // X.AbstractC13481zf
    @Nullable
    public final T poll() {
        if (!this.A01) {
            if (!this.A00) {
                this.A00 = true;
            } else if (!this.A04.hasNext()) {
                this.A01 = true;
            }
            T t = (T) this.A04.next();
            AnonymousClass219.A01(t, "The iterator returned a null value");
            return t;
        }
        return null;
    }

    public C138220o(AnonymousClass1yM<? super T> r1, Iterator<? extends T> it) {
        this.A03 = r1;
        this.A04 = it;
    }
}
