package java.util.concurrent.atomic;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import sun.misc.Unsafe;
import sun.reflect.Reflection;
import sun.reflect.misc.ReflectUtil;

public abstract class AtomicReferenceFieldUpdater {
    public abstract boolean compareAndSet(Object obj, Object obj2, Object obj3);

    public static AtomicReferenceFieldUpdater newUpdater(Class cls, Class cls2, String str) {
        return new AtomicReferenceFieldUpdaterImpl(cls, cls2, str, Reflection.getCallerClass());
    }

    protected AtomicReferenceFieldUpdater() {
    }

    /* access modifiers changed from: private */
    public static final class AtomicReferenceFieldUpdaterImpl extends AtomicReferenceFieldUpdater {
        private static final Unsafe U = Unsafe.getUnsafe();
        private final Class cclass;
        private final long offset;
        private final Class tclass;
        private final Class vclass;

        AtomicReferenceFieldUpdaterImpl(Class cls, Class cls2, String str, Class cls3) {
            try {
                Field declaredField = cls.getDeclaredField(str);
                int modifiers = declaredField.getModifiers();
                ReflectUtil.ensureMemberAccess(cls3, cls, null, modifiers);
                if (cls2 != declaredField.getType()) {
                    throw new ClassCastException();
                } else if (cls2.isPrimitive()) {
                    throw new IllegalArgumentException("Must be reference type");
                } else if (Modifier.isVolatile(modifiers)) {
                    this.cclass = !Modifier.isProtected(modifiers) ? cls : cls3;
                    this.tclass = cls;
                    this.vclass = cls2;
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

        private final void valueCheck(Object obj) {
            if (obj != null && !this.vclass.isInstance(obj)) {
                throwCCE();
                throw null;
            }
        }

        static void throwCCE() {
            throw new ClassCastException();
        }

        @Override // java.util.concurrent.atomic.AtomicReferenceFieldUpdater
        public final boolean compareAndSet(Object obj, Object obj2, Object obj3) {
            accessCheck(obj);
            valueCheck(obj3);
            return U.compareAndSwapObject(obj, this.offset, obj2, obj3);
        }
    }
}
