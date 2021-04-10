package X;

import io.reactivex.annotations.BackpressureSupport;
import io.reactivex.annotations.SchedulerSupport;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1zj  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC13521zj<T> implements AnonymousClass1I5<T> {
    public static final int A00 = Math.max(1, Integer.getInteger("rx2.buffer-size", 128).intValue());

    @SchedulerSupport("none")
    @BackpressureSupport(AnonymousClass1Jm.SPECIAL)
    public final void A00(AbstractC13601zr<? super T> r8) {
        AtomicReference<C13531zk<T>[]> atomicReference;
        C13531zk<T>[] r3;
        C13531zk<T>[] r0;
        AnonymousClass219.A01(r8, "s is null");
        try {
            AnonymousClass219.A01(r8, "The RxJavaPlugins.onSubscribe hook returned a null FlowableSubscriber. Please check the handler provided to RxJavaPlugins.setOnFlowableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
            if (!(this instanceof C13511zi)) {
                C13541zl r6 = (C13541zl) this;
                ((AbstractC13551zm) r6).A00.A00(new C13501zh(r8, r6.A00, r6.A02, r6.A01));
                return;
            }
            C13511zi r62 = (C13511zi) this;
            C13531zk<T> r5 = new C13531zk<>(r8, r62);
            r8.onSubscribe(r5);
            do {
                atomicReference = r62.A01;
                r3 = atomicReference.get();
                if (r3 == C13511zi.A03) {
                    Throwable th = r62.A00;
                    if (th != null) {
                        r8.onError(th);
                        return;
                    } else {
                        r8.onComplete();
                        return;
                    }
                } else {
                    int length = r3.length;
                    r0 = new C13531zk[(length + 1)];
                    System.arraycopy(r3, 0, r0, 0, length);
                    r0[length] = r5;
                }
            } while (!atomicReference.compareAndSet(r3, r0));
            if (r5.get() == Long.MIN_VALUE) {
                r62.A01(r5);
            }
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th2) {
            C12261xA.A00(th2);
            AnonymousClass1y3.A01(th2);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't throw other exceptions due to RS");
            nullPointerException.initCause(th2);
            throw nullPointerException;
        }
    }
}
