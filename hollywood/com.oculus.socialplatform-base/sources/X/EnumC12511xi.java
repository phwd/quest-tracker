package X;

import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1xi  reason: invalid class name and case insensitive filesystem */
public enum EnumC12511xi implements AbstractC12271xB {
    DISPOSED;

    public static boolean trySet(AtomicReference<AbstractC12271xB> atomicReference, AbstractC12271xB r2) {
        if (atomicReference.compareAndSet(null, r2)) {
            return true;
        }
        if (atomicReference.get() != DISPOSED) {
            return false;
        }
        r2.dispose();
        return false;
    }

    public static boolean validate(AbstractC12271xB r3, AbstractC12271xB r4) {
        if (r4 == null) {
            AnonymousClass1y3.A01(new NullPointerException("next is null"));
            return false;
        } else if (r3 == null) {
            return true;
        } else {
            r4.dispose();
            reportDisposableSet();
            return false;
        }
    }

    @Override // X.AbstractC12271xB
    public void dispose() {
    }

    public boolean isDisposed() {
        return true;
    }

    public static void reportDisposableSet() {
        AnonymousClass1y3.A01(new C12521xj("Disposable already set!"));
    }

    public static boolean setOnce(AtomicReference<AbstractC12271xB> atomicReference, AbstractC12271xB r2) {
        AnonymousClass219.A01(r2, "d is null");
        if (atomicReference.compareAndSet(null, r2)) {
            return true;
        }
        r2.dispose();
        if (atomicReference.get() == DISPOSED) {
            return false;
        }
        reportDisposableSet();
        return false;
    }

    public static boolean replace(AtomicReference<AbstractC12271xB> atomicReference, AbstractC12271xB r3) {
        AbstractC12271xB r1;
        do {
            r1 = atomicReference.get();
            if (r1 == DISPOSED) {
                if (r3 == null) {
                    return false;
                }
                r3.dispose();
                return false;
            }
        } while (!atomicReference.compareAndSet(r1, r3));
        return true;
    }

    public static boolean set(AtomicReference<AbstractC12271xB> atomicReference, AbstractC12271xB r3) {
        AbstractC12271xB r1;
        do {
            r1 = atomicReference.get();
            if (r1 == DISPOSED) {
                if (r3 == null) {
                    return false;
                }
                r3.dispose();
                return false;
            }
        } while (!atomicReference.compareAndSet(r1, r3));
        if (r1 == null) {
            return true;
        }
        r1.dispose();
        return true;
    }

    public static boolean dispose(AtomicReference<AbstractC12271xB> atomicReference) {
        AbstractC12271xB andSet;
        AbstractC12271xB r0 = atomicReference.get();
        EnumC12511xi r1 = DISPOSED;
        if (r0 == r1 || (andSet = atomicReference.getAndSet(r1)) == r1) {
            return false;
        }
        if (andSet == null) {
            return true;
        }
        andSet.dispose();
        return true;
    }

    public static boolean isDisposed(AbstractC12271xB r2) {
        return r2 == DISPOSED;
    }
}
