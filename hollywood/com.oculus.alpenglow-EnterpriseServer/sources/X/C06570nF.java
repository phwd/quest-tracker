package X;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/* renamed from: X.0nF  reason: invalid class name and case insensitive filesystem */
public final class C06570nF implements Iterable<AbstractC01680Ku>, Serializable {
    public static final long serialVersionUID = 1;
    public final AnonymousClass0nD[] _buckets;
    public final int _hashMask;
    public int _nextBucketIndex = 0;
    public final int _size;

    public final C06570nF A01(AbstractC01680Ku r8) {
        AnonymousClass0nD[] r1 = this._buckets;
        int length = r1.length;
        AnonymousClass0nD[] r6 = new AnonymousClass0nD[length];
        System.arraycopy(r1, 0, r6, 0, length);
        String str = r8._propName;
        if (A00(str) == null) {
            int hashCode = str.hashCode() & this._hashMask;
            AnonymousClass0nD r3 = r6[hashCode];
            int i = this._nextBucketIndex;
            int i2 = i + 1;
            this._nextBucketIndex = i2;
            r6[hashCode] = new AnonymousClass0nD(r3, str, r8, i);
            return new C06570nF(r6, this._size + 1, i2);
        }
        C06570nF r0 = new C06570nF(r6, length, this._nextBucketIndex);
        r0.A03(r8);
        return r0;
    }

    public final void A02() {
        AnonymousClass0nD[] r8 = this._buckets;
        int i = 0;
        for (AnonymousClass0nD r4 : r8) {
            while (r4 != null) {
                AbstractC01680Ku r3 = r4.value;
                int i2 = i + 1;
                int i3 = r3._propertyIndex;
                if (i3 == -1) {
                    r3._propertyIndex = i;
                    r4 = r4.next;
                    i = i2;
                } else {
                    throw new IllegalStateException("Property '" + r3._propName + "' already had index (" + i3 + "), trying to assign " + i);
                }
            }
        }
    }

    public final void A03(AbstractC01680Ku r11) {
        String str = r11._propName;
        int hashCode = str.hashCode();
        AnonymousClass0nD[] r7 = this._buckets;
        int length = hashCode & (r7.length - 1);
        AnonymousClass0nD r5 = null;
        int i = -1;
        for (AnonymousClass0nD r6 = r7[length]; r6 != null; r6 = r6.next) {
            if (i >= 0 || !r6.key.equals(str)) {
                r5 = new AnonymousClass0nD(r5, r6.key, r6.value, r6.index);
            } else {
                i = r6.index;
            }
        }
        if (i >= 0) {
            r7[length] = new AnonymousClass0nD(r5, str, r11, i);
            return;
        }
        throw new NoSuchElementException("No entry '" + r11 + "' found, can't replace");
    }

    public final AbstractC01680Ku[] A04() {
        AbstractC01680Ku[] r6 = new AbstractC01680Ku[this._nextBucketIndex];
        AnonymousClass0nD[] r5 = this._buckets;
        for (AnonymousClass0nD r2 : r5) {
            for (; r2 != null; r2 = r2.next) {
                r6[r2.index] = r2.value;
            }
        }
        return r6;
    }

    @Override // java.lang.Iterable
    public final Iterator<AbstractC01680Ku> iterator() {
        return new AnonymousClass0nE(this._buckets);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Properties=[");
        AbstractC01680Ku[] A04 = A04();
        int i = 0;
        for (AbstractC01680Ku r2 : A04) {
            if (r2 != null) {
                int i2 = i + 1;
                if (i > 0) {
                    sb.append(", ");
                }
                sb.append(r2._propName);
                sb.append('(');
                sb.append(r2.A4h());
                sb.append(')');
                i = i2;
            }
        }
        sb.append(']');
        return sb.toString();
    }

    public final AbstractC01680Ku A00(String str) {
        AnonymousClass0nD r2 = this._buckets[str.hashCode() & this._hashMask];
        if (r2 == null) {
            return null;
        }
        while (r2.key != str) {
            r2 = r2.next;
            if (r2 == null) {
                for (AnonymousClass0nD r1 = r2; r1 != null; r1 = r1.next) {
                    if (str.equals(r1.key)) {
                        return r1.value;
                    }
                }
                return null;
            }
        }
        return r2.value;
    }

    public C06570nF(Collection<AbstractC01680Ku> collection) {
        int size = collection.size();
        this._size = size;
        int i = 2;
        while (i < (size > 32 ? (size >> 2) + size : size + size)) {
            i += i;
        }
        this._hashMask = i - 1;
        AnonymousClass0nD[] r7 = new AnonymousClass0nD[i];
        for (AbstractC01680Ku r5 : collection) {
            String str = r5._propName;
            int hashCode = str.hashCode() & this._hashMask;
            AnonymousClass0nD r2 = r7[hashCode];
            int i2 = this._nextBucketIndex;
            this._nextBucketIndex = i2 + 1;
            r7[hashCode] = new AnonymousClass0nD(r2, str, r5, i2);
        }
        this._buckets = r7;
    }

    public C06570nF(AnonymousClass0nD[] r2, int i, int i2) {
        this._buckets = r2;
        this._size = i;
        this._hashMask = r2.length - 1;
        this._nextBucketIndex = i2;
    }
}
