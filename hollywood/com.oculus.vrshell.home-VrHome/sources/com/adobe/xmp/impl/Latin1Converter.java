package com.adobe.xmp.impl;

import com.adobe.xmp.options.PropertyOptions;
import java.io.UnsupportedEncodingException;

public class Latin1Converter {
    private static final int STATE_START = 0;
    private static final int STATE_UTF8CHAR = 11;

    private Latin1Converter() {
    }

    public static ByteBuffer convert(ByteBuffer buffer) {
        if (!"UTF-8".equals(buffer.getEncoding())) {
            return buffer;
        }
        byte[] readAheadBuffer = new byte[8];
        int readAhead = 0;
        int expectedBytes = 0;
        ByteBuffer out = new ByteBuffer((buffer.length() * 4) / 3);
        int state = 0;
        int i = 0;
        while (i < buffer.length()) {
            int b = buffer.charAt(i);
            switch (state) {
                case 11:
                    if (expectedBytes <= 0 || (b & 192) != 128) {
                        out.append(convertToUTF8(readAheadBuffer[0]));
                        i -= readAhead;
                        readAhead = 0;
                        state = 0;
                        break;
                    } else {
                        int readAhead2 = readAhead + 1;
                        readAheadBuffer[readAhead] = (byte) b;
                        expectedBytes--;
                        if (expectedBytes == 0) {
                            out.append(readAheadBuffer, 0, readAhead2);
                            readAhead = 0;
                            state = 0;
                            break;
                        } else {
                            readAhead = readAhead2;
                            break;
                        }
                    }
                    break;
                default:
                    if (b >= 127) {
                        if (b >= 192) {
                            expectedBytes = -1;
                            int test = b;
                            while (expectedBytes < 8 && (test & PropertyOptions.HAS_TYPE) == 128) {
                                expectedBytes++;
                                test <<= 1;
                            }
                            readAheadBuffer[readAhead] = (byte) b;
                            state = 11;
                            readAhead++;
                            break;
                        } else {
                            out.append(convertToUTF8((byte) b));
                            break;
                        }
                    } else {
                        out.append((byte) b);
                        break;
                    }
                    break;
            }
            i++;
        }
        if (state != 11) {
            return out;
        }
        for (int j = 0; j < readAhead; j++) {
            out.append(convertToUTF8(readAheadBuffer[j]));
        }
        return out;
    }

    private static byte[] convertToUTF8(byte ch) {
        int c = ch & 255;
        if (c >= 128) {
            if (c == 129 || c == 141 || c == 143 || c == 144 || c == 157) {
                try {
                    return new byte[]{32};
                } catch (UnsupportedEncodingException e) {
                }
            } else {
                return new String(new byte[]{ch}, "cp1252").getBytes("UTF-8");
            }
        }
        return new byte[]{ch};
    }
}
