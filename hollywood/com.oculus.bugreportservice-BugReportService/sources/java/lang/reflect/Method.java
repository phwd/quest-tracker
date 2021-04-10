package java.lang.reflect;

import java.util.Comparator;
import libcore.util.EmptyArray;

public final class Method extends Executable {
    public static final Comparator ORDER_BY_SIGNATURE = new Comparator() {
        /* class java.lang.reflect.Method.AnonymousClass1 */

        public int compare(Method method, Method method2) {
            if (method == method2) {
                return 0;
            }
            int compareTo = method.getName().compareTo(method2.getName());
            if (compareTo != 0 || (compareTo = method.compareMethodParametersInternal(method2)) != 0) {
                return compareTo;
            }
            Class returnType = method.getReturnType();
            Class returnType2 = method2.getReturnType();
            if (returnType == returnType2) {
                return 0;
            }
            return returnType.getName().compareTo(returnType2.getName());
        }
    };

    @Override // java.lang.reflect.Executable
    public native Class[] getExceptionTypes();

    public native Object invoke(Object obj, Object... objArr);

    private Method() {
    }

    @Override // java.lang.reflect.Executable
    public Class getDeclaringClass() {
        return super.getDeclaringClassInternal();
    }

    public String getName() {
        return getMethodNameInternal();
    }

    @Override // java.lang.reflect.Executable
    public int getModifiers() {
        return super.getModifiersInternal();
    }

    @Override // java.lang.reflect.GenericDeclaration
    public TypeVariable[] getTypeParameters() {
        return (TypeVariable[]) getMethodOrConstructorGenericInfoInternal().formalTypeParameters.clone();
    }

    public Class getReturnType() {
        return getMethodReturnTypeInternal();
    }

    public Class[] getParameterTypes() {
        Class[] parameterTypesInternal = super.getParameterTypesInternal();
        return parameterTypesInternal == null ? EmptyArray.CLASS : parameterTypesInternal;
    }

    public boolean equals(Object obj) {
        if (obj != null && (obj instanceof Method)) {
            Method method = (Method) obj;
            if (getDeclaringClass() == method.getDeclaringClass() && getName() == method.getName() && getReturnType().equals(method.getReturnType())) {
                return equalParamTypes(getParameterTypes(), method.getParameterTypes());
            }
            return false;
        }
        return false;
    }

    public int hashCode() {
        return getName().hashCode() ^ getDeclaringClass().getName().hashCode();
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

    public boolean isDefault() {
        return super.isDefaultMethodInternal();
    }
}
