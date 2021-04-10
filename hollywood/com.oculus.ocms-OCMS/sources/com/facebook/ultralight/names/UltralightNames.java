package com.facebook.ultralight.names;

import javax.annotation.Nullable;

public class UltralightNames {
    private static final String ARRAY_TYPE_SQUARE_BRACKETS_CLOSE = "_ULCB_";
    private static final String ARRAY_TYPE_SQUARE_BRACKETS_OPEN = "_ULOB_";
    public static final String BINDING_ID_SUFFIX = "BINDING_ID";
    private static final String COMMA = "_ULCOMMA_";
    private static final String DOLLAR = "_ULDOL_";
    public static final String FQN_SEPARATOR = "_";
    public static final String GENERIC_TYPE_ANGLED_BRACKETS_CLOSE = "_ULGT_";
    public static final String GENERIC_TYPE_ANGLED_BRACKETS_OPEN = "_ULLT_";
    public static final String NAME_PART_SEPARATOR = "_ULSEP_";
    public static final String NAME_PART_SEPARATOR_REGEX = "_ULSEP_";
    public static final String ULTRALIGHT_RUNTIME_PREFIX = "_UL_";
    private static final String UNDERSCORE = "_ULUNDERSCORE_";

    public static String encodeString(String str) {
        return encode(str);
    }

    public static String encodeCollection(Class cls, String str) {
        return encode(cls.getCanonicalName()) + GENERIC_TYPE_ANGLED_BRACKETS_OPEN + encode(str) + GENERIC_TYPE_ANGLED_BRACKETS_CLOSE;
    }

    @Nullable
    public static String encodeClassAndAnnotation(@Nullable String str, Class cls, String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        String encodeCollection = encodeCollection(cls, str2);
        String encodeClassAndAnnotation = encodeClassAndAnnotation(null, str3, str4);
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            sb.append(str);
            sb.append("_ULSEP_");
        }
        sb.append(encodeCollection);
        if (encodeClassAndAnnotation != null) {
            sb.append("_ULSEP_");
            sb.append(encodeClassAndAnnotation);
        }
        if (str5 != null) {
            sb.append("_ULSEP_");
            sb.append(str5);
        }
        String sb2 = sb.toString();
        if (sb2.isEmpty()) {
            return null;
        }
        return sb2;
    }

    @Nullable
    public static String encodeClassAndAnnotation(@Nullable String str, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable String str5) {
        String encodeClassAndAnnotation = encodeClassAndAnnotation(str2, str3, str4);
        if (encodeClassAndAnnotation == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            sb.append(str);
            sb.append("_ULSEP_");
        }
        sb.append(encodeClassAndAnnotation);
        if (str5 != null) {
            sb.append("_ULSEP_");
            sb.append(str5);
        }
        String sb2 = sb.toString();
        if (sb2.isEmpty()) {
            return null;
        }
        return sb2;
    }

    @Nullable
    public static String encodeClassAndAnnotation(@Nullable String str, @Nullable String str2, @Nullable String str3) {
        StringBuilder sb = new StringBuilder();
        if (str != null) {
            sb.append(encode(str));
        }
        if (str2 != null) {
            if (str != null) {
                sb.append("_ULSEP_");
            }
            sb.append(encode(str2));
        }
        if (str3 != null) {
            sb.append(UNDERSCORE);
            sb.append(encode(str3));
        }
        String sb2 = sb.toString();
        if (sb2.isEmpty()) {
            return null;
        }
        return sb2;
    }

    private static String encode(String str) {
        return str.replace(FQN_SEPARATOR, UNDERSCORE).replace("$", DOLLAR).replace(".", FQN_SEPARATOR).replace(",", COMMA).replace("<", GENERIC_TYPE_ANGLED_BRACKETS_OPEN).replace(">", GENERIC_TYPE_ANGLED_BRACKETS_CLOSE).replace("[", ARRAY_TYPE_SQUARE_BRACKETS_OPEN).replace("]", ARRAY_TYPE_SQUARE_BRACKETS_CLOSE);
    }

    public static String decodeFromJavaName(String str) {
        String[] split = str.replace(ARRAY_TYPE_SQUARE_BRACKETS_CLOSE, "]").replace(ARRAY_TYPE_SQUARE_BRACKETS_OPEN, "[").replace(GENERIC_TYPE_ANGLED_BRACKETS_CLOSE, ">").replace(GENERIC_TYPE_ANGLED_BRACKETS_OPEN, "<").replace(COMMA, ",").split(UNDERSCORE);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < split.length; i++) {
            sb.append(split[i].replace(DOLLAR, "$").replace(FQN_SEPARATOR, "."));
            if (i < split.length - 1) {
                sb.append('_');
            }
        }
        return sb.toString();
    }
}
