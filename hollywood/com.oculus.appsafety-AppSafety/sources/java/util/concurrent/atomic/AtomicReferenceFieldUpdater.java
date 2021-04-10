package java.util.concurrent.atomic;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;
import sun.misc.Unsafe;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;
import sun.reflect.misc.ReflectUtil;

public abstract class AtomicReferenceFieldUpdater<T, V> {
    public abstract boolean compareAndSet(T t, V v, V v2);

    public abstract V get(T t);

    public abstract void lazySet(T t, V v);

    public abstract void set(T t, V v);

    public abstract boolean weakCompareAndSet(T t, V v, V v2);

    @CallerSensitive
    public static <U, W> AtomicReferenceFieldUpdater<U, W> newUpdater(Class<U> tclass, Class<W> vclass, String fieldName) {
        return new AtomicReferenceFieldUpdaterImpl(tclass, vclass, fieldName, Reflection.getCallerClass());
    }

    protected AtomicReferenceFieldUpdater() {
    }

    public V getAndSet(T obj, V newValue) {
        V prev;
        do {
            prev = get(obj);
        } while (!compareAndSet(obj, prev, newValue));
        return prev;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.util.concurrent.atomic.AtomicReferenceFieldUpdater<T, V> */
    /* JADX WARN: Multi-variable type inference failed */
    public final V getAndUpdate(T obj, UnaryOperator<V> updateFunction) {
        V prev;
        do {
            prev = (V) get(obj);
        } while (!compareAndSet(obj, prev, updateFunction.apply(prev)));
        return prev;
    }

    public final V updateAndGet(T obj, UnaryOperator<V> updateFunction) {
        V prev;
        V next;
        do {
            prev = get(obj);
            next = (V) updateFunction.apply(prev);
        } while (!compareAndSet(obj, prev, next));
        return next;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.util.concurrent.atomic.AtomicReferenceFieldUpdater<T, V> */
    /* JADX WARN: Multi-variable type inference failed */
    public final V getAndAccumulate(T obj, V x, BinaryOperator<V> accumulatorFunction) {
        V prev;
        do {
            prev = (V) get(obj);
        } while (!compareAndSet(obj, prev, accumulatorFunction.apply(prev, x)));
        return prev;
    }

    public final V accumulateAndGet(T obj, V x, BinaryOperator<V> accumulatorFunction) {
        V prev;
        V next;
        do {
            prev = get(obj);
            next = (V) accumulatorFunction.apply(prev, x);
        } while (!compareAndSet(obj, prev, next));
        return next;
    }

    /* access modifiers changed from: private */
    public static final class AtomicReferenceFieldUpdaterImpl<T, V> extends AtomicReferenceFieldUpdater<T, V> {
        private static final Unsafe U = Unsafe.getUnsafe();
        private final Class<?> cclass;
        private final long offset;
        private final Class<T> tclass;
        private final Class<V> vclass;

        /* JADX DEBUG: Multi-variable search result rejected for r3v6, resolved type: java.lang.Class<?> */
        /* JADX DEBUG: Multi-variable search result rejected for r3v9, resolved type: java.lang.Class<?> */
        /* JADX WARN: Multi-variable type inference failed */
        AtomicReferenceFieldUpdaterImpl(Class<T> tclass2, Class<V> vclass2, String fieldName, Class<?> caller) {
            try {
                Field field = tclass2.getDeclaredField(fieldName);
                int modifiers = field.getModifiers();
                ReflectUtil.ensureMemberAccess(caller, tclass2, null, modifiers);
                if (vclass2 != field.getType()) {
                    throw new ClassCastException();
                } else if (vclass2.isPrimitive()) {
                    throw new IllegalArgumentException("Must be reference type");
                } else if (Modifier.isVolatile(modifiers)) {
                    this.cclass = Modifier.isProtected(modifiers) ? caller : tclass2;
                    this.tclass = tclass2;
                    this.vclass = vclass2;
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

        private final void valueCheck(V v) {
            if (v != null && !this.vclass.isInstance(v)) {
                throwCCE();
            }
        }

        static void throwCCE() {
            throw new ClassCastException();
        }

        @Override // java.util.concurrent.atomic.AtomicReferenceFieldUpdater
        public final boolean compareAndSet(T obj, V expect, V update) {
            accessCheck(obj);
            valueCheck(update);
            return U.compareAndSwapObject(obj, this.offset, expect, update);
        }

        @Override // java.util.concurrent.atomic.AtomicReferenceFieldUpdater
        public final boolean weakCompareAndSet(T obj, V expect, V update) {
            accessCheck(obj);
            valueCheck(update);
            return U.compareAndSwapObject(obj, this.offset, expect, update);
        }

        @Override // java.util.concurrent.atomic.AtomicReferenceFieldUpdater
        public final void set(T obj, V newValue) {
            accessCheck(obj);
            valueCheck(newValue);
            U.putObjectVolatile(obj, this.offset, newValue);
        }

        @Override // java.util.concurrent.atomic.AtomicReferenceFieldUpdater
        public final void lazySet(T obj, V newValue) {
            accessCheck(obj);
            valueCheck(newValue);
            U.putOrderedObject(obj, this.offset, newValue);
        }

        @Override // java.util.concurrent.atomic.AtomicReferenceFieldUpdater
        public final V get(T obj) {
            accessCheck(obj);
            return (V) U.getObjectVolatile(obj, this.offset);
        }

        @Override // java.util.concurrent.atomic.AtomicReferenceFieldUpdater
        public final V getAndSet(T obj, V newValue) {
            accessCheck(obj);
            valueCheck(newValue);
            return (V) U.getAndSetObject(obj, this.offset, newValue);
        }
    }
}
