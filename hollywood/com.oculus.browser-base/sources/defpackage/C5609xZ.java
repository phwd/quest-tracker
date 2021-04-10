package defpackage;

import J.N;
import android.content.Context;
import com.oculus.browser.R;
import java.util.Collections;
import java.util.Iterator;
import org.chromium.base.Callback;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.signin.base.CoreAccountInfo;
import org.chromium.components.signin.identitymanager.IdentityManager;
import org.chromium.components.signin.identitymanager.PrimaryAccountChangeEvent;

/* renamed from: xZ  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5609xZ extends AbstractC5779yZ implements AbstractC4968tm0, VG0, AbstractC0639Kk {
    public final Context F;
    public M2 G;
    public final AbstractC0956Pq0 H;
    public final Callback I = new C5269vZ(this);

    /* renamed from: J  reason: collision with root package name */
    public IdentityManager f11614J;
    public WG0[] K = new WG0[3];
    public int L = 0;
    public C0517Ik M;
    public C1322Vq0 N = new C1322Vq0();
    public boolean O;

    public C5609xZ(Context context, M2 m2, AbstractC0956Pq0 pq0) {
        this.F = context;
        this.G = m2;
        this.H = pq0;
        m2.a(this);
        this.M = new C0517Ik(false, null, new View$OnClickListenerC5439wZ(this), R.string.f46220_resource_name_obfuscated_RES_2131951939, false, new ZY(context.getResources(), "IPH_IdentityDisc", R.string.f53360_resource_name_obfuscated_RES_2131952653, R.string.f53350_resource_name_obfuscated_RES_2131952652), true);
    }

    @Override // defpackage.VG0
    public void D(String str) {
        if (this.L != 0 && str.equals(CoreAccountInfo.b(V()))) {
            W(false);
            W(true);
        }
    }

    @Override // defpackage.AbstractC0639Kk
    public void O(AbstractC0578Jk jk) {
        this.N.b(jk);
    }

    @Override // defpackage.AbstractC5779yZ
    public void T(PrimaryAccountChangeEvent primaryAccountChangeEvent) {
        int a2 = primaryAccountChangeEvent.a(0);
        if (a2 == 1) {
            for (int i = 0; i < 3; i++) {
                WG0[] wg0Arr = this.K;
                if (wg0Arr[i] != null) {
                    wg0Arr[i].Y(this);
                    this.K[i] = null;
                }
            }
            W(true);
        } else if (a2 == 2) {
            W(false);
        }
    }

    public final void U() {
        if (this.O) {
            String b = CoreAccountInfo.b(V());
            int i = b == null ? 0 : 1;
            this.L = i;
            if (i != 0 && this.K[i] == null) {
                WG0 wg0 = new WG0(this.F, this.F.getResources().getDimensionPixelSize(i == 1 ? R.dimen.f26350_resource_name_obfuscated_RES_2131166254 : R.dimen.f26360_resource_name_obfuscated_RES_2131166255));
                wg0.U(this);
                wg0.Z(Collections.singletonList(b));
                this.K[i] = wg0;
            }
            int i2 = this.L;
            if (i2 != 0) {
                this.M.b = this.K[i2].W(b).b;
                this.M.f8247a = true;
                return;
            }
            this.M.f8247a = false;
        }
    }

    public final CoreAccountInfo V() {
        int i = !N.M09VlOh_("MobileIdentityConsistency") ? 1 : 0;
        IdentityManager identityManager = this.f11614J;
        if (identityManager != null) {
            return identityManager.b(i);
        }
        return null;
    }

    public final void W(boolean z) {
        Iterator it = this.N.iterator();
        while (true) {
            C1261Uq0 uq0 = (C1261Uq0) it;
            if (uq0.hasNext()) {
                ((AbstractC0578Jk) uq0.next()).a(z);
            } else {
                return;
            }
        }
    }

    @Override // defpackage.AbstractC0639Kk
    public void destroy() {
        M2 m2 = this.G;
        if (m2 != null) {
            m2.b(this);
            this.G = null;
        }
        for (int i = 0; i < 3; i++) {
            WG0[] wg0Arr = this.K;
            if (wg0Arr[i] != null) {
                wg0Arr[i].Y(this);
                this.K[i] = null;
            }
        }
        IdentityManager identityManager = this.f11614J;
        if (identityManager != null) {
            identityManager.b.c(this);
            this.f11614J = null;
        }
        if (this.O) {
            AbstractC0956Pq0 pq0 = this.H;
            ((C1078Rq0) pq0).I.c(this.I);
        }
    }

    @Override // defpackage.AbstractC0639Kk
    public void n(AbstractC0578Jk jk) {
        this.N.c(jk);
    }

    @Override // defpackage.AbstractC0639Kk
    public C0517Ik r(Tab tab) {
        if (tab != null) {
            tab.Q();
        }
        C0517Ik ik = this.M;
        ik.f8247a = false;
        return ik;
    }

    @Override // defpackage.AbstractC4968tm0
    public void s() {
        this.G.b(this);
        this.G = null;
        this.O = true;
        ((C1078Rq0) this.H).l(this.I);
    }
}
