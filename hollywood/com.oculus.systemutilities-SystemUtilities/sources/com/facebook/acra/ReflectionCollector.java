package com.facebook.acra;

import android.content.Context;
import android.content.pm.PackageManager;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import org.json.JSONArray;

public class ReflectionCollector {
    public static String collectConstants(Class<?> someClass) {
        StringBuilder result = new StringBuilder();
        Field[] fields = someClass.getFields();
        for (Field field : fields) {
            result.append(field.getName()).append("=");
            try {
                Object obj = field.get(null);
                if (obj instanceof Object[]) {
                    result.append(new JSONArray((Collection) Arrays.asList((Object[]) obj)).toString());
                } else {
                    result.append(obj.toString());
                }
            } catch (Exception e) {
                result.append("N/A");
            }
            result.append("\n");
        }
        return result.toString();
    }

    public static String collectStaticGettersResults(Class<?> someClass) {
        StringBuilder result = new StringBuilder();
        Method[] methods = someClass.getMethods();
        for (Method method : methods) {
            if (method.getParameterTypes().length == 0 && ((method.getName().startsWith("get") || method.getName().startsWith("is")) && !method.getName().equals("getClass"))) {
                try {
                    result.append(method.getName()).append('=').append(method.invoke(null, null)).append("\n");
                } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                }
            }
        }
        return result.toString();
    }

    static String getPlayServicesVersion(Context context) {
        try {
            Class apiAvailabilityClass = Class.forName("com.google.android.gms.common.GoogleApiAvailability");
            Object apiAvailability = apiAvailabilityClass.getMethod("getInstance", new Class[0]).invoke(apiAvailabilityClass, new Object[0]);
            int status = ((Integer) apiAvailabilityClass.getMethod("isGooglePlayServicesAvailable", Context.class).invoke(apiAvailability, context)).intValue();
            Class connectionClass = Class.forName("com.google.android.gms.common.ConnectionResult");
            if (status == connectionClass.getField("SUCCESS").getInt(connectionClass)) {
                return String.valueOf((long) context.getPackageManager().getPackageInfo((String) apiAvailabilityClass.getField("GOOGLE_PLAY_SERVICES_PACKAGE").get(apiAvailabilityClass), 0).versionCode);
            }
        } catch (PackageManager.NameNotFoundException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException e) {
        }
        return null;
    }
}
