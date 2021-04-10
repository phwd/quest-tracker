package defpackage;

import android.hardware.display.VirtualDisplay;
import android.media.projection.MediaProjection;
import org.chromium.media.ScreenCapture;

/* renamed from: ZO0  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ZO0 extends MediaProjection.Callback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ ScreenCapture f9343a;

    public ZO0(ScreenCapture screenCapture, XO0 xo0) {
        this.f9343a = screenCapture;
    }

    public void onStop() {
        ScreenCapture screenCapture = this.f9343a;
        int i = ScreenCapture.F;
        screenCapture.a(4);
        ScreenCapture screenCapture2 = this.f9343a;
        screenCapture2.f10986J = null;
        VirtualDisplay virtualDisplay = screenCapture2.L;
        if (virtualDisplay != null) {
            virtualDisplay.release();
            this.f9343a.L = null;
        }
    }
}
