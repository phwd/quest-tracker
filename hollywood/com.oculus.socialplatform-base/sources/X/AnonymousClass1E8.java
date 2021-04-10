package X;

import androidx.annotation.NonNull;

/* renamed from: X.1E8  reason: invalid class name */
public class AnonymousClass1E8 implements Comparable<AnonymousClass1E8> {
    public int A00;
    public int A01;

    /* JADX DEBUG: Method arguments types fixed to match base method, original types: [java.lang.Object] */
    @Override // java.lang.Comparable
    public final int compareTo(@NonNull AnonymousClass1E8 r3) {
        AnonymousClass1E8 r32 = r3;
        int i = this.A01;
        int i2 = r32.A01;
        if (i == i2) {
            i = this.A00;
            i2 = r32.A00;
        }
        return i - i2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("Order{order=");
        sb.append(this.A01);
        sb.append(", index=");
        sb.append(this.A00);
        sb.append('}');
        return sb.toString();
    }
}
