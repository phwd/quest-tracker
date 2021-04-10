package defpackage;

import J.N;
import android.view.Surface;
import android.view.SurfaceHolder;
import org.chromium.base.ThreadUtils;
import org.chromium.content.browser.androidoverlay.DialogOverlayImpl;

/* renamed from: TE  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TE implements SurfaceHolder.Callback2 {
    public final /* synthetic */ UE F;

    public TE(UE ue, SE se) {
        this.F = ue;
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        DialogOverlayImpl dialogOverlayImpl;
        UE ue = this.F;
        if (ue.b != null && (dialogOverlayImpl = ue.f9014a) != null) {
            Surface surface = surfaceHolder.getSurface();
            Object obj = ThreadUtils.f10596a;
            if (dialogOverlayImpl.H != null && dialogOverlayImpl.F != null) {
                int MpcpmTlm = N.MpcpmTlm(surface);
                dialogOverlayImpl.f10923J = MpcpmTlm;
                ((C5365w5) dialogOverlayImpl.F).h0((long) MpcpmTlm);
            }
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        DialogOverlayImpl dialogOverlayImpl;
        UE ue = this.F;
        if (ue.b != null && (dialogOverlayImpl = ue.f9014a) != null) {
            Object obj = ThreadUtils.f10596a;
            if (dialogOverlayImpl.H != null) {
                dialogOverlayImpl.g0();
                dialogOverlayImpl.f0();
            }
            this.F.f9014a = null;
        }
    }

    public void surfaceRedrawNeeded(SurfaceHolder surfaceHolder) {
    }
}
