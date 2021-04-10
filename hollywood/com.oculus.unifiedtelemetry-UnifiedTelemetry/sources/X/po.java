package X;

import java.io.Serializable;

public final class po implements Serializable {
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
            po poVar = (po) obj;
            if (!(poVar.key.equals(this.key) && poVar.type == this.type && poVar.scope == this.scope)) {
                return false;
            }
        }
        return true;
    }

    public po(Class<?> cls, Class<?> cls2, Object obj) {
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
