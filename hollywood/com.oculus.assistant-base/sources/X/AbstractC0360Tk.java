package X;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableEntry;
import java.util.Map;

/* renamed from: X.Tk  reason: case insensitive filesystem */
public abstract class AbstractC0360Tk implements Map.Entry {
    public final boolean equals(Object obj) {
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
    public final Object getKey() {
        if (this instanceof C1168uP) {
            return ((C1168uP) this).A05;
        }
        if (!(this instanceof ImmutableEntry)) {
            return ((u3) this).A01;
        }
        return ((ImmutableEntry) this).key;
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        if (this instanceof C1168uP) {
            return ((C1168uP) this).A04;
        }
        if (this instanceof ImmutableEntry) {
            return ((ImmutableEntry) this).value;
        }
        u3 u3Var = (u3) this;
        u3.A00(u3Var);
        int i = u3Var.A00;
        if (i == -1) {
            return null;
        }
        return u3Var.A02.A07[i];
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        if (this instanceof C1168uP) {
            C1168uP uPVar = (C1168uP) this;
            Object obj2 = uPVar.A04;
            uPVar.A04 = obj;
            return obj2;
        } else if (!(this instanceof u3)) {
            throw new UnsupportedOperationException();
        } else {
            u3 u3Var = (u3) this;
            u3.A00(u3Var);
            int i = u3Var.A00;
            if (i == -1) {
                u3Var.A02.put(u3Var.A01, obj);
                return null;
            }
            Object[] objArr = u3Var.A02.A07;
            Object obj3 = objArr[i];
            objArr[i] = obj;
            return obj3;
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getKey());
        sb.append("=");
        sb.append(getValue());
        return sb.toString();
    }

    public final int hashCode() {
        int hashCode;
        Object key = getKey();
        Object value = getValue();
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
