package defpackage;

import android.os.Process;
import java.lang.Thread;

/* renamed from: m60  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3664m60 implements Thread.UncaughtExceptionHandler {

    /* renamed from: a  reason: collision with root package name */
    public boolean f10397a;

    public void uncaughtException(Thread thread, Throwable th) {
        if (!this.f10397a) {
            this.f10397a = true;
            Process.killProcess(Process.myPid());
            System.exit(10);
        }
    }
}
