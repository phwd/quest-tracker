package defpackage;

import J.N;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.TextureView;
import org.chromium.components.thinwebview.internal.CompositorViewImpl;

/* renamed from: Yw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class TextureView$SurfaceTextureListenerC1516Yw implements TextureView.SurfaceTextureListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ CompositorViewImpl f9306a;

    public TextureView$SurfaceTextureListenerC1516Yw(CompositorViewImpl compositorViewImpl) {
        this.f9306a = compositorViewImpl;
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
        CompositorViewImpl compositorViewImpl = this.f9306a;
        long j = compositorViewImpl.d;
        if (j != 0) {
            N.MYFXTzso(j, compositorViewImpl);
            CompositorViewImpl compositorViewImpl2 = this.f9306a;
            N.M5WiS8XV(compositorViewImpl2.d, compositorViewImpl2, -1, i, i2, false, new Surface(surfaceTexture));
        }
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
        CompositorViewImpl compositorViewImpl = this.f9306a;
        long j = compositorViewImpl.d;
        if (j == 0) {
            return false;
        }
        N.M3gcibnY(j, compositorViewImpl);
        return false;
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
        CompositorViewImpl compositorViewImpl = this.f9306a;
        long j = compositorViewImpl.d;
        if (j != 0) {
            N.M5WiS8XV(j, compositorViewImpl, -1, i, i2, false, new Surface(surfaceTexture));
        }
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
    }
}
