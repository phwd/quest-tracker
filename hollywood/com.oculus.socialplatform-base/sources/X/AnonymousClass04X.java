package X;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.04X  reason: invalid class name */
public final class AnonymousClass04X extends AnonymousClass0Ck<AnonymousClass04X> {
    public final List<AbstractC02170iC> A00 = new ArrayList();

    @Override // X.AbstractC02170iC
    public final Iterator<AbstractC02170iC> A01() {
        return this.A00.iterator();
    }

    @Override // X.AbstractC02170iC
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.A00.equals(((AnonymousClass04X) obj).A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // X.AbstractC02170iC
    public final String toString() {
        List<AbstractC02170iC> list = this.A00;
        StringBuilder sb = new StringBuilder((list.size() << 4) + 16);
        sb.append('[');
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(',');
            }
            sb.append(list.get(i).toString());
        }
        sb.append(']');
        return sb.toString();
    }

    public AnonymousClass04X(C01850hC r2) {
        super(r2);
    }

    @Override // X.AnonymousClass0p5, X.AnonymousClass0Oh
    public final void A9c(AbstractC02300iS r3, AbstractC02120i3 r4) throws IOException, C03620oC {
        r3.A0H();
        Iterator<AbstractC02170iC> it = this.A00.iterator();
        while (it.hasNext()) {
            ((AnonymousClass0Oh) it.next()).A9c(r3, r4);
        }
        r3.A0E();
    }

    @Override // X.AnonymousClass0p5, X.AnonymousClass0Oh
    public final void A9d(AbstractC02300iS r3, AbstractC02120i3 r4, AbstractC04550qd r5) throws IOException, C03620oC {
        r5.A01(this, r3);
        Iterator<AbstractC02170iC> it = this.A00.iterator();
        while (it.hasNext()) {
            ((AnonymousClass0Oh) it.next()).A9c(r3, r4);
        }
        r5.A04(this, r3);
    }
}
