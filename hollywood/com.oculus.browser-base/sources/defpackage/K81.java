package defpackage;

import androidx.viewpager2.widget.ViewPager2;

/* renamed from: K81  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class K81 implements AbstractC5716y81 {
    public final ViewPager2 F;
    public final boolean G;

    public K81(ViewPager2 viewPager2, boolean z) {
        this.F = viewPager2;
        this.G = z;
    }

    @Override // defpackage.AbstractC5546x81
    public void a(D81 d81) {
    }

    @Override // defpackage.AbstractC5546x81
    public void c(D81 d81) {
    }

    @Override // defpackage.AbstractC5546x81
    public void f(D81 d81) {
        ViewPager2 viewPager2 = this.F;
        int i = d81.d;
        boolean z = this.G;
        if (!viewPager2.T.f9428a.m) {
            viewPager2.c(i, z);
            return;
        }
        throw new IllegalStateException("Cannot change current item when ViewPager2 is fake dragging");
    }
}
