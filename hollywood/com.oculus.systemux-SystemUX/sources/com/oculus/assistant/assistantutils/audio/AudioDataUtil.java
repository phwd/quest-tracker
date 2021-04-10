package com.oculus.assistant.assistantutils.audio;

import android.util.Log;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.Charset;

public class AudioDataUtil {
    public static final int AUDIO_BUFFER_SIZE = 14400;
    public static final int WAV_HEADER_SIZE = 44;

    public static int getSampleRate() {
        return 48000;
    }

    public static int getVoiceServiceSampleRate() {
        return 16000;
    }

    public static short decodePcm(byte[] bArr, int i) {
        return decodePcm(ByteBuffer.wrap(bArr, i, 2));
    }

    public static short decodePcm(ByteBuffer byteBuffer) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return byteBuffer.getShort();
    }

    public static void encodePcm(float f, byte[] bArr, int i) {
        encodePcm(f, ByteBuffer.wrap(bArr, i, 2));
    }

    public static void encodePcm(float f, ByteBuffer byteBuffer) {
        encodePcm((short) ((int) (f * 32767.0f)), byteBuffer);
    }

    public static void encodePcm(short s, byte[] bArr, int i) {
        encodePcm(s, ByteBuffer.wrap(bArr, i, 2));
    }

    public static void encodePcm(short s, ByteBuffer byteBuffer) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        byteBuffer.putShort(s);
    }

    public static byte[] encodeWavHeader(int i) {
        byte[] bArr = new byte[44];
        encodeWavHeader(ByteBuffer.wrap(bArr), i);
        return bArr;
    }

    public static void log(String str, String str2, byte[] bArr) {
        int i = 0;
        String str3 = "";
        while (i < 5 && i * 2 < bArr.length) {
            StringBuilder sb = new StringBuilder();
            sb.append(str3);
            sb.append(String.format("%6s", "" + ((int) decodePcm(bArr, i))));
            str3 = sb.toString();
            i++;
        }
        Log.v(str, str2 + "[" + bArr.length + " bytes]:\t" + str3);
    }

    public static void log(String str, String str2, short[] sArr) {
        int i = 0;
        String str3 = "";
        while (i < 5 && i < sArr.length) {
            StringBuilder sb = new StringBuilder();
            sb.append(str3);
            sb.append(String.format("%6s", "" + ((int) sArr[i])));
            str3 = sb.toString();
            i++;
        }
        Log.v(str, str2 + "[" + (sArr.length * 2) + " bytes]:\t" + str3);
    }

    public static void encodeWavHeader(ByteBuffer byteBuffer, int i) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        Charset forName = Charset.forName("ASCII");
        byteBuffer.put("RIFF".getBytes(forName));
        byteBuffer.putInt(i + 36);
        byteBuffer.put("WAVE".getBytes(forName));
        byteBuffer.put("fmt ".getBytes(forName));
        byteBuffer.putInt(16);
        byteBuffer.putShort(1);
        byteBuffer.putShort(1);
        byteBuffer.putInt(getSampleRate());
        byteBuffer.putInt(getSampleRate() * 2);
        byteBuffer.putShort(2);
        byteBuffer.putShort(16);
        byteBuffer.put("data".getBytes(forName));
        byteBuffer.putInt(i);
    }
}
