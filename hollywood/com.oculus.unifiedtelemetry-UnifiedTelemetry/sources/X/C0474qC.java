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

/* renamed from: X.qC  reason: case insensitive filesystem */
public class C0474qC<T> {
    public final int hashCode;
    public final Class<? super T> rawType;
    public final Type type;

    private List<C0474qC<?>> resolveAll(Type[] typeArr) {
        int length = typeArr.length;
        C0474qC[] qCVarArr = new C0474qC[length];
        for (int i = 0; i < length; i++) {
            qCVarArr[i] = resolve(typeArr[i]);
        }
        return ImmutableList.A09(qCVarArr);
    }

    private void checkPrimitiveType() {
        if (this.rawType.isPrimitive()) {
            throw new IllegalArgumentException(AnonymousClass06.A04("Primitive types are not allowed: ", this.rawType.getName()));
        }
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof C0474qC) || !pt.A07(this.type, ((C0474qC) obj).type)) {
            return false;
        }
        return true;
    }

    public List<C0474qC<?>> getExceptionTypes(Member member) {
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

    public List<C0474qC<?>> getParameterTypes(Member member) {
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

    public C0474qC<?> getSupertype(Class<?> cls) {
        Preconditions.checkArgument(cls.isAssignableFrom(this.rawType), "%s is not a supertype of %s", cls, this.type);
        return resolve(pt.A04(this.type, this.rawType, cls));
    }

    public final C0474qC<eJ<T>> providerType() {
        return new C0474qC<>(new Rb(null, eJ.class, this.type));
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0038 A[LOOP:0: B:0:0x0000->B:15:0x0038, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0041 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.reflect.Type resolveType(java.lang.reflect.Type r8) {
        /*
        // Method dump skipped, instructions count: 227
        */
        throw new UnsupportedOperationException("Method not decompiled: X.C0474qC.resolveType(java.lang.reflect.Type):java.lang.reflect.Type");
    }

    public final String toString() {
        return pt.A02(this.type);
    }

    public static C0474qC<?> fromSuperclassTypeParameter(Class<?> cls) {
        return new C0474qC<>(getSuperclassTypeParameter(cls));
    }

    public static Type getSuperclassTypeParameter(Class<?> cls) {
        Type genericSuperclass = cls.getGenericSuperclass();
        if (!(genericSuperclass instanceof Class)) {
            return pt.A03(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
        }
        throw new RuntimeException("Missing type parameter.");
    }

    public C0474qC<?> getFieldType(Field field) {
        Preconditions.checkArgument(field.getDeclaringClass().isAssignableFrom(this.rawType), "%s is not defined by a supertype of %s", field, this.type);
        return resolve(field.getGenericType());
    }

    public C0474qC<?> getReturnType(Method method) {
        Preconditions.checkArgument(method.getDeclaringClass().isAssignableFrom(this.rawType), "%s is not defined by a supertype of %s", method, this.type);
        return resolve(method.getGenericReturnType());
    }

    public C0474qC<?> resolve(Type type2) {
        return new C0474qC<>(resolveType(type2));
    }

    public final Class<? super T> getRawType() {
        return this.rawType;
    }

    public final Type getType() {
        return this.type;
    }

    public final int hashCode() {
        return this.hashCode;
    }

    public C0474qC() {
        Type superclassTypeParameter = getSuperclassTypeParameter(getClass());
        this.type = superclassTypeParameter;
        this.rawType = (Class<? super T>) pt.A01(superclassTypeParameter);
        this.hashCode = this.type.hashCode();
        checkPrimitiveType();
    }

    public C0474qC(Type type2) {
        if (type2 != null) {
            Type A03 = pt.A03(type2);
            this.type = A03;
            this.rawType = (Class<? super T>) pt.A01(A03);
            this.hashCode = this.type.hashCode();
            checkPrimitiveType();
            return;
        }
        throw new NullPointerException("type is null");
    }

    public static <T> C0474qC<T> get(Class<T> cls) {
        return new C0474qC<>(cls);
    }

    public static C0474qC<?> get(Type type2) {
        return new C0474qC<>(type2);
    }
}
