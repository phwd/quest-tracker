package defpackage;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.ViewGroup;

/* renamed from: Kw  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C0663Kw {

    /* renamed from: a  reason: collision with root package name */
    public SurfaceView f8395a;
    public boolean b;
    public boolean c;
    public int d;
    public int e;
    public int f;
    public ViewGroup g;

    public C0663Kw(Context context, int i, SurfaceHolder.Callback2 callback2) {
        SurfaceView surfaceView = new SurfaceView(context);
        this.f8395a = surfaceView;
        if (i == -3) {
            surfaceView.setZOrderMediaOverlay(true);
        }
        this.f8395a.setVisibility(4);
        b().setFormat(i);
        b().addCallback(callback2);
        this.d = 0;
    }

    public boolean a() {
        return this.g != null;
    }

    public SurfaceHolder b() {
        return this.f8395a.getHolder();
    }
}
