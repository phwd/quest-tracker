package defpackage;

import android.os.SystemClock;
import java.util.Objects;

/* renamed from: qm1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C4459qm1 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C4629rm1 f11163a;

    public C4459qm1(C4629rm1 rm1) {
        this.f11163a = rm1;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C4629rm1 rm1 = this.f11163a;
        boolean booleanValue = ((Boolean) obj).booleanValue();
        Objects.requireNonNull(rm1);
        SystemClock.elapsedRealtime();
        if (!booleanValue) {
            NU0.f8549a.m("Chrome.FirstRun.SkippedByPolicy", false);
        }
        RQ rq = rm1.b;
        AbstractC2032cb cbVar = rq.g;
        if (cbVar != null) {
            cbVar.b(true);
        }
        rq.e.clear();
        rq.f.clear();
        rm1.f11220a.a();
    }
}
