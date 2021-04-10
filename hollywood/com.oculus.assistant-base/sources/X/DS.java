package X;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.RegularImmutableSet;
import com.google.common.collect.SingletonImmutableSet;

public class DS extends AbstractC1160uA {
    public DS A00(Object obj) {
        if (obj != null) {
            super.add(obj);
            return this;
        }
        throw null;
    }

    /* renamed from: A02 */
    public final ImmutableSet build() {
        if (this instanceof SX) {
            return ((SX) this).build();
        }
        int i = this.A00;
        if (i == 0) {
            return RegularImmutableSet.A05;
        }
        if (i == 1) {
            return new SingletonImmutableSet(this.A02[0]);
        }
        ImmutableSet A03 = ImmutableSet.A03(i, this.A02);
        this.A00 = A03.size();
        this.A01 = true;
        return A03;
    }

    public DS A01(Object... objArr) {
        super.add(objArr);
        return this;
    }

    @Override // X.Tx, X.AbstractC1160uA
    public /* bridge */ /* synthetic */ Tx add(Object[] objArr) {
        A01(objArr);
        return this;
    }
}
