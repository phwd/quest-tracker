package com.squareup.otto;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public final class AnnotatedHandlerFinder {
    public static final Map<Class<?>, Map<Class<?>, Method>> PRODUCERS_CACHE = new HashMap();
    public static final Map<Class<?>, Map<Class<?>, Set<Method>>> SUBSCRIBERS_CACHE = new HashMap();

    public static void loadAnnotatedMethods(Class<?> cls) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        Method[] declaredMethods = cls.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (!method.isBridge()) {
                if (method.isAnnotationPresent(Subscribe.class)) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    int length = parameterTypes.length;
                    if (length == 1) {
                        Class<?> cls2 = parameterTypes[0];
                        if (cls2.isInterface()) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Method ");
                            sb.append(method);
                            sb.append(" has @Subscribe annotation on ");
                            sb.append(cls2);
                            sb.append(" which is an interface.  Subscription must be on a concrete class type.");
                            throw new IllegalArgumentException(sb.toString());
                        } else if ((1 & method.getModifiers()) != 0) {
                            Set set = (Set) hashMap.get(cls2);
                            if (set == null) {
                                set = new HashSet();
                                hashMap.put(cls2, set);
                            }
                            set.add(method);
                        } else {
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append("Method ");
                            sb2.append(method);
                            sb2.append(" has @Subscribe annotation on ");
                            sb2.append(cls2);
                            sb2.append(" but is not 'public'.");
                            throw new IllegalArgumentException(sb2.toString());
                        }
                    } else {
                        StringBuilder sb3 = new StringBuilder();
                        sb3.append("Method ");
                        sb3.append(method);
                        sb3.append(" has @Subscribe annotation but requires ");
                        sb3.append(length);
                        sb3.append(" arguments.  Methods must require a single argument.");
                        throw new IllegalArgumentException(sb3.toString());
                    }
                } else if (!method.isAnnotationPresent(Produce.class)) {
                    continue;
                } else {
                    int length2 = method.getParameterTypes().length;
                    if (length2 != 0) {
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append("Method ");
                        sb4.append(method);
                        sb4.append("has @Produce annotation but requires ");
                        sb4.append(length2);
                        sb4.append(" arguments.  Methods must require zero arguments.");
                        throw new IllegalArgumentException(sb4.toString());
                    } else if (method.getReturnType() != Void.class) {
                        Class<?> returnType = method.getReturnType();
                        if (returnType.isInterface()) {
                            StringBuilder sb5 = new StringBuilder();
                            sb5.append("Method ");
                            sb5.append(method);
                            sb5.append(" has @Produce annotation on ");
                            sb5.append(returnType);
                            sb5.append(" which is an interface.  Producers must return a concrete class type.");
                            throw new IllegalArgumentException(sb5.toString());
                        } else if (returnType.equals(Void.TYPE)) {
                            StringBuilder sb6 = new StringBuilder();
                            sb6.append("Method ");
                            sb6.append(method);
                            sb6.append(" has @Produce annotation but has no return type.");
                            throw new IllegalArgumentException(sb6.toString());
                        } else if ((1 & method.getModifiers()) == 0) {
                            StringBuilder sb7 = new StringBuilder();
                            sb7.append("Method ");
                            sb7.append(method);
                            sb7.append(" has @Produce annotation on ");
                            sb7.append(returnType);
                            sb7.append(" but is not 'public'.");
                            throw new IllegalArgumentException(sb7.toString());
                        } else if (!hashMap2.containsKey(returnType)) {
                            hashMap2.put(returnType, method);
                        } else {
                            StringBuilder sb8 = new StringBuilder("Producer for type ");
                            sb8.append(returnType);
                            sb8.append(" has already been registered.");
                            throw new IllegalArgumentException(sb8.toString());
                        }
                    } else {
                        StringBuilder sb9 = new StringBuilder();
                        sb9.append("Method ");
                        sb9.append(method);
                        sb9.append(" has a return type of void.  Must declare a non-void type.");
                        throw new IllegalArgumentException(sb9.toString());
                    }
                }
            }
        }
        PRODUCERS_CACHE.put(cls, hashMap2);
        SUBSCRIBERS_CACHE.put(cls, hashMap);
    }

    public static Map<Class<?>, EventProducer> findAllProducers(Object obj) {
        Class<?> cls = obj.getClass();
        HashMap hashMap = new HashMap();
        if (!PRODUCERS_CACHE.containsKey(cls)) {
            loadAnnotatedMethods(cls);
        }
        Map<Class<?>, Method> map = PRODUCERS_CACHE.get(cls);
        if (!map.isEmpty()) {
            for (Map.Entry<Class<?>, Method> entry : map.entrySet()) {
                hashMap.put(entry.getKey(), new EventProducer(obj, entry.getValue()));
            }
        }
        return hashMap;
    }

    public static Map<Class<?>, Set<EventHandler>> findAllSubscribers(Object obj) {
        Class<?> cls = obj.getClass();
        HashMap hashMap = new HashMap();
        if (!SUBSCRIBERS_CACHE.containsKey(cls)) {
            loadAnnotatedMethods(cls);
        }
        Map<Class<?>, Set<Method>> map = SUBSCRIBERS_CACHE.get(cls);
        if (!map.isEmpty()) {
            for (Map.Entry<Class<?>, Set<Method>> entry : map.entrySet()) {
                HashSet hashSet = new HashSet();
                for (Method method : entry.getValue()) {
                    hashSet.add(new EventHandler(obj, method));
                }
                hashMap.put(entry.getKey(), hashSet);
            }
        }
        return hashMap;
    }
}
