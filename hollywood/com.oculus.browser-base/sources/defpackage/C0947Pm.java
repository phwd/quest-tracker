package defpackage;

import org.chromium.ui.base.LocalizationUtils;

/* renamed from: Pm  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0947Pm extends AbstractC4169p31 {
    @Override // defpackage.AbstractC4169p31
    public float a(C3998o31[] o31Arr, float f, float f2, float f3, float f4, float f5) {
        int i = 0;
        if (LocalizationUtils.isLayoutRtl()) {
            float f6 = f4 - f3;
            while (i < o31Arr.length) {
                f6 = Math.min(o31Arr[i].u, f6);
                i++;
            }
            return ((f / 2.0f) + Math.max(f6, f2)) - f5;
        }
        while (i < o31Arr.length) {
            C3998o31 o31 = o31Arr[i];
            float h = o31.h();
            f2 = Math.max(o31.u + (h * (o31.w - f)), f2);
            i++;
        }
        return Math.min(f2 + f, f4 - f3) - (f / 2.0f);
    }

    @Override // defpackage.AbstractC4169p31
    public void b(int i, C3998o31[] o31Arr, float f) {
        for (int i2 = 1; i2 < o31Arr.length; i2++) {
            int i3 = i2 - 1;
            C3998o31 o31 = o31Arr[i3];
            C3998o31 o312 = o31Arr[i2];
            if (((int) o31.v) == ((int) o312.v) && ((int) o31.u) == ((int) o312.u)) {
                if (i2 <= i) {
                    o31.l(false);
                } else if (i2 > i) {
                    o312.l(false);
                }
            } else if (((int) o31.u) != ((int) o312.u)) {
                if (i2 <= i) {
                    o31.l(true);
                } else if (i2 > i) {
                    o312.l(true);
                }
            }
            if (i2 == i) {
                o312.l(true);
            }
            if (i3 == i) {
                o31.l(true);
            }
        }
    }

    @Override // defpackage.AbstractC4169p31
    public void c(int i, C3998o31[] o31Arr, float f, int i2, float f2, float f3, float f4, float f5, boolean z) {
        int i3;
        int i4;
        float f6;
        C3998o31 o31 = i >= 0 ? o31Arr[i] : null;
        float f7 = ((o31 != null ? o31.w : 0.0f) - f) - f2;
        int i5 = 0;
        while (i5 < o31Arr.length) {
            C3998o31 o312 = o31Arr[i5];
            float f8 = o312.r;
            if (i5 < i) {
                i3 = Math.min(i5, i2);
            } else {
                i3 = Math.min(i2, i) + Math.min(i2, i5 - i);
            }
            if (i5 >= i) {
                i4 = Math.min((o31Arr.length - 1) - i5, i2);
            } else {
                i4 = Math.min((o31Arr.length - 1) - i, i2) + Math.min(i - i5, i2);
            }
            if (LocalizationUtils.isLayoutRtl()) {
                i4 = i3;
                i3 = i4;
            }
            float f9 = (((float) i3) * f) + f3;
            float f10 = (f5 - (((float) i4) * f)) - f4;
            float b = AbstractC4089od0.b(o312.s + f8, f9, f10 - o312.w);
            o312.j(b);
            o312.k(o312.t);
            float f11 = 1.0f;
            if (i5 != i) {
                float max = Math.max(o312.w, 1.0f);
                boolean z2 = !LocalizationUtils.isLayoutRtl() ? i5 < i : i5 > i;
                if (!z2) {
                    f9 += f7;
                }
                if (z2) {
                    f10 -= f7;
                }
                f11 = AbstractC4089od0.b((Math.min(f8 + max, f10) - Math.max(f8, f9)) / max, 0.0f, 1.0f);
            }
            o312.p = f11;
            o312.e(true);
            if (i5 <= (z ? i + 1 : i) || i5 <= 0) {
                f6 = 0.0f;
            } else {
                C3998o31 o313 = o31Arr[i5 - 1];
                float h = o313.h() * (o313.w - f2);
                float f12 = o313.u;
                if (!LocalizationUtils.isLayoutRtl()) {
                    f12 += h;
                }
                f6 = Math.max(f12 - b, 0.0f);
                if (LocalizationUtils.isLayoutRtl()) {
                    f6 = h - f6;
                }
            }
            o312.o = AbstractC4089od0.b(f6, 0.0f, o312.w);
            i5++;
        }
    }
}
