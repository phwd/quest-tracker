package X;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public final class Oo implements Iterable, Serializable {
    public static final long serialVersionUID = 1;
    public final C0269Om[] _buckets;
    public final int _hashMask;
    public int _nextBucketIndex = 0;
    public final int _size;

    public final Oo A01(AbstractC1034r7 r7Var) {
        C0269Om[] omArr = this._buckets;
        int length = omArr.length;
        C0269Om[] omArr2 = new C0269Om[length];
        System.arraycopy(omArr, 0, omArr2, 0, length);
        String str = r7Var._propName;
        if (A00(str) == null) {
            int hashCode = str.hashCode() & this._hashMask;
            C0269Om om = omArr2[hashCode];
            int i = this._nextBucketIndex;
            int i2 = i + 1;
            this._nextBucketIndex = i2;
            omArr2[hashCode] = new C0269Om(om, str, r7Var, i);
            return new Oo(omArr2, this._size + 1, i2);
        }
        Oo oo = new Oo(omArr2, length, this._nextBucketIndex);
        oo.A03(r7Var);
        return oo;
    }

    public final void A02() {
        C0269Om[] omArr = this._buckets;
        int i = 0;
        for (C0269Om om : omArr) {
            while (om != null) {
                AbstractC1034r7 r7Var = om.value;
                int i2 = i + 1;
                int i3 = r7Var._propertyIndex;
                if (i3 == -1) {
                    r7Var._propertyIndex = i;
                    om = om.next;
                    i = i2;
                } else {
                    StringBuilder sb = new StringBuilder("Property '");
                    sb.append(r7Var._propName);
                    sb.append("' already had index (");
                    sb.append(i3);
                    sb.append("), trying to assign ");
                    sb.append(i);
                    throw new IllegalStateException(sb.toString());
                }
            }
        }
    }

    public final void A03(AbstractC1034r7 r7Var) {
        String str = r7Var._propName;
        int hashCode = str.hashCode();
        C0269Om[] omArr = this._buckets;
        int length = hashCode & (omArr.length - 1);
        C0269Om om = null;
        int i = -1;
        for (C0269Om om2 = omArr[length]; om2 != null; om2 = om2.next) {
            if (i >= 0 || !om2.key.equals(str)) {
                om = new C0269Om(om, om2.key, om2.value, om2.index);
            } else {
                i = om2.index;
            }
        }
        if (i >= 0) {
            omArr[length] = new C0269Om(om, str, r7Var, i);
            return;
        }
        StringBuilder sb = new StringBuilder("No entry '");
        sb.append(r7Var);
        sb.append("' found, can't replace");
        throw new NoSuchElementException(sb.toString());
    }

    public final AbstractC1034r7[] A04() {
        AbstractC1034r7[] r7VarArr = new AbstractC1034r7[this._nextBucketIndex];
        C0269Om[] omArr = this._buckets;
        for (C0269Om om : omArr) {
            for (; om != null; om = om.next) {
                r7VarArr[om.index] = om.value;
            }
        }
        return r7VarArr;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new On(this._buckets);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Properties=[");
        AbstractC1034r7[] A04 = A04();
        int i = 0;
        for (AbstractC1034r7 r7Var : A04) {
            if (r7Var != null) {
                int i2 = i + 1;
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(r7Var._propName);
                sb.append('(');
                sb.append(r7Var.A34());
                sb.append(')');
                i = i2;
            }
        }
        sb.append(']');
        return sb.toString();
    }

    public final AbstractC1034r7 A00(String str) {
        C0269Om om = this._buckets[str.hashCode() & this._hashMask];
        if (om == null) {
            return null;
        }
        while (om.key != str) {
            om = om.next;
            if (om == null) {
                for (C0269Om om2 = om; om2 != null; om2 = om2.next) {
                    if (str.equals(om2.key)) {
                        return om2.value;
                    }
                }
                return null;
            }
        }
        return om.value;
    }

    public Oo(Collection collection) {
        int size = collection.size();
        this._size = size;
        int i = 2;
        while (i < (size > 32 ? (size >> 2) + size : size + size)) {
            i += i;
        }
        this._hashMask = i - 1;
        C0269Om[] omArr = new C0269Om[i];
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            AbstractC1034r7 r7Var = (AbstractC1034r7) it.next();
            String str = r7Var._propName;
            int hashCode = str.hashCode() & this._hashMask;
            C0269Om om = omArr[hashCode];
            int i2 = this._nextBucketIndex;
            this._nextBucketIndex = i2 + 1;
            omArr[hashCode] = new C0269Om(om, str, r7Var, i2);
        }
        this._buckets = omArr;
    }

    public Oo(C0269Om[] omArr, int i, int i2) {
        this._buckets = omArr;
        this._size = i;
        this._hashMask = omArr.length - 1;
        this._nextBucketIndex = i2;
    }
}
