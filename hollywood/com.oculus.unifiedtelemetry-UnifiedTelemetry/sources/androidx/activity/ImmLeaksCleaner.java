package androidx.activity;

import X.AN;
import X.AR;
import X.Zx;
import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Field;

@RequiresApi(19)
public final class ImmLeaksCleaner implements Zx {
    public static int A01;
    public static Field A02;
    public static Field A03;
    public static Field A04;
    public final Activity A00;

    @Override // X.Zx
    public final void A42(@NonNull AR ar, @NonNull AN an) {
        if (an == AN.ON_DESTROY) {
            if (A01 == 0) {
                try {
                    A01 = 2;
                    Field declaredField = InputMethodManager.class.getDeclaredField("mServedView");
                    A04 = declaredField;
                    declaredField.setAccessible(true);
                    Field declaredField2 = InputMethodManager.class.getDeclaredField("mNextServedView");
                    A03 = declaredField2;
                    declaredField2.setAccessible(true);
                    Field declaredField3 = InputMethodManager.class.getDeclaredField("mH");
                    A02 = declaredField3;
                    declaredField3.setAccessible(true);
                    A01 = 1;
                } catch (NoSuchFieldException unused) {
                }
            }
            if (A01 == 1) {
                InputMethodManager inputMethodManager = (InputMethodManager) this.A00.getSystemService("input_method");
                try {
                    Object obj = A02.get(inputMethodManager);
                    if (obj != null) {
                        synchronized (obj) {
                            try {
                                View view = (View) A04.get(inputMethodManager);
                                if (view != null) {
                                    if (!view.isAttachedToWindow()) {
                                        A03.set(inputMethodManager, null);
                                        inputMethodManager.isActive();
                                    }
                                }
                            } catch (ClassCastException | IllegalAccessException unused2) {
                            }
                        }
                    }
                } catch (IllegalAccessException unused3) {
                }
            }
        }
    }
}
