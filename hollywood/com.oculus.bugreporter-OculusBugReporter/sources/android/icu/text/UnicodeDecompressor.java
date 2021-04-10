package android.icu.text;

public final class UnicodeDecompressor implements SCSU {
    private static final int BUFSIZE = 3;
    private byte[] fBuffer = new byte[3];
    private int fBufferLength = 0;
    private int fCurrentWindow = 0;
    private int fMode = 0;
    private int[] fOffsets = new int[8];

    public UnicodeDecompressor() {
        reset();
    }

    public static String decompress(byte[] buffer) {
        return new String(decompress(buffer, 0, buffer.length));
    }

    public static char[] decompress(byte[] buffer, int start, int limit) {
        UnicodeDecompressor comp = new UnicodeDecompressor();
        int len = Math.max(2, (limit - start) * 2);
        char[] temp = new char[len];
        int charCount = comp.decompress(buffer, start, limit, null, temp, 0, len);
        char[] result = new char[charCount];
        System.arraycopy((Object) temp, 0, (Object) result, 0, charCount);
        return result;
    }

    /* JADX INFO: Multiple debug info for r5v0 int: [D('ucPos' int), D('bytePos' int)] */
    /* JADX INFO: Multiple debug info for r0v15 byte: [D('aByte' int), D('bytePos' int)] */
    /* JADX INFO: Multiple debug info for r4v6 int: [D('aByte' int), D('ucPos' int)] */
    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    public int decompress(byte[] byteBuffer, int byteBufferStart, int byteBufferLimit, int[] bytesRead, char[] charBuffer, int charBufferStart, int charBufferLimit) {
        int i;
        int i2;
        int newBytes;
        int newBytes2;
        int bytePos = byteBufferStart;
        int ucPos = charBufferStart;
        byte b = 0;
        if (charBuffer.length < 2 || charBufferLimit - charBufferStart < 2) {
            throw new IllegalArgumentException("charBuffer.length < 2");
        }
        int i3 = this.fBufferLength;
        if (i3 > 0) {
            if (i3 != 3) {
                int newBytes3 = this.fBuffer.length - i3;
                if (byteBufferLimit - byteBufferStart < newBytes3) {
                    newBytes2 = byteBufferLimit - byteBufferStart;
                } else {
                    newBytes2 = newBytes3;
                }
                System.arraycopy(byteBuffer, byteBufferStart, this.fBuffer, this.fBufferLength, newBytes2);
                newBytes = newBytes2;
            } else {
                newBytes = 0;
            }
            this.fBufferLength = 0;
            byte[] bArr = this.fBuffer;
            i = 0;
            ucPos += decompress(bArr, 0, bArr.length, null, charBuffer, charBufferStart, charBufferLimit);
            bytePos += newBytes;
        } else {
            i = 0;
        }
        while (true) {
            if (bytePos < byteBufferLimit && ucPos < charBufferLimit) {
                int i4 = this.fMode;
                if (i4 == 0) {
                    while (true) {
                        if (bytePos < byteBufferLimit && ucPos < charBufferLimit) {
                            int bytePos2 = bytePos + 1;
                            int aByte = byteBuffer[bytePos] & 255;
                            switch (aByte) {
                                case 0:
                                case 9:
                                case 10:
                                case 13:
                                case 32:
                                case 33:
                                case 34:
                                case 35:
                                case 36:
                                case 37:
                                case 38:
                                case 39:
                                case 40:
                                case 41:
                                case 42:
                                case 43:
                                case 44:
                                case 45:
                                case 46:
                                case 47:
                                case 48:
                                case 49:
                                case 50:
                                case 51:
                                case 52:
                                case 53:
                                case 54:
                                case 55:
                                case 56:
                                case 57:
                                case 58:
                                case 59:
                                case 60:
                                case 61:
                                case 62:
                                case 63:
                                case 64:
                                case 65:
                                case 66:
                                case 67:
                                case 68:
                                case 69:
                                case 70:
                                case 71:
                                case 72:
                                case 73:
                                case 74:
                                case 75:
                                case 76:
                                case 77:
                                case 78:
                                case 79:
                                case 80:
                                case 81:
                                case 82:
                                case 83:
                                case 84:
                                case 85:
                                case 86:
                                case 87:
                                case 88:
                                case 89:
                                case 90:
                                case 91:
                                case 92:
                                case 93:
                                case 94:
                                case 95:
                                case 96:
                                case 97:
                                case 98:
                                case 99:
                                case 100:
                                case 101:
                                case 102:
                                case 103:
                                case 104:
                                case 105:
                                case 106:
                                case 107:
                                case 108:
                                case 109:
                                case 110:
                                case 111:
                                case 112:
                                case 113:
                                case 114:
                                case 115:
                                case 116:
                                case 117:
                                case 118:
                                case 119:
                                case 120:
                                case 121:
                                case 122:
                                case 123:
                                case 124:
                                case 125:
                                case 126:
                                case 127:
                                    charBuffer[ucPos] = (char) aByte;
                                    bytePos = bytePos2;
                                    b = aByte;
                                    ucPos++;
                                case 1:
                                case 2:
                                case 3:
                                case 4:
                                case 5:
                                case 6:
                                case 7:
                                case 8:
                                    if (bytePos2 >= byteBufferLimit) {
                                        int bytePos3 = bytePos2 - 1;
                                        System.arraycopy(byteBuffer, bytePos3, this.fBuffer, i, byteBufferLimit - bytePos3);
                                        this.fBufferLength = byteBufferLimit - bytePos3;
                                        bytePos = bytePos3 + this.fBufferLength;
                                        break;
                                    } else {
                                        int bytePos4 = bytePos2 + 1;
                                        int dByte = byteBuffer[bytePos2] & 255;
                                        int ucPos2 = ucPos + 1;
                                        if (dByte < 0 || dByte >= 128) {
                                            i2 = this.fOffsets[aByte - 1] - 128;
                                        } else {
                                            i2 = sOffsets[aByte - 1];
                                        }
                                        charBuffer[ucPos] = (char) (i2 + dByte);
                                        b = aByte;
                                        bytePos = bytePos4;
                                        ucPos = ucPos2;
                                    }
                                case 11:
                                    if (bytePos2 + 1 >= byteBufferLimit) {
                                        int bytePos5 = bytePos2 - 1;
                                        System.arraycopy(byteBuffer, bytePos5, this.fBuffer, i, byteBufferLimit - bytePos5);
                                        this.fBufferLength = byteBufferLimit - bytePos5;
                                        bytePos = bytePos5 + this.fBufferLength;
                                        break;
                                    } else {
                                        int bytePos6 = bytePos2 + 1;
                                        int aByte2 = byteBuffer[bytePos2] & 255;
                                        this.fCurrentWindow = (aByte2 & 224) >> 5;
                                        bytePos = bytePos6 + 1;
                                        this.fOffsets[this.fCurrentWindow] = (((byteBuffer[bytePos6] & 255) | ((aByte2 & 31) << 8)) * 128) + 65536;
                                        b = aByte2;
                                    }
                                case 12:
                                default:
                                    bytePos = bytePos2;
                                    b = aByte;
                                case 14:
                                    if (bytePos2 + 1 >= byteBufferLimit) {
                                        int bytePos7 = bytePos2 - 1;
                                        System.arraycopy(byteBuffer, bytePos7, this.fBuffer, i, byteBufferLimit - bytePos7);
                                        this.fBufferLength = byteBufferLimit - bytePos7;
                                        bytePos = bytePos7 + this.fBufferLength;
                                        break;
                                    } else {
                                        int bytePos8 = bytePos2 + 1;
                                        byte b2 = byteBuffer[bytePos2];
                                        charBuffer[ucPos] = (char) ((byteBuffer[bytePos8] & 255) | (b2 << 8));
                                        b = b2;
                                        ucPos++;
                                        bytePos = bytePos8 + 1;
                                    }
                                case 15:
                                    this.fMode = 1;
                                    bytePos = bytePos2;
                                    b = aByte;
                                    break;
                                case 16:
                                case 17:
                                case 18:
                                case 19:
                                case 20:
                                case 21:
                                case 22:
                                case 23:
                                    this.fCurrentWindow = aByte - 16;
                                    bytePos = bytePos2;
                                    b = aByte;
                                case 24:
                                case 25:
                                case 26:
                                case 27:
                                case 28:
                                case 29:
                                case 30:
                                case 31:
                                    if (bytePos2 >= byteBufferLimit) {
                                        int bytePos9 = bytePos2 - 1;
                                        System.arraycopy(byteBuffer, bytePos9, this.fBuffer, i, byteBufferLimit - bytePos9);
                                        this.fBufferLength = byteBufferLimit - bytePos9;
                                        bytePos = bytePos9 + this.fBufferLength;
                                        break;
                                    } else {
                                        this.fCurrentWindow = aByte - 24;
                                        bytePos = bytePos2 + 1;
                                        this.fOffsets[this.fCurrentWindow] = sOffsetTable[byteBuffer[bytePos2] & 255];
                                        b = aByte;
                                    }
                                case 128:
                                case 129:
                                case 130:
                                case 131:
                                case 132:
                                case 133:
                                case 134:
                                case 135:
                                case 136:
                                case 137:
                                case 138:
                                case 139:
                                case 140:
                                case 141:
                                case 142:
                                case 143:
                                case 144:
                                case 145:
                                case 146:
                                case 147:
                                case 148:
                                case 149:
                                case 150:
                                case 151:
                                case 152:
                                case 153:
                                case 154:
                                case 155:
                                case 156:
                                case 157:
                                case 158:
                                case 159:
                                case 160:
                                case 161:
                                case 162:
                                case 163:
                                case 164:
                                case 165:
                                case 166:
                                case 167:
                                case 168:
                                case 169:
                                case 170:
                                case 171:
                                case 172:
                                case 173:
                                case 174:
                                case 175:
                                case 176:
                                case 177:
                                case 178:
                                case 179:
                                case 180:
                                case 181:
                                case 182:
                                case 183:
                                case 184:
                                case 185:
                                case 186:
                                case 187:
                                case 188:
                                case 189:
                                case 190:
                                case 191:
                                case 192:
                                case 193:
                                case 194:
                                case 195:
                                case 196:
                                case 197:
                                case 198:
                                case 199:
                                case 200:
                                case 201:
                                case 202:
                                case 203:
                                case 204:
                                case 205:
                                case 206:
                                case 207:
                                case 208:
                                case 209:
                                case 210:
                                case 211:
                                case 212:
                                case 213:
                                case 214:
                                case 215:
                                case 216:
                                case 217:
                                case 218:
                                case 219:
                                case 220:
                                case 221:
                                case 222:
                                case 223:
                                case 224:
                                case 225:
                                case 226:
                                case 227:
                                case 228:
                                case 229:
                                case 230:
                                case 231:
                                case 232:
                                case 233:
                                case 234:
                                case 235:
                                case 236:
                                case 237:
                                case 238:
                                case 239:
                                case 240:
                                case 241:
                                case 242:
                                case 243:
                                case 244:
                                case 245:
                                case 246:
                                case 247:
                                case 248:
                                case 249:
                                case 250:
                                case 251:
                                case 252:
                                case 253:
                                case 254:
                                case 255:
                                    int[] iArr = this.fOffsets;
                                    int i5 = this.fCurrentWindow;
                                    if (iArr[i5] <= 65535) {
                                        charBuffer[ucPos] = (char) ((iArr[i5] + aByte) - 128);
                                        bytePos = bytePos2;
                                        b = aByte;
                                        ucPos++;
                                    } else if (ucPos + 1 >= charBufferLimit) {
                                        int bytePos10 = bytePos2 - 1;
                                        System.arraycopy(byteBuffer, bytePos10, this.fBuffer, i, byteBufferLimit - bytePos10);
                                        this.fBufferLength = byteBufferLimit - bytePos10;
                                        bytePos = bytePos10 + this.fBufferLength;
                                        break;
                                    } else {
                                        int normalizedBase = iArr[i5] - 65536;
                                        int ucPos3 = ucPos + 1;
                                        charBuffer[ucPos] = (char) ((normalizedBase >> 10) + 55296);
                                        charBuffer[ucPos3] = (char) ((normalizedBase & 1023) + UTF16.TRAIL_SURROGATE_MIN_VALUE + (aByte & 127));
                                        bytePos = bytePos2;
                                        b = aByte;
                                        ucPos = ucPos3 + 1;
                                    }
                            }
                        }
                    }
                } else if (i4 != 1) {
                    continue;
                } else {
                    while (true) {
                        if (bytePos < byteBufferLimit && ucPos < charBufferLimit) {
                            int bytePos11 = bytePos + 1;
                            int aByte3 = byteBuffer[bytePos] & 255;
                            switch (aByte3) {
                                case 224:
                                case 225:
                                case 226:
                                case 227:
                                case 228:
                                case 229:
                                case 230:
                                case 231:
                                    this.fCurrentWindow = aByte3 - 224;
                                    this.fMode = i;
                                    bytePos = bytePos11;
                                    b = aByte3;
                                    break;
                                case 232:
                                case 233:
                                case 234:
                                case 235:
                                case 236:
                                case 237:
                                case 238:
                                case 239:
                                    if (bytePos11 < byteBufferLimit) {
                                        this.fCurrentWindow = aByte3 - 232;
                                        this.fOffsets[this.fCurrentWindow] = sOffsetTable[byteBuffer[bytePos11] & 255];
                                        this.fMode = i;
                                        b = aByte3;
                                        bytePos = bytePos11 + 1;
                                        break;
                                    } else {
                                        int bytePos12 = bytePos11 - 1;
                                        System.arraycopy(byteBuffer, bytePos12, this.fBuffer, i, byteBufferLimit - bytePos12);
                                        this.fBufferLength = byteBufferLimit - bytePos12;
                                        bytePos = bytePos12 + this.fBufferLength;
                                        break;
                                    }
                                case 240:
                                    if (bytePos11 >= byteBufferLimit - 1) {
                                        int bytePos13 = bytePos11 - 1;
                                        System.arraycopy(byteBuffer, bytePos13, this.fBuffer, i, byteBufferLimit - bytePos13);
                                        this.fBufferLength = byteBufferLimit - bytePos13;
                                        bytePos = bytePos13 + this.fBufferLength;
                                        break;
                                    } else {
                                        int bytePos14 = bytePos11 + 1;
                                        b = byteBuffer[bytePos11];
                                        bytePos = bytePos14 + 1;
                                        charBuffer[ucPos] = (char) ((b << 8) | (byteBuffer[bytePos14] & 255));
                                        ucPos++;
                                    }
                                case 241:
                                    if (bytePos11 + 1 < byteBufferLimit) {
                                        int bytePos15 = bytePos11 + 1;
                                        int aByte4 = byteBuffer[bytePos11] & 255;
                                        this.fCurrentWindow = (aByte4 & 224) >> 5;
                                        this.fOffsets[this.fCurrentWindow] = (((byteBuffer[bytePos15] & 255) | ((aByte4 & 31) << 8)) * 128) + 65536;
                                        this.fMode = i;
                                        b = aByte4;
                                        bytePos = bytePos15 + 1;
                                        break;
                                    } else {
                                        int bytePos16 = bytePos11 - 1;
                                        System.arraycopy(byteBuffer, bytePos16, this.fBuffer, i, byteBufferLimit - bytePos16);
                                        this.fBufferLength = byteBufferLimit - bytePos16;
                                        bytePos = bytePos16 + this.fBufferLength;
                                        break;
                                    }
                                default:
                                    if (bytePos11 >= byteBufferLimit) {
                                        int bytePos17 = bytePos11 - 1;
                                        System.arraycopy(byteBuffer, bytePos17, this.fBuffer, i, byteBufferLimit - bytePos17);
                                        this.fBufferLength = byteBufferLimit - bytePos17;
                                        bytePos = bytePos17 + this.fBufferLength;
                                        break;
                                    } else {
                                        bytePos = bytePos11 + 1;
                                        charBuffer[ucPos] = (char) ((byteBuffer[bytePos11] & 255) | (aByte3 << 8));
                                        b = aByte3;
                                        ucPos++;
                                    }
                            }
                        }
                    }
                }
            }
        }
        if (bytesRead != null) {
            bytesRead[i] = bytePos - byteBufferStart;
        }
        return ucPos - charBufferStart;
    }

    public void reset() {
        int[] iArr = this.fOffsets;
        iArr[0] = 128;
        iArr[1] = 192;
        iArr[2] = 1024;
        iArr[3] = 1536;
        iArr[4] = 2304;
        iArr[5] = 12352;
        iArr[6] = 12448;
        iArr[7] = 65280;
        this.fCurrentWindow = 0;
        this.fMode = 0;
        this.fBufferLength = 0;
    }
}
