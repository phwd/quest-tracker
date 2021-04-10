package X;

import java.util.Arrays;

/* renamed from: X.1y4  reason: invalid class name */
public final class AnonymousClass1y4 implements AnonymousClass1yR {
    public final AnonymousClass1yE A00;
    public final AnonymousClass1xF A01;

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            AnonymousClass1y4 r6 = (AnonymousClass1y4) obj;
            AnonymousClass1yE r3 = this.A00;
            AnonymousClass1yE r2 = r6.A00;
            if (r2 == null || r3.A01 != r2.A01 || r3.A00 != r2.A00 || !this.A01.equals(r6.A01)) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.A00, this.A01, 0, false});
    }

    public AnonymousClass1y4(AnonymousClass1xF r4) {
        this.A01 = r4;
        this.A00 = new AnonymousClass1yE(r4.A04, r4.A02);
    }

    @Override // X.AnonymousClass1yR
    public final AnonymousClass1yA A4Z() {
        return AnonymousClass1yA.VIDEO;
    }
}
