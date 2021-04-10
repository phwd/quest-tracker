package X;

import io.reactivex.annotations.SchedulerSupport;

/* renamed from: X.1zC  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC13231zC<T> {
    @SchedulerSupport("none")
    public final void A00(AbstractC12501xh<? super T> r5) {
        AnonymousClass219.A01(r5, "observer is null");
        AnonymousClass219.A01(r5, "The RxJavaPlugins.onSubscribe hook returned a null MaybeObserver. Please check the handler provided to RxJavaPlugins.setOnMaybeSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
        try {
            if (this instanceof C13141yw) {
                C13141yw r3 = (C13141yw) this;
                ((AbstractC13161yy) r3).A00.A00(new C13131yv(r5, r3));
            } else if (this instanceof C13151yx) {
                C13151yx r32 = (C13151yx) this;
                ((AbstractC13161yy) r32).A00.A00(new C13021yk(r5, r32.A00));
            } else if (this instanceof C13171yz) {
                C13171yz r33 = (C13171yz) this;
                ((AbstractC13161yy) r33).A00.A00(new C13181z0(r5, r33.A00));
            } else if (!(this instanceof AnonymousClass1y7)) {
                C12481xf r34 = (C12481xf) this;
                C12491xg r1 = new C12491xg(r5);
                r5.A8A(r1);
                try {
                    r34.A00.subscribe(r1);
                } catch (Throwable th) {
                    C12261xA.A00(th);
                    r1.onError(th);
                }
            } else {
                r5.A8A(AnonymousClass1z1.INSTANCE);
                r5.onSuccess(((AnonymousClass1y7) this).A00);
            }
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th2) {
            C12261xA.A00(th2);
            NullPointerException nullPointerException = new NullPointerException("subscribeActual failed");
            nullPointerException.initCause(th2);
            throw nullPointerException;
        }
    }
}
