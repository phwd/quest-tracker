package com.facebook.common.string;

import android.os.Bundle;
import android.text.SpannableStringBuilder;
import com.facebook.common.stringformat.StringFormatUtil;
import com.facebook.infer.annotation.PropagatesNullable;
import com.facebook.infer.annotation.TrueOnNull;
import com.google.common.base.Charsets;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;

public class StringUtil {

    public interface StringProcessor {
        String process(Object obj);
    }

    protected StringUtil() {
    }

    public static String emptyStringIfNull(@Nullable String s) {
        return s == null ? "" : s;
    }

    @TrueOnNull
    public static boolean isEmptyOrNull(@Nullable CharSequence s) {
        return s == null || s.length() == 0;
    }

    public static boolean isAbsentOrNull(Optional<? extends CharSequence> s) {
        return !s.isPresent() || ((CharSequence) s.get()).length() == 0;
    }

    @TrueOnNull
    public static boolean isAnyEmptyOrNull(CharSequence... charSequences) {
        Preconditions.checkNotNull(charSequences);
        for (CharSequence charSequence : charSequences) {
            if (isEmptyOrNull(charSequence)) {
                return true;
            }
        }
        return false;
    }

    public static boolean areAllEmptyOrNull(CharSequence... charSequences) {
        Preconditions.checkNotNull(charSequences);
        for (CharSequence charSequence : charSequences) {
            if (!isEmptyOrNull(charSequence)) {
                return false;
            }
        }
        return true;
    }

    public static boolean safeEquals(@Nullable String s1, @Nullable String s2) {
        if (s1 == null) {
            return s2 == null;
        }
        return s1.equals(s2);
    }

    public static boolean equalToOneOf(@Nullable String subject, String... strings) {
        if (strings == null) {
            return false;
        }
        for (String string : strings) {
            if (safeEquals(subject, string)) {
                return true;
            }
        }
        return false;
    }

    public static int safeCompareTo(@Nullable String s1, @Nullable String s2) {
        if (s1 == null) {
            return s2 == null ? 0 : -1;
        }
        if (s2 == null) {
            return 1;
        }
        return s1.compareTo(s2);
    }

    public static boolean phpStyleSafeEquals(@Nullable String s1, @Nullable String s2) {
        return Objects.equal(Strings.emptyToNull(s1), Strings.emptyToNull(s2));
    }

    public static String fromUTf8(byte[] bytes) {
        try {
            return new String(bytes, Charsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported");
        }
    }

    public static byte[] toUTf8(String s) {
        try {
            return s.getBytes(Charsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("UTF-8 not supported");
        }
    }

    public static long hash64(String string) {
        long h = 1125899906842597L;
        int len = string.length();
        for (int i = 0; i < len; i++) {
            h = (31 * h) + ((long) string.charAt(i));
        }
        return h;
    }

    public static CharSequence trim(CharSequence s) {
        return trim(s, true, true);
    }

    public static CharSequence trimLeft(CharSequence s) {
        return trim(s, true, false);
    }

    public static CharSequence trimRight(CharSequence s) {
        return trim(s, false, true);
    }

    public static CharSequence trim(CharSequence s, boolean left, boolean right) {
        int start = 0;
        int end = s.length();
        if (right) {
            while (end > 0 && Character.isWhitespace(s.charAt(end - 1))) {
                end--;
            }
        }
        if (left) {
            while (end > start && Character.isWhitespace(s.charAt(start))) {
                start++;
            }
        }
        return (end == s.length() && start == 0) ? s : s.subSequence(start, end);
    }

    public static String formatStrLocaleSafe(String str, Object... args) {
        return StringFormatUtil.formatStrLocaleSafe(str, args);
    }

    @TrueOnNull
    public static boolean isEmptyAfterTrimOrNull(@Nullable CharSequence text) {
        if (isEmptyOrNull(text)) {
            return true;
        }
        for (int i = 0; i < text.length(); i++) {
            if (!Character.isWhitespace(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @TrueOnNull
    public static boolean isBlank(@Nullable CharSequence text) {
        return isEmptyAfterTrimOrNull(text);
    }

    public static <T extends CharSequence> T assertNotBlank(@Nullable T text) {
        Preconditions.checkArgument(!isBlank(text));
        return text;
    }

    public static <T extends CharSequence> T assertNotBlank(@Nullable T text, String errorMessage) {
        Preconditions.checkArgument(!isBlank(text), errorMessage);
        return text;
    }

    public static String truncate(@Nullable String text, int length) {
        return (Strings.isNullOrEmpty(text) || length == 0 || text.length() < length) ? text : text.substring(0, length - (Character.charCount(Character.codePointAt(text, length - 1)) - 1));
    }

    public static List<String> splitString(String source, char c) {
        if (source.length() == 0) {
            return Collections.singletonList("");
        }
        return Lists.newArrayList(Splitter.on(c).split(source));
    }

    public static void join(StringBuilder sb, String separator, @Nullable StringProcessor processor, Object... items) {
        boolean first = true;
        for (Object o : items) {
            if (first) {
                first = false;
            } else {
                sb.append(separator);
            }
            if (o instanceof Collection) {
                join(sb, separator, processor, ((Collection) o).toArray());
            } else if (o instanceof Object[]) {
                join(sb, separator, processor, (Object[]) o);
            } else if (processor != null) {
                sb.append(processor.process(o));
            } else {
                sb.append(o.toString());
            }
        }
    }

    public static String join(String separator, Object... items) {
        StringBuilder sb = new StringBuilder();
        join(sb, separator, null, items);
        return sb.toString();
    }

    public static CharSequence join(CharSequence delimiter, Collection<CharSequence> tokens) {
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        boolean firstTime = true;
        for (CharSequence token : tokens) {
            if (firstTime) {
                firstTime = false;
            } else {
                ssb.append(delimiter);
            }
            ssb.append(token);
        }
        return ssb;
    }

    public static String capitalizeFirstCharacter(@PropagatesNullable String input) {
        if (input == null) {
            return null;
        }
        if (input.length() == 0) {
            return "";
        }
        StringBuilder result = new StringBuilder(input.length());
        result.append(Character.toUpperCase(input.charAt(0))).append(input.substring(1));
        return result.toString();
    }

    public static String capitalize(@PropagatesNullable String input) {
        if (input == null) {
            return null;
        }
        StringBuilder result = new StringBuilder(input.length());
        String[] words = input.split("\\s");
        int numWords = words.length;
        for (int i = 0; i < numWords; i++) {
            if (words[i].length() != 0) {
                if (i > 0) {
                    result.append(" ");
                }
                result.append(Character.toUpperCase(words[i].charAt(0))).append(words[i].substring(1));
            }
        }
        return result.toString();
    }

    public static StringBuilder appendEscapedFQLString(StringBuilder builder, String string) {
        builder.append('\'');
        int length = string.length();
        for (int i = 0; i < length; i++) {
            char c = string.charAt(i);
            if (c == '\'' || c == '\\') {
                builder.append('\\');
            }
            builder.append(c);
        }
        builder.append('\'');
        return builder;
    }

    public static String makeEscapedFqlString(String string) {
        StringBuilder builder = new StringBuilder();
        appendEscapedFQLString(builder, string);
        return builder.toString();
    }

    public static String makeEscapedJSString(String string, boolean allowControlCharacter) {
        if (string == null) {
            return "";
        }
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                buf.append(c);
            } else if (c <= 255 && (allowControlCharacter || c >= ' ')) {
                buf.append(String.format("\\x%02X", Integer.valueOf(c)));
            } else if (c > 255) {
                buf.append(String.format("\\u%04X", Integer.valueOf(c)));
            }
        }
        return buf.toString();
    }

    @Nullable
    public static String getStringValue(Bundle bundle, String key) {
        Object obj = bundle.get(key);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public static String xmlEncodeNonLatin(String str) {
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder(str.length());
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if (ch == '&') {
                sb.append("&amp;");
            } else if (ch < ' ' || ch > '~') {
                sb.append("&#");
                sb.append(Integer.toString(ch));
                sb.append(";");
            } else {
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static String replaceSpacesWithNoBreakSpaces(String str) {
        return str.replace(' ', (char) 160);
    }

    public static String tail(String str, int length) throws IndexOutOfBoundsException {
        return str.substring(str.length() - length, str.length());
    }

    public static int getNewlineCount(@Nullable String text) {
        if (isEmptyOrNull(text)) {
            return 0;
        }
        int lines = 0;
        int codePointCount = text.codePointCount(0, text.length());
        int pos = 0;
        while (pos < codePointCount) {
            char c = (char) text.codePointAt(pos);
            if (c == '\r') {
                lines++;
                if (pos + 1 < codePointCount && ((char) text.codePointAt(pos + 1)) == '\n') {
                    pos++;
                }
            } else if (c == '\n') {
                lines++;
            }
            pos++;
        }
        return lines;
    }

    @Nullable
    public static String removeFromEnd(@Nullable String str, @Nullable String remove) {
        if (isEmptyOrNull(str) || isEmptyOrNull(remove)) {
            return str;
        }
        String str2 = str.trim();
        String remove2 = remove.trim();
        if (str2.endsWith(remove2)) {
            return str2.substring(0, str2.length() - remove2.length());
        }
        return str2;
    }

    public static boolean isConsecutiveNumber(String str) {
        if (isEmptyAfterTrimOrNull(str) || str.length() == 1 || !isPositiveInt(str)) {
            return false;
        }
        int order = 1;
        if ((str.charAt(1) - '0') - (str.charAt(0) - '0') == -1) {
            order = -1;
        }
        for (int i = 1; i < str.length(); i++) {
            if ((str.charAt(i) - '0') - (str.charAt(i - 1) - '0') != order) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPositiveInt(String str) {
        try {
            if (Integer.parseInt(str) > 0) {
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isRepetitiveNumber(@Nullable String str) {
        if (isEmptyAfterTrimOrNull(str) || str.length() == 1 || !isPositiveInt(str)) {
            return false;
        }
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) != str.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
