package defpackage;

import android.view.Choreographer;

/* renamed from: up  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class C5146up implements AbstractC5436wX0 {

    /* renamed from: a  reason: collision with root package name */
    public final Choreographer f11437a;

    public C5146up(Choreographer choreographer) {
        this.f11437a = choreographer;
    }

    @Override // defpackage.AbstractC2387ef1
    public void a(Runnable runnable, long j) {
        this.f11437a.postFrameCallbackDelayed(new Choreographer$FrameCallbackC4976tp(this, runnable), j);
    }

    @Override // defpackage.AbstractC2387ef1
    public void b(Runnable runnable) {
        this.f11437a.postFrameCallback(new Choreographer$FrameCallbackC4806sp(this, runnable));
    }

    @Override // defpackage.AbstractC5436wX0
    public boolean c() {
        try {
            return this.f11437a == Choreographer.getInstance();
        } catch (IllegalStateException unused) {
            return false;
        }
    }
}
