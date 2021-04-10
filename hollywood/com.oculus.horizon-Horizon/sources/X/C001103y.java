package X;

import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import androidx.annotation.GuardedBy;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

/* renamed from: X.03y  reason: invalid class name and case insensitive filesystem */
public final class C001103y {
    @GuardedBy("sEnabledNotificationListenersLock")
    public static Set<String> A02 = new HashSet();
    public static final Object A03 = new Object();
    public static final Object A04 = new Object();
    public final NotificationManager A00;
    public final Context A01;

    public final boolean A00() {
        if (Build.VERSION.SDK_INT >= 24) {
            return this.A00.areNotificationsEnabled();
        }
        Context context = this.A01;
        Object systemService = context.getSystemService("appops");
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String packageName = context.getApplicationContext().getPackageName();
        int i = applicationInfo.uid;
        try {
            Class<?> cls = Class.forName(AppOpsManager.class.getName());
            Class<?> cls2 = Integer.TYPE;
            if (((Integer) cls.getMethod("checkOpNoThrow", cls2, cls2, String.class).invoke(systemService, Integer.valueOf(((Integer) cls.getDeclaredField("OP_POST_NOTIFICATION").get(Integer.class)).intValue()), Integer.valueOf(i), packageName)).intValue() != 0) {
                return false;
            }
            return true;
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException | NoSuchMethodException | RuntimeException | InvocationTargetException unused) {
            return true;
        }
    }

    public C001103y(Context context) {
        this.A01 = context;
        this.A00 = (NotificationManager) context.getSystemService("notification");
    }
}
