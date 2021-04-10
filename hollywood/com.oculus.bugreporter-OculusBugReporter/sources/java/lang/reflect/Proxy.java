package java.lang.reflect;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BiFunction;
import libcore.util.EmptyArray;
import sun.reflect.CallerSensitive;

public class Proxy implements Serializable {
    private static final Comparator<Method> ORDER_BY_SIGNATURE_AND_SUBTYPE = new Comparator<Method>() {
        /* class java.lang.reflect.Proxy.AnonymousClass1 */

        public int compare(Method a, Method b) {
            int comparison = Method.ORDER_BY_SIGNATURE.compare(a, b);
            if (comparison != 0) {
                return comparison;
            }
            Class<?> aClass = a.getDeclaringClass();
            Class<?> bClass = b.getDeclaringClass();
            if (aClass == bClass) {
                return 0;
            }
            if (aClass.isAssignableFrom(bClass)) {
                return 1;
            }
            if (bClass.isAssignableFrom(aClass)) {
                return -1;
            }
            return 0;
        }
    };
    private static final Class<?>[] constructorParams = {InvocationHandler.class};
    private static final Object key0 = new Object();
    private static final WeakCache<ClassLoader, Class<?>[], Class<?>> proxyClassCache = new WeakCache<>(new KeyFactory(), new ProxyClassFactory());
    private static final long serialVersionUID = -2222568056686623797L;
    protected InvocationHandler h;

    /* access modifiers changed from: private */
    public static native Class<?> generateProxy(String str, Class<?>[] clsArr, ClassLoader classLoader, Method[] methodArr, Class<?>[][] clsArr2);

    private Proxy() {
    }

    protected Proxy(InvocationHandler h2) {
        Objects.requireNonNull(h2);
        this.h = h2;
    }

    @CallerSensitive
    public static Class<?> getProxyClass(ClassLoader loader, Class<?>... interfaces) throws IllegalArgumentException {
        return getProxyClass0(loader, interfaces);
    }

    private static Class<?> getProxyClass0(ClassLoader loader, Class<?>... interfaces) {
        if (interfaces.length <= 65535) {
            return proxyClassCache.get(loader, interfaces);
        }
        throw new IllegalArgumentException("interface limit exceeded");
    }

    /* access modifiers changed from: private */
    public static final class Key1 extends WeakReference<Class<?>> {
        private final int hash;

        Key1(Class<?> intf) {
            super(intf);
            this.hash = intf.hashCode();
        }

        public int hashCode() {
            return this.hash;
        }

        public boolean equals(Object obj) {
            Class<?> intf;
            return this == obj || (obj != null && obj.getClass() == Key1.class && (intf = (Class) get()) != null && intf == ((Key1) obj).get());
        }
    }

    /* access modifiers changed from: private */
    public static final class Key2 extends WeakReference<Class<?>> {
        private final int hash;
        private final WeakReference<Class<?>> ref2;

        Key2(Class<?> intf1, Class<?> intf2) {
            super(intf1);
            this.hash = (intf1.hashCode() * 31) + intf2.hashCode();
            this.ref2 = new WeakReference<>(intf2);
        }

        public int hashCode() {
            return this.hash;
        }

        public boolean equals(Object obj) {
            Class<?> intf1;
            Class<?> intf2;
            if (this == obj || (obj != null && obj.getClass() == Key2.class && (intf1 = (Class) get()) != null && intf1 == ((Key2) obj).get() && (intf2 = this.ref2.get()) != null && intf2 == ((Key2) obj).ref2.get())) {
                return true;
            }
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static final class KeyX {
        private final int hash;
        private final WeakReference<Class<?>>[] refs;

        KeyX(Class<?>[] interfaces) {
            this.hash = Arrays.hashCode(interfaces);
            this.refs = new WeakReference[interfaces.length];
            for (int i = 0; i < interfaces.length; i++) {
                this.refs[i] = new WeakReference<>(interfaces[i]);
            }
        }

        public int hashCode() {
            return this.hash;
        }

        public boolean equals(Object obj) {
            return this == obj || (obj != null && obj.getClass() == KeyX.class && equals(this.refs, ((KeyX) obj).refs));
        }

        private static boolean equals(WeakReference<Class<?>>[] refs1, WeakReference<Class<?>>[] refs2) {
            if (refs1.length != refs2.length) {
                return false;
            }
            for (int i = 0; i < refs1.length; i++) {
                Class<?> intf = refs1[i].get();
                if (intf == null || intf != refs2[i].get()) {
                    return false;
                }
            }
            return true;
        }
    }

    private static final class KeyFactory implements BiFunction<ClassLoader, Class<?>[], Object> {
        private KeyFactory() {
        }

        public Object apply(ClassLoader classLoader, Class<?>[] interfaces) {
            int length = interfaces.length;
            if (length == 0) {
                return Proxy.key0;
            }
            if (length == 1) {
                return new Key1(interfaces[0]);
            }
            if (length != 2) {
                return new KeyX(interfaces);
            }
            return new Key2(interfaces[0], interfaces[1]);
        }
    }

    private static final class ProxyClassFactory implements BiFunction<ClassLoader, Class<?>[], Class<?>> {
        private static final AtomicLong nextUniqueNumber = new AtomicLong();
        private static final String proxyClassNamePrefix = "$Proxy";

        private ProxyClassFactory() {
        }

        public Class<?> apply(ClassLoader loader, Class<?>[] interfaces) {
            Map<Class<?>, Boolean> interfaceSet = new IdentityHashMap<>(interfaces.length);
            for (Class<?> intf : interfaces) {
                Class<?> interfaceClass = null;
                try {
                    interfaceClass = Class.forName(intf.getName(), false, loader);
                } catch (ClassNotFoundException e) {
                }
                if (interfaceClass != intf) {
                    throw new IllegalArgumentException(((Object) intf) + " is not visible from class loader");
                } else if (!interfaceClass.isInterface()) {
                    throw new IllegalArgumentException(interfaceClass.getName() + " is not an interface");
                } else if (interfaceSet.put(interfaceClass, Boolean.TRUE) != null) {
                    throw new IllegalArgumentException("repeated interface: " + interfaceClass.getName());
                }
            }
            String proxyPkg = null;
            for (Class<?> intf2 : interfaces) {
                if (!Modifier.isPublic(intf2.getModifiers())) {
                    String name = intf2.getName();
                    int n = name.lastIndexOf(46);
                    String pkg = n == -1 ? "" : name.substring(0, n + 1);
                    if (proxyPkg == null) {
                        proxyPkg = pkg;
                    } else if (!pkg.equals(proxyPkg)) {
                        throw new IllegalArgumentException("non-public interfaces from different packages");
                    }
                }
            }
            if (proxyPkg == null) {
                proxyPkg = "";
            }
            List<Method> methods = Proxy.getMethods(interfaces);
            Collections.sort(methods, Proxy.ORDER_BY_SIGNATURE_AND_SUBTYPE);
            Proxy.validateReturnTypes(methods);
            List<Class<?>[]> exceptions = Proxy.deduplicateAndGetExceptions(methods);
            return Proxy.generateProxy(proxyPkg + proxyClassNamePrefix + nextUniqueNumber.getAndIncrement(), interfaces, loader, (Method[]) methods.toArray(new Method[methods.size()]), (Class[][]) exceptions.toArray(new Class[exceptions.size()][]));
        }
    }

    /* access modifiers changed from: private */
    public static List<Class<?>[]> deduplicateAndGetExceptions(List<Method> methods) {
        List<Class<?>[]> exceptions = new ArrayList<>(methods.size());
        int i = 0;
        while (i < methods.size()) {
            Method method = methods.get(i);
            Class<?>[] exceptionTypes = method.getExceptionTypes();
            if (i <= 0 || Method.ORDER_BY_SIGNATURE.compare(method, methods.get(i - 1)) != 0) {
                exceptions.add(exceptionTypes);
                i++;
            } else {
                exceptions.set(i - 1, intersectExceptions(exceptions.get(i - 1), exceptionTypes));
                methods.remove(i);
            }
        }
        return exceptions;
    }

    private static Class<?>[] intersectExceptions(Class<?>[] aExceptions, Class<?>[] bExceptions) {
        if (aExceptions.length == 0 || bExceptions.length == 0) {
            return EmptyArray.CLASS;
        }
        if (Arrays.equals(aExceptions, bExceptions)) {
            return aExceptions;
        }
        Set<Class<?>> intersection = new HashSet<>();
        for (Class<?> a : aExceptions) {
            for (Class<?> b : bExceptions) {
                if (a.isAssignableFrom(b)) {
                    intersection.add(b);
                } else if (b.isAssignableFrom(a)) {
                    intersection.add(a);
                }
            }
        }
        return (Class[]) intersection.toArray(new Class[intersection.size()]);
    }

    /* access modifiers changed from: private */
    public static void validateReturnTypes(List<Method> methods) {
        Method vs = null;
        for (Method method : methods) {
            if (vs == null || !vs.equalNameAndParameters(method)) {
                vs = method;
            } else {
                Class<?> returnType = method.getReturnType();
                Class<?> vsReturnType = vs.getReturnType();
                if (!returnType.isInterface() || !vsReturnType.isInterface()) {
                    if (vsReturnType.isAssignableFrom(returnType)) {
                        vs = method;
                    } else if (!returnType.isAssignableFrom(vsReturnType)) {
                        throw new IllegalArgumentException("proxied interface methods have incompatible return types:\n  " + ((Object) vs) + "\n  " + ((Object) method));
                    }
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public static List<Method> getMethods(Class<?>[] interfaces) {
        List<Method> result = new ArrayList<>();
        try {
            result.add(Object.class.getMethod("equals", Object.class));
            result.add(Object.class.getMethod("hashCode", EmptyArray.CLASS));
            result.add(Object.class.getMethod("toString", EmptyArray.CLASS));
            getMethodsRecursive(interfaces, result);
            return result;
        } catch (NoSuchMethodException e) {
            throw new AssertionError();
        }
    }

    private static void getMethodsRecursive(Class<?>[] interfaces, List<Method> methods) {
        for (Class<?> i : interfaces) {
            getMethodsRecursive(i.getInterfaces(), methods);
            Collections.addAll(methods, i.getDeclaredMethods());
        }
    }

    @CallerSensitive
    public static Object newProxyInstance(ClassLoader loader, Class<?>[] interfaces, InvocationHandler h2) throws IllegalArgumentException {
        Objects.requireNonNull(h2);
        Class<?> cl = getProxyClass0(loader, (Class[]) interfaces.clone());
        try {
            Constructor<?> cons = cl.getConstructor(constructorParams);
            if (!Modifier.isPublic(cl.getModifiers())) {
                cons.setAccessible(true);
            }
            return cons.newInstance(h2);
        } catch (IllegalAccessException | InstantiationException e) {
            throw new InternalError(e.toString(), e);
        } catch (InvocationTargetException e2) {
            Throwable t = e2.getCause();
            if (t instanceof RuntimeException) {
                throw ((RuntimeException) t);
            }
            throw new InternalError(t.toString(), t);
        } catch (NoSuchMethodException e3) {
            throw new InternalError(e3.toString(), e3);
        }
    }

    public static boolean isProxyClass(Class<?> cl) {
        return Proxy.class.isAssignableFrom(cl) && proxyClassCache.containsValue(cl);
    }

    @CallerSensitive
    public static InvocationHandler getInvocationHandler(Object proxy) throws IllegalArgumentException {
        if (isProxyClass(proxy.getClass())) {
            return ((Proxy) proxy).h;
        }
        throw new IllegalArgumentException("not a proxy instance");
    }

    private static Object invoke(Proxy proxy, Method method, Object[] args) throws Throwable {
        return proxy.h.invoke(proxy, method, args);
    }
}
