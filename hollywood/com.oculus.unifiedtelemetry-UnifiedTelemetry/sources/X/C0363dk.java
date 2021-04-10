package X;

import com.facebook.tigon.iface.TigonRequest;

/* renamed from: X.dk  reason: case insensitive filesystem */
public class C0363dk {
    public String A00;
    public AbstractC0361di A01;
    public Object A02;
    public C0370dt A03;
    public C0367dp A04;

    public final void A01(String str) {
        String str2;
        C0368dq dqVar;
        C0367dp A032;
        String str3 = str;
        if (str != null) {
            int i = 3;
            if (str3.regionMatches(true, 0, "ws:", 0, 3)) {
                str2 = "http:";
            } else {
                i = 4;
                if (str3.regionMatches(true, 0, "wss:", 0, 4)) {
                    str2 = "https:";
                }
                dqVar = new C0368dq();
                if (dqVar.A02(null, str3) == AnonymousClass07.A00 || (A032 = dqVar.A03()) == null) {
                    throw new IllegalArgumentException(AnonymousClass06.A04("unexpected url: ", str3));
                }
                this.A04 = A032;
                return;
            }
            str3 = AnonymousClass06.A04(str2, str.substring(i));
            dqVar = new C0368dq();
            if (dqVar.A02(null, str3) == AnonymousClass07.A00) {
            }
            throw new IllegalArgumentException(AnonymousClass06.A04("unexpected url: ", str3));
        }
        throw new NullPointerException("url == null");
    }

    public final C0362dj A00() {
        if (this.A04 != null) {
            return new C0362dj(this);
        }
        throw new IllegalStateException("url == null");
    }

    public final void A02(String str, String str2) {
        C0370dt dtVar = this.A03;
        C0370dt.A00(str, str2);
        dtVar.A01(str);
        dtVar.A02(str, str2);
    }

    public final void A03(String str, AbstractC0361di diVar) {
        String str2;
        StringBuilder sb;
        String str3;
        if (str != null) {
            if (str.length() != 0) {
                if (diVar != null) {
                    if (!C0347dH.A00(str)) {
                        sb = new StringBuilder();
                        sb.append("method ");
                        sb.append(str);
                        str3 = " must not have a request body.";
                    }
                    this.A00 = str;
                    this.A01 = diVar;
                    return;
                }
                if (C0347dH.A01(str)) {
                    sb = new StringBuilder();
                    sb.append("method ");
                    sb.append(str);
                    str3 = " must have a request body.";
                }
                this.A00 = str;
                this.A01 = diVar;
                return;
                sb.append(str3);
                str2 = sb.toString();
            } else {
                str2 = "method.length() == 0";
            }
            throw new IllegalArgumentException(str2);
        }
        throw new NullPointerException("method == null");
    }

    public C0363dk() {
        this.A00 = TigonRequest.GET;
        this.A03 = new C0370dt();
    }

    public C0363dk(C0362dj djVar) {
        this.A04 = djVar.A03;
        this.A00 = djVar.A01;
        this.A01 = djVar.A04;
        this.A02 = djVar.A00;
        this.A03 = djVar.A02.A01();
    }
}
