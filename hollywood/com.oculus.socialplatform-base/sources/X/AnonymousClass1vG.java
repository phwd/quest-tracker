package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1vG  reason: invalid class name */
public abstract class AnonymousClass1vG<T> extends AtomicReference<T> implements AbstractC12271xB {
    public static final long serialVersionUID = 6537757548749041217L;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public AnonymousClass1vG(T t) {
        super(t);
        AnonymousClass219.A01(t, "value is null");
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        Object andSet;
        if (get() != null && (andSet = getAndSet(null)) != null) {
            ((Runnable) andSet).run();
        }
    }
}
