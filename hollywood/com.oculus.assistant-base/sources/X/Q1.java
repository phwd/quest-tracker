package X;

import java.lang.reflect.Array;

public final class Q1 {
    public final /* synthetic */ int A00;
    public final /* synthetic */ Class A01;
    public final /* synthetic */ Object A02;

    public final boolean equals(Object obj) {
        int i;
        if (obj != this) {
            if (obj != null && obj.getClass() == this.A01 && Array.getLength(obj) == (i = this.A00)) {
                for (int i2 = 0; i2 < i; i2++) {
                    Object obj2 = Array.get(this.A02, i2);
                    Object obj3 = Array.get(obj, i2);
                    if (obj2 == obj3 || obj2 == null || obj2.equals(obj3)) {
                    }
                }
            }
            return false;
        }
        return true;
    }

    public Q1(Class cls, int i, Object obj) {
        this.A01 = cls;
        this.A00 = i;
        this.A02 = obj;
    }
}
