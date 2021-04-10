package X;

import java.lang.reflect.Method;

/* renamed from: X.1a8  reason: invalid class name */
public final class AnonymousClass1a8 {
    public final Object A00;
    public final Method A01;
    public final int A02;

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            AnonymousClass1a8 r5 = (AnonymousClass1a8) obj;
            if (!this.A01.equals(r5.A01) || this.A00 != r5.A00) {
                return false;
            }
        }
        return true;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("[EventProducer ");
        sb.append(this.A01);
        sb.append("]");
        return sb.toString();
    }

    public AnonymousClass1a8(Object obj, Method method) {
        if (method != null) {
            this.A00 = obj;
            this.A01 = method;
            method.setAccessible(true);
            this.A02 = ((method.hashCode() + 31) * 31) + obj.hashCode();
            return;
        }
        throw new NullPointerException("EventProducer method cannot be null.");
    }

    public final int hashCode() {
        return this.A02;
    }
}
