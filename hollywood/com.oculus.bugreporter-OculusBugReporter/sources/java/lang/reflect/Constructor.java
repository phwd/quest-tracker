package java.lang.reflect;

import java.lang.annotation.Annotation;
import java.util.Comparator;
import libcore.util.EmptyArray;
import sun.reflect.CallerSensitive;

public final class Constructor<T> extends Executable {
    private static final Comparator<Method> ORDER_BY_SIGNATURE = null;
    private final Class<?> serializationClass;
    private final Class<?> serializationCtor;

    private native T newInstance0(Object... objArr) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;

    private static native Object newInstanceFromSerialization(Class<?> cls, Class<?> cls2) throws InstantiationException, IllegalArgumentException, InvocationTargetException;

    @Override // java.lang.reflect.Executable
    public native Class<?>[] getExceptionTypes();

    private Constructor() {
        this(null, null);
    }

    private Constructor(Class<?> serializationCtor2, Class<?> serializationClass2) {
        this.serializationCtor = serializationCtor2;
        this.serializationClass = serializationClass2;
    }

    public Constructor<T> serializationCopy(Class<?> ctor, Class<?> cl) {
        return new Constructor<>(ctor, cl);
    }

    /* access modifiers changed from: package-private */
    @Override // java.lang.reflect.Executable
    public boolean hasGenericInformation() {
        return super.hasGenericInformationInternal();
    }

    @Override // java.lang.reflect.Member, java.lang.reflect.Executable
    public Class<T> getDeclaringClass() {
        return (Class<T>) super.getDeclaringClassInternal();
    }

    @Override // java.lang.reflect.Member, java.lang.reflect.Executable
    public String getName() {
        return getDeclaringClass().getName();
    }

    @Override // java.lang.reflect.Member, java.lang.reflect.Executable
    public int getModifiers() {
        return super.getModifiersInternal();
    }

    @Override // java.lang.reflect.GenericDeclaration, java.lang.reflect.Executable
    public TypeVariable<Constructor<T>>[] getTypeParameters() {
        return (TypeVariable[]) getMethodOrConstructorGenericInfoInternal().formalTypeParameters.clone();
    }

    @Override // java.lang.reflect.Executable
    public Class<?>[] getParameterTypes() {
        Class<?>[] paramTypes = super.getParameterTypesInternal();
        if (paramTypes == null) {
            return EmptyArray.CLASS;
        }
        return paramTypes;
    }

    @Override // java.lang.reflect.Executable
    public int getParameterCount() {
        return super.getParameterCountInternal();
    }

    @Override // java.lang.reflect.Executable
    public Type[] getGenericParameterTypes() {
        return super.getGenericParameterTypes();
    }

    @Override // java.lang.reflect.Executable
    public Type[] getGenericExceptionTypes() {
        return super.getGenericExceptionTypes();
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Constructor)) {
            return false;
        }
        Constructor<?> other = (Constructor) obj;
        if (getDeclaringClass() == other.getDeclaringClass()) {
            return equalParamTypes(getParameterTypes(), other.getParameterTypes());
        }
        return false;
    }

    public int hashCode() {
        return getDeclaringClass().getName().hashCode();
    }

    public String toString() {
        return sharedToString(Modifier.constructorModifiers(), false, getParameterTypes(), getExceptionTypes());
    }

    /* access modifiers changed from: package-private */
    @Override // java.lang.reflect.Executable
    public void specificToStringHeader(StringBuilder sb) {
        sb.append(getDeclaringClass().getTypeName());
    }

    @Override // java.lang.reflect.Executable
    public String toGenericString() {
        return sharedToGenericString(Modifier.constructorModifiers(), false);
    }

    /* access modifiers changed from: package-private */
    @Override // java.lang.reflect.Executable
    public void specificToGenericStringHeader(StringBuilder sb) {
        specificToStringHeader(sb);
    }

    @CallerSensitive
    public T newInstance(Object... initargs) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> cls = this.serializationClass;
        return cls == null ? newInstance0(initargs) : (T) newInstanceFromSerialization(this.serializationCtor, cls);
    }

    @Override // java.lang.reflect.Executable
    public boolean isVarArgs() {
        return super.isVarArgs();
    }

    @Override // java.lang.reflect.Member, java.lang.reflect.Executable
    public boolean isSynthetic() {
        return super.isSynthetic();
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: java.lang.Class<T> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.reflect.AccessibleObject, java.lang.reflect.Executable, java.lang.reflect.AnnotatedElement
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        return (T) super.getAnnotation(annotationClass);
    }

    @Override // java.lang.reflect.AccessibleObject, java.lang.reflect.Executable, java.lang.reflect.AnnotatedElement
    public Annotation[] getDeclaredAnnotations() {
        return super.getDeclaredAnnotations();
    }

    @Override // java.lang.reflect.Executable
    public Annotation[][] getParameterAnnotations() {
        return super.getParameterAnnotationsInternal();
    }
}
