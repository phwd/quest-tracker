package X;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import com.facebook.infer.annotation.Nullsafe;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Comparator;

@TargetApi(18)
@Nullsafe(Nullsafe.Mode.LOCAL)
/* renamed from: X.1Xx  reason: invalid class name */
public final class AnonymousClass1Xx {
    public static final Comparator<File> A00 = new AnonymousClass1Xy();

    public static void A01(ByteBuffer byteBuffer, MediaCodec.BufferInfo bufferInfo, boolean z, DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(bufferInfo.size);
        dataOutputStream.writeInt(bufferInfo.offset);
        dataOutputStream.writeLong(bufferInfo.presentationTimeUs);
        dataOutputStream.writeInt(bufferInfo.flags);
        dataOutputStream.writeInt(z ? 1 : 0);
        byte[] bArr = new byte[byteBuffer.remaining()];
        byteBuffer.get(bArr);
        dataOutputStream.write(bArr);
    }

    public static MediaCodec.BufferInfo A00(DataInputStream dataInputStream) throws IOException {
        int readInt = dataInputStream.readInt();
        int readInt2 = dataInputStream.readInt();
        long readLong = dataInputStream.readLong();
        int readInt3 = dataInputStream.readInt();
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        bufferInfo.set(readInt2, readInt, readLong, readInt3);
        return bufferInfo;
    }
}
