package X;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import androidx.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: X.02y  reason: invalid class name and case insensitive filesystem */
public class View$OnClickListenerC000402y implements View.OnClickListener {
    public Context A00;
    public Method A01;
    public final View A02;
    public final String A03;

    public final void onClick(@NonNull View view) {
        String A07;
        Method method = this.A01;
        if (method == null) {
            View view2 = this.A02;
            Context context = view2.getContext();
            for (Context context2 = context; context2 != null; context2 = ((ContextWrapper) context2).getBaseContext()) {
                try {
                    if (!context2.isRestricted() && (method = context2.getClass().getMethod(this.A03, View.class)) != null) {
                        this.A01 = method;
                        this.A00 = context2;
                    }
                } catch (NoSuchMethodException unused) {
                }
                if (!(context2 instanceof ContextWrapper)) {
                    break;
                }
            }
            int id = view2.getId();
            if (id == -1) {
                A07 = "";
            } else {
                A07 = AnonymousClass006.A07(" with id '", context.getResources().getResourceEntryName(id), "'");
            }
            throw new IllegalStateException("Could not find method " + this.A03 + "(View) in a parent or ancestor Context for android:onClick attribute defined on view " + view2.getClass() + A07);
        }
        try {
            method.invoke(this.A00, view);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Could not execute non-public method for android:onClick", e);
        } catch (InvocationTargetException e2) {
            throw new IllegalStateException("Could not execute method for android:onClick", e2);
        }
    }

    public View$OnClickListenerC000402y(@NonNull View view, @NonNull String str) {
        this.A02 = view;
        this.A03 = str;
    }
}
