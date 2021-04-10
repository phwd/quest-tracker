package X;

import android.text.TextUtils;

public final class ZR {
    public ZQ A00 = ZQ.NotSet;
    public Object A01 = null;
    public Object A02 = null;
    public String A03 = null;
    public boolean A04 = false;
    public boolean A05 = false;
    public boolean A06 = false;

    public final void A00(String str, boolean z) {
        if ((!TextUtils.equals(this.A03, str) || this.A05 != z) && !this.A06) {
            this.A04 = false;
        }
        this.A03 = str;
        this.A05 = z;
    }

    public final boolean A01(Object obj, boolean z) {
        boolean z2;
        this.A02 = obj;
        this.A06 = z | this.A06;
        Object obj2 = this.A01;
        if (obj2 != null ? obj2.equals(obj) : obj == null) {
            z2 = false;
        } else {
            this.A01 = obj;
            z2 = true;
        }
        this.A00 = ZQ.FromService;
        return z2;
    }

    public ZR() {
    }

    public ZR(Object obj, ZQ zq) {
        this.A02 = obj;
        this.A01 = obj;
        this.A00 = zq;
    }
}
