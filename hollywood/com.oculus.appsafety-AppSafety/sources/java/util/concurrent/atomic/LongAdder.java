package java.util.concurrent.atomic;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.atomic.Striped64;

public class LongAdder extends Striped64 implements Serializable {
    private static final long serialVersionUID = 7249069246863182397L;

    public void add(long x) {
        int m;
        Striped64.Cell a;
        Striped64.Cell[] as = this.cells;
        if (as == null) {
            long b = this.base;
            if (casBase(b, b + x)) {
                return;
            }
        }
        boolean uncontended = true;
        if (!(as == null || as.length - 1 < 0 || (a = as[getProbe() & m]) == null)) {
            long v = a.value;
            boolean cas = a.cas(v, v + x);
            uncontended = cas;
            if (cas) {
                return;
            }
        }
        longAccumulate(x, null, uncontended);
    }

    public void increment() {
        add(1);
    }

    public void decrement() {
        add(-1);
    }

    public long sum() {
        Striped64.Cell[] as = this.cells;
        long sum = this.base;
        if (as != null) {
            for (Striped64.Cell a : as) {
                if (a != null) {
                    sum += a.value;
                }
            }
        }
        return sum;
    }

    public void reset() {
        Striped64.Cell[] as = this.cells;
        this.base = 0;
        if (as != null) {
            for (Striped64.Cell a : as) {
                if (a != null) {
                    a.reset();
                }
            }
        }
    }

    public long sumThenReset() {
        Striped64.Cell[] as = this.cells;
        long sum = this.base;
        this.base = 0;
        if (as != null) {
            for (Striped64.Cell a : as) {
                if (a != null) {
                    sum += a.value;
                    a.reset();
                }
            }
        }
        return sum;
    }

    public String toString() {
        return Long.toString(sum());
    }

    @Override // java.lang.Number
    public long longValue() {
        return sum();
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) sum();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return (float) sum();
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return (double) sum();
    }

    private static class SerializationProxy implements Serializable {
        private static final long serialVersionUID = 7249069246863182397L;
        private final long value;

        SerializationProxy(LongAdder a) {
            this.value = a.sum();
        }

        private Object readResolve() {
            LongAdder a = new LongAdder();
            a.base = this.value;
            return a;
        }
    }

    private Object writeReplace() {
        return new SerializationProxy(this);
    }

    private void readObject(ObjectInputStream s) throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }
}
