package X;

import android.content.Context;
import android.os.Build;
import android.view.MenuItem;
import android.widget.PopupWindow;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.lang.reflect.Method;

@RestrictTo({AnonymousClass02C.LIBRARY_GROUP_PREFIX})
/* renamed from: X.1sb  reason: invalid class name and case insensitive filesystem */
public final class C11651sb extends C11691sf implements AnonymousClass1tS {
    public static Method A01;
    public AnonymousClass1tS A00;

    public C11651sb(@NonNull Context context, @Nullable int i, int i2) {
        super(context, null, i, i2);
    }

    public final void A01() {
        if (Build.VERSION.SDK_INT <= 28) {
            Method method = A01;
            if (method != null) {
                try {
                    method.invoke(this.A0A, false);
                } catch (Exception unused) {
                }
            }
        } else {
            this.A0A.setTouchModal(false);
        }
    }

    static {
        try {
            if (Build.VERSION.SDK_INT <= 28) {
                A01 = PopupWindow.class.getDeclaredMethod("setTouchModal", Boolean.TYPE);
            }
        } catch (NoSuchMethodException unused) {
        }
    }

    @Override // X.AnonymousClass1tS
    public final void A7C(@NonNull C11581sN r2, @NonNull MenuItem menuItem) {
        AnonymousClass1tS r0 = this.A00;
        if (r0 != null) {
            r0.A7C(r2, menuItem);
        }
    }

    @Override // X.AnonymousClass1tS
    public final void A7D(@NonNull C11581sN r2, @NonNull MenuItem menuItem) {
        AnonymousClass1tS r0 = this.A00;
        if (r0 != null) {
            r0.A7D(r2, menuItem);
        }
    }
}
