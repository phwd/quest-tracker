package X;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.RegularImmutableMultiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* renamed from: X.0dz  reason: invalid class name */
public class AnonymousClass0dz<E> extends AbstractC06730pi<E> {
    public AnonymousClass0rF<E> A00;
    public boolean A01;

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0075  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x009a A[LOOP:1: B:25:0x0094->B:27:0x009a, LOOP_END] */
    @Override // X.AbstractC06730pi
    @com.google.errorprone.annotations.CanIgnoreReturnValue
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final X.AbstractC06730pi A02(java.lang.Iterable r6) {
        /*
        // Method dump skipped, instructions count: 176
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0dz.A02(java.lang.Iterable):X.0pi");
    }

    /* JADX WARN: Incorrect return type in method signature: (TE;I)LX/0dz<TE;>; */
    @CanIgnoreReturnValue
    public final void A03(Object obj, int i) {
        int i2;
        if (i != 0) {
            if (this.A01) {
                this.A00 = new AnonymousClass0rF<>(this.A00);
            }
            this.A01 = false;
            if (obj != null) {
                AnonymousClass0rF<E> r2 = this.A00;
                int A06 = r2.A06(obj);
                if (A06 == -1) {
                    i2 = 0;
                } else {
                    i2 = r2.A04[A06];
                }
                r2.A07(obj, i + i2);
                return;
            }
            throw null;
        }
    }

    @Override // X.AbstractC06730pi
    public final /* bridge */ /* synthetic */ ImmutableCollection build() {
        AnonymousClass0rF<E> r1 = this.A00;
        if (r1.A01 == 0) {
            return RegularImmutableMultiset.A03;
        }
        this.A01 = true;
        return new RegularImmutableMultiset(r1);
    }

    public AnonymousClass0dz() {
        this(4);
    }

    public AnonymousClass0dz(int i) {
        this.A01 = false;
        this.A00 = new AnonymousClass0rF<>(i);
    }

    @Override // X.AbstractC06730pi
    @CanIgnoreReturnValue
    public final AbstractC06730pi add(Object obj) {
        A03(obj, 1);
        return this;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AbstractC06730pi
    @CanIgnoreReturnValue
    public final AbstractC06730pi add(Object[] objArr) {
        super.add(objArr);
        return this;
    }
}
