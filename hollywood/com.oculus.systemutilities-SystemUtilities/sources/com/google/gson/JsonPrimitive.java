package com.google.gson;

import com.google.gson.internal.C$Gson$Preconditions;
import com.google.gson.internal.LazilyParsedNumber;
import java.math.BigInteger;

public final class JsonPrimitive extends JsonElement {
    private static final Class<?>[] PRIMITIVE_TYPES = {Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    private Object value;

    public JsonPrimitive(Boolean bool) {
        setValue(bool);
    }

    public JsonPrimitive(Number number) {
        setValue(number);
    }

    public JsonPrimitive(String string) {
        setValue(string);
    }

    /* access modifiers changed from: package-private */
    public void setValue(Object primitive) {
        if (primitive instanceof Character) {
            this.value = String.valueOf(((Character) primitive).charValue());
            return;
        }
        C$Gson$Preconditions.checkArgument((primitive instanceof Number) || isPrimitiveOrString(primitive));
        this.value = primitive;
    }

    public boolean isBoolean() {
        return this.value instanceof Boolean;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.gson.JsonElement
    public Boolean getAsBooleanWrapper() {
        return (Boolean) this.value;
    }

    @Override // com.google.gson.JsonElement
    public boolean getAsBoolean() {
        if (isBoolean()) {
            return getAsBooleanWrapper().booleanValue();
        }
        return Boolean.parseBoolean(getAsString());
    }

    public boolean isNumber() {
        return this.value instanceof Number;
    }

    @Override // com.google.gson.JsonElement
    public Number getAsNumber() {
        return this.value instanceof String ? new LazilyParsedNumber((String) this.value) : (Number) this.value;
    }

    public boolean isString() {
        return this.value instanceof String;
    }

    @Override // com.google.gson.JsonElement
    public String getAsString() {
        if (isNumber()) {
            return getAsNumber().toString();
        }
        if (isBoolean()) {
            return getAsBooleanWrapper().toString();
        }
        return (String) this.value;
    }

    @Override // com.google.gson.JsonElement
    public double getAsDouble() {
        return isNumber() ? getAsNumber().doubleValue() : Double.parseDouble(getAsString());
    }

    @Override // com.google.gson.JsonElement
    public long getAsLong() {
        return isNumber() ? getAsNumber().longValue() : Long.parseLong(getAsString());
    }

    @Override // com.google.gson.JsonElement
    public int getAsInt() {
        return isNumber() ? getAsNumber().intValue() : Integer.parseInt(getAsString());
    }

    private static boolean isPrimitiveOrString(Object target) {
        if (target instanceof String) {
            return true;
        }
        Class<?> classOfPrimitive = target.getClass();
        for (Class<?> standardPrimitive : PRIMITIVE_TYPES) {
            if (standardPrimitive.isAssignableFrom(classOfPrimitive)) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        if (this.value == null) {
            return 31;
        }
        if (isIntegral(this)) {
            long value2 = getAsNumber().longValue();
            return (int) ((value2 >>> 32) ^ value2);
        } else if (!(this.value instanceof Number)) {
            return this.value.hashCode();
        } else {
            long value3 = Double.doubleToLongBits(getAsNumber().doubleValue());
            return (int) ((value3 >>> 32) ^ value3);
        }
    }

    public boolean equals(Object obj) {
        boolean z = false;
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        JsonPrimitive other = (JsonPrimitive) obj;
        if (this.value == null) {
            return other.value == null;
        }
        if (isIntegral(this) && isIntegral(other)) {
            return getAsNumber().longValue() == other.getAsNumber().longValue();
        }
        if (!(this.value instanceof Number) || !(other.value instanceof Number)) {
            return this.value.equals(other.value);
        }
        double a = getAsNumber().doubleValue();
        double b = other.getAsNumber().doubleValue();
        if (a == b || (Double.isNaN(a) && Double.isNaN(b))) {
            z = true;
        }
        return z;
    }

    private static boolean isIntegral(JsonPrimitive primitive) {
        if (!(primitive.value instanceof Number)) {
            return false;
        }
        Number number = (Number) primitive.value;
        if ((number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte)) {
            return true;
        }
        return false;
    }
}
