package defpackage;

import android.os.Build;
import com.oculus.browser.R;

/* renamed from: re1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C4605re1 implements EP0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C5285ve1 f11211a;

    public C4605re1(C5285ve1 ve1) {
        this.f11211a = ve1;
    }

    @Override // defpackage.EP0
    public void a(float f) {
        P11 V0 = this.f11211a.F.V0();
        V0.Y = f;
        V0.i();
    }

    @Override // defpackage.EP0
    public void b(float f) {
        C1413Xd1 xd1 = this.f11211a.A0.f11622a;
        if (xd1 != null) {
            xd1.Q = f;
            xd1.F.setNavigationBarColor(xd1.a(xd1.H.getColor(xd1.O ? R.color.f15350_resource_name_obfuscated_RES_2131100225 : R.color.f10060_resource_name_obfuscated_RES_2131099696)));
            if (Build.VERSION.SDK_INT >= 28) {
                xd1.F.setNavigationBarDividerColor(xd1.a(xd1.H.getColor(xd1.O ? R.color.f12670_resource_name_obfuscated_RES_2131099957 : R.color.f10070_resource_name_obfuscated_RES_2131099697)));
            }
            if (AbstractC4089od0.a(1.0f, f)) {
                AbstractC2417ep1.l(xd1.G, false);
            } else if (AbstractC4089od0.a(0.0f, f)) {
                AbstractC2417ep1.l(xd1.G, true);
            }
        }
    }
}
