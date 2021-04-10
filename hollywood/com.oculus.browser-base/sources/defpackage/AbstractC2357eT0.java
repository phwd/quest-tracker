package defpackage;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import java.lang.reflect.Field;

/* renamed from: eT0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC2357eT0 {
    public static String a(Context context) {
        String string;
        StringBuilder sb = new StringBuilder();
        Field[] fields = Settings.Secure.class.getFields();
        for (Field field : fields) {
            try {
                if (!field.isAnnotationPresent(Deprecated.class) && field.getType() == String.class) {
                    if ((!field.getName().startsWith("WIFI_AP")) && (string = Settings.Secure.getString(context.getContentResolver(), (String) field.get(null))) != null) {
                        sb.append(field.getName());
                        sb.append("=");
                        sb.append((Object) string);
                        sb.append("\n");
                    }
                }
            } catch (Exception e) {
                String str = AbstractC1585a.f9392a;
                Log.w(str, "Error : ", e);
                Log.w(str, "Skipping key: " + field + ", key won't be logged in crash report.");
            }
        }
        return sb.toString();
    }

    public static String b(Context context) {
        String string;
        StringBuilder sb = new StringBuilder();
        Field[] fields = Settings.System.class.getFields();
        for (Field field : fields) {
            try {
                if (!field.isAnnotationPresent(Deprecated.class) && field.getType() == String.class && (string = Settings.System.getString(context.getContentResolver(), (String) field.get(null))) != null) {
                    sb.append(field.getName());
                    sb.append("=");
                    sb.append((Object) string);
                    sb.append("\n");
                }
            } catch (Exception e) {
                String str = AbstractC1585a.f9392a;
                Log.w(str, "Error : ", e);
                Log.w(str, "Skipping key: " + field + ", key won't be logged in crash report.");
            }
        }
        return sb.toString();
    }
}
