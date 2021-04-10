package java.lang.reflect;

import libcore.reflect.GenericSignatureParser;
import libcore.reflect.ListOfTypes;

public abstract class Executable extends AccessibleObject implements Member, GenericDeclaration {
    private int accessFlags;
    private Class declaringClass;

    private static int fixMethodFlags(int i) {
        if ((i & 1024) != 0) {
            i &= -257;
        }
        int i2 = i & -33;
        if ((131072 & i2) != 0) {
            i2 |= 32;
        }
        return i2 & 65535;
    }

    private native String[] getSignatureAnnotation();

    /* access modifiers changed from: package-private */
    public native int compareMethodParametersInternal(Method method);

    public abstract Class getDeclaringClass();

    public abstract Class[] getExceptionTypes();

    /* access modifiers changed from: package-private */
    public final native String getMethodNameInternal();

    /* access modifiers changed from: package-private */
    public final native Class getMethodReturnTypeInternal();

    public abstract int getModifiers();

    /* access modifiers changed from: package-private */
    public final native Class[] getParameterTypesInternal();

    /* access modifiers changed from: package-private */
    public abstract void specificToStringHeader(StringBuilder sb);

    Executable() {
    }

    /* access modifiers changed from: package-private */
    public boolean equalParamTypes(Class[] clsArr, Class[] clsArr2) {
        if (clsArr.length != clsArr2.length) {
            return false;
        }
        for (int i = 0; i < clsArr.length; i++) {
            if (clsArr[i] != clsArr2[i]) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void separateWithCommas(Class[] clsArr, StringBuilder sb) {
        for (int i = 0; i < clsArr.length; i++) {
            sb.append(clsArr[i].getTypeName());
            if (i < clsArr.length - 1) {
                sb.append(",");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void printModifiersIfNonzero(StringBuilder sb, int i, boolean z) {
        int modifiers = getModifiers() & i;
        if (modifiers == 0 || z) {
            int i2 = modifiers & 7;
            if (i2 != 0) {
                sb.append(Modifier.toString(i2));
                sb.append(' ');
            }
            if (z) {
                sb.append("default ");
            }
            int i3 = modifiers & -8;
            if (i3 != 0) {
                sb.append(Modifier.toString(i3));
                sb.append(' ');
                return;
            }
            return;
        }
        sb.append(Modifier.toString(modifiers));
        sb.append(' ');
    }

    /* access modifiers changed from: package-private */
    public String sharedToString(int i, boolean z, Class[] clsArr, Class[] clsArr2) {
        try {
            StringBuilder sb = new StringBuilder();
            printModifiersIfNonzero(sb, i, z);
            specificToStringHeader(sb);
            sb.append('(');
            separateWithCommas(clsArr, sb);
            sb.append(')');
            if (clsArr2.length > 0) {
                sb.append(" throws ");
                separateWithCommas(clsArr2, sb);
            }
            return sb.toString();
        } catch (Exception e) {
            return "<" + e + ">";
        }
    }

    /* access modifiers changed from: package-private */
    public final int getModifiersInternal() {
        return fixMethodFlags(this.accessFlags);
    }

    /* access modifiers changed from: package-private */
    public final Class getDeclaringClassInternal() {
        return this.declaringClass;
    }

    public final int getAccessFlags() {
        return this.accessFlags;
    }

    /* access modifiers changed from: package-private */
    public static final class GenericInfo {
        final TypeVariable[] formalTypeParameters;
        final ListOfTypes genericExceptionTypes;
        final ListOfTypes genericParameterTypes;
        final Type genericReturnType;

        GenericInfo(ListOfTypes listOfTypes, ListOfTypes listOfTypes2, Type type, TypeVariable[] typeVariableArr) {
            this.genericExceptionTypes = listOfTypes;
            this.genericParameterTypes = listOfTypes2;
            this.genericReturnType = type;
            this.formalTypeParameters = typeVariableArr;
        }
    }

    /* access modifiers changed from: package-private */
    public final GenericInfo getMethodOrConstructorGenericInfoInternal() {
        String signatureAttribute = getSignatureAttribute();
        Class[] exceptionTypes = getExceptionTypes();
        GenericSignatureParser genericSignatureParser = new GenericSignatureParser(getDeclaringClass().getClassLoader());
        if (this instanceof Method) {
            genericSignatureParser.parseForMethod(this, signatureAttribute, exceptionTypes);
        } else {
            genericSignatureParser.parseForConstructor(this, signatureAttribute, exceptionTypes);
        }
        return new GenericInfo(genericSignatureParser.exceptionTypes, genericSignatureParser.parameterTypes, genericSignatureParser.returnType, genericSignatureParser.formalTypeParameters);
    }

    private String getSignatureAttribute() {
        String[] signatureAnnotation = getSignatureAnnotation();
        if (signatureAnnotation == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : signatureAnnotation) {
            sb.append(str);
        }
        return sb.toString();
    }

    /* access modifiers changed from: package-private */
    public final boolean isDefaultMethodInternal() {
        return (this.accessFlags & 4194304) != 0;
    }
}
