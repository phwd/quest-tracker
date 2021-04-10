package defpackage;

import J.N;
import android.graphics.SurfaceTexture;

/* renamed from: d41  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2122d41 implements SurfaceTexture.OnFrameAvailableListener {

    /* renamed from: a  reason: collision with root package name */
    public final long f9746a;

    public C2122d41(long j) {
        this.f9746a = j;
    }

    @Override // java.lang.Object
    public void finalize() {
        try {
            N.MUH2vhAN(this.f9746a, this);
        } finally {
            super.finalize();
        }
    }

    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        N.MRbPfCZQ(this.f9746a, this);
    }
}
