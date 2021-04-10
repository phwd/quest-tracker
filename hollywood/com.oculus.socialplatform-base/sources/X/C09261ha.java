package X;

import java.util.Queue;

/* renamed from: X.1ha  reason: invalid class name and case insensitive filesystem */
public final class C09261ha implements AbstractC09351hm {
    public int A00;
    public Class<?> A01;
    public final C09271hb A02;

    @Override // X.AbstractC09351hm
    public final void A6b() {
        Queue<T> queue = this.A02.A00;
        if (queue.size() < 20) {
            queue.offer(this);
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C09261ha)) {
            return false;
        }
        C09261ha r4 = (C09261ha) obj;
        if (this.A00 == r4.A00 && this.A01 == r4.A01) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int i;
        int i2 = this.A00 * 31;
        Class<?> cls = this.A01;
        if (cls != null) {
            i = cls.hashCode();
        } else {
            i = 0;
        }
        return i2 + i;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Key{size=");
        sb.append(this.A00);
        sb.append("array=");
        sb.append(this.A01);
        sb.append('}');
        return sb.toString();
    }

    public C09261ha(C09271hb r1) {
        this.A02 = r1;
    }
}
