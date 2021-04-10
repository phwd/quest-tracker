package com.adobe.xmp.impl;

import java.io.UnsupportedEncodingException;

public class Latin1Converter {
    public static final int STATE_START = 0;
    public static final int STATE_UTF8CHAR = 11;

    public static byte[] convertToUTF8(byte b) {
        int i = b & Base64.INVALID;
        if (i >= 128) {
            if (i == 129 || i == 141 || i == 143 || i == 144 || i == 157) {
                return new byte[]{32};
            }
            try {
                return new String(new byte[]{b}, "cp1252").getBytes("UTF-8");
            } catch (UnsupportedEncodingException unused) {
            }
        }
        return new byte[]{b};
    }

    public static ByteBuffer convert(ByteBuffer byteBuffer) {
        if (!"UTF-8".equals(byteBuffer.getEncoding())) {
            return byteBuffer;
        }
        byte[] bArr = new byte[8];
        ByteBuffer byteBuffer2 = new ByteBuffer((byteBuffer.length << 2) / 3);
        int i = 0;
        char c = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < byteBuffer.length) {
            int charAt = byteBuffer.charAt(i);
            if (c == 11) {
                if (i2 <= 0 || (charAt & 192) != 128) {
                    byteBuffer2.append(convertToUTF8(bArr[0]));
                    i -= i3;
                } else {
                    int i4 = i3 + 1;
                    bArr[i3] = (byte) charAt;
                    i2--;
                    if (i2 == 0) {
                        byteBuffer2.append(bArr, 0, i4);
                    } else {
                        i3 = i4;
                    }
                }
                c = 0;
                i3 = 0;
            } else if (charAt < 127) {
                byteBuffer2.append((byte) charAt);
            } else if (charAt >= 192) {
                i2 = -1;
                int i5 = charAt;
                while ((i5 & 128) == 128) {
                    i2++;
                    i5 <<= 1;
                    if (i2 >= 8) {
                        break;
                    }
                }
                bArr[i3] = (byte) charAt;
                i3++;
                c = 11;
            } else {
                byteBuffer2.append(convertToUTF8((byte) charAt));
            }
            i++;
        }
        if (c == 11) {
            for (int i6 = 0; i6 < i3; i6++) {
                byteBuffer2.append(convertToUTF8(bArr[i6]));
            }
        }
        return byteBuffer2;
    }
}
