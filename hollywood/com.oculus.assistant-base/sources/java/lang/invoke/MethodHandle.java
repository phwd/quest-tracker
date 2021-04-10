package java.lang.invoke;

import java.util.List;

public abstract class MethodHandle {
    public MethodHandle asCollector(Class cls, int i) {
        return null;
    }

    public MethodHandle asFixedArity() {
        return null;
    }

    public MethodHandle asType(MethodType methodType) {
        return null;
    }

    public MethodHandle asVarargsCollector(Class cls) {
        return null;
    }

    public MethodHandle bindTo(Object obj) {
        return null;
    }

    public final Object invoke(Object... objArr) {
        return null;
    }

    public final Object invokeExact(Object... objArr) {
        return null;
    }

    public Object invokeWithArguments(List list) {
        return null;
    }

    public Object invokeWithArguments(Object... objArr) {
        return null;
    }

    public boolean isVarargsCollector() {
        return false;
    }

    public MethodType type() {
        return null;
    }
}
