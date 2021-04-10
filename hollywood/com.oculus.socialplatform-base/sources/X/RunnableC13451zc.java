package X;

import io.reactivex.annotations.Nullable;

/* renamed from: X.1zc  reason: invalid class name and case insensitive filesystem */
public final class RunnableC13451zc<T> extends AbstractC13361zS<T> implements AnonymousClass1yM<T>, Runnable {
    public static final String __redex_internal_original_name = "io.reactivex.internal.operators.observable.ObservableObserveOn$ObserveOnObserver";
    public static final long serialVersionUID = 6576896619930983584L;
    public final int bufferSize;
    public final boolean delayError = false;
    public volatile boolean disposed;
    public volatile boolean done;
    public final AnonymousClass1yM<? super T> downstream;
    public Throwable error;
    public boolean outputFused;
    public AbstractC13481zf<T> queue;
    public int sourceMode;
    public AbstractC12271xB upstream;
    public final AbstractC12411xQ worker;

    /* JADX WARN: Incorrect args count in method signature: (LX/1yM<-TT;>;LX/1xQ;ZI)V */
    public RunnableC13451zc(AnonymousClass1yM r2, AbstractC12411xQ r3, int i) {
        this.downstream = r2;
        this.worker = r3;
        this.bufferSize = i;
    }

    @Override // X.AbstractC13491zg
    public final int requestFusion(int i) {
        if ((i & 2) == 0) {
            return 0;
        }
        this.outputFused = true;
        return 2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0017, code lost:
        if (r1 != null) goto L_0x0019;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean A00(boolean r4, boolean r5, X.AnonymousClass1yM<? super T> r6) {
        /*
            r3 = this;
            boolean r0 = r3.disposed
            r2 = 1
            if (r0 == 0) goto L_0x000b
            X.1zf<T> r0 = r3.queue
            r0.clear()
            return r2
        L_0x000b:
            if (r4 == 0) goto L_0x0034
            java.lang.Throwable r1 = r3.error
            boolean r0 = r3.delayError
            if (r0 == 0) goto L_0x0022
            if (r5 == 0) goto L_0x0034
            r3.disposed = r2
            if (r1 == 0) goto L_0x0030
        L_0x0019:
            r6.onError(r1)
        L_0x001c:
            X.1xQ r0 = r3.worker
            r0.dispose()
            return r2
        L_0x0022:
            if (r1 == 0) goto L_0x002c
            r3.disposed = r2
            X.1zf<T> r0 = r3.queue
            r0.clear()
            goto L_0x0019
        L_0x002c:
            if (r5 == 0) goto L_0x0034
            r3.disposed = r2
        L_0x0030:
            r6.onComplete()
            goto L_0x001c
        L_0x0034:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.RunnableC13451zc.A00(boolean, boolean, X.1yM):boolean");
    }

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r3) {
        if (EnumC12511xi.validate(this.upstream, r3)) {
            this.upstream = r3;
            if (r3 instanceof AnonymousClass12n) {
                AbstractC13491zg r32 = (AbstractC13491zg) r3;
                int requestFusion = r32.requestFusion(7);
                if (requestFusion == 1) {
                    this.sourceMode = requestFusion;
                    this.queue = r32;
                    this.done = true;
                    this.downstream.A8A(this);
                    if (getAndIncrement() == 0) {
                        this.worker.A01(this);
                        return;
                    }
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

    @Override // X.AbstractC13481zf
    public final void clear() {
        this.queue.clear();
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        if (!this.disposed) {
            this.disposed = true;
            this.upstream.dispose();
            this.worker.dispose();
            if (getAndIncrement() == 0) {
                this.queue.clear();
            }
        }
    }

    @Override // X.AbstractC13481zf
    public final boolean isEmpty() {
        return this.queue.isEmpty();
    }

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        if (!this.done) {
            this.done = true;
            if (getAndIncrement() == 0) {
                this.worker.A01(this);
            }
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        if (this.done) {
            AnonymousClass1y3.A01(th);
            return;
        }
        this.error = th;
        this.done = true;
        if (getAndIncrement() == 0) {
            this.worker.A01(this);
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
        if (!this.done) {
            if (this.sourceMode != 2) {
                this.queue.offer(t);
            }
            if (getAndIncrement() == 0) {
                this.worker.A01(this);
            }
        }
    }

    @Override // X.AbstractC13481zf
    @Nullable
    public final T poll() throws Exception {
        return this.queue.poll();
    }

    public final void run() {
        if (this.outputFused) {
            int i = 1;
            while (!this.disposed) {
                boolean z = this.done;
                Throwable th = this.error;
                if (this.delayError || !z || th == null) {
                    this.downstream.onNext(null);
                    if (z) {
                        this.disposed = true;
                        th = this.error;
                        if (th == null) {
                            this.downstream.onComplete();
                            this.worker.dispose();
                            return;
                        }
                    } else {
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    }
                } else {
                    this.disposed = true;
                }
                this.downstream.onError(th);
                this.worker.dispose();
                return;
            }
            return;
        }
        AbstractC13481zf<T> r6 = this.queue;
        AnonymousClass1yM<? super T> r5 = this.downstream;
        int i2 = 1;
        while (!A00(this.done, r6.isEmpty(), r5)) {
            while (true) {
                boolean z2 = this.done;
                try {
                    T poll = r6.poll();
                    boolean z3 = false;
                    if (poll == null) {
                        z3 = true;
                    }
                    if (A00(z2, z3, r5)) {
                        return;
                    }
                    if (z3) {
                        i2 = addAndGet(-i2);
                        if (i2 == 0) {
                            return;
                        }
                    } else {
                        r5.onNext(poll);
                    }
                } catch (Throwable th2) {
                    C12261xA.A00(th2);
                    this.disposed = true;
                    this.upstream.dispose();
                    r6.clear();
                    r5.onError(th2);
                    this.worker.dispose();
                    return;
                }
            }
        }
    }
}
