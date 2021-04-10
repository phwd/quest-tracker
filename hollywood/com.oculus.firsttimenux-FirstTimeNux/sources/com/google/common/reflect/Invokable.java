package com.google.common.reflect;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

@Beta
public abstract class Invokable<T, R> extends Element implements GenericDeclaration {
    /* access modifiers changed from: package-private */
    public abstract Type[] getGenericExceptionTypes();

    /* access modifiers changed from: package-private */
    public abstract Type[] getGenericParameterTypes();

    /* access modifiers changed from: package-private */
    public abstract Type getGenericReturnType();

    /* access modifiers changed from: package-private */
    public abstract Annotation[][] getParameterAnnotations();

    /* access modifiers changed from: package-private */
    public abstract Object invokeInternal(@NullableDecl Object obj, Object[] objArr) throws InvocationTargetException, IllegalAccessException;

    public abstract boolean isOverridable();

    public abstract boolean isVarArgs();

    @Override // com.google.common.reflect.Element
    public /* bridge */ /* synthetic */ boolean equals(@NullableDecl Object obj) {
        return super.equals(obj);
    }

    @Override // com.google.common.reflect.Element
    public /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    @Override // com.google.common.reflect.Element
    public /* bridge */ /* synthetic */ String toString() {
        return super.toString();
    }

    <M extends AccessibleObject & Member> Invokable(M member) {
        super(member);
    }

    public static Invokable<?, Object> from(Method method) {
        return new MethodInvokable(method);
    }

    public static <T> Invokable<T, T> from(Constructor<T> constructor) {
        return new ConstructorInvokable(constructor);
    }

    @CanIgnoreReturnValue
    public final R invoke(@NullableDecl T receiver, Object... args) throws InvocationTargetException, IllegalAccessException {
        return (R) invokeInternal(receiver, (Object[]) Preconditions.checkNotNull(args));
    }

    public final TypeToken<? extends R> getReturnType() {
        return (TypeToken<? extends R>) TypeToken.of(getGenericReturnType());
    }

    public final ImmutableList<Parameter> getParameters() {
        Type[] parameterTypes = getGenericParameterTypes();
        Annotation[][] annotations = getParameterAnnotations();
        ImmutableList.Builder<Parameter> builder = ImmutableList.builder();
        for (int i = 0; i < parameterTypes.length; i++) {
            builder.add(new Parameter(this, i, TypeToken.of(parameterTypes[i]), annotations[i]));
        }
        return builder.build();
    }

    public final ImmutableList<TypeToken<? extends Throwable>> getExceptionTypes() {
        ImmutableList.Builder builder = ImmutableList.builder();
        for (Type type : getGenericExceptionTypes()) {
            builder.add((Object) TypeToken.of(type));
        }
        return builder.build();
    }

    public final <R1 extends R> Invokable<T, R1> returning(Class<R1> returnType) {
        return returning(TypeToken.of((Class) returnType));
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: com.google.common.reflect.Invokable<T, R> */
    /* JADX WARN: Multi-variable type inference failed */
    public final <R1 extends R> Invokable<T, R1> returning(TypeToken<R1> returnType) {
        if (returnType.isSupertypeOf(getReturnType())) {
            return this;
        }
        throw new IllegalArgumentException("Invokable is known to return " + getReturnType() + ", not " + returnType);
    }

    @Override // com.google.common.reflect.Element, java.lang.reflect.Member
    public final Class<? super T> getDeclaringClass() {
        return (Class<? super T>) super.getDeclaringClass();
    }

    @Override // com.google.common.reflect.Element
    public TypeToken<T> getOwnerType() {
        return TypeToken.of((Class) getDeclaringClass());
    }

    /* access modifiers changed from: package-private */
    public static class MethodInvokable<T> extends Invokable<T, Object> {
        final Method method;

        MethodInvokable(Method method2) {
            super(method2);
            this.method = method2;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.Invokable
        public final Object invokeInternal(@NullableDecl Object receiver, Object[] args) throws InvocationTargetException, IllegalAccessException {
            return this.method.invoke(receiver, args);
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.Invokable
        public Type getGenericReturnType() {
            return this.method.getGenericReturnType();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.Invokable
        public Type[] getGenericParameterTypes() {
            return this.method.getGenericParameterTypes();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.Invokable
        public Type[] getGenericExceptionTypes() {
            return this.method.getGenericExceptionTypes();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.Invokable
        public final Annotation[][] getParameterAnnotations() {
            return this.method.getParameterAnnotations();
        }

        @Override // java.lang.reflect.GenericDeclaration
        public final TypeVariable<?>[] getTypeParameters() {
            return this.method.getTypeParameters();
        }

        @Override // com.google.common.reflect.Invokable
        public final boolean isOverridable() {
            return !isFinal() && !isPrivate() && !isStatic() && !Modifier.isFinal(getDeclaringClass().getModifiers());
        }

        @Override // com.google.common.reflect.Invokable
        public final boolean isVarArgs() {
            return this.method.isVarArgs();
        }
    }

    /* access modifiers changed from: package-private */
    public static class ConstructorInvokable<T> extends Invokable<T, T> {
        final Constructor<?> constructor;

        ConstructorInvokable(Constructor<?> constructor2) {
            super(constructor2);
            this.constructor = constructor2;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.Invokable
        public final Object invokeInternal(@NullableDecl Object receiver, Object[] args) throws InvocationTargetException, IllegalAccessException {
            try {
                return this.constructor.newInstance(args);
            } catch (InstantiationException e) {
                throw new RuntimeException(this.constructor + " failed.", e);
            }
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.Invokable
        public Type getGenericReturnType() {
            Class<?> declaringClass = getDeclaringClass();
            TypeVariable<?>[] typeParams = declaringClass.getTypeParameters();
            if (typeParams.length > 0) {
                return Types.newParameterizedType(declaringClass, typeParams);
            }
            return declaringClass;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.Invokable
        public Type[] getGenericParameterTypes() {
            Type[] types = this.constructor.getGenericParameterTypes();
            if (types.length > 0 && mayNeedHiddenThis()) {
                Class<?>[] rawParamTypes = this.constructor.getParameterTypes();
                if (types.length == rawParamTypes.length && rawParamTypes[0] == getDeclaringClass().getEnclosingClass()) {
                    return (Type[]) Arrays.copyOfRange(types, 1, types.length);
                }
            }
            return types;
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.Invokable
        public Type[] getGenericExceptionTypes() {
            return this.constructor.getGenericExceptionTypes();
        }

        /* access modifiers changed from: package-private */
        @Override // com.google.common.reflect.Invokable
        public final Annotation[][] getParameterAnnotations() {
            return this.constructor.getParameterAnnotations();
        }

        @Override // java.lang.reflect.GenericDeclaration
        public final TypeVariable<?>[] getTypeParameters() {
            TypeVariable<?>[] declaredByClass = getDeclaringClass().getTypeParameters();
            TypeVariable<?>[] declaredByConstructor = this.constructor.getTypeParameters();
            TypeVariable<?>[] result = new TypeVariable[(declaredByClass.length + declaredByConstructor.length)];
            System.arraycopy(declaredByClass, 0, result, 0, declaredByClass.length);
            System.arraycopy(declaredByConstructor, 0, result, declaredByClass.length, declaredByConstructor.length);
            return result;
        }

        @Override // com.google.common.reflect.Invokable
        public final boolean isOverridable() {
            return false;
        }

        @Override // com.google.common.reflect.Invokable
        public final boolean isVarArgs() {
            return this.constructor.isVarArgs();
        }

        private boolean mayNeedHiddenThis() {
            Class<?> declaringClass = this.constructor.getDeclaringClass();
            if (declaringClass.getEnclosingConstructor() != null) {
                return true;
            }
            Method enclosingMethod = declaringClass.getEnclosingMethod();
            return enclosingMethod != null ? !Modifier.isStatic(enclosingMethod.getModifiers()) : declaringClass.getEnclosingClass() != null && !Modifier.isStatic(declaringClass.getModifiers());
        }
    }
}
