package X;

import java.lang.reflect.Type;

public final class Ss extends AbstractC1044rJ {
    public static final long serialVersionUID = 1;
    public final int _index;
    public final SV _owner;
    public final Type _type;

    public final String toString() {
        StringBuilder sb = new StringBuilder("[parameter #");
        sb.append(this._index);
        sb.append(", annotations: ");
        sb.append(this.A00);
        sb.append("]");
        return sb.toString();
    }

    public Ss(SV sv, Type type, C1045rK rKVar, int i) {
        super(rKVar);
        this._owner = sv;
        this._type = type;
        this._index = i;
    }
}
