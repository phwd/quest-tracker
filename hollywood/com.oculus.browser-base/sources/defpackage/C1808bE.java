package defpackage;

import android.os.Handler;
import android.os.MessageQueue;

/* renamed from: bE  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class C1808bE implements MessageQueue.IdleHandler {

    /* renamed from: a  reason: collision with root package name */
    public final C2150dE f9522a;

    public C1808bE(C2150dE dEVar) {
        this.f9522a = dEVar;
    }

    public boolean queueIdle() {
        C2150dE dEVar = this.f9522a;
        Runnable runnable = (Runnable) dEVar.b.poll();
        if (runnable != null) {
            runnable.run();
        }
        if (dEVar.b.isEmpty()) {
            if (C2150dE.f9760a == dEVar) {
                C2150dE.f9760a = null;
            }
            return false;
        }
        new Handler().post(new RunnableC1979cE());
        return true;
    }
}
