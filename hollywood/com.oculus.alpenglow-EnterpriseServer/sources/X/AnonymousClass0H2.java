package X;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/* renamed from: X.0H2  reason: invalid class name */
public class AnonymousClass0H2<T> {
    public final int hashCode;
    public final Class<? super T> rawType;
    public final Type type;

    private List<AnonymousClass0H2<?>> resolveAll(Type[] typeArr) {
        int length = typeArr.length;
        AnonymousClass0H2[] r2 = new AnonymousClass0H2[length];
        for (int i = 0; i < length; i++) {
            r2[i] = resolve(typeArr[i]);
        }
        return ImmutableList.copyOf(r2);
    }

    private void checkPrimitiveType() {
        if (this.rawType.isPrimitive()) {
            throw new IllegalArgumentException(AnonymousClass006.A05("Primitive types are not allowed: ", this.rawType.getName()));
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof AnonymousClass0H2) || !AnonymousClass0Hy.A07(this.type, ((AnonymousClass0H2) obj).type)) {
            return false;
        }
        return true;
    }

    public List<AnonymousClass0H2<?>> getExceptionTypes(Member member) {
        Type[] genericExceptionTypes;
        if (member instanceof Method) {
            Method method = (Method) member;
            Preconditions.checkArgument(method.getDeclaringClass().isAssignableFrom(this.rawType), "%s is not defined by a supertype of %s", method, this.type);
            genericExceptionTypes = method.getGenericExceptionTypes();
        } else if (member instanceof Constructor) {
            Constructor constructor = (Constructor) member;
            Preconditions.checkArgument(constructor.getDeclaringClass().isAssignableFrom(this.rawType), "%s does not construct a supertype of %s", constructor, this.type);
            genericExceptionTypes = constructor.getGenericExceptionTypes();
        } else {
            throw new IllegalArgumentException("Not a method or a constructor: " + member);
        }
        return resolveAll(genericExceptionTypes);
    }

    public List<AnonymousClass0H2<?>> getParameterTypes(Member member) {
        Type[] genericParameterTypes;
        if (member instanceof Method) {
            Method method = (Method) member;
            Preconditions.checkArgument(method.getDeclaringClass().isAssignableFrom(this.rawType), "%s is not defined by a supertype of %s", method, this.type);
            genericParameterTypes = method.getGenericParameterTypes();
        } else if (member instanceof Constructor) {
            Constructor constructor = (Constructor) member;
            Preconditions.checkArgument(constructor.getDeclaringClass().isAssignableFrom(this.rawType), "%s does not construct a supertype of %s", constructor, this.type);
            genericParameterTypes = constructor.getGenericParameterTypes();
        } else {
            throw new IllegalArgumentException("Not a method or a constructor: " + member);
        }
        return resolveAll(genericParameterTypes);
    }

    public final Class<? super T> getRawType() {
        return this.rawType;
    }

    public AnonymousClass0H2<?> getSupertype(Class<?> cls) {
        Preconditions.checkArgument(cls.isAssignableFrom(this.rawType), "%s is not a supertype of %s", cls, this.type);
        return resolve(AnonymousClass0Hy.A04(this.type, this.rawType, cls));
    }

    public final Type getType() {
        return this.type;
    }

    public final int hashCode() {
        return this.hashCode;
    }

    public final AnonymousClass0H2<AbstractC07240oz<T>> providerType() {
        return new AnonymousClass0H2<>(new AnonymousClass0Vu(null, AbstractC07240oz.class, this.type));
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038 A[LOOP:0: B:0:0x0000->B:15:0x0038, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0041 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.reflect.Type resolveType(java.lang.reflect.Type r8) {
        /*
        // Method dump skipped, instructions count: 227
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass0H2.resolveType(java.lang.reflect.Type):java.lang.reflect.Type");
    }

    public final String toString() {
        return AnonymousClass0Hy.A02(this.type);
    }

    public static AnonymousClass0H2<?> fromSuperclassTypeParameter(Class<?> cls) {
        return new AnonymousClass0H2<>(getSuperclassTypeParameter(cls));
    }

    public static Type getSuperclassTypeParameter(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (!(genericSuperclass instanceof Class)) {
            return AnonymousClass0Hy.A03(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
        }
        throw new RuntimeException("Missing type parameter.");
    }

    public AnonymousClass0H2<?> getFieldType(Field field) {
        Preconditions.checkArgument(field.getDeclaringClass().isAssignableFrom(this.rawType), "%s is not defined by a supertype of %s", field, this.type);
        return resolve(field.getGenericType());
    }

    public AnonymousClass0H2<?> getReturnType(Method method) {
        Preconditions.checkArgument(method.getDeclaringClass().isAssignableFrom(this.rawType), "%s is not defined by a supertype of %s", method, this.type);
        return resolve(method.getGenericReturnType());
    }

    public AnonymousClass0H2<?> resolve(Type type2) {
        return new AnonymousClass0H2<>(resolveType(type2));
    }

    public AnonymousClass0H2() {
        Type superclassTypeParameter = getSuperclassTypeParameter(getClass());
        this.type = superclassTypeParameter;
        this.rawType = (Class<? super T>) AnonymousClass0Hy.A01(superclassTypeParameter);
        this.hashCode = this.type.hashCode();
        checkPrimitiveType();
    }

    public AnonymousClass0H2(Type type2) {
        if (type2 != null) {
            Type A03 = AnonymousClass0Hy.A03(type2);
            this.type = A03;
            this.rawType = (Class<? super T>) AnonymousClass0Hy.A01(A03);
            this.hashCode = this.type.hashCode();
            checkPrimitiveType();
            return;
        }
        throw new NullPointerException("type is null");
    }

    public static <T> AnonymousClass0H2<T> get(Class<T> cls) {
        return new AnonymousClass0H2<>(cls);
    }

    public static AnonymousClass0H2<?> get(Type type2) {
        return new AnonymousClass0H2<>(type2);
    }
}
