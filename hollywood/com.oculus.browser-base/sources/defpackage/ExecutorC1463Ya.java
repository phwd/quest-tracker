package defpackage;

import java.util.concurrent.Executor;
import org.chromium.base.task.PostTask;

/* renamed from: Ya  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class ExecutorC1463Ya implements Executor {
    public void execute(Runnable runnable) {
        PostTask.b(C3070if1.b, runnable, 0);
    }
}
