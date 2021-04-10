package java.lang.invoke;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.List;

public class MethodHandles {

    public final class Lookup {
        public static final int PACKAGE = 0;
        public static final int PRIVATE = 0;
        public static final int PROTECTED = 0;
        public static final int PUBLIC = 0;

        public MethodHandle bind(Object obj, String str, MethodType methodType) {
            return null;
        }

        public MethodHandle findConstructor(Class cls, MethodType methodType) {
            return null;
        }

        public MethodHandle findGetter(Class cls, String str, Class cls2) {
            return null;
        }

        public MethodHandle findSetter(Class cls, String str, Class cls2) {
            return null;
        }

        public MethodHandle findSpecial(Class cls, String str, MethodType methodType, Class cls2) {
            return null;
        }

        public MethodHandle findStatic(Class cls, String str, MethodType methodType) {
            return null;
        }

        public MethodHandle findStaticGetter(Class cls, String str, Class cls2) {
            return null;
        }

        public MethodHandle findStaticSetter(Class cls, String str, Class cls2) {
            return null;
        }

        public MethodHandle findVirtual(Class cls, String str, MethodType methodType) {
            return null;
        }

        public Lookup in(Class cls) {
            return null;
        }

        public Class lookupClass() {
            return null;
        }

        public int lookupModes() {
            return 0;
        }

        public MethodHandleInfo revealDirect(MethodHandle methodHandle) {
            return null;
        }

        public MethodHandle unreflect(Method method) {
            return null;
        }

        public MethodHandle unreflectConstructor(Constructor constructor) {
            return null;
        }

        public MethodHandle unreflectGetter(Field field) {
            return null;
        }

        public MethodHandle unreflectSetter(Field field) {
            return null;
        }

        public MethodHandle unreflectSpecial(Method method, Class cls) {
            return null;
        }
    }

    public static MethodHandle arrayElementGetter(Class cls) {
        return null;
    }

    public static MethodHandle arrayElementSetter(Class cls) {
        return null;
    }

    public static MethodHandle catchException(MethodHandle methodHandle, Class cls, MethodHandle methodHandle2) {
        return null;
    }

    public static MethodHandle collectArguments(MethodHandle methodHandle, int i, MethodHandle methodHandle2) {
        return null;
    }

    public static MethodHandle constant(Class cls, Object obj) {
        return null;
    }

    public static MethodHandle dropArguments(MethodHandle methodHandle, int i, List list) {
        return null;
    }

    public static MethodHandle dropArguments(MethodHandle methodHandle, int i, Class... clsArr) {
        return null;
    }

    public static MethodHandle exactInvoker(MethodType methodType) {
        return null;
    }

    public static MethodHandle explicitCastArguments(MethodHandle methodHandle, MethodType methodType) {
        return null;
    }

    public static MethodHandle filterArguments(MethodHandle methodHandle, int i, MethodHandle... methodHandleArr) {
        return null;
    }

    public static MethodHandle filterReturnValue(MethodHandle methodHandle, MethodHandle methodHandle2) {
        return null;
    }

    public static MethodHandle foldArguments(MethodHandle methodHandle, MethodHandle methodHandle2) {
        return null;
    }

    public static MethodHandle guardWithTest(MethodHandle methodHandle, MethodHandle methodHandle2, MethodHandle methodHandle3) {
        return null;
    }

    public static MethodHandle identity(Class cls) {
        return null;
    }

    public static MethodHandle insertArguments(MethodHandle methodHandle, int i, Object... objArr) {
        return null;
    }

    public static MethodHandle invoker(MethodType methodType) {
        return null;
    }

    public static Lookup lookup() {
        return null;
    }

    public static MethodHandle permuteArguments(MethodHandle methodHandle, MethodType methodType, int... iArr) {
        return null;
    }

    public static Lookup publicLookup() {
        return null;
    }

    public static Member reflectAs(Class cls, MethodHandle methodHandle) {
        return null;
    }

    public static MethodHandle spreadInvoker(MethodType methodType, int i) {
        return null;
    }

    public static MethodHandle throwException(Class cls, Class cls2) {
        return null;
    }
}
