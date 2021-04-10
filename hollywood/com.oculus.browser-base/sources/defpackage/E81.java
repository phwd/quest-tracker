package defpackage;

import com.google.android.material.tabs.TabLayout;
import java.lang.ref.WeakReference;

/* renamed from: E81  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class E81 implements Du1 {

    /* renamed from: a  reason: collision with root package name */
    public final WeakReference f7938a;
    public int b;
    public int c;

    public E81(TabLayout tabLayout) {
        this.f7938a = new WeakReference(tabLayout);
    }

    @Override // defpackage.Du1
    public void a(int i, float f, int i2) {
        TabLayout tabLayout = (TabLayout) this.f7938a.get();
        if (tabLayout != null) {
            int i3 = this.c;
            boolean z = false;
            boolean z2 = i3 != 2 || this.b == 1;
            if (!(i3 == 2 && this.b == 0)) {
                z = true;
            }
            tabLayout.q(i, f, z2, z);
        }
    }

    @Override // defpackage.Du1
    public void b(int i) {
        this.b = this.c;
        this.c = i;
    }

    @Override // defpackage.Du1
    public void c(int i) {
        TabLayout tabLayout = (TabLayout) this.f7938a.get();
        if (tabLayout != null && tabLayout.h() != i && i < tabLayout.j()) {
            int i2 = this.c;
            tabLayout.o(tabLayout.i(i), i2 == 0 || (i2 == 2 && this.b == 0));
        }
    }
}
