package X;

import com.facebook.assistant.oacr.OacrConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/* renamed from: X.Fj  reason: case insensitive filesystem */
public abstract class AbstractC0163Fj {
    public static final byte[] A00 = {60, -15};

    public abstract ByteBuffer getJavaByteBuffer();

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
                        byte b2 = bArr[0];
                        str2 = OacrConstants.AUTO_SPEECH_DOMAIN;
                        if (b == b2 && map.get() == bArr[1]) {
                            byte b3 = map.get();
                            if (b3 <= 0 || b3 > 1) {
                                str2 = String.format("Bad file ver:%d, current:%d", Byte.valueOf(b3), (byte) 1);
                            } else {
                                byte b4 = map.get();
                                if (b4 <= 0 || b4 >= (length = length2 - bArr.length)) {
                                    str2 = String.format("Bad bodyOffset:%d", Byte.valueOf(b4));
                                } else {
                                    int i = map.getInt();
                                    if (i != length2) {
                                        str2 = String.format("Bad fileSize:%d, actual fileSize:%d", Integer.valueOf(i), Integer.valueOf(length2));
                                    } else {
                                        map.position(length);
                                        byte b5 = map.get();
                                        byte b6 = map.get();
                                        if (b5 == bArr[0] && b6 == bArr[1]) {
                                            map.position(b4);
                                        } else {
                                            str2 = String.format("Bad footer magicHex:%02X %02X", Byte.valueOf(b5), Byte.valueOf(b6));
                                        }
                                    }
                                }
                            }
                        } else {
                            map.position(0);
                        }
                    }
                    if (!str2.isEmpty()) {
                        C0139Dd.A0P("MobileConfigMmapHandle", "Cannot validate \"%s\", err:%s", str, str2);
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
            C0139Dd.A0W("MobileConfigMmapHandle", e, "Cannot open \"%s\"", str);
            return null;
        }
    }
}
