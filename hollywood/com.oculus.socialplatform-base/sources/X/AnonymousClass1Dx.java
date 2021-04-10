package X;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.View;
import androidx.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* renamed from: X.1Dx  reason: invalid class name */
public class AnonymousClass1Dx implements View.OnClickListener {
    public Context A00;
    public Method A01;
    public final View A02;
    public final String A03;

    public final void onClick(@NonNull View view) {
        String A09;
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
                A09 = "";
            } else {
                A09 = AnonymousClass006.A09(" with id '", context.getResources().getResourceEntryName(id), "'");
            }
            StringBuilder sb = new StringBuilder("Could not find method ");
            sb.append(this.A03);
            sb.append("(View) in a parent or ancestor Context for android:onClick attribute defined on view ");
            sb.append(view2.getClass());
            sb.append(A09);
            throw new IllegalStateException(sb.toString());
        }
        try {
            method.invoke(this.A00, view);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Could not execute non-public method for android:onClick", e);
        } catch (InvocationTargetException e2) {
            throw new IllegalStateException("Could not execute method for android:onClick", e2);
        }
    }

    public AnonymousClass1Dx(@NonNull View view, @NonNull String str) {
        this.A02 = view;
        this.A03 = str;
    }
}
