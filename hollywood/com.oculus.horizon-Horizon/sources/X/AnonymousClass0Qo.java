package X;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: X.0Qo  reason: invalid class name */
public class AnonymousClass0Qo implements Iterator<T> {
    public final AtomicInteger A00 = new AtomicInteger(0);
    public final /* synthetic */ AnonymousClass0Qp A01;

    public AnonymousClass0Qo(AnonymousClass0Qp r3) {
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
        AnonymousClass0Qp r8 = this.A01;
        if (andIncrement < r8.size()) {
            Object[] objArr = r8.A03;
            synchronized (objArr) {
                int i = r8.A00;
                if (andIncrement >= i) {
                    objArr[andIncrement] = AnonymousClass0Qp.A04;
                    r8.A00 = i + 1;
                    try {
                        T t = (T) AnonymousClass117.A00(r8.A02[andIncrement], r8.A01);
                        synchronized (objArr) {
                            objArr[andIncrement] = t;
                            objArr.notifyAll();
                        }
                        return t;
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException(String.format("Invalid binding id %d", Integer.valueOf(r8.A02[andIncrement])), e);
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
                        if (t2 != AnonymousClass0Qp.A04) {
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
