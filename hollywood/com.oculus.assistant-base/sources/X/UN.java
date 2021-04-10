package X;

import com.google.common.base.Preconditions;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class UN implements Iterator {
    public int A00;
    public int A01;
    public AbstractC1179ua A02;
    public boolean A03;
    public final UM A04;
    public final Iterator A05;

    public final boolean hasNext() {
        if (this.A00 > 0 || this.A05.hasNext()) {
            return true;
        }
        return false;
    }

    public final void remove() {
        Preconditions.checkState(this.A03, "no calls to next() since the last call to remove()");
        if (this.A01 == 1) {
            this.A05.remove();
        } else {
            this.A04.remove(this.A02.A01());
        }
        this.A01--;
        this.A03 = false;
    }

    public UN(UM um, Iterator it) {
        this.A04 = um;
        this.A05 = it;
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (hasNext()) {
            int i = this.A00;
            if (i == 0) {
                AbstractC1179ua uaVar = (AbstractC1179ua) this.A05.next();
                this.A02 = uaVar;
                i = uaVar.A00();
                this.A00 = i;
                this.A01 = i;
            }
            this.A00 = i - 1;
            this.A03 = true;
            return this.A02.A01();
        }
        throw new NoSuchElementException();
    }
}
