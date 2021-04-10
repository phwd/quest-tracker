package X;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.21P  reason: invalid class name */
public final class AnonymousClass21P<T> extends AtomicInteger implements AbstractC12271xB {
    public static final long serialVersionUID = 2728361546769921047L;
    public volatile boolean cancelled;
    public final AnonymousClass1yM<? super T> child;
    public Object index;
    public final AnonymousClass21J<T> parent;

    @Override // X.AbstractC12271xB
    public final void dispose() {
        if (!this.cancelled) {
            this.cancelled = true;
            this.parent.A00(this);
        }
    }

    public AnonymousClass21P(AnonymousClass21J<T> r1, AnonymousClass1yM<? super T> r2) {
        this.parent = r1;
        this.child = r2;
    }
}
