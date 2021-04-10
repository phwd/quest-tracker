package X;

import io.reactivex.annotations.Nullable;

/* renamed from: X.213  reason: invalid class name */
public final class AnonymousClass213<T> extends AbstractC13311zN<T> {
    public int A00;
    public boolean A01;
    public final AnonymousClass1yM<? super T> A02;
    public final T[] A03;
    public volatile boolean A04;

    @Override // X.AbstractC12271xB
    public final void dispose() {
        this.A04 = true;
    }

    @Override // X.AbstractC13491zg
    public final int requestFusion(int i) {
        if ((i & 1) == 0) {
            return 0;
        }
        this.A01 = true;
        return 1;
    }

    @Override // X.AbstractC13481zf
    public final void clear() {
        this.A00 = this.A03.length;
    }

    @Override // X.AbstractC13481zf
    public final boolean isEmpty() {
        if (this.A00 == this.A03.length) {
            return true;
        }
        return false;
    }

    @Override // X.AbstractC13481zf
    @Nullable
    public final T poll() {
        int i = this.A00;
        T[] tArr = this.A03;
        if (i == tArr.length) {
            return null;
        }
        this.A00 = i + 1;
        T t = tArr[i];
        AnonymousClass219.A01(t, "The array element is null");
        return t;
    }

    public AnonymousClass213(AnonymousClass1yM<? super T> r1, T[] tArr) {
        this.A02 = r1;
        this.A03 = tArr;
    }
}
