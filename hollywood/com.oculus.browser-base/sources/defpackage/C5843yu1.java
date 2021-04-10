package defpackage;

import android.graphics.Rect;
import android.os.Build;
import android.view.View;
import androidx.viewpager.widget.ViewPager;

/* renamed from: yu1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5843yu1 implements AbstractC0290Es0 {

    /* renamed from: a  reason: collision with root package name */
    public final Rect f11708a = new Rect();
    public final /* synthetic */ ViewPager b;

    public C5843yu1(ViewPager viewPager) {
        this.b = viewPager;
    }

    @Override // defpackage.AbstractC0290Es0
    public C3985nz1 a(View view, C3985nz1 nz1) {
        AbstractC2789gz1 gz1;
        C3985nz1 i = AbstractC1920bu1.i(view, nz1);
        if (i.f()) {
            return i;
        }
        Rect rect = this.f11708a;
        rect.left = i.b();
        rect.top = i.d();
        rect.right = i.c();
        rect.bottom = i.a();
        int childCount = this.b.getChildCount();
        for (int i2 = 0; i2 < childCount; i2++) {
            C3985nz1 b2 = AbstractC1920bu1.b(this.b.getChildAt(i2), i);
            rect.left = Math.min(b2.b(), rect.left);
            rect.top = Math.min(b2.d(), rect.top);
            rect.right = Math.min(b2.c(), rect.right);
            rect.bottom = Math.min(b2.a(), rect.bottom);
        }
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 30) {
            gz1 = new C2618fz1(i);
        } else if (i3 >= 29) {
            gz1 = new C2447ez1(i);
        } else {
            gz1 = new C2276dz1(i);
        }
        gz1.c(X10.a(rect.left, rect.top, rect.right, rect.bottom));
        return gz1.a();
    }
}
