package X;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.20Y  reason: invalid class name */
public final class AnonymousClass20Y<T, R> extends AtomicInteger implements AnonymousClass1yM<T>, AbstractC12271xB {
    public static final AnonymousClass20X<Object, Object> A00;
    public static final long serialVersionUID = -3491074160481096299L;
    public final AtomicReference<AnonymousClass20X<T, R>> active = new AtomicReference<>();
    public final int bufferSize;
    public volatile boolean cancelled;
    public final boolean delayErrors;
    public volatile boolean done;
    public final AnonymousClass1yM<? super R> downstream;
    public final AnonymousClass20G errors;
    public final AbstractC13031yl<? super T, ? extends AbstractC13121yu<? extends R>> mapper;
    public volatile long unique;
    public AbstractC12271xB upstream;

    static {
        AnonymousClass20X<Object, Object> r0 = new AnonymousClass20X<>(null, -1, 1);
        A00 = r0;
        EnumC12511xi.dispose(r0);
    }

    /* JADX WARN: Incorrect args count in method signature: (LX/1yM<-TR;>;LX/1yl<-TT;+LX/1yu<+TR;>;>;IZ)V */
    public AnonymousClass20Y(AnonymousClass1yM r3, AbstractC13031yl r4, int i) {
        this.downstream = r3;
        this.mapper = r4;
        this.bufferSize = i;
        this.delayErrors = false;
        this.errors = new AnonymousClass20G();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v2, resolved type: java.util.concurrent.atomic.AtomicReference<X.20X<T, R>> */
    /* JADX WARN: Multi-variable type inference failed */
    private final void A00() {
        AtomicReference atomicReference;
        AnonymousClass20X<T, R> r0 = this.active.get();
        AnonymousClass20X<Object, Object> r1 = A00;
        if (r0 != r1 && (atomicReference = (AtomicReference) this.active.getAndSet(r1)) != r1 && atomicReference != null) {
            EnumC12511xi.dispose(atomicReference);
        }
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
        if (!this.cancelled) {
            this.cancelled = true;
            this.upstream.dispose();
            A00();
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        if (!this.done) {
            this.done = true;
            A01();
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        if (this.done || !this.errors.A01(th)) {
            AnonymousClass1y3.A01(th);
            return;
        }
        if (!this.delayErrors) {
            A00();
        }
        this.done = true;
        A01();
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
        AnonymousClass20X<T, R> r1;
        long j = this.unique + 1;
        this.unique = j;
        AnonymousClass20X<T, R> r0 = this.active.get();
        if (r0 != null) {
            EnumC12511xi.dispose(r0);
        }
        try {
            Object apply = this.mapper.apply(t);
            AnonymousClass219.A01(apply, "The ObservableSource returned is null");
            AbstractC13121yu r5 = (AbstractC13121yu) apply;
            AnonymousClass20X<T, R> r2 = new AnonymousClass20X<>(this, j, this.bufferSize);
            do {
                r1 = this.active.get();
                if (r1 == A00) {
                    return;
                }
            } while (!this.active.compareAndSet(r1, r2));
            r5.AAa(r2);
        } catch (Throwable th) {
            C12261xA.A00(th);
            this.upstream.dispose();
            onError(th);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002a, code lost:
        if (r0 != null) goto L_0x002c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0064, code lost:
        if (r1 != false) goto L_0x00a6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A01() {
        /*
        // Method dump skipped, instructions count: 193
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass20Y.A01():void");
    }
}
