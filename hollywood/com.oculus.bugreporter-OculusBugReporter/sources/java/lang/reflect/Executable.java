package java.lang.reflect;

import java.lang.annotation.Annotation;
import java.util.Objects;
import libcore.reflect.AnnotatedElements;
import libcore.reflect.GenericSignatureParser;
import libcore.reflect.ListOfTypes;
import libcore.reflect.Types;

public abstract class Executable extends AccessibleObject implements Member, GenericDeclaration {
    private int accessFlags;
    private long artMethod;
    private Class<?> declaringClass;
    private Class<?> declaringClassOfOverriddenMethod;
    private int dexMethodIndex;
    private volatile transient boolean hasRealParameterData;
    private volatile transient Parameter[] parameters;

    private native <T extends Annotation> T getAnnotationNative(Class<T> cls);

    private native Annotation[] getDeclaredAnnotationsNative();

    private native Annotation[][] getParameterAnnotationsNative();

    private native Parameter[] getParameters0();

    private native String[] getSignatureAnnotation();

    private native boolean isAnnotationPresentNative(Class<? extends Annotation> cls);

    /* access modifiers changed from: package-private */
    public native int compareMethodParametersInternal(Method method);

    @Override // java.lang.reflect.Member
    public abstract Class<?> getDeclaringClass();

    public abstract Class<?>[] getExceptionTypes();

    /* access modifiers changed from: package-private */
    public final native String getMethodNameInternal();

    /* access modifiers changed from: package-private */
    public final native Class<?> getMethodReturnTypeInternal();

    @Override // java.lang.reflect.Member
    public abstract int getModifiers();

    @Override // java.lang.reflect.Member
    public abstract String getName();

    public abstract Annotation[][] getParameterAnnotations();

    /* access modifiers changed from: package-private */
    public final native int getParameterCountInternal();

    public abstract Class<?>[] getParameterTypes();

    /* access modifiers changed from: package-private */
    public final native Class<?>[] getParameterTypesInternal();

    @Override // java.lang.reflect.GenericDeclaration
    public abstract TypeVariable<?>[] getTypeParameters();

    /* access modifiers changed from: package-private */
    public abstract boolean hasGenericInformation();

    /* access modifiers changed from: package-private */
    public abstract void specificToGenericStringHeader(StringBuilder sb);

    /* access modifiers changed from: package-private */
    public abstract void specificToStringHeader(StringBuilder sb);

    public abstract String toGenericString();

    Executable() {
    }

    /* access modifiers changed from: package-private */
    public boolean equalParamTypes(Class<?>[] params1, Class<?>[] params2) {
        if (params1.length != params2.length) {
            return false;
        }
        for (int i = 0; i < params1.length; i++) {
            if (params1[i] != params2[i]) {
                return false;
            }
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void separateWithCommas(Class<?>[] types, StringBuilder sb) {
        for (int j = 0; j < types.length; j++) {
            sb.append(types[j].getTypeName());
            if (j < types.length - 1) {
                sb.append(",");
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void printModifiersIfNonzero(StringBuilder sb, int mask, boolean isDefault) {
        int mod = getModifiers() & mask;
        if (mod == 0 || isDefault) {
            int access_mod = mod & 7;
            if (access_mod != 0) {
                sb.append(Modifier.toString(access_mod));
                sb.append(' ');
            }
            if (isDefault) {
                sb.append("default ");
            }
            int mod2 = mod & -8;
            if (mod2 != 0) {
                sb.append(Modifier.toString(mod2));
                sb.append(' ');
                return;
            }
            return;
        }
        sb.append(Modifier.toString(mod));
        sb.append(' ');
    }

    /* access modifiers changed from: package-private */
    public String sharedToString(int modifierMask, boolean isDefault, Class<?>[] parameterTypes, Class<?>[] exceptionTypes) {
        try {
            StringBuilder sb = new StringBuilder();
            printModifiersIfNonzero(sb, modifierMask, isDefault);
            specificToStringHeader(sb);
            sb.append('(');
            separateWithCommas(parameterTypes, sb);
            sb.append(')');
            if (exceptionTypes.length > 0) {
                sb.append(" throws ");
                separateWithCommas(exceptionTypes, sb);
            }
            return sb.toString();
        } catch (Exception e) {
            return "<" + ((Object) e) + ">";
        }
    }

    /* access modifiers changed from: package-private */
    public String sharedToGenericString(int modifierMask, boolean isDefault) {
        String str;
        try {
            StringBuilder sb = new StringBuilder();
            printModifiersIfNonzero(sb, modifierMask, isDefault);
            TypeVariable<?>[] typeparms = getTypeParameters();
            if (typeparms.length > 0) {
                sb.append('<');
                boolean first = true;
                for (TypeVariable<?> typeparm : typeparms) {
                    if (!first) {
                        sb.append(',');
                    }
                    sb.append(typeparm.toString());
                    first = false;
                }
                sb.append("> ");
            }
            specificToGenericStringHeader(sb);
            sb.append('(');
            Type[] params = getGenericParameterTypes();
            for (int j = 0; j < params.length; j++) {
                String param = params[j].getTypeName();
                if (isVarArgs() && j == params.length - 1) {
                    param = param.replaceFirst("\\[\\]$", "...");
                }
                sb.append(param);
                if (j < params.length - 1) {
                    sb.append(',');
                }
            }
            sb.append(')');
            Type[] exceptions = getGenericExceptionTypes();
            if (exceptions.length > 0) {
                sb.append(" throws ");
                for (int k = 0; k < exceptions.length; k++) {
                    if (exceptions[k] instanceof Class) {
                        str = ((Class) exceptions[k]).getName();
                    } else {
                        str = exceptions[k].toString();
                    }
                    sb.append(str);
                    if (k < exceptions.length - 1) {
                        sb.append(',');
                    }
                }
            }
            return sb.toString();
        } catch (Exception e) {
            return "<" + ((Object) e) + ">";
        }
    }

    public int getParameterCount() {
        throw new AbstractMethodError();
    }

    public Type[] getGenericParameterTypes() {
        return Types.getTypeArray(getMethodOrConstructorGenericInfoInternal().genericParameterTypes, false);
    }

    /* access modifiers changed from: package-private */
    public Type[] getAllGenericParameterTypes() {
        if (!hasGenericInformation()) {
            return getParameterTypes();
        }
        boolean realParamData = hasRealParameterData();
        Type[] genericParamTypes = getGenericParameterTypes();
        Type[] nonGenericParamTypes = getParameterTypes();
        Type[] out = new Type[nonGenericParamTypes.length];
        Parameter[] params = getParameters();
        int fromidx = 0;
        if (!realParamData) {
            return genericParamTypes.length == nonGenericParamTypes.length ? genericParamTypes : nonGenericParamTypes;
        }
        for (int i = 0; i < out.length; i++) {
            Parameter param = params[i];
            if (param.isSynthetic() || param.isImplicit()) {
                out[i] = nonGenericParamTypes[i];
            } else {
                out[i] = genericParamTypes[fromidx];
                fromidx++;
            }
        }
        return out;
    }

    public Parameter[] getParameters() {
        return (Parameter[]) privateGetParameters().clone();
    }

    private Parameter[] synthesizeAllParams() {
        int realparams = getParameterCount();
        Parameter[] out = new Parameter[realparams];
        for (int i = 0; i < realparams; i++) {
            out[i] = new Parameter("arg" + i, 0, this, i);
        }
        return out;
    }

    private void verifyParameters(Parameter[] parameters2) {
        if (getParameterTypes().length == parameters2.length) {
            for (Parameter parameter : parameters2) {
                String name = parameter.getRealName();
                int mods = parameter.getModifiers();
                if (name != null && (name.isEmpty() || name.indexOf(46) != -1 || name.indexOf(59) != -1 || name.indexOf(91) != -1 || name.indexOf(47) != -1)) {
                    throw new MalformedParametersException("Invalid parameter name \"" + name + "\"");
                } else if (mods != (36880 & mods)) {
                    throw new MalformedParametersException("Invalid parameter modifiers");
                }
            }
            return;
        }
        throw new MalformedParametersException("Wrong number of parameters in MethodParameters attribute");
    }

    private Parameter[] privateGetParameters() {
        Parameter[] tmp = this.parameters;
        if (tmp == null) {
            try {
                tmp = getParameters0();
                if (tmp == null) {
                    this.hasRealParameterData = false;
                    tmp = synthesizeAllParams();
                } else {
                    this.hasRealParameterData = true;
                    verifyParameters(tmp);
                }
                this.parameters = tmp;
            } catch (IllegalArgumentException e) {
                MalformedParametersException e2 = new MalformedParametersException("Invalid parameter metadata in class file");
                e2.initCause(e);
                throw e2;
            }
        }
        return tmp;
    }

    /* access modifiers changed from: package-private */
    public boolean hasRealParameterData() {
        if (this.parameters == null) {
            privateGetParameters();
        }
        return this.hasRealParameterData;
    }

    public Type[] getGenericExceptionTypes() {
        return Types.getTypeArray(getMethodOrConstructorGenericInfoInternal().genericExceptionTypes, false);
    }

    public boolean isVarArgs() {
        return (this.accessFlags & 128) != 0;
    }

    @Override // java.lang.reflect.Member
    public boolean isSynthetic() {
        return (this.accessFlags & 4096) != 0;
    }

    @Override // java.lang.reflect.AccessibleObject, java.lang.reflect.AnnotatedElement
    public <T extends Annotation> T getAnnotation(Class<T> annotationClass) {
        Objects.requireNonNull(annotationClass);
        return (T) getAnnotationNative(annotationClass);
    }

    @Override // java.lang.reflect.AccessibleObject, java.lang.reflect.AnnotatedElement
    public <T extends Annotation> T[] getAnnotationsByType(Class<T> annotationClass) {
        return (T[]) AnnotatedElements.getDirectOrIndirectAnnotationsByType(this, annotationClass);
    }

    @Override // java.lang.reflect.AccessibleObject, java.lang.reflect.AnnotatedElement
    public Annotation[] getDeclaredAnnotations() {
        return getDeclaredAnnotationsNative();
    }

    private static int fixMethodFlags(int flags) {
        if ((flags & 1024) != 0) {
            flags &= -257;
        }
        int flags2 = flags & -33;
        if ((flags2 & 131072) != 0) {
            flags2 |= 32;
        }
        return 65535 & flags2;
    }

    /* access modifiers changed from: package-private */
    public final int getModifiersInternal() {
        return fixMethodFlags(this.accessFlags);
    }

    /* access modifiers changed from: package-private */
    public final Class<?> getDeclaringClassInternal() {
        return this.declaringClass;
    }

    @Override // java.lang.reflect.AccessibleObject, java.lang.reflect.AnnotatedElement
    public final boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
        Objects.requireNonNull(annotationType);
        return isAnnotationPresentNative(annotationType);
    }

    /* access modifiers changed from: package-private */
    public final Annotation[][] getParameterAnnotationsInternal() {
        Annotation[][] parameterAnnotations = getParameterAnnotationsNative();
        if (parameterAnnotations == null) {
            return (Annotation[][]) Array.newInstance(Annotation.class, getParameterTypes().length, 0);
        }
        return parameterAnnotations;
    }

    public final int getAccessFlags() {
        return this.accessFlags;
    }

    public final long getArtMethod() {
        return this.artMethod;
    }

    /* access modifiers changed from: package-private */
    public static final class GenericInfo {
        final TypeVariable<?>[] formalTypeParameters;
        final ListOfTypes genericExceptionTypes;
        final ListOfTypes genericParameterTypes;
        final Type genericReturnType;

        GenericInfo(ListOfTypes exceptions, ListOfTypes parameters, Type ret, TypeVariable<?>[] formal) {
            this.genericExceptionTypes = exceptions;
            this.genericParameterTypes = parameters;
            this.genericReturnType = ret;
            this.formalTypeParameters = formal;
        }
    }

    /* access modifiers changed from: package-private */
    public final boolean hasGenericInformationInternal() {
        return getSignatureAnnotation() != null;
    }

    /* access modifiers changed from: package-private */
    public final GenericInfo getMethodOrConstructorGenericInfoInternal() {
        String signatureAttribute = getSignatureAttribute();
        Class<?>[] exceptionTypes = getExceptionTypes();
        GenericSignatureParser parser = new GenericSignatureParser(getDeclaringClass().getClassLoader());
        if (this instanceof Method) {
            parser.parseForMethod(this, signatureAttribute, exceptionTypes);
        } else {
            parser.parseForConstructor(this, signatureAttribute, exceptionTypes);
        }
        return new GenericInfo(parser.exceptionTypes, parser.parameterTypes, parser.returnType, parser.formalTypeParameters);
    }

    private String getSignatureAttribute() {
        String[] annotation = getSignatureAnnotation();
        if (annotation == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        for (String s : annotation) {
            result.append(s);
        }
        return result.toString();
    }

    /* access modifiers changed from: package-private */
    public final boolean equalNameAndParametersInternal(Method m) {
        return getName().equals(m.getName()) && compareMethodParametersInternal(m) == 0;
    }

    /* access modifiers changed from: package-private */
    public final boolean isDefaultMethodInternal() {
        return (this.accessFlags & 4194304) != 0;
    }

    /* access modifiers changed from: package-private */
    public final boolean isBridgeMethodInternal() {
        return (this.accessFlags & 64) != 0;
    }
}
