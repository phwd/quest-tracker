package com.facebook.acra;

import android.content.Context;
import android.content.pm.PackageManager;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import javax.annotation.Nullable;
import org.json.JSONArray;

public class ReflectionCollector {
    public static String collectConstants(Class<?> cls) {
        StringBuilder sb = new StringBuilder();
        Field[] fields = cls.getFields();
        for (Field field : fields) {
            sb.append(field.getName());
            sb.append("=");
            try {
                Object obj = field.get(null);
                if (obj instanceof Object[]) {
                    sb.append(new JSONArray((Collection) Arrays.asList((Object[]) obj)).toString());
                } else {
                    sb.append(obj.toString());
                }
            } catch (Exception unused) {
                sb.append("N/A");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static String collectStaticGettersResults(Class<?> cls) {
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

    @Nullable
    static String getPlayServicesVersion(Context context) {
        try {
            Class<?> cls = Class.forName("com.google.android.gms.common.GoogleApiAvailability");
            Object invoke = cls.getMethod("getInstance", new Class[0]).invoke(cls, new Object[0]);
            int intValue = ((Integer) cls.getMethod("isGooglePlayServicesAvailable", Context.class).invoke(invoke, context)).intValue();
            Class<?> cls2 = Class.forName("com.google.android.gms.common.ConnectionResult");
            if (intValue != cls2.getField("SUCCESS").getInt(cls2)) {
                return null;
            }
            return String.valueOf((long) context.getPackageManager().getPackageInfo((String) cls.getField("GOOGLE_PLAY_SERVICES_PACKAGE").get(cls), 0).versionCode);
        } catch (PackageManager.NameNotFoundException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException unused) {
            return null;
        }
    }
}
