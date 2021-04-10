package X;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import javax.annotation.Nullable;

/* renamed from: X.0RZ  reason: invalid class name */
public abstract class AnonymousClass0RZ {
    public static final byte[] A00 = {60, -15};

    public abstract ByteBuffer getJavaByteBuffer();

    @Nullable
    public final ByteBuffer A00(String str) {
        String str2;
        int length;
        File file = new File(str);
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            try {
                FileChannel channel = fileInputStream.getChannel();
                try {
                    MappedByteBuffer map = channel.map(FileChannel.MapMode.READ_ONLY, 0, channel.size());
                    map.order(ByteOrder.LITTLE_ENDIAN);
                    int length2 = (int) file.length();
                    if (length2 < 8) {
                        str2 = "file size too small";
                    } else {
                        byte b = map.get();
                        byte[] bArr = A00;
                        str2 = "";
                        if (b == bArr[0] && map.get() == bArr[1]) {
                            byte b2 = map.get();
                            if (b2 <= 0 || b2 > 1) {
                                str2 = String.format("Bad file ver:%d, current:%d", Byte.valueOf(b2), (byte) 1);
                            } else {
                                byte b3 = map.get();
                                if (b3 <= 0 || b3 >= (length = length2 - bArr.length)) {
                                    str2 = String.format("Bad bodyOffset:%d", Byte.valueOf(b3));
                                } else {
                                    int i = map.getInt();
                                    if (i != length2) {
                                        str2 = String.format("Bad fileSize:%d, actual fileSize:%d", Integer.valueOf(i), Integer.valueOf(length2));
                                    } else {
                                        map.position(length);
                                        byte b4 = map.get();
                                        byte b5 = map.get();
                                        if (b4 == bArr[0] && b5 == bArr[1]) {
                                            map.position(b3);
                                        } else {
                                            str2 = String.format("Bad footer magicHex:%02X %02X", Byte.valueOf(b4), Byte.valueOf(b5));
                                        }
                                    }
                                }
                            }
                        } else {
                            map.position(0);
                        }
                    }
                    if (!str2.isEmpty()) {
                        AnonymousClass0NO.A0F("MobileConfigMmapHandle", "Cannot validate \"%s\", err:%s", str, str2);
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
            AnonymousClass0NO.A0L("MobileConfigMmapHandle", e, "Cannot open \"%s\"", str);
            return null;
        }
    }
}
