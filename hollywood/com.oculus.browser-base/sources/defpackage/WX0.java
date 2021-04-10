package defpackage;

import J.N;
import android.os.SystemClock;
import java.util.Objects;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.policy.EnterpriseInfo;
import org.chromium.components.policy.PolicyService;

/* renamed from: WX0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WX0 implements AbstractC1509Ys0 {
    public final C1128Sl F;
    public final C1570Zs0 G;
    public final long H;
    public final C3946nm1 I;

    /* renamed from: J  reason: collision with root package name */
    public C5563xE0 f9153J;
    public Boolean K;
    public Boolean L;

    public WX0(RQ rq, AbstractC1509Ys0 ys0, EnterpriseInfo enterpriseInfo, C3946nm1 nm1) {
        this.F = new C1128Sl();
        this.G = new C1570Zs0();
        this.H = SystemClock.elapsedRealtime();
        this.I = null;
        C5563xE0 xe0 = new C5563xE0(rq, ys0);
        this.f9153J = xe0;
        c(enterpriseInfo, xe0);
    }

    public void a() {
        this.F.a();
        C5563xE0 xe0 = this.f9153J;
        if (xe0 != null) {
            xe0.F.a();
            if (xe0.I != null) {
                PolicyService policyService = (PolicyService) xe0.H.get();
                policyService.b.c(xe0.I);
                if (policyService.b.isEmpty()) {
                    N.MU0pXsSP(policyService.f10882a, policyService);
                }
                xe0.I = null;
            }
            this.f9153J = null;
        }
    }

    /* renamed from: b */
    public Boolean get() {
        return (Boolean) this.G.get();
    }

    public final void c(EnterpriseInfo enterpriseInfo, AbstractC1509Ys0 ys0) {
        Boolean bool = (Boolean) ys0.g(this.F.b(new UX0(this)));
        if (bool != null) {
            e(bool.booleanValue());
        }
        if (this.G.get() == null) {
            enterpriseInfo.a(this.F.b(new VX0(this)));
        }
    }

    /* renamed from: d */
    public Boolean g(Callback callback) {
        C1570Zs0 zs0 = this.G;
        Objects.requireNonNull(zs0.G);
        zs0.F.g(callback);
        return (Boolean) zs0.get();
    }

    public final void e(boolean z) {
        if (this.K == null) {
            if (!z) {
                this.K = Boolean.TRUE;
            } else {
                this.K = Boolean.valueOf(N.MJs$aI$X());
                C3946nm1 nm1 = this.I;
                if (nm1 != null) {
                    AbstractC3364kK0.k(nm1.f10512a.F0 ? "MobileFre.CctTos.EnterprisePolicyCheckSpeed2.SlowerThanInflation" : "MobileFre.CctTos.EnterprisePolicyCheckSpeed2.FasterThanInflation", SystemClock.elapsedRealtime() - this.H);
                }
            }
            f();
        }
    }

    public final void f() {
        if (this.G.get() == null) {
            Boolean bool = this.L;
            boolean z = true;
            boolean z2 = bool != null && !bool.booleanValue();
            Boolean bool2 = this.K;
            boolean z3 = bool2 != null && bool2.booleanValue();
            if (!(this.L == null || this.K == null)) {
                C1570Zs0 zs0 = this.G;
                if (this.K.booleanValue() || !this.L.booleanValue()) {
                    z = false;
                }
                zs0.a(Boolean.valueOf(z));
            } else if (z3 || z2) {
                this.G.a(Boolean.FALSE);
            }
        }
    }

    public WX0(AbstractC1509Ys0 ys0, EnterpriseInfo enterpriseInfo, C3946nm1 nm1) {
        this.F = new C1128Sl();
        this.G = new C1570Zs0();
        this.H = SystemClock.elapsedRealtime();
        this.I = nm1;
        c(enterpriseInfo, ys0);
    }
}
