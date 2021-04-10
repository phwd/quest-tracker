package defpackage;

import android.view.Choreographer;

/* renamed from: sp  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Choreographer$FrameCallbackC4806sp implements Choreographer.FrameCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Runnable f11302a;

    public Choreographer$FrameCallbackC4806sp(C5146up upVar, Runnable runnable) {
        this.f11302a = runnable;
    }

    public void doFrame(long j) {
        this.f11302a.run();
    }
}
