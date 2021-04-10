package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.Serializable;

/* renamed from: X.Vm  reason: case insensitive filesystem */
public final class C0204Vm implements Serializable {
    public static final long serialVersionUID = 1;
    public final JsonDeserializer<Object> deserializer;
    public final pp<?> generator;
    public final AbstractC0073Cr idProperty;
    public final AbstractC0224Wl idType;
    public final String propertyName;

    public C0204Vm(AbstractC0224Wl wl, String str, pp<?> ppVar, JsonDeserializer<?> jsonDeserializer, AbstractC0073Cr cr) {
        this.idType = wl;
        this.propertyName = str;
        this.generator = ppVar;
        this.deserializer = jsonDeserializer;
        this.idProperty = cr;
    }
}
