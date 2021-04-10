package java.util;

import java.io.Serializable;

public class EventObject implements Serializable {
    private static final long serialVersionUID = 5516075349620653480L;
    protected transient Object source;

    public EventObject(Object obj) {
        if (obj != null) {
            this.source = obj;
            return;
        }
        throw new IllegalArgumentException("null source");
    }
}
