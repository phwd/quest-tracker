package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.lang.reflect.Constructor;

public final class UP extends AbstractC1034r7 {
    public static final long serialVersionUID = 1;
    public final Constructor _creator;
    public final AbstractC1034r7 _delegate;

    public UP(AbstractC1034r7 r7Var, Constructor constructor) {
        super(r7Var);
        this._delegate = r7Var;
        this._creator = constructor;
    }

    public UP(UP up, JsonDeserializer jsonDeserializer) {
        super(up, jsonDeserializer);
        this._delegate = up._delegate.A02(jsonDeserializer);
        this._creator = up._creator;
    }

    public UP(UP up, String str) {
        super(up, str);
        this._delegate = up._delegate.A03(str);
        this._creator = up._creator;
    }
}
