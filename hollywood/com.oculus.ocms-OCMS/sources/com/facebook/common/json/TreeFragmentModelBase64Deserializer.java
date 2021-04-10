package com.facebook.common.json;

import android.util.Base64;
import com.facebook.graphservice.interfaces.Tree;
import com.facebook.graphservice.staticcontext.StaticGraphServiceFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.google.common.base.Throwables;
import java.io.IOException;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;

public class TreeFragmentModelBase64Deserializer extends FbJsonDeserializer {
    private Class<Tree> treeClass;

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: java.lang.Class<?> */
    /* JADX WARN: Multi-variable type inference failed */
    public TreeFragmentModelBase64Deserializer(Class<?> cls) {
        this.treeClass = cls;
    }

    @Override // com.facebook.common.json.FbJsonDeserializer, com.fasterxml.jackson.databind.JsonDeserializer
    @Nullable
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        try {
            String valueAsString = jsonParser.getValueAsString();
            if (valueAsString == null) {
                return null;
            }
            int typeTag = TreeEncodingUtils.getTypeTag(valueAsString);
            return StaticGraphServiceFactory.getTreeSerializer().deserializeTreeFromByteBuffer(ByteBuffer.wrap(Base64.decode(TreeEncodingUtils.stripTypeTagPrefix(valueAsString), 2)), this.treeClass, typeTag);
        } catch (Exception e) {
            Throwables.propagateIfPossible(e, IOException.class);
            FbJsonUtil.throwDeserializationFailure(this.treeClass, jsonParser, e);
            return null;
        }
    }
}
