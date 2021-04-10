package X;

import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.20Z  reason: invalid class name */
public final class AnonymousClass20Z<T, R> extends AtomicInteger implements AbstractC12271xB {
    public static final long serialVersionUID = 8567835998786448817L;
    public int active;
    public volatile boolean cancelled;
    public final AbstractC13031yl<? super Object[], ? extends R> combiner;
    public int complete;
    public final boolean delayError;
    public volatile boolean done;
    public final AnonymousClass1yM<? super R> downstream;
    public final AnonymousClass20G errors = new AnonymousClass20G();
    public Object[] latest;
    public final C137120d<T, R>[] observers;
    public final C12451xc<Object[]> queue;

    /* JADX WARN: Incorrect args count in method signature: (LX/1yM<-TR;>;LX/1yl<-[Ljava/lang/Object;+TR;>;IIZ)V */
    public AnonymousClass20Z(AnonymousClass1yM r4, AbstractC13031yl r5, int i, int i2) {
        this.downstream = r4;
        this.combiner = r5;
        this.delayError = false;
        this.latest = new Object[i];
        C137120d<T, R>[] r2 = new C137120d[i];
        for (int i3 = 0; i3 < i; i3++) {
            r2[i3] = new C137120d<>(this, i3);
        }
        this.observers = r2;
        this.queue = new C12451xc<>(i2);
    }

    private final void A02(C12451xc<?> r2) {
        synchronized (this) {
            this.latest = null;
        }
        r2.clear();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v2, resolved type: X.1xc<java.lang.Object[]> */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0023, code lost:
        if (r0 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0025, code lost:
        A01(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A03(int r4, T r5) {
        /*
            r3 = this;
            monitor-enter(r3)
            java.lang.Object[] r2 = r3.latest     // Catch:{ all -> 0x0029 }
            if (r2 != 0) goto L_0x0007
            monitor-exit(r3)     // Catch:{ all -> 0x0029 }
            return
        L_0x0007:
            r0 = r2[r4]     // Catch:{ all -> 0x0029 }
            int r1 = r3.active     // Catch:{ all -> 0x0029 }
            if (r0 != 0) goto L_0x0011
            int r1 = r1 + 1
            r3.active = r1     // Catch:{ all -> 0x0029 }
        L_0x0011:
            r2[r4] = r5     // Catch:{ all -> 0x0029 }
            int r0 = r2.length     // Catch:{ all -> 0x0029 }
            if (r1 != r0) goto L_0x0021
            X.1xc<java.lang.Object[]> r1 = r3.queue     // Catch:{ all -> 0x0029 }
            java.lang.Object r0 = r2.clone()     // Catch:{ all -> 0x0029 }
            r1.offer(r0)     // Catch:{ all -> 0x0029 }
            r0 = 1
            goto L_0x0022
        L_0x0021:
            r0 = 0
        L_0x0022:
            monitor-exit(r3)     // Catch:{ all -> 0x0029 }
            if (r0 == 0) goto L_0x0028
            A01(r3)
        L_0x0028:
            return
        L_0x0029:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass20Z.A03(int, java.lang.Object):void");
    }

    public static final void A00(AnonymousClass20Z r3) {
        for (C137120d<T, R> r0 : r3.observers) {
            EnumC12511xi.dispose(r0);
        }
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        if (!this.cancelled) {
            this.cancelled = true;
            A00(this);
            if (getAndIncrement() == 0) {
                A02(this.queue);
            }
        }
    }

    public static final void A01(AnonymousClass20Z r7) {
        Throwable A00;
        if (r7.getAndIncrement() == 0) {
            C12451xc<Object[]> r5 = r7.queue;
            AnonymousClass1yM<? super R> r3 = r7.downstream;
            boolean z = r7.delayError;
            int i = 1;
            while (!r7.cancelled) {
                if (z || r7.errors.get() == null) {
                    boolean z2 = r7.done;
                    Object[] poll = r5.poll();
                    boolean z3 = false;
                    if (poll == null) {
                        z3 = true;
                    }
                    if (z2) {
                        if (z3) {
                            r7.A02(r5);
                            A00 = r7.errors.A00();
                            if (A00 == null) {
                                r3.onComplete();
                                return;
                            }
                        }
                    } else if (z3) {
                        i = r7.addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    }
                    try {
                        Object obj = (Object) r7.combiner.apply(poll);
                        AnonymousClass219.A01(obj, "The combiner returned a null value");
                        r3.onNext(obj);
                    } catch (Throwable th) {
                        C12261xA.A00(th);
                        r7.errors.A01(th);
                        A00(r7);
                        r7.A02(r5);
                        r3.onError(r7.errors.A00());
                        return;
                    }
                } else {
                    A00(r7);
                    r7.A02(r5);
                    A00 = r7.errors.A00();
                }
                r3.onError(A00);
                return;
            }
            r7.A02(r5);
        }
    }
}
