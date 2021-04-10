package defpackage;

import android.app.Activity;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.Callback;
import org.chromium.ui.base.WindowAndroid;

/* renamed from: n11  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3821n11 implements Z9 {
    public final long F;
    public final WindowAndroid G;
    public final C1551Zj H;
    public final Q31 I;

    /* renamed from: J  reason: collision with root package name */
    public final Callback f10469J;

    public C3821n11(WindowAndroid windowAndroid, long j, C1551Zj zj, Q31 q31, Callback callback) {
        this.G = windowAndroid;
        this.F = j;
        this.H = zj;
        this.I = q31;
        this.f10469J = callback;
        ApplicationStatus.g(this, (Activity) windowAndroid.s0().get());
    }

    public B90 a() {
        return (B90) this.I.get();
    }

    @Override // defpackage.Z9
    public void t(Activity activity, int i) {
        if (i == 6) {
            AbstractC3992o11.b.remove(this.G);
            ApplicationStatus.h(this);
        }
    }
}
