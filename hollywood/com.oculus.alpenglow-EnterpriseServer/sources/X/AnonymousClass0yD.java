package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.0yD  reason: invalid class name */
public class AnonymousClass0yD {
    public final AnonymousClass0z5 A00;
    @Nullable
    public final Object A01;
    public final Object A02;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        AnonymousClass0z5 r1 = this.A00;
        sb.append(r1.A03.toString());
        sb.append(' ');
        sb.append(EnumC08980yG.fromInt(r1.A02));
        return sb.toString();
    }

    public AnonymousClass0yD(AnonymousClass0z5 r1, Object obj, @Nullable Object obj2) {
        this.A00 = r1;
        this.A02 = obj;
        this.A01 = obj2;
    }

    @Nullable
    public Object A00() {
        return this.A01;
    }

    public Object A01() {
        return this.A02;
    }
}
