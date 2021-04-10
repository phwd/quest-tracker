package X;

import java.io.Serializable;

public final class b6 implements Serializable {
    public static final b6 A00 = new b6();

    private Object readResolve() {
        return A00;
    }
}
