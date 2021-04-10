package X;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/* renamed from: X.hE  reason: case insensitive filesystem */
public class C0410hE {
    public final String A00;
    public final String A01;

    public C0410hE(JsonPOJOBuilder jsonPOJOBuilder) {
        this.A00 = jsonPOJOBuilder.buildMethodName();
        this.A01 = jsonPOJOBuilder.withPrefix();
    }
}
