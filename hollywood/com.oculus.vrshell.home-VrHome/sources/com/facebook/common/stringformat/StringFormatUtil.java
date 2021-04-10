package com.facebook.common.stringformat;

import com.facebook.infer.annotation.Assertions;
import com.facebook.proguard.annotations.DoNotStrip;
import java.util.Formattable;
import java.util.Formatter;
import java.util.MissingFormatArgumentException;
import java.util.UnknownFormatConversionException;
import javax.annotation.Nullable;

@DoNotStrip
public class StringFormatUtil {
    private static final Object[] SINGLE_ITEM_NULL_OBJECT_ARRAY = {null};

    @DoNotStrip
    public static String formatStrLocaleSafe(String format) {
        return formatStrLocaleSafeInner(format, 0, null, null, null, null, null);
    }

    @DoNotStrip
    public static String formatStrLocaleSafe(String format, @Nullable Object arg0) {
        return formatStrLocaleSafeInner(format, 1, arg0, null, null, null, null);
    }

    @DoNotStrip
    public static String formatStrLocaleSafe(String format, @Nullable Object arg0, @Nullable Object arg1) {
        return formatStrLocaleSafeInner(format, 2, arg0, arg1, null, null, null);
    }

    @DoNotStrip
    public static String formatStrLocaleSafe(String format, @Nullable Object arg0, @Nullable Object arg1, @Nullable Object arg2) {
        return formatStrLocaleSafeInner(format, 3, arg0, arg1, arg2, null, null);
    }

    @DoNotStrip
    public static String formatStrLocaleSafe(String format, @Nullable Object arg0, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3) {
        return formatStrLocaleSafeInner(format, 4, arg0, arg1, arg2, arg3, null);
    }

    @DoNotStrip
    public static String formatStrLocaleSafe(String format, Object... args) {
        return formatStrLocaleSafeInner(format, -1, null, null, null, null, args);
    }

    private static String formatStrLocaleSafeInner(String format, int argCount, @Nullable Object arg0, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3, @Nullable Object[] argsList) {
        int bufferSize = doDryRunInternal(format, argCount, arg0, arg1, arg2, arg3, argsList);
        if (bufferSize == -1) {
            return fallbackToSystem(format, argCount, arg0, arg1, arg2, arg3, argsList);
        }
        if (bufferSize == -2) {
            return format;
        }
        StringBuilder output = new StringBuilder(bufferSize);
        if (argCount == -1) {
            doFormatArray(output, format, argsList);
        } else {
            doFormatArgs(output, format, argCount, arg0, arg1, arg2, arg3);
        }
        return output.toString();
    }

    @DoNotStrip
    public static void appendFormatStrLocaleSafe(StringBuilder output, String format, Object... args) {
        int bufferSize = doDryRunInternal(format, -1, null, null, null, null, args);
        if (bufferSize == -1) {
            new Formatter(output).format(null, format, args);
        } else if (bufferSize == -2) {
            output.append(format);
        } else {
            output.ensureCapacity(bufferSize);
            doFormatArray(output, format, args);
        }
    }

    static int doDryRun(String format) {
        return doFormatArgs(null, format, 0, null, null, null, null);
    }

    static int doDryRun(String format, @Nullable Object arg0) {
        return doFormatArgs(null, format, 1, arg0, null, null, null);
    }

    static int doDryRun(String format, @Nullable Object arg0, @Nullable Object arg1) {
        return doFormatArgs(null, format, 2, arg0, arg1, null, null);
    }

    static int doDryRun(String format, @Nullable Object arg0, @Nullable Object arg1, @Nullable Object arg2) {
        return doFormatArgs(null, format, 3, arg0, arg1, arg2, null);
    }

    static int doDryRun(String format, @Nullable Object arg0, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3) {
        return doFormatArgs(null, format, 4, arg0, arg1, arg2, arg3);
    }

    static int doDryRun(String format, @Nullable Object[] args) {
        return doFormatArray(null, format, args);
    }

    private static int doDryRunInternal(String format, int argCount, @Nullable Object arg0, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3, @Nullable Object[] argsList) {
        switch (argCount) {
            case 0:
                return doDryRun(format);
            case 1:
                return doDryRun(format, arg0);
            case 2:
                return doDryRun(format, arg0, arg1);
            case 3:
                return doDryRun(format, arg0, arg1, arg2);
            case 4:
                return doDryRun(format, arg0, arg1, arg2, arg3);
            default:
                return doDryRun(format, argsList);
        }
    }

    private static int doFormatArgs(@Nullable StringBuilder output, String format, int argCount, @Nullable Object arg0, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3) {
        int segIdx = 0;
        int length = 0;
        boolean dryRun = output == null;
        int startAt = argCount == 0 ? -1 : 0;
        for (int argIdx = startAt; argIdx < argCount; argIdx++) {
            int appendLen = appendSegmentFormatArgs(output, format, segIdx, arg0, arg1, arg2, arg3, argIdx);
            if (appendLen == -1) {
                return -1;
            }
            if (dryRun) {
                length += appendLen;
            }
            segIdx = getNextFormatSegmentIndex(format, segIdx);
            if (argIdx == startAt && segIdx == -200 && dryRun) {
                return -2;
            }
            if (segIdx < 0) {
                break;
            }
        }
        if (!(segIdx == -200 || segIdx == -201)) {
            return processRemainingString(output, format, segIdx, length, dryRun);
        }
        if (dryRun) {
            return length;
        }
        return -3;
    }

    private static int appendSegmentFormatArgs(@Nullable StringBuilder output, String format, int startIdx, @Nullable Object arg0, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3, int argIdx) {
        switch (argIdx) {
            case -1:
                return appendSegmentFormat(output, format, startIdx, null, false);
            case 0:
                return appendSegmentFormat(output, format, startIdx, arg0, true);
            case 1:
                return appendSegmentFormat(output, format, startIdx, arg1, true);
            case 2:
                return appendSegmentFormat(output, format, startIdx, arg2, true);
            case 3:
                return appendSegmentFormat(output, format, startIdx, arg3, true);
            default:
                throw new AssertionError();
        }
    }

    private static int doFormatArray(@Nullable StringBuilder output, String format, @Nullable Object... args) {
        int segIdx = 0;
        int length = 0;
        boolean dryRun = output == null;
        boolean argsWasEmpty = false;
        if (args == null || args.length == 0) {
            args = SINGLE_ITEM_NULL_OBJECT_ARRAY;
            argsWasEmpty = true;
        }
        boolean formatted = false;
        int length2 = args.length;
        int i = 0;
        while (true) {
            if (i >= length2) {
                break;
            }
            int appendLen = appendSegmentFormat(output, format, segIdx, args[i], !argsWasEmpty);
            if (appendLen != -1) {
                if (dryRun) {
                    length += appendLen;
                }
                segIdx = getNextFormatSegmentIndex(format, segIdx);
                if (segIdx == -200) {
                    break;
                } else if (segIdx == -201) {
                    formatted = true;
                    break;
                } else {
                    formatted = true;
                    i++;
                }
            } else {
                return -1;
            }
        }
        if (dryRun && !formatted) {
            return -2;
        }
        if (segIdx != -200 && segIdx != -201) {
            return processRemainingString(output, format, segIdx, length, dryRun);
        }
        if (dryRun) {
            return length;
        }
        return -3;
    }

    private static int appendSegmentFormat(@Nullable StringBuilder output, String format, int startIdx, @Nullable Object arg, boolean considerArg) {
        int appendResult;
        int index = startIdx;
        int formatLen = format.length();
        int length = 0;
        while (index < formatLen) {
            char ch = format.charAt(index);
            if (ch == '%') {
                if (validateFormatPercent(format, index) != -100) {
                    return -1;
                }
                char nextChar = format.charAt(index + 1);
                if (!considerArg && nextChar != '%') {
                    return -1;
                }
                boolean segmentDone = true;
                if (nextChar == 's') {
                    appendResult = appendStringTypeArg(output, arg);
                } else if (nextChar == 'd') {
                    appendResult = appendIntTypeArg(output, arg);
                } else if (nextChar == '%') {
                    segmentDone = false;
                    if (output != null) {
                        output.append('%');
                    }
                    appendResult = 1;
                    index++;
                } else {
                    appendResult = -1;
                }
                if (appendResult == -1) {
                    return -1;
                }
                if (output == null) {
                    length += appendResult;
                }
                if (segmentDone) {
                    break;
                }
            } else if (output == null) {
                length++;
            } else {
                output.append(ch);
            }
            index++;
        }
        if (output == null) {
            return length;
        }
        return -3;
    }

    private static int appendStringTypeArg(@Nullable StringBuilder output, @Nullable Object arg) {
        if (!(arg instanceof Formattable)) {
            String str = null;
            if (arg instanceof String) {
                str = (String) arg;
            } else if (arg != null) {
                str = arg.toString();
            }
            if (str == null) {
                str = "null";
            }
            if (output == null) {
                return str.length();
            }
            output.append(str);
            return -3;
        } else if (output == null) {
            return -1;
        } else {
            throw new AssertionError();
        }
    }

    private static int appendIntTypeArg(@Nullable StringBuilder output, @Nullable Object arg) {
        int length = 0;
        if (arg == null) {
            if (output == null) {
                length = 0 + "null".length();
            } else {
                output.append("null");
            }
        } else if (arg instanceof Integer) {
            if (output == null) {
                length = 0 + 11;
            } else {
                output.append(((Number) arg).intValue());
            }
        } else if (arg instanceof Short) {
            if (output == null) {
                length = 0 + 6;
            } else {
                output.append(((Number) arg).intValue());
            }
        } else if (arg instanceof Byte) {
            if (output == null) {
                length = 0 + 4;
            } else {
                output.append(((Number) arg).intValue());
            }
        } else if (arg instanceof Long) {
            if (output == null) {
                length = 0 + 20;
            } else {
                output.append(((Long) arg).longValue());
            }
        } else if (output == null) {
            return -1;
        } else {
            throw new AssertionError();
        }
        if (output == null) {
            return length;
        }
        return -3;
    }

    private static int processRemainingString(@Nullable StringBuilder output, String format, int startIdx, int currLength, boolean dryRun) {
        int formatLen = format.length();
        int index = startIdx;
        int remainingLen = 0;
        while (index < formatLen) {
            char ch = format.charAt(index);
            if (ch == '%') {
                if (formatLen > index + 1 && format.charAt(index + 1) == '%') {
                    index++;
                } else if (dryRun) {
                    return -1;
                } else {
                    throw new AssertionError();
                }
            }
            if (output == null) {
                remainingLen++;
            } else {
                output.append(ch);
            }
            index++;
        }
        if (dryRun) {
            return currLength + remainingLen;
        }
        return -3;
    }

    private static int validateFormatPercent(String format, int percentIndex) {
        char nextChar;
        int nextIndex = percentIndex + 1;
        if (format.length() <= nextIndex || ((nextChar = format.charAt(nextIndex)) != 's' && nextChar != 'd' && nextChar != '%')) {
            return -101;
        }
        return -100;
    }

    private static int getNextFormatSegmentIndex(String format, int startIdx) {
        int index = startIdx;
        int formatLen = format.length();
        boolean foundDoublePercent = false;
        while (index < formatLen) {
            if (format.charAt(index) == '%' && validateFormatPercent(format, index) == -100) {
                if (format.charAt(index + 1) != '%') {
                    return index + 2;
                }
                index++;
                foundDoublePercent = true;
            }
            index++;
        }
        return foundDoublePercent ? -201 : -200;
    }

    private static String fallbackToSystem(String format, int argCount, @Nullable Object arg0, @Nullable Object arg1, @Nullable Object arg2, @Nullable Object arg3, @Nullable Object[] argsList) {
        switch (argCount) {
            case 0:
                return doFallbackToSystem(format, new Object[0]);
            case 1:
                return doFallbackToSystem(format, arg0);
            case 2:
                return doFallbackToSystem(format, arg0, arg1);
            case 3:
                return doFallbackToSystem(format, arg0, arg1, arg2);
            case 4:
                return doFallbackToSystem(format, arg0, arg1, arg2, arg3);
            default:
                return doFallbackToSystem(format, argsList);
        }
    }

    private static String doFallbackToSystem(String format, @Nullable Object... args) {
        try {
            return String.format(null, format, (Object[]) Assertions.assumeNotNull(args, "Should not be null under normal circumstances"));
        } catch (MissingFormatArgumentException | UnknownFormatConversionException ex) {
            throw new RuntimeException(ex.getMessage() + ": " + format);
        }
    }
}
