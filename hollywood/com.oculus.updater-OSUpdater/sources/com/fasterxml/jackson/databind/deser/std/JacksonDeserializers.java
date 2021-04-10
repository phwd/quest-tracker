package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;

public class JacksonDeserializers {

    public static class JavaTypeDeserializer extends StdScalarDeserializer<JavaType> {
        public static final JavaTypeDeserializer instance = new JavaTypeDeserializer();

        public JavaTypeDeserializer() {
            super(JavaType.class);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public JavaType deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonToken currentToken = jsonParser.getCurrentToken();
            if (currentToken == JsonToken.VALUE_STRING) {
                String trim = jsonParser.getText().trim();
                if (trim.length() == 0) {
                    return (JavaType) getEmptyValue();
                }
                return deserializationContext.getTypeFactory().constructFromCanonical(trim);
            } else if (currentToken == JsonToken.VALUE_EMBEDDED_OBJECT) {
                return (JavaType) jsonParser.getEmbeddedObject();
            } else {
                throw deserializationContext.mappingException(this._valueClass);
            }
        }
    }

    @JacksonStdImpl
    public static class TokenBufferDeserializer extends StdScalarDeserializer<TokenBuffer> {
        public static final TokenBufferDeserializer instance = new TokenBufferDeserializer();

        public TokenBufferDeserializer() {
            super(TokenBuffer.class);
        }

        @Override // com.fasterxml.jackson.databind.JsonDeserializer
        public TokenBuffer deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            TokenBuffer tokenBuffer = new TokenBuffer(jsonParser.getCodec());
            tokenBuffer.copyCurrentStructure(jsonParser);
            return tokenBuffer;
        }
    }
}
