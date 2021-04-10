package com.squareup.otto;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

final class AnnotatedHandlerFinder {
    private static final Map<Class<?>, Map<Class<?>, Method>> PRODUCERS_CACHE = new HashMap();
    private static final Map<Class<?>, Map<Class<?>, Set<Method>>> SUBSCRIBERS_CACHE = new HashMap();

    private static void loadAnnotatedMethods(Class<?> cls) {
        Map<Class<?>, Set<Method>> hashMap = new HashMap<>();
        Map<Class<?>, Method> hashMap2 = new HashMap<>();
        Method[] declaredMethods = cls.getDeclaredMethods();
        for (Method method : declaredMethods) {
            if (!method.isBridge()) {
                if (method.isAnnotationPresent(Subscribe.class)) {
                    Class<?>[] parameterTypes = method.getParameterTypes();
                    if (parameterTypes.length == 1) {
                        Class<?> cls2 = parameterTypes[0];
                        if (cls2.isInterface()) {
                            throw new IllegalArgumentException("Method " + method + " has @Subscribe annotation on " + cls2 + " which is an interface.  Subscription must be on a concrete class type.");
                        } else if ((1 & method.getModifiers()) != 0) {
                            Set<Method> set = hashMap.get(cls2);
                            if (set == null) {
                                set = new HashSet<>();
                                hashMap.put(cls2, set);
                            }
                            set.add(method);
                        } else {
                            throw new IllegalArgumentException("Method " + method + " has @Subscribe annotation on " + cls2 + " but is not 'public'.");
                        }
                    } else {
                        throw new IllegalArgumentException("Method " + method + " has @Subscribe annotation but requires " + parameterTypes.length + " arguments.  Methods must require a single argument.");
                    }
                } else if (method.isAnnotationPresent(Produce.class)) {
                    Class<?>[] parameterTypes2 = method.getParameterTypes();
                    if (parameterTypes2.length != 0) {
                        throw new IllegalArgumentException("Method " + method + "has @Produce annotation but requires " + parameterTypes2.length + " arguments.  Methods must require zero arguments.");
                    } else if (method.getReturnType() != Void.class) {
                        Class<?> returnType = method.getReturnType();
                        if (returnType.isInterface()) {
                            throw new IllegalArgumentException("Method " + method + " has @Produce annotation on " + returnType + " which is an interface.  Producers must return a concrete class type.");
                        } else if (returnType.equals(Void.TYPE)) {
                            throw new IllegalArgumentException("Method " + method + " has @Produce annotation but has no return type.");
                        } else if ((1 & method.getModifiers()) == 0) {
                            throw new IllegalArgumentException("Method " + method + " has @Produce annotation on " + returnType + " but is not 'public'.");
                        } else if (!hashMap2.containsKey(returnType)) {
                            hashMap2.put(returnType, method);
                        } else {
                            throw new IllegalArgumentException("Producer for type " + returnType + " has already been registered.");
                        }
                    } else {
                        throw new IllegalArgumentException("Method " + method + " has a return type of void.  Must declare a non-void type.");
                    }
                } else {
                    continue;
                }
            }
        }
        PRODUCERS_CACHE.put(cls, hashMap2);
        SUBSCRIBERS_CACHE.put(cls, hashMap);
    }

    static Map<Class<?>, EventProducer> findAllProducers(Object obj) {
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

    static Map<Class<?>, Set<EventHandler>> findAllSubscribers(Object obj) {
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

    private AnnotatedHandlerFinder() {
    }
}
