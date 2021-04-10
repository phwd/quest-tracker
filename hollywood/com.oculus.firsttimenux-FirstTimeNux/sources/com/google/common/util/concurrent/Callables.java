package com.google.common.util.concurrent;

import com.facebook.common.build.config.BuildConfig;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import java.util.concurrent.Callable;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@GwtCompatible(emulated = BuildConfig.IS_INTERNAL_BUILD)
public final class Callables {
    private Callables() {
    }

    public static <T> Callable<T> returning(@NullableDecl final T value) {
        return new Callable<T>() {
            /* class com.google.common.util.concurrent.Callables.AnonymousClass1 */

            @Override // java.util.concurrent.Callable
            public T call() {
                return (T) value;
            }
        };
    }

    @Beta
    @GwtIncompatible
    public static <T> AsyncCallable<T> asAsyncCallable(final Callable<T> callable, final ListeningExecutorService listeningExecutorService) {
        Preconditions.checkNotNull(callable);
        Preconditions.checkNotNull(listeningExecutorService);
        return new AsyncCallable<T>() {
            /* class com.google.common.util.concurrent.Callables.AnonymousClass2 */

            @Override // com.google.common.util.concurrent.AsyncCallable
            public ListenableFuture<T> call() throws Exception {
                return ListeningExecutorService.this.submit(callable);
            }
        };
    }

    @GwtIncompatible
    static <T> Callable<T> threadRenaming(final Callable<T> callable, final Supplier<String> nameSupplier) {
        Preconditions.checkNotNull(nameSupplier);
        Preconditions.checkNotNull(callable);
        return new Callable<T>() {
            /* class com.google.common.util.concurrent.Callables.AnonymousClass3 */

            @Override // java.util.concurrent.Callable
            public T call() throws Exception {
                Thread currentThread = Thread.currentThread();
                String oldName = currentThread.getName();
                boolean restoreName = Callables.trySetName((String) Supplier.this.get(), currentThread);
                try {
                    return (T) callable.call();
                } finally {
                    if (restoreName) {
                        Callables.trySetName(oldName, currentThread);
                    }
                }
            }
        };
    }

    @GwtIncompatible
    static Runnable threadRenaming(final Runnable task, final Supplier<String> nameSupplier) {
        Preconditions.checkNotNull(nameSupplier);
        Preconditions.checkNotNull(task);
        return new Runnable() {
            /* class com.google.common.util.concurrent.Callables.AnonymousClass4 */

            public void run() {
                Thread currentThread = Thread.currentThread();
                String oldName = currentThread.getName();
                boolean restoreName = Callables.trySetName((String) Supplier.this.get(), currentThread);
                try {
                    task.run();
                } finally {
                    if (restoreName) {
                        Callables.trySetName(oldName, currentThread);
                    }
                }
            }
        };
    }

    /* access modifiers changed from: private */
    @GwtIncompatible
    public static boolean trySetName(String threadName, Thread currentThread) {
        try {
            currentThread.setName(threadName);
            return true;
        } catch (SecurityException e) {
            return false;
        }
    }
}
