package com.squareup.otto;

import X.AnonymousClass006;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EventProducer {
    public final int hashCode;
    public final Method method;
    public final Object target;
    public boolean valid = true;

    public boolean equals(Object obj) {
        if (this != obj) {
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            EventProducer eventProducer = (EventProducer) obj;
            if (!this.method.equals(eventProducer.method) || this.target != eventProducer.target) {
                return false;
            }
        }
        return true;
    }

    public void invalidate() {
        this.valid = false;
    }

    public int hashCode() {
        return this.hashCode;
    }

    public boolean isValid() {
        return this.valid;
    }

    public Object produceEvent() throws InvocationTargetException {
        if (this.valid) {
            try {
                return this.method.invoke(this.target, new Object[0]);
            } catch (IllegalAccessException e) {
                throw new AssertionError(e);
            } catch (InvocationTargetException e2) {
                if (e2.getCause() instanceof Error) {
                    throw e2.getCause();
                }
                throw e2;
            }
        } else {
            throw new IllegalStateException(AnonymousClass006.A07(toString(), " has been invalidated and can no longer produce events."));
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("[EventProducer ");
        sb.append(this.method);
        sb.append("]");
        return sb.toString();
    }

    public EventProducer(Object obj, Method method2) {
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
}
