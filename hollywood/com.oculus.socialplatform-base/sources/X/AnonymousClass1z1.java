package X;

import io.reactivex.annotations.Nullable;

/* renamed from: X.1z1  reason: invalid class name */
public enum AnonymousClass1z1 implements AnonymousClass12n<Object> {
    INSTANCE,
    NEVER;

    @Override // X.AbstractC13481zf
    public void clear() {
    }

    @Override // X.AbstractC12271xB
    public void dispose() {
    }

    @Override // X.AbstractC13481zf
    public boolean isEmpty() {
        return true;
    }

    @Override // X.AbstractC13481zf
    @Nullable
    public Object poll() throws Exception {
        return null;
    }

    @Override // X.AbstractC13491zg
    public int requestFusion(int i) {
        return i & 2;
    }

    public boolean isDisposed() {
        if (this == INSTANCE) {
            return true;
        }
        return false;
    }

    public static void complete(AbstractC12941yc r1) {
        r1.A8A(INSTANCE);
        r1.onComplete();
    }

    public static void complete(AbstractC12501xh<?> r1) {
        r1.A8A(INSTANCE);
        r1.onComplete();
    }

    public static void complete(AnonymousClass1yM<?> r1) {
        r1.A8A(INSTANCE);
        r1.onComplete();
    }

    public static void error(Throwable th, AbstractC12941yc r2) {
        r2.A8A(INSTANCE);
        r2.onError(th);
    }

    public static void error(Throwable th, AbstractC12501xh<?> r2) {
        r2.A8A(INSTANCE);
        r2.onError(th);
    }

    public static void error(Throwable th, AnonymousClass1yM<?> r2) {
        r2.A8A(INSTANCE);
        r2.onError(th);
    }

    public static void error(Throwable th, AbstractC12721yD<?> r2) {
        r2.A8A(INSTANCE);
        r2.onError(th);
    }

    @Override // X.AbstractC13481zf
    public boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public boolean offer(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
