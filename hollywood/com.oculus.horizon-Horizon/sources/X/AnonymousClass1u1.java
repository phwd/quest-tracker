package X;

import javax.annotation.Nullable;

/* renamed from: X.1u1  reason: invalid class name */
public class AnonymousClass1u1 implements AnonymousClass0T6 {
    public boolean A00 = true;
    public final /* synthetic */ AnonymousClass1tv A01;

    public AnonymousClass1u1(AnonymousClass1tv r2) {
        this.A01 = r2;
    }

    @Override // X.AnonymousClass0T6
    public final void A9w(String str, @Nullable String str2, int i) {
        if (this.A00) {
            this.A00 = false;
        } else {
            this.A01.A02.append(',');
        }
        StringBuilder sb = this.A01.A02;
        sb.append('\"');
        sb.append(AnonymousClass1tm.A02(str));
        sb.append("\":\"");
        sb.append(AnonymousClass1tm.A02(str2));
        sb.append('\"');
    }
}
