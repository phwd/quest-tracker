package X;

import androidx.recyclerview.widget.RecyclerView;
import io.reactivex.annotations.Nullable;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: X.1zh  reason: invalid class name and case insensitive filesystem */
public final class C13501zh<T> extends AbstractC13561zn<T> implements AbstractC13601zr<T> {
    public static final long serialVersionUID = -2514538129242366402L;
    public volatile boolean cancelled;
    public final boolean delayError;
    public volatile boolean done;
    public final AbstractC13581zp<? super T> downstream;
    public Throwable error;
    public final AbstractC12881yV onOverflow;
    public boolean outputFused;
    public final AbstractC13591zq<T> queue;
    public final AtomicLong requested = new AtomicLong();
    public AbstractC12551xm upstream;

    /* JADX WARN: Incorrect args count in method signature: (LX/1zp<-TT;>;IZZLX/1yV;)V */
    public C13501zh(AbstractC13581zp r3, int i, boolean z, AbstractC12881yV r6) {
        AbstractC13591zq<T> r0;
        this.downstream = r3;
        this.onOverflow = r6;
        this.delayError = false;
        if (z) {
            r0 = new C12451xc<>(i);
        } else {
            r0 = new C12441xb<>(i);
        }
        this.queue = r0;
    }

    @Override // X.AbstractC13581zp
    public final void onComplete() {
        this.done = true;
        if (this.outputFused) {
            this.downstream.onComplete();
        } else {
            A00();
        }
    }

    @Override // X.AbstractC13491zg
    public final int requestFusion(int i) {
        if ((i & 2) == 0) {
            return 0;
        }
        this.outputFused = true;
        return 2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0015, code lost:
        if (r1 != null) goto L_0x0017;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean A01(boolean r4, boolean r5, X.AbstractC13581zp<? super T> r6) {
        /*
            r3 = this;
            boolean r0 = r3.cancelled
            r2 = 1
            if (r0 == 0) goto L_0x000b
            X.1zq<T> r0 = r3.queue
            r0.clear()
            return r2
        L_0x000b:
            if (r4 == 0) goto L_0x002b
            boolean r0 = r3.delayError
            if (r0 == 0) goto L_0x001b
            if (r5 == 0) goto L_0x002b
            java.lang.Throwable r1 = r3.error
            if (r1 == 0) goto L_0x0027
        L_0x0017:
            r6.onError(r1)
            return r2
        L_0x001b:
            java.lang.Throwable r1 = r3.error
            if (r1 == 0) goto L_0x0025
            X.1zq<T> r0 = r3.queue
            r0.clear()
            goto L_0x0017
        L_0x0025:
            if (r5 == 0) goto L_0x002b
        L_0x0027:
            r6.onComplete()
            return r2
        L_0x002b:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C13501zh.A01(boolean, boolean, X.1zp):boolean");
    }

    @Override // X.AbstractC12551xm
    public final void cancel() {
        if (!this.cancelled) {
            this.cancelled = true;
            this.upstream.cancel();
            if (getAndIncrement() == 0) {
                this.queue.clear();
            }
        }
    }

    @Override // X.AbstractC13481zf
    public final void clear() {
        this.queue.clear();
    }

    @Override // X.AbstractC13481zf
    public final boolean isEmpty() {
        return this.queue.isEmpty();
    }

    @Override // X.AbstractC13581zp
    public final void onError(Throwable th) {
        this.error = th;
        this.done = true;
        if (this.outputFused) {
            this.downstream.onError(th);
        } else {
            A00();
        }
    }

    @Override // X.AbstractC13581zp
    public final void onNext(T t) {
        if (!this.queue.offer(t)) {
            this.upstream.cancel();
            C13611zs r1 = new C13611zs("Buffer is full");
            try {
                this.onOverflow.run();
            } catch (Throwable th) {
                C12261xA.A00(th);
                r1.initCause(th);
            }
            onError(r1);
        } else if (this.outputFused) {
            this.downstream.onNext(null);
        } else {
            A00();
        }
    }

    @Override // X.AbstractC13581zp
    public final void onSubscribe(AbstractC12551xm r3) {
        if (EnumC12531xk.validate(this.upstream, r3)) {
            this.upstream = r3;
            this.downstream.onSubscribe(this);
            r3.request(RecyclerView.FOREVER_NS);
        }
    }

    @Override // X.AbstractC13481zf
    @Nullable
    public final T poll() throws Exception {
        return this.queue.poll();
    }

    @Override // X.AbstractC12551xm
    public final void request(long j) {
        if (!this.outputFused && EnumC12531xk.validate(j)) {
            C12541xl.A00(this.requested, j);
            A00();
        }
    }

    private final void A00() {
        if (getAndIncrement() == 0) {
            AbstractC13591zq<T> r6 = this.queue;
            AbstractC13581zp<? super T> r5 = this.downstream;
            int i = 1;
            while (!A01(this.done, r6.isEmpty(), r5)) {
                long j = this.requested.get();
                long j2 = 0;
                while (j2 != j) {
                    boolean z = this.done;
                    T poll = r6.poll();
                    boolean z2 = false;
                    if (poll == null) {
                        z2 = true;
                    }
                    if (!A01(z, z2, r5)) {
                        if (z2) {
                            break;
                        }
                        r5.onNext(poll);
                        j2++;
                    } else {
                        return;
                    }
                }
                if (j2 != j || !A01(this.done, r6.isEmpty(), r5)) {
                    if (!(j2 == 0 || j == RecyclerView.FOREVER_NS)) {
                        this.requested.addAndGet(-j2);
                    }
                    i = addAndGet(-i);
                    if (i == 0) {
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }
}
