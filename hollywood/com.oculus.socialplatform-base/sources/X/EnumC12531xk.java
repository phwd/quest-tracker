package X;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1xk  reason: invalid class name and case insensitive filesystem */
public enum EnumC12531xk implements AbstractC12551xm {
    CANCELLED;

    @Override // X.AbstractC12551xm
    public void cancel() {
    }

    @Override // X.AbstractC12551xm
    public void request(long j) {
    }

    public static void reportMoreProduced(long j) {
        AnonymousClass1y3.A01(new C12521xj(AnonymousClass006.A06("More produced than requested: ", j)));
    }

    public static void reportSubscriptionSet() {
        AnonymousClass1y3.A01(new C12521xj("Subscription already set!"));
    }

    public static void deferredRequest(AtomicReference<AbstractC12551xm> atomicReference, AtomicLong atomicLong, long j) {
        AbstractC12551xm r0 = atomicReference.get();
        if (r0 != null) {
            r0.request(j);
        } else if (validate(j)) {
            C12541xl.A00(atomicLong, j);
            AbstractC12551xm r5 = atomicReference.get();
            if (r5 != null) {
                long andSet = atomicLong.getAndSet(0);
                if (andSet != 0) {
                    r5.request(andSet);
                }
            }
        }
    }

    public static boolean deferredSetOnce(AtomicReference<AbstractC12551xm> atomicReference, AtomicLong atomicLong, AbstractC12551xm r6) {
        if (!setOnce(atomicReference, r6)) {
            return false;
        }
        long andSet = atomicLong.getAndSet(0);
        if (andSet == 0) {
            return true;
        }
        r6.request(andSet);
        return true;
    }

    public static boolean replace(AtomicReference<AbstractC12551xm> atomicReference, AbstractC12551xm r3) {
        AbstractC12551xm r1;
        do {
            r1 = atomicReference.get();
            if (r1 == CANCELLED) {
                if (r3 == null) {
                    return false;
                }
                r3.cancel();
                return false;
            }
        } while (!atomicReference.compareAndSet(r1, r3));
        return true;
    }

    public static boolean set(AtomicReference<AbstractC12551xm> atomicReference, AbstractC12551xm r3) {
        AbstractC12551xm r1;
        do {
            r1 = atomicReference.get();
            if (r1 == CANCELLED) {
                if (r3 == null) {
                    return false;
                }
                r3.cancel();
                return false;
            }
        } while (!atomicReference.compareAndSet(r1, r3));
        if (r1 == null) {
            return true;
        }
        r1.cancel();
        return true;
    }

    public static boolean cancel(AtomicReference<AbstractC12551xm> atomicReference) {
        AbstractC12551xm andSet;
        AbstractC12551xm r0 = atomicReference.get();
        EnumC12531xk r1 = CANCELLED;
        if (r0 == r1 || (andSet = atomicReference.getAndSet(r1)) == r1) {
            return false;
        }
        if (andSet == null) {
            return true;
        }
        andSet.cancel();
        return true;
    }

    public static boolean setOnce(AtomicReference<AbstractC12551xm> atomicReference, AbstractC12551xm r2) {
        AnonymousClass219.A01(r2, "s is null");
        if (atomicReference.compareAndSet(null, r2)) {
            return true;
        }
        r2.cancel();
        if (atomicReference.get() == CANCELLED) {
            return false;
        }
        reportSubscriptionSet();
        return false;
    }

    public static boolean setOnce(AtomicReference<AbstractC12551xm> atomicReference, AbstractC12551xm r1, long j) {
        if (!setOnce(atomicReference, r1)) {
            return false;
        }
        r1.request(j);
        return true;
    }

    public static boolean validate(long j) {
        if (j > 0) {
            return true;
        }
        AnonymousClass1y3.A01(new IllegalArgumentException(AnonymousClass006.A06("n > 0 required but it was ", j)));
        return false;
    }

    public static boolean validate(AbstractC12551xm r3, AbstractC12551xm r4) {
        if (r4 == null) {
            AnonymousClass1y3.A01(new NullPointerException("next is null"));
            return false;
        } else if (r3 == null) {
            return true;
        } else {
            r4.cancel();
            reportSubscriptionSet();
            return false;
        }
    }
}
