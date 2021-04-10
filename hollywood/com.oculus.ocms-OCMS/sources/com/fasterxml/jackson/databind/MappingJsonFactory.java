package com.fasterxml.jackson.databind;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;
import java.io.IOException;

public class MappingJsonFactory extends JsonFactory {
    private static final long serialVersionUID = -6744103724013275513L;

    @Override // com.fasterxml.jackson.core.JsonFactory
    public String getFormatName() {
        return JsonFactory.FORMAT_NAME_JSON;
    }

    public MappingJsonFactory() {
        this(null);
    }

    public MappingJsonFactory(ObjectMapper objectMapper) {
        super(objectMapper);
        if (objectMapper == null) {
            setCodec(new ObjectMapper(this));
        }
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    public final ObjectMapper getCodec() {
        return (ObjectMapper) this._objectCodec;
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    public JsonFactory copy() {
        _checkInvalidCopy(MappingJsonFactory.class);
        return new MappingJsonFactory(null);
    }

    @Override // com.fasterxml.jackson.core.JsonFactory
    public MatchStrength hasFormat(InputAccessor inputAccessor) throws IOException {
        if (getClass() == MappingJsonFactory.class) {
            return hasJSONFormat(inputAccessor);
        }
        return null;
    }
}
