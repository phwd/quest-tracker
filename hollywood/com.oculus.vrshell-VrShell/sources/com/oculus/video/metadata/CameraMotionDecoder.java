package com.oculus.video.metadata;

import com.oculus.android.exoplayer2.metadata.Metadata;
import com.oculus.android.exoplayer2.metadata.MetadataDecoder;
import com.oculus.android.exoplayer2.metadata.MetadataDecoderException;
import com.oculus.android.exoplayer2.metadata.MetadataInputBuffer;
import com.oculus.android.exoplayer2.util.ParsableByteArray;
import java.nio.ByteBuffer;

public class CameraMotionDecoder implements MetadataDecoder {
    public static final long ADVANCED_RENDERING_OFFSET_US = 100000;
    private static final long VR180_BUFFER_SIZE = 16;

    @Override // com.oculus.android.exoplayer2.metadata.MetadataDecoder
    public Metadata decode(MetadataInputBuffer metadataInputBuffer) throws MetadataDecoderException {
        ByteBuffer byteBuffer = metadataInputBuffer.data;
        if (((long) byteBuffer.limit()) != 16) {
            return null;
        }
        ParsableByteArray parsableByteArray = new ParsableByteArray(byteBuffer.array(), byteBuffer.limit());
        float[] fArr = new float[3];
        float[] fArr2 = new float[(fArr.length + 1)];
        fArr2[0] = Float.intBitsToFloat(parsableByteArray.readLittleEndianInt());
        fArr[0] = Float.intBitsToFloat(parsableByteArray.readLittleEndianInt());
        fArr[1] = Float.intBitsToFloat(parsableByteArray.readLittleEndianInt());
        fArr[2] = Float.intBitsToFloat(parsableByteArray.readLittleEndianInt());
        int i = 1;
        int i2 = 0;
        while (i2 < fArr.length) {
            fArr2[i] = fArr[i2];
            i2++;
            i++;
        }
        return new Metadata(new CameraMotionData(metadataInputBuffer.timeUs, fArr2));
    }
}
