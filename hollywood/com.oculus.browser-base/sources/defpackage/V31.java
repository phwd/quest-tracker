package defpackage;

import android.view.InflateException;
import android.view.MenuItem;
import java.lang.reflect.Method;

/* renamed from: V31  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class V31 implements MenuItem.OnMenuItemClickListener {
    public static final Class[] F = {MenuItem.class};
    public Object G;
    public Method H;

    public V31(Object obj, String str) {
        this.G = obj;
        Class<?> cls = obj.getClass();
        try {
            this.H = cls.getMethod(str, F);
        } catch (Exception e) {
            StringBuilder k = AbstractC2531fV.k("Couldn't resolve menu item onClick handler ", str, " in class ");
            k.append(cls.getName());
            InflateException inflateException = new InflateException(k.toString());
            inflateException.initCause(e);
            throw inflateException;
        }
    }

    public boolean onMenuItemClick(MenuItem menuItem) {
        try {
            if (this.H.getReturnType() == Boolean.TYPE) {
                return ((Boolean) this.H.invoke(this.G, menuItem)).booleanValue();
            }
            this.H.invoke(this.G, menuItem);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
