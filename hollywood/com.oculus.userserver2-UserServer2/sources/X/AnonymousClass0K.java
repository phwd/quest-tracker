package X;

import java.io.Serializable;
import java.util.Iterator;

/* renamed from: X.0K  reason: invalid class name */
public final class AnonymousClass0K implements Iterable<AQ>, Serializable {
    public static final long serialVersionUID = 1;
    public final AnonymousClass0M[] _buckets;
    public final int _hashMask;
    public final int _nextBucketIndex;
    public final int _size;

    @Override // java.lang.Iterable
    public final Iterator<AQ> iterator() {
        return new AnonymousClass0L(this._buckets);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Properties=[");
        int i = this._nextBucketIndex;
        AQ[] aqArr = new AQ[i];
        AnonymousClass0M[] r8 = this._buckets;
        for (AnonymousClass0M r2 : r8) {
            for (; r2 != null; r2 = r2.next) {
                aqArr[r2.index] = r2.value;
            }
        }
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            AQ aq = aqArr[i3];
            if (aq != null) {
                int i4 = i2 + 1;
                if (i2 > 0) {
                    sb.append(", ");
                }
                sb.append(aq._propName);
                sb.append('(');
                sb.append(aq._type);
                sb.append(')');
                i2 = i4;
            }
        }
        sb.append(']');
        return sb.toString();
    }

    public final AQ A00(String str) {
        AnonymousClass0M r2 = this._buckets[str.hashCode() & this._hashMask];
        if (r2 == null) {
            return null;
        }
        while (r2.key != str) {
            r2 = r2.next;
            if (r2 == null) {
                for (AnonymousClass0M r1 = r2; r1 != null; r1 = r1.next) {
                    if (str.equals(r1.key)) {
                        return r1.value;
                    }
                }
                return null;
            }
        }
        return r2.value;
    }
}
