package X;

import com.facebook.tigon.iface.TigonRequest;

public class XO {
    public Object A00;
    public String A01;
    public XM A02;
    public XX A03;
    public XT A04;

    public final void A01(String str) {
        String str2;
        XU xu;
        XT A032;
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
                xu = new XU();
                if (xu.A02(null, str3) == AnonymousClass07.A00 || (A032 = xu.A03()) == null) {
                    throw new IllegalArgumentException(AnonymousClass06.A03("unexpected url: ", str3));
                }
                this.A04 = A032;
                return;
            }
            str3 = AnonymousClass06.A03(str2, str.substring(i));
            xu = new XU();
            if (xu.A02(null, str3) == AnonymousClass07.A00) {
            }
            throw new IllegalArgumentException(AnonymousClass06.A03("unexpected url: ", str3));
        }
        throw new NullPointerException("url == null");
    }

    public final XN A00() {
        if (this.A04 != null) {
            return new XN(this);
        }
        throw new IllegalStateException("url == null");
    }

    public final void A02(String str, String str2) {
        XX xx = this.A03;
        XX.A00(str, str2);
        xx.A01(str);
        xx.A02(str, str2);
    }

    public final void A03(String str, XM xm) {
        String str2;
        StringBuilder sb;
        String str3;
        if (str != null) {
            if (str.length() != 0) {
                if (xm != null) {
                    if (!C0170Wv.A00(str)) {
                        sb = new StringBuilder();
                        sb.append("method ");
                        sb.append(str);
                        str3 = " must not have a request body.";
                    }
                    this.A01 = str;
                    this.A02 = xm;
                    return;
                }
                if (C0170Wv.A01(str)) {
                    sb = new StringBuilder();
                    sb.append("method ");
                    sb.append(str);
                    str3 = " must have a request body.";
                }
                this.A01 = str;
                this.A02 = xm;
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

    public XO() {
        this.A01 = TigonRequest.GET;
        this.A03 = new XX();
    }

    public XO(XN xn) {
        this.A04 = xn.A03;
        this.A01 = xn.A01;
        this.A02 = xn.A04;
        this.A00 = xn.A00;
        this.A03 = xn.A02.A01();
    }
}
