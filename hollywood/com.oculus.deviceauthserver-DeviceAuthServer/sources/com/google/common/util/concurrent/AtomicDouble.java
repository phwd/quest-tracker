package com.google.common.util.concurrent;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

public class AtomicDouble extends Number implements Serializable {
    private static final long serialVersionUID = 0;
    private static final AtomicLongFieldUpdater<AtomicDouble> updater = AtomicLongFieldUpdater.newUpdater(AtomicDouble.class, "value");
    private volatile transient long value;

    public AtomicDouble(double initialValue) {
        this.value = Double.doubleToRawLongBits(initialValue);
    }

    public AtomicDouble() {
    }

    public final double get() {
        return Double.longBitsToDouble(this.value);
    }

    public final void set(double newValue) {
        this.value = Double.doubleToRawLongBits(newValue);
    }

    public final void lazySet(double newValue) {
        set(newValue);
    }

    public final double getAndSet(double newValue) {
        return Double.longBitsToDouble(updater.getAndSet(this, Double.doubleToRawLongBits(newValue)));
    }

    public final boolean compareAndSet(double expect, double update) {
        return updater.compareAndSet(this, Double.doubleToRawLongBits(expect), Double.doubleToRawLongBits(update));
    }

    public final boolean weakCompareAndSet(double expect, double update) {
        return updater.weakCompareAndSet(this, Double.doubleToRawLongBits(expect), Double.doubleToRawLongBits(update));
    }

    public final double getAndAdd(double delta) {
        long current;
        double currentVal;
        do {
            current = this.value;
            currentVal = Double.longBitsToDouble(current);
        } while (!updater.compareAndSet(this, current, Double.doubleToRawLongBits(currentVal + delta)));
        return currentVal;
    }

    public final double addAndGet(double delta) {
        long current;
        double nextVal;
        do {
            current = this.value;
            nextVal = Double.longBitsToDouble(current) + delta;
        } while (!updater.compareAndSet(this, current, Double.doubleToRawLongBits(nextVal)));
        return nextVal;
    }

    public String toString() {
        return Double.toString(get());
    }

    public int intValue() {
        return (int) get();
    }

    public long longValue() {
        return (long) get();
    }

    public float floatValue() {
        return (float) get();
    }

    public double doubleValue() {
        return get();
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeDouble(get());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        s.defaultReadObject();
        set(s.readDouble());
    }
}
