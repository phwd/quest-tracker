package X;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.20T  reason: invalid class name */
public final class AnonymousClass20T<T, R> extends AtomicInteger implements AnonymousClass1yM<T>, AbstractC12271xB {
    public static final AnonymousClass20U<Object> A00 = new AnonymousClass20U<>(null);
    public static final long serialVersionUID = -5402190102429853762L;
    public volatile boolean cancelled;
    public final boolean delayErrors = false;
    public volatile boolean done;
    public final AnonymousClass1yM<? super R> downstream;
    public final AnonymousClass20G errors = new AnonymousClass20G();
    public final AtomicReference<AnonymousClass20U<R>> inner = new AtomicReference<>();
    public final AbstractC13031yl<? super T, ? extends AbstractC12761yH<? extends R>> mapper;
    public AbstractC12271xB upstream;

    /* JADX WARN: Incorrect args count in method signature: (LX/1yM<-TR;>;LX/1yl<-TT;+LX/1yH<+TR;>;>;Z)V */
    public AnonymousClass20T(AnonymousClass1yM r2, AbstractC13031yl r3) {
        this.downstream = r2;
        this.mapper = r3;
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        this.cancelled = true;
        this.upstream.dispose();
        A00(this);
    }

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        this.done = true;
        A01();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.concurrent.atomic.AtomicReference<X.20U<R>> */
    /* JADX WARN: Multi-variable type inference failed */
    public static final void A00(AnonymousClass20T r1) {
        AnonymousClass20U<Object> r12 = A00;
        AtomicReference atomicReference = (AtomicReference) r1.inner.getAndSet(r12);
        if (atomicReference != null && atomicReference != r12) {
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

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        if (this.errors.A01(th)) {
            if (!this.delayErrors) {
                A00(this);
            }
            this.done = true;
            A01();
            return;
        }
        AnonymousClass1y3.A01(th);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.util.concurrent.atomic.AtomicReference<X.20U<R>> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
        AnonymousClass20U<R> r1;
        AnonymousClass20U<R> r0 = this.inner.get();
        if (r0 != null) {
            EnumC12511xi.dispose(r0);
        }
        try {
            Object apply = this.mapper.apply(t);
            AnonymousClass219.A01(apply, "The mapper returned a null SingleSource");
            AbstractC12761yH r3 = (AbstractC12761yH) apply;
            AnonymousClass20U<R> r2 = new AnonymousClass20U<>(this);
            do {
                r1 = this.inner.get();
                if (r1 == A00) {
                    return;
                }
            } while (!this.inner.compareAndSet(r1, r2));
            r3.AAb(r2);
        } catch (Throwable th) {
            C12261xA.A00(th);
            this.upstream.dispose();
            this.inner.getAndSet(A00);
            onError(th);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003d, code lost:
        if (r0 == false) goto L_0x003f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void A01() {
        /*
            r7 = this;
            int r0 = r7.getAndIncrement()
            if (r0 != 0) goto L_0x0022
            X.1yM<? super R> r6 = r7.downstream
            X.20G r5 = r7.errors
            java.util.concurrent.atomic.AtomicReference<X.20U<R>> r4 = r7.inner
            r3 = 1
        L_0x000d:
            boolean r0 = r7.cancelled
            if (r0 != 0) goto L_0x0022
            java.lang.Object r0 = r5.get()
            if (r0 == 0) goto L_0x0023
            boolean r0 = r7.delayErrors
            if (r0 != 0) goto L_0x0023
            java.lang.Throwable r0 = r5.A00()
        L_0x001f:
            r6.onError(r0)
        L_0x0022:
            return
        L_0x0023:
            boolean r2 = r7.done
            java.lang.Object r1 = r4.get()
            X.20U r1 = (X.AnonymousClass20U) r1
            r0 = 0
            if (r1 != 0) goto L_0x002f
            r0 = 1
        L_0x002f:
            if (r2 == 0) goto L_0x003d
            if (r0 == 0) goto L_0x003f
            java.lang.Throwable r0 = r5.A00()
            if (r0 != 0) goto L_0x001f
            r6.onComplete()
            return
        L_0x003d:
            if (r0 != 0) goto L_0x004d
        L_0x003f:
            R r0 = r1.item
            if (r0 == 0) goto L_0x004d
            r0 = 0
            r4.compareAndSet(r1, r0)
            R r0 = r1.item
            r6.onNext(r0)
            goto L_0x000d
        L_0x004d:
            int r0 = -r3
            int r3 = r7.addAndGet(r0)
            if (r3 != 0) goto L_0x000d
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass20T.A01():void");
    }
}
