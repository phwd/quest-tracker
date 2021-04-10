package X;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.20C  reason: invalid class name */
public final class AnonymousClass20C<T, U> extends AtomicInteger implements AnonymousClass1yM<T>, AbstractC12271xB {
    public static final long serialVersionUID = 8828587559905699186L;
    public volatile boolean active;
    public final int bufferSize;
    public volatile boolean disposed;
    public volatile boolean done;
    public final AnonymousClass1yM<? super U> downstream;
    public int fusionMode;
    public final AnonymousClass20B<U> inner;
    public final AbstractC13031yl<? super T, ? extends AbstractC13121yu<? extends U>> mapper;
    public AbstractC13481zf<T> queue;
    public AbstractC12271xB upstream;

    @Override // X.AbstractC12271xB
    public final void dispose() {
        this.disposed = true;
        EnumC12511xi.dispose(this.inner);
        this.upstream.dispose();
        if (getAndIncrement() == 0) {
            this.queue.clear();
        }
    }

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r3) {
        if (EnumC12511xi.validate(this.upstream, r3)) {
            this.upstream = r3;
            if (r3 instanceof AnonymousClass12n) {
                AbstractC13491zg r32 = (AbstractC13491zg) r3;
                int requestFusion = r32.requestFusion(3);
                if (requestFusion == 1) {
                    this.fusionMode = requestFusion;
                    this.queue = r32;
                    this.done = true;
                    this.downstream.A8A(this);
                    A00(this);
                    return;
                } else if (requestFusion == 2) {
                    this.fusionMode = requestFusion;
                    this.queue = r32;
                    this.downstream.A8A(this);
                }
            }
            this.queue = new C12451xc(this.bufferSize);
            this.downstream.A8A(this);
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        if (!this.done) {
            this.done = true;
            A00(this);
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        if (this.done) {
            AnonymousClass1y3.A01(th);
            return;
        }
        this.done = true;
        dispose();
        this.downstream.onError(th);
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
        if (!this.done) {
            if (this.fusionMode == 0) {
                this.queue.offer(t);
            }
            A00(this);
        }
    }

    public AnonymousClass20C(AnonymousClass1yM<? super U> r2, AbstractC13031yl<? super T, ? extends AbstractC13121yu<? extends U>> r3, int i) {
        this.downstream = r2;
        this.mapper = r3;
        this.bufferSize = i;
        this.inner = new AnonymousClass20B<>(r2, this);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002e, code lost:
        if (r0 == false) goto L_0x0030;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void A00(X.AnonymousClass20C r4) {
        /*
            int r0 = r4.getAndIncrement()
            if (r0 == 0) goto L_0x0007
            return
        L_0x0007:
            boolean r0 = r4.disposed
            if (r0 == 0) goto L_0x0011
            X.1zf<T> r0 = r4.queue
            r0.clear()
            return
        L_0x0011:
            boolean r0 = r4.active
            if (r0 != 0) goto L_0x0044
            boolean r3 = r4.done
            X.1zf<T> r0 = r4.queue     // Catch:{ all -> 0x004b }
            java.lang.Object r1 = r0.poll()     // Catch:{ all -> 0x004b }
            r2 = 1
            r0 = 0
            if (r1 != 0) goto L_0x0022
            r0 = 1
        L_0x0022:
            if (r3 == 0) goto L_0x002e
            if (r0 == 0) goto L_0x0030
            r4.disposed = r2
            X.1yM<? super U> r0 = r4.downstream
            r0.onComplete()
            return
        L_0x002e:
            if (r0 != 0) goto L_0x0044
        L_0x0030:
            X.1yl<? super T, ? extends X.1yu<? extends U>> r0 = r4.mapper
            java.lang.Object r1 = r0.apply(r1)
            java.lang.String r0 = "The mapper returned a null ObservableSource"
            X.AnonymousClass219.A01(r1, r0)
            X.1yu r1 = (X.AbstractC13121yu) r1
            r4.active = r2
            X.20B<U> r0 = r4.inner
            r1.AAa(r0)
        L_0x0044:
            int r0 = r4.decrementAndGet()
            if (r0 != 0) goto L_0x0007
            return
        L_0x004b:
            r1 = move-exception
            X.C12261xA.A00(r1)
            r4.dispose()
            X.1zf<T> r0 = r4.queue
            r0.clear()
            X.1yM<? super U> r0 = r4.downstream
            r0.onError(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass20C.A00(X.20C):void");
    }
}
