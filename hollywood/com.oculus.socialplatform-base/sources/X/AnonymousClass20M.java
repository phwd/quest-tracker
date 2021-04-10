package X;

import java.util.concurrent.Callable;

/* renamed from: X.20M  reason: invalid class name */
public final class AnonymousClass20M {
    public static <T, R> boolean A00(AbstractC13121yu<T> r3, AnonymousClass1yM<? super R> r4, AbstractC13031yl<? super T, ? extends AbstractC13121yu<? extends R>> r5) {
        if (!(r3 instanceof Callable)) {
            return false;
        }
        try {
            Object obj = (Object) ((Callable) r3).call();
            if (obj != 0) {
                Object apply = r5.apply(obj);
                AnonymousClass219.A01(apply, "The mapper returned a null ObservableSource");
                AbstractC13121yu r1 = (AbstractC13121yu) apply;
                if (r1 instanceof Callable) {
                    Object call = ((Callable) r1).call();
                    if (call != null) {
                        AnonymousClass20K r0 = new AnonymousClass20K(r4, call);
                        r4.A8A(r0);
                        r0.run();
                        return true;
                    }
                } else {
                    r1.AAa(r4);
                    return true;
                }
            }
            AnonymousClass1z1.complete(r4);
            return true;
        } catch (Throwable th) {
            C12261xA.A00(th);
            AnonymousClass1z1.error(th, r4);
            return true;
        }
    }
}
