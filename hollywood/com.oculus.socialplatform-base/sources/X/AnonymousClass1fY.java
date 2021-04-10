package X;

import java.util.concurrent.Executor;

/* renamed from: X.1fY  reason: invalid class name */
public final class AnonymousClass1fY {
    public final AnonymousClass1f3 A00;
    public final Executor A01;

    public final boolean equals(Object obj) {
        if (obj instanceof AnonymousClass1fY) {
            return this.A00.equals(((AnonymousClass1fY) obj).A00);
        }
        return false;
    }

    public final int hashCode() {
        return this.A00.hashCode();
    }

    public AnonymousClass1fY(AnonymousClass1f3 r1, Executor executor) {
        this.A00 = r1;
        this.A01 = executor;
    }
}
