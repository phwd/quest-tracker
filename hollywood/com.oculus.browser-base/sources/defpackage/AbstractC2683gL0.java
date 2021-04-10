package defpackage;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: gL0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2683gL0 {
    public static String a(Class cls) {
        StringBuilder sb = new StringBuilder();
        Field[] fields = cls.getFields();
        for (Field field : fields) {
            sb.append(field.getName());
            sb.append("=");
            try {
                sb.append(field.get(null).toString());
            } catch (Exception unused) {
                sb.append("N/A");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static String b(Class cls) {
        StringBuilder sb = new StringBuilder();
        Method[] methods = cls.getMethods();
        for (Method method : methods) {
            if (method.getParameterTypes().length == 0 && ((method.getName().startsWith("get") || method.getName().startsWith("is")) && !method.getName().equals("getClass"))) {
                try {
                    sb.append(method.getName());
                    sb.append('=');
                    sb.append(method.invoke(null, null));
                    sb.append("\n");
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException unused) {
                }
            }
        }
        return sb.toString();
    }
}
