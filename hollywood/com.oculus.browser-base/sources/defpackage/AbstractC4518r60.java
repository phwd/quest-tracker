package defpackage;

import android.animation.Animator;
import android.content.Context;
import android.view.ActionMode;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: r60  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4518r60 {
    public static Object a(Object obj, String str) {
        Field declaredField = obj.getClass().getDeclaredField(str);
        declaredField.setAccessible(true);
        return declaredField.get(obj);
    }

    public static void b(Context context, ActionMode actionMode) {
        boolean z;
        int i;
        String packageName = context.getPackageName();
        int a2 = AbstractC4652ru0.a(context, packageName);
        if (a2 != -1 && (i = context.getApplicationInfo().targetSdkVersion) >= 23 && i <= 24 && "com.lge.email".equals(packageName) && a2 <= 67502100) {
            AbstractC1220Ua0.f("Ime", AbstractC2531fV.w("Working around action mode LG Email bug in WebView (http://crbug.com/651706). APK name: com.lge.email, versionCode: ", a2), new Object[0]);
            z = true;
        } else {
            z = false;
        }
        if (z) {
            try {
                c(actionMode, "mCallback", new C4006o60((ActionMode.Callback2) a(actionMode, "mCallback")));
                Object a3 = a(actionMode, "mFloatingToolbar");
                Object a4 = a(a3, "mPopup");
                ViewGroup viewGroup = (ViewGroup) a(a4, "mContentContainer");
                Method declaredMethod = a3.getClass().getDeclaredMethod("createExitAnimation", View.class, Integer.TYPE, Animator.AnimatorListener.class);
                declaredMethod.setAccessible(true);
                c(a4, "mDismissAnimation", declaredMethod.invoke(null, viewGroup, 150, new C4348q60((PopupWindow) a(a4, "mPopupWindow"), viewGroup)));
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | NoSuchMethodException | InvocationTargetException unused) {
            } catch (Exception e) {
                AbstractC1220Ua0.f("Ime", "Error occurred during LGEmailActionModeWorkaround: ", e);
            }
        }
    }

    public static void c(Object obj, String str, Object obj2) {
        Field declaredField = obj.getClass().getDeclaredField(str);
        declaredField.setAccessible(true);
        declaredField.set(obj, obj2);
    }
}
