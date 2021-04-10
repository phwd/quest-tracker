package X;

/* renamed from: X.20y  reason: invalid class name and case insensitive filesystem */
public enum EnumC139220y {
    COMPLETE;

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    public static <T> T getValue(Object obj) {
        return obj;
    }

    public static <T> Object next(T t) {
        return t;
    }

    public String toString() {
        return "NotificationLite.Complete";
    }

    public static Object complete() {
        return COMPLETE;
    }

    public static Object disposable(AbstractC12271xB r1) {
        return new AnonymousClass21T(r1);
    }

    public static Object error(Throwable th) {
        return new AnonymousClass21B(th);
    }

    public static AbstractC12271xB getDisposable(Object obj) {
        return ((AnonymousClass21T) obj).upstream;
    }

    public static Throwable getError(Object obj) {
        return ((AnonymousClass21B) obj).e;
    }

    public static AbstractC12551xm getSubscription(Object obj) {
        return ((AnonymousClass21U) obj).upstream;
    }

    public static boolean isComplete(Object obj) {
        if (obj == COMPLETE) {
            return true;
        }
        return false;
    }

    public static Object subscription(AbstractC12551xm r1) {
        return new AnonymousClass21U(r1);
    }

    public static boolean isDisposable(Object obj) {
        return obj instanceof AnonymousClass21T;
    }

    public static boolean isError(Object obj) {
        return obj instanceof AnonymousClass21B;
    }

    public static boolean isSubscription(Object obj) {
        return obj instanceof AnonymousClass21U;
    }

    public static <T> boolean accept(Object obj, AnonymousClass1yM<? super T> r3) {
        if (obj == COMPLETE) {
            r3.onComplete();
            return true;
        } else if (obj instanceof AnonymousClass21B) {
            r3.onError(((AnonymousClass21B) obj).e);
            return true;
        } else {
            r3.onNext(obj);
            return false;
        }
    }

    public static <T> boolean accept(Object obj, AbstractC13581zp<? super T> r3) {
        if (obj == COMPLETE) {
            r3.onComplete();
            return true;
        } else if (obj instanceof AnonymousClass21B) {
            r3.onError(((AnonymousClass21B) obj).e);
            return true;
        } else {
            r3.onNext(obj);
            return false;
        }
    }

    public static <T> boolean acceptFull(Object obj, AnonymousClass1yM<? super T> r3) {
        if (obj == COMPLETE) {
            r3.onComplete();
            return true;
        } else if (obj instanceof AnonymousClass21B) {
            r3.onError(((AnonymousClass21B) obj).e);
            return true;
        } else if (obj instanceof AnonymousClass21T) {
            r3.A8A(((AnonymousClass21T) obj).upstream);
            return false;
        } else {
            r3.onNext(obj);
            return false;
        }
    }

    public static <T> boolean acceptFull(Object obj, AbstractC13581zp<? super T> r3) {
        if (obj == COMPLETE) {
            r3.onComplete();
            return true;
        } else if (obj instanceof AnonymousClass21B) {
            r3.onError(((AnonymousClass21B) obj).e);
            return true;
        } else if (obj instanceof AnonymousClass21U) {
            r3.onSubscribe(((AnonymousClass21U) obj).upstream);
            return false;
        } else {
            r3.onNext(obj);
            return false;
        }
    }
}
