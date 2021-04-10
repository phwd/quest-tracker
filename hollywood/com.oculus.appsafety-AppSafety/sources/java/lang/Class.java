package java.lang;

import android.icu.impl.number.Padder;
import android.icu.text.DateTimePatternGenerator;
import dalvik.system.ClassExt;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.net.URL;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import libcore.reflect.GenericSignatureParser;
import libcore.reflect.Types;
import libcore.util.BasicLruCache;
import libcore.util.CollectionUtils;
import libcore.util.EmptyArray;
import sun.reflect.CallerSensitive;
import sun.reflect.Reflection;

public final class Class<T> implements Serializable, GenericDeclaration, Type, AnnotatedElement {
    private static final int ANNOTATION = 8192;
    private static final int ENUM = 16384;
    private static final int FINALIZABLE = Integer.MIN_VALUE;
    private static final int SYNTHETIC = 4096;
    private static final long serialVersionUID = 3206093459760846163L;
    private transient int accessFlags;
    private transient int classFlags;
    private transient ClassLoader classLoader;
    private transient int classSize;
    private transient int clinitThreadId;
    private transient Class<?> componentType;
    private transient short copiedMethodsOffset;
    private transient Object dexCache;
    private transient int dexClassDefIndex;
    private volatile transient int dexTypeIndex;
    private transient ClassExt extData;
    private transient long iFields;
    private transient Object[] ifTable;
    private transient long methods;
    private transient String name;
    private transient int numReferenceInstanceFields;
    private transient int numReferenceStaticFields;
    private transient int objectSize;
    private transient int objectSizeAllocFastPath;
    private transient int primitiveType;
    private transient int referenceInstanceOffsets;
    private transient long sFields;
    private transient int status;
    private transient Class<? super T> superClass;
    private transient short virtualMethodsOffset;
    private transient Object vtable;

    static native Class<?> classForName(String str, boolean z, ClassLoader classLoader2) throws ClassNotFoundException;

    private native Constructor<T> getDeclaredConstructorInternal(Class<?>[] clsArr);

    private native Constructor<?>[] getDeclaredConstructorsInternal(boolean z);

    private native Method getDeclaredMethodInternal(String str, Class<?>[] clsArr);

    private native Constructor<?> getEnclosingConstructorNative();

    private native Method getEnclosingMethodNative();

    private native int getInnerClassFlags(int i);

    private native String getInnerClassName();

    private native Class<?>[] getInterfacesInternal();

    private native String getNameNative();

    static native Class<?> getPrimitiveClass(String str);

    private native Field[] getPublicDeclaredFields();

    private native Field getPublicFieldRecursive(String str);

    private native String[] getSignatureAnnotation();

    private native boolean isDeclaredAnnotationPresent(Class<? extends Annotation> cls);

    @Override // java.lang.reflect.AnnotatedElement
    public native <A extends Annotation> A getDeclaredAnnotation(Class<A> cls);

    @Override // java.lang.reflect.AnnotatedElement
    public native Annotation[] getDeclaredAnnotations();

    public native Class<?>[] getDeclaredClasses();

    public native Field getDeclaredField(String str) throws NoSuchFieldException;

    public native Field[] getDeclaredFields();

    public native Field[] getDeclaredFieldsUnchecked(boolean z);

    public native Method[] getDeclaredMethodsUnchecked(boolean z);

    public native Class<?> getDeclaringClass();

    public native Class<?> getEnclosingClass();

    public native boolean isAnonymousClass();

    public native T newInstance() throws InstantiationException, IllegalAccessException;

    private Class() {
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(isInterface() ? "interface " : isPrimitive() ? "" : "class ");
        sb.append(getName());
        return sb.toString();
    }

    public String toGenericString() {
        if (isPrimitive()) {
            return toString();
        }
        StringBuilder sb = new StringBuilder();
        int modifiers = getModifiers() & Modifier.classModifiers();
        if (modifiers != 0) {
            sb.append(Modifier.toString(modifiers));
            sb.append(' ');
        }
        if (isAnnotation()) {
            sb.append('@');
        }
        if (isInterface()) {
            sb.append("interface");
        } else if (isEnum()) {
            sb.append("enum");
        } else {
            sb.append("class");
        }
        sb.append(' ');
        sb.append(getName());
        TypeVariable<?>[] typeparms = getTypeParameters();
        if (typeparms.length > 0) {
            boolean first = true;
            sb.append('<');
            for (TypeVariable<?> typeparm : typeparms) {
                if (!first) {
                    sb.append(',');
                }
                sb.append(typeparm.getTypeName());
                first = false;
            }
            sb.append('>');
        }
        return sb.toString();
    }

    @CallerSensitive
    public static Class<?> forName(String className) throws ClassNotFoundException {
        return forName(className, true, ClassLoader.getClassLoader(Reflection.getCallerClass()));
    }

    @CallerSensitive
    public static Class<?> forName(String name2, boolean initialize, ClassLoader loader) throws ClassNotFoundException {
        if (loader == null) {
            loader = BootClassLoader.getInstance();
        }
        try {
            return classForName(name2, initialize, loader);
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

    public boolean isAssignableFrom(Class<?> cls) {
        if (this == cls) {
            return true;
        }
        if (this == Object.class) {
            return true ^ cls.isPrimitive();
        }
        if (isArray()) {
            if (!cls.isArray() || !this.componentType.isAssignableFrom(cls.componentType)) {
                return false;
            }
            return true;
        } else if (isInterface()) {
            Object[] iftable = cls.ifTable;
            if (iftable != null) {
                for (int i = 0; i < iftable.length; i += 2) {
                    if (iftable[i] == this) {
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
        return (this.primitiveType & DateTimePatternGenerator.MATCH_ALL_FIELDS_LENGTH) != 0;
    }

    public boolean isFinalizable() {
        return (getModifiers() & Integer.MIN_VALUE) != 0;
    }

    public boolean isAnnotation() {
        return (getModifiers() & 8192) != 0;
    }

    public boolean isSynthetic() {
        return (getModifiers() & 4096) != 0;
    }

    public String getName() {
        String name2 = this.name;
        if (name2 != null) {
            return name2;
        }
        String name3 = getNameNative();
        this.name = name3;
        return name3;
    }

    public ClassLoader getClassLoader() {
        if (isPrimitive()) {
            return null;
        }
        ClassLoader classLoader2 = this.classLoader;
        return classLoader2 == null ? BootClassLoader.getInstance() : classLoader2;
    }

    @Override // java.lang.reflect.GenericDeclaration
    public synchronized TypeVariable<Class<T>>[] getTypeParameters() {
        String annotationSignature = getSignatureAttribute();
        if (annotationSignature == null) {
            return EmptyArray.TYPE_VARIABLE;
        }
        GenericSignatureParser parser = new GenericSignatureParser(getClassLoader());
        parser.parseForClass(this, annotationSignature);
        return parser.formalTypeParameters;
    }

    public Class<? super T> getSuperclass() {
        if (isInterface()) {
            return null;
        }
        return this.superClass;
    }

    public Type getGenericSuperclass() {
        Type genericSuperclass = getSuperclass();
        if (genericSuperclass == null) {
            return null;
        }
        String annotationSignature = getSignatureAttribute();
        if (annotationSignature != null) {
            GenericSignatureParser parser = new GenericSignatureParser(getClassLoader());
            parser.parseForClass(this, annotationSignature);
            genericSuperclass = parser.superclassType;
        }
        return Types.getType(genericSuperclass);
    }

    public Package getPackage() {
        String packageName;
        ClassLoader loader = getClassLoader();
        if (loader == null || (packageName = getPackageName$()) == null) {
            return null;
        }
        return loader.getPackage(packageName);
    }

    public String getPackageName$() {
        String name2 = getName();
        int last = name2.lastIndexOf(46);
        if (last == -1) {
            return null;
        }
        return name2.substring(0, last);
    }

    public Class<?>[] getInterfaces() {
        if (isArray()) {
            return new Class[]{Cloneable.class, Serializable.class};
        }
        Class<?>[] ifaces = getInterfacesInternal();
        if (ifaces == null) {
            return EmptyArray.CLASS;
        }
        return ifaces;
    }

    public Type[] getGenericInterfaces() {
        Type[] result;
        synchronized (Caches.genericInterfaces) {
            result = (Type[]) Caches.genericInterfaces.get(this);
            if (result == null) {
                String annotationSignature = getSignatureAttribute();
                if (annotationSignature == null) {
                    result = getInterfaces();
                } else {
                    GenericSignatureParser parser = new GenericSignatureParser(getClassLoader());
                    parser.parseForClass(this, annotationSignature);
                    result = Types.getTypeArray(parser.interfaceTypes, false);
                }
                Caches.genericInterfaces.put(this, result);
            }
        }
        return result.length == 0 ? result : (Type[]) result.clone();
    }

    public Class<?> getComponentType() {
        return this.componentType;
    }

    public int getModifiers() {
        if (!isArray()) {
            return getInnerClassFlags(this.accessFlags & DateTimePatternGenerator.MATCH_ALL_FIELDS_LENGTH) & DateTimePatternGenerator.MATCH_ALL_FIELDS_LENGTH;
        }
        int componentModifiers = getComponentType().getModifiers();
        if ((componentModifiers & 512) != 0) {
            componentModifiers &= -521;
        }
        return componentModifiers | 1040;
    }

    public Object[] getSigners() {
        return null;
    }

    public Method getEnclosingMethod() {
        if (classNameImpliesTopLevel()) {
            return null;
        }
        return getEnclosingMethodNative();
    }

    public Constructor<?> getEnclosingConstructor() {
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
            String simpleName = getName();
            if (simpleName.lastIndexOf(".") > 0) {
                return simpleName.substring(simpleName.lastIndexOf(".") + 1);
            }
            return simpleName;
        }
    }

    @Override // java.lang.reflect.Type
    public String getTypeName() {
        if (isArray()) {
            Class<T> cls = this;
            int dimensions = 0;
            while (cls.isArray()) {
                try {
                    dimensions++;
                    cls = cls.getComponentType();
                } catch (Throwable th) {
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(cls.getName());
            for (int i = 0; i < dimensions; i++) {
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
            Class<?> enclosingClass = getEnclosingClass();
            if (enclosingClass == null) {
                return getName();
            }
            String enclosingName = enclosingClass.getCanonicalName();
            if (enclosingName == null) {
                return null;
            }
            return enclosingName + "." + getSimpleName();
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

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: java.util.ArrayList */
    /* JADX WARN: Multi-variable type inference failed */
    @CallerSensitive
    public Class<?>[] getClasses() {
        List<Class<?>> result = new ArrayList<>();
        for (Class<? super T> cls = this; cls != null; cls = cls.superClass) {
            Class<?>[] declaredClasses = cls.getDeclaredClasses();
            for (Class<?> member : declaredClasses) {
                if (Modifier.isPublic(member.getModifiers())) {
                    result.add(member);
                }
            }
        }
        return (Class[]) result.toArray(new Class[result.size()]);
    }

    @CallerSensitive
    public Field[] getFields() throws SecurityException {
        List<Field> fields = new ArrayList<>();
        getPublicFieldsRecursive(fields);
        return (Field[]) fields.toArray(new Field[fields.size()]);
    }

    /* JADX INFO: Multiple debug info for r0v2 java.lang.Object[]: [D('c' java.lang.Class<?>), D('iftable' java.lang.Object[])] */
    private void getPublicFieldsRecursive(List<Field> result) {
        for (Class<? super T> cls = this; cls != null; cls = cls.superClass) {
            Collections.addAll(result, cls.getPublicDeclaredFields());
        }
        Object[] iftable = this.ifTable;
        if (iftable != null) {
            for (int i = 0; i < iftable.length; i += 2) {
                Collections.addAll(result, ((Class) iftable[i]).getPublicDeclaredFields());
            }
        }
    }

    @CallerSensitive
    public Method[] getMethods() throws SecurityException {
        List<Method> methods2 = new ArrayList<>();
        getPublicMethodsInternal(methods2);
        CollectionUtils.removeDuplicates(methods2, Method.ORDER_BY_SIGNATURE);
        return (Method[]) methods2.toArray(new Method[methods2.size()]);
    }

    /* JADX INFO: Multiple debug info for r1v2 java.lang.Object[]: [D('c' java.lang.Class<?>), D('iftable' java.lang.Object[])] */
    private void getPublicMethodsInternal(List<Method> result) {
        Collections.addAll(result, getDeclaredMethodsUnchecked(true));
        if (!isInterface()) {
            for (Class<?> c = this.superClass; c != null; c = c.superClass) {
                Collections.addAll(result, c.getDeclaredMethodsUnchecked(true));
            }
        }
        Object[] iftable = this.ifTable;
        if (iftable != null) {
            for (int i = 0; i < iftable.length; i += 2) {
                Collections.addAll(result, ((Class) iftable[i]).getDeclaredMethodsUnchecked(true));
            }
        }
    }

    @CallerSensitive
    public Constructor<?>[] getConstructors() throws SecurityException {
        return getDeclaredConstructorsInternal(true);
    }

    public Field getField(String name2) throws NoSuchFieldException {
        if (name2 != null) {
            Field result = getPublicFieldRecursive(name2);
            if (result != null) {
                return result;
            }
            throw new NoSuchFieldException(name2);
        }
        throw new NullPointerException("name == null");
    }

    @CallerSensitive
    public Method getMethod(String name2, Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException {
        return getMethod(name2, parameterTypes, true);
    }

    public Constructor<T> getConstructor(Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException {
        return getConstructor0(parameterTypes, 0);
    }

    public Method[] getDeclaredMethods() throws SecurityException {
        Method[] result = getDeclaredMethodsUnchecked(false);
        for (Method m : result) {
            m.getReturnType();
            m.getParameterTypes();
        }
        return result;
    }

    public Constructor<?>[] getDeclaredConstructors() throws SecurityException {
        return getDeclaredConstructorsInternal(false);
    }

    @CallerSensitive
    public Method getDeclaredMethod(String name2, Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException {
        return getMethod(name2, parameterTypes, false);
    }

    private Method getMethod(String name2, Class<?>[] parameterTypes, boolean recursivePublicMethods) throws NoSuchMethodException {
        Method result;
        if (name2 != null) {
            if (parameterTypes == null) {
                parameterTypes = EmptyArray.CLASS;
            }
            for (Class<?> c : parameterTypes) {
                if (c == null) {
                    throw new NoSuchMethodException("parameter type is null");
                }
            }
            if (recursivePublicMethods) {
                result = getPublicMethodRecursive(name2, parameterTypes);
            } else {
                result = getDeclaredMethodInternal(name2, parameterTypes);
            }
            if (result != null && (!recursivePublicMethods || Modifier.isPublic(result.getAccessFlags()))) {
                return result;
            }
            throw new NoSuchMethodException(getName() + "." + name2 + Padder.FALLBACK_PADDING_STRING + Arrays.toString(parameterTypes));
        }
        throw new NullPointerException("name == null");
    }

    private Method getPublicMethodRecursive(String name2, Class<?>[] parameterTypes) {
        for (Class<T> cls = this; cls != null; cls = cls.getSuperclass()) {
            Method result = cls.getDeclaredMethodInternal(name2, parameterTypes);
            if (result != null && Modifier.isPublic(result.getAccessFlags())) {
                return result;
            }
        }
        return findInterfaceMethod(name2, parameterTypes);
    }

    public Method getInstanceMethod(String name2, Class<?>[] parameterTypes) throws NoSuchMethodException, IllegalAccessException {
        for (Class<T> cls = this; cls != null; cls = cls.getSuperclass()) {
            Method result = cls.getDeclaredMethodInternal(name2, parameterTypes);
            if (!(result == null || Modifier.isStatic(result.getModifiers()))) {
                return result;
            }
        }
        return findInterfaceMethod(name2, parameterTypes);
    }

    private Method findInterfaceMethod(String name2, Class<?>[] parameterTypes) {
        Object[] iftable = this.ifTable;
        if (iftable == null) {
            return null;
        }
        for (int i = iftable.length - 2; i >= 0; i -= 2) {
            Method result = ((Class) iftable[i]).getPublicMethodRecursive(name2, parameterTypes);
            if (result != null && Modifier.isPublic(result.getAccessFlags())) {
                return result;
            }
        }
        return null;
    }

    @CallerSensitive
    public Constructor<T> getDeclaredConstructor(Class<?>... parameterTypes) throws NoSuchMethodException, SecurityException {
        return getConstructor0(parameterTypes, 1);
    }

    public InputStream getResourceAsStream(String name2) {
        String name3 = resolveName(name2);
        ClassLoader cl = getClassLoader();
        if (cl == null) {
            return ClassLoader.getSystemResourceAsStream(name3);
        }
        return cl.getResourceAsStream(name3);
    }

    public URL getResource(String name2) {
        String name3 = resolveName(name2);
        ClassLoader cl = getClassLoader();
        if (cl == null) {
            return ClassLoader.getSystemResource(name3);
        }
        return cl.getResource(name3);
    }

    public ProtectionDomain getProtectionDomain() {
        return null;
    }

    private String resolveName(String name2) {
        if (name2 == null) {
            return name2;
        }
        if (name2.startsWith("/")) {
            return name2.substring(1);
        }
        Class<?> c = this;
        while (c.isArray()) {
            c = c.getComponentType();
        }
        String baseName = c.getName();
        int index = baseName.lastIndexOf(46);
        if (index == -1) {
            return name2;
        }
        return baseName.substring(0, index).replace('.', '/') + "/" + name2;
    }

    private Constructor<T> getConstructor0(Class<?>[] parameterTypes, int which) throws NoSuchMethodException {
        if (parameterTypes == null) {
            parameterTypes = EmptyArray.CLASS;
        }
        for (Class<?> c : parameterTypes) {
            if (c == null) {
                throw new NoSuchMethodException("parameter type is null");
            }
        }
        Constructor<T> result = getDeclaredConstructorInternal(parameterTypes);
        if (result != null && (which != 0 || Modifier.isPublic(result.getAccessFlags()))) {
            return result;
        }
        throw new NoSuchMethodException(getName() + ".<init> " + Arrays.toString(parameterTypes));
    }

    public boolean desiredAssertionStatus() {
        return false;
    }

    public boolean isEnum() {
        return (getModifiers() & 16384) != 0 && getSuperclass() == Enum.class;
    }

    public T[] getEnumConstants() {
        T[] values = getEnumConstantsShared();
        if (values != null) {
            return (T[]) ((Object[]) values.clone());
        }
        return null;
    }

    public T[] getEnumConstantsShared() {
        if (!isEnum()) {
            return null;
        }
        return (T[]) Enum.getSharedConstants(this);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.lang.Object */
    /* JADX WARN: Multi-variable type inference failed */
    public T cast(Object obj) {
        if (obj == 0 || isInstance(obj)) {
            return obj;
        }
        throw new ClassCastException(cannotCastMsg(obj));
    }

    private String cannotCastMsg(Object obj) {
        return "Cannot cast " + obj.getClass().getName() + " to " + getName();
    }

    public <U> Class<? extends U> asSubclass(Class<U> clazz) {
        if (clazz.isAssignableFrom(this)) {
            return this;
        }
        throw new ClassCastException(toString() + " cannot be cast to " + clazz.getName());
    }

    @Override // java.lang.reflect.AnnotatedElement
    public <A extends Annotation> A getAnnotation(Class<A> annotationClass) {
        Objects.requireNonNull(annotationClass);
        A annotation = (A) getDeclaredAnnotation(annotationClass);
        if (annotation != null) {
            return annotation;
        }
        if (!annotationClass.isDeclaredAnnotationPresent(Inherited.class)) {
            return null;
        }
        for (Class<?> sup = getSuperclass(); sup != null; sup = sup.getSuperclass()) {
            A annotation2 = (A) sup.getDeclaredAnnotation(annotationClass);
            if (annotation2 != null) {
                return annotation2;
            }
        }
        return null;
    }

    @Override // java.lang.reflect.AnnotatedElement
    public boolean isAnnotationPresent(Class<? extends Annotation> annotationClass) {
        if (annotationClass == null) {
            throw new NullPointerException("annotationClass == null");
        } else if (isDeclaredAnnotationPresent(annotationClass)) {
            return true;
        } else {
            if (!annotationClass.isDeclaredAnnotationPresent(Inherited.class)) {
                return false;
            }
            for (Class<?> sup = getSuperclass(); sup != null; sup = sup.getSuperclass()) {
                if (sup.isDeclaredAnnotationPresent(annotationClass)) {
                    return true;
                }
            }
            return false;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: java.lang.Class<A extends java.lang.annotation.Annotation> */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.reflect.AnnotatedElement
    public <A extends Annotation> A[] getAnnotationsByType(Class<A> annotationClass) {
        Class<?> superClass2;
        A[] annotations = (A[]) super.getAnnotationsByType(annotationClass);
        if (annotations.length != 0) {
            return annotations;
        }
        return (!annotationClass.isDeclaredAnnotationPresent(Inherited.class) || (superClass2 = getSuperclass()) == null) ? (A[]) ((Annotation[]) Array.newInstance((Class<?>) annotationClass, 0)) : (A[]) superClass2.getAnnotationsByType(annotationClass);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v3, resolved type: java.util.Collection */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.lang.reflect.AnnotatedElement
    public Annotation[] getAnnotations() {
        HashMap<Class<?>, Annotation> map = new HashMap<>();
        Annotation[] declaredAnnotations = getDeclaredAnnotations();
        for (Annotation declaredAnnotation : declaredAnnotations) {
            map.put(declaredAnnotation.annotationType(), declaredAnnotation);
        }
        for (Class<?> sup = getSuperclass(); sup != null; sup = sup.getSuperclass()) {
            Annotation[] declaredAnnotations2 = sup.getDeclaredAnnotations();
            for (Annotation declaredAnnotation2 : declaredAnnotations2) {
                Class<? extends Annotation> clazz = declaredAnnotation2.annotationType();
                if (!map.containsKey(clazz) && clazz.isDeclaredAnnotationPresent(Inherited.class)) {
                    map.put(clazz, declaredAnnotation2);
                }
            }
        }
        Collection<Annotation> coll = map.values();
        return (Annotation[]) coll.toArray(new Annotation[coll.size()]);
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

    public boolean isProxy() {
        return (this.accessFlags & 262144) != 0;
    }

    public int getAccessFlags() {
        return this.accessFlags;
    }

    private static class Caches {
        private static final BasicLruCache<Class, Type[]> genericInterfaces = new BasicLruCache<>(8);

        private Caches() {
        }
    }
}
