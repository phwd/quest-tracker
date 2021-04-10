package com.oculus.android.exoplayer2.metadata.emsg;

import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.metadata.Metadata;
import com.oculus.android.exoplayer2.metadata.MetadataDecoder;
import com.oculus.android.exoplayer2.metadata.MetadataInputBuffer;
import com.oculus.android.exoplayer2.util.ParsableByteArray;
import com.oculus.android.exoplayer2.util.Util;
import java.nio.ByteBuffer;
import java.util.Arrays;

public final class EventMessageDecoder implements MetadataDecoder {
    @Override // com.oculus.android.exoplayer2.metadata.MetadataDecoder
    public Metadata decode(MetadataInputBuffer metadataInputBuffer) {
        ByteBuffer byteBuffer = metadataInputBuffer.data;
        byte[] array = byteBuffer.array();
        int limit = byteBuffer.limit();
        ParsableByteArray parsableByteArray = new ParsableByteArray(array, limit);
        String readNullTerminatedString = parsableByteArray.readNullTerminatedString();
        String readNullTerminatedString2 = parsableByteArray.readNullTerminatedString();
        long readUnsignedInt = parsableByteArray.readUnsignedInt();
        long scaleLargeTimestamp = Util.scaleLargeTimestamp(parsableByteArray.readUnsignedInt(), C.MICROS_PER_SECOND, readUnsignedInt);
        return new Metadata(new EventMessage(readNullTerminatedString, readNullTerminatedString2, Util.scaleLargeTimestamp(parsableByteArray.readUnsignedInt(), 1000, readUnsignedInt), parsableByteArray.readUnsignedInt(), Arrays.copyOfRange(array, parsableByteArray.getPosition(), limit), scaleLargeTimestamp));
    }
}
