package defpackage;

import android.view.View;
import com.google.android.material.appbar.AppBarLayout;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: z7  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5881z7 implements AbstractC0290Es0 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ AppBarLayout f11722a;

    public C5881z7(AppBarLayout appBarLayout) {
        this.f11722a = appBarLayout;
    }

    @Override // defpackage.AbstractC0290Es0
    public C3985nz1 a(View view, C3985nz1 nz1) {
        AppBarLayout appBarLayout = this.f11722a;
        Objects.requireNonNull(appBarLayout);
        AtomicInteger atomicInteger = AbstractC1920bu1.f9571a;
        C3985nz1 nz12 = appBarLayout.getFitsSystemWindows() ? nz1 : null;
        if (!Objects.equals(appBarLayout.L, nz12)) {
            appBarLayout.L = nz12;
            appBarLayout.l();
            appBarLayout.requestLayout();
        }
        return nz1;
    }
}
