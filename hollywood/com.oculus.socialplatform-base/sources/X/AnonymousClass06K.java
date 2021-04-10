package X;

import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* renamed from: X.06K  reason: invalid class name */
public class AnonymousClass06K implements Runnable {
    public static final String __redex_internal_original_name = "androidx.core.provider.SelfDestructiveThread$3";
    public final /* synthetic */ AnonymousClass06M A00;
    public final /* synthetic */ Callable A01;
    public final /* synthetic */ AtomicBoolean A02;
    public final /* synthetic */ AtomicReference A03;
    public final /* synthetic */ Condition A04;
    public final /* synthetic */ ReentrantLock A05;

    public AnonymousClass06K(AnonymousClass06M r1, AtomicReference atomicReference, Callable callable, ReentrantLock reentrantLock, AtomicBoolean atomicBoolean, Condition condition) {
        this.A00 = r1;
        this.A03 = atomicReference;
        this.A01 = callable;
        this.A05 = reentrantLock;
        this.A02 = atomicBoolean;
        this.A04 = condition;
    }

    public final void run() {
        try {
            this.A03.set(this.A01.call());
        } catch (Exception unused) {
        }
        ReentrantLock reentrantLock = this.A05;
        reentrantLock.lock();
        try {
            this.A02.set(false);
            this.A04.signal();
        } finally {
            reentrantLock.unlock();
        }
    }
}
