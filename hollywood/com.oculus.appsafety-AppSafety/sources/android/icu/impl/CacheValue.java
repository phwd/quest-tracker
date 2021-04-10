package android.icu.impl;

import android.icu.util.ICUException;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

public abstract class CacheValue<V> {
    private static final CacheValue NULL_VALUE = new NullValue();
    private static volatile Strength strength = Strength.SOFT;

    public enum Strength {
        STRONG,
        SOFT
    }

    public abstract V get();

    public abstract V resetIfCleared(V v);

    public static void setStrength(Strength strength2) {
        strength = strength2;
    }

    public static boolean futureInstancesWillBeStrong() {
        return strength == Strength.STRONG;
    }

    public static <V> CacheValue<V> getInstance(V value) {
        if (value == null) {
            return NULL_VALUE;
        }
        return strength == Strength.STRONG ? new StrongValue(value) : new SoftValue(value);
    }

    public boolean isNull() {
        return false;
    }

    private static final class NullValue<V> extends CacheValue<V> {
        private NullValue() {
        }

        @Override // android.icu.impl.CacheValue
        public boolean isNull() {
            return true;
        }

        @Override // android.icu.impl.CacheValue
        public V get() {
            return null;
        }

        @Override // android.icu.impl.CacheValue
        public V resetIfCleared(V value) {
            if (value == null) {
                return null;
            }
            throw new ICUException("resetting a null value to a non-null value");
        }
    }

    private static final class StrongValue<V> extends CacheValue<V> {
        private V value;

        StrongValue(V value2) {
            this.value = value2;
        }

        @Override // android.icu.impl.CacheValue
        public V get() {
            return this.value;
        }

        @Override // android.icu.impl.CacheValue
        public V resetIfCleared(V v) {
            return this.value;
        }
    }

    private static final class SoftValue<V> extends CacheValue<V> {
        private volatile Reference<V> ref;

        SoftValue(V value) {
            this.ref = new SoftReference(value);
        }

        @Override // android.icu.impl.CacheValue
        public V get() {
            return this.ref.get();
        }

        @Override // android.icu.impl.CacheValue
        public synchronized V resetIfCleared(V value) {
            V oldValue = this.ref.get();
            if (oldValue != null) {
                return oldValue;
            }
            this.ref = new SoftReference(value);
            return value;
        }
    }
}
