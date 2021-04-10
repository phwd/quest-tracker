package defpackage;

import android.view.View;
import com.google.android.material.appbar.AppBarLayout;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: D7  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class D7 implements AbstractC2620g0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AppBarLayout f7866a;
    public final /* synthetic */ boolean b;

    public D7(AppBarLayout.Behavior behavior, AppBarLayout appBarLayout, boolean z) {
        this.f7866a = appBarLayout;
        this.b = z;
    }

    @Override // defpackage.AbstractC2620g0
    public boolean a(View view, Y y) {
        AppBarLayout appBarLayout = this.f7866a;
        boolean z = this.b;
        Objects.requireNonNull(appBarLayout);
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        appBarLayout.h(z, appBarLayout.isLaidOut(), true);
        return true;
    }
}
