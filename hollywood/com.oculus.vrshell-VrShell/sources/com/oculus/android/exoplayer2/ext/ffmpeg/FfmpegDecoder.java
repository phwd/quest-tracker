package com.oculus.android.exoplayer2.ext.ffmpeg;

import com.oculus.android.exoplayer2.decoder.DecoderInputBuffer;
import com.oculus.android.exoplayer2.decoder.SimpleDecoder;
import com.oculus.android.exoplayer2.decoder.SimpleOutputBuffer;
import com.oculus.android.exoplayer2.util.MimeTypes;
import com.oculus.android.exoplayer2.util.ParsableByteArray;
import java.nio.ByteBuffer;
import java.util.List;

final class FfmpegDecoder extends SimpleDecoder<DecoderInputBuffer, SimpleOutputBuffer, FfmpegDecoderException> {
    private static final int OUTPUT_BUFFER_SIZE_16BIT = 49152;
    private static final int OUTPUT_BUFFER_SIZE_32BIT = 98304;
    private volatile int channelCount;
    private final String codecName;
    private final int encoding;
    private final byte[] extraData;
    private boolean hasOutputFormat;
    private long nativeContext;
    private final int outputBufferSize;
    private volatile int sampleRate;

    private native int ffmpegDecode(long j, ByteBuffer byteBuffer, int i, ByteBuffer byteBuffer2, int i2);

    private native int ffmpegGetChannelCount(long j);

    private native int ffmpegGetSampleRate(long j);

    private native long ffmpegInitialize(String str, byte[] bArr, boolean z);

    private native void ffmpegRelease(long j);

    private native long ffmpegReset(long j, byte[] bArr);

    public FfmpegDecoder(int i, int i2, int i3, String str, List<byte[]> list, boolean z) throws FfmpegDecoderException {
        super(new DecoderInputBuffer[i], new SimpleOutputBuffer[i2]);
        if (FfmpegLibrary.isAvailable()) {
            this.codecName = FfmpegLibrary.getCodecName(str);
            this.extraData = getExtraData(str, list);
            this.encoding = z ? 4 : 2;
            this.outputBufferSize = z ? OUTPUT_BUFFER_SIZE_32BIT : OUTPUT_BUFFER_SIZE_16BIT;
            this.nativeContext = ffmpegInitialize(this.codecName, this.extraData, z);
            if (this.nativeContext != 0) {
                setInitialInputBufferSize(i3);
                return;
            }
            throw new FfmpegDecoderException("Initialization failed.");
        }
        throw new FfmpegDecoderException("Failed to load decoder native libraries.");
    }

    @Override // com.oculus.android.exoplayer2.decoder.Decoder
    public String getName() {
        return "ffmpeg" + FfmpegLibrary.getVersion() + "-" + this.codecName;
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
    public FfmpegDecoderException createUnexpectedDecodeException(Throwable th) {
        return new FfmpegDecoderException("Unexpected decode error", th);
    }

    /* access modifiers changed from: protected */
    public FfmpegDecoderException decode(DecoderInputBuffer decoderInputBuffer, SimpleOutputBuffer simpleOutputBuffer, boolean z) {
        if (z) {
            this.nativeContext = ffmpegReset(this.nativeContext, this.extraData);
            if (this.nativeContext == 0) {
                return new FfmpegDecoderException("Error resetting (see logcat).");
            }
        }
        ByteBuffer byteBuffer = decoderInputBuffer.data;
        int ffmpegDecode = ffmpegDecode(this.nativeContext, byteBuffer, byteBuffer.limit(), simpleOutputBuffer.init(decoderInputBuffer.timeUs, this.outputBufferSize), this.outputBufferSize);
        if (ffmpegDecode < 0) {
            return new FfmpegDecoderException("Error decoding (see logcat). Code: " + ffmpegDecode);
        }
        if (!this.hasOutputFormat) {
            this.channelCount = ffmpegGetChannelCount(this.nativeContext);
            this.sampleRate = ffmpegGetSampleRate(this.nativeContext);
            if (this.sampleRate == 0 && "alac".equals(this.codecName)) {
                ParsableByteArray parsableByteArray = new ParsableByteArray(this.extraData);
                parsableByteArray.setPosition(this.extraData.length - 4);
                this.sampleRate = parsableByteArray.readUnsignedIntToInt();
            }
            this.hasOutputFormat = true;
        }
        simpleOutputBuffer.data.position(0);
        simpleOutputBuffer.data.limit(ffmpegDecode);
        return null;
    }

    @Override // com.oculus.android.exoplayer2.decoder.SimpleDecoder, com.oculus.android.exoplayer2.decoder.Decoder
    public void release() {
        super.release();
        ffmpegRelease(this.nativeContext);
        this.nativeContext = 0;
    }

    public int getChannelCount() {
        return this.channelCount;
    }

    public int getSampleRate() {
        return this.sampleRate;
    }

    public int getEncoding() {
        return this.encoding;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private static byte[] getExtraData(String str, List<byte[]> list) {
        char c;
        switch (str.hashCode()) {
            case -1003765268:
                if (str.equals(MimeTypes.AUDIO_VORBIS)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -53558318:
                if (str.equals(MimeTypes.AUDIO_AAC)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 1504470054:
                if (str.equals(MimeTypes.AUDIO_ALAC)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 1504891608:
                if (str.equals(MimeTypes.AUDIO_OPUS)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0 || c == 1 || c == 2) {
            return list.get(0);
        }
        if (c != 3) {
            return null;
        }
        byte[] bArr = list.get(0);
        byte[] bArr2 = list.get(1);
        byte[] bArr3 = new byte[(bArr.length + bArr2.length + 6)];
        bArr3[0] = (byte) (bArr.length >> 8);
        bArr3[1] = (byte) (bArr.length & 255);
        System.arraycopy(bArr, 0, bArr3, 2, bArr.length);
        bArr3[bArr.length + 2] = 0;
        bArr3[bArr.length + 3] = 0;
        bArr3[bArr.length + 4] = (byte) (bArr2.length >> 8);
        bArr3[bArr.length + 5] = (byte) (bArr2.length & 255);
        System.arraycopy(bArr2, 0, bArr3, bArr.length + 6, bArr2.length);
        return bArr3;
    }
}
