package com.google.common.eventbus;

import com.google.common.base.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
public class EventSubscriber {
    private final Method method;
    private final Object target;

    EventSubscriber(Object target2, Method method2) {
        Preconditions.checkNotNull(target2, "EventSubscriber target cannot be null.");
        Preconditions.checkNotNull(method2, "EventSubscriber method cannot be null.");
        this.target = target2;
        this.method = method2;
        method2.setAccessible(true);
    }

    public void handleEvent(Object event) throws InvocationTargetException {
        Preconditions.checkNotNull(event);
        try {
            this.method.invoke(this.target, event);
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

    public String toString() {
        return "[wrapper " + this.method + "]";
    }

    public int hashCode() {
        return ((this.method.hashCode() + 31) * 31) + System.identityHashCode(this.target);
    }

    public boolean equals(@Nullable Object obj) {
        if (!(obj instanceof EventSubscriber)) {
            return false;
        }
        EventSubscriber that = (EventSubscriber) obj;
        if (this.target != that.target || !this.method.equals(that.method)) {
            return false;
        }
        return true;
    }

    public Object getSubscriber() {
        return this.target;
    }

    public Method getMethod() {
        return this.method;
    }
}
