package java.lang.reflect;

import java.lang.annotation.Annotation;
import java.util.Comparator;
import libcore.reflect.Types;
import libcore.util.EmptyArray;
import sun.reflect.CallerSensitive;

public final class Method extends Executable {
    public static final Comparator<Method> ORDER_BY_SIGNATURE = new Comparator<Method>() {
        /* class java.lang.reflect.Method.AnonymousClass1 */

        public int compare(Method a, Method b) {
            if (a == b) {
                return 0;
            }
            int comparison = a.getName().compareTo(b.getName());
            if (comparison != 0) {
                return comparison;
            }
            int comparison2 = a.compareMethodParametersInternal(b);
            if (comparison2 != 0) {
                return comparison2;
            }
            Class<?> aReturnType = a.getReturnType();
            Class<?> bReturnType = b.getReturnType();
            if (aReturnType == bReturnType) {
                return 0;
            }
            return aReturnType.getName().compareTo(bReturnType.getName());
        }
    };

    public native Object getDefaultValue();

    @Override // java.lang.reflect.Executable
    public native Class<?>[] getExceptionTypes();

    @CallerSensitive
    public native Object invoke(Object obj, Object... objArr) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException;

    private Method() {
    }

    /* access modifiers changed from: package-private */
    @Override // java.lang.reflect.Executable
    public boolean hasGenericInformation() {
        return super.hasGenericInformationInternal();
    }

    @Override // java.lang.reflect.Member, java.lang.reflect.Executable
    public Class<?> getDeclaringClass() {
        return super.getDeclaringClassInternal();
    }

    @Override // java.lang.reflect.Member, java.lang.reflect.Executable
    public String getName() {
        return getMethodNameInternal();
    }

    @Override // java.lang.reflect.Member, java.lang.reflect.Executable
    public int getModifiers() {
        return super.getModifiersInternal();
    }

    @Override // java.lang.reflect.GenericDeclaration, java.lang.reflect.Executable
    public TypeVariable<Method>[] getTypeParameters() {
        return (TypeVariable[]) getMethodOrConstructorGenericInfoInternal().formalTypeParameters.clone();
    }

    public Class<?> getReturnType() {
        return getMethodReturnTypeInternal();
    }

    public Type getGenericReturnType() {
        return Types.getType(getMethodOrConstructorGenericInfoInternal().genericReturnType);
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
        if (obj != null && (obj instanceof Method)) {
            Method other = (Method) obj;
            if (getDeclaringClass() == other.getDeclaringClass() && getName() == other.getName() && getReturnType().equals(other.getReturnType())) {
                return equalParamTypes(getParameterTypes(), other.getParameterTypes());
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return getDeclaringClass().getName().hashCode() ^ getName().hashCode();
    }

    public String toString() {
        return sharedToString(Modifier.methodModifiers(), isDefault(), getParameterTypes(), getExceptionTypes());
    }

    /* access modifiers changed from: package-private */
    @Override // java.lang.reflect.Executable
    public void specificToStringHeader(StringBuilder sb) {
        sb.append(getReturnType().getTypeName());
        sb.append(' ');
        sb.append(getDeclaringClass().getTypeName());
        sb.append('.');
        sb.append(getName());
    }

    @Override // java.lang.reflect.Executable
    public String toGenericString() {
        return sharedToGenericString(Modifier.methodModifiers(), isDefault());
    }

    /* access modifiers changed from: package-private */
    @Override // java.lang.reflect.Executable
    public void specificToGenericStringHeader(StringBuilder sb) {
        sb.append(getGenericReturnType().getTypeName());
        sb.append(' ');
        sb.append(getDeclaringClass().getTypeName());
        sb.append('.');
        sb.append(getName());
    }

    public boolean isBridge() {
        return super.isBridgeMethodInternal();
    }

    @Override // java.lang.reflect.Executable
    public boolean isVarArgs() {
        return super.isVarArgs();
    }

    @Override // java.lang.reflect.Member, java.lang.reflect.Executable
    public boolean isSynthetic() {
        return super.isSynthetic();
    }

    public boolean isDefault() {
        return super.isDefaultMethodInternal();
    }

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

    /* access modifiers changed from: package-private */
    public boolean equalNameAndParameters(Method m) {
        return equalNameAndParametersInternal(m);
    }
}
