package libcore.reflect;

import java.lang.reflect.TypeVariable;
import java.util.ArrayList;

/* access modifiers changed from: package-private */
public final class ListOfVariables {
    final ArrayList array = new ArrayList();

    ListOfVariables() {
    }

    /* access modifiers changed from: package-private */
    public void add(TypeVariable typeVariable) {
        this.array.add(typeVariable);
    }

    /* access modifiers changed from: package-private */
    public TypeVariable[] getArray() {
        return (TypeVariable[]) this.array.toArray(new TypeVariable[this.array.size()]);
    }
}
