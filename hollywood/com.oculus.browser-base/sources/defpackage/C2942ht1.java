package defpackage;

import J.N;
import android.hardware.Camera;

/* renamed from: ht1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2942ht1 implements Camera.ErrorCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ C5500wt1 f10106a;

    public C2942ht1(C5500wt1 wt1, C2600ft1 ft1) {
        this.f10106a = wt1;
    }

    public void onError(int i, Camera camera) {
        C5500wt1 wt1 = this.f10106a;
        N.MhmwjISE(wt1.e, wt1, 68, AbstractC2531fV.w("Error id: ", i));
        synchronized (this.f10106a.h) {
            C5500wt1 wt12 = this.f10106a;
            long j = wt12.i;
            if (j != 0) {
                wt12.d(j);
                this.f10106a.i = 0;
            }
        }
    }
}
