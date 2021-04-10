package X;

import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.SchedulerSupport;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.20a  reason: invalid class name and case insensitive filesystem */
public abstract class AbstractC136820a<T> implements AbstractC13121yu<T> {
    /* JADX WARN: Incorrect args count in method signature: <R:Ljava/lang/Object;>(LX/1yl<-TT;+LX/1yu<+TR;>;>;I)LX/20a<TR;>; */
    @SchedulerSupport("none")
    @CheckReturnValue
    public final AbstractC136820a A0C(AbstractC13031yl r4) {
        AnonymousClass219.A01(r4, "mapper is null");
        if (!(this instanceof AnonymousClass1y8)) {
            return new AnonymousClass20D(this, r4, 5, AnonymousClass20H.IMMEDIATE);
        }
        Object call = ((AnonymousClass1y8) this).call();
        if (call == null) {
            return AnonymousClass21S.A00;
        }
        return new AnonymousClass20L(call, r4);
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/1yS<-TT;>;LX/1yS<-Ljava/lang/Throwable;>;LX/1yV;LX/1yV;)LX/20a<TT;>; */
    @SchedulerSupport("none")
    @CheckReturnValue
    public static AbstractC136820a A00(AbstractC136820a r1, AbstractC12851yS r2, AbstractC12851yS r3, AbstractC12881yV r4, AbstractC12881yV r5) {
        AnonymousClass219.A01(r2, "onNext is null");
        AnonymousClass219.A01(r3, "onError is null");
        AnonymousClass219.A01(r4, "onComplete is null");
        AnonymousClass219.A01(r5, "onAfterTerminate is null");
        return new C13071yp(r1, r2, r3, r4, r5);
    }

    @SchedulerSupport("none")
    @CheckReturnValue
    public static <T> AbstractC136820a<T> A01(AnonymousClass1vc<T> r1) {
        AnonymousClass219.A01(r1, "source is null");
        return new C12801yL(r1);
    }

    @SchedulerSupport("none")
    @CheckReturnValue
    public static <T1, T2, T3, T4, R> AbstractC136820a<R> A02(AbstractC13121yu<? extends T1> r3, AbstractC13121yu<? extends T2> r4, AbstractC13121yu<? extends T3> r5, AbstractC13121yu<? extends T4> r6, AnonymousClass21Z<? super T1, ? super T2, ? super T3, ? super T4, ? extends R> r7) {
        AnonymousClass219.A01(r3, "source1 is null");
        AnonymousClass219.A01(r4, "source2 is null");
        AnonymousClass219.A01(r5, "source3 is null");
        AnonymousClass219.A01(r6, "source4 is null");
        AnonymousClass219.A01(r7, "f is null");
        return A04(new C138520r(r7), AbstractC13521zj.A00, r3, r4, r5, r6);
    }

    @SchedulerSupport("none")
    @CheckReturnValue
    public static <T1, T2, R> AbstractC136820a<R> A03(AbstractC13121yu<? extends T1> r3, AbstractC13121yu<? extends T2> r4, AbstractC13321zO<? super T1, ? super T2, ? extends R> r5) {
        AnonymousClass219.A01(r3, "source1 is null");
        AnonymousClass219.A01(r4, "source2 is null");
        AnonymousClass219.A01(r5, "f is null");
        return A04(new C13271zG(r5), AbstractC13521zj.A00, r3, r4);
    }

    @SchedulerSupport("none")
    @CheckReturnValue
    public static <T, R> AbstractC136820a<R> A04(AbstractC13031yl<? super Object[], ? extends R> r2, int i, AbstractC13121yu<? extends T>... r4) {
        AnonymousClass219.A01(r4, "sources is null");
        if (r4.length == 0) {
            return (AbstractC136820a<R>) AnonymousClass21S.A00;
        }
        AnonymousClass219.A01(r2, "combiner is null");
        AnonymousClass219.A00(i, "bufferSize");
        return new C136920b(r4, r2, i << 1);
    }

    @SchedulerSupport("none")
    @CheckReturnValue
    public static <T> AbstractC136820a<T> A05(T... tArr) {
        AnonymousClass219.A01(tArr, "items is null");
        int length = tArr.length;
        if (length == 0) {
            return (AbstractC136820a<T>) AnonymousClass21S.A00;
        }
        if (length != 1) {
            return new AnonymousClass212(tArr);
        }
        T t = tArr[0];
        AnonymousClass219.A01(t, "The item is null");
        return new C138820u(t);
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/1yS<-TT;>;LX/1yS<-Ljava/lang/Throwable;>;LX/1yV;LX/1yS<-LX/1xB;>;)LX/1xB; */
    @SchedulerSupport("none")
    @CheckReturnValue
    public static final AbstractC12271xB A06(AbstractC136820a r1, AbstractC12851yS r2, AbstractC12851yS r3, AbstractC12881yV r4, AbstractC12851yS r5) {
        AnonymousClass219.A01(r2, "onNext is null");
        AnonymousClass219.A01(r3, "onError is null");
        AnonymousClass219.A01(r4, "onComplete is null");
        AnonymousClass219.A01(r5, "onSubscribe is null");
        C12871yU r0 = new C12871yU(r2, r3, r4, r5);
        r1.AAa(r0);
        return r0;
    }

    @SchedulerSupport("custom")
    @CheckReturnValue
    public final AbstractC136820a<T> A08(AbstractC12361xL r3) {
        int i = AbstractC13521zj.A00;
        AnonymousClass219.A01(r3, "scheduler is null");
        AnonymousClass219.A00(i, "bufferSize");
        return new C13101ys(this, r3, i);
    }

    @SchedulerSupport("none")
    @CheckReturnValue
    public final AbstractC136820a<T> A09(AbstractC12851yS<? super AnonymousClass215<T>> r5) {
        AnonymousClass219.A01(r5, "consumer is null");
        return A00(this, new C139020w(r5), new C139120x(r5), new AnonymousClass216(r5), C137220e.A03);
    }

    @SchedulerSupport("none")
    @CheckReturnValue
    public final AbstractC136820a<T> A0A(AbstractC12851yS<? super T> r3) {
        AbstractC12851yS<Object> r1 = C137220e.A04;
        AbstractC12881yV r0 = C137220e.A03;
        return A00(this, r3, r1, r0, r0);
    }

    @SchedulerSupport("none")
    @CheckReturnValue
    public final AbstractC136820a<T> A0B(AbstractC12851yS<? super AbstractC12271xB> r2, AbstractC12881yV r3) {
        AnonymousClass219.A01(r2, "onSubscribe is null");
        AnonymousClass219.A01(r3, "onDispose is null");
        return new C13091yr(this, r2, r3);
    }

    @SchedulerSupport("none")
    @CheckReturnValue
    public final <R> AbstractC136820a<R> A0D(AbstractC13031yl<? super T, ? extends AbstractC13121yu<? extends R>> r3) {
        int i = AbstractC13521zj.A00;
        AnonymousClass219.A01(r3, "mapper is null");
        AnonymousClass219.A00(i, "bufferSize");
        if (!(this instanceof AnonymousClass1y8)) {
            return new AnonymousClass20A(this, r3, i);
        }
        Object call = ((AnonymousClass1y8) this).call();
        return call == null ? (AbstractC136820a<R>) AnonymousClass21S.A00 : new AnonymousClass20L(call, r3);
    }

    @SchedulerSupport("none")
    @CheckReturnValue
    public final <R> AbstractC136820a<R> A0E(AbstractC13031yl<? super T, ? extends AbstractC13121yu<? extends R>> r3) {
        int i = AbstractC13521zj.A00;
        AnonymousClass219.A01(r3, "mapper is null");
        AnonymousClass219.A00(i, "bufferSize");
        if (!(this instanceof AnonymousClass1y8)) {
            return new C138120n(this, r3, i);
        }
        Object call = ((AnonymousClass1y8) this).call();
        return call == null ? (AbstractC136820a<R>) AnonymousClass21S.A00 : new AnonymousClass20L(call, r3);
    }

    @SchedulerSupport("none")
    @CheckReturnValue
    public final AbstractC136820a<T> A0F(T t) {
        AnonymousClass219.A01(t, "item is null");
        AnonymousClass219.A01(t, "The item is null");
        return new AnonymousClass20D(A05(new C138820u(t), this), C137220e.A08, AbstractC13521zj.A00, AnonymousClass20H.BOUNDARY);
    }

    /* JADX WARN: Incorrect args count in method signature: (JLjava/util/concurrent/TimeUnit;)LX/20a<TT;>; */
    @SchedulerSupport("io.reactivex:computation")
    @CheckReturnValue
    public final AbstractC136820a A0G(TimeUnit timeUnit) {
        AbstractC12361xL r1 = C12581xp.A02;
        AnonymousClass219.A01(timeUnit, "unit is null");
        AnonymousClass219.A01(r1, "scheduler is null");
        return new C13061yo(this, timeUnit, r1);
    }

    @SchedulerSupport("none")
    @CheckReturnValue
    public final <U> AbstractC13251zE<U> A0H(U u, AbstractC140121h<? super U, ? super T> r4) {
        AnonymousClass219.A01(u, "initialValue is null");
        AnonymousClass218 r1 = new AnonymousClass218(u);
        AnonymousClass219.A01(r4, "collector is null");
        return new C137020c(this, r1, r4);
    }

    /* JADX WARN: Incorrect args count in method signature: (I)LX/21O<TT;>; */
    @SchedulerSupport("none")
    @CheckReturnValue
    public final AnonymousClass21O A0I() {
        AnonymousClass21V r3 = new AnonymousClass21V();
        AtomicReference atomicReference = new AtomicReference();
        return new AnonymousClass21I(new AnonymousClass21K(atomicReference, r3), this, atomicReference, r3);
    }

    @SchedulerSupport("none")
    @CheckReturnValue
    public final <R> R A0J(@NonNull AbstractC140021g<T, ? extends R> r2) {
        AnonymousClass219.A01(r2, "converter is null");
        return (R) r2.apply(this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:291:0x0489, code lost:
        if (r2 == null) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:293:0x048f, code lost:
        if (r4.test(r2) == false) goto L_0x0492;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:294:0x0491, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:296:0x0494, code lost:
        if (r4.A07 != false) goto L_0x04c8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:297:0x0496, code lost:
        monitor-enter(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:299:?, code lost:
        r2 = r4.A02;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:300:0x0499, code lost:
        if (r2 != null) goto L_0x04a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:301:0x049b, code lost:
        r4.A03 = false;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:302:0x049e, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:304:0x04a0, code lost:
        r4.A02 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:305:0x04a3, code lost:
        monitor-exit(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:306:0x04a4, code lost:
        r1 = r2.A03;
        r3 = r2.A02;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:307:0x04a8, code lost:
        if (r1 == null) goto L_0x0492;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:308:0x04aa, code lost:
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:309:0x04ab, code lost:
        if (r2 >= r3) goto L_0x04ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:310:0x04ad, code lost:
        r0 = r1[r2];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:311:0x04af, code lost:
        if (r0 == null) goto L_0x04ba;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:313:0x04b5, code lost:
        if (r4.test(r0) != false) goto L_0x0492;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:314:0x04b7, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:315:0x04ba, code lost:
        r1 = r1[r3];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:316:0x04bf, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:357:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:363:?, code lost:
        return;
     */
    @Override // X.AbstractC13121yu
    @io.reactivex.annotations.SchedulerSupport("none")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void AAa(X.AnonymousClass1yM<? super T> r14) {
        /*
        // Method dump skipped, instructions count: 1245
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC136820a.AAa(X.1yM):void");
    }
}
