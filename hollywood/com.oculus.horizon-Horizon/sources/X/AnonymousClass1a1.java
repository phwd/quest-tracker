package X;

import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* renamed from: X.1a1  reason: invalid class name */
public final class AnonymousClass1a1 {
    public static final Map<Class<?>, Map<Class<?>, Method>> A00 = new HashMap();
    public static final Map<Class<?>, Map<Class<?>, Set<Method>>> A01 = new HashMap();

    public static void A00(Class<?> cls) {
        StringBuilder sb;
        String str;
        Class<?> cls2;
        String str2;
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        Method[] declaredMethods = cls.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (!method.isBridge()) {
                if (method.isAnnotationPresent(Subscribe.class)) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    int length = parameterTypes.length;
                    if (length == 1) {
                        cls2 = parameterTypes[0];
                        str2 = " has @Subscribe annotation on ";
                        if (cls2.isInterface()) {
                            sb = new StringBuilder();
                            sb.append("Method ");
                            sb.append(method);
                            sb.append(str2);
                            sb.append(cls2);
                            str = " which is an interface.  Subscription must be on a concrete class type.";
                        } else if ((1 & method.getModifiers()) != 0) {
                            Set set = (Set) hashMap.get(cls2);
                            if (set == null) {
                                set = new HashSet();
                                hashMap.put(cls2, set);
                            }
                            set.add(method);
                        }
                    } else {
                        sb = new StringBuilder();
                        sb.append("Method ");
                        sb.append(method);
                        sb.append(" has @Subscribe annotation but requires ");
                        sb.append(length);
                        str = " arguments.  Methods must require a single argument.";
                    }
                    sb.append(str);
                    throw new IllegalArgumentException(sb.toString());
                } else if (!method.isAnnotationPresent(Produce.class)) {
                    continue;
                } else {
                    int length2 = method.getParameterTypes().length;
                    if (length2 != 0) {
                        sb = new StringBuilder();
                        sb.append("Method ");
                        sb.append(method);
                        sb.append("has @Produce annotation but requires ");
                        sb.append(length2);
                        str = " arguments.  Methods must require zero arguments.";
                    } else if (method.getReturnType() != Void.class) {
                        cls2 = method.getReturnType();
                        str2 = " has @Produce annotation on ";
                        if (cls2.isInterface()) {
                            sb = new StringBuilder();
                            sb.append("Method ");
                            sb.append(method);
                            sb.append(str2);
                            sb.append(cls2);
                            str = " which is an interface.  Producers must return a concrete class type.";
                        } else if (cls2.equals(Void.TYPE)) {
                            sb = new StringBuilder();
                            sb.append("Method ");
                            sb.append(method);
                            str = " has @Produce annotation but has no return type.";
                        } else if ((1 & method.getModifiers()) != 0) {
                            if (!hashMap2.containsKey(cls2)) {
                                hashMap2.put(cls2, method);
                            } else {
                                sb = new StringBuilder("Producer for type ");
                                sb.append(cls2);
                                str = " has already been registered.";
                            }
                        }
                    } else {
                        sb = new StringBuilder();
                        sb.append("Method ");
                        sb.append(method);
                        str = " has a return type of void.  Must declare a non-void type.";
                    }
                    sb.append(str);
                    throw new IllegalArgumentException(sb.toString());
                }
                sb = new StringBuilder();
                sb.append("Method ");
                sb.append(method);
                sb.append(str2);
                sb.append(cls2);
                sb.append(" but is not 'public'.");
                throw new IllegalArgumentException(sb.toString());
            }
        }
        A00.put(cls, hashMap2);
        A01.put(cls, hashMap);
    }
}
