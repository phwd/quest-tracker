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

    public static Key bindingIdToKey(int bindingId) {
        if (!BuildConstants.isDebugBuild() && !TestEnvironment.isTest()) {
            return null;
        }
        try {
            Field[] fields = UL.id.class.getFields();
            Field bindingField = null;
            int length = fields.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                Field field = fields[i];
                if (field.getInt(null) == bindingId) {
                    bindingField = field;
                    break;
                }
                i++;
            }
            if (bindingField != null) {
                return bindingIdToKey(bindingField.getName());
            }
            return null;
        } catch (ClassNotFoundException | IllegalAccessException | NoClassDefFoundError e) {
            return null;
        }
    }

    public static Key bindingIdToKey(String bindingId) throws ClassNotFoundException {
        Type type;
        String className = getClassNameFromBindingId(bindingId);
        if (className.startsWith("java.util.Set<")) {
            type = createParameterizedType(className);
        } else {
            type = Class.forName(className);
        }
        String annotationName = getAnnotationNameFromBindingId(bindingId);
        if (annotationName != null) {
            return Key.get(type, (Class<? extends Annotation>) Class.forName(annotationName));
        }
        return Key.get(type);
    }

    public static String getClassNameFromBindingId(String bindingId) {
        char c;
        String[] parts = bindingId.split(UltralightNames.NAME_PART_SEPARATOR_REGEX);
        String className = UltralightNames.decodeFromJavaName((parts.length > 1 ? parts[1] : UNKNOWN_BINDING_KEY_FORMAT_CLASS).split(UltralightNames.GENERIC_TYPE_ANGLED_BRACKETS_OPEN)[0]);
        StringBuilder sb = new StringBuilder();
        boolean foundCapital = false;
        int previousIndex = 0;
        int currentIndex = className.indexOf(46);
        while (previousIndex < className.length()) {
            if (currentIndex == -1) {
                currentIndex = className.length();
            }
            String part = className.substring(previousIndex, currentIndex);
            if (previousIndex != 0) {
                if (foundCapital) {
                    c = '$';
                } else {
                    c = '.';
                }
                sb.append(c);
            }
            sb.append(part);
            if (Character.isUpperCase(part.charAt(0))) {
                foundCapital = true;
            }
            if (part.contains("<") || part.contains(">")) {
                foundCapital = false;
            }
            previousIndex = currentIndex + 1;
            currentIndex = className.indexOf(46, previousIndex);
        }
        return sb.toString();
    }

    public static int getBindingIdFromClasses(Class clazz, @Nullable Class annotation) {
        return 0;
    }

    @Nullable
    public static String getAnnotationNameFromBindingId(String bindingId) {
        String[] parts = bindingId.split(UltralightNames.NAME_PART_SEPARATOR_REGEX);
        if (parts.length != 4) {
            return null;
        }
        return UltralightNames.decodeFromJavaName(parts[2]);
    }

    private static Type createParameterizedType(String className) throws ClassNotFoundException {
        String[] parts = className.split("[<>]");
        return new MoreTypes.ParameterizedTypeImpl(null, Class.forName(parts[0]), Class.forName(parts.length >= 1 ? parts[1] : "unknown_binding_id_format"));
    }
}
