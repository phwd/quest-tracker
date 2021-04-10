package defpackage;

import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import com.google.android.material.appbar.AppBarLayout;

/* renamed from: C7  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C7 implements AbstractC2620g0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CoordinatorLayout f7786a;
    public final /* synthetic */ AppBarLayout b;
    public final /* synthetic */ View c;
    public final /* synthetic */ int d;
    public final /* synthetic */ AppBarLayout.Behavior e;

    public C7(AppBarLayout.Behavior behavior, CoordinatorLayout coordinatorLayout, AppBarLayout appBarLayout, View view, int i) {
        this.e = behavior;
        this.f7786a = coordinatorLayout;
        this.b = appBarLayout;
        this.c = view;
        this.d = i;
    }

    @Override // defpackage.AbstractC2620g0
    public boolean a(View view, Y y) {
        this.e.i(this.f7786a, this.b, this.c, 0, this.d, new int[]{0, 0}, 1);
        return true;
    }
}
