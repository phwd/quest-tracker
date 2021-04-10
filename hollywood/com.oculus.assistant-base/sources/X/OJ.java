package X;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

public final class OJ {
    public final String A00;
    public final String A01;

    public OJ(JsonPOJOBuilder jsonPOJOBuilder) {
        this.A00 = jsonPOJOBuilder.buildMethodName();
        this.A01 = jsonPOJOBuilder.withPrefix();
    }
}
