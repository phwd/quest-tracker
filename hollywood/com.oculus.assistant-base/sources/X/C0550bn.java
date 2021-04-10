package X;

import com.facebook.tigon.iface.TigonRequest;

/* renamed from: X.bn  reason: case insensitive filesystem */
public final class C0550bn {
    public Object A00;
    public String A01;
    public AbstractC0552bp A02;
    public C0541be A03;
    public C0544bh A04;

    public final void A01(String str) {
        String str2;
        C0543bg bgVar;
        C0544bh A032;
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
                bgVar = new C0543bg();
                if (bgVar.A02(null, str3) == AnonymousClass09.A00 || (A032 = bgVar.A03()) == null) {
                    throw new IllegalArgumentException(AnonymousClass08.A04("unexpected url: ", str3));
                }
                this.A04 = A032;
                return;
            }
            str3 = AnonymousClass08.A04(str2, str.substring(i));
            bgVar = new C0543bg();
            if (bgVar.A02(null, str3) == AnonymousClass09.A00) {
            }
            throw new IllegalArgumentException(AnonymousClass08.A04("unexpected url: ", str3));
        }
        throw new NullPointerException("url == null");
    }

    public final C0551bo A00() {
        if (this.A04 != null) {
            return new C0551bo(this);
        }
        throw new IllegalStateException("url == null");
    }

    public final void A02(String str, String str2) {
        C0541be beVar = this.A03;
        C0541be.A00(str, str2);
        beVar.A01(str);
        beVar.A02(str, str2);
    }

    public final void A03(String str, AbstractC0552bp bpVar) {
        if (str == null) {
            throw new NullPointerException("method == null");
        } else if (str.length() != 0) {
            if (bpVar != null) {
                if (!C0575cE.A00(str)) {
                    throw new IllegalArgumentException(AnonymousClass08.A05("method ", str, " must not have a request body."));
                }
            } else if (C0575cE.A01(str)) {
                throw new IllegalArgumentException(AnonymousClass08.A05("method ", str, " must have a request body."));
            }
            this.A01 = str;
            this.A02 = bpVar;
        } else {
            throw new IllegalArgumentException("method.length() == 0");
        }
    }

    public C0550bn() {
        this.A01 = TigonRequest.GET;
        this.A03 = new C0541be();
    }

    public C0550bn(C0551bo boVar) {
        this.A04 = boVar.A03;
        this.A01 = boVar.A01;
        this.A02 = boVar.A04;
        this.A00 = boVar.A00;
        this.A03 = boVar.A02.A01();
    }
}
