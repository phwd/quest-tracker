package com.facebook.crudolib.params;

import java.io.IOException;
import java.io.Writer;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class NumberFormatHelper {
    private static final ThreadLocal<NumberFormatHelper> sInstance = new ThreadLocal<NumberFormatHelper>() {
        /* class com.facebook.crudolib.params.NumberFormatHelper.AnonymousClass1 */

        @Override // java.lang.ThreadLocal
        public NumberFormatHelper initialValue() {
            return new NumberFormatHelper();
        }
    };
    private final StringBuilder mBuffer = new StringBuilder(20);

    public static NumberFormatHelper getThreadLocalInstance() {
        return sInstance.get();
    }

    NumberFormatHelper() {
    }

    public CharSequence bufferedToString(Number number) {
        StringBuilder sb = this.mBuffer;
        sb.delete(0, sb.length());
        if (number instanceof Float) {
            sb.append(number.floatValue());
        } else if (number instanceof Double) {
            sb.append(number.doubleValue());
        } else if (number instanceof Integer) {
            sb.append(number.intValue());
        } else if (number instanceof Long) {
            sb.append(number.longValue());
        } else if (number instanceof Short) {
            sb.append((int) number.shortValue());
        } else if (number instanceof Byte) {
            sb.append((int) number.byteValue());
        } else {
            throw new UnsupportedOperationException("Type " + number.getClass() + " not supported");
        }
        return sb;
    }

    public void writeTo(Writer writer, Number number) throws IOException {
        CharSequence bufferedToString = bufferedToString(number);
        int length = bufferedToString.length();
        for (int i = 0; i < length; i++) {
            writer.write(bufferedToString.charAt(i));
        }
    }
}
