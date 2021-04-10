package defpackage;

import org.chromium.ui.base.LocalizationUtils;

/* renamed from: WP0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class WP0 extends AbstractC4169p31 {
    @Override // defpackage.AbstractC4169p31
    public float a(C3998o31[] o31Arr, float f, float f2, float f3, float f4, float f5) {
        float f6;
        if (LocalizationUtils.isLayoutRtl()) {
            float f7 = f4 - f3;
            int length = o31Arr.length;
            if (length >= 1) {
                f7 = Math.min(o31Arr[length - 1].u, f7);
            }
            return ((f / 2.0f) + f7) - f5;
        }
        int length2 = o31Arr.length;
        for (int i = length2 > 0 ? length2 >= 2 ? length2 - 2 : length2 - 1 : 0; i < length2; i++) {
            C3998o31 o31 = o31Arr[i];
            if (o31.l) {
                f6 = o31.h() * o31.w;
            } else {
                f6 = o31.w;
            }
            f2 = Math.max(o31.u + f6, f2);
        }
        return f2 - (f / 2.0f);
    }

    @Override // defpackage.AbstractC4169p31
    public void b(int i, C3998o31[] o31Arr, float f) {
        for (C3998o31 o31 : o31Arr) {
            float f2 = o31.u;
            o31.l(o31.w + f2 >= 0.0f && f2 <= f);
        }
    }

    @Override // defpackage.AbstractC4169p31
    public void c(int i, C3998o31[] o31Arr, float f, int i2, float f2, float f3, float f4, float f5, boolean z) {
        for (C3998o31 o31 : o31Arr) {
            o31.j(o31.r);
            o31.k(o31.t);
            o31.p = 1.0f;
            o31.e(true);
            o31.o = AbstractC4089od0.b(0.0f, 0.0f, o31.w);
        }
    }
}
