package X;

import java.lang.reflect.Method;

public final class AJ {
    public final int A00;
    public final Method A01;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AJ)) {
            return false;
        }
        AJ aj = (AJ) obj;
        return this.A00 == aj.A00 && this.A01.getName().equals(aj.A01.getName());
    }

    public final int hashCode() {
        return (this.A00 * 31) + this.A01.getName().hashCode();
    }

    public AJ(int i, Method method) {
        this.A00 = i;
        this.A01 = method;
        method.setAccessible(true);
    }
}
