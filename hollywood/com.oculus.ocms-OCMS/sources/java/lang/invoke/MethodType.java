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

    public MethodType appendParameterTypes(List<Class<?>> list) {
        return null;
    }

    public MethodType appendParameterTypes(Class<?>... clsArr) {
        return null;
    }

    public MethodType changeParameterType(int i, Class<?> cls) {
        return null;
    }

    public MethodType changeReturnType(Class<?> cls) {
        return null;
    }

    public MethodType dropParameterTypes(int i, int i2) {
        return null;
    }

    public MethodType erase() {
        return null;
    }

    public MethodType generic() {
        return null;
    }

    public boolean hasPrimitives() {
        return false;
    }

    public boolean hasWrappers() {
        return false;
    }

    public MethodType insertParameterTypes(int i, List<Class<?>> list) {
        return null;
    }

    public MethodType insertParameterTypes(int i, Class<?>... clsArr) {
        return null;
    }

    public Class<?>[] parameterArray() {
        return null;
    }

    public int parameterCount() {
        return 0;
    }

    public List<Class<?>> parameterList() {
        return null;
    }

    public Class<?> parameterType(int i) {
        return null;
    }

    public Class<?> returnType() {
        return null;
    }

    public String toMethodDescriptorString() {
        return null;
    }

    public MethodType unwrap() {
        return null;
    }

    public MethodType wrap() {
        return null;
    }
}
