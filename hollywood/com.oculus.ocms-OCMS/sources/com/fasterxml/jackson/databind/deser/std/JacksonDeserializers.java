package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.databind.deser.CreatorProperty;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;

public class JacksonDeserializers {
    @Deprecated
    public static StdDeserializer<?>[] all() {
        return new StdDeserializer[]{JavaTypeDeserializer.instance, TokenBufferDeserializer.instance};
    }

    public static JsonDeserializer<?> find(Class<?> cls) {
        if (cls == TokenBuffer.class) {
            return TokenBufferDeserializer.instance;
        }
        if (JavaType.class.isAssignableFrom(cls)) {
            return JavaTypeDeserializer.instance;
        }
        return null;
    }

    public static ValueInstantiator findValueInstantiator(DeserializationConfig deserializationConfig, BeanDescription beanDescription) {
        if (beanDescription.getBeanClass() == JsonLocation.class) {
            return JsonLocationInstantiator.instance;
        }
        return null;
    }

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

    public static class JsonLocationInstantiator extends ValueInstantiator {
        public static final JsonLocationInstantiator instance = new JsonLocationInstantiator();

        @Override // com.fasterxml.jackson.databind.deser.ValueInstantiator
        public boolean canCreateFromObjectWith() {
            return true;
        }

        @Override // com.fasterxml.jackson.databind.deser.ValueInstantiator
        public String getValueTypeDesc() {
            return JsonLocation.class.getName();
        }

        @Override // com.fasterxml.jackson.databind.deser.ValueInstantiator
        public SettableBeanProperty[] getFromObjectArguments(DeserializationConfig deserializationConfig) {
            JavaType constructType = deserializationConfig.constructType(Integer.TYPE);
            JavaType constructType2 = deserializationConfig.constructType(Long.TYPE);
            return new CreatorProperty[]{creatorProp("sourceRef", deserializationConfig.constructType(Object.class), 0), creatorProp("byteOffset", constructType2, 1), creatorProp("charOffset", constructType2, 2), creatorProp("lineNr", constructType, 3), creatorProp("columnNr", constructType, 4)};
        }

        private static CreatorProperty creatorProp(String str, JavaType javaType, int i) {
            return new CreatorProperty(str, javaType, null, null, null, null, i, null, true);
        }

        @Override // com.fasterxml.jackson.databind.deser.ValueInstantiator
        public Object createFromObjectWith(DeserializationContext deserializationContext, Object[] objArr) {
            return new JsonLocation(objArr[0], _long(objArr[1]), _long(objArr[2]), _int(objArr[3]), _int(objArr[4]));
        }

        private static final long _long(Object obj) {
            if (obj == null) {
                return 0;
            }
            return ((Number) obj).longValue();
        }

        private static final int _int(Object obj) {
            if (obj == null) {
                return 0;
            }
            return ((Number) obj).intValue();
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
