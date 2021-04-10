package X;

import com.facebook.tigon.iface.TigonRequest;

/* renamed from: X.0kf  reason: invalid class name and case insensitive filesystem */
public class C05710kf {
    public Object A00;
    public String A01;
    public AbstractC05690kc A02;
    public C06030lq A03;
    public C05890la A04;

    public final void A01(String str) {
        String str2;
        C06000lm r2;
        C05890la A032;
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
                r2 = new C06000lm();
                if (r2.A02(null, str3) == AnonymousClass007.A00 || (A032 = r2.A03()) == null) {
                    throw new IllegalArgumentException(AnonymousClass006.A05("unexpected url: ", str3));
                }
                this.A04 = A032;
                return;
            }
            str3 = AnonymousClass006.A05(str2, str.substring(i));
            r2 = new C06000lm();
            if (r2.A02(null, str3) == AnonymousClass007.A00) {
            }
            throw new IllegalArgumentException(AnonymousClass006.A05("unexpected url: ", str3));
        }
        throw new NullPointerException("url == null");
    }

    public final C05700ke A00() {
        if (this.A04 != null) {
            return new C05700ke(this);
        }
        throw new IllegalStateException("url == null");
    }

    public final void A02(String str, String str2) {
        C06030lq r0 = this.A03;
        C06030lq.A00(str, str2);
        r0.A01(str);
        r0.A02(str, str2);
    }

    public final void A03(String str, AbstractC05690kc r5) {
        String str2;
        StringBuilder sb;
        String str3;
        if (str != null) {
            if (str.length() != 0) {
                if (r5 != null) {
                    if (!AnonymousClass0i3.A00(str)) {
                        sb = new StringBuilder();
                        sb.append("method ");
                        sb.append(str);
                        str3 = " must not have a request body.";
                    }
                    this.A01 = str;
                    this.A02 = r5;
                    return;
                }
                if (AnonymousClass0i3.A01(str)) {
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

    public C05710kf() {
        this.A01 = TigonRequest.GET;
        this.A03 = new C06030lq();
    }

    public C05710kf(C05700ke r2) {
        this.A04 = r2.A03;
        this.A01 = r2.A01;
        this.A02 = r2.A04;
        this.A00 = r2.A00;
        this.A03 = r2.A02.A01();
    }
}
