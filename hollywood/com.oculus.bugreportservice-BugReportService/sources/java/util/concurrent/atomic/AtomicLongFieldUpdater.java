package java.util.concurrent.atomic;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import sun.misc.Unsafe;
import sun.reflect.Reflection;
import sun.reflect.misc.ReflectUtil;

public abstract class AtomicLongFieldUpdater {
    public abstract boolean compareAndSet(Object obj, long j, long j2);

    public abstract long get(Object obj);

    public static AtomicLongFieldUpdater newUpdater(Class cls, String str) {
        Class callerClass = Reflection.getCallerClass();
        if (AtomicLong.VM_SUPPORTS_LONG_CAS) {
            return new CASUpdater(cls, str, callerClass);
        }
        return new LockedUpdater(cls, str, callerClass);
    }

    protected AtomicLongFieldUpdater() {
    }

    public long incrementAndGet(Object obj) {
        long j;
        long j2;
        do {
            j = get(obj);
            j2 = j + 1;
        } while (!compareAndSet(obj, j, j2));
        return j2;
    }

    private static final class CASUpdater extends AtomicLongFieldUpdater {
        private static final Unsafe U = Unsafe.getUnsafe();
        private final Class cclass;
        private final long offset;
        private final Class tclass;

        CASUpdater(Class cls, String str, Class cls2) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                int modifiers = declaredField.getModifiers();
                ReflectUtil.ensureMemberAccess(cls2, cls, null, modifiers);
                if (declaredField.getType() != Long.TYPE) {
                    throw new IllegalArgumentException("Must be long type");
                } else if (Modifier.isVolatile(modifiers)) {
                    this.cclass = !Modifier.isProtected(modifiers) ? cls : cls2;
                    this.tclass = cls;
                    this.offset = U.objectFieldOffset(declaredField);
                } else {
                    throw new IllegalArgumentException("Must be volatile type");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private final void accessCheck(Object obj) {
            if (!this.cclass.isInstance(obj)) {
                throwAccessCheckException(obj);
                throw null;
            }
        }

        private final void throwAccessCheckException(Object obj) {
            if (this.cclass == this.tclass) {
                throw new ClassCastException();
            }
            throw new RuntimeException(new IllegalAccessException("Class " + this.cclass.getName() + " can not access a protected member of class " + this.tclass.getName() + " using an instance of " + obj.getClass().getName()));
        }

        @Override // java.util.concurrent.atomic.AtomicLongFieldUpdater
        public final boolean compareAndSet(Object obj, long j, long j2) {
            accessCheck(obj);
            return U.compareAndSwapLong(obj, this.offset, j, j2);
        }

        @Override // java.util.concurrent.atomic.AtomicLongFieldUpdater
        public final long get(Object obj) {
            accessCheck(obj);
            return U.getLongVolatile(obj, this.offset);
        }

        public final long getAndAdd(Object obj, long j) {
            accessCheck(obj);
            return U.getAndAddLong(obj, this.offset, j);
        }

        @Override // java.util.concurrent.atomic.AtomicLongFieldUpdater
        public final long incrementAndGet(Object obj) {
            return getAndAdd(obj, 1) + 1;
        }
    }

    private static final class LockedUpdater extends AtomicLongFieldUpdater {
        private static final Unsafe U = Unsafe.getUnsafe();
        private final Class cclass;
        private final long offset;
        private final Class tclass;

        LockedUpdater(Class cls, String str, Class cls2) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                int modifiers = declaredField.getModifiers();
                ReflectUtil.ensureMemberAccess(cls2, cls, null, modifiers);
                if (declaredField.getType() != Long.TYPE) {
                    throw new IllegalArgumentException("Must be long type");
                } else if (Modifier.isVolatile(modifiers)) {
                    this.cclass = !Modifier.isProtected(modifiers) ? cls : cls2;
                    this.tclass = cls;
                    this.offset = U.objectFieldOffset(declaredField);
                } else {
                    throw new IllegalArgumentException("Must be volatile type");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        private final void accessCheck(Object obj) {
            if (!this.cclass.isInstance(obj)) {
                throw accessCheckException(obj);
            }
        }

        private final RuntimeException accessCheckException(Object obj) {
            if (this.cclass == this.tclass) {
                return new ClassCastException();
            }
            return new RuntimeException(new IllegalAccessException("Class " + this.cclass.getName() + " can not access a protected member of class " + this.tclass.getName() + " using an instance of " + obj.getClass().getName()));
        }

        @Override // java.util.concurrent.atomic.AtomicLongFieldUpdater
        public final boolean compareAndSet(Object obj, long j, long j2) {
            accessCheck(obj);
            synchronized (this) {
                if (U.getLong(obj, this.offset) != j) {
                    return false;
                }
                U.putLong(obj, this.offset, j2);
                return true;
            }
        }

        @Override // java.util.concurrent.atomic.AtomicLongFieldUpdater
        public final long get(Object obj) {
            long j;
            accessCheck(obj);
            synchronized (this) {
                j = U.getLong(obj, this.offset);
            }
            return j;
        }
    }
}
