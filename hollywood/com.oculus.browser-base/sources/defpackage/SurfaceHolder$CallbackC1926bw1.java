package defpackage;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.oculus.browser.VrShellImpl;
import java.util.Objects;

/* renamed from: bw1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class SurfaceHolder$CallbackC1926bw1 extends SurfaceView implements SurfaceHolder.Callback {
    public VrShellImpl F;

    public SurfaceHolder$CallbackC1926bw1(Context context, VrShellImpl vrShellImpl) {
        super(context);
        this.F = vrShellImpl;
        getHolder().addCallback(this);
    }

    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
        StringBuilder i4 = AbstractC2531fV.i("surfaceChanged ");
        i4.append(surfaceHolder.getSurface());
        i4.append(" w: ");
        i4.append(i2);
        i4.append(" h: ");
        i4.append(i3);
        AbstractC1220Ua0.d("VrApiSurfaceView", i4.toString(), new Object[0]);
        VrShellImpl vrShellImpl = this.F;
        if (vrShellImpl != null) {
            Objects.requireNonNull(vrShellImpl);
            AbstractC1220Ua0.d("VrShellImpl", "artb: VrShellImpl.onSurfaceChanged " + surfaceHolder.getSurface() + " w = " + i2 + " h = " + i3, new Object[0]);
            long j = vrShellImpl.c;
            if (j != 0) {
                vrShellImpl.nativeSetVrApiSurface(j, surfaceHolder.getSurface());
            }
        }
    }

    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        StringBuilder i = AbstractC2531fV.i("surfaceCreated ");
        i.append(surfaceHolder.getSurface());
        i.append(" w: ");
        i.append(surfaceHolder.getSurfaceFrame().width());
        i.append(" h: ");
        i.append(surfaceHolder.getSurfaceFrame().height());
        AbstractC1220Ua0.d("VrApiSurfaceView", i.toString(), new Object[0]);
        VrShellImpl vrShellImpl = this.F;
        if (vrShellImpl != null) {
            Objects.requireNonNull(vrShellImpl);
            AbstractC1220Ua0.d("VrShellImpl", "artb: VrShellImpl.onSurfaceCreated " + surfaceHolder.getSurface(), new Object[0]);
            long j = vrShellImpl.c;
            if (j != 0) {
                vrShellImpl.nativeSetVrApiSurface(j, surfaceHolder.getSurface());
            }
        }
    }

    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        StringBuilder i = AbstractC2531fV.i("surfaceDestroyed");
        i.append(surfaceHolder.getSurface());
        AbstractC1220Ua0.d("VrApiSurfaceView", i.toString(), new Object[0]);
        VrShellImpl vrShellImpl = this.F;
        if (vrShellImpl != null) {
            Objects.requireNonNull(vrShellImpl);
            AbstractC1220Ua0.d("VrShellImpl", "artb: VrShellImpl.onSurfaceDestroyed " + surfaceHolder.getSurface(), new Object[0]);
            long j = vrShellImpl.c;
            if (j != 0) {
                vrShellImpl.nativeSetVrApiSurface(j, null);
            }
        }
    }
}
