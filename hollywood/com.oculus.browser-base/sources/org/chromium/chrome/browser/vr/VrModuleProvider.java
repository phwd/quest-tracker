package org.chromium.chrome.browser.vr;

import J.N;
import com.oculus.browser.R;
import java.util.ArrayList;
import java.util.List;
import org.chromium.chrome.browser.tab.Tab;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class VrModuleProvider implements AbstractC5135ul0 {

    /* renamed from: a  reason: collision with root package name */
    public static C2780gw1 f10799a;
    public static final List b = new ArrayList();
    public long c;
    public Tab d;

    public VrModuleProvider(long j) {
        this.c = j;
    }

    public static AbstractC2097cw1 b() {
        return c().f10036a;
    }

    public static C2780gw1 c() {
        if (f10799a == null) {
            if (!AbstractC4147ow1.a()) {
                f10799a = new C2780gw1();
            } else {
                f10799a = (C2780gw1) AbstractC4147ow1.f11037a.b();
            }
        }
        return f10799a;
    }

    public static VrModuleProvider create(long j) {
        return new VrModuleProvider(j);
    }

    public static AbstractC3463kw1 d() {
        return c().b;
    }

    public static void e(AbstractC1593a20 a20) {
        AbstractC4147ow1.f11037a.d(new C4318pw1(a20));
    }

    public static boolean isModuleInstalled() {
        return AbstractC4147ow1.a();
    }

    @Override // defpackage.AbstractC5135ul0
    public void a(boolean z) {
        long j = this.c;
        if (j != 0) {
            if (z) {
                installModule(this.d);
            } else {
                N.Mmw1DU8y(j, this, false);
            }
        }
    }

    public final void installModule(Tab tab) {
        this.d = tab;
        C5305vl0 vl0 = new C5305vl0(tab, R.string.f64560_resource_name_obfuscated_RES_2131953773, this);
        vl0.b();
        e(new C4489qw1(this, vl0));
    }

    public final void onNativeDestroy() {
        this.c = 0;
    }
}
