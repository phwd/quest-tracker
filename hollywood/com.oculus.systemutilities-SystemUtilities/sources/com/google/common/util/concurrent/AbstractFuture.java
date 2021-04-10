package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.base.Throwables;
import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Locale;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.concurrent.locks.LockSupport;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

public abstract class AbstractFuture<V> extends FluentFuture<V> {
    private static final AtomicHelper ATOMIC_HELPER;
    private static final boolean GENERATE_CANCELLATION_CAUSES = Boolean.parseBoolean(System.getProperty("guava.concurrent.generate_cancellation_cause", "false"));
    private static final Object NULL = new Object();
    private static final Logger log = Logger.getLogger(AbstractFuture.class.getName());
    private volatile Listener listeners;
    private volatile Object value;
    private volatile Waiter waiters;

    static {
        AtomicHelper helper;
        Throwable thrownUnsafeFailure = null;
        Throwable thrownAtomicReferenceFieldUpdaterFailure = null;
        try {
            helper = new UnsafeAtomicHelper();
        } catch (Throwable atomicReferenceFieldUpdaterFailure) {
            thrownAtomicReferenceFieldUpdaterFailure = atomicReferenceFieldUpdaterFailure;
            helper = new SynchronizedHelper();
        }
        ATOMIC_HELPER = helper;
        if (thrownAtomicReferenceFieldUpdaterFailure != null) {
            log.log(Level.SEVERE, "UnsafeAtomicHelper is broken!", thrownUnsafeFailure);
            log.log(Level.SEVERE, "SafeAtomicHelper is broken!", thrownAtomicReferenceFieldUpdaterFailure);
        }
    }

    /* access modifiers changed from: package-private */
    public static abstract class TrustedFuture<V> extends AbstractFuture<V> {
        TrustedFuture() {
        }

        @Override // java.util.concurrent.Future, com.google.common.util.concurrent.AbstractFuture
        public final V get() throws InterruptedException, ExecutionException {
            return (V) AbstractFuture.super.get();
        }

        @Override // java.util.concurrent.Future, com.google.common.util.concurrent.AbstractFuture
        public final V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
            return (V) AbstractFuture.super.get(timeout, unit);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public final boolean isDone() {
            return AbstractFuture.super.isDone();
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public final boolean isCancelled() {
            return AbstractFuture.super.isCancelled();
        }

        @Override // com.google.common.util.concurrent.ListenableFuture, com.google.common.util.concurrent.AbstractFuture
        public final void addListener(Runnable listener, Executor executor) {
            AbstractFuture.super.addListener(listener, executor);
        }

        @Override // com.google.common.util.concurrent.AbstractFuture
        public final boolean cancel(boolean mayInterruptIfRunning) {
            return AbstractFuture.super.cancel(mayInterruptIfRunning);
        }
    }

    /* access modifiers changed from: private */
    public static final class Waiter {
        static final Waiter TOMBSTONE = new Waiter(false);
        volatile Waiter next;
        volatile Thread thread;

        Waiter(boolean unused) {
        }

        Waiter() {
            AbstractFuture.ATOMIC_HELPER.putThread(this, Thread.currentThread());
        }

        /* access modifiers changed from: package-private */
        public void setNext(Waiter next2) {
            AbstractFuture.ATOMIC_HELPER.putNext(this, next2);
        }

        /* access modifiers changed from: package-private */
        public void unpark() {
            Thread w = this.thread;
            if (w != null) {
                this.thread = null;
                LockSupport.unpark(w);
            }
        }
    }

    private void removeWaiter(Waiter node) {
        node.thread = null;
        while (true) {
            Waiter pred = null;
            Waiter curr = this.waiters;
            if (curr != Waiter.TOMBSTONE) {
                while (curr != null) {
                    Waiter succ = curr.next;
                    if (curr.thread != null) {
                        pred = curr;
                    } else if (pred != null) {
                        pred.next = succ;
                        if (pred.thread == null) {
                        }
                    } else if (!ATOMIC_HELPER.casWaiters(this, curr, succ)) {
                    }
                    curr = succ;
                }
                return;
            }
            return;
        }
    }

    /* access modifiers changed from: private */
    public static final class Listener {
        static final Listener TOMBSTONE = new Listener(null, null);
        final Executor executor;
        Listener next;
        final Runnable task;

        Listener(Runnable task2, Executor executor2) {
            this.task = task2;
            this.executor = executor2;
        }
    }

    /* access modifiers changed from: private */
    public static final class Failure {
        static final Failure FALLBACK_INSTANCE = new Failure(new Throwable("Failure occurred while trying to finish a future.") {
            /* class com.google.common.util.concurrent.AbstractFuture.Failure.AnonymousClass1 */

            public synchronized Throwable fillInStackTrace() {
                return this;
            }
        });
        final Throwable exception;

        Failure(Throwable exception2) {
            this.exception = (Throwable) Preconditions.checkNotNull(exception2);
        }
    }

    /* access modifiers changed from: private */
    public static final class Cancellation {
        static final Cancellation CAUSELESS_CANCELLED;
        static final Cancellation CAUSELESS_INTERRUPTED;
        final Throwable cause;
        final boolean wasInterrupted;

        static {
            if (AbstractFuture.GENERATE_CANCELLATION_CAUSES) {
                CAUSELESS_CANCELLED = null;
                CAUSELESS_INTERRUPTED = null;
                return;
            }
            CAUSELESS_CANCELLED = new Cancellation(false, null);
            CAUSELESS_INTERRUPTED = new Cancellation(true, null);
        }

        Cancellation(boolean wasInterrupted2, Throwable cause2) {
            this.wasInterrupted = wasInterrupted2;
            this.cause = cause2;
        }
    }

    /* access modifiers changed from: private */
    public static final class SetFuture<V> implements Runnable {
        final ListenableFuture<? extends V> future;
        final AbstractFuture<V> owner;

        public void run() {
            if (((AbstractFuture) this.owner).value == this) {
                if (AbstractFuture.ATOMIC_HELPER.casValue(this.owner, this, AbstractFuture.getFutureValue(this.future))) {
                    AbstractFuture.complete(this.owner);
                }
            }
        }
    }

    protected AbstractFuture() {
    }

    @Override // java.util.concurrent.Future
    public V get(long timeout, TimeUnit unit) throws InterruptedException, TimeoutException, ExecutionException {
        long remainingNanos = unit.toNanos(timeout);
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object localValue = this.value;
        if ((localValue != null) && (!(localValue instanceof SetFuture))) {
            return getDoneValue(localValue);
        }
        long endNanos = remainingNanos > 0 ? System.nanoTime() + remainingNanos : 0;
        if (remainingNanos >= 1000) {
            Waiter oldHead = this.waiters;
            if (oldHead != Waiter.TOMBSTONE) {
                Waiter node = new Waiter();
                do {
                    node.setNext(oldHead);
                    if (ATOMIC_HELPER.casWaiters(this, oldHead, node)) {
                        do {
                            LockSupport.parkNanos(this, remainingNanos);
                            if (Thread.interrupted()) {
                                removeWaiter(node);
                                throw new InterruptedException();
                            }
                            Object localValue2 = this.value;
                            if ((localValue2 != null) && (!(localValue2 instanceof SetFuture))) {
                                return getDoneValue(localValue2);
                            }
                            remainingNanos = endNanos - System.nanoTime();
                        } while (remainingNanos >= 1000);
                        removeWaiter(node);
                    } else {
                        oldHead = this.waiters;
                    }
                } while (oldHead != Waiter.TOMBSTONE);
            }
            return getDoneValue(this.value);
        }
        while (remainingNanos > 0) {
            Object localValue3 = this.value;
            if ((localValue3 != null) && (!(localValue3 instanceof SetFuture))) {
                return getDoneValue(localValue3);
            }
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
            remainingNanos = endNanos - System.nanoTime();
        }
        String futureToString = toString();
        if (isDone()) {
            throw new TimeoutException("Waited " + timeout + " " + unit.toString().toLowerCase(Locale.ROOT) + " but future completed as timeout expired");
        }
        throw new TimeoutException("Waited " + timeout + " " + unit.toString().toLowerCase(Locale.ROOT) + " for " + futureToString);
    }

    @Override // java.util.concurrent.Future
    public V get() throws InterruptedException, ExecutionException {
        Object localValue;
        boolean z;
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        Object localValue2 = this.value;
        if ((localValue2 != null) && (!(localValue2 instanceof SetFuture))) {
            return getDoneValue(localValue2);
        }
        Waiter oldHead = this.waiters;
        if (oldHead != Waiter.TOMBSTONE) {
            Waiter node = new Waiter();
            do {
                node.setNext(oldHead);
                if (ATOMIC_HELPER.casWaiters(this, oldHead, node)) {
                    do {
                        LockSupport.park(this);
                        if (Thread.interrupted()) {
                            removeWaiter(node);
                            throw new InterruptedException();
                        }
                        localValue = this.value;
                        if (localValue != null) {
                            z = true;
                        } else {
                            z = false;
                        }
                    } while (!(z & (!(localValue instanceof SetFuture))));
                    return getDoneValue(localValue);
                }
                oldHead = this.waiters;
            } while (oldHead != Waiter.TOMBSTONE);
        }
        return getDoneValue(this.value);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    private V getDoneValue(Object obj) throws ExecutionException {
        if (obj instanceof Cancellation) {
            throw cancellationExceptionWithCause("Task was cancelled.", ((Cancellation) obj).cause);
        } else if (obj instanceof Failure) {
            throw new ExecutionException(((Failure) obj).exception);
        } else if (obj == NULL) {
            return null;
        } else {
            return obj;
        }
    }

    public boolean isDone() {
        boolean z = true;
        Object localValue = this.value;
        boolean z2 = localValue != null;
        if (localValue instanceof SetFuture) {
            z = false;
        }
        return z2 & z;
    }

    public boolean isCancelled() {
        return this.value instanceof Cancellation;
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        boolean z;
        Cancellation valueToSet;
        boolean z2;
        Object localValue = this.value;
        boolean rValue = false;
        if (localValue == null) {
            z = true;
        } else {
            z = false;
        }
        if (z || (localValue instanceof SetFuture)) {
            if (GENERATE_CANCELLATION_CAUSES) {
                valueToSet = new Cancellation(mayInterruptIfRunning, new CancellationException("Future.cancel() was called."));
            } else if (mayInterruptIfRunning) {
                valueToSet = Cancellation.CAUSELESS_INTERRUPTED;
            } else {
                valueToSet = Cancellation.CAUSELESS_CANCELLED;
            }
            AbstractFuture<V> abstractFuture = this;
            while (true) {
                if (ATOMIC_HELPER.casValue(abstractFuture, localValue, valueToSet)) {
                    rValue = true;
                    if (mayInterruptIfRunning) {
                        abstractFuture.interruptTask();
                    }
                    complete(abstractFuture);
                    if (!(localValue instanceof SetFuture)) {
                        break;
                    }
                    ListenableFuture<?> futureToPropagateTo = ((SetFuture) localValue).future;
                    if (!(futureToPropagateTo instanceof TrustedFuture)) {
                        futureToPropagateTo.cancel(mayInterruptIfRunning);
                        break;
                    }
                    AbstractFuture<V> abstractFuture2 = (AbstractFuture) futureToPropagateTo;
                    localValue = abstractFuture2.value;
                    if (localValue == null) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (!z2 && !(localValue instanceof SetFuture)) {
                        break;
                    }
                    abstractFuture = abstractFuture2;
                } else {
                    localValue = abstractFuture.value;
                    if (!(localValue instanceof SetFuture)) {
                        break;
                    }
                }
            }
        }
        return rValue;
    }

    /* access modifiers changed from: protected */
    public void interruptTask() {
    }

    /* access modifiers changed from: protected */
    public final boolean wasInterrupted() {
        Object localValue = this.value;
        return (localValue instanceof Cancellation) && ((Cancellation) localValue).wasInterrupted;
    }

    @Override // com.google.common.util.concurrent.ListenableFuture
    public void addListener(Runnable listener, Executor executor) {
        Preconditions.checkNotNull(listener, "Runnable was null.");
        Preconditions.checkNotNull(executor, "Executor was null.");
        Listener oldHead = this.listeners;
        if (oldHead != Listener.TOMBSTONE) {
            Listener newNode = new Listener(listener, executor);
            do {
                newNode.next = oldHead;
                if (!ATOMIC_HELPER.casListeners(this, oldHead, newNode)) {
                    oldHead = this.listeners;
                } else {
                    return;
                }
            } while (oldHead != Listener.TOMBSTONE);
        }
        executeListener(listener, executor);
    }

    /* access modifiers changed from: protected */
    public boolean set(V value2) {
        Object valueToSet;
        if (value2 == null) {
            valueToSet = NULL;
        } else {
            valueToSet = value2;
        }
        if (!ATOMIC_HELPER.casValue(this, null, valueToSet)) {
            return false;
        }
        complete(this);
        return true;
    }

    /* access modifiers changed from: protected */
    public boolean setException(Throwable throwable) {
        if (!ATOMIC_HELPER.casValue(this, null, new Failure((Throwable) Preconditions.checkNotNull(throwable)))) {
            return false;
        }
        complete(this);
        return true;
    }

    /* access modifiers changed from: private */
    public static Object getFutureValue(ListenableFuture<?> future) {
        Object failure;
        if (future instanceof TrustedFuture) {
            Object v = ((AbstractFuture) future).value;
            if (!(v instanceof Cancellation)) {
                return v;
            }
            Cancellation c = (Cancellation) v;
            if (!c.wasInterrupted) {
                return v;
            }
            if (c.cause != null) {
                return new Cancellation(false, c.cause);
            }
            return Cancellation.CAUSELESS_CANCELLED;
        }
        try {
            Object v2 = Futures.getDone(future);
            if (v2 == null) {
                failure = NULL;
            } else {
                failure = v2;
            }
        } catch (ExecutionException exception) {
            failure = new Failure(exception.getCause());
        } catch (CancellationException cancellation) {
            failure = new Cancellation(false, cancellation);
        } catch (Throwable t) {
            failure = new Failure(t);
        }
        return failure;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:13:0x0001 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.google.common.util.concurrent.AbstractFuture<?>] */
    /* JADX WARN: Type inference failed for: r6v1, types: [com.google.common.util.concurrent.AbstractFuture] */
    /* JADX WARN: Type inference failed for: r6v3, types: [com.google.common.util.concurrent.AbstractFuture<V>, com.google.common.util.concurrent.AbstractFuture] */
    /* JADX WARN: Type inference failed for: r5v4, types: [com.google.common.util.concurrent.AbstractFuture$AtomicHelper] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void complete(com.google.common.util.concurrent.AbstractFuture<?> r6) {
        /*
            r1 = 0
        L_0x0001:
            r6.releaseWaiters()
            r6.afterDone()
            com.google.common.util.concurrent.AbstractFuture$Listener r1 = r6.clearListeners(r1)
            r6 = 0
        L_0x000c:
            if (r1 == 0) goto L_0x0035
            r0 = r1
            com.google.common.util.concurrent.AbstractFuture$Listener r1 = r1.next
            java.lang.Runnable r3 = r0.task
            boolean r5 = r3 instanceof com.google.common.util.concurrent.AbstractFuture.SetFuture
            if (r5 == 0) goto L_0x002f
            r2 = r3
            com.google.common.util.concurrent.AbstractFuture$SetFuture r2 = (com.google.common.util.concurrent.AbstractFuture.SetFuture) r2
            com.google.common.util.concurrent.AbstractFuture<V> r6 = r2.owner
            java.lang.Object r5 = r6.value
            if (r5 != r2) goto L_0x000c
            com.google.common.util.concurrent.ListenableFuture<? extends V> r5 = r2.future
            java.lang.Object r4 = getFutureValue(r5)
            com.google.common.util.concurrent.AbstractFuture$AtomicHelper r5 = com.google.common.util.concurrent.AbstractFuture.ATOMIC_HELPER
            boolean r5 = r5.casValue(r6, r2, r4)
            if (r5 == 0) goto L_0x000c
            goto L_0x0001
        L_0x002f:
            java.util.concurrent.Executor r5 = r0.executor
            executeListener(r3, r5)
            goto L_0x000c
        L_0x0035:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.AbstractFuture.complete(com.google.common.util.concurrent.AbstractFuture):void");
    }

    /* access modifiers changed from: protected */
    public void afterDone() {
    }

    private void releaseWaiters() {
        Waiter head;
        do {
            head = this.waiters;
        } while (!ATOMIC_HELPER.casWaiters(this, head, Waiter.TOMBSTONE));
        for (Waiter currentWaiter = head; currentWaiter != null; currentWaiter = currentWaiter.next) {
            currentWaiter.unpark();
        }
    }

    private Listener clearListeners(Listener onto) {
        Listener head;
        do {
            head = this.listeners;
        } while (!ATOMIC_HELPER.casListeners(this, head, Listener.TOMBSTONE));
        Listener reversedList = onto;
        while (head != null) {
            head = head.next;
            head.next = reversedList;
            reversedList = head;
        }
        return reversedList;
    }

    public String toString() {
        String pendingDescription;
        StringBuilder builder = new StringBuilder().append(super.toString()).append("[status=");
        if (isCancelled()) {
            builder.append("CANCELLED");
        } else if (isDone()) {
            addDoneString(builder);
        } else {
            try {
                pendingDescription = pendingToString();
            } catch (RuntimeException e) {
                pendingDescription = "Exception thrown from implementation: " + e.getClass();
            }
            if (!Strings.isNullOrEmpty(pendingDescription)) {
                builder.append("PENDING, info=[").append(pendingDescription).append("]");
            } else if (isDone()) {
                addDoneString(builder);
            } else {
                builder.append("PENDING");
            }
        }
        return builder.append("]").toString();
    }

    /* access modifiers changed from: protected */
    public String pendingToString() {
        Object localValue = this.value;
        if (localValue instanceof SetFuture) {
            return "setFuture=[" + userObjectToString(((SetFuture) localValue).future) + "]";
        }
        if (this instanceof ScheduledFuture) {
            return "remaining delay=[" + ((ScheduledFuture) this).getDelay(TimeUnit.MILLISECONDS) + " ms]";
        }
        return null;
    }

    private void addDoneString(StringBuilder builder) {
        try {
            builder.append("SUCCESS, result=[").append(userObjectToString(Futures.getDone(this))).append("]");
        } catch (ExecutionException e) {
            builder.append("FAILURE, cause=[").append(e.getCause()).append("]");
        } catch (CancellationException e2) {
            builder.append("CANCELLED");
        } catch (RuntimeException e3) {
            builder.append("UNKNOWN, cause=[").append(e3.getClass()).append(" thrown from get()]");
        }
    }

    private String userObjectToString(Object o) {
        if (o == this) {
            return "this future";
        }
        return String.valueOf(o);
    }

    private static void executeListener(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (RuntimeException e) {
            log.log(Level.SEVERE, "RuntimeException while executing runnable " + runnable + " with executor " + executor, (Throwable) e);
        }
    }

    /* access modifiers changed from: private */
    public static abstract class AtomicHelper {
        /* access modifiers changed from: package-private */
        public abstract boolean casListeners(AbstractFuture<?> abstractFuture, Listener listener, Listener listener2);

        /* access modifiers changed from: package-private */
        public abstract boolean casValue(AbstractFuture<?> abstractFuture, Object obj, Object obj2);

        /* access modifiers changed from: package-private */
        public abstract boolean casWaiters(AbstractFuture<?> abstractFuture, Waiter waiter, Waiter waiter2);

        /* access modifiers changed from: package-private */
        public abstract void putNext(Waiter waiter, Waiter waiter2);

        /* access modifiers changed from: package-private */
        public abstract void putThread(Waiter waiter, Thread thread);

        private AtomicHelper() {
        }
    }

    private static final class UnsafeAtomicHelper extends AtomicHelper {
        static final long LISTENERS_OFFSET;
        static final Unsafe UNSAFE;
        static final long VALUE_OFFSET;
        static final long WAITERS_OFFSET;
        static final long WAITER_NEXT_OFFSET;
        static final long WAITER_THREAD_OFFSET;

        private UnsafeAtomicHelper() {
            super();
        }

        static {
            Unsafe unsafe;
            try {
                unsafe = Unsafe.getUnsafe();
            } catch (SecurityException e) {
                try {
                    unsafe = (Unsafe) AccessController.doPrivileged(new PrivilegedExceptionAction<Unsafe>() {
                        /* class com.google.common.util.concurrent.AbstractFuture.UnsafeAtomicHelper.AnonymousClass1 */

                        @Override // java.security.PrivilegedExceptionAction
                        public Unsafe run() throws Exception {
                            Field[] declaredFields = Unsafe.class.getDeclaredFields();
                            for (Field f : declaredFields) {
                                f.setAccessible(true);
                                Object x = f.get(null);
                                if (Unsafe.class.isInstance(x)) {
                                    return (Unsafe) Unsafe.class.cast(x);
                                }
                            }
                            throw new NoSuchFieldError("the Unsafe");
                        }
                    });
                } catch (PrivilegedActionException e2) {
                    throw new RuntimeException("Could not initialize intrinsics", e2.getCause());
                }
            }
            try {
                WAITERS_OFFSET = unsafe.objectFieldOffset(AbstractFuture.class.getDeclaredField("waiters"));
                LISTENERS_OFFSET = unsafe.objectFieldOffset(AbstractFuture.class.getDeclaredField("listeners"));
                VALUE_OFFSET = unsafe.objectFieldOffset(AbstractFuture.class.getDeclaredField("value"));
                WAITER_THREAD_OFFSET = unsafe.objectFieldOffset(Waiter.class.getDeclaredField("thread"));
                WAITER_NEXT_OFFSET = unsafe.objectFieldOffset(Waiter.class.getDeclaredField("next"));
                UNSAFE = unsafe;
            } catch (Exception e3) {
                Throwables.throwIfUnchecked(e3);
                throw new RuntimeException(e3);
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        public void putThread(Waiter waiter, Thread newValue) {
            UNSAFE.putObject(waiter, WAITER_THREAD_OFFSET, newValue);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        public void putNext(Waiter waiter, Waiter newValue) {
            UNSAFE.putObject(waiter, WAITER_NEXT_OFFSET, newValue);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        public boolean casWaiters(AbstractFuture<?> future, Waiter expect, Waiter update) {
            return UNSAFE.compareAndSwapObject(future, WAITERS_OFFSET, expect, update);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        public boolean casListeners(AbstractFuture<?> future, Listener expect, Listener update) {
            return UNSAFE.compareAndSwapObject(future, LISTENERS_OFFSET, expect, update);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        public boolean casValue(AbstractFuture<?> future, Object expect, Object update) {
            return UNSAFE.compareAndSwapObject(future, VALUE_OFFSET, expect, update);
        }
    }

    private static final class SafeAtomicHelper extends AtomicHelper {
        final AtomicReferenceFieldUpdater<AbstractFuture, Listener> listenersUpdater;
        final AtomicReferenceFieldUpdater<AbstractFuture, Object> valueUpdater;
        final AtomicReferenceFieldUpdater<Waiter, Waiter> waiterNextUpdater;
        final AtomicReferenceFieldUpdater<Waiter, Thread> waiterThreadUpdater;
        final AtomicReferenceFieldUpdater<AbstractFuture, Waiter> waitersUpdater;

        SafeAtomicHelper(AtomicReferenceFieldUpdater<Waiter, Thread> waiterThreadUpdater2, AtomicReferenceFieldUpdater<Waiter, Waiter> waiterNextUpdater2, AtomicReferenceFieldUpdater<AbstractFuture, Waiter> waitersUpdater2, AtomicReferenceFieldUpdater<AbstractFuture, Listener> listenersUpdater2, AtomicReferenceFieldUpdater<AbstractFuture, Object> valueUpdater2) {
            super();
            this.waiterThreadUpdater = waiterThreadUpdater2;
            this.waiterNextUpdater = waiterNextUpdater2;
            this.waitersUpdater = waitersUpdater2;
            this.listenersUpdater = listenersUpdater2;
            this.valueUpdater = valueUpdater2;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        public void putThread(Waiter waiter, Thread newValue) {
            this.waiterThreadUpdater.lazySet(waiter, newValue);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        public void putNext(Waiter waiter, Waiter newValue) {
            this.waiterNextUpdater.lazySet(waiter, newValue);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        public boolean casWaiters(AbstractFuture<?> future, Waiter expect, Waiter update) {
            return this.waitersUpdater.compareAndSet(future, expect, update);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        public boolean casListeners(AbstractFuture<?> future, Listener expect, Listener update) {
            return this.listenersUpdater.compareAndSet(future, expect, update);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        public boolean casValue(AbstractFuture<?> future, Object expect, Object update) {
            return this.valueUpdater.compareAndSet(future, expect, update);
        }
    }

    private static final class SynchronizedHelper extends AtomicHelper {
        private SynchronizedHelper() {
            super();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        public void putThread(Waiter waiter, Thread newValue) {
            waiter.thread = newValue;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        public void putNext(Waiter waiter, Waiter newValue) {
            waiter.next = newValue;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        public boolean casWaiters(AbstractFuture<?> future, Waiter expect, Waiter update) {
            boolean z;
            synchronized (future) {
                if (((AbstractFuture) future).waiters == expect) {
                    ((AbstractFuture) future).waiters = update;
                    z = true;
                } else {
                    z = false;
                }
            }
            return z;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        public boolean casListeners(AbstractFuture<?> future, Listener expect, Listener update) {
            boolean z;
            synchronized (future) {
                if (((AbstractFuture) future).listeners == expect) {
                    ((AbstractFuture) future).listeners = update;
                    z = true;
                } else {
                    z = false;
                }
            }
            return z;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.util.concurrent.AbstractFuture.AtomicHelper
        public boolean casValue(AbstractFuture<?> future, Object expect, Object update) {
            boolean z;
            synchronized (future) {
                if (((AbstractFuture) future).value == expect) {
                    ((AbstractFuture) future).value = update;
                    z = true;
                } else {
                    z = false;
                }
            }
            return z;
        }
    }

    private static CancellationException cancellationExceptionWithCause(String message, Throwable cause) {
        CancellationException exception = new CancellationException(message);
        exception.initCause(cause);
        return exception;
    }
}
