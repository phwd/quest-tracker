package java.util.concurrent.atomic;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;
import sun.misc.Unsafe;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;
import sun.reflect.misc.ReflectUtil;

public abstract class AtomicLongFieldUpdater<T> {
    public abstract boolean compareAndSet(T t, long j, long j2);

    public abstract long get(T t);

    public abstract void lazySet(T t, long j);

    public abstract void set(T t, long j);

    public abstract boolean weakCompareAndSet(T t, long j, long j2);

    @CallerSensitive
    public static <U> AtomicLongFieldUpdater<U> newUpdater(Class<U> tclass, String fieldName) {
        Class<?> caller = Reflection.getCallerClass();
        if (AtomicLong.VM_SUPPORTS_LONG_CAS) {
            return new CASUpdater(tclass, fieldName, caller);
        }
        return new LockedUpdater(tclass, fieldName, caller);
    }

    protected AtomicLongFieldUpdater() {
    }

    public long getAndSet(T obj, long newValue) {
        long prev;
        do {
            prev = get(obj);
        } while (!compareAndSet(obj, prev, newValue));
        return prev;
    }

    public long getAndIncrement(T obj) {
        long prev;
        do {
            prev = get(obj);
        } while (!compareAndSet(obj, prev, prev + 1));
        return prev;
    }

    public long getAndDecrement(T obj) {
        long prev;
        do {
            prev = get(obj);
        } while (!compareAndSet(obj, prev, prev - 1));
        return prev;
    }

    public long getAndAdd(T obj, long delta) {
        long prev;
        do {
            prev = get(obj);
        } while (!compareAndSet(obj, prev, prev + delta));
        return prev;
    }

    public long incrementAndGet(T obj) {
        long prev;
        long next;
        do {
            prev = get(obj);
            next = prev + 1;
        } while (!compareAndSet(obj, prev, next));
        return next;
    }

    public long decrementAndGet(T obj) {
        long prev;
        long next;
        do {
            prev = get(obj);
            next = prev - 1;
        } while (!compareAndSet(obj, prev, next));
        return next;
    }

    public long addAndGet(T obj, long delta) {
        long prev;
        long next;
        do {
            prev = get(obj);
            next = prev + delta;
        } while (!compareAndSet(obj, prev, next));
        return next;
    }

    public final long getAndUpdate(T obj, LongUnaryOperator updateFunction) {
        long prev;
        do {
            prev = get(obj);
        } while (!compareAndSet(obj, prev, updateFunction.applyAsLong(prev)));
        return prev;
    }

    public final long updateAndGet(T obj, LongUnaryOperator updateFunction) {
        long prev;
        long next;
        do {
            prev = get(obj);
            next = updateFunction.applyAsLong(prev);
        } while (!compareAndSet(obj, prev, next));
        return next;
    }

    public final long getAndAccumulate(T obj, long x, LongBinaryOperator accumulatorFunction) {
        long prev;
        do {
            prev = get(obj);
        } while (!compareAndSet(obj, prev, accumulatorFunction.applyAsLong(prev, x)));
        return prev;
    }

    public final long accumulateAndGet(T obj, long x, LongBinaryOperator accumulatorFunction) {
        long prev;
        long next;
        do {
            prev = get(obj);
            next = accumulatorFunction.applyAsLong(prev, x);
        } while (!compareAndSet(obj, prev, next));
        return next;
    }

    /* access modifiers changed from: private */
    public static final class CASUpdater<T> extends AtomicLongFieldUpdater<T> {
        private static final Unsafe U = Unsafe.getUnsafe();
        private final Class<?> cclass;
        private final long offset;
        private final Class<T> tclass;

        /* JADX DEBUG: Multi-variable search result rejected for r2v6, resolved type: java.lang.Class<?> */
        /* JADX DEBUG: Multi-variable search result rejected for r2v9, resolved type: java.lang.Class<?> */
        /* JADX WARN: Multi-variable type inference failed */
        CASUpdater(Class<T> tclass2, String fieldName, Class<?> caller) {
            try {
                Field field = tclass2.getDeclaredField(fieldName);
                int modifiers = field.getModifiers();
                ReflectUtil.ensureMemberAccess(caller, tclass2, null, modifiers);
                if (field.getType() != Long.TYPE) {
                    throw new IllegalArgumentException("Must be long type");
                } else if (Modifier.isVolatile(modifiers)) {
                    this.cclass = Modifier.isProtected(modifiers) ? caller : tclass2;
                    this.tclass = tclass2;
                    this.offset = U.objectFieldOffset(field);
                } else {
                    throw new IllegalArgumentException("Must be volatile type");
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        private final void accessCheck(T obj) {
            if (!this.cclass.isInstance(obj)) {
                throwAccessCheckException(obj);
            }
        }

        private final void throwAccessCheckException(T obj) {
            if (this.cclass == this.tclass) {
                throw new ClassCastException();
            }
            throw new RuntimeException(new IllegalAccessException("Class " + this.cclass.getName() + " can not access a protected member of class " + this.tclass.getName() + " using an instance of " + obj.getClass().getName()));
        }

        @Override // java.util.concurrent.atomic.AtomicLongFieldUpdater
        public final boolean compareAndSet(T obj, long expect, long update) {
            accessCheck(obj);
            return U.compareAndSwapLong(obj, this.offset, expect, update);
        }

        @Override // java.util.concurrent.atomic.AtomicLongFieldUpdater
        public final boolean weakCompareAndSet(T obj, long expect, long update) {
            accessCheck(obj);
            return U.compareAndSwapLong(obj, this.offset, expect, update);
        }

        @Override // java.util.concurrent.atomic.AtomicLongFieldUpdater
        public final void set(T obj, long newValue) {
            accessCheck(obj);
            U.putLongVolatile(obj, this.offset, newValue);
        }

        @Override // java.util.concurrent.atomic.AtomicLongFieldUpdater
        public final void lazySet(T obj, long newValue) {
            accessCheck(obj);
            U.putOrderedLong(obj, this.offset, newValue);
        }

        @Override // java.util.concurrent.atomic.AtomicLongFieldUpdater
        public final long get(T obj) {
            accessCheck(obj);
            return U.getLongVolatile(obj, this.offset);
        }

        @Override // java.util.concurrent.atomic.AtomicLongFieldUpdater
        public final long getAndSet(T obj, long newValue) {
            accessCheck(obj);
            return U.getAndSetLong(obj, this.offset, newValue);
        }

        @Override // java.util.concurrent.atomic.AtomicLongFieldUpdater
        public final long getAndAdd(T obj, long delta) {
            accessCheck(obj);
            return U.getAndAddLong(obj, this.offset, delta);
        }

        @Override // java.util.concurrent.atomic.AtomicLongFieldUpdater
        public final long getAndIncrement(T obj) {
            return getAndAdd(obj, 1);
        }

        @Override // java.util.concurrent.atomic.AtomicLongFieldUpdater
        public final long getAndDecrement(T obj) {
            return getAndAdd(obj, -1);
        }

        @Override // java.util.concurrent.atomic.AtomicLongFieldUpdater
        public final long incrementAndGet(T obj) {
            return getAndAdd(obj, 1) + 1;
        }

        @Override // java.util.concurrent.atomic.AtomicLongFieldUpdater
        public final long decrementAndGet(T obj) {
            return getAndAdd(obj, -1) - 1;
        }

        @Override // java.util.concurrent.atomic.AtomicLongFieldUpdater
        public final long addAndGet(T obj, long delta) {
            return getAndAdd(obj, delta) + delta;
        }
    }

    /* access modifiers changed from: private */
    public static final class LockedUpdater<T> extends AtomicLongFieldUpdater<T> {
        private static final Unsafe U = Unsafe.getUnsafe();
        private final Class<?> cclass;
        private final long offset;
        private final Class<T> tclass;

        /* JADX DEBUG: Multi-variable search result rejected for r2v9, resolved type: java.lang.Class<?> */
        /* JADX DEBUG: Multi-variable search result rejected for r2v12, resolved type: java.lang.Class<?> */
        /* JADX WARN: Multi-variable type inference failed */
        LockedUpdater(Class<T> tclass2, String fieldName, Class<?> caller) {
            try {
                Field field = tclass2.getDeclaredField(fieldName);
                int modifiers = field.getModifiers();
                ReflectUtil.ensureMemberAccess(caller, tclass2, null, modifiers);
                if (field.getType() != Long.TYPE) {
                    throw new IllegalArgumentException("Must be long type");
                } else if (Modifier.isVolatile(modifiers)) {
                    this.cclass = Modifier.isProtected(modifiers) ? caller : tclass2;
                    this.tclass = tclass2;
                    this.offset = U.objectFieldOffset(field);
                } else {
                    throw new IllegalArgumentException("Must be volatile type");
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }

        private final void accessCheck(T obj) {
            if (!this.cclass.isInstance(obj)) {
                throw accessCheckException(obj);
            }
        }

        private final RuntimeException accessCheckException(T obj) {
            if (this.cclass == this.tclass) {
                return new ClassCastException();
            }
            return new RuntimeException(new IllegalAccessException("Class " + this.cclass.getName() + " can not access a protected member of class " + this.tclass.getName() + " using an instance of " + obj.getClass().getName()));
        }

        @Override // java.util.concurrent.atomic.AtomicLongFieldUpdater
        public final boolean compareAndSet(T obj, long expect, long update) {
            accessCheck(obj);
            synchronized (this) {
                if (U.getLong(obj, this.offset) != expect) {
                    return false;
                }
                U.putLong(obj, this.offset, update);
                return true;
            }
        }

        @Override // java.util.concurrent.atomic.AtomicLongFieldUpdater
        public final boolean weakCompareAndSet(T obj, long expect, long update) {
            return compareAndSet(obj, expect, update);
        }

        @Override // java.util.concurrent.atomic.AtomicLongFieldUpdater
        public final void set(T obj, long newValue) {
            accessCheck(obj);
            synchronized (this) {
                U.putLong(obj, this.offset, newValue);
            }
        }

        @Override // java.util.concurrent.atomic.AtomicLongFieldUpdater
        public final void lazySet(T obj, long newValue) {
            set(obj, newValue);
        }

        @Override // java.util.concurrent.atomic.AtomicLongFieldUpdater
        public final long get(T obj) {
            long j;
            accessCheck(obj);
            synchronized (this) {
                j = U.getLong(obj, this.offset);
            }
            return j;
        }
    }
}
