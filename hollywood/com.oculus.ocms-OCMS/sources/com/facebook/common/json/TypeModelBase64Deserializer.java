package com.facebook.common.json;

import android.util.Base64;
import com.facebook.flatbuffers.Flattenable;
import com.facebook.flatbuffers.ModelConstructorHelper;
import com.facebook.flatbuffers.MutableFlatBuffer;
import com.facebook.graphservice.staticcontext.StaticGraphServiceFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public class TypeModelBase64Deserializer extends FbJsonDeserializer {
    @Override // com.facebook.common.json.FbJsonDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    @Nullable
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        try {
            String valueAsString = jsonParser.getValueAsString();
            if (valueAsString == null) {
                return null;
            }
            if (valueAsString.startsWith(AutoGenJsonHelper.FLATBUFFER_PREFIX)) {
                String replaceFirst = valueAsString.replaceFirst(AutoGenJsonHelper.FLATBUFFER_PREFIX, "");
                int typeTag = TreeEncodingUtils.getTypeTag(replaceFirst);
                return MutableFlatBuffer.resolveRootFlattenable(ByteBuffer.wrap(Base64.decode(TreeEncodingUtils.stripTypeTagPrefix(replaceFirst), 2)), (Flattenable) ModelConstructorHelper.newTypeModelInstance(this.mClass, typeTag), (MutableFlatBuffer.FlatBufferCorruptionHandler) null);
            }
            Preconditions.checkState(valueAsString.startsWith(AutoGenJsonHelper.TREE_PREFIX));
            String replaceFirst2 = valueAsString.replaceFirst(AutoGenJsonHelper.TREE_PREFIX, "");
            int typeTag2 = TreeEncodingUtils.getTypeTag(replaceFirst2);
            return StaticGraphServiceFactory.getTreeSerializer().deserializeTreeFromByteBuffer(ByteBuffer.wrap(Base64.decode(TreeEncodingUtils.stripTypeTagPrefix(replaceFirst2), 2)), this.mClass, typeTag2);
        } catch (Exception e) {
            Throwables.propagateIfPossible(e, IOException.class);
            FbJsonUtil.throwDeserializationFailure(this.mClass, jsonParser, e);
            return null;
        }
    }
}
