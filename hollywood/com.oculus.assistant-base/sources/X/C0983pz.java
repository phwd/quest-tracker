package X;

import java.io.Serializable;

/* renamed from: X.pz  reason: case insensitive filesystem */
public final class C0983pz implements AbstractC0464a8, Serializable {
    public final Object value;

    public C0983pz(Object obj) {
        this.value = obj;
    }

    @Override // X.AbstractC0464a8
    public final Object getValue() {
        return this.value;
    }

    public final String toString() {
        return String.valueOf(getValue());
    }
}
