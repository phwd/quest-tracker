package com.facebook.flatbuffers.flatteners;

import android.net.Uri;
import com.facebook.flatbuffers.FlatBuffer;
import com.facebook.flatbuffers.FlatBufferBuilder;
import com.facebook.flatbuffers.Flattener;
import java.nio.ByteBuffer;

public class UriFlattener implements Flattener<Uri> {
    public static final UriFlattener INSTANCE = new UriFlattener();

    public int flattenToBuffer(Uri uri, FlatBufferBuilder flatBufferBuilder) {
        int createStringReference = flatBufferBuilder.createStringReference(uri.toString());
        flatBufferBuilder.startObject(1);
        flatBufferBuilder.addReference(0, createStringReference);
        return flatBufferBuilder.endObject();
    }

    @Override // com.facebook.flatbuffers.Flattener
    public Uri initFromFlatBuffer(ByteBuffer byteBuffer, int i) {
        return Uri.parse(FlatBuffer.resolveStringReference(byteBuffer, i, 0));
    }
}
