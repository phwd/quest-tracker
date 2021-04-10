package X;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.Xw  reason: case insensitive filesystem */
public final class C0249Xw implements Iterator<AbstractC0073Cr> {
    public int A00;
    public YB A01;
    public final YB[] A02;

    public final boolean hasNext() {
        if (this.A01 != null) {
            return true;
        }
        return false;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.Iterator
    public final AbstractC0073Cr next() {
        YB yb = this.A01;
        if (yb != null) {
            YB yb2 = yb.next;
            while (yb2 == null) {
                int i = this.A00;
                YB[] ybArr = this.A02;
                if (i >= ybArr.length) {
                    break;
                }
                this.A00 = i + 1;
                yb2 = ybArr[i];
            }
            this.A01 = yb2;
            return yb.value;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public C0249Xw(YB[] ybArr) {
        this.A02 = ybArr;
        int length = ybArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            int i2 = i + 1;
            YB yb = ybArr[i];
            if (yb != null) {
                this.A01 = yb;
                i = i2;
                break;
            }
            i = i2;
        }
        this.A00 = i;
    }
}
