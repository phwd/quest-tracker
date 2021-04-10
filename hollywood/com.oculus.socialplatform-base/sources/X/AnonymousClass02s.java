package X;

import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/* renamed from: X.02s  reason: invalid class name */
public final class AnonymousClass02s implements Iterator<Map.Entry<K, V>>, Map.Entry<K, V> {
    public int A00;
    public int A01;
    public boolean A02 = false;
    public final /* synthetic */ AnonymousClass02u A03;

    public AnonymousClass02s(AnonymousClass02u r2) {
        this.A03 = r2;
        this.A00 = r2.A00() - 1;
        this.A01 = -1;
    }

    public final boolean equals(Object obj) {
        if (!this.A02) {
            throw new IllegalStateException("This container does not support retaining Map.Entry objects");
        } else if (!(obj instanceof Map.Entry)) {
            return false;
        } else {
            Map.Entry entry = (Map.Entry) obj;
            Object key = entry.getKey();
            AnonymousClass02u r3 = this.A03;
            Object A032 = r3.A03(this.A01, 0);
            if (key != A032 && (key == null || !key.equals(A032))) {
                return false;
            }
            Object value = entry.getValue();
            Object A033 = r3.A03(this.A01, 1);
            if (value == A033 || (value != null && value.equals(A033))) {
                return true;
            }
            return false;
        }
    }

    @Override // java.util.Map.Entry
    public final K getKey() {
        if (this.A02) {
            return (K) this.A03.A03(this.A01, 0);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    @Override // java.util.Map.Entry
    public final V getValue() {
        if (this.A02) {
            return (V) this.A03.A03(this.A01, 1);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public final boolean hasNext() {
        if (this.A01 < this.A00) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        int hashCode;
        if (this.A02) {
            AnonymousClass02u r4 = this.A03;
            int i = this.A01;
            int i2 = 0;
            Object A032 = r4.A03(i, 0);
            Object A033 = r4.A03(i, 1);
            if (A032 == null) {
                hashCode = 0;
            } else {
                hashCode = A032.hashCode();
            }
            if (A033 != null) {
                i2 = A033.hashCode();
            }
            return hashCode ^ i2;
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public final void remove() {
        if (this.A02) {
            this.A03.A07(this.A01);
            this.A01--;
            this.A00--;
            this.A02 = false;
            return;
        }
        throw new IllegalStateException();
    }

    @Override // java.util.Map.Entry
    public final V setValue(V v) {
        if (this.A02) {
            return (V) this.A03.A04(this.A01, v);
        }
        throw new IllegalStateException("This container does not support retaining Map.Entry objects");
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getKey());
        sb.append("=");
        sb.append(getValue());
        return sb.toString();
    }

    @Override // java.util.Iterator
    public final Object next() {
        if (hasNext()) {
            this.A01++;
            this.A02 = true;
            return this;
        }
        throw new NoSuchElementException();
    }
}
