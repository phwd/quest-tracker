package sun.net.util;

public class IPAddressUtil {
    public static byte[] textToNumericFormatV4(String str) {
        byte[] bArr = new byte[4];
        int length = str.length();
        if (length != 0 && length <= 15) {
            long j = 0;
            boolean z = true;
            int i = 0;
            for (int i2 = 0; i2 < length; i2++) {
                char charAt = str.charAt(i2);
                if (charAt != '.') {
                    int digit = Character.digit(charAt, 10);
                    if (digit < 0) {
                        return null;
                    }
                    j = (j * 10) + ((long) digit);
                    z = false;
                } else if (z || j < 0 || j > 255 || i == 3) {
                    return null;
                } else {
                    bArr[i] = (byte) ((int) (j & 255));
                    i++;
                    j = 0;
                    z = true;
                }
            }
            if (!z && j >= 0 && j < (1 << ((4 - i) * 8)) && i != 0 && i != 1 && i != 2) {
                if (i == 3) {
                    bArr[3] = (byte) ((int) ((j >> 0) & 255));
                }
                return bArr;
            }
        }
        return null;
    }

    public static byte[] textToNumericFormatV6(String str) {
        int i;
        int i2;
        byte[] textToNumericFormatV4;
        if (str.length() < 2) {
            return null;
        }
        char[] charArray = str.toCharArray();
        byte[] bArr = new byte[16];
        int length = charArray.length;
        int indexOf = str.indexOf("%");
        if (indexOf == length - 1) {
            return null;
        }
        if (indexOf != -1) {
            length = indexOf;
        }
        if (charArray[0] != ':') {
            i = 0;
        } else if (charArray[1] != ':') {
            return null;
        } else {
            i = 1;
        }
        int i3 = 0;
        boolean z = false;
        int i4 = 0;
        int i5 = -1;
        int i6 = i;
        while (true) {
            if (i >= length) {
                break;
            }
            int i7 = i + 1;
            char c = charArray[i];
            int digit = Character.digit(c, 16);
            if (digit != -1) {
                i3 = (i3 << 4) | digit;
                if (i3 > 65535) {
                    return null;
                }
                i = i7;
                z = true;
            } else if (c == ':') {
                if (!z) {
                    if (i5 != -1) {
                        return null;
                    }
                    i = i7;
                    i6 = i;
                    i5 = i4;
                } else if (i7 == length || i4 + 2 > 16) {
                    return null;
                } else {
                    int i8 = i4 + 1;
                    bArr[i4] = (byte) ((i3 >> 8) & 255);
                    i4 = i8 + 1;
                    bArr[i8] = (byte) (i3 & 255);
                    i = i7;
                    i6 = i;
                    i3 = 0;
                    z = false;
                }
            } else if (c != '.' || i4 + 4 > 16) {
                return null;
            } else {
                String substring = str.substring(i6, length);
                int i9 = 0;
                int i10 = 0;
                while (true) {
                    int indexOf2 = substring.indexOf(46, i9);
                    if (indexOf2 == -1) {
                        break;
                    }
                    i10++;
                    i9 = indexOf2 + 1;
                }
                if (i10 != 3 || (textToNumericFormatV4 = textToNumericFormatV4(substring)) == null) {
                    return null;
                }
                int i11 = 0;
                while (i11 < 4) {
                    bArr[i4] = textToNumericFormatV4[i11];
                    i11++;
                    i4++;
                }
                z = false;
            }
        }
        if (!z) {
            i2 = i4;
        } else if (i4 + 2 > 16) {
            return null;
        } else {
            int i12 = i4 + 1;
            bArr[i4] = (byte) ((i3 >> 8) & 255);
            i2 = i12 + 1;
            bArr[i12] = (byte) (i3 & 255);
        }
        if (i5 != -1) {
            int i13 = i2 - i5;
            if (i2 == 16) {
                return null;
            }
            for (int i14 = 1; i14 <= i13; i14++) {
                int i15 = (i5 + i13) - i14;
                bArr[16 - i14] = bArr[i15];
                bArr[i15] = 0;
            }
            i2 = 16;
        }
        if (i2 != 16) {
            return null;
        }
        byte[] convertFromIPv4MappedAddress = convertFromIPv4MappedAddress(bArr);
        return convertFromIPv4MappedAddress != null ? convertFromIPv4MappedAddress : bArr;
    }

    public static boolean isIPv6LiteralAddress(String str) {
        return textToNumericFormatV6(str) != null;
    }

    public static byte[] convertFromIPv4MappedAddress(byte[] bArr) {
        if (!isIPv4MappedAddress(bArr)) {
            return null;
        }
        byte[] bArr2 = new byte[4];
        System.arraycopy(bArr, 12, bArr2, 0, 4);
        return bArr2;
    }

    private static boolean isIPv4MappedAddress(byte[] bArr) {
        if (bArr.length >= 16 && bArr[0] == 0 && bArr[1] == 0 && bArr[2] == 0 && bArr[3] == 0 && bArr[4] == 0 && bArr[5] == 0 && bArr[6] == 0 && bArr[7] == 0 && bArr[8] == 0 && bArr[9] == 0 && bArr[10] == -1 && bArr[11] == -1) {
            return true;
        }
        return false;
    }
}
