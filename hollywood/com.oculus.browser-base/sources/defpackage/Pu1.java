package defpackage;

import android.view.View;
import androidx.viewpager2.widget.ViewPager2;

/* renamed from: Pu1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Pu1 implements AbstractC2620g0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Su1 f8719a;

    public Pu1(Su1 su1) {
        this.f8719a = su1;
    }

    @Override // defpackage.AbstractC2620g0
    public boolean a(View view, Y y) {
        this.f8719a.d(((ViewPager2) view).f9488J + 1);
        return true;
    }
}
