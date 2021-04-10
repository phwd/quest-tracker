package com.squareup.otto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* access modifiers changed from: package-private */
public class EventHandler {
    private final int hashCode;
    private final Method method;
    private final Object target;
    private boolean valid = true;

    EventHandler(Object obj, Method method2) {
        if (obj == null) {
            throw new NullPointerException("EventHandler target cannot be null.");
        } else if (method2 != null) {
            this.target = obj;
            this.method = method2;
            method2.setAccessible(true);
            this.hashCode = ((method2.hashCode() + 31) * 31) + obj.hashCode();
        } else {
            throw new NullPointerException("EventHandler method cannot be null.");
        }
    }

    public boolean isValid() {
        return this.valid;
    }

    public void invalidate() {
        this.valid = false;
    }

    public void handleEvent(Object obj) throws InvocationTargetException {
        if (this.valid) {
            try {
                this.method.invoke(this.target, obj);
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (InvocationTargetException e2) {
                if (e2.getCause() instanceof Error) {
                    throw ((Error) e2.getCause());
                }
                throw e2;
            }
        } else {
            throw new IllegalStateException(toString() + " has been invalidated and can no longer handle events.");
        }
    }

    public String toString() {
        return "[EventHandler " + this.method + "]";
    }

    public int hashCode() {
        return this.hashCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        EventHandler eventHandler = (EventHandler) obj;
        if (!this.method.equals(eventHandler.method) || this.target != eventHandler.target) {
            return false;
        }
        return true;
    }
}
