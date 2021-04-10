package defpackage;

import android.view.Choreographer;

/* renamed from: tp  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class Choreographer$FrameCallbackC4976tp implements Choreographer.FrameCallback {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Runnable f11371a;

    public Choreographer$FrameCallbackC4976tp(C5146up upVar, Runnable runnable) {
        this.f11371a = runnable;
    }

    public void doFrame(long j) {
        this.f11371a.run();
    }
}
