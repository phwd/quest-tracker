package defpackage;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.view.View;
import java.util.Objects;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: BU0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class BU0 implements View.OnClickListener {
    public final CU0 F;
    public final C5090uU0 G;
    public final C2189dU0 H;
    public final WindowAndroid I;

    /* renamed from: J  reason: collision with root package name */
    public final AbstractC2018cU0 f7740J;
    public final boolean K;
    public final ResolveInfo L;
    public final int M;
    public final long N;

    public BU0(CU0 cu0, C5090uU0 uu0, C2189dU0 du0, WindowAndroid windowAndroid, AbstractC2018cU0 cu02, boolean z, ResolveInfo resolveInfo, int i, long j) {
        this.F = cu0;
        this.G = uu0;
        this.H = du0;
        this.I = windowAndroid;
        this.f7740J = cu02;
        this.K = z;
        this.L = resolveInfo;
        this.M = i;
        this.N = j;
    }

    public void onClick(View view) {
        CU0 cu0 = this.F;
        C5090uU0 uu0 = this.G;
        C2189dU0 du0 = this.H;
        AbstractC2018cU0 cu02 = this.f7740J;
        boolean z = this.K;
        ResolveInfo resolveInfo = this.L;
        int i = this.M;
        long j = this.N;
        Objects.requireNonNull(cu0);
        ActivityInfo activityInfo = resolveInfo.activityInfo;
        AbstractC3535lK0.a("SharingHubAndroid.ThirdPartyAppSelected");
        AbstractC3364kK0.g("Sharing.SharingHubAndroid.ThirdPartyAppUsage", i, 8);
        C1569Zs.b(j);
        ComponentName componentName = new ComponentName(activityInfo.applicationInfo.packageName, activityInfo.name);
        if (cu02 != null) {
            cu02.b(componentName);
        }
        if (z) {
            PU0 pu0 = NU0.f8549a;
            pu0.p("last_shared_package_name", componentName.getPackageName());
            pu0.p("last_shared_class_name", componentName.getClassName());
        }
        ((C5638xj) cu0.c).p(uu0, true, 0);
        Intent c = IT0.c(du0);
        c.addFlags(50331648);
        c.setComponent(componentName);
        IT0.a(du0.f9785a, c, null);
    }
}
