package X;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.Xs  reason: case insensitive filesystem */
public final class C0245Xs implements Iterable<AbstractC0073Cr>, Serializable {
    public static final long serialVersionUID = 1;
    public final YB[] _buckets;
    public final int _hashMask;
    public int _nextBucketIndex = 0;
    public final int _size;

    public final C0245Xs A01(AbstractC0073Cr cr) {
        YB[] ybArr = this._buckets;
        int length = ybArr.length;
        YB[] ybArr2 = new YB[length];
        System.arraycopy(ybArr, 0, ybArr2, 0, length);
        String str = cr._propName;
        if (A00(str) == null) {
            int hashCode = str.hashCode() & this._hashMask;
            YB yb = ybArr2[hashCode];
            int i = this._nextBucketIndex;
            int i2 = i + 1;
            this._nextBucketIndex = i2;
            ybArr2[hashCode] = new YB(yb, str, cr, i);
            return new C0245Xs(ybArr2, this._size + 1, i2);
        }
        C0245Xs xs = new C0245Xs(ybArr2, length, this._nextBucketIndex);
        xs.A03(cr);
        return xs;
    }

    public final void A02() {
        YB[] ybArr = this._buckets;
        int i = 0;
        for (YB yb : ybArr) {
            while (yb != null) {
                AbstractC0073Cr cr = yb.value;
                int i2 = i + 1;
                int i3 = cr._propertyIndex;
                if (i3 == -1) {
                    cr._propertyIndex = i;
                    yb = yb.next;
                    i = i2;
                } else {
                    StringBuilder sb = new StringBuilder("Property '");
                    sb.append(cr._propName);
                    sb.append("' already had index (");
                    sb.append(i3);
                    sb.append("), trying to assign ");
                    sb.append(i);
                    throw new IllegalStateException(sb.toString());
                }
            }
        }
    }

    public final void A03(AbstractC0073Cr cr) {
        String str = cr._propName;
        int hashCode = str.hashCode();
        YB[] ybArr = this._buckets;
        int length = hashCode & (ybArr.length - 1);
        YB yb = null;
        int i = -1;
        for (YB yb2 = ybArr[length]; yb2 != null; yb2 = yb2.next) {
            if (i >= 0 || !yb2.key.equals(str)) {
                yb = new YB(yb, yb2.key, yb2.value, yb2.index);
            } else {
                i = yb2.index;
            }
        }
        if (i >= 0) {
            ybArr[length] = new YB(yb, str, cr, i);
            return;
        }
        StringBuilder sb = new StringBuilder("No entry '");
        sb.append(cr);
        sb.append("' found, can't replace");
        throw new NoSuchElementException(sb.toString());
    }

    public final AbstractC0073Cr[] A04() {
        AbstractC0073Cr[] crArr = new AbstractC0073Cr[this._nextBucketIndex];
        YB[] ybArr = this._buckets;
        for (YB yb : ybArr) {
            for (; yb != null; yb = yb.next) {
                crArr[yb.index] = yb.value;
            }
        }
        return crArr;
    }

    @Override // java.lang.Iterable
    public final Iterator<AbstractC0073Cr> iterator() {
        return new C0249Xw(this._buckets);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Properties=[");
        AbstractC0073Cr[] A04 = A04();
        int i = 0;
        for (AbstractC0073Cr cr : A04) {
            if (cr != null) {
                int i2 = i + 1;
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(cr._propName);
                sb.append('(');
                sb.append(cr._type);
                sb.append(')');
                i = i2;
            }
        }
        sb.append(']');
        return sb.toString();
    }

    public final AbstractC0073Cr A00(String str) {
        YB yb = this._buckets[str.hashCode() & this._hashMask];
        if (yb == null) {
            return null;
        }
        while (yb.key != str) {
            yb = yb.next;
            if (yb == null) {
                for (YB yb2 = yb; yb2 != null; yb2 = yb2.next) {
                    if (str.equals(yb2.key)) {
                        return yb2.value;
                    }
                }
                return null;
            }
        }
        return yb.value;
    }

    public C0245Xs(Collection<AbstractC0073Cr> collection) {
        int size = collection.size();
        this._size = size;
        int i = 2;
        while (i < (size > 32 ? (size >> 2) + size : size + size)) {
            i += i;
        }
        this._hashMask = i - 1;
        YB[] ybArr = new YB[i];
        for (AbstractC0073Cr cr : collection) {
            String str = cr._propName;
            int hashCode = str.hashCode() & this._hashMask;
            YB yb = ybArr[hashCode];
            int i2 = this._nextBucketIndex;
            this._nextBucketIndex = i2 + 1;
            ybArr[hashCode] = new YB(yb, str, cr, i2);
        }
        this._buckets = ybArr;
    }

    public C0245Xs(YB[] ybArr, int i, int i2) {
        this._buckets = ybArr;
        this._size = i;
        this._hashMask = ybArr.length - 1;
        this._nextBucketIndex = i2;
    }
}
