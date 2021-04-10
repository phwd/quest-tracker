package defpackage;

import J.N;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Display;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ApplicationStatus;
import org.chromium.ui.display.DisplayAndroidManager;

/* renamed from: km0  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C3432km0 implements Z9 {
    public static final C3432km0 F = new C3432km0();
    public Boolean G;
    public WeakReference H;

    public static String b(ActivityManager.AppTask appTask) {
        ComponentName componentName;
        ActivityManager.RecentTaskInfo b = AbstractC5028u6.b(appTask);
        if (b == null || (componentName = b.baseActivity) == null) {
            return "";
        }
        String className = componentName.getClassName();
        return TextUtils.equals(className, "com.google.android.apps.chrome.Main") ? AbstractActivityC2601fu.class.getName() : className;
    }

    public static Bundle d(Activity activity) {
        int i;
        if (!F.h(activity)) {
            return null;
        }
        if (N.M09VlOh_("AndroidMultipleDisplay")) {
            List e = AbstractC3153j7.e(activity);
            Display a2 = DisplayAndroidManager.a(activity);
            ArrayList arrayList = (ArrayList) e;
            if (arrayList.size() != 0) {
                Iterator it = arrayList.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    i = ((Integer) it.next()).intValue();
                    if (i != a2.getDisplayId()) {
                        break;
                    }
                }
            }
        }
        i = -1;
        if (i == -1) {
            throw new IllegalStateException("Attempting to open window in other display, but one is not found");
        } else if (Build.VERSION.SDK_INT >= 26) {
            return C2641g7.a(i);
        } else {
            return null;
        }
    }

    public static boolean g(Activity activity) {
        if (activity == null) {
            return false;
        }
        int e = ApplicationStatus.e(activity);
        return e == 3 || e == 4;
    }

    public static boolean l() {
        Iterator it = ((ArrayList) ApplicationStatus.d()).iterator();
        while (it.hasNext()) {
            if (((Activity) it.next()).getClass().equals(AbstractActivityC2601fu.class)) {
                return true;
            }
        }
        return false;
    }

    public static void m(Intent intent, Activity activity, Class cls) {
        intent.setClass(activity, cls);
        intent.addFlags(4096);
        if (cls.equals(AbstractActivityC2601fu.class) && l()) {
            intent.setFlags(intent.getFlags() & -4097);
        }
        intent.putExtra("com.android.browser.application_id", activity.getPackageName());
        intent.putExtra("create_new_tab", true);
    }

    public boolean a(Context context) {
        Iterator it = ((ArrayList) ApplicationStatus.d()).iterator();
        boolean z = false;
        boolean z2 = false;
        while (it.hasNext()) {
            Activity activity = (Activity) it.next();
            if (activity.getClass().equals(AbstractActivityC2601fu.class)) {
                z = true;
            } else if (activity.getClass().equals(AbstractActivityC2430eu.class)) {
                z2 = true;
            }
        }
        if (z && z2) {
            return true;
        }
        for (ActivityManager.AppTask appTask : ((ActivityManager) context.getSystemService("activity")).getAppTasks()) {
            String b = b(appTask);
            if (TextUtils.equals(b, AbstractActivityC2601fu.class.getName())) {
                z = true;
            } else if (TextUtils.equals(b, AbstractActivityC2430eu.class.getName())) {
                z2 = true;
            }
        }
        return z && z2;
    }

    public Class c(Activity activity) {
        if (!(activity instanceof AbstractActivityC2601fu)) {
            return null;
        }
        this.G = Boolean.TRUE;
        ApplicationStatus.g.b(F);
        return AbstractActivityC2430eu.class;
    }

    public Class e(Intent intent, Context context) {
        AbstractActivityC2601fu fuVar;
        Boolean bool = this.G;
        if (bool != null && !bool.booleanValue()) {
            return AbstractActivityC2601fu.class;
        }
        if (intent != null && U20.o(intent, "org.chromium.chrome.browser.window_id")) {
            int h = U20.h(intent, "org.chromium.chrome.browser.window_id", 0);
            if (h == 1) {
                return AbstractActivityC2601fu.class;
            }
            if (h == 2) {
                return AbstractActivityC2430eu.class;
            }
        }
        boolean f = f(AbstractActivityC2430eu.class.getName(), context);
        if (!f) {
            this.G = Boolean.FALSE;
            return AbstractActivityC2601fu.class;
        }
        boolean f2 = f(AbstractActivityC2601fu.class.getName(), context);
        if (!f2) {
            return AbstractActivityC2430eu.class;
        }
        Iterator it = ((ArrayList) ApplicationStatus.d()).iterator();
        Activity activity = null;
        Activity activity2 = null;
        while (it.hasNext()) {
            Activity activity3 = (Activity) it.next();
            if (activity3.getClass().equals(AbstractActivityC2601fu.class)) {
                activity = activity3;
            } else if (activity3.getClass().equals(AbstractActivityC2430eu.class)) {
                activity2 = activity3;
            }
        }
        boolean g = g(activity);
        if (!(g(activity2) ^ g)) {
            WeakReference weakReference = this.H;
            if (weakReference == null || (fuVar = (AbstractActivityC2601fu) weakReference.get()) == null) {
                return AbstractActivityC2601fu.class;
            }
            Class<?> cls = fuVar.getClass();
            if ((!f2 || !cls.equals(AbstractActivityC2601fu.class)) && f && cls.equals(AbstractActivityC2430eu.class)) {
                return AbstractActivityC2430eu.class;
            }
            return AbstractActivityC2601fu.class;
        } else if (g) {
            return AbstractActivityC2601fu.class;
        } else {
            return AbstractActivityC2430eu.class;
        }
    }

    public final boolean f(String str, Context context) {
        for (ActivityManager.AppTask appTask : ((ActivityManager) context.getSystemService("activity")).getAppTasks()) {
            if (TextUtils.equals(b(appTask), str)) {
                return true;
            }
        }
        return false;
    }

    public boolean h(Activity activity) {
        if (N.M09VlOh_("AndroidMultipleDisplay") && ((ArrayList) AbstractC3153j7.e(activity)).size() == 2) {
            return true;
        }
        return false;
    }

    public boolean i(Activity activity) {
        if (activity == null) {
            return false;
        }
        return activity.isInMultiWindowMode();
    }

    public boolean j(Activity activity) {
        if (activity == null) {
            return false;
        }
        try {
            PackageManager packageManager = activity.getPackageManager();
            if (packageManager.hasSystemFeature((String) packageManager.getClass().getField("FEATURE_MULTIWINDOW").get(null)) && (((Integer) activity.getClass().getMethod("getWindowMode", null).invoke(activity, null)).intValue() & ((Integer) Class.forName("android.view.WindowManagerPolicy").getField("WINDOW_MODE_FREESTYLE").get(null)).intValue()) != 0) {
                return true;
            }
            return false;
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException unused) {
            return false;
        }
    }

    public boolean k(Activity activity) {
        if ((i(activity) || h(activity)) && c(activity) != null) {
            return true;
        }
        return false;
    }

    @Override // defpackage.Z9
    public void t(Activity activity, int i) {
        if (i == 3 && (activity instanceof AbstractActivityC2601fu)) {
            this.H = new WeakReference((AbstractActivityC2601fu) activity);
        }
    }
}
