package libcore.reflect;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import libcore.util.EmptyArray;

public final class Types {
    private static final Map PRIMITIVE_TO_SIGNATURE = new HashMap(9);

    static {
        PRIMITIVE_TO_SIGNATURE.put(Byte.TYPE, "B");
        PRIMITIVE_TO_SIGNATURE.put(Character.TYPE, "C");
        PRIMITIVE_TO_SIGNATURE.put(Short.TYPE, "S");
        PRIMITIVE_TO_SIGNATURE.put(Integer.TYPE, "I");
        PRIMITIVE_TO_SIGNATURE.put(Long.TYPE, "J");
        PRIMITIVE_TO_SIGNATURE.put(Float.TYPE, "F");
        PRIMITIVE_TO_SIGNATURE.put(Double.TYPE, "D");
        PRIMITIVE_TO_SIGNATURE.put(Void.TYPE, "V");
        PRIMITIVE_TO_SIGNATURE.put(Boolean.TYPE, "Z");
    }

    public static Type[] getTypeArray(ListOfTypes listOfTypes, boolean z) {
        if (listOfTypes.length() == 0) {
            return EmptyArray.TYPE;
        }
        Type[] resolvedTypes = listOfTypes.getResolvedTypes();
        return z ? (Type[]) resolvedTypes.clone() : resolvedTypes;
    }
}
