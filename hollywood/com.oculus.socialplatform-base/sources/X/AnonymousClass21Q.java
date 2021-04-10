package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.21Q  reason: invalid class name */
public final class AnonymousClass21Q<T> extends AtomicReference<Object> implements AbstractC12271xB {
    public static final long serialVersionUID = -1100270633763673112L;
    public final AnonymousClass1yM<? super T> child;

    public AnonymousClass21Q(AnonymousClass1yM<? super T> r1) {
        this.child = r1;
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        Object andSet = getAndSet(this);
        if (andSet != null && andSet != this) {
            ((AnonymousClass21E) andSet).A00(this);
        }
    }
}
