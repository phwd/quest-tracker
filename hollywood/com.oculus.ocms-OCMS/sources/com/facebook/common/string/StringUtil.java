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

    public static String emptyStringIfNull(@Nullable String str) {
        return str == null ? "" : str;
    }

    protected StringUtil() {
    }

    @TrueOnNull
    public static boolean isEmptyOrNull(@Nullable CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean isAbsentOrNull(Optional<? extends CharSequence> optional) {
        return !optional.isPresent() || ((CharSequence) optional.get()).length() == 0;
    }

    @TrueOnNull
    public static boolean isAnyEmptyOrNull(CharSequence... charSequenceArr) {
        Preconditions.checkNotNull(charSequenceArr);
        for (CharSequence charSequence : charSequenceArr) {
            if (isEmptyOrNull(charSequence)) {
                return true;
            }
        }
        return false;
    }

    public static boolean areAllEmptyOrNull(CharSequence... charSequenceArr) {
        Preconditions.checkNotNull(charSequenceArr);
        for (CharSequence charSequence : charSequenceArr) {
            if (!isEmptyOrNull(charSequence)) {
                return false;
            }
        }
        return true;
    }

    public static boolean safeEquals(@Nullable String str, @Nullable String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }

    public static boolean equalToOneOf(@Nullable String str, String... strArr) {
        if (strArr != null) {
            for (String str2 : strArr) {
                if (safeEquals(str, str2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static int safeCompareTo(@Nullable String str, @Nullable String str2) {
        if (str == null) {
            return str2 == null ? 0 : -1;
        }
        if (str2 == null) {
            return 1;
        }
        return str.compareTo(str2);
    }

    public static boolean phpStyleSafeEquals(@Nullable String str, @Nullable String str2) {
        return Objects.equal(Strings.emptyToNull(str), Strings.emptyToNull(str2));
    }

    public static String fromUTf8(byte[] bArr) {
        try {
            return new String(bArr, Charsets.UTF_8.name());
        } catch (UnsupportedEncodingException unused) {
            throw new RuntimeException("UTF-8 not supported");
        }
    }

    public static byte[] toUTf8(String str) {
        try {
            return str.getBytes(Charsets.UTF_8.name());
        } catch (UnsupportedEncodingException unused) {
            throw new RuntimeException("UTF-8 not supported");
        }
    }

    public static long hash64(String str) {
        int length = str.length();
        long j = 1125899906842597L;
        for (int i = 0; i < length; i++) {
            j = (j * 31) + ((long) str.charAt(i));
        }
        return j;
    }

    public static CharSequence trim(CharSequence charSequence) {
        return trim(charSequence, true, true);
    }

    public static CharSequence trimLeft(CharSequence charSequence) {
        return trim(charSequence, true, false);
    }

    public static CharSequence trimRight(CharSequence charSequence) {
        return trim(charSequence, false, true);
    }

    public static CharSequence trim(CharSequence charSequence, boolean z, boolean z2) {
        int length = charSequence.length();
        if (z2) {
            while (length > 0 && Character.isWhitespace(charSequence.charAt(length - 1))) {
                length--;
            }
        }
        int i = 0;
        if (z) {
            while (length > i && Character.isWhitespace(charSequence.charAt(i))) {
                i++;
            }
        }
        if (length == charSequence.length() && i == 0) {
            return charSequence;
        }
        return charSequence.subSequence(i, length);
    }

    public static String formatStrLocaleSafe(String str, Object... objArr) {
        return StringFormatUtil.formatStrLocaleSafe(str, objArr);
    }

    @TrueOnNull
    public static boolean isEmptyAfterTrimOrNull(@Nullable CharSequence charSequence) {
        if (isEmptyOrNull(charSequence)) {
            return true;
        }
        for (int i = 0; i < charSequence.length(); i++) {
            if (!Character.isWhitespace(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    @TrueOnNull
    public static boolean isBlank(@Nullable CharSequence charSequence) {
        return isEmptyAfterTrimOrNull(charSequence);
    }

    public static <T extends CharSequence> T assertNotBlank(@Nullable T t) {
        Preconditions.checkArgument(!isBlank(t));
        return t;
    }

    public static <T extends CharSequence> T assertNotBlank(@Nullable T t, String str) {
        Preconditions.checkArgument(!isBlank(t), str);
        return t;
    }

    public static String truncate(@Nullable String str, int i) {
        return (Strings.isNullOrEmpty(str) || i == 0 || str.length() < i) ? str : str.substring(0, i - (Character.charCount(Character.codePointAt(str, i - 1)) - 1));
    }

    public static List<String> splitString(String str, char c) {
        if (str.length() == 0) {
            return Collections.singletonList("");
        }
        return Lists.newArrayList(Splitter.on(c).split(str));
    }

    public static void join(StringBuilder sb, String str, @Nullable StringProcessor stringProcessor, Object... objArr) {
        boolean z = true;
        for (Object obj : objArr) {
            if (z) {
                z = false;
            } else {
                sb.append(str);
            }
            if (obj instanceof Collection) {
                join(sb, str, stringProcessor, ((Collection) obj).toArray());
            } else if (obj instanceof Object[]) {
                join(sb, str, stringProcessor, (Object[]) obj);
            } else if (stringProcessor != null) {
                sb.append(stringProcessor.process(obj));
            } else {
                sb.append(obj.toString());
            }
        }
    }

    public static String join(String str, Object... objArr) {
        StringBuilder sb = new StringBuilder();
        join(sb, str, null, objArr);
        return sb.toString();
    }

    public static CharSequence join(CharSequence charSequence, Collection<CharSequence> collection) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        boolean z = true;
        for (CharSequence charSequence2 : collection) {
            if (z) {
                z = false;
            } else {
                spannableStringBuilder.append(charSequence);
            }
            spannableStringBuilder.append(charSequence2);
        }
        return spannableStringBuilder;
    }

    public static String capitalizeFirstCharacter(@PropagatesNullable String str) {
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(str.length());
        sb.append(Character.toUpperCase(str.charAt(0)));
        sb.append(str.substring(1));
        return sb.toString();
    }

    public static String capitalize(@PropagatesNullable String str) {
        if (str == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder(str.length());
        String[] split = str.split("\\s");
        int length = split.length;
        for (int i = 0; i < length; i++) {
            if (split[i].length() != 0) {
                if (i > 0) {
                    sb.append(" ");
                }
                sb.append(Character.toUpperCase(split[i].charAt(0)));
                sb.append(split[i].substring(1));
            }
        }
        return sb.toString();
    }

    public static StringBuilder appendEscapedFQLString(StringBuilder sb, String str) {
        sb.append('\'');
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '\'' || charAt == '\\') {
                sb.append('\\');
            }
            sb.append(charAt);
        }
        sb.append('\'');
        return sb;
    }

    public static String makeEscapedFqlString(String str) {
        StringBuilder sb = new StringBuilder();
        appendEscapedFQLString(sb, str);
        return sb.toString();
    }

    public static String makeEscapedJSString(String str, boolean z) {
        if (str == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isLetterOrDigit(charAt)) {
                sb.append(charAt);
            } else if (charAt <= 255 && (z || charAt >= ' ')) {
                sb.append(String.format("\\x%02X", Integer.valueOf(charAt)));
            } else if (charAt > 255) {
                sb.append(String.format("\\u%04X", Integer.valueOf(charAt)));
            }
        }
        return sb.toString();
    }

    @Nullable
    public static String getStringValue(Bundle bundle, String str) {
        Object obj = bundle.get(str);
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
            char charAt = str.charAt(i);
            if (charAt == '&') {
                sb.append("&amp;");
            } else if (charAt < ' ' || charAt > '~') {
                sb.append("&#");
                sb.append(Integer.toString(charAt));
                sb.append(";");
            } else {
                sb.append(charAt);
            }
        }
        return sb.toString();
    }

    public static String replaceSpacesWithNoBreakSpaces(String str) {
        return str.replace(' ', (char) 160);
    }

    public static String tail(String str, int i) throws IndexOutOfBoundsException {
        return str.substring(str.length() - i, str.length());
    }

    public static int getNewlineCount(@Nullable String str) {
        int i = 0;
        if (isEmptyOrNull(str)) {
            return 0;
        }
        int codePointCount = str.codePointCount(0, str.length());
        int i2 = 0;
        while (i < codePointCount) {
            char codePointAt = (char) str.codePointAt(i);
            if (codePointAt == '\r') {
                i2++;
                int i3 = i + 1;
                if (i3 < codePointCount && ((char) str.codePointAt(i3)) == '\n') {
                    i = i3;
                }
            } else if (codePointAt == '\n') {
                i2++;
            }
            i++;
        }
        return i2;
    }

    @Nullable
    public static String removeFromEnd(@Nullable String str, @Nullable String str2) {
        if (isEmptyOrNull(str) || isEmptyOrNull(str2)) {
            return str;
        }
        String trim = str.trim();
        String trim2 = str2.trim();
        return trim.endsWith(trim2) ? trim.substring(0, trim.length() - trim2.length()) : trim;
    }

    public static boolean isConsecutiveNumber(String str) {
        if (isEmptyAfterTrimOrNull(str) || str.length() == 1 || !isPositiveInt(str)) {
            return false;
        }
        int i = -1;
        if ((str.charAt(1) - '0') - (str.charAt(0) - '0') != -1) {
            i = 1;
        }
        for (int i2 = 1; i2 < str.length(); i2++) {
            if ((str.charAt(i2) - '0') - (str.charAt(i2 - 1) - '0') != i) {
                return false;
            }
        }
        return true;
    }

    public static boolean isPositiveInt(String str) {
        try {
            return Integer.parseInt(str) > 0;
        } catch (NumberFormatException unused) {
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
