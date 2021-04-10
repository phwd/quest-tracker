package X;

import androidx.annotation.NonNull;

/* renamed from: X.08P  reason: invalid class name */
public final class AnonymousClass08P {
    @NonNull
    public static final AnonymousClass08P A04 = new AnonymousClass08P(0, 0, 0, 0);
    public final int A00;
    public final int A01;
    public final int A02;
    public final int A03;

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj != null && getClass() == obj.getClass()) {
                AnonymousClass08P r5 = (AnonymousClass08P) obj;
                if (!(this.A00 == r5.A00 && this.A01 == r5.A01 && this.A02 == r5.A02 && this.A03 == r5.A03)) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    @NonNull
    public static AnonymousClass08P A00(int i, int i2, int i3, int i4) {
        if (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            return A04;
        }
        return new AnonymousClass08P(i, i2, i3, i4);
    }

    public final int hashCode() {
        return (((((this.A01 * 31) + this.A03) * 31) + this.A02) * 31) + this.A00;
    }

    public final String toString() {
        return "Insets{left=" + this.A01 + ", top=" + this.A03 + ", right=" + this.A02 + ", bottom=" + this.A00 + '}';
    }

    public AnonymousClass08P(int i, int i2, int i3, int i4) {
        this.A01 = i;
        this.A03 = i2;
        this.A02 = i3;
        this.A00 = i4;
    }
}
