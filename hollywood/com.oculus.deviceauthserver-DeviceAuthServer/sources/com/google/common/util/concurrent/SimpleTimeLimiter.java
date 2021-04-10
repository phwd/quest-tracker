package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.collect.ObjectArrays;
import com.google.common.collect.Sets;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Beta
public final class SimpleTimeLimiter implements TimeLimiter {
    private final ExecutorService executor;

    public SimpleTimeLimiter(ExecutorService executor2) {
        this.executor = (ExecutorService) Preconditions.checkNotNull(executor2);
    }

    public SimpleTimeLimiter() {
        this(Executors.newCachedThreadPool());
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public <T> T newProxy(final T target, Class<T> interfaceType, final long timeoutDuration, final TimeUnit timeoutUnit) {
        Preconditions.checkNotNull(target);
        Preconditions.checkNotNull(interfaceType);
        Preconditions.checkNotNull(timeoutUnit);
        Preconditions.checkArgument(timeoutDuration > 0, "bad timeout: %s", Long.valueOf(timeoutDuration));
        Preconditions.checkArgument(interfaceType.isInterface(), "interfaceType must be an interface type");
        final Set<Method> interruptibleMethods = findInterruptibleMethods(interfaceType);
        return (T) newProxy(interfaceType, new InvocationHandler() {
            /* class com.google.common.util.concurrent.SimpleTimeLimiter.AnonymousClass1 */

            @Override // java.lang.reflect.InvocationHandler
            public Object invoke(Object obj, final Method method, final Object[] args) throws Throwable {
                return SimpleTimeLimiter.this.callWithTimeout(new Callable<Object>() {
                    /* class com.google.common.util.concurrent.SimpleTimeLimiter.AnonymousClass1.AnonymousClass1 */

                    @Override // java.util.concurrent.Callable
                    public Object call() throws Exception {
                        try {
                            return method.invoke(target, args);
                        } catch (InvocationTargetException e) {
                            SimpleTimeLimiter.throwCause(e, false);
                            throw new AssertionError("can't get here");
                        }
                    }
                }, timeoutDuration, timeoutUnit, interruptibleMethods.contains(method));
            }
        });
    }

    @Override // com.google.common.util.concurrent.TimeLimiter
    public <T> T callWithTimeout(Callable<T> callable, long timeoutDuration, TimeUnit timeoutUnit, boolean amInterruptible) throws Exception {
        Preconditions.checkNotNull(callable);
        Preconditions.checkNotNull(timeoutUnit);
        Preconditions.checkArgument(timeoutDuration > 0, "timeout must be positive: %s", Long.valueOf(timeoutDuration));
        Future<T> future = this.executor.submit(callable);
        if (!amInterruptible) {
            return (T) Uninterruptibles.getUninterruptibly(future, timeoutDuration, timeoutUnit);
        }
        try {
            return future.get(timeoutDuration, timeoutUnit);
        } catch (InterruptedException e) {
            future.cancel(true);
            throw e;
        } catch (ExecutionException e2) {
            throw throwCause(e2, true);
        } catch (TimeoutException e3) {
            future.cancel(true);
            throw new UncheckedTimeoutException(e3);
        }
    }

    /* access modifiers changed from: private */
    public static Exception throwCause(Exception e, boolean combineStackTraces) throws Exception {
        Throwable cause = e.getCause();
        if (cause != null) {
            if (combineStackTraces) {
                cause.setStackTrace((StackTraceElement[]) ObjectArrays.concat(cause.getStackTrace(), e.getStackTrace(), StackTraceElement.class));
            }
            if (cause instanceof Exception) {
                throw ((Exception) cause);
            } else if (cause instanceof Error) {
                throw ((Error) cause);
            } else {
                throw e;
            }
        } else {
            throw e;
        }
    }

    private static Set<Method> findInterruptibleMethods(Class<?> interfaceType) {
        Set<Method> set = Sets.newHashSet();
        Method[] methods = interfaceType.getMethods();
        for (Method m : methods) {
            if (declaresInterruptedEx(m)) {
                set.add(m);
            }
        }
        return set;
    }

    private static boolean declaresInterruptedEx(Method method) {
        for (Class<?> exType : method.getExceptionTypes()) {
            if (exType == InterruptedException.class) {
                return true;
            }
        }
        return false;
    }

    private static <T> T newProxy(Class<T> interfaceType, InvocationHandler handler) {
        return interfaceType.cast(Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class[]{interfaceType}, handler));
    }
}
