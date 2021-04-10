package com.google.common.reflect;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.UnmodifiableIterator;
import java.io.Serializable;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.security.AccessControlException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicReference;
import javax.annotation.Nullable;

/* access modifiers changed from: package-private */
public final class Types {
    private static final Joiner COMMA_JOINER = Joiner.on(", ").useForNull("null");
    private static final Function<Type, String> TYPE_NAME = new Function<Type, String>() {
        /* class com.google.common.reflect.Types.AnonymousClass1 */

        public String apply(Type from) {
            return JavaVersion.CURRENT.typeName(from);
        }
    };

    static Type newArrayType(Type componentType) {
        if (!(componentType instanceof WildcardType)) {
            return JavaVersion.CURRENT.newArrayType(componentType);
        }
        WildcardType wildcard = (WildcardType) componentType;
        Type[] lowerBounds = wildcard.getLowerBounds();
        boolean z = true;
        Preconditions.checkArgument(lowerBounds.length <= 1, "Wildcard cannot have more than one lower bounds.");
        if (lowerBounds.length == 1) {
            return supertypeOf(newArrayType(lowerBounds[0]));
        }
        Type[] upperBounds = wildcard.getUpperBounds();
        if (upperBounds.length != 1) {
            z = false;
        }
        Preconditions.checkArgument(z, "Wildcard should have only one upper bound.");
        return subtypeOf(newArrayType(upperBounds[0]));
    }

    static ParameterizedType newParameterizedTypeWithOwner(@Nullable Type ownerType, Class<?> rawType, Type... arguments) {
        if (ownerType == null) {
            return newParameterizedType(rawType, arguments);
        }
        Preconditions.checkNotNull(arguments);
        Preconditions.checkArgument(rawType.getEnclosingClass() != null, "Owner type for unenclosed %s", rawType);
        return new ParameterizedTypeImpl(ownerType, rawType, arguments);
    }

    static ParameterizedType newParameterizedType(Class<?> rawType, Type... arguments) {
        return new ParameterizedTypeImpl(ClassOwnership.JVM_BEHAVIOR.getOwnerType(rawType), rawType, arguments);
    }

    /* access modifiers changed from: private */
    public enum ClassOwnership {
        OWNED_BY_ENCLOSING_CLASS {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.Types.ClassOwnership
            @Nullable
            public Class<?> getOwnerType(Class<?> rawType) {
                return rawType.getEnclosingClass();
            }
        },
        LOCAL_CLASS_HAS_NO_OWNER {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.Types.ClassOwnership
            @Nullable
            public Class<?> getOwnerType(Class<?> rawType) {
                if (rawType.isLocalClass()) {
                    return null;
                }
                return rawType.getEnclosingClass();
            }
        };
        
        static final ClassOwnership JVM_BEHAVIOR = detectJvmBehavior();

        /* access modifiers changed from: package-private */
        @Nullable
        public abstract Class<?> getOwnerType(Class<?> cls);

        private static ClassOwnership detectJvmBehavior() {
            ParameterizedType parameterizedType = (ParameterizedType) new AnonymousClass1LocalClass<String>() {
                /* class com.google.common.reflect.Types.ClassOwnership.AnonymousClass3 */
            }.getClass().getGenericSuperclass();
            ClassOwnership[] values = values();
            for (ClassOwnership behavior : values) {
                if (behavior.getOwnerType(AnonymousClass1LocalClass.class) == parameterizedType.getOwnerType()) {
                    return behavior;
                }
            }
            throw new AssertionError();
        }
    }

    static <D extends GenericDeclaration> TypeVariable<D> newArtificialTypeVariable(D declaration, String name, Type... bounds) {
        return newTypeVariableImpl(declaration, name, bounds.length == 0 ? new Type[]{Object.class} : bounds);
    }

    @VisibleForTesting
    static WildcardType subtypeOf(Type upperBound) {
        return new WildcardTypeImpl(new Type[0], new Type[]{upperBound});
    }

    @VisibleForTesting
    static WildcardType supertypeOf(Type lowerBound) {
        return new WildcardTypeImpl(new Type[]{lowerBound}, new Type[]{Object.class});
    }

    static String toString(Type type) {
        if (type instanceof Class) {
            return ((Class) type).getName();
        }
        return type.toString();
    }

    @Nullable
    static Type getComponentType(Type type) {
        Preconditions.checkNotNull(type);
        final AtomicReference<Type> result = new AtomicReference<>();
        new TypeVisitor() {
            /* class com.google.common.reflect.Types.AnonymousClass2 */

            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.TypeVisitor
            public void visitTypeVariable(TypeVariable<?> t) {
                result.set(Types.subtypeOfComponentType(t.getBounds()));
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.TypeVisitor
            public void visitWildcardType(WildcardType t) {
                result.set(Types.subtypeOfComponentType(t.getUpperBounds()));
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.TypeVisitor
            public void visitGenericArrayType(GenericArrayType t) {
                result.set(t.getGenericComponentType());
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.TypeVisitor
            public void visitClass(Class<?> t) {
                result.set(t.getComponentType());
            }
        }.visit(type);
        return result.get();
    }

    /* access modifiers changed from: private */
    @Nullable
    public static Type subtypeOfComponentType(Type[] bounds) {
        for (Type bound : bounds) {
            Type componentType = getComponentType(bound);
            if (componentType != null) {
                if (componentType instanceof Class) {
                    Class<?> componentClass = (Class) componentType;
                    if (componentClass.isPrimitive()) {
                        return componentClass;
                    }
                }
                return subtypeOf(componentType);
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public static final class GenericArrayTypeImpl implements GenericArrayType, Serializable {
        private static final long serialVersionUID = 0;
        private final Type componentType;

        GenericArrayTypeImpl(Type componentType2) {
            this.componentType = JavaVersion.CURRENT.usedInGenericType(componentType2);
        }

        public Type getGenericComponentType() {
            return this.componentType;
        }

        public String toString() {
            return Types.toString(this.componentType) + "[]";
        }

        public int hashCode() {
            return this.componentType.hashCode();
        }

        public boolean equals(Object obj) {
            if (obj instanceof GenericArrayType) {
                return Objects.equal(getGenericComponentType(), ((GenericArrayType) obj).getGenericComponentType());
            }
            return false;
        }
    }

    /* access modifiers changed from: private */
    public static final class ParameterizedTypeImpl implements ParameterizedType, Serializable {
        private static final long serialVersionUID = 0;
        private final ImmutableList<Type> argumentsList;
        private final Type ownerType;
        private final Class<?> rawType;

        ParameterizedTypeImpl(@Nullable Type ownerType2, Class<?> rawType2, Type[] typeArguments) {
            Preconditions.checkNotNull(rawType2);
            Preconditions.checkArgument(typeArguments.length == rawType2.getTypeParameters().length);
            Types.disallowPrimitiveType(typeArguments, "type parameter");
            this.ownerType = ownerType2;
            this.rawType = rawType2;
            this.argumentsList = JavaVersion.CURRENT.usedInGenericType(typeArguments);
        }

        public Type[] getActualTypeArguments() {
            return Types.toArray(this.argumentsList);
        }

        public Type getRawType() {
            return this.rawType;
        }

        public Type getOwnerType() {
            return this.ownerType;
        }

        public String toString() {
            StringBuilder builder = new StringBuilder();
            if (this.ownerType != null) {
                builder.append(JavaVersion.CURRENT.typeName(this.ownerType));
                builder.append('.');
            }
            builder.append(this.rawType.getName());
            builder.append('<');
            builder.append(Types.COMMA_JOINER.join(Iterables.transform(this.argumentsList, Types.TYPE_NAME)));
            builder.append('>');
            return builder.toString();
        }

        public int hashCode() {
            Type type = this.ownerType;
            return ((type == null ? 0 : type.hashCode()) ^ this.argumentsList.hashCode()) ^ this.rawType.hashCode();
        }

        public boolean equals(Object other) {
            if (!(other instanceof ParameterizedType)) {
                return false;
            }
            ParameterizedType that = (ParameterizedType) other;
            if (!getRawType().equals(that.getRawType()) || !Objects.equal(getOwnerType(), that.getOwnerType()) || !Arrays.equals(getActualTypeArguments(), that.getActualTypeArguments())) {
                return false;
            }
            return true;
        }
    }

    private static <D extends GenericDeclaration> TypeVariable<D> newTypeVariableImpl(D genericDeclaration, String name, Type[] bounds) {
        return (TypeVariable) Reflection.newProxy(TypeVariable.class, new TypeVariableInvocationHandler(new TypeVariableImpl<>(genericDeclaration, name, bounds)));
    }

    /* access modifiers changed from: private */
    public static final class TypeVariableInvocationHandler implements InvocationHandler {
        private static final ImmutableMap<String, Method> typeVariableMethods;
        private final TypeVariableImpl<?> typeVariableImpl;

        static {
            ImmutableMap.Builder<String, Method> builder = ImmutableMap.builder();
            Method[] methods = TypeVariableImpl.class.getMethods();
            for (Method method : methods) {
                if (method.getDeclaringClass().equals(TypeVariableImpl.class)) {
                    try {
                        method.setAccessible(true);
                    } catch (AccessControlException e) {
                    }
                    builder.put(method.getName(), method);
                }
            }
            typeVariableMethods = builder.build();
        }

        TypeVariableInvocationHandler(TypeVariableImpl<?> typeVariableImpl2) {
            this.typeVariableImpl = typeVariableImpl2;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String methodName = method.getName();
            Method typeVariableMethod = typeVariableMethods.get(methodName);
            if (typeVariableMethod != null) {
                try {
                    return typeVariableMethod.invoke(this.typeVariableImpl, args);
                } catch (InvocationTargetException e) {
                    throw e.getCause();
                }
            } else {
                throw new UnsupportedOperationException(methodName);
            }
        }
    }

    /* access modifiers changed from: private */
    public static final class TypeVariableImpl<D extends GenericDeclaration> {
        private final ImmutableList<Type> bounds;
        private final D genericDeclaration;
        private final String name;

        TypeVariableImpl(D genericDeclaration2, String name2, Type[] bounds2) {
            Types.disallowPrimitiveType(bounds2, "bound for type variable");
            this.genericDeclaration = (D) ((GenericDeclaration) Preconditions.checkNotNull(genericDeclaration2));
            this.name = (String) Preconditions.checkNotNull(name2);
            this.bounds = ImmutableList.copyOf(bounds2);
        }

        public Type[] getBounds() {
            return Types.toArray(this.bounds);
        }

        public D getGenericDeclaration() {
            return this.genericDeclaration;
        }

        public String getName() {
            return this.name;
        }

        public String getTypeName() {
            return this.name;
        }

        public String toString() {
            return this.name;
        }

        public int hashCode() {
            return this.genericDeclaration.hashCode() ^ this.name.hashCode();
        }

        public boolean equals(Object obj) {
            if (NativeTypeVariableEquals.NATIVE_TYPE_VARIABLE_ONLY) {
                if (obj == null || !Proxy.isProxyClass(obj.getClass()) || !(Proxy.getInvocationHandler(obj) instanceof TypeVariableInvocationHandler)) {
                    return false;
                }
                TypeVariableImpl<?> that = ((TypeVariableInvocationHandler) Proxy.getInvocationHandler(obj)).typeVariableImpl;
                return this.name.equals(that.getName()) && this.genericDeclaration.equals(that.getGenericDeclaration()) && this.bounds.equals(that.bounds);
            } else if (!(obj instanceof TypeVariable)) {
                return false;
            } else {
                TypeVariable<?> that2 = (TypeVariable) obj;
                return this.name.equals(that2.getName()) && this.genericDeclaration.equals(that2.getGenericDeclaration());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public static final class WildcardTypeImpl implements WildcardType, Serializable {
        private static final long serialVersionUID = 0;
        private final ImmutableList<Type> lowerBounds;
        private final ImmutableList<Type> upperBounds;

        WildcardTypeImpl(Type[] lowerBounds2, Type[] upperBounds2) {
            Types.disallowPrimitiveType(lowerBounds2, "lower bound for wildcard");
            Types.disallowPrimitiveType(upperBounds2, "upper bound for wildcard");
            this.lowerBounds = JavaVersion.CURRENT.usedInGenericType(lowerBounds2);
            this.upperBounds = JavaVersion.CURRENT.usedInGenericType(upperBounds2);
        }

        public Type[] getLowerBounds() {
            return Types.toArray(this.lowerBounds);
        }

        public Type[] getUpperBounds() {
            return Types.toArray(this.upperBounds);
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof WildcardType)) {
                return false;
            }
            WildcardType that = (WildcardType) obj;
            if (!this.lowerBounds.equals(Arrays.asList(that.getLowerBounds())) || !this.upperBounds.equals(Arrays.asList(that.getUpperBounds()))) {
                return false;
            }
            return true;
        }

        public int hashCode() {
            return this.lowerBounds.hashCode() ^ this.upperBounds.hashCode();
        }

        public String toString() {
            StringBuilder builder = new StringBuilder("?");
            UnmodifiableIterator<Type> it = this.lowerBounds.iterator();
            while (it.hasNext()) {
                builder.append(" super ");
                builder.append(JavaVersion.CURRENT.typeName(it.next()));
            }
            for (Type upperBound : Types.filterUpperBounds(this.upperBounds)) {
                builder.append(" extends ");
                builder.append(JavaVersion.CURRENT.typeName(upperBound));
            }
            return builder.toString();
        }
    }

    /* access modifiers changed from: private */
    public static Type[] toArray(Collection<Type> types) {
        return (Type[]) types.toArray(new Type[types.size()]);
    }

    /* access modifiers changed from: private */
    public static Iterable<Type> filterUpperBounds(Iterable<Type> bounds) {
        return Iterables.filter(bounds, Predicates.not(Predicates.equalTo(Object.class)));
    }

    /* access modifiers changed from: private */
    public static void disallowPrimitiveType(Type[] types, String usedAs) {
        for (Type type : types) {
            if (type instanceof Class) {
                Class<?> cls = (Class) type;
                Preconditions.checkArgument(!cls.isPrimitive(), "Primitive type '%s' used as %s", cls, usedAs);
            }
        }
    }

    static Class<?> getArrayClass(Class<?> componentType) {
        return Array.newInstance(componentType, 0).getClass();
    }

    /* access modifiers changed from: package-private */
    public enum JavaVersion {
        JAVA6 {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.Types.JavaVersion
            public GenericArrayType newArrayType(Type componentType) {
                return new GenericArrayTypeImpl(componentType);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.Types.JavaVersion
            public Type usedInGenericType(Type type) {
                Preconditions.checkNotNull(type);
                if (type instanceof Class) {
                    Class<?> cls = (Class) type;
                    if (cls.isArray()) {
                        return new GenericArrayTypeImpl(cls.getComponentType());
                    }
                }
                return type;
            }
        },
        JAVA7 {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.Types.JavaVersion
            public Type newArrayType(Type componentType) {
                if (componentType instanceof Class) {
                    return Types.getArrayClass((Class) componentType);
                }
                return new GenericArrayTypeImpl(componentType);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.Types.JavaVersion
            public Type usedInGenericType(Type type) {
                return (Type) Preconditions.checkNotNull(type);
            }
        },
        JAVA8 {
            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.Types.JavaVersion
            public Type newArrayType(Type componentType) {
                return JAVA7.newArrayType(componentType);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.Types.JavaVersion
            public Type usedInGenericType(Type type) {
                return JAVA7.usedInGenericType(type);
            }

            /* access modifiers changed from: package-private */
            @Override // com.google.common.reflect.Types.JavaVersion
            public String typeName(Type type) {
                try {
                    return (String) Type.class.getMethod("getTypeName", new Class[0]).invoke(type, new Object[0]);
                } catch (NoSuchMethodException e) {
                    throw new AssertionError("Type.getTypeName should be available in Java 8");
                } catch (InvocationTargetException e2) {
                    throw new RuntimeException(e2);
                } catch (IllegalAccessException e3) {
                    throw new RuntimeException(e3);
                }
            }
        };
        
        static final JavaVersion CURRENT;

        /* access modifiers changed from: package-private */
        public abstract Type newArrayType(Type type);

        /* access modifiers changed from: package-private */
        public abstract Type usedInGenericType(Type type);

        static {
            if (AnnotatedElement.class.isAssignableFrom(TypeVariable.class)) {
                CURRENT = JAVA8;
            } else if (new TypeCapture<int[]>() {
                /* class com.google.common.reflect.Types.JavaVersion.AnonymousClass4 */
            }.capture() instanceof Class) {
                CURRENT = JAVA7;
            } else {
                CURRENT = JAVA6;
            }
        }

        /* access modifiers changed from: package-private */
        public String typeName(Type type) {
            return Types.toString(type);
        }

        /* access modifiers changed from: package-private */
        public final ImmutableList<Type> usedInGenericType(Type[] types) {
            ImmutableList.Builder<Type> builder = ImmutableList.builder();
            for (Type type : types) {
                builder.add(usedInGenericType(type));
            }
            return builder.build();
        }
    }

    static final class NativeTypeVariableEquals<X> {
        static final boolean NATIVE_TYPE_VARIABLE_ONLY = (!NativeTypeVariableEquals.class.getTypeParameters()[0].equals(Types.newArtificialTypeVariable(NativeTypeVariableEquals.class, "X", new Type[0])));

        NativeTypeVariableEquals() {
        }
    }

    private Types() {
    }
}
