package defpackage;

import android.os.SystemClock;

/* renamed from: VX0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class VX0 extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final WX0 f9089a;

    public VX0(WX0 wx0) {
        this.f9089a = wx0;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        WX0 wx0 = this.f9089a;
        C4731sL sLVar = (C4731sL) obj;
        if (wx0.L == null) {
            wx0.L = Boolean.valueOf(sLVar != null && sLVar.f11267a);
            C3946nm1 nm1 = wx0.I;
            if (nm1 != null) {
                AbstractC3364kK0.k(nm1.f10512a.F0 ? "MobileFre.CctTos.IsDeviceOwnedCheckSpeed2.SlowerThanInflation" : "MobileFre.CctTos.IsDeviceOwnedCheckSpeed2.FasterThanInflation", SystemClock.elapsedRealtime() - wx0.H);
            }
            wx0.f();
        }
    }
}
