package X;

import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.SchedulerSupport;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1zE  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC13251zE<T> implements AbstractC12761yH<T> {
    @SchedulerSupport("none")
    @CheckReturnValue
    public static <T> AbstractC13251zE<T> A00(AbstractC06371Zh<T> r1) {
        AnonymousClass219.A01(r1, "source is null");
        return new C12691yA(r1);
    }

    @SchedulerSupport("none")
    @CheckReturnValue
    public static <T1, T2, R> AbstractC13251zE<R> A01(AbstractC12761yH<? extends T1> r3, AbstractC12761yH<? extends T2> r4, AbstractC13321zO<? super T1, ? super T2, ? extends R> r5) {
        AnonymousClass219.A01(r3, "source1 is null");
        AnonymousClass219.A01(r4, "source2 is null");
        AnonymousClass219.A01(r5, "f is null");
        return new C13411zY(new AbstractC12761yH[]{r3, r4}, new C13271zG(r5));
    }

    @SchedulerSupport("none")
    @CheckReturnValue
    public final AbstractC136820a<T> A02() {
        if (this instanceof AbstractC13331zP) {
            return ((AbstractC13331zP) this).A3G();
        }
        return new AnonymousClass1zI(this);
    }

    @SchedulerSupport("custom")
    @CheckReturnValue
    public final AbstractC13251zE<T> A03(AbstractC12361xL r2) {
        AnonymousClass219.A01(r2, "scheduler is null");
        return new C12751yG(this, r2);
    }

    @SchedulerSupport("none")
    @CheckReturnValue
    public final <R> AbstractC13251zE<R> A04(AbstractC13031yl<? super T, ? extends R> r2) {
        AnonymousClass219.A01(r2, "mapper is null");
        return new C13341zQ(this, r2);
    }

    @SchedulerSupport("none")
    @CheckReturnValue
    public final AbstractC12271xB A05(AbstractC12851yS<? super T> r2, AbstractC12851yS<? super Throwable> r3) {
        AnonymousClass219.A01(r2, "onSuccess is null");
        AnonymousClass219.A01(r3, "onError is null");
        C12841yR r0 = new C12841yR(r2, r3);
        AAb(r0);
        return r0;
    }

    @SchedulerSupport("none")
    @CheckReturnValue
    public final T A06() {
        C12991yh r5 = new C12991yh();
        AAb(r5);
        if (r5.getCount() != 0) {
            try {
                r5.await();
            } catch (InterruptedException e) {
                r5.A03 = true;
                AbstractC12271xB r0 = r5.A00;
                if (r0 != null) {
                    r0.dispose();
                }
                throw C12301xE.A00(e);
            }
        }
        Throwable th = r5.A02;
        if (th == null) {
            return r5.A01;
        }
        throw C12301xE.A00(th);
    }

    @Override // X.AbstractC12761yH
    @SchedulerSupport("none")
    public final void AAb(AbstractC12721yD<? super T> r8) {
        Throwable th;
        AtomicReference<C12711yC<T>[]> atomicReference;
        C12711yC<T>[] r3;
        C12711yC<T>[] r0;
        AtomicReference<C13281zH<T>[]> atomicReference2;
        C13281zH<T>[] r32;
        C13281zH<T>[] r02;
        AnonymousClass219.A01(r8, "subscriber is null");
        AnonymousClass219.A01(r8, "The RxJavaPlugins.onSubscribe hook returned a null SingleObserver. Please check the handler provided to RxJavaPlugins.setOnSingleSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
        try {
            if (this instanceof C12701yB) {
                C12701yB r6 = (C12701yB) this;
                C12711yC<T> r5 = new C12711yC<>(r8, r6);
                r8.A8A(r5);
                do {
                    atomicReference = r6.A03;
                    r3 = atomicReference.get();
                    if (r3 == C12701yB.A05) {
                        th = r6.A01;
                        if (th == null) {
                            r8.onSuccess(r6.A00);
                            return;
                        }
                    } else {
                        int length = r3.length;
                        r0 = new C12711yC[(length + 1)];
                        System.arraycopy(r3, 0, r0, 0, length);
                        r0[length] = r5;
                    }
                } while (!atomicReference.compareAndSet(r3, r0));
                if (r5.get() == null) {
                    r6.A07(r5);
                    return;
                }
                return;
            } else if (this instanceof C13411zY) {
                C13411zY r62 = (C13411zY) this;
                AbstractC12761yH<? extends T>[] r52 = r62.A01;
                int length2 = r52.length;
                if (length2 == 1) {
                    r52[0].AAb(new C13351zR(r8, new C13441zb(r62)));
                    return;
                }
                C13421zZ r2 = new C13421zZ(r8, length2, r62.A00);
                r8.A8A(r2);
                for (int i = 0; i < length2 && r2.get() > 0; i++) {
                    AbstractC12761yH<? extends T> r1 = r52[i];
                    if (r1 == null) {
                        r2.A00(new NullPointerException("One of the sources is null"), i);
                        return;
                    } else {
                        r1.AAb(r2.observers[i]);
                    }
                }
                return;
            } else if (this instanceof C13011yj) {
                C13011yj r63 = (C13011yj) this;
                RunnableC13001yi r22 = new RunnableC13001yi(r8, r63.A01);
                r8.A8A(r22);
                EnumC12511xi.replace(r22.task, r63.A00.A01(r22));
                return;
            } else if (this instanceof C12751yG) {
                C12751yG r64 = (C12751yG) this;
                r64.A01.AAb(new RunnableC12741yF(r8, r64.A00));
                return;
            } else if (this instanceof C13341zQ) {
                C13341zQ r65 = (C13341zQ) this;
                r65.A00.AAb(new C13351zR(r8, r65.A01));
                return;
            } else if (this instanceof C12731yE) {
                r8.A8A(AnonymousClass1z1.INSTANCE);
                r8.onSuccess(((C12731yE) this).A00);
                return;
            } else if (this instanceof C13401zX) {
                C13401zX r66 = (C13401zX) this;
                r66.A00.AAb(new C13381zU(r8, r66.A01));
                return;
            } else if (this instanceof AnonymousClass1zJ) {
                AnonymousClass1zJ r67 = (AnonymousClass1zJ) this;
                r67.A01.AAb(new C13291zK(r67, r8));
                return;
            } else if (this instanceof C12691yA) {
                C12691yA r68 = (C12691yA) this;
                AnonymousClass1y9 r12 = new AnonymousClass1y9(r8);
                r8.A8A(r12);
                try {
                    r68.A00.subscribe(r12);
                    return;
                } catch (Throwable th2) {
                    C12261xA.A00(th2);
                    r12.onError(th2);
                    return;
                }
            } else if (this instanceof C13261zF) {
                C13261zF r69 = (C13261zF) this;
                C13281zH<T> r53 = new C13281zH<>(r8, r69);
                r8.A8A(r53);
                do {
                    atomicReference2 = r69.A04;
                    r32 = atomicReference2.get();
                    if (r32 == C13261zF.A06) {
                        th = r69.A01;
                        if (th == null) {
                            r8.onSuccess(r69.A00);
                            return;
                        }
                    } else {
                        int length3 = r32.length;
                        r02 = new C13281zH[(length3 + 1)];
                        System.arraycopy(r32, 0, r02, 0, length3);
                        r02[length3] = r53;
                    }
                } while (!atomicReference2.compareAndSet(r32, r02));
                if (r53.get()) {
                    r69.A07(r53);
                }
                if (r69.A03.getAndIncrement() == 0) {
                    r69.A02.AAb(r69);
                    return;
                }
                return;
            } else if (this instanceof C13041ym) {
                ((C13041ym) this).A00.AAa(new C12901yY(r8));
                return;
            } else if (!(this instanceof C13051yn)) {
                C137020c r610 = (C137020c) this;
                try {
                    Object call = r610.A02.call();
                    AnonymousClass219.A01(call, "The initialSupplier returned a null value");
                    r610.A00.AAa(new C137920l(r8, call, r610.A01));
                    return;
                } catch (Throwable th3) {
                    AnonymousClass1z1.error(th3, r8);
                    return;
                }
            } else {
                ((C13051yn) this).A00.AAa(new C12921ya(r8));
                return;
            }
            r8.onError(th);
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th4) {
            C12261xA.A00(th4);
            NullPointerException nullPointerException = new NullPointerException("subscribeActual failed");
            nullPointerException.initCause(th4);
            throw nullPointerException;
        }
    }
}
