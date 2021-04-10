package X;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.38  reason: invalid class name */
public final class AnonymousClass38 extends AnonymousClass7J<AnonymousClass38> {
    public final List<AbstractC0222Wi> A00 = new ArrayList();

    @Override // X.AbstractC0222Wi
    public final Iterator<AbstractC0222Wi> A02() {
        return this.A00.iterator();
    }

    @Override // X.AbstractC0222Wi
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.A00.equals(((AnonymousClass38) obj).A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // X.AbstractC0222Wi
    public final String toString() {
        List<AbstractC0222Wi> list = this.A00;
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

    public AnonymousClass38(W6 w6) {
        super(w6);
    }

    @Override // X.AbstractC0222Wi
    public final Integer A04() {
        return AnonymousClass07.A00;
    }
}
