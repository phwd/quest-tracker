package com.adobe.xmp.impl;

import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;

public class FixASCIIControlsReader extends PushbackReader {
    public static final int BUFFER_SIZE = 8;
    public static final int STATE_AMP = 1;
    public static final int STATE_DIG1 = 4;
    public static final int STATE_ERROR = 5;
    public static final int STATE_HASH = 2;
    public static final int STATE_HEX = 3;
    public static final int STATE_START = 0;
    public int control = 0;
    public int digits = 0;
    public int state = 0;

    public FixASCIIControlsReader(Reader reader) {
        super(reader, 8);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003a, code lost:
        if (r0 <= 5) goto L_0x008b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0063, code lost:
        if (r0 <= 4) goto L_0x007c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private char processChar(char r11) {
        /*
        // Method dump skipped, instructions count: 152
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.FixASCIIControlsReader.processChar(char):char");
    }

    @Override // java.io.PushbackReader, java.io.FilterReader, java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        char[] cArr2 = new char[8];
        boolean z = true;
        int i3 = 0;
        loop0:
        while (true) {
            int i4 = 0;
            while (true) {
                if (i3 >= i2) {
                    break loop0;
                }
                z = false;
                if (super.read(cArr2, i4, 1) == 1) {
                    z = true;
                }
                if (!z) {
                    if (i4 <= 0) {
                        break;
                    }
                    unread(cArr2, 0, i4);
                    this.state = 5;
                    z = true;
                } else {
                    char processChar = processChar(cArr2[i4]);
                    int i5 = this.state;
                    if (i5 != 0) {
                        i4++;
                        if (i5 == 5) {
                            unread(cArr2, 0, i4);
                            break;
                        }
                    } else {
                        if (Utils.isControlChar(processChar)) {
                            processChar = ' ';
                        }
                        cArr[i] = processChar;
                        i3++;
                        i++;
                    }
                }
            }
        }
        if (i3 > 0 || z) {
            return i3;
        }
        return -1;
    }
}
