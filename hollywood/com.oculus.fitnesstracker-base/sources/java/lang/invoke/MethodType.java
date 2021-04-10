package java.lang.invoke;

import java.io.Serializable;
import java.util.List;

public final class MethodType implements Serializable {
    public static MethodType fromMethodDescriptorString(String str, ClassLoader classLoader) throws IllegalArgumentException, TypeNotPresentException {
        return null;
    }

    public static MethodType genericMethodType(int i) {
        return null;
    }

    public static MethodType genericMethodType(int i, boolean z) {
        return null;
    }

    public static MethodType methodType(Class<?> cls) {
        return null;
    }

    public static MethodType methodType(Class<?> cls, Class<?> cls2) {
        return null;
    }

    public static MethodType methodType(Class<?> cls, Class<?> cls2, Class<?>... clsArr) {
        return null;
    }

    public static MethodType methodType(Class<?> cls, MethodType methodType) {
        return null;
    }

    public static MethodType methodType(Class<?> cls, List<Class<?>> list) {
        return null;
    }

    public static MethodType methodType(Class<?> cls, Class<?>[] clsArr) {
        return null;
    }

    public final MethodType appendParameterTypes(List<Class<?>> list) {
        return null;
    }

    public final MethodType appendParameterTypes(Class<?>... clsArr) {
        return null;
    }

    public final MethodType changeParameterType(int i, Class<?> cls) {
        return null;
    }

    public final MethodType changeReturnType(Class<?> cls) {
        return null;
    }

    public final MethodType dropParameterTypes(int i, int i2) {
        return null;
    }

    public final MethodType erase() {
        return null;
    }

    public final MethodType generic() {
        return null;
    }

    public final boolean hasPrimitives() {
        return false;
    }

    public final boolean hasWrappers() {
        return false;
    }

    public final MethodType insertParameterTypes(int i, List<Class<?>> list) {
        return null;
    }

    public final MethodType insertParameterTypes(int i, Class<?>... clsArr) {
        return null;
    }

    public final Class<?>[] parameterArray() {
        return null;
    }

    public final int parameterCount() {
        return 0;
    }

    public final List<Class<?>> parameterList() {
        return null;
    }

    public final Class<?> parameterType(int i) {
        return null;
    }

    public final Class<?> returnType() {
        return null;
    }

    public final String toMethodDescriptorString() {
        return null;
    }

    public final MethodType unwrap() {
        return null;
    }

    public final MethodType wrap() {
        return null;
    }
}
