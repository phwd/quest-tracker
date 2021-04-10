package defpackage;

import android.os.Handler;

/* renamed from: TS  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TS implements AbstractC3841n80 {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Handler f8957a;
    public final /* synthetic */ Runnable b;

    public TS(AbstractC1664aT aTVar, Handler handler, Runnable runnable) {
        this.f8957a = handler;
        this.b = runnable;
    }

    @Override // defpackage.AbstractC3841n80
    public void a(AbstractC4524r80 r80, EnumC3157j80 j80) {
        if (j80 == EnumC3157j80.ON_DESTROY) {
            this.f8957a.removeCallbacks(this.b);
            r80.Q().b(this);
        }
    }
}
