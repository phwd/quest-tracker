package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.Serializable;

/* renamed from: X.0lQ  reason: invalid class name and case insensitive filesystem */
public final class C05530lQ implements Serializable {
    public static final long serialVersionUID = 1;
    public final JsonDeserializer<Object> deserializer;
    public final AbstractC04750jT<?> generator;
    public final AnonymousClass0HD idProperty;
    public final AbstractC04000gb idType;
    public final String propertyName;

    public C05530lQ(AbstractC04000gb r1, String str, AbstractC04750jT<?> r3, JsonDeserializer<?> jsonDeserializer, AnonymousClass0HD r5) {
        this.idType = r1;
        this.propertyName = str;
        this.generator = r3;
        this.deserializer = jsonDeserializer;
        this.idProperty = r5;
    }
}
