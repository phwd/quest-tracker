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
import javax.inject.Provider;

/* renamed from: X.0za  reason: invalid class name and case insensitive filesystem */
public class C09170za<T> {
    public final int hashCode;
    public final Class<? super T> rawType;
    public final Type type;

    private List<C09170za<?>> resolveAll(Type[] typeArr) {
        int length = typeArr.length;
        C09170za[] r2 = new C09170za[length];
        for (int i = 0; i < length; i++) {
            r2[i] = resolve(typeArr[i]);
        }
        return ImmutableList.A0E(r2);
    }

    private void checkPrimitiveType() {
        if (this.rawType.isPrimitive()) {
            throw new IllegalArgumentException(AnonymousClass006.A05("Primitive types are not allowed: ", this.rawType.getName()));
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C09170za) || !C09190ze.A07(this.type, ((C09170za) obj).type)) {
            return false;
        }
        return true;
    }

    public List<C09170za<?>> getExceptionTypes(Member member) {
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
            StringBuilder sb = new StringBuilder("Not a method or a constructor: ");
            sb.append(member);
            throw new IllegalArgumentException(sb.toString());
        }
        return resolveAll(genericExceptionTypes);
    }

    public List<C09170za<?>> getParameterTypes(Member member) {
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
            StringBuilder sb = new StringBuilder("Not a method or a constructor: ");
            sb.append(member);
            throw new IllegalArgumentException(sb.toString());
        }
        return resolveAll(genericParameterTypes);
    }

    public C09170za<?> getSupertype(Class<?> cls) {
        Preconditions.checkArgument(cls.isAssignableFrom(this.rawType), "%s is not a supertype of %s", cls, this.type);
        return resolve(C09190ze.A04(this.type, this.rawType, cls));
    }

    public final C09170za<Provider<T>> providerType() {
        return new C09170za<>(new AnonymousClass0UP(null, Provider.class, this.type));
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038 A[LOOP:0: B:0:0x0000->B:15:0x0038, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0041 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.reflect.Type resolveType(java.lang.reflect.Type r8) {
        /*
        // Method dump skipped, instructions count: 228
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C09170za.resolveType(java.lang.reflect.Type):java.lang.reflect.Type");
    }

    public final String toString() {
        return C09190ze.A02(this.type);
    }

    public static C09170za<?> fromSuperclassTypeParameter(Class<?> cls) {
        return new C09170za<>(getSuperclassTypeParameter(cls));
    }

    public static Type getSuperclassTypeParameter(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (!(genericSuperclass instanceof Class)) {
            return C09190ze.A03(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
        }
        throw new RuntimeException("Missing type parameter.");
    }

    public C09170za<?> getFieldType(Field field) {
        Preconditions.checkArgument(field.getDeclaringClass().isAssignableFrom(this.rawType), "%s is not defined by a supertype of %s", field, this.type);
        return resolve(field.getGenericType());
    }

    public final Class<? super T> getRawType() {
        return this.rawType;
    }

    public C09170za<?> getReturnType(Method method) {
        Preconditions.checkArgument(method.getDeclaringClass().isAssignableFrom(this.rawType), "%s is not defined by a supertype of %s", method, this.type);
        return resolve(method.getGenericReturnType());
    }

    public final Type getType() {
        return this.type;
    }

    public final int hashCode() {
        return this.hashCode;
    }

    public C09170za<?> resolve(Type type2) {
        return new C09170za<>(resolveType(type2));
    }

    public C09170za() {
        Type superclassTypeParameter = getSuperclassTypeParameter(getClass());
        this.type = superclassTypeParameter;
        this.rawType = (Class<? super T>) C09190ze.A01(superclassTypeParameter);
        this.hashCode = this.type.hashCode();
        checkPrimitiveType();
    }

    public C09170za(Type type2) {
        if (type2 != null) {
            Type A03 = C09190ze.A03(type2);
            this.type = A03;
            this.rawType = (Class<? super T>) C09190ze.A01(A03);
            this.hashCode = this.type.hashCode();
            checkPrimitiveType();
            return;
        }
        throw new NullPointerException("type is null");
    }

    public static <T> C09170za<T> get(Class<T> cls) {
        return new C09170za<>(cls);
    }

    public static C09170za<?> get(Type type2) {
        return new C09170za<>(type2);
    }
}
