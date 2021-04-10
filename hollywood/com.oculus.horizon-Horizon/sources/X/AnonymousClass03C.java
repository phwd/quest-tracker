package X;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* renamed from: X.03C  reason: invalid class name */
public final class AnonymousClass03C extends AnonymousClass07H<AnonymousClass03C> {
    public final List<AbstractC03980gY> A00 = new ArrayList();

    @Override // X.AbstractC03980gY
    public final Iterator<AbstractC03980gY> A01() {
        return this.A00.iterator();
    }

    @Override // X.AbstractC03980gY
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.A00.equals(((AnonymousClass03C) obj).A00);
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    @Override // X.AbstractC03980gY
    public final String toString() {
        List<AbstractC03980gY> list = this.A00;
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

    public AnonymousClass03C(C03700fv r2) {
        super(r2);
    }
}
