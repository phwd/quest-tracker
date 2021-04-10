package X;

import java.lang.reflect.Method;

/* renamed from: X.0DS  reason: invalid class name */
public final class AnonymousClass0DS {
    public final int A00;
    public final Method A01;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AnonymousClass0DS)) {
            return false;
        }
        AnonymousClass0DS r4 = (AnonymousClass0DS) obj;
        return this.A00 == r4.A00 && this.A01.getName().equals(r4.A01.getName());
    }

    public final int hashCode() {
        return (this.A00 * 31) + this.A01.getName().hashCode();
    }

    public AnonymousClass0DS(int i, Method method) {
        this.A00 = i;
        this.A01 = method;
        method.setAccessible(true);
    }
}
