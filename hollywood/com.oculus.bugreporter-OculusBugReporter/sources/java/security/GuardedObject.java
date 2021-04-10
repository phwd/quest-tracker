package java.security;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class GuardedObject implements Serializable {
    private static final long serialVersionUID = -5240450096227834308L;
    private Guard guard;
    private Object object;

    public GuardedObject(Object object2, Guard guard2) {
        this.guard = guard2;
        this.object = object2;
    }

    public Object getObject() throws SecurityException {
        Guard guard2 = this.guard;
        if (guard2 != null) {
            guard2.checkGuard(this.object);
        }
        return this.object;
    }

    private void writeObject(ObjectOutputStream oos) throws IOException {
        Guard guard2 = this.guard;
        if (guard2 != null) {
            guard2.checkGuard(this.object);
        }
        oos.defaultWriteObject();
    }
}
