package X;

import android.view.InflateException;
import android.view.MenuItem;
import java.lang.reflect.Method;

/* renamed from: X.03H  reason: invalid class name */
public class AnonymousClass03H implements MenuItem.OnMenuItemClickListener {
    public static final Class<?>[] A02 = {MenuItem.class};
    public Object A00;
    public Method A01;

    public final boolean onMenuItemClick(MenuItem menuItem) {
        try {
            if (this.A01.getReturnType() == Boolean.TYPE) {
                return ((Boolean) this.A01.invoke(this.A00, menuItem)).booleanValue();
            }
            this.A01.invoke(this.A00, menuItem);
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public AnonymousClass03H(Object obj, String str) {
        this.A00 = obj;
        Class<?> cls = obj.getClass();
        try {
            this.A01 = cls.getMethod(str, A02);
        } catch (Exception e) {
            InflateException inflateException = new InflateException(AnonymousClass006.A08("Couldn't resolve menu item onClick handler ", str, " in class ", cls.getName()));
            inflateException.initCause(e);
            throw inflateException;
        }
    }
}
