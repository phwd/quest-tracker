package org.chromium.components.thinwebview.internal;

import J.N;
import android.content.Context;
import android.view.TextureView;
import android.view.View;
import org.chromium.ui.base.WindowAndroid;

/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class CompositorViewImpl implements AbstractC0845Nw {

    /* renamed from: a  reason: collision with root package name */
    public final Context f10901a;
    public final View b;
    public final C1483Yg1 c;
    public long d;

    public CompositorViewImpl(Context context, WindowAndroid windowAndroid, C1483Yg1 yg1) {
        this.f10901a = context;
        C1483Yg1 yg12 = new C1483Yg1();
        yg12.F = yg1.F;
        yg12.G = yg1.G;
        this.c = yg12;
        TextureView textureView = new TextureView(context);
        textureView.setSurfaceTextureListener(new TextureView$SurfaceTextureListenerC1516Yw(this));
        this.b = textureView;
        this.d = N.MPS$crjv(this, windowAndroid, yg1.G);
    }

    public final long getNativePtr() {
        return this.d;
    }

    public final void onCompositorLayout() {
    }

    public final void recreateSurface() {
    }
}
