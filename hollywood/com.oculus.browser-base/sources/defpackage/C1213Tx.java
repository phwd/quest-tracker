package defpackage;

import java.util.Objects;

/* renamed from: Tx  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1213Tx extends AbstractC0823Nl {

    /* renamed from: a  reason: collision with root package name */
    public final C1518Yx f8997a;

    public C1213Tx(C1518Yx yx) {
        this.f8997a = yx;
    }

    @Override // org.chromium.base.Callback
    public void onResult(Object obj) {
        C1518Yx yx = this.f8997a;
        Integer num = (Integer) obj;
        Objects.requireNonNull(yx);
        AbstractC1220Ua0.d("OfflineIndicator", "sendHttpProbe returned with result=" + num + " and mConnectivityCheckingStage=" + yx.L, new Object[0]);
        int i = 2;
        if (yx.L == 2) {
            StringBuilder i2 = AbstractC2531fV.i("ConnectivityDetector.DefaultHttpProbeResult.");
            i2.append(yx.H);
            AbstractC3364kK0.g(i2.toString(), num.intValue(), 6);
        } else {
            StringBuilder i3 = AbstractC2531fV.i("ConnectivityDetector.FallbackHttpProbeResult.");
            i3.append(yx.H);
            AbstractC3364kK0.g(i3.toString(), num.intValue(), 6);
        }
        if (yx.I != 6) {
            int intValue = num.intValue();
            int i4 = yx.f9307J;
            AbstractC1220Ua0.d("OfflineIndicator", AbstractC2531fV.w("updateConnectionStatePerProbeResult result=", intValue), new Object[0]);
            if (intValue != 0) {
                i = intValue != 2 ? (intValue == 3 || intValue == 4 || intValue == 5) ? 4 : i4 : 3;
            }
            yx.f(i);
            AbstractC3364kK0.g("ConnectivityDetector.ConnectionState." + yx.H, yx.f9307J, 5);
            yx.d();
        }
    }
}
