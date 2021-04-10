package X;

import com.fasterxml.jackson.databind.JsonDeserializer;
import java.io.Serializable;

/* renamed from: X.Ou  reason: case insensitive filesystem */
public final class C0273Ou implements Serializable {
    public static final long serialVersionUID = 1;
    public final JsonDeserializer deserializer;
    public final NN generator;
    public final AbstractC1034r7 idProperty;
    public final AbstractC1024qt idType;
    public final String propertyName;

    public C0273Ou(AbstractC1024qt qtVar, String str, NN nn, JsonDeserializer jsonDeserializer, AbstractC1034r7 r7Var) {
        this.idType = qtVar;
        this.propertyName = str;
        this.generator = nn;
        this.deserializer = jsonDeserializer;
        this.idProperty = r7Var;
    }
}
