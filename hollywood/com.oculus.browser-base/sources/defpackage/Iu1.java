package defpackage;

import androidx.viewpager2.widget.ViewPager2;

/* renamed from: Iu1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Iu1 extends Ou1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ViewPager2 f8257a;

    public Iu1(ViewPager2 viewPager2) {
        this.f8257a = viewPager2;
    }

    @Override // defpackage.Ou1
    public void a(int i) {
        if (i == 0) {
            this.f8257a.d();
        }
    }

    @Override // defpackage.Ou1
    public void c(int i) {
        ViewPager2 viewPager2 = this.f8257a;
        if (viewPager2.f9488J != i) {
            viewPager2.f9488J = i;
            viewPager2.a0.c();
        }
    }
}
