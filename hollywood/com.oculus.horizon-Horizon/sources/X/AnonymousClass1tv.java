package X;

import javax.annotation.Nullable;

/* renamed from: X.1tv  reason: invalid class name */
public class AnonymousClass1tv implements AnonymousClass0Sz {
    public boolean A00 = true;
    public final /* synthetic */ AnonymousClass1tm A01;
    public final /* synthetic */ StringBuilder A02;

    public AnonymousClass1tv(AnonymousClass1tm r2, StringBuilder sb) {
        this.A01 = r2;
        this.A02 = sb;
    }

    @Override // X.AnonymousClass0Sz
    public final void A9l(long j, long j2, int i, String str, @Nullable AnonymousClass0T7 r10, AnonymousClass0VE<?> r11) {
        if (!this.A00) {
            this.A02.append(',');
        } else {
            this.A00 = false;
        }
        StringBuilder sb = this.A02;
        sb.append("{\"name\":\"");
        sb.append(AnonymousClass1tm.A02(str));
        sb.append('\"');
        sb.append(",\"value\":");
        sb.append(j);
        if (r10 != null) {
            sb.append(",\"data\":{");
            r10.A00(new AnonymousClass1u1(this));
            sb.append("}");
        }
        sb.append("}");
    }
}
