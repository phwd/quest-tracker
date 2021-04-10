package defpackage;

import java.util.concurrent.Executor;

/* renamed from: io  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class ExecutorC3095io implements Executor {

    /* renamed from: a  reason: collision with root package name */
    public final C5653xo f10162a;

    public ExecutorC3095io(C5653xo xoVar) {
        this.f10162a = xoVar;
    }

    public void execute(Runnable runnable) {
        this.f10162a.e.post(runnable);
    }
}
