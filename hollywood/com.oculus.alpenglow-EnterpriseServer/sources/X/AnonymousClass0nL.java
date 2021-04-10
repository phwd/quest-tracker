package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.Serializable;

/* renamed from: X.0nL  reason: invalid class name */
public final class AnonymousClass0nL implements Serializable {
    public static final long serialVersionUID = 1;
    public final JsonDeserializer<Object> deserializer;
    public final AnonymousClass0lR<?> generator;
    public final AbstractC01680Ku idProperty;
    public final AnonymousClass0aI idType;
    public final String propertyName;

    public AnonymousClass0nL(AnonymousClass0aI r1, String str, AnonymousClass0lR<?> r3, JsonDeserializer<?> jsonDeserializer, AbstractC01680Ku r5) {
        this.idType = r1;
        this.propertyName = str;
        this.generator = r3;
        this.deserializer = jsonDeserializer;
        this.idProperty = r5;
    }
}
