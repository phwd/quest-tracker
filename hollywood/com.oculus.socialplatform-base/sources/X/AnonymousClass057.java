package X;

import androidx.annotation.NonNull;

/* renamed from: X.057  reason: invalid class name */
public final class AnonymousClass057 {
    @NonNull
    public static final AnonymousClass057 A04 = new AnonymousClass057(0, 0, 0, 0);
    public final int A00;
    public final int A01;
    public final int A02;
    public final int A03;

    public final boolean equals(Object obj) {
        if (this != obj) {
            if (obj != null && getClass() == obj.getClass()) {
                AnonymousClass057 r5 = (AnonymousClass057) obj;
                if (!(this.A00 == r5.A00 && this.A01 == r5.A01 && this.A02 == r5.A02 && this.A03 == r5.A03)) {
                    return false;
                }
            }
            return false;
        }
        return true;
    }

    @NonNull
    public static AnonymousClass057 A00(int i, int i2, int i3, int i4) {
        if (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            return A04;
        }
        return new AnonymousClass057(i, i2, i3, i4);
    }

    public final int hashCode() {
        return (((((this.A01 * 31) + this.A03) * 31) + this.A02) * 31) + this.A00;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Insets{left=");
        sb.append(this.A01);
        sb.append(", top=");
        sb.append(this.A03);
        sb.append(", right=");
        sb.append(this.A02);
        sb.append(", bottom=");
        sb.append(this.A00);
        sb.append('}');
        return sb.toString();
    }

    public AnonymousClass057(int i, int i2, int i3, int i4) {
        this.A01 = i;
        this.A03 = i2;
        this.A02 = i3;
        this.A00 = i4;
    }
}
