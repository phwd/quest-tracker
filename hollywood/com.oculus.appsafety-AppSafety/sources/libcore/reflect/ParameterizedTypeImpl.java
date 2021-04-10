package libcore.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Objects;

public final class ParameterizedTypeImpl implements ParameterizedType {
    private final ListOfTypes args;
    private final ClassLoader loader;
    private final ParameterizedTypeImpl ownerType0;
    private Type ownerTypeRes;
    private Class rawType;
    private final String rawTypeName;

    public ParameterizedTypeImpl(ParameterizedTypeImpl ownerType, String rawTypeName2, ListOfTypes args2, ClassLoader loader2) {
        if (args2 != null) {
            this.ownerType0 = ownerType;
            this.rawTypeName = rawTypeName2;
            this.args = args2;
            this.loader = loader2;
            return;
        }
        throw new NullPointerException();
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type[] getActualTypeArguments() {
        return (Type[]) this.args.getResolvedTypes().clone();
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getOwnerType() {
        if (this.ownerTypeRes == null) {
            ParameterizedTypeImpl parameterizedTypeImpl = this.ownerType0;
            if (parameterizedTypeImpl != null) {
                this.ownerTypeRes = parameterizedTypeImpl.getResolvedType();
            } else {
                this.ownerTypeRes = getRawType().getDeclaringClass();
            }
        }
        return this.ownerTypeRes;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Class getRawType() {
        if (this.rawType == null) {
            try {
                this.rawType = Class.forName(this.rawTypeName, false, this.loader);
            } catch (ClassNotFoundException e) {
                throw new TypeNotPresentException(this.rawTypeName, e);
            }
        }
        return this.rawType;
    }

    /* access modifiers changed from: package-private */
    public Type getResolvedType() {
        if (this.args.getResolvedTypes().length == 0) {
            return getRawType();
        }
        return this;
    }

    public boolean equals(Object o) {
        if (!(o instanceof ParameterizedType)) {
            return false;
        }
        ParameterizedType that = (ParameterizedType) o;
        if (!Objects.equals(getRawType(), that.getRawType()) || !Objects.equals(getOwnerType(), that.getOwnerType()) || !Arrays.equals(this.args.getResolvedTypes(), that.getActualTypeArguments())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (((Objects.hashCode(getRawType()) * 31) + Objects.hashCode(getOwnerType())) * 31) + Arrays.hashCode(this.args.getResolvedTypes());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.rawTypeName);
        if (this.args.length() > 0) {
            sb.append("<");
            sb.append((Object) this.args);
            sb.append(">");
        }
        return sb.toString();
    }
}
