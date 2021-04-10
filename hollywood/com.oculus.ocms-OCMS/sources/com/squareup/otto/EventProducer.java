package com.squareup.otto;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* access modifiers changed from: package-private */
public class EventProducer {
    private final int hashCode;
    private final Method method;
    final Object target;
    private boolean valid = true;

    EventProducer(Object obj, Method method2) {
        if (obj == null) {
            throw new NullPointerException("EventProducer target cannot be null.");
        } else if (method2 != null) {
            this.target = obj;
            this.method = method2;
            method2.setAccessible(true);
            this.hashCode = ((method2.hashCode() + 31) * 31) + obj.hashCode();
        } else {
            throw new NullPointerException("EventProducer method cannot be null.");
        }
    }

    public boolean isValid() {
        return this.valid;
    }

    public void invalidate() {
        this.valid = false;
    }

    public Object produceEvent() throws InvocationTargetException {
        if (this.valid) {
            try {
                return this.method.invoke(this.target, new Object[0]);
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (InvocationTargetException e2) {
                if (e2.getCause() instanceof Error) {
                    throw ((Error) e2.getCause());
                }
                throw e2;
            }
        } else {
            throw new IllegalStateException(toString() + " has been invalidated and can no longer produce events.");
        }
    }

    public String toString() {
        return "[EventProducer " + this.method + "]";
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
        EventProducer eventProducer = (EventProducer) obj;
        if (!this.method.equals(eventProducer.method) || this.target != eventProducer.target) {
            return false;
        }
        return true;
    }
}
