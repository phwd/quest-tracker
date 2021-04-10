package libcore.reflect;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import libcore.util.EmptyArray;

public final class ListOfTypes {
    public static final ListOfTypes EMPTY = new ListOfTypes(0);
    private Type[] resolvedTypes;
    private final ArrayList types;

    ListOfTypes(int i) {
        this.types = new ArrayList(i);
    }

    ListOfTypes(Type[] typeArr) {
        this.types = new ArrayList(typeArr.length);
        for (Type type : typeArr) {
            this.types.add(type);
        }
    }

    /* access modifiers changed from: package-private */
    public void add(Type type) {
        if (type != null) {
            this.types.add(type);
            return;
        }
        throw new NullPointerException("type == null");
    }

    /* access modifiers changed from: package-private */
    public int length() {
        return this.types.size();
    }

    public Type[] getResolvedTypes() {
        Type[] typeArr = this.resolvedTypes;
        if (typeArr != null) {
            return typeArr;
        }
        Type[] resolveTypes = resolveTypes(this.types);
        this.resolvedTypes = resolveTypes;
        return resolveTypes;
    }

    private Type[] resolveTypes(List list) {
        int size = list.size();
        if (size == 0) {
            return EmptyArray.TYPE;
        }
        Type[] typeArr = new Type[size];
        for (int i = 0; i < size; i++) {
            Type type = (Type) list.get(i);
            if (type instanceof ParameterizedTypeImpl) {
                typeArr[i] = ((ParameterizedTypeImpl) type).getResolvedType();
            } else {
                typeArr[i] = type;
            }
        }
        return typeArr;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.types.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(this.types.get(i));
        }
        return sb.toString();
    }
}
