package X;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Objects;
import java.util.Map;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible
public abstract class QL<K, V> implements Map.Entry<K, V> {
    @Override // java.util.Map.Entry
    public final K getKey() {
        return ((NF) this).A01;
    }

    @Override // java.util.Map.Entry
    public final V getValue() {
        NF nf = (NF) this;
        NF.A00(nf);
        int i = nf.A00;
        if (i == -1) {
            return null;
        }
        return (V) nf.A02.A07[i];
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (!(obj instanceof Map.Entry)) {
            return false;
        }
        Map.Entry entry = (Map.Entry) obj;
        if (!Objects.equal(getKey(), entry.getKey()) || !Objects.equal(getValue(), entry.getValue())) {
            return false;
        }
        return true;
    }

    @Override // java.util.Map.Entry
    public final V setValue(V v) {
        if (!(this instanceof NF)) {
            throw new UnsupportedOperationException();
        }
        NF nf = (NF) this;
        NF.A00(nf);
        int i = nf.A00;
        if (i == -1) {
            nf.A02.put(nf.A01, v);
            return null;
        }
        Object[] objArr = nf.A02.A07;
        V v2 = (V) objArr[i];
        objArr[i] = v;
        return v2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append((Object) getKey());
        sb.append("=");
        sb.append((Object) getValue());
        return sb.toString();
    }

    public final int hashCode() {
        int hashCode;
        K key = getKey();
        V value = getValue();
        int i = 0;
        if (key == null) {
            hashCode = 0;
        } else {
            hashCode = key.hashCode();
        }
        if (value != null) {
            i = value.hashCode();
        }
        return hashCode ^ i;
    }
}
