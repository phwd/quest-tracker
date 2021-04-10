package defpackage;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

/* renamed from: Oe1  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public final class ExecutorC0867Oe1 implements Executor {

    /* renamed from: a  reason: collision with root package name */
    public final Handler f8638a = new Handler(Looper.getMainLooper());

    public final void execute(Runnable runnable) {
        this.f8638a.post(runnable);
    }
}
