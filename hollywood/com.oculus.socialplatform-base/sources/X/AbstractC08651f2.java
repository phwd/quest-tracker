package X;

import androidx.annotation.NonNull;
import com.bumptech.glide.load.engine.DecodeJob;
import com.bumptech.glide.load.engine.EngineJobListener;
import com.bumptech.glide.load.engine.EngineResource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.1f2  reason: invalid class name and case insensitive filesystem */
public interface AbstractC08651f2<R> extends DecodeJob.Callback<R> {
    public static final C08991fr A0O = new C08991fr();
    public AnonymousClass1fM A00;
    public AbstractC06491aL A01;
    public AnonymousClass1f1<R> A02;
    public C08701f8<?> A03;
    public AnonymousClass1Or A04;
    public AnonymousClass1fR<?> A05;
    public boolean A06;
    public boolean A07;
    public boolean A08;
    public boolean A09;
    public boolean A0A;
    public boolean A0B;
    public final AnonymousClass06o<AbstractC08651f2<?>> A0C;
    public final AbstractC08671f5 A0D;
    public final AbstractC08671f5 A0E;
    public final C08991fr A0F;
    public final AnonymousClass1fI A0G = new AnonymousClass1fI();
    public final ExecutorServiceC07671cp A0H;
    public final ExecutorServiceC07671cp A0I;
    public final ExecutorServiceC07671cp A0J;
    public final ExecutorServiceC07671cp A0K;
    public final AbstractC08341eQ A0L = new C08331eP();
    public final AtomicInteger A0M = new AtomicInteger();
    public volatile boolean A0N;

    static synchronized default void A00(AbstractC08651f2 r4) {
        boolean z;
        synchronized (r4) {
            if (r4.A01 != null) {
                r4.A0G.A00.clear();
                r4.A01 = null;
                r4.A03 = null;
                r4.A05 = null;
                r4.A06 = false;
                r4.A0N = false;
                r4.A07 = false;
                AnonymousClass1f1<R> r2 = r4.A02;
                C08881fg r1 = r2.A0N;
                synchronized (r1) {
                    r1.A02 = true;
                    if (r1.A01 || r1.A00) {
                        z = true;
                    } else {
                        z = false;
                    }
                }
                if (z) {
                    AnonymousClass1f1.A07(r2);
                }
                r4.A02 = null;
                r4.A04 = null;
                r4.A00 = null;
                r4.A0C.A8z(r4);
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x000c, code lost:
        if (r2.A0N != false) goto L_0x000e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static final synchronized default void A01(X.AbstractC08651f2 r2, int r3) {
        /*
            monitor-enter(r2)
            boolean r0 = r2.A06     // Catch:{ all -> 0x0025 }
            if (r0 != 0) goto L_0x000e
            boolean r0 = r2.A07     // Catch:{ all -> 0x0025 }
            if (r0 != 0) goto L_0x000e
            boolean r0 = r2.A0N     // Catch:{ all -> 0x0025 }
            r1 = 0
            if (r0 == 0) goto L_0x000f
        L_0x000e:
            r1 = 1
        L_0x000f:
            java.lang.String r0 = "Not yet complete!"
            X.AnonymousClass1S2.A01(r1, r0)     // Catch:{ all -> 0x0025 }
            java.util.concurrent.atomic.AtomicInteger r0 = r2.A0M     // Catch:{ all -> 0x0025 }
            int r0 = r0.getAndAdd(r3)     // Catch:{ all -> 0x0025 }
            if (r0 != 0) goto L_0x0023
            X.1f8<?> r0 = r2.A03     // Catch:{ all -> 0x0025 }
            if (r0 == 0) goto L_0x0023
            r0.A01()     // Catch:{ all -> 0x0025 }
        L_0x0023:
            monitor-exit(r2)
            return
        L_0x0025:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC08651f2.A01(X.1f2, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0011, code lost:
        if (r3.A0N != false) goto L_0x0013;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    final default void A02() {
        /*
            r3 = this;
            monitor-enter(r3)
            X.1eQ r0 = r3.A0L     // Catch:{ all -> 0x0039 }
            r0.A00()     // Catch:{ all -> 0x0039 }
            boolean r0 = r3.A06     // Catch:{ all -> 0x0039 }
            if (r0 != 0) goto L_0x0013
            boolean r0 = r3.A07     // Catch:{ all -> 0x0039 }
            if (r0 != 0) goto L_0x0013
            boolean r0 = r3.A0N     // Catch:{ all -> 0x0039 }
            r1 = 0
            if (r0 == 0) goto L_0x0014
        L_0x0013:
            r1 = 1
        L_0x0014:
            java.lang.String r0 = "Not yet complete!"
            X.AnonymousClass1S2.A01(r1, r0)     // Catch:{ all -> 0x0039 }
            java.util.concurrent.atomic.AtomicInteger r0 = r3.A0M     // Catch:{ all -> 0x0039 }
            int r2 = r0.decrementAndGet()     // Catch:{ all -> 0x0039 }
            r1 = 0
            if (r2 < 0) goto L_0x0023
            r1 = 1
        L_0x0023:
            java.lang.String r0 = "Can't decrement below 0"
            X.AnonymousClass1S2.A01(r1, r0)     // Catch:{ all -> 0x0039 }
            if (r2 != 0) goto L_0x002b
            goto L_0x002d
        L_0x002b:
            r0 = 0
            goto L_0x0032
        L_0x002d:
            X.1f8<?> r0 = r3.A03     // Catch:{ all -> 0x0039 }
            A00(r3)     // Catch:{ all -> 0x0039 }
        L_0x0032:
            monitor-exit(r3)     // Catch:{ all -> 0x0039 }
            if (r0 == 0) goto L_0x0038
            r0.A00()
        L_0x0038:
            return
        L_0x0039:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AbstractC08651f2.A02():void");
    }

    final synchronized default void A04(AnonymousClass1f3 r5) {
        Map<AbstractC06491aL, AbstractC08651f2<?>> map;
        this.A0L.A00();
        List<AnonymousClass1fY> list = this.A0G.A00;
        list.remove(new AnonymousClass1fY(r5, C07681cq.A00));
        if (list.isEmpty()) {
            if (!this.A06 && !this.A07 && !this.A0N) {
                this.A0N = true;
                AnonymousClass1f1<R> r1 = this.A02;
                r1.A0T = true;
                AbstractC08981fq r0 = r1.A0S;
                if (r0 != null) {
                    r0.cancel();
                }
                AbstractC08671f5 r3 = this.A0D;
                AbstractC06491aL r2 = this.A01;
                synchronized (r3) {
                    AnonymousClass1fV r12 = r3.A05;
                    if (this.A09) {
                        map = r12.A01;
                    } else {
                        map = r12.A00;
                    }
                    if (equals(map.get(r2))) {
                        map.remove(r2);
                    }
                }
            }
            if ((this.A07 || this.A06) && this.A0M.get() == 0) {
                A00(this);
            }
        }
    }

    final synchronized default void A05(AnonymousClass1f3 r3, Executor executor) {
        this.A0L.A00();
        this.A0G.A00.add(new AnonymousClass1fY(r3, executor));
        boolean z = true;
        if (this.A07) {
            A01(this, 1);
            executor.execute(new AnonymousClass1fC(this, r3));
        } else if (this.A06) {
            A01(this, 1);
            executor.execute(new RunnableC08751fE(this, r3));
        } else {
            if (this.A0N) {
                z = false;
            }
            AnonymousClass1S2.A01(z, "Cannot add callbacks to a cancelled EngineJob");
        }
    }

    default AbstractC08651f2(ExecutorServiceC07671cp r3, ExecutorServiceC07671cp r4, ExecutorServiceC07671cp r5, ExecutorServiceC07671cp r6, EngineJobListener engineJobListener, EngineResource.ResourceListener resourceListener, AnonymousClass06o<AbstractC08651f2<?>> r9) {
        C08991fr r1 = A0O;
        this.A0I = r3;
        this.A0J = r4;
        this.A0K = r5;
        this.A0H = r6;
        this.A0D = engineJobListener;
        this.A0E = resourceListener;
        this.A0C = r9;
        this.A0F = r1;
    }

    final default void A03(AnonymousClass1f1<?> r2) {
        ExecutorServiceC07671cp r0;
        if (this.A0B) {
            r0 = this.A0K;
        } else if (this.A0A) {
            r0 = this.A0H;
        } else {
            r0 = this.A0J;
        }
        r0.execute(r2);
    }

    @NonNull
    final default AbstractC08341eQ A5G() {
        return this.A0L;
    }
}
