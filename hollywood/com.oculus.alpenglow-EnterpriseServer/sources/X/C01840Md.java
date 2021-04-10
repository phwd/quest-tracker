package X;

import android.content.Context;
import android.os.Build;
import android.view.MenuItem;
import android.widget.PopupWindow;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.lang.reflect.Method;

@RestrictTo({AnonymousClass02D.LIBRARY_GROUP_PREFIX})
/* renamed from: X.0Md  reason: invalid class name and case insensitive filesystem */
public final class C01840Md extends C04080dy implements AbstractC004304x {
    public static Method A01;
    public AbstractC004304x A00;

    public C01840Md(@NonNull Context context, @Nullable int i, int i2) {
        super(context, null, i, i2);
    }

    public final void A02() {
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

    @Override // X.C04080dy
    @NonNull
    public final C003004g A00(Context context, boolean z) {
        C04070dx r0 = new C04070dx(context, z);
        r0.A00 = this;
        return r0;
    }

    @Override // X.AbstractC004304x
    public final void A6B(@NonNull C04280eZ r2, @NonNull MenuItem menuItem) {
        AbstractC004304x r0 = this.A00;
        if (r0 != null) {
            r0.A6B(r2, menuItem);
        }
    }

    @Override // X.AbstractC004304x
    public final void A6C(@NonNull C04280eZ r2, @NonNull MenuItem menuItem) {
        AbstractC004304x r0 = this.A00;
        if (r0 != null) {
            r0.A6C(r2, menuItem);
        }
    }
}
