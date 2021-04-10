package com.facebook.ultralight.names;

import javax.annotation.Nullable;

public class UltralightNames {
    private static final String ARRAY_TYPE_SQUARE_BRACKETS_CLOSE = "$x5D";
    private static final String ARRAY_TYPE_SQUARE_BRACKETS_OPEN = "$x5B";
    public static final String BINDING_ID_SUFFIX = "BINDING_ID";
    private static final String COMMA = "$x2C";
    private static final String DOLLAR = "$x24";
    public static final String FQN_SEPARATOR = "_";
    public static final String GENERIC_TYPE_ANGLED_BRACKETS_CLOSE = "$x3E";
    public static final String GENERIC_TYPE_ANGLED_BRACKETS_OPEN = "$x3C";
    public static final String NAME_PART_SEPARATOR = "$xXX";
    public static final String NAME_PART_SEPARATOR_REGEX = "\\$xXX";
    public static final String ULTRALIGHT_RUNTIME_PREFIX = "$ul_";
    private static final String UNDERSCORE = "$x5F";

    public static String encodeString(String genericString) {
        return encode(genericString);
    }

    public static String encodeCollection(Class type, String erasureFqn) {
        StringBuilder sb = new StringBuilder();
        sb.append(encode(type.getCanonicalName())).append(GENERIC_TYPE_ANGLED_BRACKETS_OPEN).append(encode(erasureFqn)).append(GENERIC_TYPE_ANGLED_BRACKETS_CLOSE);
        return sb.toString();
    }

    @Nullable
    public static String encodeClassAndAnnotation(@Nullable String prefix, Class collectionType, String erasureFqn, @Nullable String bindingAnnotationFqn, @Nullable String namedAnnotationValue, @Nullable String suffix) {
        String collectionString = encodeCollection(collectionType, erasureFqn);
        String annotationString = encodeClassAndAnnotation(null, bindingAnnotationFqn, namedAnnotationValue);
        StringBuilder sb = new StringBuilder();
        if (prefix != null) {
            sb.append(prefix);
            sb.append(NAME_PART_SEPARATOR);
        }
        sb.append(collectionString);
        if (annotationString != null) {
            sb.append(NAME_PART_SEPARATOR);
            sb.append(annotationString);
        }
        if (suffix != null) {
            sb.append(NAME_PART_SEPARATOR);
            sb.append(suffix);
        }
        String results = sb.toString();
        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    @Nullable
    public static String encodeClassAndAnnotation(@Nullable String prefix, @Nullable String classFqn, @Nullable String bindingAnnotationFqn, @Nullable String namedAnnotationValue, @Nullable String suffix) {
        String encodedString = encodeClassAndAnnotation(classFqn, bindingAnnotationFqn, namedAnnotationValue);
        if (encodedString == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        if (prefix != null) {
            sb.append(prefix);
            sb.append(NAME_PART_SEPARATOR);
        }
        sb.append(encodedString);
        if (suffix != null) {
            sb.append(NAME_PART_SEPARATOR);
            sb.append(suffix);
        }
        String results = sb.toString();
        if (results.isEmpty()) {
            results = null;
        }
        return results;
    }

    @Nullable
    public static String encodeClassAndAnnotation(@Nullable String classFqn, @Nullable String bindingAnnotationFqn, @Nullable String namedAnnotationValue) {
        StringBuilder sb = new StringBuilder();
        if (classFqn != null) {
            sb.append(encode(classFqn));
        }
        if (bindingAnnotationFqn != null) {
            if (classFqn != null) {
                sb.append(NAME_PART_SEPARATOR);
            }
            sb.append(encode(bindingAnnotationFqn));
        }
        if (namedAnnotationValue != null) {
            sb.append(UNDERSCORE);
            sb.append(encode(namedAnnotationValue));
        }
        String results = sb.toString();
        if (results.isEmpty()) {
            return null;
        }
        return results;
    }

    private static String encode(String type) {
        return type.replace("$", DOLLAR).replace(FQN_SEPARATOR, UNDERSCORE).replace(".", FQN_SEPARATOR).replace(",", COMMA).replace("<", GENERIC_TYPE_ANGLED_BRACKETS_OPEN).replace(">", GENERIC_TYPE_ANGLED_BRACKETS_CLOSE).replace("[", ARRAY_TYPE_SQUARE_BRACKETS_OPEN).replace("]", ARRAY_TYPE_SQUARE_BRACKETS_CLOSE);
    }

    public static String decodeFromJavaName(String type) {
        return type.replace(ARRAY_TYPE_SQUARE_BRACKETS_CLOSE, "]").replace(ARRAY_TYPE_SQUARE_BRACKETS_OPEN, "[").replace(GENERIC_TYPE_ANGLED_BRACKETS_CLOSE, ">").replace(GENERIC_TYPE_ANGLED_BRACKETS_OPEN, "<").replace(COMMA, ",").replace(FQN_SEPARATOR, ".").replace(UNDERSCORE, FQN_SEPARATOR).replace(DOLLAR, "$");
    }
}
