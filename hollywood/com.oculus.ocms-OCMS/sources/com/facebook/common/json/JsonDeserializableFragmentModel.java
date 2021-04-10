package com.facebook.common.json;

import com.facebook.flatbuffers.FlatBufferBuilder;
import com.facebook.flatbuffers.MutableFlatBuffer;
import com.fasterxml.jackson.core.JsonParser;
import java.io.IOException;

public interface JsonDeserializableFragmentModel {
    int flattenFromJson(JsonParser jsonParser, FlatBufferBuilder flatBufferBuilder) throws IllegalArgumentException, IOException;

    void initFromMutableFlatBufferWithFieldTracking(MutableFlatBuffer mutableFlatBuffer, int i, Object obj);
}
