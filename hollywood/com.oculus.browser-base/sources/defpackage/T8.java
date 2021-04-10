package defpackage;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: T8  reason: default package */
/* compiled from: chromium-OculusBrowser.apk-stable-281887347 */
public class T8 implements View.OnClickListener {
    public final View F;
    public final String G;
    public Method H;
    public Context I;

    public T8(View view, String str) {
        this.F = view;
        this.G = str;
    }

    public void onClick(View view) {
        String str;
        Method method;
        if (this.H == null) {
            Context context = this.F.getContext();
            while (context != null) {
                try {
                    if (!context.isRestricted() && (method = context.getClass().getMethod(this.G, View.class)) != null) {
                        this.H = method;
                        this.I = context;
                    }
                } catch (NoSuchMethodException unused) {
                }
                context = context instanceof ContextWrapper ? ((ContextWrapper) context).getBaseContext() : null;
            }
            int id = this.F.getId();
            if (id == -1) {
                str = "";
            } else {
                StringBuilder i = AbstractC2531fV.i(" with id '");
                i.append(this.F.getContext().getResources().getResourceEntryName(id));
                i.append("'");
                str = i.toString();
            }
            StringBuilder i2 = AbstractC2531fV.i("Could not find method ");
            i2.append(this.G);
            i2.append("(View) in a parent or ancestor Context for android:onClick attribute defined on view ");
            i2.append(this.F.getClass());
            i2.append(str);
            throw new IllegalStateException(i2.toString());
        }
        try {
            this.H.invoke(this.I, view);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Could not execute non-public method for android:onClick", e);
        } catch (InvocationTargetException e2) {
            throw new IllegalStateException("Could not execute method for android:onClick", e2);
        }
    }
}
