package X;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.RegularImmutableMultiset;

/* renamed from: X.uG  reason: case insensitive filesystem */
public final class C1163uG extends Tx {
    public UQ A00;
    public boolean A01;

    public final void A00(Object obj, int i) {
        int i2;
        if (i != 0) {
            if (this.A01) {
                this.A00 = new UQ(this.A00);
            }
            this.A01 = false;
            if (obj != null) {
                UQ uq = this.A00;
                int A04 = uq.A04(obj);
                if (A04 == -1) {
                    i2 = 0;
                } else {
                    i2 = uq.A05[A04];
                }
                uq.A05(obj, i + i2);
                return;
            }
            throw null;
        }
    }

    @Override // X.Tx
    public final /* bridge */ /* synthetic */ ImmutableCollection build() {
        UQ uq = this.A00;
        if (uq.A02 == 0) {
            return RegularImmutableMultiset.A03;
        }
        this.A01 = true;
        return new RegularImmutableMultiset(uq);
    }

    public C1163uG() {
        this(4);
    }

    public C1163uG(int i) {
        this.A01 = false;
        this.A00 = new UQ(i);
    }

    @Override // X.Tx
    public final Tx add(Object obj) {
        A00(obj, 1);
        return this;
    }

    @Override // X.Tx
    public final Tx add(Object[] objArr) {
        super.add(objArr);
        return this;
    }
}
