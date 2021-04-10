package com.google.common.eventbus;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.j2objc.annotations.Weak;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* access modifiers changed from: package-private */
public class Subscriber {
    @Weak
    private EventBus bus;
    private final Executor executor;
    private final Method method;
    @VisibleForTesting
    final Object target;

    static Subscriber create(EventBus bus2, Object listener, Method method2) {
        if (isDeclaredThreadSafe(method2)) {
            return new Subscriber(bus2, listener, method2);
        }
        return new SynchronizedSubscriber(bus2, listener, method2);
    }

    private Subscriber(EventBus bus2, Object target2, Method method2) {
        this.bus = bus2;
        this.target = Preconditions.checkNotNull(target2);
        this.method = method2;
        method2.setAccessible(true);
        this.executor = bus2.executor();
    }

    /* access modifiers changed from: package-private */
    public final void dispatchEvent(final Object event) {
        this.executor.execute(new Runnable() {
            /* class com.google.common.eventbus.Subscriber.AnonymousClass1 */

            public void run() {
                try {
                    Subscriber.this.invokeSubscriberMethod(event);
                } catch (InvocationTargetException e) {
                    Subscriber.this.bus.handleSubscriberException(e.getCause(), Subscriber.this.context(event));
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void invokeSubscriberMethod(Object event) throws InvocationTargetException {
        try {
            this.method.invoke(this.target, Preconditions.checkNotNull(event));
        } catch (IllegalArgumentException e) {
            throw new Error("Method rejected target/argument: " + event, e);
        } catch (IllegalAccessException e2) {
            throw new Error("Method became inaccessible: " + event, e2);
        } catch (InvocationTargetException e3) {
            if (e3.getCause() instanceof Error) {
                throw ((Error) e3.getCause());
            }
            throw e3;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private SubscriberExceptionContext context(Object event) {
        return new SubscriberExceptionContext(this.bus, event, this.target, this.method);
    }

    public final int hashCode() {
        return ((this.method.hashCode() + 31) * 31) + System.identityHashCode(this.target);
    }

    public final boolean equals(@NullableDecl Object obj) {
        if (!(obj instanceof Subscriber)) {
            return false;
        }
        Subscriber that = (Subscriber) obj;
        if (this.target != that.target || !this.method.equals(that.method)) {
            return false;
        }
        return true;
    }

    private static boolean isDeclaredThreadSafe(Method method2) {
        return method2.getAnnotation(AllowConcurrentEvents.class) != null;
    }

    @VisibleForTesting
    static final class SynchronizedSubscriber extends Subscriber {
        private SynchronizedSubscriber(EventBus bus, Object target, Method method) {
            super(bus, target, method);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.eventbus.Subscriber
        public void invokeSubscriberMethod(Object event) throws InvocationTargetException {
            synchronized (this) {
                Subscriber.super.invokeSubscriberMethod(event);
            }
        }
    }
}
