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

/* renamed from: X.14R  reason: invalid class name */
public class AnonymousClass14R<T> {
    public final int hashCode;
    public final Class<? super T> rawType;
    public final Type type;

    private List<AnonymousClass14R<?>> resolveAll(Type[] typeArr) {
        int length = typeArr.length;
        AnonymousClass14R[] r2 = new AnonymousClass14R[length];
        for (int i = 0; i < length; i++) {
            r2[i] = resolve(typeArr[i]);
        }
        return ImmutableList.A0C(r2);
    }

    private void checkPrimitiveType() {
        if (this.rawType.isPrimitive()) {
            throw new IllegalArgumentException(AnonymousClass006.A07("Primitive types are not allowed: ", this.rawType.getName()));
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof AnonymousClass14R) || !AnonymousClass14V.A07(this.type, ((AnonymousClass14R) obj).type)) {
            return false;
        }
        return true;
    }

    public List<AnonymousClass14R<?>> getExceptionTypes(Member member) {
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

    public List<AnonymousClass14R<?>> getParameterTypes(Member member) {
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

    public AnonymousClass14R<?> getSupertype(Class<?> cls) {
        Preconditions.checkArgument(cls.isAssignableFrom(this.rawType), "%s is not a supertype of %s", cls, this.type);
        return resolve(AnonymousClass14V.A04(this.type, this.rawType, cls));
    }

    public final AnonymousClass14R<Provider<T>> providerType() {
        return new AnonymousClass14R<>(new AnonymousClass0cv(null, Provider.class, this.type));
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038 A[LOOP:0: B:0:0x0000->B:15:0x0038, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0041 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.reflect.Type resolveType(java.lang.reflect.Type r8) {
        /*
        // Method dump skipped, instructions count: 227
        */
        throw new UnsupportedOperationException("Method not decompiled: X.AnonymousClass14R.resolveType(java.lang.reflect.Type):java.lang.reflect.Type");
    }

    public final String toString() {
        return AnonymousClass14V.A02(this.type);
    }

    public static AnonymousClass14R<?> fromSuperclassTypeParameter(Class<?> cls) {
        return new AnonymousClass14R<>(getSuperclassTypeParameter(cls));
    }

    public static Type getSuperclassTypeParameter(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (!(genericSuperclass instanceof Class)) {
            return AnonymousClass14V.A03(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
        }
        throw new RuntimeException("Missing type parameter.");
    }

    public AnonymousClass14R<?> getFieldType(Field field) {
        Preconditions.checkArgument(field.getDeclaringClass().isAssignableFrom(this.rawType), "%s is not defined by a supertype of %s", field, this.type);
        return resolve(field.getGenericType());
    }

    public final Class<? super T> getRawType() {
        return this.rawType;
    }

    public AnonymousClass14R<?> getReturnType(Method method) {
        Preconditions.checkArgument(method.getDeclaringClass().isAssignableFrom(this.rawType), "%s is not defined by a supertype of %s", method, this.type);
        return resolve(method.getGenericReturnType());
    }

    public final Type getType() {
        return this.type;
    }

    public final int hashCode() {
        return this.hashCode;
    }

    public AnonymousClass14R<?> resolve(Type type2) {
        return new AnonymousClass14R<>(resolveType(type2));
    }

    public AnonymousClass14R() {
        Type superclassTypeParameter = getSuperclassTypeParameter(getClass());
        this.type = superclassTypeParameter;
        this.rawType = (Class<? super T>) AnonymousClass14V.A01(superclassTypeParameter);
        this.hashCode = this.type.hashCode();
        checkPrimitiveType();
    }

    public AnonymousClass14R(Type type2) {
        if (type2 != null) {
            Type A03 = AnonymousClass14V.A03(type2);
            this.type = A03;
            this.rawType = (Class<? super T>) AnonymousClass14V.A01(A03);
            this.hashCode = this.type.hashCode();
            checkPrimitiveType();
            return;
        }
        throw new NullPointerException("type is null");
    }

    public static <T> AnonymousClass14R<T> get(Class<T> cls) {
        return new AnonymousClass14R<>(cls);
    }

    public static AnonymousClass14R<?> get(Type type2) {
        return new AnonymousClass14R<>(type2);
    }
}
