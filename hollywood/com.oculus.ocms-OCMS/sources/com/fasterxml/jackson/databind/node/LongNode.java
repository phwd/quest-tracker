package com.fasterxml.jackson.databind.node;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.io.NumberOutput;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.oculus.library.database.contract.LibraryDBContract;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;

public final class LongNode extends NumericNode {
    final long _value;

    @Override // com.fasterxml.jackson.databind.node.NumericNode, com.fasterxml.jackson.databind.JsonNode
    public boolean canConvertToLong() {
        return true;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public boolean isIntegralNumber() {
        return true;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public boolean isLong() {
        return true;
    }

    public LongNode(long j) {
        this._value = j;
    }

    public static LongNode valueOf(long j) {
        return new LongNode(j);
    }

    @Override // com.fasterxml.jackson.core.TreeNode, com.fasterxml.jackson.databind.node.BaseJsonNode, com.fasterxml.jackson.databind.node.ValueNode
    public JsonToken asToken() {
        return JsonToken.VALUE_NUMBER_INT;
    }

    @Override // com.fasterxml.jackson.databind.node.NumericNode, com.fasterxml.jackson.core.TreeNode, com.fasterxml.jackson.databind.node.BaseJsonNode
    public JsonParser.NumberType numberType() {
        return JsonParser.NumberType.LONG;
    }

    @Override // com.fasterxml.jackson.databind.node.NumericNode, com.fasterxml.jackson.databind.JsonNode
    public boolean canConvertToInt() {
        long j = this._value;
        return j >= LibraryDBContract.VERSION_NOT_INSTALLED && j <= 2147483647L;
    }

    @Override // com.fasterxml.jackson.databind.node.NumericNode, com.fasterxml.jackson.databind.JsonNode
    public Number numberValue() {
        return Long.valueOf(this._value);
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public short shortValue() {
        return (short) ((int) this._value);
    }

    @Override // com.fasterxml.jackson.databind.node.NumericNode, com.fasterxml.jackson.databind.JsonNode
    public int intValue() {
        return (int) this._value;
    }

    @Override // com.fasterxml.jackson.databind.node.NumericNode, com.fasterxml.jackson.databind.JsonNode
    public long longValue() {
        return this._value;
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public float floatValue() {
        return (float) this._value;
    }

    @Override // com.fasterxml.jackson.databind.node.NumericNode, com.fasterxml.jackson.databind.JsonNode
    public double doubleValue() {
        return (double) this._value;
    }

    @Override // com.fasterxml.jackson.databind.node.NumericNode, com.fasterxml.jackson.databind.JsonNode
    public BigDecimal decimalValue() {
        return BigDecimal.valueOf(this._value);
    }

    @Override // com.fasterxml.jackson.databind.node.NumericNode, com.fasterxml.jackson.databind.JsonNode
    public BigInteger bigIntegerValue() {
        return BigInteger.valueOf(this._value);
    }

    @Override // com.fasterxml.jackson.databind.node.NumericNode, com.fasterxml.jackson.databind.JsonNode
    public String asText() {
        return NumberOutput.toString(this._value);
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public boolean asBoolean(boolean z) {
        return this._value != 0;
    }

    @Override // com.fasterxml.jackson.databind.node.BaseJsonNode, com.fasterxml.jackson.databind.JsonSerializable
    public final void serialize(JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        jsonGenerator.writeNumber(this._value);
    }

    @Override // com.fasterxml.jackson.databind.JsonNode
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj != null && obj.getClass() == getClass() && ((LongNode) obj)._value == this._value) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = this._value;
        return ((int) j) ^ ((int) (j >> 32));
    }
}
