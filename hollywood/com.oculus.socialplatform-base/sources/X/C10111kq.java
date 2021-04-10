package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1kq  reason: invalid class name and case insensitive filesystem */
public final class C10111kq<T> extends AbstractC10121kr<AbstractC00820Ju<T>> {
    @Override // X.AbstractC03830od
    public final void A03(Object obj) {
        AbstractC00820Ju.A03((AbstractC00820Ju) obj);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: X.1kq<T> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AbstractC10121kr
    public final void A08(Object obj, int i, C10161kv r4) {
        super.A08(AbstractC00820Ju.A00((AbstractC00820Ju) obj), i, r4);
    }

    @Override // X.AbstractC03830od, X.AnonymousClass0M8
    @Nullable
    public final Object A4p() {
        return AbstractC00820Ju.A00((AbstractC00820Ju) super.A4p());
    }

    public C10111kq(AnonymousClass1j8<AbstractC00820Ju<T>> r1, AnonymousClass1l1 r2, AnonymousClass1m8 r3) {
        super(r1, r2, r3);
    }
}
