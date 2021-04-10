package defpackage;

import java.util.concurrent.Executor;

/* renamed from: nM  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC3880nM {

    /* renamed from: a  reason: collision with root package name */
    public static final ThreadLocal f10485a = new ThreadLocal();

    public static Executor a(SA sa) {
        ThreadLocal threadLocal = f10485a;
        Executor executor = (Executor) threadLocal.get();
        if (executor != null) {
            return executor;
        }
        ExecutorC3709mM mMVar = new ExecutorC3709mM(sa);
        threadLocal.set(mMVar);
        return mMVar;
    }
}
