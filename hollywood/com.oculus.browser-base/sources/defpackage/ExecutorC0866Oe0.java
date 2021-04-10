package defpackage;

import android.os.Handler;
import java.util.concurrent.Executor;

/* renamed from: Oe0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class ExecutorC0866Oe0 implements Executor {

    /* renamed from: a  reason: collision with root package name */
    public final Handler f8637a;

    public ExecutorC0866Oe0(Handler handler) {
        this.f8637a = handler;
    }

    public void execute(Runnable runnable) {
        this.f8637a.post(runnable);
    }
}
