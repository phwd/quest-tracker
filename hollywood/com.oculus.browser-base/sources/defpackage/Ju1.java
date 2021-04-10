package defpackage;

import androidx.viewpager2.widget.ViewPager2;

/* renamed from: Ju1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Ju1 extends Ou1 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ViewPager2 f8325a;

    public Ju1(ViewPager2 viewPager2) {
        this.f8325a = viewPager2;
    }

    @Override // defpackage.Ou1
    public void c(int i) {
        this.f8325a.clearFocus();
        if (this.f8325a.hasFocus()) {
            this.f8325a.P.requestFocus(2);
        }
    }
}
