package X;

import android.media.MediaCodec;
import android.media.MediaFormat;
import java.io.IOException;
import java.nio.ByteBuffer;

/* renamed from: X.1yB  reason: invalid class name */
public interface AnonymousClass1yB {
    void A1o(String str) throws IOException;

    void A8b(MediaFormat mediaFormat);

    void A8h(int i);

    void A8t(MediaFormat mediaFormat);

    boolean A9Q() throws IOException, IllegalStateException;

    void AAB(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) throws IOException;

    void AAJ(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo) throws IOException;

    void start() throws IOException;
}
