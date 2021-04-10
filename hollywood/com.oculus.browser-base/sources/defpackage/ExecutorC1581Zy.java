package defpackage;

import android.os.Handler;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;

/* renamed from: Zy  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ExecutorC1581Zy implements Executor {

    /* renamed from: a  reason: collision with root package name */
    public final Handler f9390a;

    public ExecutorC1581Zy(Handler handler) {
        this.f9390a = handler;
    }

    public void execute(Runnable runnable) {
        if (!this.f9390a.post(runnable)) {
            throw new RejectedExecutionException(this.f9390a + " is shutting down");
        }
    }
}
