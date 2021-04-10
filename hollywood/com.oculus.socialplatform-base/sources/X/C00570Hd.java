package X;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import javax.annotation.Nullable;

/* renamed from: X.0Hd  reason: invalid class name and case insensitive filesystem */
public final class C00570Hd implements Iterator<E> {
    public int A00;
    public int A01;
    public int A02;
    public boolean A03 = false;
    public final AnonymousClass0He<E> A04;
    public final E[] A05;
    public final /* synthetic */ AnonymousClass0He A06;

    public C00570Hd(AnonymousClass0He r2, AnonymousClass0He<E> r3) {
        this.A06 = r2;
        this.A04 = r3;
        this.A05 = (E[]) r3.A02;
        this.A01 = r3.A00;
        int A002 = AnonymousClass0He.A00(r3, -1);
        this.A00 = A002;
        this.A02 = A002;
    }

    public final boolean hasNext() {
        if (this.A00 >= 0) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    @Nullable
    public final E next() {
        if (this.A01 == this.A04.A00) {
            int i = this.A00;
            if (i >= 0) {
                E[] eArr = this.A05;
                E e = eArr[i];
                if (e == AnonymousClass0He.A03) {
                    e = null;
                }
                this.A02 = i;
                this.A03 = true;
                int i2 = i + 1;
                while (true) {
                    if (i2 < eArr.length) {
                        if (eArr[i2] != null) {
                            break;
                        }
                        i2++;
                    } else {
                        i2 = -1;
                        break;
                    }
                }
                this.A00 = i2;
                return e;
            }
            throw new NoSuchElementException();
        }
        throw new ConcurrentModificationException();
    }

    public final void remove() {
        int i = this.A01;
        AnonymousClass0He<E> r1 = this.A04;
        if (i != r1.A00) {
            throw new ConcurrentModificationException();
        } else if (this.A03) {
            this.A03 = false;
            E[] eArr = this.A05;
            r1.remove(eArr[this.A02]);
            this.A01++;
            int i2 = this.A02;
            while (true) {
                if (i2 < eArr.length) {
                    if (eArr[i2] != null) {
                        break;
                    }
                    i2++;
                } else {
                    i2 = -1;
                    break;
                }
            }
            this.A00 = i2;
        } else {
            throw new IllegalStateException();
        }
    }
}
