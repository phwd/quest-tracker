package X;

import java.util.Iterator;
import java.util.NoSuchElementException;

public final class On implements Iterator {
    public int A00;
    public C0269Om A01;
    public final C0269Om[] A02;

    public final boolean hasNext() {
        if (this.A01 != null) {
            return true;
        }
        return false;
    }

    @Override // java.util.Iterator
    public final Object next() {
        C0269Om om = this.A01;
        if (om != null) {
            C0269Om om2 = om.next;
            while (om2 == null) {
                int i = this.A00;
                C0269Om[] omArr = this.A02;
                if (i >= omArr.length) {
                    break;
                }
                this.A00 = i + 1;
                om2 = omArr[i];
            }
            this.A01 = om2;
            return om.value;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public On(C0269Om[] omArr) {
        this.A02 = omArr;
        int length = omArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            int i2 = i + 1;
            C0269Om om = omArr[i];
            if (om != null) {
                this.A01 = om;
                i = i2;
                break;
            }
            i = i2;
        }
        this.A00 = i;
    }
}
