package defpackage;

import java.lang.Thread;
import org.chromium.base.JavaHandlerThread;

/* renamed from: A40  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class A40 implements Thread.UncaughtExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ JavaHandlerThread f7653a;

    public A40(JavaHandlerThread javaHandlerThread) {
        this.f7653a = javaHandlerThread;
    }

    public void uncaughtException(Thread thread, Throwable th) {
        this.f7653a.b = th;
    }
}
