package defpackage;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: hu  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class ThreadFactoryC2943hu implements ThreadFactory {

    /* renamed from: a  reason: collision with root package name */
    public final AtomicInteger f10107a = new AtomicInteger(1);

    public Thread newThread(Runnable runnable) {
        RunnableC2772gu guVar = new RunnableC2772gu(runnable);
        StringBuilder i = AbstractC2531fV.i("CrAsyncTask #");
        i.append(this.f10107a.getAndIncrement());
        return new Thread(guVar, i.toString());
    }
}
