package android.icu.impl;

import android.support.v4.view.InputDeviceCompat;

public final class SimpleFormatterImpl {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final int ARG_NUM_LIMIT = 256;
    private static final String[][] COMMON_PATTERNS = {new String[]{"{0} {1}", "\u0002\u0000ā \u0001"}, new String[]{"{0} ({1})", "\u0002\u0000Ă (\u0001ā)"}, new String[]{"{0}, {1}", "\u0002\u0000Ă, \u0001"}, new String[]{"{0} – {1}", "\u0002\u0000ă – \u0001"}};
    private static final char LEN1_CHAR = 257;
    private static final char LEN2_CHAR = 258;
    private static final char LEN3_CHAR = 259;
    private static final int MAX_SEGMENT_LENGTH = 65279;
    private static final char SEGMENT_LENGTH_ARGUMENT_CHAR = 65535;

    private SimpleFormatterImpl() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:57:0x00d6  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String compileToStringMinMaxArguments(java.lang.CharSequence r18, java.lang.StringBuilder r19, int r20, int r21) {
        /*
        // Method dump skipped, instructions count: 387
        */
        throw new UnsupportedOperationException("Method not decompiled: android.icu.impl.SimpleFormatterImpl.compileToStringMinMaxArguments(java.lang.CharSequence, java.lang.StringBuilder, int, int):java.lang.String");
    }

    public static int getArgumentLimit(String compiledPattern) {
        return compiledPattern.charAt(0);
    }

    public static String formatCompiledPattern(String compiledPattern, CharSequence... values) {
        return formatAndAppend(compiledPattern, new StringBuilder(), null, values).toString();
    }

    public static String formatRawPattern(String pattern, int min, int max, CharSequence... values) {
        StringBuilder sb = new StringBuilder();
        String compiledPattern = compileToStringMinMaxArguments(pattern, sb, min, max);
        sb.setLength(0);
        return formatAndAppend(compiledPattern, sb, null, values).toString();
    }

    public static StringBuilder formatAndAppend(String compiledPattern, StringBuilder appendTo, int[] offsets, CharSequence... values) {
        if ((values != null ? values.length : 0) >= getArgumentLimit(compiledPattern)) {
            return format(compiledPattern, values, appendTo, null, true, offsets);
        }
        throw new IllegalArgumentException("Too few values.");
    }

    public static StringBuilder formatAndReplace(String compiledPattern, StringBuilder result, int[] offsets, CharSequence... values) {
        String resultCopy;
        if ((values != null ? values.length : 0) >= getArgumentLimit(compiledPattern)) {
            int firstArg = -1;
            String resultCopy2 = null;
            if (getArgumentLimit(compiledPattern) > 0) {
                int n = 1;
                while (n < compiledPattern.length()) {
                    int i = n + 1;
                    int n2 = compiledPattern.charAt(n);
                    if (n2 < 256) {
                        if (values[n2] == result) {
                            if (i == 2) {
                                firstArg = n2;
                                n = i;
                            } else if (resultCopy2 == null) {
                                resultCopy2 = result.toString();
                                n = i;
                            }
                        }
                        n = i;
                    } else {
                        n = i + n2 + InputDeviceCompat.SOURCE_ANY;
                    }
                }
                resultCopy = resultCopy2;
            } else {
                resultCopy = null;
            }
            if (firstArg < 0) {
                result.setLength(0);
            }
            return format(compiledPattern, values, result, resultCopy, false, offsets);
        }
        throw new IllegalArgumentException("Too few values.");
    }

    public static String getTextWithNoArguments(String compiledPattern) {
        StringBuilder sb = new StringBuilder((compiledPattern.length() - 1) - getArgumentLimit(compiledPattern));
        int segmentLength = 1;
        while (segmentLength < compiledPattern.length()) {
            int i = segmentLength + 1;
            int segmentLength2 = compiledPattern.charAt(segmentLength) + InputDeviceCompat.SOURCE_ANY;
            if (segmentLength2 > 0) {
                int limit = i + segmentLength2;
                sb.append((CharSequence) compiledPattern, i, limit);
                segmentLength = limit;
            } else {
                segmentLength = i;
            }
        }
        return sb.toString();
    }

    private static StringBuilder format(String compiledPattern, CharSequence[] values, StringBuilder result, String resultCopy, boolean forbidResultAsValue, int[] offsets) {
        int offsetsLength;
        if (offsets == null) {
            offsetsLength = 0;
        } else {
            offsetsLength = offsets.length;
            for (int i = 0; i < offsetsLength; i++) {
                offsets[i] = -1;
            }
        }
        int n = 1;
        while (n < compiledPattern.length()) {
            int i2 = n + 1;
            int n2 = compiledPattern.charAt(n);
            if (n2 < 256) {
                CharSequence value = values[n2];
                if (value != result) {
                    if (n2 < offsetsLength) {
                        offsets[n2] = result.length();
                    }
                    result.append(value);
                } else if (forbidResultAsValue) {
                    throw new IllegalArgumentException("Value must not be same object as result");
                } else if (i2 != 2) {
                    if (n2 < offsetsLength) {
                        offsets[n2] = result.length();
                    }
                    result.append(resultCopy);
                } else if (n2 < offsetsLength) {
                    offsets[n2] = 0;
                }
                n = i2;
            } else {
                int limit = n2 + InputDeviceCompat.SOURCE_ANY + i2;
                result.append((CharSequence) compiledPattern, i2, limit);
                n = limit;
            }
        }
        return result;
    }
}
