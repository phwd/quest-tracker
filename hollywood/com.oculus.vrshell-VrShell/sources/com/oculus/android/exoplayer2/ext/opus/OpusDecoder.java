package com.oculus.android.exoplayer2.ext.opus;

import com.oculus.android.exoplayer2.C;
import com.oculus.android.exoplayer2.decoder.CryptoInfo;
import com.oculus.android.exoplayer2.decoder.DecoderInputBuffer;
import com.oculus.android.exoplayer2.decoder.SimpleDecoder;
import com.oculus.android.exoplayer2.decoder.SimpleOutputBuffer;
import com.oculus.android.exoplayer2.drm.DecryptionException;
import com.oculus.android.exoplayer2.drm.ExoMediaCrypto;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

public final class OpusDecoder extends SimpleDecoder<DecoderInputBuffer, SimpleOutputBuffer, OpusDecoderException> {
    private static final int DECODE_ERROR = -1;
    private static final int DEFAULT_SEEK_PRE_ROLL_SAMPLES = 3840;
    private static final int DRM_ERROR = -2;
    private static final int MAX_CHANNEL_COUNT = 255;
    private static final int NO_ERROR = 0;
    private static final int SAMPLE_RATE = 48000;
    private final int channelCount;
    private final ExoMediaCrypto exoMediaCrypto;
    private final int headerSeekPreRollSamples;
    private final int headerSkipSamples;
    private final long nativeDecoderContext;
    private int skipSamples;

    private native void opusClose(long j);

    private native int opusDecode(long j, long j2, ByteBuffer byteBuffer, int i, SimpleOutputBuffer simpleOutputBuffer);

    private native int opusGetErrorCode(long j);

    private native String opusGetErrorMessage(long j);

    private native long opusInit(int i, int i2, int i3, int i4, int i5, byte[] bArr);

    private native void opusReset(long j);

    private native int opusSecureDecode(long j, long j2, ByteBuffer byteBuffer, int i, SimpleOutputBuffer simpleOutputBuffer, int i2, ExoMediaCrypto exoMediaCrypto2, int i3, byte[] bArr, byte[] bArr2, int i4, int[] iArr, int[] iArr2);

    public int getSampleRate() {
        return SAMPLE_RATE;
    }

    public OpusDecoder(int i, int i2, int i3, List<byte[]> list, ExoMediaCrypto exoMediaCrypto2) throws OpusDecoderException {
        super(new DecoderInputBuffer[i], new SimpleOutputBuffer[i2]);
        int i4;
        int i5;
        if (OpusLibrary.isAvailable()) {
            this.exoMediaCrypto = exoMediaCrypto2;
            if (exoMediaCrypto2 == null || OpusLibrary.opusIsSecureDecodeSupported()) {
                byte[] bArr = list.get(0);
                if (bArr.length >= 19) {
                    this.channelCount = bArr[9] & 255;
                    if (this.channelCount <= 255) {
                        int readLittleEndian16 = readLittleEndian16(bArr, 10);
                        int readLittleEndian162 = readLittleEndian16(bArr, 16);
                        int i6 = this.channelCount;
                        byte[] bArr2 = new byte[i6];
                        if (bArr[18] == 0) {
                            if (i6 <= 2) {
                                int i7 = i6 == 2 ? 1 : 0;
                                bArr2[0] = 0;
                                bArr2[1] = 1;
                                i4 = i7;
                                i5 = 1;
                            } else {
                                throw new OpusDecoderException("Invalid Header, missing stream map.");
                            }
                        } else if (bArr.length >= i6 + 21) {
                            i5 = bArr[19] & 255;
                            i4 = bArr[20] & 255;
                            System.arraycopy(bArr, 21, bArr2, 0, i6);
                        } else {
                            throw new OpusDecoderException("Header size is too small.");
                        }
                        if (list.size() != 3) {
                            this.headerSkipSamples = readLittleEndian16;
                            this.headerSeekPreRollSamples = DEFAULT_SEEK_PRE_ROLL_SAMPLES;
                        } else if (list.get(1).length == 8 && list.get(2).length == 8) {
                            long j = ByteBuffer.wrap(list.get(1)).order(ByteOrder.nativeOrder()).getLong();
                            long j2 = ByteBuffer.wrap(list.get(2)).order(ByteOrder.nativeOrder()).getLong();
                            this.headerSkipSamples = nsToSamples(j);
                            this.headerSeekPreRollSamples = nsToSamples(j2);
                        } else {
                            throw new OpusDecoderException("Invalid Codec Delay or Seek Preroll");
                        }
                        this.nativeDecoderContext = opusInit(SAMPLE_RATE, this.channelCount, i5, i4, readLittleEndian162, bArr2);
                        if (this.nativeDecoderContext != 0) {
                            setInitialInputBufferSize(i3);
                            return;
                        }
                        throw new OpusDecoderException("Failed to initialize decoder");
                    }
                    throw new OpusDecoderException("Invalid channel count: " + this.channelCount);
                }
                throw new OpusDecoderException("Header size is too small.");
            }
            throw new OpusDecoderException("Opus decoder does not support secure decode.");
        }
        throw new OpusDecoderException("Failed to load decoder native libraries.");
    }

    @Override // com.oculus.android.exoplayer2.decoder.Decoder
    public String getName() {
        return "libopus" + OpusLibrary.getVersion();
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.decoder.SimpleDecoder
    public DecoderInputBuffer createInputBuffer() {
        return new DecoderInputBuffer(2);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.decoder.SimpleDecoder
    public SimpleOutputBuffer createOutputBuffer() {
        return new SimpleOutputBuffer(this);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.android.exoplayer2.decoder.SimpleDecoder
    public OpusDecoderException createUnexpectedDecodeException(Throwable th) {
        return new OpusDecoderException("Unexpected decode error", th);
    }

    /* access modifiers changed from: protected */
    public OpusDecoderException decode(DecoderInputBuffer decoderInputBuffer, SimpleOutputBuffer simpleOutputBuffer, boolean z) {
        OpusDecoder opusDecoder;
        int i;
        if (z) {
            opusReset(this.nativeDecoderContext);
            this.skipSamples = decoderInputBuffer.timeUs == 0 ? this.headerSkipSamples : this.headerSeekPreRollSamples;
        }
        ByteBuffer byteBuffer = decoderInputBuffer.data;
        int position = byteBuffer.position();
        byteBuffer.position(0);
        CryptoInfo cryptoInfo = decoderInputBuffer.cryptoInfo;
        if (decoderInputBuffer.isEncrypted()) {
            i = opusSecureDecode(this.nativeDecoderContext, decoderInputBuffer.timeUs, byteBuffer, position, simpleOutputBuffer, SAMPLE_RATE, this.exoMediaCrypto, cryptoInfo.mode, cryptoInfo.key, cryptoInfo.iv, cryptoInfo.numSubSamples, cryptoInfo.numBytesOfClearData, cryptoInfo.numBytesOfEncryptedData);
            opusDecoder = this;
        } else {
            opusDecoder = this;
            i = opusDecode(opusDecoder.nativeDecoderContext, decoderInputBuffer.timeUs, byteBuffer, position, simpleOutputBuffer);
        }
        if (i >= 0) {
            ByteBuffer byteBuffer2 = simpleOutputBuffer.data;
            byteBuffer2.position(0);
            byteBuffer2.limit(i);
            int i2 = opusDecoder.skipSamples;
            if (i2 <= 0) {
                return null;
            }
            int i3 = opusDecoder.channelCount * 2;
            int i4 = i2 * i3;
            if (i <= i4) {
                opusDecoder.skipSamples = i2 - (i / i3);
                simpleOutputBuffer.addFlag(Integer.MIN_VALUE);
                byteBuffer2.position(i);
                return null;
            }
            opusDecoder.skipSamples = 0;
            byteBuffer2.position(i4);
            return null;
        } else if (i == -2) {
            String str = "Drm error: " + opusDecoder.opusGetErrorMessage(opusDecoder.nativeDecoderContext);
            return new OpusDecoderException(str, new DecryptionException(opusDecoder.opusGetErrorCode(opusDecoder.nativeDecoderContext), str));
        } else {
            return new OpusDecoderException("Decode error: " + opusDecoder.opusGetErrorMessage((long) i));
        }
    }

    @Override // com.oculus.android.exoplayer2.decoder.SimpleDecoder, com.oculus.android.exoplayer2.decoder.Decoder
    public void release() {
        super.release();
        opusClose(this.nativeDecoderContext);
    }

    public int getChannelCount() {
        return this.channelCount;
    }

    private static int nsToSamples(long j) {
        return (int) ((j * 48000) / C.NANOS_PER_SECOND);
    }

    private static int readLittleEndian16(byte[] bArr, int i) {
        return ((bArr[i + 1] & 255) << 8) | (bArr[i] & 255);
    }
}
