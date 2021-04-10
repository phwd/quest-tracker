package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.reflect.Types;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Beta
public final class TypeResolver {
    private final TypeTable typeTable;

    public TypeResolver() {
        this.typeTable = new TypeTable();
    }

    private TypeResolver(TypeTable typeTable2) {
        this.typeTable = typeTable2;
    }

    static TypeResolver covariantly(Type contextType) {
        return new TypeResolver().where(TypeMappingIntrospector.getTypeMappings(contextType));
    }

    static TypeResolver invariantly(Type contextType) {
        return new TypeResolver().where(TypeMappingIntrospector.getTypeMappings(WildcardCapturer.INSTANCE.capture(contextType)));
    }

    public TypeResolver where(Type formal, Type actual) {
        Map<TypeVariableKey, Type> mappings = Maps.newHashMap();
        populateTypeMappings(mappings, (Type) Preconditions.checkNotNull(formal), (Type) Preconditions.checkNotNull(actual));
        return where(mappings);
    }

    /* access modifiers changed from: package-private */
    public TypeResolver where(Map<TypeVariableKey, ? extends Type> mappings) {
        return new TypeResolver(this.typeTable.where(mappings));
    }

    /* access modifiers changed from: private */
    public static void populateTypeMappings(final Map<TypeVariableKey, Type> mappings, Type from, final Type to) {
        if (!from.equals(to)) {
            new TypeVisitor() {
                /* class com.google.common.reflect.TypeResolver.AnonymousClass1 */

                /* access modifiers changed from: package-private */
                @Override // com.google.common.reflect.TypeVisitor
                public void visitTypeVariable(TypeVariable<?> typeVariable) {
                    mappings.put(new TypeVariableKey(typeVariable), to);
                }

                /* access modifiers changed from: package-private */
                @Override // com.google.common.reflect.TypeVisitor
                public void visitWildcardType(WildcardType fromWildcardType) {
                    if (to instanceof WildcardType) {
                        WildcardType toWildcardType = (WildcardType) to;
                        Type[] fromUpperBounds = fromWildcardType.getUpperBounds();
                        Type[] toUpperBounds = toWildcardType.getUpperBounds();
                        Type[] fromLowerBounds = fromWildcardType.getLowerBounds();
                        Type[] toLowerBounds = toWildcardType.getLowerBounds();
                        Preconditions.checkArgument(fromUpperBounds.length == toUpperBounds.length && fromLowerBounds.length == toLowerBounds.length, "Incompatible type: %s vs. %s", fromWildcardType, to);
                        for (int i = 0; i < fromUpperBounds.length; i++) {
                            TypeResolver.populateTypeMappings(mappings, fromUpperBounds[i], toUpperBounds[i]);
                        }
                        for (int i2 = 0; i2 < fromLowerBounds.length; i2++) {
                            TypeResolver.populateTypeMappings(mappings, fromLowerBounds[i2], toLowerBounds[i2]);
                        }
                    }
                }

                /* access modifiers changed from: package-private */
                @Override // com.google.common.reflect.TypeVisitor
                public void visitParameterizedType(ParameterizedType fromParameterizedType) {
                    if (!(to instanceof WildcardType)) {
                        ParameterizedType toParameterizedType = (ParameterizedType) TypeResolver.expectArgument(ParameterizedType.class, to);
                        if (!(fromParameterizedType.getOwnerType() == null || toParameterizedType.getOwnerType() == null)) {
                            TypeResolver.populateTypeMappings(mappings, fromParameterizedType.getOwnerType(), toParameterizedType.getOwnerType());
                        }
                        Preconditions.checkArgument(fromParameterizedType.getRawType().equals(toParameterizedType.getRawType()), "Inconsistent raw type: %s vs. %s", fromParameterizedType, to);
                        Type[] fromArgs = fromParameterizedType.getActualTypeArguments();
                        Type[] toArgs = toParameterizedType.getActualTypeArguments();
                        Preconditions.checkArgument(fromArgs.length == toArgs.length, "%s not compatible with %s", fromParameterizedType, toParameterizedType);
                        for (int i = 0; i < fromArgs.length; i++) {
                            TypeResolver.populateTypeMappings(mappings, fromArgs[i], toArgs[i]);
                        }
                    }
                }

                /* access modifiers changed from: package-private */
                @Override // com.google.common.reflect.TypeVisitor
                public void visitGenericArrayType(GenericArrayType fromArrayType) {
                    if (!(to instanceof WildcardType)) {
                        Type componentType = Types.getComponentType(to);
                        Preconditions.checkArgument(componentType != null, "%s is not an array type.", to);
                        TypeResolver.populateTypeMappings(mappings, fromArrayType.getGenericComponentType(), componentType);
                    }
                }

                /* access modifiers changed from: package-private */
                @Override // com.google.common.reflect.TypeVisitor
                public void visitClass(Class<?> fromClass) {
                    if (!(to instanceof WildcardType)) {
                        throw new IllegalArgumentException("No type mapping from " + fromClass + " to " + to);
                    }
                }
            }.visit(from);
        }
    }

    public Type resolveType(Type type) {
        Preconditions.checkNotNull(type);
        if (type instanceof TypeVariable) {
            return this.typeTable.resolve((TypeVariable) type);
        }
        if (type instanceof ParameterizedType) {
            return resolveParameterizedType((ParameterizedType) type);
        }
        if (type instanceof GenericArrayType) {
            return resolveGenericArrayType((GenericArrayType) type);
        }
        if (type instanceof WildcardType) {
            return resolveWildcardType((WildcardType) type);
        }
        return type;
    }

    /* access modifiers changed from: package-private */
    public Type[] resolveTypesInPlace(Type[] types) {
        for (int i = 0; i < types.length; i++) {
            types[i] = resolveType(types[i]);
        }
        return types;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private Type[] resolveTypes(Type[] types) {
        Type[] result = new Type[types.length];
        for (int i = 0; i < types.length; i++) {
            result[i] = resolveType(types[i]);
        }
        return result;
    }

    private WildcardType resolveWildcardType(WildcardType type) {
        return new Types.WildcardTypeImpl(resolveTypes(type.getLowerBounds()), resolveTypes(type.getUpperBounds()));
    }

    private Type resolveGenericArrayType(GenericArrayType type) {
        return Types.newArrayType(resolveType(type.getGenericComponentType()));
    }

    private ParameterizedType resolveParameterizedType(ParameterizedType type) {
        Type owner = type.getOwnerType();
        return Types.newParameterizedTypeWithOwner(owner == null ? null : resolveType(owner), (Class) resolveType(type.getRawType()), resolveTypes(type.getActualTypeArguments()));
    }

    /* access modifiers changed from: private */
    public static <T> T expectArgument(Class<T> type, Object arg) {
        try {
            return type.cast(arg);
        } catch (ClassCastException e) {
            throw new IllegalArgumentException(arg + " is not a " + type.getSimpleName());
        }
    }

    /* access modifiers changed from: private */
    public static class TypeTable {
        private final ImmutableMap<TypeVariableKey, Type> map;

        TypeTable() {
            this.map = ImmutableMap.of();
        }

        private TypeTable(ImmutableMap<TypeVariableKey, Type> map2) {
            this.map = map2;
        }

        /* access modifiers changed from: package-private */
        public final TypeTable where(Map<TypeVariableKey, ? extends Type> mappings) {
            ImmutableMap.Builder<TypeVariableKey, Type> builder = ImmutableMap.builder();
            builder.putAll(this.map);
            for (Map.Entry<TypeVariableKey, ? extends Type> mapping : mappings.entrySet()) {
                TypeVariableKey variable = mapping.getKey();
                Type type = (Type) mapping.getValue();
                Preconditions.checkArgument(!variable.equalsType(type), "Type variable %s bound to itself", variable);
                builder.put(variable, type);
            }
            return new TypeTable(builder.build());
        }

        /* access modifiers changed from: package-private */
        public final Type resolve(final TypeVariable<?> var) {
            return resolveInternal(var, new TypeTable() {
                /* class com.google.common.reflect.TypeResolver.TypeTable.AnonymousClass1 */

                @Override // com.google.common.reflect.TypeResolver.TypeTable
                public Type resolveInternal(TypeVariable<?> intermediateVar, TypeTable forDependent) {
                    return intermediateVar.getGenericDeclaration().equals(var.getGenericDeclaration()) ? intermediateVar : this.resolveInternal(intermediateVar, forDependent);
                }
            });
        }

        /* access modifiers changed from: package-private */
        public Type resolveInternal(TypeVariable<?> var, TypeTable forDependants) {
            Type type = this.map.get(new TypeVariableKey(var));
            if (type != null) {
                return new TypeResolver(forDependants).resolveType(type);
            }
            Type[] bounds = var.getBounds();
            if (bounds.length == 0) {
                return var;
            }
            Type[] resolvedBounds = new TypeResolver(forDependants).resolveTypes(bounds);
            if (!Types.NativeTypeVariableEquals.NATIVE_TYPE_VARIABLE_ONLY || !Arrays.equals(bounds, resolvedBounds)) {
                return Types.newArtificialTypeVariable(var.getGenericDeclaration(), var.getName(), resolvedBounds);
            }
            return var;
        }
    }

    /* access modifiers changed from: private */
    public static final class TypeMappingIntrospector extends TypeVisitor {
        private final Map<TypeVariableKey, Type> mappings = Maps.newHashMap();

        private TypeMappingIntrospector() {
        }

        static ImmutableMap<TypeVariableKey, Type> getTypeMappings(Type contextType) {
            Preconditions.checkNotNull(contextType);
            TypeMappingIntrospector introspector = new TypeMappingIntrospector();
            introspector.visit(contextType);
            return ImmutableMap.copyOf(introspector.mappings);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.TypeVisitor
        public void visitClass(Class<?> clazz) {
            visit(clazz.getGenericSuperclass());
            visit(clazz.getGenericInterfaces());
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.TypeVisitor
        public void visitParameterizedType(ParameterizedType parameterizedType) {
            Class<?> rawClass = (Class) parameterizedType.getRawType();
            TypeVariable<?>[] vars = rawClass.getTypeParameters();
            Type[] typeArgs = parameterizedType.getActualTypeArguments();
            Preconditions.checkState(vars.length == typeArgs.length);
            for (int i = 0; i < vars.length; i++) {
                map(new TypeVariableKey(vars[i]), typeArgs[i]);
            }
            visit(rawClass);
            visit(parameterizedType.getOwnerType());
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.TypeVisitor
        public void visitTypeVariable(TypeVariable<?> t) {
            visit(t.getBounds());
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.TypeVisitor
        public void visitWildcardType(WildcardType t) {
            visit(t.getUpperBounds());
        }

        private void map(TypeVariableKey var, Type arg) {
            if (!this.mappings.containsKey(var)) {
                Type t = arg;
                while (t != null) {
                    if (var.equalsType(t)) {
                        Type x = arg;
                        while (x != null) {
                            x = this.mappings.remove(TypeVariableKey.forLookup(x));
                        }
                        return;
                    }
                    t = this.mappings.get(TypeVariableKey.forLookup(t));
                }
                this.mappings.put(var, arg);
            }
        }
    }

    /* access modifiers changed from: private */
    public static class WildcardCapturer {
        static final WildcardCapturer INSTANCE = new WildcardCapturer();
        private final AtomicInteger id;

        private WildcardCapturer() {
            this(new AtomicInteger());
        }

        private WildcardCapturer(AtomicInteger id2) {
            this.id = id2;
        }

        /* access modifiers changed from: package-private */
        public final Type capture(Type type) {
            Preconditions.checkNotNull(type);
            if ((type instanceof Class) || (type instanceof TypeVariable)) {
                return type;
            }
            if (type instanceof GenericArrayType) {
                return Types.newArrayType(notForTypeVariable().capture(((GenericArrayType) type).getGenericComponentType()));
            }
            if (type instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) type;
                Class<?> rawType = (Class) parameterizedType.getRawType();
                TypeVariable<?>[] typeVars = rawType.getTypeParameters();
                Type[] typeArgs = parameterizedType.getActualTypeArguments();
                for (int i = 0; i < typeArgs.length; i++) {
                    typeArgs[i] = forTypeVariable(typeVars[i]).capture(typeArgs[i]);
                }
                return Types.newParameterizedTypeWithOwner(notForTypeVariable().captureNullable(parameterizedType.getOwnerType()), rawType, typeArgs);
            } else if (type instanceof WildcardType) {
                WildcardType wildcardType = (WildcardType) type;
                if (wildcardType.getLowerBounds().length == 0) {
                    return captureAsTypeVariable(wildcardType.getUpperBounds());
                }
                return type;
            } else {
                throw new AssertionError("must have been one of the known types");
            }
        }

        /* access modifiers changed from: package-private */
        public TypeVariable<?> captureAsTypeVariable(Type[] upperBounds) {
            return Types.newArtificialTypeVariable(WildcardCapturer.class, "capture#" + this.id.incrementAndGet() + "-of ? extends " + Joiner.on('&').join(upperBounds), upperBounds);
        }

        private WildcardCapturer forTypeVariable(final TypeVariable<?> typeParam) {
            return new WildcardCapturer(this.id) {
                /* class com.google.common.reflect.TypeResolver.WildcardCapturer.AnonymousClass1 */

                /* access modifiers changed from: package-private */
                @Override // com.google.common.reflect.TypeResolver.WildcardCapturer
                public TypeVariable<?> captureAsTypeVariable(Type[] upperBounds) {
                    Set<Type> combined = new LinkedHashSet<>(Arrays.asList(upperBounds));
                    combined.addAll(Arrays.asList(typeParam.getBounds()));
                    if (combined.size() > 1) {
                        combined.remove(Object.class);
                    }
                    return super.captureAsTypeVariable((Type[]) combined.toArray(new Type[0]));
                }
            };
        }

        private WildcardCapturer notForTypeVariable() {
            return new WildcardCapturer(this.id);
        }

        private Type captureNullable(@NullableDecl Type type) {
            if (type == null) {
                return null;
            }
            return capture(type);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class TypeVariableKey {
        private final TypeVariable<?> var;

        TypeVariableKey(TypeVariable<?> var2) {
            this.var = (TypeVariable) Preconditions.checkNotNull(var2);
        }

        public int hashCode() {
            return Objects.hashCode(this.var.getGenericDeclaration(), this.var.getName());
        }

        public boolean equals(Object obj) {
            if (obj instanceof TypeVariableKey) {
                return equalsTypeVariable(((TypeVariableKey) obj).var);
            }
            return false;
        }

        public String toString() {
            return this.var.toString();
        }

        static TypeVariableKey forLookup(Type t) {
            if (t instanceof TypeVariable) {
                return new TypeVariableKey((TypeVariable) t);
            }
            return null;
        }

        /* access modifiers changed from: package-private */
        public boolean equalsType(Type type) {
            if (type instanceof TypeVariable) {
                return equalsTypeVariable((TypeVariable) type);
            }
            return false;
        }

        private boolean equalsTypeVariable(TypeVariable<?> that) {
            return this.var.getGenericDeclaration().equals(that.getGenericDeclaration()) && this.var.getName().equals(that.getName());
        }
    }
}
