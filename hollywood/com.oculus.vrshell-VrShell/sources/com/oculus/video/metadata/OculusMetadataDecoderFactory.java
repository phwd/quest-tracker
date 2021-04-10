package com.oculus.video.metadata;

import com.oculus.android.exoplayer2.Format;
import com.oculus.android.exoplayer2.metadata.MetadataDecoder;
import com.oculus.android.exoplayer2.metadata.MetadataDecoderFactory;
import com.oculus.android.exoplayer2.util.MimeTypes;

public class OculusMetadataDecoderFactory implements MetadataDecoderFactory {
    @Override // com.oculus.android.exoplayer2.metadata.MetadataDecoderFactory
    public boolean supportsFormat(Format format) {
        if (MetadataDecoderFactory.DEFAULT.supportsFormat(format)) {
            return true;
        }
        return MimeTypes.APPLICATION_CAMERA_MOTION.equals(format.sampleMimeType);
    }

    @Override // com.oculus.android.exoplayer2.metadata.MetadataDecoderFactory
    public MetadataDecoder createDecoder(Format format) {
        if (MimeTypes.APPLICATION_CAMERA_MOTION.equals(format.sampleMimeType)) {
            return new CameraMotionDecoder();
        }
        return MetadataDecoderFactory.DEFAULT.createDecoder(format);
    }
}
