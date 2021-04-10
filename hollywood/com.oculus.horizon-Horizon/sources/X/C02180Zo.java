package X;

import com.facebook.infer.annotation.Nullsafe;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0Zo  reason: invalid class name and case insensitive filesystem */
public final class C02180Zo {
    public final int A00;
    public final String A01;

    public final String toString() {
        StringBuilder sb = new StringBuilder("topic:");
        sb.append(this.A01);
        sb.append(" messageId:");
        sb.append(this.A00);
        return sb.toString();
    }

    public C02180Zo(String str, int i) {
        this.A01 = str;
        this.A00 = i;
    }
}
