package java.lang.reflect;

import libcore.util.EmptyArray;

public final class Constructor extends Executable {
    private final Class serializationClass;
    private final Class serializationCtor;

    private native Object newInstance0(Object... objArr);

    private static native Object newInstanceFromSerialization(Class cls, Class cls2);

    @Override // java.lang.reflect.Executable
    public native Class[] getExceptionTypes();

    private Constructor() {
        this(null, null);
    }

    private Constructor(Class cls, Class cls2) {
        this.serializationCtor = cls;
        this.serializationClass = cls2;
    }

    @Override // java.lang.reflect.Executable
    public Class getDeclaringClass() {
        return super.getDeclaringClassInternal();
    }

    @Override // java.lang.reflect.Executable
    public int getModifiers() {
        return super.getModifiersInternal();
    }

    @Override // java.lang.reflect.GenericDeclaration
    public TypeVariable[] getTypeParameters() {
        return (TypeVariable[]) getMethodOrConstructorGenericInfoInternal().formalTypeParameters.clone();
    }

    public Class[] getParameterTypes() {
        Class[] parameterTypesInternal = super.getParameterTypesInternal();
        return parameterTypesInternal == null ? EmptyArray.CLASS : parameterTypesInternal;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Constructor)) {
            return false;
        }
        Constructor constructor = (Constructor) obj;
        if (getDeclaringClass() == constructor.getDeclaringClass()) {
            return equalParamTypes(getParameterTypes(), constructor.getParameterTypes());
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

    public Object newInstance(Object... objArr) {
        Class cls = this.serializationClass;
        if (cls == null) {
            return newInstance0(objArr);
        }
        return newInstanceFromSerialization(this.serializationCtor, cls);
    }
}
