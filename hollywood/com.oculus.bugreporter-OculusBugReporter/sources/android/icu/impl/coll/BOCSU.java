package android.icu.impl.coll;

import android.icu.util.ByteArrayWrapper;

public class BOCSU {
    private static final int SLOPE_LEAD_2_ = 42;
    private static final int SLOPE_LEAD_3_ = 3;
    private static final int SLOPE_MAX_ = 255;
    private static final int SLOPE_MAX_BYTES_ = 4;
    private static final int SLOPE_MIDDLE_ = 129;
    private static final int SLOPE_MIN_ = 3;
    private static final int SLOPE_REACH_NEG_1_ = -80;
    private static final int SLOPE_REACH_NEG_2_ = -10668;
    private static final int SLOPE_REACH_NEG_3_ = -192786;
    private static final int SLOPE_REACH_POS_1_ = 80;
    private static final int SLOPE_REACH_POS_2_ = 10667;
    private static final int SLOPE_REACH_POS_3_ = 192785;
    private static final int SLOPE_SINGLE_ = 80;
    private static final int SLOPE_START_NEG_2_ = 49;
    private static final int SLOPE_START_NEG_3_ = 7;
    private static final int SLOPE_START_POS_2_ = 210;
    private static final int SLOPE_START_POS_3_ = 252;
    private static final int SLOPE_TAIL_COUNT_ = 253;

    public static int writeIdenticalLevelRun(int prev, CharSequence s, int i, int length, ByteArrayWrapper sink) {
        int prev2;
        while (i < length) {
            ensureAppendCapacity(sink, 16, s.length() * 2);
            byte[] buffer = sink.bytes;
            int capacity = buffer.length;
            int p = sink.size;
            int lastSafe = capacity - 4;
            while (i < length && p <= lastSafe) {
                if (prev < 19968 || prev >= 40960) {
                    prev2 = (prev & -128) + 80;
                } else {
                    prev2 = 30292;
                }
                int c = Character.codePointAt(s, i);
                i += Character.charCount(c);
                if (c == 65534) {
                    buffer[p] = 2;
                    prev = 0;
                    p++;
                } else {
                    p = writeDiff(c - prev2, buffer, p);
                    prev = c;
                }
            }
            sink.size = p;
        }
        return prev;
    }

    private static void ensureAppendCapacity(ByteArrayWrapper sink, int minCapacity, int desiredCapacity) {
        if (sink.bytes.length - sink.size < minCapacity) {
            if (desiredCapacity < minCapacity) {
                desiredCapacity = minCapacity;
            }
            sink.ensureCapacity(sink.size + desiredCapacity);
        }
    }

    private BOCSU() {
    }

    private static final long getNegDivMod(int number, int factor) {
        int modulo = number % factor;
        long result = (long) (number / factor);
        if (modulo < 0) {
            result--;
            modulo += factor;
        }
        return (result << 32) | ((long) modulo);
    }

    private static final int writeDiff(int diff, byte[] buffer, int offset) {
        if (diff < SLOPE_REACH_NEG_1_) {
            long division = getNegDivMod(diff, 253);
            int modulo = (int) division;
            if (diff >= SLOPE_REACH_NEG_2_) {
                int offset2 = offset + 1;
                buffer[offset] = (byte) (((int) (division >> 32)) + 49);
                int offset3 = offset2 + 1;
                buffer[offset2] = (byte) (modulo + 3);
                return offset3;
            } else if (diff >= SLOPE_REACH_NEG_3_) {
                buffer[offset + 2] = (byte) (modulo + 3);
                long division2 = getNegDivMod((int) (division >> 32), 253);
                buffer[offset + 1] = (byte) (((int) division2) + 3);
                buffer[offset] = (byte) (((int) (division2 >> 32)) + 7);
                return offset + 3;
            } else {
                buffer[offset + 3] = (byte) (modulo + 3);
                long division3 = getNegDivMod((int) (division >> 32), 253);
                buffer[offset + 2] = (byte) (((int) division3) + 3);
                buffer[offset + 1] = (byte) (((int) getNegDivMod((int) (division3 >> 32), 253)) + 3);
                buffer[offset] = 3;
                return offset + 4;
            }
        } else if (diff <= 80) {
            int offset4 = offset + 1;
            buffer[offset] = (byte) (diff + 129);
            return offset4;
        } else if (diff <= SLOPE_REACH_POS_2_) {
            int offset5 = offset + 1;
            buffer[offset] = (byte) ((diff / 253) + 210);
            int offset6 = offset5 + 1;
            buffer[offset5] = (byte) ((diff % 253) + 3);
            return offset6;
        } else if (diff <= SLOPE_REACH_POS_3_) {
            buffer[offset + 2] = (byte) ((diff % 253) + 3);
            int diff2 = diff / 253;
            buffer[offset + 1] = (byte) ((diff2 % 253) + 3);
            buffer[offset] = (byte) ((diff2 / 253) + 252);
            return offset + 3;
        } else {
            buffer[offset + 3] = (byte) ((diff % 253) + 3);
            int diff3 = diff / 253;
            buffer[offset + 2] = (byte) ((diff3 % 253) + 3);
            buffer[offset + 1] = (byte) (((diff3 / 253) % 253) + 3);
            buffer[offset] = -1;
            return offset + 4;
        }
    }
}
