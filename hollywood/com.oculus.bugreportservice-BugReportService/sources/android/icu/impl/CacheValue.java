package android.icu.impl;

import android.icu.util.ICUException;
import java.lang.ref.Reference;
import java.lang.ref.SoftReference;

public abstract class CacheValue {
    private static final CacheValue NULL_VALUE = new NullValue();
    private static volatile Strength strength = Strength.SOFT;

    public enum Strength {
        STRONG,
        SOFT
    }

    public abstract Object get();

    public boolean isNull() {
        return false;
    }

    public abstract Object resetIfCleared(Object obj);

    public static boolean futureInstancesWillBeStrong() {
        return strength == Strength.STRONG;
    }

    public static CacheValue getInstance(Object obj) {
        if (obj == null) {
            return NULL_VALUE;
        }
        return strength == Strength.STRONG ? new StrongValue(obj) : new SoftValue(obj);
    }

    private static final class NullValue extends CacheValue {
        @Override // android.icu.impl.CacheValue
        public Object get() {
            return null;
        }

        @Override // android.icu.impl.CacheValue
        public boolean isNull() {
            return true;
        }

        private NullValue() {
        }

        @Override // android.icu.impl.CacheValue
        public Object resetIfCleared(Object obj) {
            if (obj == null) {
                return null;
            }
            throw new ICUException("resetting a null value to a non-null value");
        }
    }

    private static final class StrongValue extends CacheValue {
        private Object value;

        StrongValue(Object obj) {
            this.value = obj;
        }

        @Override // android.icu.impl.CacheValue
        public Object get() {
            return this.value;
        }

        @Override // android.icu.impl.CacheValue
        public Object resetIfCleared(Object obj) {
            return this.value;
        }
    }

    private static final class SoftValue extends CacheValue {
        private volatile Reference ref;

        SoftValue(Object obj) {
            this.ref = new SoftReference(obj);
        }

        @Override // android.icu.impl.CacheValue
        public Object get() {
            return this.ref.get();
        }

        @Override // android.icu.impl.CacheValue
        public synchronized Object resetIfCleared(Object obj) {
            Object obj2 = this.ref.get();
            if (obj2 != null) {
                return obj2;
            }
            this.ref = new SoftReference(obj);
            return obj;
        }
    }
}
