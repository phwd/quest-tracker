package X;

import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.20I  reason: invalid class name */
public final class AnonymousClass20I<T, R> extends AtomicInteger implements AnonymousClass1yM<T>, AbstractC12271xB, AnonymousClass21A<R> {
    public static final long serialVersionUID = 8080567949447303262L;
    public int activeCount;
    public volatile boolean cancelled;
    public AnonymousClass20J<R> current;
    public volatile boolean done;
    public final AnonymousClass1yM<? super R> downstream;
    public final AnonymousClass20G error = new AnonymousClass20G();
    public final AnonymousClass20H errorMode;
    public final AbstractC13031yl<? super T, ? extends AbstractC13121yu<? extends R>> mapper;
    public final int maxConcurrency;
    public final ArrayDeque<AnonymousClass20J<R>> observers = new ArrayDeque<>();
    public final int prefetch;
    public AbstractC13481zf<T> queue;
    public int sourceMode;
    public AbstractC12271xB upstream;

    @Override // X.AnonymousClass21A
    public final void A5j(AnonymousClass20J<R> r2) {
        r2.done = true;
        A2g();
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        this.cancelled = true;
        if (getAndIncrement() == 0) {
            this.queue.clear();
            A00();
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        this.done = true;
        A2g();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        if (r0 != null) goto L_0x0004;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0.dispose();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0007, code lost:
        r0 = r1.observers.poll();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000f, code lost:
        if (r0 != null) goto L_0x0004;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x0011, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void A00() {
        /*
            r1 = this;
            X.20J<R> r0 = r1.current
            if (r0 == 0) goto L_0x0007
        L_0x0004:
            r0.dispose()
        L_0x0007:
            java.util.ArrayDeque<X.20J<R>> r0 = r1.observers
            java.lang.Object r0 = r0.poll()
            X.20J r0 = (X.AnonymousClass20J) r0
            if (r0 != 0) goto L_0x0004
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass20I.A00():void");
    }

    @Override // X.AnonymousClass21A
    public final void A5k(AnonymousClass20J<R> r3, Throwable th) {
        if (this.error.A01(th)) {
            if (this.errorMode == AnonymousClass20H.IMMEDIATE) {
                this.upstream.dispose();
            }
            r3.done = true;
            A2g();
            return;
        }
        AnonymousClass1y3.A01(th);
    }

    @Override // X.AnonymousClass21A
    public final void A5l(AnonymousClass20J<R> r2, R r) {
        r2.queue.offer(r);
        A2g();
    }

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r3) {
        if (EnumC12511xi.validate(this.upstream, r3)) {
            this.upstream = r3;
            if (r3 instanceof AnonymousClass12n) {
                AbstractC13491zg r32 = (AbstractC13491zg) r3;
                int requestFusion = r32.requestFusion(3);
                if (requestFusion == 1) {
                    this.sourceMode = requestFusion;
                    this.queue = r32;
                    this.done = true;
                    this.downstream.A8A(this);
                    A2g();
                    return;
                } else if (requestFusion == 2) {
                    this.sourceMode = requestFusion;
                    this.queue = r32;
                    this.downstream.A8A(this);
                }
            }
            this.queue = new C12451xc(this.prefetch);
            this.downstream.A8A(this);
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        if (this.error.A01(th)) {
            this.done = true;
            A2g();
            return;
        }
        AnonymousClass1y3.A01(th);
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
        if (this.sourceMode == 0) {
            this.queue.offer(t);
        }
        A2g();
    }

    public AnonymousClass20I(AnonymousClass1yM<? super R> r2, AbstractC13031yl<? super T, ? extends AbstractC13121yu<? extends R>> r3, int i, int i2, AnonymousClass20H r6) {
        this.downstream = r2;
        this.mapper = r3;
        this.maxConcurrency = i;
        this.prefetch = i2;
        this.errorMode = r6;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0099, code lost:
        if (r0 == false) goto L_0x009b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x009d, code lost:
        if (r10 != null) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x0109, code lost:
        r4.clear();
        A00();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x010f, code lost:
        return;
     */
    @Override // X.AnonymousClass21A
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A2g() {
        /*
        // Method dump skipped, instructions count: 272
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass20I.A2g():void");
    }
}
