package java.lang.reflect;

import java.lang.annotation.Annotation;
import java.util.Objects;
import libcore.reflect.AnnotatedElements;

public final class Parameter implements AnnotatedElement {
    private final Executable executable;
    private final int index;
    private final int modifiers;
    private final String name;
    private volatile transient Class<?> parameterClassCache = null;
    private volatile transient Type parameterTypeCache = null;

    private static native <A extends Annotation> A getAnnotationNative(Executable executable2, int i, Class<A> cls);

    Parameter(String name2, int modifiers2, Executable executable2, int index2) {
        this.name = name2;
        this.modifiers = modifiers2;
        this.executable = executable2;
        this.index = index2;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Parameter)) {
            return false;
        }
        Parameter other = (Parameter) obj;
        if (!other.executable.equals(this.executable) || other.index != this.index) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return this.executable.hashCode() ^ this.index;
    }

    public boolean isNamePresent() {
        return this.executable.hasRealParameterData() && this.name != null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String typename = getParameterizedType().getTypeName();
        sb.append(Modifier.toString(getModifiers()));
        if (this.modifiers != 0) {
            sb.append(' ');
        }
        if (isVarArgs()) {
            sb.append(typename.replaceFirst("\\[\\]$", "..."));
        } else {
            sb.append(typename);
        }
        sb.append(' ');
        sb.append(getName());
        return sb.toString();
    }

    public Executable getDeclaringExecutable() {
        return this.executable;
    }

    public int getModifiers() {
        return this.modifiers;
    }

    public String getName() {
        String str = this.name;
        if (str != null && !str.equals("")) {
            return this.name;
        }
        return "arg" + this.index;
    }

    /* access modifiers changed from: package-private */
    public String getRealName() {
        return this.name;
    }

    public Type getParameterizedType() {
        Type tmp = this.parameterTypeCache;
        if (tmp != null) {
            return tmp;
        }
        Type tmp2 = this.executable.getAllGenericParameterTypes()[this.index];
        this.parameterTypeCache = tmp2;
        return tmp2;
    }

    public Class<?> getType() {
        Class<?> tmp = this.parameterClassCache;
        if (tmp != null) {
            return tmp;
        }
        Class<?> tmp2 = this.executable.getParameterTypes()[this.index];
        this.parameterClassCache = tmp2;
        return tmp2;
    }

    public boolean isImplicit() {
        return Modifier.isMandated(getModifiers());
    }

    public boolean isSynthetic() {
        return Modifier.isSynthetic(getModifiers());
    }

    public boolean isVarArgs() {
        if (!this.executable.isVarArgs() || this.index != this.executable.getParameterCount() - 1) {
            return false;
        }
        return true;
    }

    @Override // java.lang.reflect.AnnotatedElement
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        Objects.requireNonNull(annotationClass);
        return (T) getAnnotationNative(this.executable, this.index, annotationClass);
    }

    @Override // java.lang.reflect.AnnotatedElement
    public <T extends Annotation> T[] getAnnotationsByType(Class<T> annotationClass) {
        return (T[]) AnnotatedElements.getDirectOrIndirectAnnotationsByType(this, annotationClass);
    }

    @Override // java.lang.reflect.AnnotatedElement
    public Annotation[] getDeclaredAnnotations() {
        return this.executable.getParameterAnnotations()[this.index];
    }

    @Override // java.lang.reflect.AnnotatedElement
    public <T extends Annotation> T getDeclaredAnnotation(Class<T> annotationClass) {
        return (T) getAnnotation(annotationClass);
    }

    @Override // java.lang.reflect.AnnotatedElement
    public <T extends Annotation> T[] getDeclaredAnnotationsByType(Class<T> annotationClass) {
        return (T[]) getAnnotationsByType(annotationClass);
    }

    @Override // java.lang.reflect.AnnotatedElement
    public Annotation[] getAnnotations() {
        return getDeclaredAnnotations();
    }
}
