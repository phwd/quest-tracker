package com.facebook.inject;

import com.facebook.common.build.BuildConstants;
import com.facebook.testenv.TestEnvironment;
import com.facebook.ultralight.UL;
import com.facebook.ultralight.names.UltralightNames;
import com.google.inject.Key;
import com.google.inject.internal.MoreTypes;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import javax.annotation.Nullable;

public class RuntimeBindingIdUtils {
    private static final String UNKNOWN_BINDING_KEY_FORMAT_CLASS = "unknown.binding.key.format";

    public static int getBindingIdFromClasses(Class cls, @Nullable Class cls2) {
        return 0;
    }

    public static Key bindingIdToKey(int i) {
        Field field;
        if (!BuildConstants.isDebugBuild() && !TestEnvironment.isTest()) {
            return null;
        }
        try {
            Field[] fields = UL.id.class.getFields();
            int length = fields.length;
            int i2 = 0;
            while (true) {
                if (i2 >= length) {
                    field = null;
                    break;
                }
                field = fields[i2];
                if (field.getInt(null) == i) {
                    break;
                }
                i2++;
            }
            if (field == null) {
                return null;
            }
            return bindingIdToKey(field.getName());
        } catch (ClassNotFoundException | IllegalAccessException | NoClassDefFoundError unused) {
            return null;
        }
    }

    public static Key bindingIdToKey(String str) throws ClassNotFoundException {
        Type type;
        String classNameFromBindingId = getClassNameFromBindingId(str);
        if (classNameFromBindingId.startsWith("java.util.Set<")) {
            type = createParameterizedType(classNameFromBindingId);
        } else {
            type = Class.forName(classNameFromBindingId);
        }
        String annotationNameFromBindingId = getAnnotationNameFromBindingId(str);
        if (annotationNameFromBindingId != null) {
            return Key.get(type, (Class<? extends Annotation>) Class.forName(annotationNameFromBindingId));
        }
        return Key.get(type);
    }

    public static String getClassNameFromBindingId(String str) {
        String[] split = str.split("_ULSEP_");
        String decodeFromJavaName = UltralightNames.decodeFromJavaName(split.length > 1 ? split[1] : UNKNOWN_BINDING_KEY_FORMAT_CLASS);
        StringBuilder sb = new StringBuilder();
        int indexOf = decodeFromJavaName.indexOf(46);
        int i = 0;
        boolean z = false;
        while (i < decodeFromJavaName.length()) {
            if (indexOf == -1) {
                indexOf = decodeFromJavaName.length();
            }
            String substring = decodeFromJavaName.substring(i, indexOf);
            if (i != 0) {
                sb.append(z ? '$' : '.');
            }
            sb.append(substring);
            if (Character.isUpperCase(substring.charAt(0))) {
                z = true;
            }
            if (substring.contains("<") || substring.contains(">")) {
                z = false;
            }
            i = indexOf + 1;
            indexOf = decodeFromJavaName.indexOf(46, i);
        }
        return sb.toString();
    }

    @Nullable
    public static String getAnnotationNameFromBindingId(String str) {
        String[] split = str.split("_ULSEP_");
        if (split.length != 4) {
            return null;
        }
        return UltralightNames.decodeFromJavaName(split[2]);
    }

    private static Type createParameterizedType(String str) throws ClassNotFoundException {
        String[] split = str.split("[<>]");
        return new MoreTypes.ParameterizedTypeImpl(null, Class.forName(split[0]), Class.forName(split.length >= 1 ? split[1] : "unknown_binding_id_format"));
    }
}
