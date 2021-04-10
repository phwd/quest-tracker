package java.util.concurrent.atomic;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import sun.misc.Unsafe;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;
import sun.reflect.misc.ReflectUtil;

public abstract class AtomicIntegerFieldUpdater<T> {
    public abstract boolean compareAndSet(T t, int i, int i2);

    public abstract int get(T t);

    public abstract void lazySet(T t, int i);

    public abstract void set(T t, int i);

    public abstract boolean weakCompareAndSet(T t, int i, int i2);

    @CallerSensitive
    public static <U> AtomicIntegerFieldUpdater<U> newUpdater(Class<U> tclass, String fieldName) {
        return new AtomicIntegerFieldUpdaterImpl(tclass, fieldName, Reflection.getCallerClass());
    }

    protected AtomicIntegerFieldUpdater() {
    }

    public int getAndSet(T obj, int newValue) {
        int prev;
        do {
            prev = get(obj);
        } while (!compareAndSet(obj, prev, newValue));
        return prev;
    }

    public int getAndIncrement(T obj) {
        int prev;
        do {
            prev = get(obj);
        } while (!compareAndSet(obj, prev, prev + 1));
        return prev;
    }

    public int getAndDecrement(T obj) {
        int prev;
        do {
            prev = get(obj);
        } while (!compareAndSet(obj, prev, prev - 1));
        return prev;
    }

    public int getAndAdd(T obj, int delta) {
        int prev;
        do {
            prev = get(obj);
        } while (!compareAndSet(obj, prev, prev + delta));
        return prev;
    }

    public int incrementAndGet(T obj) {
        int prev;
        int next;
        do {
            prev = get(obj);
            next = prev + 1;
        } while (!compareAndSet(obj, prev, next));
        return next;
    }

    public int decrementAndGet(T obj) {
        int prev;
        int next;
        do {
            prev = get(obj);
            next = prev - 1;
        } while (!compareAndSet(obj, prev, next));
        return next;
    }

    public int addAndGet(T obj, int delta) {
        int prev;
        int next;
        do {
            prev = get(obj);
            next = prev + delta;
        } while (!compareAndSet(obj, prev, next));
        return next;
    }

    public final int getAndUpdate(T obj, IntUnaryOperator updateFunction) {
        int prev;
        do {
            prev = get(obj);
        } while (!compareAndSet(obj, prev, updateFunction.applyAsInt(prev)));
        return prev;
    }

    public final int updateAndGet(T obj, IntUnaryOperator updateFunction) {
        int prev;
        int next;
        do {
            prev = get(obj);
            next = updateFunction.applyAsInt(prev);
        } while (!compareAndSet(obj, prev, next));
        return next;
    }

    public final int getAndAccumulate(T obj, int x, IntBinaryOperator accumulatorFunction) {
        int prev;
        do {
            prev = get(obj);
        } while (!compareAndSet(obj, prev, accumulatorFunction.applyAsInt(prev, x)));
        return prev;
    }

    public final int accumulateAndGet(T obj, int x, IntBinaryOperator accumulatorFunction) {
        int prev;
        int next;
        do {
            prev = get(obj);
            next = accumulatorFunction.applyAsInt(prev, x);
        } while (!compareAndSet(obj, prev, next));
        return next;
    }

    private static final class AtomicIntegerFieldUpdaterImpl<T> extends AtomicIntegerFieldUpdater<T> {
        private static final Unsafe U = Unsafe.getUnsafe();
        private final Class<?> cclass;
        private final long offset;
        private final Class<T> tclass;

        /* JADX DEBUG: Multi-variable search result rejected for r2v6, resolved type: java.lang.Class<?> */
        /* JADX DEBUG: Multi-variable search result rejected for r2v9, resolved type: java.lang.Class<?> */
        /* JADX WARN: Multi-variable type inference failed */
        AtomicIntegerFieldUpdaterImpl(Class<T> tclass2, String fieldName, Class<?> caller) {
            try {
                Field field = tclass2.getDeclaredField(fieldName);
                int modifiers = field.getModifiers();
                ReflectUtil.ensureMemberAccess(caller, tclass2, null, modifiers);
                if (field.getType() != Integer.TYPE) {
                    throw new IllegalArgumentException("Must be integer type");
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

        @Override // java.util.concurrent.atomic.AtomicIntegerFieldUpdater
        public final boolean compareAndSet(T obj, int expect, int update) {
            accessCheck(obj);
            return U.compareAndSwapInt(obj, this.offset, expect, update);
        }

        @Override // java.util.concurrent.atomic.AtomicIntegerFieldUpdater
        public final boolean weakCompareAndSet(T obj, int expect, int update) {
            accessCheck(obj);
            return U.compareAndSwapInt(obj, this.offset, expect, update);
        }

        @Override // java.util.concurrent.atomic.AtomicIntegerFieldUpdater
        public final void set(T obj, int newValue) {
            accessCheck(obj);
            U.putIntVolatile(obj, this.offset, newValue);
        }

        @Override // java.util.concurrent.atomic.AtomicIntegerFieldUpdater
        public final void lazySet(T obj, int newValue) {
            accessCheck(obj);
            U.putOrderedInt(obj, this.offset, newValue);
        }

        @Override // java.util.concurrent.atomic.AtomicIntegerFieldUpdater
        public final int get(T obj) {
            accessCheck(obj);
            return U.getIntVolatile(obj, this.offset);
        }

        @Override // java.util.concurrent.atomic.AtomicIntegerFieldUpdater
        public final int getAndSet(T obj, int newValue) {
            accessCheck(obj);
            return U.getAndSetInt(obj, this.offset, newValue);
        }

        @Override // java.util.concurrent.atomic.AtomicIntegerFieldUpdater
        public final int getAndAdd(T obj, int delta) {
            accessCheck(obj);
            return U.getAndAddInt(obj, this.offset, delta);
        }

        @Override // java.util.concurrent.atomic.AtomicIntegerFieldUpdater
        public final int getAndIncrement(T obj) {
            return getAndAdd(obj, 1);
        }

        @Override // java.util.concurrent.atomic.AtomicIntegerFieldUpdater
        public final int getAndDecrement(T obj) {
            return getAndAdd(obj, -1);
        }

        @Override // java.util.concurrent.atomic.AtomicIntegerFieldUpdater
        public final int incrementAndGet(T obj) {
            return getAndAdd(obj, 1) + 1;
        }

        @Override // java.util.concurrent.atomic.AtomicIntegerFieldUpdater
        public final int decrementAndGet(T obj) {
            return getAndAdd(obj, -1) - 1;
        }

        @Override // java.util.concurrent.atomic.AtomicIntegerFieldUpdater
        public final int addAndGet(T obj, int delta) {
            return getAndAdd(obj, delta) + delta;
        }
    }
}
