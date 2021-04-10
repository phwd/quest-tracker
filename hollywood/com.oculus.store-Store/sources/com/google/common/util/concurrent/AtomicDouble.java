package com.google.common.util.concurrent;

import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

public class AtomicDouble extends Number implements Serializable {
    private static final long serialVersionUID = 0;
    private transient AtomicLong value;

    public AtomicDouble(double initialValue) {
        this.value = new AtomicLong(Double.doubleToRawLongBits(initialValue));
    }

    public AtomicDouble() {
        this(0.0d);
    }

    public final double get() {
        return Double.longBitsToDouble(this.value.get());
    }

    public final void set(double newValue) {
        this.value.set(Double.doubleToRawLongBits(newValue));
    }

    public final void lazySet(double newValue) {
        this.value.lazySet(Double.doubleToRawLongBits(newValue));
    }

    public final double getAndSet(double newValue) {
        return Double.longBitsToDouble(this.value.getAndSet(Double.doubleToRawLongBits(newValue)));
    }

    public final boolean compareAndSet(double expect, double update) {
        return this.value.compareAndSet(Double.doubleToRawLongBits(expect), Double.doubleToRawLongBits(update));
    }

    public final boolean weakCompareAndSet(double expect, double update) {
        return this.value.weakCompareAndSet(Double.doubleToRawLongBits(expect), Double.doubleToRawLongBits(update));
    }

    @CanIgnoreReturnValue
    public final double getAndAdd(double delta) {
        long current;
        double currentVal;
        do {
            current = this.value.get();
            currentVal = Double.longBitsToDouble(current);
        } while (!this.value.compareAndSet(current, Double.doubleToRawLongBits(currentVal + delta)));
        return currentVal;
    }

    @CanIgnoreReturnValue
    public final double addAndGet(double delta) {
        long current;
        double nextVal;
        do {
            current = this.value.get();
            nextVal = Double.longBitsToDouble(current) + delta;
        } while (!this.value.compareAndSet(current, Double.doubleToRawLongBits(nextVal)));
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
        this.value = new AtomicLong();
        set(s.readDouble());
    }
}
