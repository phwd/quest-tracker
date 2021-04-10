package X;

import javax.annotation.Nullable;
import javax.annotation.concurrent.GuardedBy;

/* renamed from: X.Yg  reason: case insensitive filesystem */
public final class C0258Yg implements H1 {
    @Nullable
    public G0 A00;
    @Nullable
    public C0260Yj A01;
    @Nullable
    public String A02;
    @GuardedBy("this")
    public boolean A03;
    public final Fc A04;
    public final C0257Yf A05;
    public final H0 A06;
    public final boolean A07 = true;

    public C0258Yg(H0 h0, Fc fc, C0257Yf yf) {
        this.A06 = h0;
        this.A04 = fc;
        this.A05 = yf;
    }

    private synchronized void A01() {
        if (!this.A03) {
            throw new IllegalStateException("SessionDelegate should have called bootstrapIfNeeded");
        }
    }

    public final synchronized void A03() {
        C0260Yj yj;
        if (!this.A03) {
            H0 h0 = this.A06;
            synchronized (h0) {
                if (!h0.mPigeonIdentityKnown) {
                    h0.mPigeonIdentity = h0.A00();
                    h0.mPigeonIdentityKnown = true;
                }
                yj = h0.mPigeonIdentity;
            }
            this.A01 = yj;
            h0.mSessionManagerCallbackObservable.registerObserver(this);
            A02(AnonymousClass07.A00);
            this.A03 = true;
        }
    }

    private void A00() {
        this.A00 = new G0(this.A01, this.A02);
        synchronized (this) {
            this.A04.A5O(this.A00);
        }
    }

    private void A02(Integer num) {
        String str;
        String obj;
        C0257Yf yf = this.A05;
        synchronized (yf) {
            StringBuilder sb = new StringBuilder();
            sb.append("UFS");
            sb.append("-");
            sb.append(yf.A01);
            sb.append("-");
            if (1 - num.intValue() != 0) {
                str = "fg";
            } else {
                str = "bg";
            }
            sb.append(str);
            sb.append("-");
            int i = yf.A00;
            yf.A00 = i + 1;
            sb.append(i);
            obj = sb.toString();
        }
        this.A02 = obj;
        A00();
    }

    @Override // X.H1
    public final void A3c() {
        A01();
        A02(AnonymousClass07.A01);
    }

    @Override // X.H1
    public final void A3l() {
        A01();
        A02(AnonymousClass07.A00);
    }

    @Override // X.H1
    public final void A5b(C0260Yj yj) {
        A01();
        this.A01 = yj;
        if (this.A07) {
            A02(AnonymousClass07.A00);
        } else {
            A00();
        }
    }

    @Override // X.H1
    public final void A5c() {
        A01();
        this.A04.A3Y(this.A01);
        this.A01 = null;
        if (this.A07) {
            A02(AnonymousClass07.A00);
        } else {
            A00();
        }
    }
}
