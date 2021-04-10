package libcore.reflect;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;

public final class WildcardTypeImpl implements WildcardType {
    private final ListOfTypes extendsBound;
    private final ListOfTypes superBound;

    public WildcardTypeImpl(ListOfTypes extendsBound2, ListOfTypes superBound2) {
        this.extendsBound = extendsBound2;
        this.superBound = superBound2;
    }

    @Override // java.lang.reflect.WildcardType
    public Type[] getLowerBounds() throws TypeNotPresentException, MalformedParameterizedTypeException {
        return (Type[]) this.superBound.getResolvedTypes().clone();
    }

    @Override // java.lang.reflect.WildcardType
    public Type[] getUpperBounds() throws TypeNotPresentException, MalformedParameterizedTypeException {
        return (Type[]) this.extendsBound.getResolvedTypes().clone();
    }

    public boolean equals(Object o) {
        if (!(o instanceof WildcardType)) {
            return false;
        }
        WildcardType that = (WildcardType) o;
        if (!Arrays.equals(getLowerBounds(), that.getLowerBounds()) || !Arrays.equals(getUpperBounds(), that.getUpperBounds())) {
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
            sb.append((Object) this.extendsBound);
        } else if (this.superBound.length() > 0) {
            sb.append(" super ");
            sb.append((Object) this.superBound);
        }
        return sb.toString();
    }
}
