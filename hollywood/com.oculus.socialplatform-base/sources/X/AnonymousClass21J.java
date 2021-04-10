package X;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* renamed from: X.21J  reason: invalid class name */
public final class AnonymousClass21J<T> extends AtomicReference<AbstractC12271xB> implements AnonymousClass1yM<T>, AbstractC12271xB {
    public static final AnonymousClass21P[] A00 = new AnonymousClass21P[0];
    public static final AnonymousClass21P[] A01 = new AnonymousClass21P[0];
    public static final long serialVersionUID = -533785617179540163L;
    public final AnonymousClass21R<T> buffer;
    public boolean done;
    public final AtomicReference<AnonymousClass21P[]> observers = new AtomicReference<>(A00);
    public final AtomicBoolean shouldConnect = new AtomicBoolean();

    public final void A00(AnonymousClass21P<T> r8) {
        AnonymousClass21P[] r6;
        AnonymousClass21P[] r1;
        do {
            r6 = this.observers.get();
            int length = r6.length;
            if (length != 0) {
                int i = 0;
                while (!r6[i].equals(r8)) {
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
                    r1 = new AnonymousClass21P[(length - 1)];
                    System.arraycopy(r6, 0, r1, 0, i);
                    System.arraycopy(r6, i + 1, r1, i, (length - i) - 1);
                }
            } else {
                return;
            }
        } while (!this.observers.compareAndSet(r6, r1));
    }

    @Override // X.AbstractC12271xB
    public final void dispose() {
        this.observers.set(A01);
        EnumC12511xi.dispose(this);
    }

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        if (!this.done) {
            this.done = true;
            this.buffer.A2C();
            for (AnonymousClass21P<T> r1 : this.observers.getAndSet(A01)) {
                this.buffer.A9F(r1);
            }
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        if (!this.done) {
            this.done = true;
            this.buffer.A2t(th);
            for (AnonymousClass21P<T> r1 : this.observers.getAndSet(A01)) {
                this.buffer.A9F(r1);
            }
            return;
        }
        AnonymousClass1y3.A01(th);
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
        if (!this.done) {
            this.buffer.A6Y(t);
            for (AnonymousClass21P<T> r1 : this.observers.get()) {
                this.buffer.A9F(r1);
            }
        }
    }

    public AnonymousClass21J(AnonymousClass21R<T> r3) {
        this.buffer = r3;
    }

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r6) {
        if (EnumC12511xi.setOnce(this, r6)) {
            for (AnonymousClass21P<T> r1 : this.observers.get()) {
                this.buffer.A9F(r1);
            }
        }
    }
}
