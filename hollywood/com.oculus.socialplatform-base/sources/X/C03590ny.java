package X;

import java.io.Serializable;

/* renamed from: X.0ny  reason: invalid class name and case insensitive filesystem */
public final class C03590ny implements Serializable {
    public static final long serialVersionUID = 1;
    public final int hashCode;
    public final Object key;
    public final Class<?> scope;
    public final Class<?> type;

    public final boolean equals(Object obj) {
        if (obj != this) {
            if (obj == null || obj.getClass() != getClass()) {
                return false;
            }
            C03590ny r5 = (C03590ny) obj;
            if (!(r5.key.equals(this.key) && r5.type == this.type && r5.scope == this.scope)) {
                return false;
            }
        }
        return true;
    }

    public C03590ny(Class<?> cls, Class<?> cls2, Object obj) {
        this.type = cls;
        this.scope = cls2;
        this.key = obj;
        int hashCode2 = obj.hashCode() + cls.getName().hashCode();
        this.hashCode = cls2 != null ? hashCode2 ^ cls2.getName().hashCode() : hashCode2;
    }

    public final int hashCode() {
        return this.hashCode;
    }
}
