package defpackage;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: wS0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class RunnableC5426wS0 implements Runnable {
    public final /* synthetic */ AtomicReference F;
    public final /* synthetic */ Callable G;
    public final /* synthetic */ ReentrantLock H;
    public final /* synthetic */ AtomicBoolean I;

    /* renamed from: J  reason: collision with root package name */
    public final /* synthetic */ Condition f11546J;

    public RunnableC5426wS0(C5766yS0 ys0, AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition) {
        this.F = atomicReference;
        this.G = callable;
        this.H = reentrantLock;
        this.I = atomicBoolean;
        this.f11546J = condition;
    }

    public void run() {
        try {
            this.F.set(this.G.call());
        } catch (Exception unused) {
        }
        this.H.lock();
        try {
            this.I.set(false);
            this.f11546J.signal();
        } finally {
            this.H.unlock();
        }
    }
}
