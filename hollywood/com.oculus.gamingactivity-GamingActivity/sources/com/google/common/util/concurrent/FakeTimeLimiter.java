package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.base.Preconditions;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@CanIgnoreReturnValue
@Beta
@GwtIncompatible
public final class FakeTimeLimiter implements TimeLimiter {
    @Override // com.google.common.util.concurrent.TimeLimiter
    public <T> T newProxy(T target, Class<T> interfaceType, long timeoutDuration, TimeUnit timeoutUnit) {
        Preconditions.checkNotNull(target);
        Preconditions.checkNotNull(interfaceType);
        Preconditions.checkNotNull(timeoutUnit);
        return target;
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public <T> T callWithTimeout(Callable<T> callable, long timeoutDuration, TimeUnit timeoutUnit) throws ExecutionException {
        Preconditions.checkNotNull(callable);
        Preconditions.checkNotNull(timeoutUnit);
        try {
            return callable.call();
        } catch (RuntimeException e) {
            throw new UncheckedExecutionException(e);
        } catch (Exception e2) {
            throw new ExecutionException(e2);
        } catch (Error e3) {
            throw new ExecutionError(e3);
        } catch (Throwable e4) {
            throw new ExecutionException(e4);
        }
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public <T> T callUninterruptiblyWithTimeout(Callable<T> callable, long timeoutDuration, TimeUnit timeoutUnit) throws ExecutionException {
        return (T) callWithTimeout(callable, timeoutDuration, timeoutUnit);
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public void runWithTimeout(Runnable runnable, long timeoutDuration, TimeUnit timeoutUnit) {
        Preconditions.checkNotNull(runnable);
        Preconditions.checkNotNull(timeoutUnit);
        try {
            runnable.run();
        } catch (RuntimeException e) {
            throw new UncheckedExecutionException(e);
        } catch (Error e2) {
            throw new ExecutionError(e2);
        } catch (Throwable e3) {
            throw new UncheckedExecutionException(e3);
        }
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public void runUninterruptiblyWithTimeout(Runnable runnable, long timeoutDuration, TimeUnit timeoutUnit) {
        runWithTimeout(runnable, timeoutDuration, timeoutUnit);
    }
}
