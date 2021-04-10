package X;

import io.reactivex.annotations.Nullable;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* renamed from: X.210  reason: invalid class name */
public final class AnonymousClass210<T> extends AbstractC139521b<T> {
    public static final AnonymousClass211[] A07 = new AnonymousClass211[0];
    public static final AnonymousClass211[] A08 = new AnonymousClass211[0];
    public long A00;
    public final ReadWriteLock A01;
    public final AtomicReference<AnonymousClass211<T>[]> A02 = new AtomicReference<>(A07);
    public final AtomicReference<Throwable> A03 = new AtomicReference<>();
    public final AtomicReference<Object> A04 = new AtomicReference<>();
    public final Lock A05;
    public final Lock A06 = this.A01.writeLock();

    private final AnonymousClass211<T>[] A07(Object obj) {
        AtomicReference<AnonymousClass211<T>[]> atomicReference = this.A02;
        AnonymousClass211<T>[] r0 = A08;
        AnonymousClass211<T>[] andSet = atomicReference.getAndSet(r0);
        if (andSet != r0) {
            Lock lock = this.A06;
            lock.lock();
            this.A00++;
            this.A04.lazySet(obj);
            lock.unlock();
        }
        return andSet;
    }

    @Nullable
    public final T A0K() {
        T t = (T) this.A04.get();
        if (EnumC139220y.isComplete(t) || EnumC139220y.isError(t)) {
            return null;
        }
        return t;
    }

    public final void A0L(AnonymousClass211<T> r9) {
        AtomicReference<AnonymousClass211<T>[]> atomicReference;
        AnonymousClass211<T>[] r6;
        AnonymousClass211<T>[] r1;
        do {
            atomicReference = this.A02;
            r6 = atomicReference.get();
            int length = r6.length;
            if (length != 0) {
                int i = 0;
                while (r6[i] != r9) {
                    i++;
                    if (i >= length) {
                        return;
                    }
                }
                if (i < 0) {
                    return;
                }
                if (length == 1) {
                    r1 = A07;
                } else {
                    r1 = new AnonymousClass211[(length - 1)];
                    System.arraycopy(r6, 0, r1, 0, i);
                    System.arraycopy(r6, i + 1, r1, i, (length - i) - 1);
                }
            } else {
                return;
            }
        } while (!atomicReference.compareAndSet(r6, r1));
    }

    @Override // X.AnonymousClass1yM
    public final void A8A(AbstractC12271xB r2) {
        if (this.A03.get() != null) {
            r2.dispose();
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onComplete() {
        if (this.A03.compareAndSet(null, C12301xE.A00)) {
            Object complete = EnumC139220y.complete();
            for (AnonymousClass211<T> r2 : A07(complete)) {
                r2.A00(complete, this.A00);
            }
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onError(Throwable th) {
        AnonymousClass219.A01(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (!this.A03.compareAndSet(null, th)) {
            AnonymousClass1y3.A01(th);
            return;
        }
        Object error = EnumC139220y.error(th);
        for (AnonymousClass211<T> r2 : A07(error)) {
            r2.A00(error, this.A00);
        }
    }

    @Override // X.AnonymousClass1yM
    public final void onNext(T t) {
        AnonymousClass219.A01(t, "onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.A03.get() == null) {
            Lock lock = this.A06;
            lock.lock();
            this.A00++;
            this.A04.lazySet(t);
            lock.unlock();
            for (AnonymousClass211<T> r2 : this.A02.get()) {
                r2.A00(t, this.A00);
            }
        }
    }

    public AnonymousClass210() {
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
        this.A01 = reentrantReadWriteLock;
        this.A05 = reentrantReadWriteLock.readLock();
    }
}
