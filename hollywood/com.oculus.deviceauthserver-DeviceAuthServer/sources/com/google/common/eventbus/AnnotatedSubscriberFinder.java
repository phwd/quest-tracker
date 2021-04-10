package com.google.common.eventbus;

import com.google.common.base.Objects;
import com.google.common.base.Throwables;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.UnmodifiableIterator;
import com.google.common.reflect.TypeToken;
import com.google.common.util.concurrent.UncheckedExecutionException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
public class AnnotatedSubscriberFinder implements SubscriberFindingStrategy {
    private static final LoadingCache<Class<?>, ImmutableList<Method>> subscriberMethodsCache = CacheBuilder.newBuilder().weakKeys().build(new CacheLoader<Class<?>, ImmutableList<Method>>() {
        /* class com.google.common.eventbus.AnnotatedSubscriberFinder.AnonymousClass1 */

        public ImmutableList<Method> load(Class<?> concreteClass) throws Exception {
            return AnnotatedSubscriberFinder.getAnnotatedMethodsInternal(concreteClass);
        }
    });

    AnnotatedSubscriberFinder() {
    }

    @Override // com.google.common.eventbus.SubscriberFindingStrategy
    public Multimap<Class<?>, EventSubscriber> findAllSubscribers(Object listener) {
        Multimap<Class<?>, EventSubscriber> methodsInListener = HashMultimap.create();
        UnmodifiableIterator<Method> it = getAnnotatedMethods(listener.getClass()).iterator();
        while (it.hasNext()) {
            Method method = it.next();
            methodsInListener.put(method.getParameterTypes()[0], makeSubscriber(listener, method));
        }
        return methodsInListener;
    }

    private static ImmutableList<Method> getAnnotatedMethods(Class<?> clazz) {
        try {
            return subscriberMethodsCache.getUnchecked(clazz);
        } catch (UncheckedExecutionException e) {
            throw Throwables.propagate(e.getCause());
        }
    }

    /* access modifiers changed from: private */
    public static final class MethodIdentifier {
        private final String name;
        private final List<Class<?>> parameterTypes;

        MethodIdentifier(Method method) {
            this.name = method.getName();
            this.parameterTypes = Arrays.asList(method.getParameterTypes());
        }

        public int hashCode() {
            return Objects.hashCode(this.name, this.parameterTypes);
        }

        public boolean equals(@Nullable Object o) {
            if (!(o instanceof MethodIdentifier)) {
                return false;
            }
            MethodIdentifier ident = (MethodIdentifier) o;
            if (!this.name.equals(ident.name) || !this.parameterTypes.equals(ident.parameterTypes)) {
                return false;
            }
            return true;
        }
    }

    /* access modifiers changed from: private */
    public static ImmutableList<Method> getAnnotatedMethodsInternal(Class<?> clazz) {
        Set<? extends Class<?>> supers = TypeToken.of((Class) clazz).getTypes().rawTypes();
        Map<MethodIdentifier, Method> identifiers = Maps.newHashMap();
        for (Class<?> superClazz : supers) {
            Method[] methods = superClazz.getMethods();
            int length = methods.length;
            int i = 0;
            while (true) {
                if (i < length) {
                    Method superClazzMethod = methods[i];
                    if (superClazzMethod.isAnnotationPresent(Subscribe.class) && !superClazzMethod.isBridge()) {
                        Class<?>[] parameterTypes = superClazzMethod.getParameterTypes();
                        if (parameterTypes.length == 1) {
                            MethodIdentifier ident = new MethodIdentifier(superClazzMethod);
                            if (!identifiers.containsKey(ident)) {
                                identifiers.put(ident, superClazzMethod);
                            }
                        } else {
                            throw new IllegalArgumentException("Method " + superClazzMethod + " has @Subscribe annotation, but requires " + parameterTypes.length + " arguments.  Event subscriber methods must require a single argument.");
                        }
                    }
                    i++;
                }
            }
        }
        return ImmutableList.copyOf((Collection) identifiers.values());
    }

    private static EventSubscriber makeSubscriber(Object listener, Method method) {
        if (methodIsDeclaredThreadSafe(method)) {
            return new EventSubscriber(listener, method);
        }
        return new SynchronizedEventSubscriber(listener, method);
    }

    private static boolean methodIsDeclaredThreadSafe(Method method) {
        return method.getAnnotation(AllowConcurrentEvents.class) != null;
    }
}
