package X;

import com.facebook.tigon.iface.TigonRequest;

/* renamed from: X.0wO  reason: invalid class name and case insensitive filesystem */
public class C08340wO {
    public Object A00;
    public String A01;
    public AbstractC08320wM A02;
    public C08420wY A03;
    public C08400wU A04;

    public final void A01(String str) {
        String str2;
        C08410wV r2;
        C08400wU A032;
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
                r2 = new C08410wV();
                if (r2.A02(null, str3) == AnonymousClass007.A00 || (A032 = r2.A03()) == null) {
                    throw new IllegalArgumentException(AnonymousClass006.A05("unexpected url: ", str3));
                }
                this.A04 = A032;
                return;
            }
            str3 = AnonymousClass006.A05(str2, str.substring(i));
            r2 = new C08410wV();
            if (r2.A02(null, str3) == AnonymousClass007.A00) {
            }
            throw new IllegalArgumentException(AnonymousClass006.A05("unexpected url: ", str3));
        }
        throw new NullPointerException("url == null");
    }

    public final C08330wN A00() {
        if (this.A04 != null) {
            return new C08330wN(this);
        }
        throw new IllegalStateException("url == null");
    }

    public final void A02(String str, String str2) {
        C08420wY r0 = this.A03;
        C08420wY.A00(str, str2);
        r0.A01(str);
        r0.A02(str, str2);
    }

    public final void A03(String str, AbstractC08320wM r5) {
        String str2;
        StringBuilder sb;
        String str3;
        if (str != null) {
            if (str.length() != 0) {
                if (r5 != null) {
                    if (!C08040vn.A00(str)) {
                        sb = new StringBuilder();
                        sb.append("method ");
                        sb.append(str);
                        str3 = " must not have a request body.";
                    }
                    this.A01 = str;
                    this.A02 = r5;
                    return;
                }
                if (C08040vn.A01(str)) {
                    sb = new StringBuilder();
                    sb.append("method ");
                    sb.append(str);
                    str3 = " must have a request body.";
                }
                this.A01 = str;
                this.A02 = r5;
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

    public C08340wO() {
        this.A01 = TigonRequest.GET;
        this.A03 = new C08420wY();
    }

    public C08340wO(C08330wN r2) {
        this.A04 = r2.A03;
        this.A01 = r2.A01;
        this.A02 = r2.A04;
        this.A00 = r2.A00;
        this.A03 = r2.A02.A01();
    }
}
