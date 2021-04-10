package X;

import java.util.concurrent.Callable;

/* renamed from: X.218  reason: invalid class name */
public final class AnonymousClass218<T, U> implements Callable<U>, AbstractC13031yl<T, U> {
    public final U A00;

    @Override // java.util.concurrent.Callable
    public final U call() throws Exception {
        return this.A00;
    }

    public AnonymousClass218(U u) {
        this.A00 = u;
    }

    @Override // X.AbstractC13031yl
    public final U apply(T t) throws Exception {
        return this.A00;
    }
}
