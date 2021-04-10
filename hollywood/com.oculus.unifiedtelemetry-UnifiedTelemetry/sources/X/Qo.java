package X;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

public class Qo implements Iterator<T> {
    public final AtomicInteger A00 = new AtomicInteger(0);
    public final /* synthetic */ Qp A01;

    public Qo(Qp qp) {
        this.A01 = qp;
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
        Qp qp = this.A01;
        if (andIncrement < qp.size()) {
            Object[] objArr = qp.A03;
            synchronized (objArr) {
                int i = qp.A00;
                if (andIncrement >= i) {
                    objArr[andIncrement] = Qp.A04;
                    qp.A00 = i + 1;
                    try {
                        T t = (T) C0515sp.A00(qp.A02[andIncrement], qp.A01);
                        synchronized (objArr) {
                            objArr[andIncrement] = t;
                            objArr.notifyAll();
                        }
                        return t;
                    } catch (IllegalArgumentException e) {
                        throw new IllegalArgumentException(String.format("Invalid binding id %d", Integer.valueOf(qp.A02[andIncrement])), e);
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
                        if (t2 != Qp.A04) {
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
