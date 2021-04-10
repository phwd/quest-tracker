package defpackage;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import com.google.android.material.tabs.TabLayout;
import com.oculus.browser.R;
import org.chromium.chrome.browser.keyboard_accessory.tab_layout_component.KeyboardAccessoryTabLayoutView;

/* renamed from: U50  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class U50 implements AbstractC2136d90 {
    public static void d(UH0 uh0, KeyboardAccessoryTabLayoutView keyboardAccessoryTabLayoutView, KH0 kh0) {
        AbstractC5716y81 y81;
        OH0 oh0 = S50.f8876a;
        int i = 0;
        if (kh0 == oh0) {
            U50 u50 = new U50();
            ((C1794b90) uh0.g(oh0)).F.b(new C2306e90((C1794b90) uh0.g(oh0), keyboardAccessoryTabLayoutView, u50));
            u50.e(keyboardAccessoryTabLayoutView, (C1794b90) uh0.g(oh0));
            C1794b90 b90 = (C1794b90) uh0.g(oh0);
            while (i < b90.size()) {
                ((C3148j50) b90.get(i)).h.F.add(new T50(u50, b90, keyboardAccessoryTabLayoutView, i));
                i++;
            }
            return;
        }
        TH0 th0 = S50.b;
        if (kh0 == th0) {
            Integer num = (Integer) uh0.g(th0);
            for (int j = keyboardAccessoryTabLayoutView.j() - 1; j >= 0; j--) {
                D81 i2 = keyboardAccessoryTabLayoutView.i(j);
                if (!(i2 == null || i2.f7870a == null)) {
                    int i3 = 16842913;
                    if (num == null || j != num.intValue()) {
                        i3 = -16842913;
                    } else if (!i2.a()) {
                        i2.b();
                    }
                    Drawable drawable = i2.f7870a;
                    ColorStateList colorStateList = keyboardAccessoryTabLayoutView.P;
                    drawable.setColorFilter(colorStateList.getColorForState(new int[]{i3}, colorStateList.getDefaultColor()), PorterDuff.Mode.SRC_IN);
                }
            }
            TH0 th02 = S50.b;
            int intValue = uh0.g(th02) != null ? ((Integer) uh0.g(th02)).intValue() : -1;
            while (true) {
                OH0 oh02 = S50.f8876a;
                if (i < ((C1794b90) uh0.g(oh02)).size()) {
                    C3148j50 j50 = (C3148j50) ((C1794b90) uh0.g(oh02)).get(i);
                    if (intValue == i) {
                        D81 i4 = keyboardAccessoryTabLayoutView.i(i);
                        if (i4 == null) {
                            continue;
                        } else {
                            TabLayout tabLayout = i4.f;
                            if (tabLayout != null) {
                                i4.c = tabLayout.getResources().getText(R.string.f53620_resource_name_obfuscated_RES_2131952679);
                                i4.e();
                            } else {
                                throw new IllegalArgumentException("Tab not attached to a TabLayout");
                            }
                        }
                    } else {
                        String str = j50.d;
                        D81 i5 = keyboardAccessoryTabLayoutView.i(i);
                        if (i5 != null) {
                            i5.c = str;
                            i5.e();
                        }
                    }
                    i++;
                } else {
                    return;
                }
            }
        } else {
            TH0 th03 = S50.c;
            if (kh0 == th03 && (y81 = (AbstractC5716y81) uh0.g(th03)) != null) {
                keyboardAccessoryTabLayoutView.m0.clear();
                if (!keyboardAccessoryTabLayoutView.m0.contains(y81)) {
                    keyboardAccessoryTabLayoutView.m0.add(y81);
                }
            }
        }
    }

    @Override // defpackage.AbstractC2136d90
    public void a(Object obj, Object obj2, int i, int i2) {
        e((KeyboardAccessoryTabLayoutView) obj2, (C1794b90) obj);
    }

    @Override // defpackage.AbstractC2136d90
    public void b(Object obj, Object obj2, int i, int i2) {
        e((KeyboardAccessoryTabLayoutView) obj2, (C1794b90) obj);
    }

    @Override // defpackage.AbstractC2136d90
    public void c(Object obj, Object obj2, int i, int i2) {
        e((KeyboardAccessoryTabLayoutView) obj2, (C1794b90) obj);
    }

    public final void e(KeyboardAccessoryTabLayoutView keyboardAccessoryTabLayoutView, C1794b90 b90) {
        keyboardAccessoryTabLayoutView.n();
        if (b90.size() > 0) {
            for (int i = 0; i < b90.size(); i++) {
                C3148j50 j50 = (C3148j50) b90.get(i);
                Drawable drawable = j50.b;
                String str = j50.d;
                D81 l = keyboardAccessoryTabLayoutView.l();
                l.c(drawable.mutate());
                l.f7870a.setColorFilter(keyboardAccessoryTabLayoutView.getResources().getColor(R.color.f11220_resource_name_obfuscated_RES_2131099812), PorterDuff.Mode.SRC_IN);
                l.c = str;
                l.e();
                keyboardAccessoryTabLayoutView.c(l, i, false);
            }
        }
    }
}
