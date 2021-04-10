package defpackage;

import android.os.SystemClock;
import java.util.Objects;

/* renamed from: sr0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4813sr0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C6003zr0 f11305a;

    public C4813sr0(C6003zr0 zr0) {
        this.f11305a = zr0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C6003zr0 zr0 = this.f11305a;
        Objects.requireNonNull(zr0);
        boolean booleanValue = ((Boolean) obj).booleanValue();
        if (zr0.n != booleanValue) {
            zr0.c.removeCallbacks(zr0.l);
            long elapsedRealtime = SystemClock.elapsedRealtime() - zr0.m;
            if (elapsedRealtime < 5000) {
                zr0.c.postDelayed(zr0.l, 5000 - elapsedRealtime);
            } else {
                zr0.a(booleanValue);
            }
        }
    }
}
