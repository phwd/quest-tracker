package sun.misc;

import android.icu.impl.UCharacterProperty;

public class BASE64Decoder extends CharacterDecoder {
    private static final char[] pem_array = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', UCharacterProperty.LATIN_SMALL_LETTER_I_, 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
    private static final byte[] pem_convert_array = new byte[256];
    byte[] decode_buffer = new byte[4];

    /* access modifiers changed from: protected */
    @Override // sun.misc.CharacterDecoder
    public int bytesPerAtom() {
        return 4;
    }

    /* access modifiers changed from: protected */
    @Override // sun.misc.CharacterDecoder
    public int bytesPerLine() {
        return 72;
    }

    static {
        for (int i = 0; i < 255; i++) {
            pem_convert_array[i] = -1;
        }
        int i2 = 0;
        while (true) {
            char[] cArr = pem_array;
            if (i2 < cArr.length) {
                pem_convert_array[cArr[i2]] = (byte) i2;
                i2++;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00ac  */
    @Override // sun.misc.CharacterDecoder
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void decodeAtom(java.io.PushbackInputStream r13, java.io.OutputStream r14, int r15) throws java.io.IOException {
        /*
        // Method dump skipped, instructions count: 206
        */
        throw new UnsupportedOperationException("Method not decompiled: sun.misc.BASE64Decoder.decodeAtom(java.io.PushbackInputStream, java.io.OutputStream, int):void");
    }
}
