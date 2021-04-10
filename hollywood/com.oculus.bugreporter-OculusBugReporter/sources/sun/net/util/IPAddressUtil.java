package sun.net.util;

public class IPAddressUtil {
    private static final int INADDR16SZ = 16;
    private static final int INADDR4SZ = 4;
    private static final int INT16SZ = 2;

    public static byte[] textToNumericFormatV4(String src) {
        byte[] res = new byte[4];
        long tmpValue = 0;
        int currByte = 0;
        boolean newOctet = true;
        int len = src.length();
        if (len != 0) {
            if (len <= 15) {
                for (int i = 0; i < len; i++) {
                    char c = src.charAt(i);
                    if (c != '.') {
                        int digit = Character.digit(c, 10);
                        if (digit < 0) {
                            return null;
                        }
                        tmpValue = (tmpValue * 10) + ((long) digit);
                        newOctet = false;
                    } else if (newOctet || tmpValue < 0 || tmpValue > 255 || currByte == 3) {
                        return null;
                    } else {
                        res[currByte] = (byte) ((int) (tmpValue & 255));
                        tmpValue = 0;
                        newOctet = true;
                        currByte++;
                    }
                }
                if (newOctet || tmpValue < 0 || tmpValue >= (1 << ((4 - currByte) * 8)) || currByte == 0 || currByte == 1 || currByte == 2) {
                    return null;
                }
                if (currByte == 3) {
                    res[3] = (byte) ((int) ((tmpValue >> 0) & 255));
                }
                return res;
            }
        }
        return null;
    }

    /* JADX INFO: Multiple debug info for r9v3 char: [D('ch' char), D('i' int)] */
    public static byte[] textToNumericFormatV6(String src) {
        byte[] bArr;
        byte[] v4addr;
        if (src.length() < 2) {
            return null;
        }
        char[] srcb = src.toCharArray();
        int i = 16;
        byte[] dst = new byte[16];
        int srcb_length = srcb.length;
        int pc = src.indexOf("%");
        if (pc == srcb_length - 1) {
            return null;
        }
        int i2 = -1;
        if (pc != -1) {
            srcb_length = pc;
        }
        int colonp = -1;
        int i3 = 0;
        int j = 0;
        if (srcb[0] == ':') {
            i3 = 0 + 1;
            if (srcb[i3] != ':') {
                return null;
            }
        }
        int curtok = i3;
        boolean saw_xdigit = false;
        int val = 0;
        while (true) {
            if (i3 >= srcb_length) {
                bArr = null;
                break;
            }
            int i4 = i3 + 1;
            char ch = srcb[i3];
            int chval = Character.digit(ch, i);
            if (chval != i2) {
                val = (val << 4) | chval;
                if (val > 65535) {
                    return null;
                }
                saw_xdigit = true;
                i3 = i4;
                i = 16;
            } else if (ch == ':') {
                curtok = i4;
                if (!saw_xdigit) {
                    if (colonp != i2) {
                        return null;
                    }
                    colonp = j;
                    i3 = i4;
                    i = 16;
                } else if (i4 == srcb_length || j + 2 > 16) {
                    return null;
                } else {
                    int j2 = j + 1;
                    dst[j] = (byte) ((val >> 8) & 255);
                    j = j2 + 1;
                    dst[j2] = (byte) (val & 255);
                    saw_xdigit = false;
                    val = 0;
                    i3 = i4;
                    i = 16;
                    i2 = -1;
                }
            } else {
                int i5 = 46;
                if (ch != '.' || j + 4 > 16) {
                    return null;
                }
                String ia4 = src.substring(curtok, srcb_length);
                int dot_count = 0;
                int index = 0;
                while (true) {
                    int index2 = ia4.indexOf(i5, index);
                    if (index2 == -1) {
                        break;
                    }
                    dot_count++;
                    index = index2 + 1;
                    srcb = srcb;
                    i5 = 46;
                }
                if (dot_count != 3 || (v4addr = textToNumericFormatV4(ia4)) == null) {
                    return null;
                }
                int k = 0;
                while (k < 4) {
                    dst[j] = v4addr[k];
                    k++;
                    j++;
                    dot_count = dot_count;
                }
                saw_xdigit = false;
                bArr = null;
            }
        }
        if (saw_xdigit) {
            if (j + 2 > 16) {
                return bArr;
            }
            int j3 = j + 1;
            dst[j] = (byte) ((val >> 8) & 255);
            j = j3 + 1;
            dst[j3] = (byte) (val & 255);
        }
        if (colonp != -1) {
            int n = j - colonp;
            if (j == 16) {
                return null;
            }
            for (int i6 = 1; i6 <= n; i6++) {
                dst[16 - i6] = dst[(colonp + n) - i6];
                dst[(colonp + n) - i6] = 0;
            }
            j = 16;
        }
        if (j != 16) {
            return null;
        }
        byte[] newdst = convertFromIPv4MappedAddress(dst);
        if (newdst != null) {
            return newdst;
        }
        return dst;
    }

    public static boolean isIPv4LiteralAddress(String src) {
        return textToNumericFormatV4(src) != null;
    }

    public static boolean isIPv6LiteralAddress(String src) {
        return textToNumericFormatV6(src) != null;
    }

    public static byte[] convertFromIPv4MappedAddress(byte[] addr) {
        if (!isIPv4MappedAddress(addr)) {
            return null;
        }
        byte[] newAddr = new byte[4];
        System.arraycopy(addr, 12, newAddr, 0, 4);
        return newAddr;
    }

    private static boolean isIPv4MappedAddress(byte[] addr) {
        if (addr.length >= 16 && addr[0] == 0 && addr[1] == 0 && addr[2] == 0 && addr[3] == 0 && addr[4] == 0 && addr[5] == 0 && addr[6] == 0 && addr[7] == 0 && addr[8] == 0 && addr[9] == 0 && addr[10] == -1 && addr[11] == -1) {
            return true;
        }
        return false;
    }
}
