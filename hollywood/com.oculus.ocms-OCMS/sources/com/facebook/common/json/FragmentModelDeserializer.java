package com.facebook.common.json;

import com.facebook.flatbuffers.FlatBuffer;
import com.facebook.flatbuffers.FlatBufferBuilder;
import com.facebook.flatbuffers.MutableFlatBuffer;
import com.facebook.infer.annotation.Nullsafe;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.google.common.base.Throwables;
import java.io.IOException;
import java.nio.ByteBuffer;

@Nullsafe(Nullsafe.Mode.LOCAL)
public class FragmentModelDeserializer extends FbJsonDeserializer {
    @Override // com.facebook.common.json.FbJsonDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        try {
            JsonDeserializableFragmentModel jsonDeserializableFragmentModel = (JsonDeserializableFragmentModel) callDefaultConstructor();
            FlatBufferBuilder flatBufferBuilder = new FlatBufferBuilder(128);
            flatBufferBuilder.finish(jsonDeserializableFragmentModel.flattenFromJson(jsonParser, flatBufferBuilder));
            ByteBuffer wrap = ByteBuffer.wrap(flatBufferBuilder.sizedByteArray());
            wrap.position(0);
            MutableFlatBuffer mutableFlatBuffer = new MutableFlatBuffer(wrap, null, true, null);
            mutableFlatBuffer.addTag(4, true);
            mutableFlatBuffer.markDebugSource("FragmentModelDeserializer.deserialize");
            jsonDeserializableFragmentModel.initFromMutableFlatBufferWithFieldTracking(mutableFlatBuffer, FlatBuffer.getRootObjectPosition(mutableFlatBuffer.getBaseBuffer()), jsonParser);
            return jsonDeserializableFragmentModel;
        } catch (Exception e) {
            Throwables.propagateIfPossible(e, IOException.class);
            FbJsonUtil.throwDeserializationFailure(this.mClass, jsonParser, e);
            throw new RuntimeException("not reached");
        }
    }
}
