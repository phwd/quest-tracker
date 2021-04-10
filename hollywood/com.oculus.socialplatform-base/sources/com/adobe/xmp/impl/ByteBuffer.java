package com.adobe.xmp.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ByteBuffer {
    public byte[] buffer;
    public String encoding;
    public int length;

    private void ensureCapacity(int i) {
        byte[] bArr = this.buffer;
        int length2 = bArr.length;
        if (i > length2) {
            byte[] bArr2 = new byte[(length2 << 1)];
            this.buffer = bArr2;
            System.arraycopy(bArr, 0, bArr2, 0, bArr.length);
        }
    }

    public byte byteAt(int i) {
        if (i < this.length) {
            return this.buffer[i];
        }
        throw new IndexOutOfBoundsException("The index exceeds the valid buffer area");
    }

    public int charAt(int i) {
        if (i < this.length) {
            return this.buffer[i] & Base64.INVALID;
        }
        throw new IndexOutOfBoundsException("The index exceeds the valid buffer area");
    }

    public InputStream getByteStream() {
        return new ByteArrayInputStream(this.buffer, 0, this.length);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0054, code lost:
        if (r7[2] == 0) goto L_0x0057;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getEncoding() {
        /*
            r11 = this;
            java.lang.String r10 = r11.encoding
            if (r10 != 0) goto L_0x002f
            int r9 = r11.length
            java.lang.String r10 = "UTF-8"
            r8 = 2
            if (r9 < r8) goto L_0x002d
            byte[] r7 = r11.buffer
            r0 = 0
            byte r6 = r7[r0]
            java.lang.String r5 = "UTF-32"
            r4 = 254(0xfe, float:3.56E-43)
            r3 = 1
            r2 = 4
            r1 = 255(0xff, float:3.57E-43)
            if (r6 != 0) goto L_0x0033
            if (r9 < r2) goto L_0x0030
            byte r0 = r7[r3]
            if (r0 != 0) goto L_0x0030
            byte r0 = r7[r8]
            r0 = r0 & r1
            if (r0 != r4) goto L_0x0057
            r0 = 3
            byte r0 = r7[r0]
            r0 = r0 & r1
            if (r0 != r1) goto L_0x0057
            java.lang.String r10 = "UTF-32BE"
        L_0x002d:
            r11.encoding = r10
        L_0x002f:
            return r10
        L_0x0030:
            java.lang.String r10 = "UTF-16BE"
            goto L_0x002d
        L_0x0033:
            r6 = r6 & r1
            r0 = 128(0x80, float:1.794E-43)
            if (r6 >= r0) goto L_0x0048
            byte r0 = r7[r3]
            if (r0 != 0) goto L_0x002d
            if (r9 < r2) goto L_0x0045
            byte r0 = r7[r8]
            if (r0 != 0) goto L_0x0045
            java.lang.String r10 = "UTF-32LE"
            goto L_0x002d
        L_0x0045:
            java.lang.String r10 = "UTF-16LE"
            goto L_0x002d
        L_0x0048:
            r0 = 239(0xef, float:3.35E-43)
            if (r6 == r0) goto L_0x002d
            java.lang.String r10 = "UTF-16"
            if (r6 == r4) goto L_0x002d
            if (r9 < r2) goto L_0x002d
            byte r0 = r7[r8]
            if (r0 == 0) goto L_0x0057
            goto L_0x002d
        L_0x0057:
            r11.encoding = r5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adobe.xmp.impl.ByteBuffer.getEncoding():java.lang.String");
    }

    public int length() {
        return this.length;
    }

    public ByteBuffer(int i) {
        this.encoding = null;
        this.buffer = new byte[i];
        this.length = 0;
    }

    public ByteBuffer(InputStream inputStream) throws IOException {
        this.encoding = null;
        this.length = 0;
        this.buffer = new byte[16384];
        while (true) {
            int read = inputStream.read(this.buffer, this.length, 16384);
            if (read > 0) {
                int i = this.length + read;
                this.length = i;
                if (read == 16384) {
                    ensureCapacity(i + 16384);
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public ByteBuffer(byte[] bArr) {
        this.encoding = null;
        this.buffer = bArr;
        this.length = bArr.length;
    }

    public ByteBuffer(byte[] bArr, int i) {
        this.encoding = null;
        if (i <= bArr.length) {
            this.buffer = bArr;
            this.length = i;
            return;
        }
        throw new ArrayIndexOutOfBoundsException("Valid length exceeds the buffer length.");
    }

    public ByteBuffer(byte[] bArr, int i, int i2) {
        this.encoding = null;
        if (i2 <= bArr.length - i) {
            byte[] bArr2 = new byte[i2];
            this.buffer = bArr2;
            System.arraycopy(bArr, i, bArr2, 0, i2);
            this.length = i2;
            return;
        }
        throw new ArrayIndexOutOfBoundsException("Valid length exceeds the buffer length.");
    }

    public void append(byte b) {
        ensureCapacity(this.length + 1);
        byte[] bArr = this.buffer;
        int i = this.length;
        this.length = i + 1;
        bArr[i] = b;
    }

    public void append(ByteBuffer byteBuffer) {
        append(byteBuffer.buffer, 0, byteBuffer.length);
    }

    public void append(byte[] bArr) {
        append(bArr, 0, bArr.length);
    }

    public void append(byte[] bArr, int i, int i2) {
        ensureCapacity(this.length + i2);
        System.arraycopy(bArr, i, this.buffer, this.length, i2);
        this.length += i2;
    }
}
