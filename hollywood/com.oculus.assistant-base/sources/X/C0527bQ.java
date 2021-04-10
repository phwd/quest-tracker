package X;

import com.facebook.assistant.oacr.OacrConstants;
import java.util.concurrent.TimeUnit;

/* renamed from: X.bQ  reason: case insensitive filesystem */
public final class C0527bQ {
    public static final C0527bQ A0C;
    public static final C0527bQ A0D;
    public String A00;
    public final int A01;
    public final int A02;
    public final int A03;
    public final int A04;
    public final boolean A05;
    public final boolean A06;
    public final boolean A07;
    public final boolean A08;
    public final boolean A09;
    public final boolean A0A;
    public final boolean A0B;

    static {
        int i;
        C0526bP bPVar = new C0526bP();
        bPVar.A01 = true;
        A0D = new C0527bQ(bPVar);
        C0526bP bPVar2 = new C0526bP();
        bPVar2.A02 = true;
        long seconds = TimeUnit.SECONDS.toSeconds((long) Integer.MAX_VALUE);
        if (seconds > 2147483647L) {
            i = Integer.MAX_VALUE;
        } else {
            i = (int) seconds;
        }
        bPVar2.A00 = i;
        A0C = new C0527bQ(bPVar2);
    }

    public final String toString() {
        String str = this.A00;
        if (str == null) {
            StringBuilder sb = new StringBuilder();
            if (this.A08) {
                sb.append("no-cache, ");
            }
            if (this.A09) {
                sb.append("no-store, ");
            }
            int i = this.A01;
            if (i != -1) {
                sb.append("max-age=");
                sb.append(i);
                sb.append(", ");
            }
            int i2 = this.A04;
            if (i2 != -1) {
                sb.append("s-maxage=");
                sb.append(i2);
                sb.append(", ");
            }
            if (this.A05) {
                sb.append("private, ");
            }
            if (this.A06) {
                sb.append("public, ");
            }
            if (this.A07) {
                sb.append("must-revalidate, ");
            }
            int i3 = this.A02;
            if (i3 != -1) {
                sb.append("max-stale=");
                sb.append(i3);
                sb.append(", ");
            }
            int i4 = this.A03;
            if (i4 != -1) {
                sb.append("min-fresh=");
                sb.append(i4);
                sb.append(", ");
            }
            if (this.A0B) {
                sb.append("only-if-cached, ");
            }
            if (this.A0A) {
                sb.append("no-transform, ");
            }
            if (sb.length() == 0) {
                str = OacrConstants.AUTO_SPEECH_DOMAIN;
            } else {
                sb.delete(sb.length() - 2, sb.length());
                str = sb.toString();
            }
            this.A00 = str;
        }
        return str;
    }

    public C0527bQ(C0526bP bPVar) {
        this.A08 = bPVar.A01;
        this.A09 = false;
        this.A01 = -1;
        this.A04 = -1;
        this.A05 = false;
        this.A06 = false;
        this.A07 = false;
        this.A02 = bPVar.A00;
        this.A03 = -1;
        this.A0B = bPVar.A02;
        this.A0A = false;
    }

    public C0527bQ(boolean z, boolean z2, int i, int i2, boolean z3, boolean z4, boolean z5, int i3, int i4, boolean z6, boolean z7, String str) {
        this.A08 = z;
        this.A09 = z2;
        this.A01 = i;
        this.A04 = i2;
        this.A05 = z3;
        this.A06 = z4;
        this.A07 = z5;
        this.A02 = i3;
        this.A03 = i4;
        this.A0B = z6;
        this.A0A = z7;
        this.A00 = str;
    }
}
