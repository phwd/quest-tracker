package X;

import java.lang.reflect.Method;

public final class E4 extends WU {
    public final Object A00;
    public final Method A01;

    public final int hashCode() {
        return 0;
    }

    public E4(Object obj, Method method) {
        this.A00 = obj;
        this.A01 = method;
    }

    public final boolean equals(Object obj) {
        return obj instanceof E4;
    }
}
