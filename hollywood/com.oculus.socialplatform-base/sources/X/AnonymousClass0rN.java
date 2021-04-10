package X;

import javax.annotation.Nullable;

/* renamed from: X.0rN  reason: invalid class name */
public class AnonymousClass0rN implements AbstractC00810Jt {
    @Override // X.AbstractC00810Jt
    public final boolean A9K() {
        return false;
    }

    @Override // X.AbstractC00810Jt
    public final void A9G(C00860Jy<Object> r6, @Nullable Throwable th) {
        String name;
        Object A01 = r6.A01();
        Integer valueOf = Integer.valueOf(System.identityHashCode(this));
        Integer valueOf2 = Integer.valueOf(System.identityHashCode(r6));
        if (A01 == null) {
            name = null;
        } else {
            name = A01.getClass().getName();
        }
        AnonymousClass0J5.A02(AbstractC00820Ju.class, "Finalized without closing: %x %x (type = %s)", valueOf, valueOf2, name);
    }
}
