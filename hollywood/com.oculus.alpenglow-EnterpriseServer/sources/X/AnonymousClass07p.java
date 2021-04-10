package X;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.07p  reason: invalid class name */
public final class AnonymousClass07p extends AnonymousClass0E3<AnonymousClass07p> {
    public final List<AnonymousClass0aF> A00 = new ArrayList();

    @Override // X.AnonymousClass0aF
    public final Iterator<AnonymousClass0aF> A01() {
        return this.A00.iterator();
    }

    @Override // X.AnonymousClass0aF
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.A00.equals(((AnonymousClass07p) obj).A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // X.AnonymousClass0aF
    public final String toString() {
        List<AnonymousClass0aF> list = this.A00;
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

    public AnonymousClass07p(AnonymousClass0Zc r2) {
        super(r2);
    }

    @Override // X.AnonymousClass0Jx, X.AbstractC06310mX
    public final void A7h(AbstractC02640aV r3, AnonymousClass0a8 r4) throws IOException, C05910ld {
        r3.A0E();
        Iterator<AnonymousClass0aF> it = this.A00.iterator();
        while (it.hasNext()) {
            ((AnonymousClass0Jx) it.next()).A7h(r3, r4);
        }
        r3.A0B();
    }

    @Override // X.AnonymousClass0Jx, X.AbstractC06310mX
    public final void A7i(AbstractC02640aV r3, AnonymousClass0a8 r4, AnonymousClass0o6 r5) throws IOException, C05910ld {
        r5.A01(this, r3);
        Iterator<AnonymousClass0aF> it = this.A00.iterator();
        while (it.hasNext()) {
            ((AnonymousClass0Jx) it.next()).A7h(r3, r4);
        }
        r5.A04(this, r3);
    }
}
