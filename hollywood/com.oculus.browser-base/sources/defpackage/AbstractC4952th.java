package defpackage;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import android.os.UserHandle;
import java.lang.reflect.Method;
import java.util.concurrent.Executor;

/* renamed from: th  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public abstract class AbstractC4952th {

    /* renamed from: a  reason: collision with root package name */
    public static Method f11360a;

    public static boolean a(Context context, Intent intent, ServiceConnection serviceConnection, int i, Handler handler) {
        if (f11360a == null) {
            f11360a = Context.class.getDeclaredMethod("bindServiceAsUser", Intent.class, ServiceConnection.class, Integer.TYPE, Handler.class, UserHandle.class);
        }
        while (context instanceof ContextWrapper) {
            context = ((ContextWrapper) context).getBaseContext();
        }
        return ((Boolean) f11360a.invoke(context, intent, serviceConnection, Integer.valueOf(i), handler, Process.myUserHandle())).booleanValue();
    }

    public static boolean b(Context context, Intent intent, ServiceConnection serviceConnection, int i, Handler handler, Executor executor, String str) {
        if (c() && str != null) {
            return C4520r7.a(context, intent, i, str, executor, serviceConnection);
        }
        try {
            return a(context, intent, serviceConnection, i, handler);
        } catch (ReflectiveOperationException e) {
            try {
                return context.bindService(intent, serviceConnection, i);
            } catch (RuntimeException e2) {
                throw new RuntimeException(e2.getMessage(), e);
            }
        }
    }

    public static boolean c() {
        return Build.VERSION.SDK_INT >= 29;
    }
}
