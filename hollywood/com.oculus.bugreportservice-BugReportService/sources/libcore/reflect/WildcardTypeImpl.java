package libcore.reflect;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;

public final class WildcardTypeImpl implements WildcardType {
    private final ListOfTypes extendsBound;
    private final ListOfTypes superBound;

    public WildcardTypeImpl(ListOfTypes listOfTypes, ListOfTypes listOfTypes2) {
        this.extendsBound = listOfTypes;
        this.superBound = listOfTypes2;
    }

    @Override // java.lang.reflect.WildcardType
    public Type[] getLowerBounds() {
        return (Type[]) this.superBound.getResolvedTypes().clone();
    }

    @Override // java.lang.reflect.WildcardType
    public Type[] getUpperBounds() {
        return (Type[]) this.extendsBound.getResolvedTypes().clone();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof WildcardType)) {
            return false;
        }
        WildcardType wildcardType = (WildcardType) obj;
        if (!Arrays.equals(getLowerBounds(), wildcardType.getLowerBounds()) || !Arrays.equals(getUpperBounds(), wildcardType.getUpperBounds())) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return (Arrays.hashCode(getLowerBounds()) * 31) + Arrays.hashCode(getUpperBounds());
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("?");
        if ((this.extendsBound.length() == 1 && this.extendsBound.getResolvedTypes()[0] != Object.class) || this.extendsBound.length() > 1) {
            sb.append(" extends ");
            sb.append(this.extendsBound);
        } else if (this.superBound.length() > 0) {
            sb.append(" super ");
            sb.append(this.superBound);
        }
        return sb.toString();
    }
}
