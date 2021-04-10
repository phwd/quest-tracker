package defpackage;

import java.util.concurrent.Executor;
import org.chromium.base.task.PostTask;

/* renamed from: Xn1  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final /* synthetic */ class Xn1 implements Executor {
    public void execute(Runnable runnable) {
        PostTask.b(Zo1.c, runnable, 0);
    }
}
