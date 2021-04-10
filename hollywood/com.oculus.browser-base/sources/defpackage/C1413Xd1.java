package defpackage;

import android.content.res.Resources;
import android.os.Build;
import android.view.ViewGroup;
import android.view.Window;
import com.oculus.browser.R;
import org.chromium.chrome.browser.vr.VrModuleProvider;

/* renamed from: Xd1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C1413Xd1 implements AbstractC3976nw1 {
    public final Window F;
    public final ViewGroup G;
    public final Resources H;
    public final int I;

    /* renamed from: J  reason: collision with root package name */
    public final AbstractC0124Ca1 f9222J;
    public final AbstractC0612Ka1 K;
    public AbstractC2260du0 L;
    public QK M;
    public C1128Sl N = new C1128Sl();
    public boolean O;
    public boolean P;
    public float Q;

    public C1413Xd1(Window window, AbstractC0124Ca1 ca1, AbstractC1509Ys0 ys0) {
        this.F = window;
        ViewGroup viewGroup = (ViewGroup) window.getDecorView().getRootView();
        this.G = viewGroup;
        Resources resources = viewGroup.getResources();
        this.H = resources;
        this.I = resources.getColor(R.color.f10000_resource_name_obfuscated_RES_2131099690);
        if (!resources.getBoolean(R.bool.f9560_resource_name_obfuscated_RES_2131034119)) {
            this.f9222J = null;
            this.K = null;
            return;
        }
        this.f9222J = ca1;
        C1291Vd1 vd1 = new C1291Vd1(this);
        this.K = vd1;
        ((AbstractC0246Ea1) ca1).c(vd1);
        ys0.g(this.N.b(new C1230Ud1(this)));
        b();
        VrModuleProvider.b.add(this);
    }

    public final int a(int i) {
        int i2 = this.I;
        return AbstractC1270Uv.b(i, i2 & -16777216, this.Q * (((float) (i2 >>> 24)) / 255.0f), true);
    }

    public final void b() {
        AbstractC2260du0 du0 = this.L;
        boolean z = false;
        boolean z2 = du0 != null && ((AbstractC3838n70) du0).C() && !this.P;
        if (C5052uE.a() || AbstractC4772sd1.b()) {
            z = ((AbstractC0246Ea1) this.f9222J).r();
        } else if (((AbstractC0246Ea1) this.f9222J).r() && !z2) {
            z = true;
        }
        boolean z3 = (!AbstractC2417ep1.i()) & z;
        if (this.O != z3) {
            this.O = z3;
            this.F.setNavigationBarColor(this.H.getColor(z3 ? R.color.f15350_resource_name_obfuscated_RES_2131100225 : R.color.f10060_resource_name_obfuscated_RES_2131099696));
            if (Build.VERSION.SDK_INT >= 28) {
                this.F.setNavigationBarDividerColor(this.H.getColor(this.O ? R.color.f12670_resource_name_obfuscated_RES_2131099957 : R.color.f10070_resource_name_obfuscated_RES_2131099697));
            }
            AbstractC2417ep1.l(this.G, !this.O);
        }
    }
}
