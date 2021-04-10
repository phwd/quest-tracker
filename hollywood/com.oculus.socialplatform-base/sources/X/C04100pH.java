package X;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/* renamed from: X.0pH  reason: invalid class name and case insensitive filesystem */
public class C04100pH {
    public final String A00;
    public final String A01;

    public C04100pH(JsonPOJOBuilder jsonPOJOBuilder) {
        this.A00 = jsonPOJOBuilder.buildMethodName();
        this.A01 = jsonPOJOBuilder.withPrefix();
    }
}
