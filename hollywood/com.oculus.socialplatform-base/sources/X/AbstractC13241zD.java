package X;

import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.SchedulerSupport;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.1zD  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC13241zD implements AbstractC12981yg {
    @SchedulerSupport("none")
    @CheckReturnValue
    public final AbstractC13241zD A00(AbstractC12881yV r9) {
        AbstractC12851yS<Object> r2 = C137220e.A04;
        AbstractC12881yV r5 = C137220e.A03;
        AnonymousClass219.A01(r2, "onSubscribe is null");
        AnonymousClass219.A01(r2, "onError is null");
        AnonymousClass219.A01(r9, "onComplete is null");
        AnonymousClass219.A01(r5, "onTerminate is null");
        AnonymousClass219.A01(r5, "onAfterTerminate is null");
        AnonymousClass219.A01(r5, "onDispose is null");
        return new AnonymousClass1z7(this, r2, r2, r9, r5, r5, r5);
    }

    @SchedulerSupport("none")
    @CheckReturnValue
    public final <T> AbstractC136820a<T> A01() {
        if (this instanceof AbstractC13331zP) {
            return ((AbstractC13331zP) this).A3G();
        }
        return new AnonymousClass1zL(this);
    }

    @SchedulerSupport("none")
    @CheckReturnValue
    public final AbstractC12271xB A02(AbstractC12881yV r2, AbstractC12851yS<? super Throwable> r3) {
        AnonymousClass219.A01(r3, "onError is null");
        AnonymousClass219.A01(r2, "onComplete is null");
        C12861yT r0 = new C12861yT(r3, r2);
        AAZ(r0);
        return r0;
    }

    @Override // X.AbstractC12981yg
    @SchedulerSupport("none")
    public final void AAZ(AbstractC12941yc r8) {
        AbstractC12271xB r1;
        AtomicReference<AnonymousClass201[]> atomicReference;
        AnonymousClass201[] r3;
        AnonymousClass201[] r0;
        AnonymousClass219.A01(r8, "s is null");
        try {
            AnonymousClass219.A01(r8, "The RxJavaPlugins.onSubscribe hook returned a null CompletableObserver. Please check the handler provided to RxJavaPlugins.setOnCompletableSubscribe for invalid null returns. Further reading: https://github.com/ReactiveX/RxJava/wiki/Plugins");
            if (this instanceof AnonymousClass1z9) {
                AnonymousClass1z9 r4 = (AnonymousClass1z9) this;
                AnonymousClass1z8 r12 = new AnonymousClass1z8(r8, r4.A01);
                r8.A8A(r12);
                r4.A00.AAb(r12);
            } else if (this instanceof C13661zx) {
                ((C13661zx) this).A00.AAa(new C13651zw(r8));
            } else if (this instanceof AnonymousClass1z7) {
                AnonymousClass1z7 r42 = (AnonymousClass1z7) this;
                r42.A05.AAZ(new C13201z6(r42, r8));
            } else if (this instanceof C12971yf) {
                C12971yf r43 = (C12971yf) this;
                r43.A00.AAZ(new RunnableC12961ye(r8, r43.A01));
            } else if (this instanceof C13641zv) {
                ((C13641zv) this).A00.AAb(new C13631zu(r8));
            } else if (this instanceof AnonymousClass1z3) {
                AnonymousClass1z1.complete(r8);
            } else if (this instanceof C12931yb) {
                C12931yb r44 = (C12931yb) this;
                C12771yI r32 = new C12771yI(r8);
                r8.A8A(r32);
                try {
                    r44.A00.subscribe(r32);
                } catch (Throwable th) {
                    if (r1 != null) {
                        r1.dispose();
                    }
                    throw th;
                }
            } else if (!(this instanceof C13221zB)) {
                C13621zt r45 = (C13621zt) this;
                AnonymousClass201 r6 = new AnonymousClass201(r45, r8);
                r8.A8A(r6);
                do {
                    atomicReference = r45.A03;
                    r3 = atomicReference.get();
                    if (r3 == C13621zt.A05) {
                        Throwable th2 = r45.A00;
                        if (th2 != null) {
                            r8.onError(th2);
                            return;
                        } else {
                            r8.onComplete();
                            return;
                        }
                    } else {
                        int length = r3.length;
                        r0 = new AnonymousClass201[(length + 1)];
                        System.arraycopy(r3, 0, r0, 0, length);
                        r0[length] = r6;
                    }
                } while (!atomicReference.compareAndSet(r3, r0));
                if (r6.get()) {
                    r45.A03(r6);
                }
                if (r45.A02.compareAndSet(false, true)) {
                    r45.A01.AAZ(r45);
                }
            } else {
                C13211zA r13 = new C13211zA(r8, ((C13221zB) this).A00);
                r8.A8A(r13.sd);
                r13.A00();
            }
        } catch (NullPointerException e) {
            throw e;
        } catch (Throwable th3) {
            C12261xA.A00(th3);
            AnonymousClass1y3.A01(th3);
            NullPointerException nullPointerException = new NullPointerException("Actually not, but can't pass out an exception otherwise...");
            nullPointerException.initCause(th3);
            throw nullPointerException;
        }
    }
}
