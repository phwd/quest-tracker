package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;

/* access modifiers changed from: package-private */
/* compiled from: JsonNodeDeserializer */
public abstract class BaseNodeDeserializer extends StdDeserializer<JsonNode> {
    /* access modifiers changed from: protected */
    public void _handleDuplicateField(String str, ObjectNode objectNode, JsonNode jsonNode, JsonNode jsonNode2) throws JsonProcessingException {
    }

    public BaseNodeDeserializer() {
        super(JsonNode.class);
    }

    @Override // com.fasterxml.jackson.databind.deser.std.StdDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserializeWithType(JsonParser jsonParser, DeserializationContext deserializationContext, TypeDeserializer typeDeserializer) throws IOException, JsonProcessingException {
        return typeDeserializer.deserializeTypedFromAny(jsonParser, deserializationContext);
    }

    @Override // com.fasterxml.jackson.databind.JsonDeserializer
    public JsonNode getNullValue() {
        return NullNode.getInstance();
    }

    /* access modifiers changed from: protected */
    public void _reportProblem(JsonParser jsonParser, String str) throws JsonMappingException {
        throw new JsonMappingException(str, jsonParser.getTokenLocation());
    }

    /* access modifiers changed from: protected */
    public final ObjectNode deserializeObject(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException, JsonProcessingException {
        JsonNode jsonNode;
        ObjectNode objectNode = jsonNodeFactory.objectNode();
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (currentToken == JsonToken.START_OBJECT) {
            currentToken = jsonParser.nextToken();
        }
        while (currentToken == JsonToken.FIELD_NAME) {
            String currentName = jsonParser.getCurrentName();
            int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[jsonParser.nextToken().ordinal()];
            if (i == 1) {
                jsonNode = deserializeObject(jsonParser, deserializationContext, jsonNodeFactory);
            } else if (i == 2) {
                jsonNode = deserializeArray(jsonParser, deserializationContext, jsonNodeFactory);
            } else if (i != 3) {
                jsonNode = deserializeAny(jsonParser, deserializationContext, jsonNodeFactory);
            } else {
                jsonNode = jsonNodeFactory.textNode(jsonParser.getText());
            }
            JsonNode replace = objectNode.replace(currentName, jsonNode);
            if (replace != null) {
                _handleDuplicateField(currentName, objectNode, replace, jsonNode);
            }
            currentToken = jsonParser.nextToken();
        }
        return objectNode;
    }

    /* access modifiers changed from: protected */
    public final ArrayNode deserializeArray(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException, JsonProcessingException {
        ArrayNode arrayNode = jsonNodeFactory.arrayNode();
        while (true) {
            JsonToken nextToken = jsonParser.nextToken();
            if (nextToken != null) {
                int i = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$core$JsonToken[nextToken.ordinal()];
                if (i == 1) {
                    arrayNode.add(deserializeObject(jsonParser, deserializationContext, jsonNodeFactory));
                } else if (i == 2) {
                    arrayNode.add(deserializeArray(jsonParser, deserializationContext, jsonNodeFactory));
                } else if (i == 3) {
                    arrayNode.add(jsonNodeFactory.textNode(jsonParser.getText()));
                } else if (i == 4) {
                    return arrayNode;
                } else {
                    arrayNode.add(deserializeAny(jsonParser, deserializationContext, jsonNodeFactory));
                }
            } else {
                throw deserializationContext.mappingException("Unexpected end-of-input when binding data into ArrayNode");
            }
        }
    }

    /* access modifiers changed from: protected */
    public final JsonNode deserializeAny(JsonParser jsonParser, DeserializationContext deserializationContext, JsonNodeFactory jsonNodeFactory) throws IOException, JsonProcessingException {
        switch (jsonParser.getCurrentToken()) {
            case START_OBJECT:
                return deserializeObject(jsonParser, deserializationContext, jsonNodeFactory);
            case START_ARRAY:
                return deserializeArray(jsonParser, deserializationContext, jsonNodeFactory);
            case VALUE_STRING:
                return jsonNodeFactory.textNode(jsonParser.getText());
            case END_ARRAY:
            default:
                throw deserializationContext.mappingException(getValueClass());
            case FIELD_NAME:
                return deserializeObject(jsonParser, deserializationContext, jsonNodeFactory);
            case VALUE_EMBEDDED_OBJECT:
                Object embeddedObject = jsonParser.getEmbeddedObject();
                if (embeddedObject == null) {
                    return jsonNodeFactory.nullNode();
                }
                if (embeddedObject.getClass() == byte[].class) {
                    return jsonNodeFactory.binaryNode((byte[]) embeddedObject);
                }
                return jsonNodeFactory.pojoNode(embeddedObject);
            case VALUE_NUMBER_INT:
                JsonParser.NumberType numberType = jsonParser.getNumberType();
                if (numberType == JsonParser.NumberType.BIG_INTEGER || deserializationContext.isEnabled(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS)) {
                    return jsonNodeFactory.numberNode(jsonParser.getBigIntegerValue());
                }
                if (numberType == JsonParser.NumberType.INT) {
                    return jsonNodeFactory.numberNode(jsonParser.getIntValue());
                }
                return jsonNodeFactory.numberNode(jsonParser.getLongValue());
            case VALUE_NUMBER_FLOAT:
                if (jsonParser.getNumberType() == JsonParser.NumberType.BIG_DECIMAL || deserializationContext.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)) {
                    return jsonNodeFactory.numberNode(jsonParser.getDecimalValue());
                }
                return jsonNodeFactory.numberNode(jsonParser.getDoubleValue());
            case VALUE_TRUE:
                return jsonNodeFactory.booleanNode(true);
            case VALUE_FALSE:
                return jsonNodeFactory.booleanNode(false);
            case VALUE_NULL:
                return jsonNodeFactory.nullNode();
        }
    }
}
