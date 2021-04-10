package com.facebook.mobileconfig;

import com.facebook.debug.log.BLog;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import javax.annotation.Nullable;

public abstract class MobileConfigMmapHandle {
    private static final String TAG = "MobileConfigMmapHandle";
    public static final byte kCurrentFileFormatVersion = 1;
    public static final byte[] kMagicCode = {60, -15};
    public static final int kMinFileHeaderSize = 8;

    public abstract String getFilename();

    public abstract ByteBuffer getJavaByteBuffer();

    /* access modifiers changed from: protected */
    @Nullable
    public ByteBuffer mmapFile(String str) {
        File file = new File(str);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                FileChannel channel = fileInputStream.getChannel();
                try {
                    MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
                    map.order(ByteOrder.LITTLE_ENDIAN);
                    String validateFile = validateFile(map, (int) file.length());
                    if (!validateFile.isEmpty()) {
                        BLog.w(TAG, "Cannot validate \"%s\", err:%s", str, validateFile);
                        channel.close();
                        fileInputStream.close();
                        return null;
                    }
                    channel.close();
                    fileInputStream.close();
                    return map;
                } catch (Throwable unused) {
                }
                throw th;
                throw th;
            } catch (Throwable unused2) {
            }
        } catch (IOException e) {
            BLog.w(TAG, e, "Cannot open \"%s\"", str);
            return null;
        }
    }

    private String validateFile(MappedByteBuffer mappedByteBuffer, int i) {
        if (i < 8) {
            return "file size too small";
        }
        if (mappedByteBuffer.get() == kMagicCode[0] && mappedByteBuffer.get() == kMagicCode[1]) {
            byte b = mappedByteBuffer.get();
            if (b <= 0 || b > 1) {
                return String.format("Bad file ver:%d, current:%d", Byte.valueOf(b), (byte) 1);
            }
            byte b2 = mappedByteBuffer.get();
            if (b2 <= 0 || b2 >= i - kMagicCode.length) {
                return String.format("Bad bodyOffset:%d", Byte.valueOf(b2));
            }
            int i2 = mappedByteBuffer.getInt();
            if (i2 != i) {
                return String.format("Bad fileSize:%d, actual fileSize:%d", Integer.valueOf(i2), Integer.valueOf(i));
            }
            mappedByteBuffer.position(i - kMagicCode.length);
            byte b3 = mappedByteBuffer.get();
            byte b4 = mappedByteBuffer.get();
            byte[] bArr = kMagicCode;
            if (b3 == bArr[0] && b4 == bArr[1]) {
                mappedByteBuffer.position(b2);
                return "";
            }
            return String.format("Bad footer magicHex:%02X %02X", Byte.valueOf(b3), Byte.valueOf(b4));
        }
        mappedByteBuffer.position(0);
        return "";
    }
}
