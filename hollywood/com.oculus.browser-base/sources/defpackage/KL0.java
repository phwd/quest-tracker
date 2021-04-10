package defpackage;

import com.google.android.gms.common.api.Status;
import java.util.Objects;

/* renamed from: KL0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class KL0 extends AbstractC2325eF1 {
    public RF1 q;
    public final boolean r;
    public final /* synthetic */ ML0 s;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KL0(ML0 ml0, YV yv) {
        super(yv);
        this.s = ml0;
        this.r = false;
        this.q = new C2152dE1(this, ml0);
    }

    @Override // com.google.android.gms.common.api.internal.BasePendingResult
    public /* synthetic */ AM0 c(Status status) {
        return new C2664gE1(status);
    }

    @Override // defpackage.AbstractC4439qg
    public void j(Z6 z6) {
        C3350kF1 kf1 = (C3350kF1) z6;
        if (!this.r) {
            for (C2490fD1 fd1 : this.s.h) {
                Objects.requireNonNull(fd1);
            }
            for (GL0 gl0 : this.s.i) {
                Objects.requireNonNull(gl0);
            }
        }
        try {
            synchronized (this.s.f8471a) {
                n(kf1);
            }
        } catch (PF1 unused) {
            f(new C2664gE1(new Status(2100)));
        }
    }

    public abstract void n(C3350kF1 kf1);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public KL0(ML0 ml0, YV yv, boolean z) {
        super(yv);
        this.s = ml0;
        this.r = z;
        this.q = new C2152dE1(this, ml0);
    }
}
