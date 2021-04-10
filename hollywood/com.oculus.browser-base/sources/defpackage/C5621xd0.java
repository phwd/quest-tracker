package defpackage;

import android.media.MediaCodec;
import android.util.SparseArray;
import java.nio.ByteBuffer;
import org.chromium.media.MediaCodecBridge;

/* renamed from: xd0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C5621xd0 extends MediaCodecBridge {
    public SparseArray n = new SparseArray();
    public ByteBuffer o;

    public C5621xd0(MediaCodec mediaCodec, int i) {
        super(mediaCodec, i, false);
    }

    @Override // org.chromium.media.MediaCodecBridge
    public int b(MediaCodec.BufferInfo bufferInfo, long j) {
        ByteBuffer byteBuffer;
        int i = -1;
        try {
            i = this.c.dequeueOutputBuffer(bufferInfo, j);
            if (i >= 0) {
                if ((bufferInfo.flags & 2) != 0) {
                    ByteBuffer outputBuffer = super.getOutputBuffer(i);
                    if (outputBuffer != null) {
                        outputBuffer.position(bufferInfo.offset);
                        outputBuffer.limit(bufferInfo.offset + bufferInfo.size);
                        ByteBuffer allocateDirect = ByteBuffer.allocateDirect(bufferInfo.size);
                        this.o = allocateDirect;
                        allocateDirect.put(outputBuffer);
                        StringBuilder sb = new StringBuilder();
                        int i2 = 0;
                        while (true) {
                            int i3 = bufferInfo.size;
                            if (i3 >= 8) {
                                i3 = 8;
                            }
                            if (i2 >= i3) {
                                break;
                            }
                            sb.append(Integer.toHexString(this.o.get(i2) & 255));
                            sb.append(" ");
                            i2++;
                        }
                        AbstractC1220Ua0.d("MediaCodecEncoder", "spsData: %s", sb.toString());
                        this.c.releaseOutputBuffer(i, false);
                        i = this.c.dequeueOutputBuffer(bufferInfo, j);
                    } else {
                        throw new IllegalStateException("Got null output buffer");
                    }
                }
            }
            if (i >= 0) {
                ByteBuffer outputBuffer2 = super.getOutputBuffer(i);
                if (outputBuffer2 != null) {
                    outputBuffer2.position(bufferInfo.offset);
                    outputBuffer2.limit(bufferInfo.offset + bufferInfo.size);
                    if (!((bufferInfo.flags & 1) != 0) || (byteBuffer = this.o) == null) {
                        this.n.put(i, outputBuffer2);
                    } else {
                        byteBuffer.capacity();
                        ByteBuffer allocateDirect2 = ByteBuffer.allocateDirect(this.o.capacity() + bufferInfo.size);
                        this.o.rewind();
                        allocateDirect2.put(this.o);
                        allocateDirect2.put(outputBuffer2);
                        allocateDirect2.rewind();
                        bufferInfo.offset = 0;
                        bufferInfo.size += this.o.capacity();
                        this.n.put(i, allocateDirect2);
                    }
                } else {
                    throw new IllegalStateException("Got null output buffer");
                }
            }
        } catch (IllegalStateException e) {
            AbstractC1220Ua0.a("MediaCodecEncoder", "Failed to dequeue output buffer", e);
        }
        return i;
    }

    @Override // org.chromium.media.MediaCodecBridge
    public ByteBuffer getOutputBuffer(int i) {
        return (ByteBuffer) this.n.get(i);
    }

    @Override // org.chromium.media.MediaCodecBridge
    public void releaseOutputBuffer(int i, boolean z) {
        try {
            this.c.releaseOutputBuffer(i, z);
            this.n.remove(i);
        } catch (IllegalStateException e) {
            AbstractC1220Ua0.a("MediaCodecEncoder", "Failed to release output buffer", e);
        }
    }
}
