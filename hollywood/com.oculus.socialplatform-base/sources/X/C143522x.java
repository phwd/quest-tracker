package X;

import com.facebook.infer.annotation.Nullsafe;
import javax.annotation.Nullable;

@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.22x  reason: invalid class name and case insensitive filesystem */
public class C143522x {
    public final AnonymousClass23P A00;
    @Nullable
    public final Object A01;
    public final Object A02;

    @Nullable
    public Object A00() {
        return this.A01;
    }

    public Object A01() {
        return this.A02;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        AnonymousClass23P r1 = this.A00;
        sb.append(r1.A03.toString());
        sb.append(' ');
        sb.append(EnumC143322v.fromInt(r1.A02));
        return sb.toString();
    }

    public C143522x(AnonymousClass23P r1, Object obj, @Nullable Object obj2) {
        this.A00 = r1;
        this.A02 = obj;
        this.A01 = obj2;
    }
}
