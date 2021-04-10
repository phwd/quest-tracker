package X;

import java.lang.reflect.Method;

/* renamed from: X.Bk  reason: case insensitive filesystem */
public final class C0036Bk {
    public final int A00;
    public final Method A01;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C0036Bk)) {
            return false;
        }
        C0036Bk bk = (C0036Bk) obj;
        return this.A00 == bk.A00 && this.A01.getName().equals(bk.A01.getName());
    }

    public final int hashCode() {
        return (this.A00 * 31) + this.A01.getName().hashCode();
    }

    public C0036Bk(int i, Method method) {
        this.A00 = i;
        this.A01 = method;
        method.setAccessible(true);
    }
}
