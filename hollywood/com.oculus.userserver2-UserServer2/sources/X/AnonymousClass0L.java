package X;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.0L  reason: invalid class name */
public final class AnonymousClass0L implements Iterator<AQ> {
    public int A00;
    public AnonymousClass0M A01;
    public final AnonymousClass0M[] A02;

    public final boolean hasNext() {
        if (this.A01 != null) {
            return true;
        }
        return false;
    }

    /* Return type fixed from 'java.lang.Object' to match base method */
    @Override // java.util.Iterator
    public final AQ next() {
        AnonymousClass0M r4 = this.A01;
        if (r4 != null) {
            AnonymousClass0M r3 = r4.next;
            while (r3 == null) {
                int i = this.A00;
                AnonymousClass0M[] r1 = this.A02;
                if (i >= r1.length) {
                    break;
                }
                this.A00 = i + 1;
                r3 = r1[i];
            }
            this.A01 = r3;
            return r4.value;
        }
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public AnonymousClass0L(AnonymousClass0M[] r4) {
        this.A02 = r4;
        int length = r4.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            int i2 = i + 1;
            AnonymousClass0M r0 = r4[i];
            if (r0 != null) {
                this.A01 = r0;
                i = i2;
                break;
            }
            i = i2;
        }
        this.A00 = i;
    }
}
