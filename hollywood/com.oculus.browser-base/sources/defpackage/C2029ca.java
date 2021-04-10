package defpackage;

import android.app.Activity;
import android.view.Window;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;
import org.chromium.base.ApplicationStatus;

/* renamed from: ca  reason: default package and case insensitive filesystem */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class C2029ca implements InvocationHandler {

    /* renamed from: a  reason: collision with root package name */
    public final Window.Callback f9615a;
    public final Activity b;

    public C2029ca(Activity activity, Window.Callback callback) {
        this.f9615a = callback;
        this.b = activity;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) {
        if (!method.getName().equals("onWindowFocusChanged") || objArr.length != 1 || !(objArr[0] instanceof Boolean)) {
            try {
                return method.invoke(this.f9615a, objArr);
            } catch (InvocationTargetException e) {
                if (e.getCause() instanceof AbstractMethodError) {
                    throw e.getCause();
                }
                throw e;
            }
        } else {
            boolean booleanValue = ((Boolean) objArr[0]).booleanValue();
            this.f9615a.onWindowFocusChanged(booleanValue);
            Iterator it = ApplicationStatus.i.iterator();
            while (true) {
                C1261Uq0 uq0 = (C1261Uq0) it;
                if (!uq0.hasNext()) {
                    return null;
                }
                ((AbstractC2200da) uq0.next()).e(this.b, booleanValue);
            }
        }
    }
}
