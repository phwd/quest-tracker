package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

/* renamed from: wE  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC5392wE {
    public static String a(Context context) {
        int i;
        try {
            i = Build.VERSION.class.getField("SDK_INT").getInt(null);
        } catch (SecurityException unused) {
            i = Integer.parseInt(Build.VERSION.SDK);
        } catch (NoSuchFieldException unused2) {
            i = Integer.parseInt(Build.VERSION.SDK);
        } catch (IllegalArgumentException unused3) {
            i = Integer.parseInt(Build.VERSION.SDK);
        } catch (IllegalAccessException unused4) {
            i = Integer.parseInt(Build.VERSION.SDK);
        }
        if (i < 5) {
            return "Data available only with API Level > 5";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            Object[] objArr = (Object[]) PackageManager.class.getMethod("getSystemAvailableFeatures", null).invoke(context.getPackageManager(), new Object[0]);
            if (objArr != null) {
                for (Object obj : objArr) {
                    String str = (String) obj.getClass().getField("name").get(obj);
                    if (str != null) {
                        stringBuffer.append(str);
                    } else {
                        stringBuffer.append("glEsVersion = ");
                        stringBuffer.append((String) obj.getClass().getMethod("getGlEsVersion", null).invoke(obj, new Object[0]));
                    }
                    stringBuffer.append("\n");
                }
            }
        } catch (Throwable th) {
            String str2 = AbstractC1585a.f9392a;
            StringBuilder i2 = AbstractC2531fV.i("Couldn't retrieve device features for ");
            i2.append(context.getPackageName());
            Log.w(str2, i2.toString(), th);
            stringBuffer.append("Could not retrieve data: ");
            stringBuffer.append(th.getMessage());
        }
        return stringBuffer.toString();
    }
}
