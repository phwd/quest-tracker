package com.facebook.common.util;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Throwables;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

public class ReflectionUtils {

    public interface Filter<T> {
        T mapper(AccessibleObject accessibleObject);
    }

    public enum IncludeFlags {
        INCLUDE_SUPERCLASSES,
        INCLUDE_CONSTRUCTORS,
        INCLUDE_METHODS,
        INCLUDE_FIELDS
    }

    public static <T> Map<? extends AccessibleObject, T> getComponents(Class<?> cls, Filter<T> filter, EnumSet<IncludeFlags> enumSet) {
        HashMap hashMap = new HashMap();
        getComponents(cls, filter, enumSet, hashMap);
        return hashMap;
    }

    protected static <T> void getComponents(Class<?> cls, Filter<T> filter, EnumSet<IncludeFlags> enumSet, Map<AccessibleObject, T> map) {
        Class<? super Object> superclass;
        if (enumSet.contains(IncludeFlags.INCLUDE_SUPERCLASSES) && (superclass = cls.getSuperclass()) != null) {
            getComponents(superclass, filter, enumSet, map);
        }
        if (enumSet.contains(IncludeFlags.INCLUDE_CONSTRUCTORS)) {
            Constructor<?>[] declaredConstructors = cls.getDeclaredConstructors();
            for (int i = 0; i < declaredConstructors.length; i++) {
                T mapper = filter.mapper(declaredConstructors[i]);
                if (mapper != null) {
                    map.put(declaredConstructors[i], mapper);
                }
            }
        }
        if (enumSet.contains(IncludeFlags.INCLUDE_METHODS)) {
            Method[] declaredMethods = cls.getDeclaredMethods();
            for (int i2 = 0; i2 < declaredMethods.length; i2++) {
                T mapper2 = filter.mapper(declaredMethods[i2]);
                if (mapper2 != null) {
                    map.put(declaredMethods[i2], mapper2);
                }
            }
        }
        if (enumSet.contains(IncludeFlags.INCLUDE_FIELDS)) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (int i3 = 0; i3 < declaredFields.length; i3++) {
                T mapper3 = filter.mapper(declaredFields[i3]);
                if (mapper3 != null) {
                    map.put(declaredFields[i3], mapper3);
                }
            }
        }
    }

    public static String reflectiveToString(Object obj) {
        Class<?> cls = obj.getClass();
        StringBuilder sb = new StringBuilder();
        sb.append(cls.getName());
        sb.append(" extends ");
        sb.append(cls.getSuperclass().getName());
        sb.append(" {\n");
        for (Member member : Iterables.concat(Arrays.asList(cls.getDeclaredConstructors()), Arrays.asList(cls.getDeclaredMethods()), Arrays.asList(cls.getDeclaredFields()))) {
            sb.append("  ");
            sb.append(member);
            if (member instanceof Field) {
                sb.append(" = ");
                sb.append(readField(obj, (Field) member));
            }
            sb.append("\n");
        }
        sb.append("}");
        return sb.toString();
    }

    public static <T> T getEnclosingObject(Object obj) throws NoSuchFieldException {
        return (T) readField(obj, "this$0");
    }

    public static <T> T readField(Object obj, String str) throws NoSuchFieldException {
        return (T) readField(obj, str, obj.getClass());
    }

    private static <T> T readField(Object obj, String str, Class<?> cls) throws NoSuchFieldException {
        try {
            return (T) readField(obj, cls.getDeclaredField(str));
        } catch (NoSuchFieldException unused) {
            if (cls != Object.class) {
                return (T) readField(obj, str, cls.getSuperclass());
            }
            throw new NoSuchFieldException("No field " + str + " among classes " + getAncestralChain(obj.getClass()));
        }
    }

    public static <T> T readField(Object obj, Field field) {
        field.setAccessible(true);
        try {
            return (T) field.get(obj);
        } catch (IllegalAccessException e) {
            throw Throwables.propagate(e);
        }
    }

    public static void writeField(Object obj, Field field, Object obj2) {
        field.setAccessible(true);
        try {
            field.set(obj, obj2);
        } catch (IllegalAccessException e) {
            throw Throwables.propagate(e);
        }
    }

    public static Iterable<Class> getAncestralChain(Class cls) {
        Set singleton = Collections.singleton(cls);
        if (cls == Object.class) {
            return singleton;
        }
        return Iterables.concat(singleton, getAncestralChain(cls.getSuperclass()));
    }

    public static <T> T newInstance(Class<T> cls, Object... objArr) throws InvocationTargetException, InstantiationException, NoSuchMethodException {
        Constructor<T> declaredConstructor = cls.getDeclaredConstructor(FluentIterable.of(objArr, new Object[0][]).transform(new Function<Object, Class>() {
            /* class com.facebook.common.util.ReflectionUtils.AnonymousClass1 */

            @Override // com.google.common.base.Function
            public Class apply(Object obj) {
                return obj.getClass();
            }
        }).toArray(Class.class));
        declaredConstructor.setAccessible(true);
        try {
            return declaredConstructor.newInstance(objArr);
        } catch (IllegalAccessException e) {
            throw Throwables.propagate(e);
        }
    }

    public static <T> Constructor<T> getOnlyConstructor(Class<T> cls) {
        Constructor<?>[] constructors = cls.getConstructors();
        boolean z = true;
        if (constructors.length != 1) {
            z = false;
        }
        Preconditions.checkArgument(z, "%s has %d constructors", (Object) cls, constructors.length);
        return (Constructor<T>) constructors[0];
    }

    public static ImmutableMap<Field, Object> getConstants(Class<?> cls) {
        return FluentIterable.from(cls.getFields()).filter(new Predicate<Field>() {
            /* class com.facebook.common.util.ReflectionUtils.AnonymousClass3 */

            public boolean apply(Field field) {
                int modifiers = field.getModifiers();
                return Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers);
            }
        }).toMap(new Function<Field, Object>() {
            /* class com.facebook.common.util.ReflectionUtils.AnonymousClass2 */

            @Nullable
            public Object apply(Field field) {
                return ReflectionUtils.readField((Object) null, field);
            }
        });
    }

    public static boolean hasAnnotation(Class cls, Class<? extends Annotation> cls2) {
        return cls.getAnnotation(cls2) != null;
    }

    public static class FieldMap extends AbstractMap<String, Object> {
        private final ImmutableSet<Map.Entry<String, Object>> mEntries;

        public FieldMap(final Object obj) {
            this.mEntries = FluentIterable.from(obj.getClass().getDeclaredFields()).transform(new Function<Field, Map.Entry<String, Object>>() {
                /* class com.facebook.common.util.ReflectionUtils.FieldMap.AnonymousClass1 */

                public Map.Entry<String, Object> apply(final Field field) {
                    return new Map.Entry<String, Object>() {
                        /* class com.facebook.common.util.ReflectionUtils.FieldMap.AnonymousClass1.AnonymousClass1 */

                        @Override // java.util.Map.Entry
                        public String getKey() {
                            return field.getName();
                        }

                        @Override // java.util.Map.Entry
                        public Object getValue() {
                            return ReflectionUtils.readField(obj, field);
                        }

                        @Override // java.util.Map.Entry
                        public Object setValue(Object obj) {
                            Object value = getValue();
                            ReflectionUtils.writeField(obj, field, obj);
                            return value;
                        }
                    };
                }
            }).toSet();
        }

        /* Return type fixed from 'com.google.common.collect.ImmutableSet<java.util.Map$Entry<java.lang.String, java.lang.Object>>' to match base method */
        @Override // java.util.AbstractMap, java.util.Map
        public Set<Map.Entry<String, Object>> entrySet() {
            return this.mEntries;
        }
    }
}
