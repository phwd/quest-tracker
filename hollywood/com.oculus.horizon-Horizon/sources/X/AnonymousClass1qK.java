package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
@Nullsafe(Nullsafe.Mode.STRICT)
/* renamed from: X.1qK  reason: invalid class name */
public final class AnonymousClass1qK<T> extends AnonymousClass1r0<AnonymousClass1qa<T>> {
    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: X.1qK<T> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AnonymousClass1r0
    public final void A09(Object obj, int i, AnonymousClass1qU r4) {
        super.A09(AnonymousClass1qa.A00((AnonymousClass1qa) obj), i, r4);
    }

    @Override // X.AnonymousClass1r1
    @Nullable
    public final Object A02() {
        return AnonymousClass1qa.A00((AnonymousClass1qa) super.A02());
    }

    public AnonymousClass1qK(AnonymousClass1pP<AnonymousClass1qa<T>> r1, AnonymousClass1rN r2, AnonymousClass1t0 r3) {
        super(r1, r2, r3);
    }
}
