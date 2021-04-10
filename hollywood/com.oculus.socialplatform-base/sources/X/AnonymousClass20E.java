package X;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.20E  reason: invalid class name */
public final class AnonymousClass20E<T, R> extends AtomicInteger implements AnonymousClass1yM<T>, AbstractC12271xB {
    public static final long serialVersionUID = -6951100001833242599L;
    public volatile boolean active;
    public final int bufferSize;
    public volatile boolean cancelled;
    public volatile boolean done;
    public final AnonymousClass1yM<? super R> downstream;
    public final AnonymousClass20G error = new AnonymousClass20G();
    public final AbstractC13031yl<? super T, ? extends AbstractC13121yu<? extends R>> mapper;
    public final AnonymousClass20F<R> observer;
    public AbstractC13481zf<T> queue;
    public int sourceMode;
    public final boolean tillTheEnd;
    public AbstractC12271xB upstream;

    @Override // X.AbstractC12271xB
    public final void dispose() {
        this.cancelled = true;
        this.upstream.dispose();
        EnumC12511xi.dispose(this.observer);
    }

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        this.done = true;
        A00();
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
                    A00();
                    return;
                } else if (requestFusion == 2) {
                    this.sourceMode = requestFusion;
                    this.queue = r32;
                    this.downstream.A8A(this);
                }
            }
            this.queue = new C12451xc(this.bufferSize);
            this.downstream.A8A(this);
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        if (this.error.A01(th)) {
            this.done = true;
            A00();
            return;
        }
        AnonymousClass1y3.A01(th);
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
        if (this.sourceMode == 0) {
            this.queue.offer(t);
        }
        A00();
    }

    public AnonymousClass20E(AnonymousClass1yM<? super R> r2, AbstractC13031yl<? super T, ? extends AbstractC13121yu<? extends R>> r3, int i, boolean z) {
        this.downstream = r2;
        this.mapper = r3;
        this.bufferSize = i;
        this.tillTheEnd = z;
        this.observer = new AnonymousClass20F<>(r2, this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x004a, code lost:
        if (r0 == false) goto L_0x004c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A00() {
        /*
        // Method dump skipped, instructions count: 163
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass20E.A00():void");
    }
}
