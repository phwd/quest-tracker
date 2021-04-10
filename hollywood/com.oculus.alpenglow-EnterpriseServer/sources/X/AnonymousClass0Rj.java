package X;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.0Rj  reason: invalid class name */
public class AnonymousClass0Rj implements Iterator<T> {
    public final AtomicInteger A00 = new AtomicInteger(0);
    public final /* synthetic */ AnonymousClass0Rk A01;

    public AnonymousClass0Rj(AnonymousClass0Rk r3) {
        this.A01 = r3;
    }

    public final boolean hasNext() {
        if (this.A00.get() < this.A01.size()) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final T next() {
        int andIncrement = this.A00.getAndIncrement();
        AnonymousClass0Rk r6 = this.A01;
        if (andIncrement < r6.size()) {
            Object[] objArr = r6.A03;
            synchronized (objArr) {
                int i = r6.A00;
                if (andIncrement >= i) {
                    objArr[andIncrement] = AnonymousClass0Rk.A04;
                    r6.A00 = i + 1;
                    try {
                        T t = (T) AnonymousClass13m.A00(r6.A02[andIncrement], r6.A01);
                        synchronized (objArr) {
                            objArr[andIncrement] = t;
                            objArr.notifyAll();
                        }
                        return t;
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException(String.format("Invalid binding id %d", Integer.valueOf(r6.A02[andIncrement])), e);
                    } catch (Throwable th) {
                        th = th;
                        synchronized (objArr) {
                            try {
                                objArr[andIncrement] = null;
                                objArr.notifyAll();
                            } catch (Throwable th2) {
                                th = th2;
                            }
                            throw th;
                        }
                    }
                } else {
                    while (true) {
                        T t2 = (T) objArr[andIncrement];
                        if (t2 != AnonymousClass0Rk.A04) {
                            return t2;
                        }
                        try {
                            objArr.wait();
                        } catch (InterruptedException e2) {
                            Thread.currentThread().interrupt();
                            throw new RuntimeException(e2);
                        }
                    }
                }
            }
        } else {
            throw new NoSuchElementException();
        }
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
