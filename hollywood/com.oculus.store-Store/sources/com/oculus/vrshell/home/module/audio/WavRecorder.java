package com.oculus.vrshell.home.module.audio;

import androidx.annotation.NonNull;
import com.google.common.base.Ascii;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public abstract class WavRecorder extends Recorder {
    public WavRecorder(@NonNull File path) {
        super(path);
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.home.module.audio.Recorder
    public String getFileExtension() {
        return "wav";
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.home.module.audio.Recorder
    public void writeFileHeader(@NonNull OutputStream os) throws IOException {
        short channels;
        short bitDepth;
        switch (getChannelMask()) {
            case 12:
                channels = 2;
                break;
            case 16:
                channels = 1;
                break;
            default:
                throw new IllegalArgumentException("Unacceptable channel mask");
        }
        switch (getEncoding()) {
            case 2:
                bitDepth = 16;
                break;
            case 3:
                bitDepth = 8;
                break;
            case 4:
                bitDepth = 32;
                break;
            default:
                throw new IllegalArgumentException("Unacceptable encoding");
        }
        int sampleRate = getSampleRate();
        byte[] littleBytes = ByteBuffer.allocate(14).order(ByteOrder.LITTLE_ENDIAN).putShort(channels).putInt(sampleRate).putInt(sampleRate * channels * (bitDepth / 8)).putShort((short) ((bitDepth / 8) * channels)).putShort(bitDepth).array();
        os.write(new byte[]{82, 73, 70, 70, 0, 0, 0, 0, 87, 65, 86, 69, 102, 109, 116, 32, Ascii.DLE, 0, 0, 0, 1, 0, littleBytes[0], littleBytes[1], littleBytes[2], littleBytes[3], littleBytes[4], littleBytes[5], littleBytes[6], littleBytes[7], littleBytes[8], littleBytes[9], littleBytes[10], littleBytes[11], littleBytes[12], littleBytes[13], 100, 97, 116, 97, 0, 0, 0, 0});
    }

    /* access modifiers changed from: protected */
    @Override // com.oculus.vrshell.home.module.audio.Recorder
    public void writeFileFooter(@NonNull File file) throws IOException {
        Throwable th;
        IOException ex;
        byte[] sizes = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN).putInt((int) (file.length() - 8)).putInt((int) (file.length() - 44)).array();
        RandomAccessFile accessWave = null;
        try {
            RandomAccessFile accessWave2 = new RandomAccessFile(file, "rw");
            try {
                accessWave2.seek(4);
                accessWave2.write(sizes, 0, 4);
                accessWave2.seek(40);
                accessWave2.write(sizes, 4, 4);
                if (accessWave2 != null) {
                    try {
                        accessWave2.close();
                    } catch (IOException e) {
                    }
                }
            } catch (IOException e2) {
                ex = e2;
                accessWave = accessWave2;
                try {
                    throw ex;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                accessWave = accessWave2;
                if (accessWave != null) {
                    try {
                        accessWave.close();
                    } catch (IOException e3) {
                    }
                }
                throw th;
            }
        } catch (IOException e4) {
            ex = e4;
            throw ex;
        }
    }
}
