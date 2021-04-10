package X;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.RegularImmutableMultiset;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

/* renamed from: X.Un  reason: case insensitive filesystem */
public class C0186Un<E> extends AnonymousClass3J<E> {
    public AnonymousClass3s<E> A00;
    public boolean A01;

    /* JADX WARN: Incorrect return type in method signature: (TE;I)LX/Un<TE;>; */
    @CanIgnoreReturnValue
    public final void A00(Object obj, int i) {
        int i2;
        if (i != 0) {
            if (this.A01) {
                this.A00 = new AnonymousClass3s<>(this.A00);
            }
            this.A01 = false;
            if (obj != null) {
                AnonymousClass3s<E> r2 = this.A00;
                int A02 = r2.A02(obj);
                if (A02 == -1) {
                    i2 = 0;
                } else {
                    i2 = r2.A05[A02];
                }
                r2.A03(obj, i + i2);
                return;
            }
            throw null;
        }
    }

    @Override // X.AnonymousClass3J
    public final /* bridge */ /* synthetic */ ImmutableCollection build() {
        AnonymousClass3s<E> r1 = this.A00;
        if (r1.A02 == 0) {
            return RegularImmutableMultiset.A03;
        }
        this.A01 = true;
        return new RegularImmutableMultiset(r1);
    }

    public C0186Un() {
        this(4);
    }

    public C0186Un(int i) {
        this.A01 = false;
        this.A00 = new AnonymousClass3s<>(i);
    }

    @Override // X.AnonymousClass3J
    @CanIgnoreReturnValue
    public final AnonymousClass3J add(Object obj) {
        A00(obj, 1);
        return this;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Object[] */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AnonymousClass3J
    @CanIgnoreReturnValue
    public final AnonymousClass3J add(Object[] objArr) {
        super.add(objArr);
        return this;
    }
}
