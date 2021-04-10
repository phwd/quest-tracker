package defpackage;

import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.viewpager.widget.ViewPager;
import org.chromium.chrome.browser.keyboard_accessory.sheet_component.AccessorySheetView;

/* renamed from: k0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C3303k0 implements YH0 {
    @Override // defpackage.YH0
    public void a(Object obj, Object obj2, Object obj3) {
        String str;
        UH0 uh0 = (UH0) obj;
        AccessorySheetView accessorySheetView = (AccessorySheetView) obj2;
        KH0 kh0 = (KH0) obj3;
        OH0 oh0 = AbstractC4158p0.f11043a;
        if (kh0 == oh0) {
            C1794b90 b90 = (C1794b90) uh0.g(oh0);
            ViewPager viewPager = accessorySheetView.F;
            C3132j0 j0Var = new C3132j0(b90);
            b90.F.b(new C2306e90(b90, viewPager, j0Var));
            accessorySheetView.F.w(j0Var);
            return;
        }
        QH0 qh0 = AbstractC4158p0.c;
        int i = 0;
        if (kh0 == qh0) {
            accessorySheetView.bringToFront();
            if (!uh0.h(qh0)) {
                i = 8;
            }
            accessorySheetView.setVisibility(i);
            if (uh0.h(qh0)) {
                SH0 sh0 = AbstractC4158p0.b;
                if (uh0.f(sh0) != -1 && (str = ((C3148j50) ((C1794b90) uh0.g(oh0)).get(uh0.f(sh0))).c) != null) {
                    accessorySheetView.announceForAccessibility(str);
                    return;
                }
                return;
            }
            return;
        }
        SH0 sh02 = AbstractC4158p0.d;
        if (kh0 == sh02) {
            ViewGroup.LayoutParams layoutParams = accessorySheetView.getLayoutParams();
            layoutParams.height = uh0.f(sh02);
            accessorySheetView.setLayoutParams(layoutParams);
            return;
        }
        QH0 qh02 = AbstractC4158p0.e;
        if (kh0 == qh02) {
            boolean h = uh0.h(qh02);
            ImageView imageView = accessorySheetView.G;
            if (!h) {
                i = 4;
            }
            imageView.setVisibility(i);
            return;
        }
        SH0 sh03 = AbstractC4158p0.b;
        if (kh0 != sh03) {
            TH0 th0 = AbstractC4158p0.f;
            if (kh0 == th0 && uh0.g(th0) != null) {
                accessorySheetView.F.b((Du1) uh0.g(th0));
            }
        } else if (uh0.f(sh03) != -1) {
            int f = uh0.f(sh03);
            accessorySheetView.F.x(f);
            accessorySheetView.F.post(new C0(accessorySheetView, f));
        }
    }
}
