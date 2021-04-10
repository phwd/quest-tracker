package X;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.208  reason: invalid class name */
public final class AnonymousClass208<T, U> extends AtomicInteger implements AbstractC12271xB, AnonymousClass1yM<T> {
    public static final AnonymousClass209<?, ?>[] A00 = new AnonymousClass209[0];
    public static final AnonymousClass209<?, ?>[] A01 = new AnonymousClass209[0];
    public static final long serialVersionUID = -2117620485640801370L;
    public final int bufferSize;
    public volatile boolean cancelled;
    public final boolean delayErrors;
    public volatile boolean done;
    public final AnonymousClass1yM<? super U> downstream;
    public final AnonymousClass20G errors = new AnonymousClass20G();
    public long lastId;
    public int lastIndex;
    public final AbstractC13031yl<? super T, ? extends AbstractC13121yu<? extends U>> mapper;
    public final int maxConcurrency;
    public final AtomicReference<AnonymousClass209<?, ?>[]> observers;
    public volatile AbstractC13591zq<U> queue;
    public Queue<AbstractC13121yu<? extends U>> sources;
    public long uniqueId;
    public AbstractC12271xB upstream;
    public int wip;

    /* JADX WARN: Incorrect args count in method signature: (LX/1yM<-TU;>;LX/1yl<-TT;+LX/1yu<+TU;>;>;ZII)V */
    public AnonymousClass208(AnonymousClass1yM r3, AbstractC13031yl r4, int i, int i2) {
        this.downstream = r3;
        this.mapper = r4;
        this.delayErrors = false;
        this.maxConcurrency = i;
        this.bufferSize = i2;
        if (i != Integer.MAX_VALUE) {
            this.sources = new ArrayDeque(i);
        }
        this.observers = new AtomicReference<>(A00);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v16, types: [X.1zf] */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0023, code lost:
        if (decrementAndGet() == 0) goto L_0x005d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final void A00(X.AbstractC13121yu<? extends U> r7) {
        /*
        // Method dump skipped, instructions count: 191
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass208.A00(X.1yu):void");
    }

    private final void A01(AnonymousClass209<T, U> r8) {
        AnonymousClass209<?, ?>[] r6;
        AnonymousClass209<?, ?>[] r1;
        do {
            r6 = this.observers.get();
            int length = r6.length;
            if (length != 0) {
                int i = 0;
                while (r6[i] != r8) {
                    i++;
                    if (i >= length) {
                        return;
                    }
                }
                if (i < 0) {
                    return;
                }
                if (length == 1) {
                    r1 = A00;
                } else {
                    r1 = new AnonymousClass209[(length - 1)];
                    System.arraycopy(r6, 0, r1, 0, i);
                    System.arraycopy(r6, i + 1, r1, i, (length - i) - 1);
                }
            } else {
                return;
            }
        } while (!this.observers.compareAndSet(r6, r1));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x005a, code lost:
        if (r8 != 0) goto L_0x005c;
     */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x010f A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0004 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final void A02(X.AnonymousClass208 r13) {
        /*
        // Method dump skipped, instructions count: 283
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass208.A02(X.208):void");
    }

    private final boolean A03() {
        if (!this.cancelled) {
            Object obj = this.errors.get();
            if (this.delayErrors || obj == null) {
                return false;
            }
            A05();
            Throwable A002 = this.errors.A00();
            if (A002 != C12301xE.A00) {
                this.downstream.onError(A002);
            }
        }
        return true;
    }

    public final boolean A05() {
        AnonymousClass209<?, ?>[] andSet;
        this.upstream.dispose();
        AnonymousClass209<?, ?>[] r0 = this.observers.get();
        AnonymousClass209<?, ?>[] r1 = A01;
        if (r0 == r1 || (andSet = this.observers.getAndSet(r1)) == r1) {
            return false;
        }
        for (AnonymousClass209<?, ?> r02 : andSet) {
            EnumC12511xi.dispose(r02);
        }
        return true;
    }

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r2) {
        if (EnumC12511xi.validate(this.upstream, r2)) {
            this.upstream = r2;
            this.downstream.A8A(this);
        }
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        Throwable A002;
        if (!this.cancelled) {
            this.cancelled = true;
            if (A05() && (A002 = this.errors.A00()) != null && A002 != C12301xE.A00) {
                AnonymousClass1y3.A01(A002);
            }
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        if (!this.done) {
            this.done = true;
            A04();
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        if (this.done || !this.errors.A01(th)) {
            AnonymousClass1y3.A01(th);
            return;
        }
        this.done = true;
        A04();
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
        if (!this.done) {
            try {
                Object apply = this.mapper.apply(t);
                AnonymousClass219.A01(apply, "The mapper returned a null ObservableSource");
                AbstractC13121yu<? extends U> r2 = (AbstractC13121yu) apply;
                if (this.maxConcurrency != Integer.MAX_VALUE) {
                    synchronized (this) {
                        int i = this.wip;
                        if (i == this.maxConcurrency) {
                            this.sources.offer(r2);
                            return;
                        }
                        this.wip = i + 1;
                    }
                }
                A00(r2);
            } catch (Throwable th) {
                C12261xA.A00(th);
                this.upstream.dispose();
                onError(th);
            }
        }
    }

    public final void A04() {
        if (getAndIncrement() == 0) {
            A02(this);
        }
    }
}
