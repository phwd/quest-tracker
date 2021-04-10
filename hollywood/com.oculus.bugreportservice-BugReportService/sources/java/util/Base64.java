package java.util;

import java.nio.charset.StandardCharsets;

public class Base64 {
    public static Decoder getMimeDecoder() {
        return Decoder.RFC2045;
    }

    public static class Encoder {
        private static final byte[] CRLF = {13, 10};
        static final Encoder RFC2045 = new Encoder(false, CRLF, 76, true);
        static final Encoder RFC4648 = new Encoder(false, null, -1, true);
        static final Encoder RFC4648_URLSAFE = new Encoder(true, null, -1, true);
        private static final char[] toBase64 = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
        private static final char[] toBase64URL = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'};
        private final boolean doPadding;
        private final boolean isURL;
        private final int linemax;
        private final byte[] newline;

        private Encoder(boolean z, byte[] bArr, int i, boolean z2) {
            this.isURL = z;
            this.newline = bArr;
            this.linemax = i;
            this.doPadding = z2;
        }
    }

    public static class Decoder {
        static final Decoder RFC2045 = new Decoder(false, true);
        static final Decoder RFC4648 = new Decoder(false, false);
        static final Decoder RFC4648_URLSAFE = new Decoder(true, false);
        private static final int[] fromBase64 = new int[256];
        private static final int[] fromBase64URL = new int[256];
        private final boolean isMIME;
        private final boolean isURL;

        private Decoder(boolean z, boolean z2) {
            this.isURL = z;
            this.isMIME = z2;
        }

        static {
            Arrays.fill(fromBase64, -1);
            for (int i = 0; i < Encoder.toBase64.length; i++) {
                fromBase64[Encoder.toBase64[i]] = i;
            }
            fromBase64[61] = -2;
            Arrays.fill(fromBase64URL, -1);
            for (int i2 = 0; i2 < Encoder.toBase64URL.length; i2++) {
                fromBase64URL[Encoder.toBase64URL[i2]] = i2;
            }
            fromBase64URL[61] = -2;
        }

        public byte[] decode(byte[] bArr) {
            byte[] bArr2 = new byte[outLength(bArr, 0, bArr.length)];
            int decode0 = decode0(bArr, 0, bArr.length, bArr2);
            return decode0 != bArr2.length ? Arrays.copyOf(bArr2, decode0) : bArr2;
        }

        public byte[] decode(String str) {
            return decode(str.getBytes(StandardCharsets.ISO_8859_1));
        }

        private int outLength(byte[] bArr, int i, int i2) {
            int i3;
            int[] iArr = this.isURL ? fromBase64URL : fromBase64;
            int i4 = i2 - i;
            int i5 = 0;
            if (i4 == 0) {
                return 0;
            }
            if (i4 >= 2) {
                if (this.isMIME) {
                    int i6 = 0;
                    while (true) {
                        if (i >= i2) {
                            break;
                        }
                        int i7 = i + 1;
                        int i8 = bArr[i] & 255;
                        if (i8 == 61) {
                            i4 -= (i2 - i7) + 1;
                            break;
                        }
                        if (iArr[i8] == -1) {
                            i6++;
                        }
                        i = i7;
                    }
                    i4 -= i6;
                } else if (bArr[i2 - 1] == 61) {
                    i5 = bArr[i2 - 2] == 61 ? 2 : 1;
                }
                if (i5 == 0 && (i3 = i4 & 3) != 0) {
                    i5 = 4 - i3;
                }
                return (((i4 + 3) / 4) * 3) - i5;
            } else if (this.isMIME && iArr[0] == -1) {
                return 0;
            } else {
                throw new IllegalArgumentException("Input byte[] should at least have 2 bytes for base64 bytes");
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:14:0x002b, code lost:
            if (r11[r8] == 61) goto L_0x002f;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:16:0x002f, code lost:
            if (r4 != 18) goto L_0x007e;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private int decode0(byte[] r11, int r12, int r13, byte[] r14) {
            /*
            // Method dump skipped, instructions count: 207
            */
            throw new UnsupportedOperationException("Method not decompiled: java.util.Base64.Decoder.decode0(byte[], int, int, byte[]):int");
        }
    }
}
