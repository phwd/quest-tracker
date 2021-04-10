package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.Serializable;

/* renamed from: X.0pu  reason: invalid class name and case insensitive filesystem */
public final class C04390pu implements Serializable {
    public static final long serialVersionUID = 1;
    public final JsonDeserializer<Object> deserializer;
    public final AbstractC03600nz<?> generator;
    public final AbstractC01170Rz idProperty;
    public final AbstractC02190iF idType;
    public final String propertyName;

    public C04390pu(AbstractC02190iF r1, String str, AbstractC03600nz<?> r3, JsonDeserializer<?> jsonDeserializer, AbstractC01170Rz r5) {
        this.idType = r1;
        this.propertyName = str;
        this.generator = r3;
        this.deserializer = jsonDeserializer;
        this.idProperty = r5;
    }
}
