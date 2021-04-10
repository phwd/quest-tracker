package java.lang;

import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import libcore.reflect.GenericSignatureParser;
import libcore.reflect.Types;
import libcore.util.BasicLruCache;
import libcore.util.EmptyArray;
import sun.reflect.Reflection;

public final class Class implements Serializable, GenericDeclaration, Type, AnnotatedElement {
    private static final long serialVersionUID = 3206093459760846163L;
    private transient int accessFlags;
    private transient ClassLoader classLoader;
    private transient Class componentType;
    private transient Object[] ifTable;
    private transient String name;
    private transient int primitiveType;
    private transient Class superClass;

    private static class Caches {
        private static final BasicLruCache genericInterfaces = new BasicLruCache(8);
    }

    static native Class classForName(String str, boolean z, ClassLoader classLoader2);

    private native Constructor getDeclaredConstructorInternal(Class[] clsArr);

    private native Constructor[] getDeclaredConstructorsInternal(boolean z);

    private native Method getDeclaredMethodInternal(String str, Class[] clsArr);

    private native Constructor getEnclosingConstructorNative();

    private native Method getEnclosingMethodNative();

    private native int getInnerClassFlags(int i);

    private native String getInnerClassName();

    private native Class[] getInterfacesInternal();

    private native String getNameNative();

    static native Class getPrimitiveClass(String str);

    private native Field[] getPublicDeclaredFields();

    private native Field getPublicFieldRecursive(String str);

    private native String[] getSignatureAnnotation();

    public native Class[] getDeclaredClasses();

    public native Field getDeclaredField(String str);

    public native Field[] getDeclaredFields();

    public native Class getDeclaringClass();

    public native Class getEnclosingClass();

    public native boolean isAnonymousClass();

    public native Object newInstance();

    private Class() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(isInterface() ? "interface " : isPrimitive() ? "" : "class ");
        sb.append(getName());
        return sb.toString();
    }

    public static Class forName(String str) {
        return forName(str, true, ClassLoader.getClassLoader(Reflection.getCallerClass()));
    }

    public static Class forName(String str, boolean z, ClassLoader classLoader2) {
        if (classLoader2 == null) {
            classLoader2 = BootClassLoader.getInstance();
        }
        try {
            return classForName(str, z, classLoader2);
        } catch (ClassNotFoundException e) {
            Throwable cause = e.getCause();
            if (cause instanceof LinkageError) {
                throw ((LinkageError) cause);
            }
            throw e;
        }
    }

    public boolean isInstance(Object obj) {
        if (obj == null) {
            return false;
        }
        return isAssignableFrom(obj.getClass());
    }

    public boolean isAssignableFrom(Class cls) {
        if (this == cls) {
            return true;
        }
        if (this == Object.class) {
            return !cls.isPrimitive();
        }
        if (isArray()) {
            if (!cls.isArray() || !this.componentType.isAssignableFrom(cls.componentType)) {
                return false;
            }
            return true;
        } else if (isInterface()) {
            Object[] objArr = cls.ifTable;
            if (objArr != null) {
                for (int i = 0; i < objArr.length; i += 2) {
                    if (objArr[i] == this) {
                        return true;
                    }
                }
            }
            return false;
        } else {
            if (!cls.isInterface()) {
                do {
                    cls = cls.superClass;
                    if (cls != null) {
                    }
                } while (cls != this);
                return true;
            }
            return false;
        }
    }

    public boolean isInterface() {
        return (this.accessFlags & 512) != 0;
    }

    public boolean isArray() {
        return getComponentType() != null;
    }

    public boolean isPrimitive() {
        return (this.primitiveType & 65535) != 0;
    }

    public String getName() {
        String str = this.name;
        if (str != null) {
            return str;
        }
        String nameNative = getNameNative();
        this.name = nameNative;
        return nameNative;
    }

    public ClassLoader getClassLoader() {
        if (isPrimitive()) {
            return null;
        }
        ClassLoader classLoader2 = this.classLoader;
        return classLoader2 == null ? BootClassLoader.getInstance() : classLoader2;
    }

    @Override // java.lang.reflect.GenericDeclaration
    public synchronized TypeVariable[] getTypeParameters() {
        String signatureAttribute = getSignatureAttribute();
        if (signatureAttribute == null) {
            return EmptyArray.TYPE_VARIABLE;
        }
        GenericSignatureParser genericSignatureParser = new GenericSignatureParser(getClassLoader());
        genericSignatureParser.parseForClass(this, signatureAttribute);
        return genericSignatureParser.formalTypeParameters;
    }

    public Class getSuperclass() {
        if (isInterface()) {
            return null;
        }
        return this.superClass;
    }

    public Package getPackage() {
        String packageName$;
        ClassLoader classLoader2 = getClassLoader();
        if (classLoader2 == null || (packageName$ = getPackageName$()) == null) {
            return null;
        }
        return classLoader2.getPackage(packageName$);
    }

    public String getPackageName$() {
        String name2 = getName();
        int lastIndexOf = name2.lastIndexOf(46);
        if (lastIndexOf == -1) {
            return null;
        }
        return name2.substring(0, lastIndexOf);
    }

    public Class[] getInterfaces() {
        if (isArray()) {
            return new Class[]{Cloneable.class, Serializable.class};
        }
        Class[] interfacesInternal = getInterfacesInternal();
        return interfacesInternal == null ? EmptyArray.CLASS : interfacesInternal;
    }

    public Type[] getGenericInterfaces() {
        Type[] typeArr;
        synchronized (Caches.genericInterfaces) {
            typeArr = (Type[]) Caches.genericInterfaces.get(this);
            if (typeArr == null) {
                String signatureAttribute = getSignatureAttribute();
                if (signatureAttribute == null) {
                    typeArr = getInterfaces();
                } else {
                    GenericSignatureParser genericSignatureParser = new GenericSignatureParser(getClassLoader());
                    genericSignatureParser.parseForClass(this, signatureAttribute);
                    typeArr = Types.getTypeArray(genericSignatureParser.interfaceTypes, false);
                }
                Caches.genericInterfaces.put(this, typeArr);
            }
        }
        return typeArr.length == 0 ? typeArr : (Type[]) typeArr.clone();
    }

    public Class getComponentType() {
        return this.componentType;
    }

    public int getModifiers() {
        if (!isArray()) {
            return getInnerClassFlags(this.accessFlags & 65535) & 65535;
        }
        int modifiers = getComponentType().getModifiers();
        if ((modifiers & 512) != 0) {
            modifiers &= -521;
        }
        return modifiers | 1040;
    }

    public Method getEnclosingMethod() {
        if (classNameImpliesTopLevel()) {
            return null;
        }
        return getEnclosingMethodNative();
    }

    public Constructor getEnclosingConstructor() {
        if (classNameImpliesTopLevel()) {
            return null;
        }
        return getEnclosingConstructorNative();
    }

    private boolean classNameImpliesTopLevel() {
        return !getName().contains("$");
    }

    public String getSimpleName() {
        if (isArray()) {
            return getComponentType().getSimpleName() + "[]";
        } else if (isAnonymousClass()) {
            return "";
        } else {
            if (isMemberClass() || isLocalClass()) {
                return getInnerClassName();
            }
            String name2 = getName();
            return name2.lastIndexOf(".") > 0 ? name2.substring(name2.lastIndexOf(".") + 1) : name2;
        }
    }

    public String getTypeName() {
        if (isArray()) {
            Class cls = this;
            int i = 0;
            while (cls.isArray()) {
                try {
                    i++;
                    cls = cls.getComponentType();
                } catch (Throwable unused) {
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(cls.getName());
            for (int i2 = 0; i2 < i; i2++) {
                sb.append("[]");
            }
            return sb.toString();
        }
        return getName();
    }

    public String getCanonicalName() {
        if (isArray()) {
            String canonicalName = getComponentType().getCanonicalName();
            if (canonicalName == null) {
                return null;
            }
            return canonicalName + "[]";
        } else if (isLocalOrAnonymousClass()) {
            return null;
        } else {
            Class enclosingClass = getEnclosingClass();
            if (enclosingClass == null) {
                return getName();
            }
            String canonicalName2 = enclosingClass.getCanonicalName();
            if (canonicalName2 == null) {
                return null;
            }
            return canonicalName2 + "." + getSimpleName();
        }
    }

    public boolean isLocalClass() {
        return !(getEnclosingMethod() == null && getEnclosingConstructor() == null) && !isAnonymousClass();
    }

    public boolean isMemberClass() {
        return getDeclaringClass() != null;
    }

    private boolean isLocalOrAnonymousClass() {
        return isLocalClass() || isAnonymousClass();
    }

    public Field[] getFields() {
        ArrayList arrayList = new ArrayList();
        getPublicFieldsRecursive(arrayList);
        return (Field[]) arrayList.toArray(new Field[arrayList.size()]);
    }

    private void getPublicFieldsRecursive(List list) {
        for (Class cls = this; cls != null; cls = cls.superClass) {
            Collections.addAll(list, cls.getPublicDeclaredFields());
        }
        Object[] objArr = this.ifTable;
        if (objArr != null) {
            for (int i = 0; i < objArr.length; i += 2) {
                Collections.addAll(list, ((Class) objArr[i]).getPublicDeclaredFields());
            }
        }
    }

    public Constructor[] getConstructors() {
        return getDeclaredConstructorsInternal(true);
    }

    public Field getField(String str) {
        if (str != null) {
            Field publicFieldRecursive = getPublicFieldRecursive(str);
            if (publicFieldRecursive != null) {
                return publicFieldRecursive;
            }
            throw new NoSuchFieldException(str);
        }
        throw new NullPointerException("name == null");
    }

    public Method getMethod(String str, Class... clsArr) {
        return getMethod(str, clsArr, true);
    }

    public Constructor getConstructor(Class... clsArr) {
        return getConstructor0(clsArr, 0);
    }

    public Method getDeclaredMethod(String str, Class... clsArr) {
        return getMethod(str, clsArr, false);
    }

    private Method getMethod(String str, Class[] clsArr, boolean z) {
        Method method;
        if (str != null) {
            if (clsArr == null) {
                clsArr = EmptyArray.CLASS;
            }
            for (Class cls : clsArr) {
                if (cls == null) {
                    throw new NoSuchMethodException("parameter type is null");
                }
            }
            if (z) {
                method = getPublicMethodRecursive(str, clsArr);
            } else {
                method = getDeclaredMethodInternal(str, clsArr);
            }
            if (method != null && (!z || Modifier.isPublic(method.getAccessFlags()))) {
                return method;
            }
            throw new NoSuchMethodException(getName() + "." + str + " " + Arrays.toString(clsArr));
        }
        throw new NullPointerException("name == null");
    }

    private Method getPublicMethodRecursive(String str, Class[] clsArr) {
        for (Class cls = this; cls != null; cls = cls.getSuperclass()) {
            Method declaredMethodInternal = cls.getDeclaredMethodInternal(str, clsArr);
            if (declaredMethodInternal != null && Modifier.isPublic(declaredMethodInternal.getAccessFlags())) {
                return declaredMethodInternal;
            }
        }
        return findInterfaceMethod(str, clsArr);
    }

    private Method findInterfaceMethod(String str, Class[] clsArr) {
        Object[] objArr = this.ifTable;
        if (objArr == null) {
            return null;
        }
        for (int length = objArr.length - 2; length >= 0; length -= 2) {
            Method publicMethodRecursive = ((Class) objArr[length]).getPublicMethodRecursive(str, clsArr);
            if (publicMethodRecursive != null && Modifier.isPublic(publicMethodRecursive.getAccessFlags())) {
                return publicMethodRecursive;
            }
        }
        return null;
    }

    public Constructor getDeclaredConstructor(Class... clsArr) {
        return getConstructor0(clsArr, 1);
    }

    public InputStream getResourceAsStream(String str) {
        String resolveName = resolveName(str);
        ClassLoader classLoader2 = getClassLoader();
        if (classLoader2 == null) {
            return ClassLoader.getSystemResourceAsStream(resolveName);
        }
        return classLoader2.getResourceAsStream(resolveName);
    }

    private String resolveName(String str) {
        if (str == null) {
            return str;
        }
        if (str.startsWith("/")) {
            return str.substring(1);
        }
        while (this.isArray()) {
            this = this.getComponentType();
        }
        String name2 = this.getName();
        int lastIndexOf = name2.lastIndexOf(46);
        if (lastIndexOf == -1) {
            return str;
        }
        return name2.substring(0, lastIndexOf).replace('.', '/') + "/" + str;
    }

    private Constructor getConstructor0(Class[] clsArr, int i) {
        if (clsArr == null) {
            clsArr = EmptyArray.CLASS;
        }
        for (Class cls : clsArr) {
            if (cls == null) {
                throw new NoSuchMethodException("parameter type is null");
            }
        }
        Constructor declaredConstructorInternal = getDeclaredConstructorInternal(clsArr);
        if (declaredConstructorInternal != null && (i != 0 || Modifier.isPublic(declaredConstructorInternal.getAccessFlags()))) {
            return declaredConstructorInternal;
        }
        throw new NoSuchMethodException(getName() + ".<init> " + Arrays.toString(clsArr));
    }

    public boolean isEnum() {
        return (getModifiers() & 16384) != 0 && getSuperclass() == Enum.class;
    }

    public Object[] getEnumConstants() {
        Object[] enumConstantsShared = getEnumConstantsShared();
        if (enumConstantsShared != null) {
            return (Object[]) enumConstantsShared.clone();
        }
        return null;
    }

    public Object[] getEnumConstantsShared() {
        if (!isEnum()) {
            return null;
        }
        return Enum.getSharedConstants(this);
    }

    public Object cast(Object obj) {
        if (obj == null || isInstance(obj)) {
            return obj;
        }
        throw new ClassCastException(cannotCastMsg(obj));
    }

    private String cannotCastMsg(Object obj) {
        return "Cannot cast " + obj.getClass().getName() + " to " + getName();
    }

    public Class asSubclass(Class cls) {
        if (cls.isAssignableFrom(this)) {
            return this;
        }
        throw new ClassCastException(toString() + " cannot be cast to " + cls.getName());
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

    public boolean isProxy() {
        return (this.accessFlags & 262144) != 0;
    }

    public int getAccessFlags() {
        return this.accessFlags;
    }
}
